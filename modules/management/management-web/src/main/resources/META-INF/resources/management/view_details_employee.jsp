<%@ include file="../init.jsp" %>

<%
    EmployeeDisplay employeeDisplay = (EmployeeDisplay) request.getAttribute(ManagementPortletKeys.EMPLOYEE_DISPLAY);
%>

<clay:container-fluid>
    <clay:row>
        <clay:col
            lg="6"
        >
            <h2 class="employee-full-name">Test</h2>
        </clay:col>
        <clay:col
            lg="6"
        >
            <h2 class="employee-id"><%= employeeDisplay.getEmployeeId() %></h2>
        </clay:col>
    </clay:row>

    <clay:row>
        <clay:col
            lg="9"
        >
            <clay:container-fluid>
                <div class="management-web-employee-details">
                    <div class="sheet-text">
                        <liferay-ui:message key="look-to-the-details-at-dashboard-employee-view" />
                    </div>

                    <aui:fieldset>
                        <h2 class="department-title"><%= employeeDisplay.getDepartment() %></h2>
                    </aui:fieldset>
                </div>
            </clay:container-fluid>
        </clay:col>
    </clay:row>
</clay:container-fluid>