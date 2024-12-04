package management.web.internal.dao.search;

import com.liferay.dynamic.data.mapping.constants.DDMPortletKeys;
import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.SearchOrderByUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import com.management.app.model.Employee;
import com.management.app.util.comparator.EmployeeModifiedDateComparator;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import java.util.List;
import java.util.Map;

/**
 * @author Albert Cabral
 */
public class EmployeeSearch extends SearchContainer<Employee> {

    public static final String EMPTY_RESULTS_MESSAGE = "no-entries-were-found";

    public static Map<String, String> orderableHeaders = HashMapBuilder.put(
            "modified-date", "modified-date"
    ).build();

    public static OrderByComparator<Employee> getEmployeeOrderByComparator(
            String orderByCol, String orderByType) {

        boolean orderByAsc = orderByType.equals("asc");

        OrderByComparator<Employee> orderByComparator = null;

        if (orderByCol.equals("modified-date")) {
            orderByComparator = new EmployeeModifiedDateComparator(
                    orderByAsc);
        }
        else {
            orderByComparator = new EmployeeModifiedDateComparator();
        }
        
        return orderByComparator;
    }

    public EmployeeSearch(
            PortletRequest portletRequest, PortletURL portletURL,
            List<String> headerNames) {

        super(
            portletRequest, new DisplayTerms(portletRequest), null,
            DEFAULT_CUR_PARAM, DEFAULT_DELTA, portletURL, headerNames,
            EMPTY_RESULTS_MESSAGE);

        try {
            String keywords = ParamUtil.getString(portletRequest, "keywords");

            if (Validator.isNotNull(keywords)) {
                setSearch(true);
            }

            setOrderableHeaders(orderableHeaders);

            String orderByCol = SearchOrderByUtil.getOrderByCol(
                    portletRequest, DDMPortletKeys.DYNAMIC_DATA_MAPPING_FORM_ADMIN,
                    "view-entries-order-by-col", "id");

            setOrderByCol(orderByCol);

            String orderByType = SearchOrderByUtil.getOrderByType(
                    portletRequest, DDMPortletKeys.DYNAMIC_DATA_MAPPING_FORM_ADMIN,
                    "view-entries-order-by-type", "asc");

            setOrderByComparator(
                    getEmployeeOrderByComparator(
                            orderByCol, orderByType));
            setOrderByType(orderByType);
        }
        catch (Exception exception) {
            _log.error(exception);
        }
    }

    private static final Log _log = LogFactoryUtil.getLog(
            EmployeeSearch.class);

}
