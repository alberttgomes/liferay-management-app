package management.web.display.context;

import com.liferay.frontend.data.set.model.FDSActionDropdownItem;
import com.liferay.frontend.data.set.model.FDSSortItemBuilder;
import com.liferay.frontend.data.set.model.FDSSortItemList;
import com.liferay.frontend.data.set.model.FDSSortItemListBuilder;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.util.ListUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import management.web.display.helper.ManagementRequestHelper;

/**
 * @author Albert Cabral
 */
public class ViewEmployeeStaffMembersDisplayContext {

    public ViewEmployeeStaffMembersDisplayContext(
            HttpServletRequest httpServletRequest) {

        this._managementRequestHelper = new ManagementRequestHelper(
                httpServletRequest);
    }

    public String getAPIURL() {
        return "/o/management/v1.0/employee";
    }

    public List<FDSActionDropdownItem> getFDSActionDropdownItems() {
        return ListUtil.fromArray(
            new FDSActionDropdownItem(
                PortletURLBuilder.createRenderURL(
                    _managementRequestHelper.getLiferayPortletResponse()
                ).setMVCRenderCommandName(
                    "/management/employee_promotion"
                ).setParameter(
                    "employeeId", "{employeeId}"
                ).buildString(),
                "list-ul", "employee-promotion",
                "Request a promotion to Employee", "post",
                "get", null));
    }

    public FDSSortItemList getFDSSortItemList() {
        return FDSSortItemListBuilder.add(
            FDSSortItemBuilder.setDirection(
                "asc"
            ).setKey(
                "typeName"
            ).build()
        ).build();
    }

    private final ManagementRequestHelper _managementRequestHelper;

}
