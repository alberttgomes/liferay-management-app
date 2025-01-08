package management.web.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItemListBuilder;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

import management.web.display.EmployeeDisplay;

/**
 * @author Albert Cabral
 */
public class ViewEmployeesManagementToolbarDisplayContext
    extends SearchContainerManagementToolbarDisplayContext {

    public ViewEmployeesManagementToolbarDisplayContext(
        HttpServletRequest httpServletRequest,
        LiferayPortletRequest liferayPortletRequest,
        LiferayPortletResponse liferayPortletResponse,
        SearchContainer<EmployeeDisplay> searchContainer) {

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
        return "employeeSearchContainerId";
    }

    private final HttpServletRequest _httpServletRequest;

    private static final Log _log = LogFactoryUtil.getLog(
            ViewEmployeesManagementToolbarDisplayContext.class);

}
