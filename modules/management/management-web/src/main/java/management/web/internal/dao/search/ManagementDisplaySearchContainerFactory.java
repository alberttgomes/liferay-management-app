package management.web.internal.dao.search;

import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.SearchOrderByUtil;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import com.management.app.model.Employee;
import com.management.app.service.EmployeeLocalServiceUtil;

import java.util.LinkedHashMap;
import java.util.Objects;

import management.web.constants.ManagementPortletKeys;

/**
 * @author Albert Cabral
 */
public class ManagementDisplaySearchContainerFactory {

    public static SearchContainer<Employee> create(
            LiferayPortletRequest liferayPortletRequest,
            LiferayPortletResponse liferayPortletResponse)
        throws PortalException {

        SearchContainer<Employee> searchContainer = new SearchContainer<>(
                liferayPortletRequest,
                PortletURLUtil.getCurrent(
                        liferayPortletRequest, liferayPortletResponse),
                null, "no-employee-were-found");

        searchContainer.setId("employeeSearchContainerId");
        searchContainer.setOrderByCol(
                SearchOrderByUtil.getOrderByCol(
                        liferayPortletRequest,
                        ManagementPortletKeys.MANAGEMENT_WEB,
                        "order-by-col", "name"));
        searchContainer.setOrderByType(
                SearchOrderByUtil.getOrderByType(
                        liferayPortletRequest,
                        ManagementPortletKeys.MANAGEMENT_WEB,
                        "order-by-type", "asc"));

        String keywords = ParamUtil.getString(
                liferayPortletRequest, "keywords");

        LinkedHashMap<String, Object> params = new LinkedHashMap<>();

        ThemeDisplay themeDisplay =
                (ThemeDisplay)liferayPortletRequest.getAttribute(
                        WebKeys.THEME_DISPLAY);

        BaseModelSearchResult<Employee> baseModelSearchResult =
                EmployeeLocalServiceUtil.searchEmployees(
                        themeDisplay.getCompanyId(), Employee.class.getName(),
                        keywords, params, searchContainer.getStart(),
                        searchContainer.getEnd(),
                        _getSort(
                                searchContainer.getOrderByCol(),
                                searchContainer.getOrderByType()));

        if (baseModelSearchResult.getBaseModels().isEmpty()) {
            baseModelSearchResult =
                    EmployeeLocalServiceUtil.searchEmployees(
                            Long.parseLong(keywords), keywords, keywords,
                            searchContainer.getStart(), searchContainer.getEnd(),
                            _getSort(
                                    searchContainer.getOrderByCol(),
                                    searchContainer.getOrderByType()));
        }

        BaseModelSearchResult<Employee> finalBaseModelSearchResult =
                baseModelSearchResult;

//        searchContainer.setResultsAndTotal(
//                () -> TransformUtil.transfor
//                        finalBaseModelSearchResult.getBaseModels(), Employee::getFirstName),
//                baseModelSearchResult.getLength());

        searchContainer.setRowChecker(
                new EmptyOnClickRowChecker(liferayPortletResponse));

        return searchContainer;
    }

    private static Sort _getSort(String orderByCol, String orderByType) {
        return SortFactoryUtil.create(
                orderByCol, Objects.equals(orderByType, "desc"));
    }

}
