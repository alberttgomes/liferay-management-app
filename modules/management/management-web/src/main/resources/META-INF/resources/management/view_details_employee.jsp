<%@ page import="management.web.display.context.ViewEmployeeStaffMembersDisplayContext" %>
<%@ include file="../init.jsp" %>

<%
    ViewEmployeeStaffMembersDisplayContext viewEmployeeStaffMembersDisplayContext = new ViewEmployeeStaffMembersDisplayContext(request);
%>

<clay:container-fluid>
    <frontend-data-set:headless-display
            apiURL="<%= viewEmployeeStaffMembersDisplayContext.getAPIURL() %>"
            fdsActionDropdownItems="<%= viewEmployeeStaffMembersDisplayContext.getFDSActionDropdownItems() %>"
            fdsSortItemList="<%= viewEmployeeStaffMembersDisplayContext.getFDSSortItemList() %>"
            id="<%= EmployeeDetailsFDSNames.EMPLOYEE_PROMOTION %>"
            style="fluid"
    />
</clay:container-fluid>