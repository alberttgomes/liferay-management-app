<%@ include file="../init.jsp" %>

<%
    ViewEmployeeStaffMembersDisplayContext viewEmployeeStaffMembersDisplayContext = new ViewEmployeeStaffMembersDisplayContext(request);
%>

<clay:container-fluid>
    <frontend-data-set:headless-display
        apiURL="<%= viewEmployeeStaffMembersDisplayContext.getAPIURL() %>"
        fdsSortItemList="<%= viewEmployeeStaffMembersDisplayContext.getFDSSortItemList() %>"
        showSearch="<%= false %>"
        id="<%= EmployeeDetailsFDSNames.EMPLOYEE_PROMOTION %>"
        style="fluid"
    />
</clay:container-fluid>