<%@ include file="../init.jsp" %>

<%
    ManagementDisplayContext managementDisplayContext = (ManagementDisplayContext)request.getAttribute(ManagementPortletKeys.EMPLOYEE_DISPLAY);
    long employeeId = ParamUtil.getLong(request, "employeeId");
%>

<clay:container-fluid>
    <frontend-data-set:headless-display
            apiURL="<%= managementDisplayContext.getAPIURL() %>"
            fdsActionDropdownItems="<%= managementDisplayContext.getFDSActionDropdownItems() %>"
            fdsSortItemList="<%= managementDisplayContext.getFDSSortItemList() %>"
            id="<%= EmployeeDetailsFDSNames.EMPLOYEE_PROMOTION %>"
            style="fluid"
    />
</clay:container-fluid>