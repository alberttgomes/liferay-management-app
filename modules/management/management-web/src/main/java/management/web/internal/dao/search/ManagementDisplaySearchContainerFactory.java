package management.web.internal.dao.search;

import com.liferay.account.constants.AccountListTypeConstants;
import com.liferay.account.model.AccountEntry;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.SearchOrderByUtil;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.management.app.constants.EmployeeListTypeConstants;
import com.management.app.model.Employee;
import com.management.app.service.EmployeeLocalServiceUtil;
import management.web.constants.ManagementPortletKeys;
import management.web.display.EmployeeDisplay;

import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * @author Albert Cabral
 */
public class ManagementDisplaySearchContainerFactory {

    public static SearchContainer<EmployeeDisplay> create(
            LiferayPortletRequest liferayPortletRequest,
            LiferayPortletResponse liferayPortletResponse)
        throws PortalException {

        SearchContainer<EmployeeDisplay> searchContainer = new SearchContainer<>(
                liferayPortletRequest,
                PortletURLUtil.getCurrent(
                        liferayPortletRequest, liferayPortletResponse),
                null, "no-employee-were-found");

        searchContainer.setId("employeeId");
        searchContainer.setOrderByCol(
                SearchOrderByUtil.getOrderByCol(
                        liferayPortletRequest, ManagementPortletKeys.MANAGEMENT_WEB,
                        "employee-order-by-col", "name"));
        searchContainer.setOrderByType(
                SearchOrderByUtil.getOrderByType(
                        liferayPortletRequest, ManagementPortletKeys.MANAGEMENT_WEB,
                        "employee-order-by-type", "asc"));

        String keywords = ParamUtil.getString(
                liferayPortletRequest, "keywords");

        String type = ParamUtil.getString(liferayPortletRequest, "type");

        LinkedHashMap<String, Object> params = new LinkedHashMap<>();

        if (Validator.isNotNull(type) && !type.equals("all")) {
            params.put(
                    "typeNames",
                    new String[] {
                            type,
                            EmployeeListTypeConstants.EMPLOYEE_ENTRY_DEPARTMENT
                    });
        }
        else {
            params.put(
                    "typeNames",
                    new String[] {
                            EmployeeListTypeConstants.EMPLOYEE_ENTRY_DEPARTMENT,
                            EmployeeListTypeConstants.EMPLOYEE_ENTRY_MANAGERS,
                            EmployeeListTypeConstants.EMPLOYEE_ENTRY_POSITION
                    });
        }

        ThemeDisplay themeDisplay =
                (ThemeDisplay)liferayPortletRequest.getAttribute(
                        WebKeys.THEME_DISPLAY);

        BaseModelSearchResult<Employee> baseModelSearchResult =
                EmployeeLocalServiceUtil.searchAddresses(
                        themeDisplay.getCompanyId(), AccountEntry.class.getName(),
                        ParamUtil.getLong(liferayPortletRequest, "employeeId"),
                        keywords, params, searchContainer.getStart(),
                        searchContainer.getEnd(),
                        _getSort(
                                searchContainer.getOrderByCol(),
                                searchContainer.getOrderByType()));

        searchContainer.setResultsAndTotal(
                () -> TransformUtil.transform(
                        baseModelSearchResult.getBaseModels(), EmployeeDisplay::of),
                baseModelSearchResult.getLength());

        searchContainer.setRowChecker(
                new EmptyOnClickRowChecker(liferayPortletResponse));

        return searchContainer;
    }

    private static Sort _getSort(String orderByCol, String orderByType) {
        return SortFactoryUtil.create(
                orderByCol, Objects.equals(orderByType, "desc"));
    }

}
