<%@ include file="../init.jsp" %>

<%
    ManagementDisplayContext managementDisplayContext = (ManagementDisplayContext) request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
    EmployeeDisplay employeeDisplay = managementDisplayContext.getEmployeeDisplay();

    if (employeeDisplay == null) {
        %>
        <liferay-ui:message key="employee-not-was-found" />
        <%
    }
%>

<liferay-ui:error embed="<%= false %>" exception="<%= NoSuchEmployeeException.class %>" message="employee-not-was-found" />

<clay:sheet-section
        id="employeeDetails"
>
    <frontend-data-set:classic-display
            creationMenu="<%= managementDisplayContext.getCreationMenu() %>"
            dataProviderKey="<%= EmployeeDetailsFDSNames.EMPLOYEE_DETAILS %>"
            id="<%= EmployeeDetailsFDSNames.EMPLOYEE_DETAILS %>"
            itemsPerPage="<%= 10 %>"
            showSearch="<%= false %>"
            style="fluid"
    />
</clay:sheet-section>