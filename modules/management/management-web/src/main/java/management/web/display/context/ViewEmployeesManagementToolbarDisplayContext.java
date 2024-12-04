package management.web.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.*;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import com.management.app.model.Employee;
import com.management.app.service.EmployeeLocalService;

import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import management.web.internal.dao.search.EmployeeSearch;

/**
 * @author Albert Cabral
 */
public class ViewEmployeesManagementToolbarDisplayContext
        extends SearchContainerManagementToolbarDisplayContext {

    public ViewEmployeesManagementToolbarDisplayContext(
        HttpServletRequest httpServletRequest,
        LiferayPortletRequest liferayPortletRequest,
        LiferayPortletResponse liferayPortletResponse,
        SearchContainer<Employee> searchContainer) {

        super(
            httpServletRequest, liferayPortletRequest, liferayPortletResponse,
            searchContainer);

        this._httpServletRequest = PortalUtil.getHttpServletRequest(
                liferayPortletRequest);
    }

    @Override
    public String getClearResultsURL() {
        return PortletURLBuilder.create(
                getPortletURL()
        ).setKeywords(
                StringPool.BLANK
        ).setParameter(
                "type", (String)null
        ).buildString();
    }

    @Override
    public String getComponentId() {
        return "employeesManagementToolbar";
    }

    @Override
    public CreationMenu getCreationMenu() {
        return CreationMenuBuilder.addPrimaryDropdownItem(
            dropdownItem -> {
                dropdownItem.setHref(
                    liferayPortletResponse.createRenderURL(),
                    "mvcPath",
                    "/management/edit_employee_entry.jsp", "backURL",
                    currentURLObj.toString(), "employeeId",
                    ParamUtil.getLong(liferayPortletRequest, "employeeId"));
                dropdownItem.setLabel("Add a new employee entry");
                }
        ).build();
    }

    @Override
    public List<LabelItem> getFilterLabelItems() {
        return LabelItemListBuilder.add(
            () -> !Objects.equals(getNavigation(), "all"),
                labelItem -> {
                    labelItem.putData(
                        "removeLabelURL",
                        PortletURLBuilder.create(
                                getPortletURL()
                        ).setParameter(
                            "type", (String)null
                        ).buildString());
                    labelItem.setDismissible(true);
                    labelItem.setLabel(
                        String.format(
                            "%s: %s", LanguageUtil.get(httpServletRequest, "type"),
                            LanguageUtil.get(httpServletRequest, getNavigation())));
                }
        ).build();
    }

    public String getKeywords() {
        return ParamUtil.getString(_httpServletRequest, "keywords");
    }

    @Override
    public PortletURL getPortletURL() {
        try {
            return PortletURLUtil.clone(currentURLObj, liferayPortletResponse);
        }
        catch (Exception exception) {
            if (_log.isWarnEnabled()) {
                _log.warn(exception);
            }

            return liferayPortletResponse.createRenderURL();
        }
    }

    @Override
    protected String getFilterNavigationDropdownItemsLabel() {
        return LanguageUtil.get(httpServletRequest, "filter-by-type");
    }

    @Override
    protected String getNavigation() {
        return ParamUtil.getString(
            liferayPortletRequest, getNavigationParam(), "all");
    }

    @Override
    protected String getNavigationParam() {
        return "type";
    }

    @Override
    protected String getOrderByCol() {
        return ParamUtil.getString(
            liferayPortletRequest, getOrderByColParam(), "name");
    }

    @Override
    protected String[] getOrderByKeys() {
        return new String[] {"name"};
    }

    @Override
    public String getSearchContainerId() {
        return "employee";
    }

    public SearchContainer<Employee> getSearchContainer() throws PortalException {
        PortletURL portletURL = PortletURLBuilder.create(
                getPortletURL()
        ).setParameter(
                "displayStyle", getDisplayStyle()
        ).buildPortletURL();

        EmployeeSearch employeeSearch = new EmployeeSearch(
                liferayPortletRequest, portletURL, new ArrayList<>());

        employeeSearch.setEmptyResultsMessage(
                "no-entries-were-found");

        employeeSearch.setOrderByCol(getOrderByCol());
        employeeSearch.setOrderByComparator(
                EmployeeSearch.getEmployeeOrderByComparator(
                        getOrderByCol(), getOrderByType()));
        employeeSearch.setOrderByType(getOrderByType());

        if (Validator.isNull(getKeywords())) {
            employeeSearch.setResultsAndTotal(
                    () -> _getEmployeeLocalService().getEmployees(
                            employeeSearch.getStart(), employeeSearch.getEnd()),
                    _getEmployeeLocalService().getEmployeesCount());
        }
        else {
            employeeSearch.setResultsAndTotal(
                    _getEmployeeLocalService().searchEmployees(
                            _getSearchContext()));
        }

        new ViewEmployeesManagementToolbarDisplayContext(
                _httpServletRequest, liferayPortletRequest,
                liferayPortletResponse, employeeSearch);

        return employeeSearch;
    }

    public int getTotalItems() throws PortalException {
        SearchContainer<Employee> searchContainer = getSearchContainer();

        return searchContainer.getTotal();
    }

    private EmployeeLocalService _getEmployeeLocalService() {
        return _snapshot.get();
    }

    private SearchContext _getSearchContext() {
        SearchContext searchContext = SearchContextFactory.getInstance(
                _httpServletRequest);

        searchContext.setEnd(searchContext.getEnd());
        searchContext.setKeywords(getKeywords());
        searchContext.setStart(searchContext.getStart());

        return searchContext;
    }

    private static final Log _log = LogFactoryUtil.getLog(
            ViewEmployeesManagementToolbarDisplayContext.class);

    private final HttpServletRequest _httpServletRequest;

    private final Snapshot<EmployeeLocalService> _snapshot = new Snapshot<>(
            ViewEmployeesManagementToolbarDisplayContext.class, EmployeeLocalService.class);

}
