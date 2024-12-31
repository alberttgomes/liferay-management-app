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

<portlet:renderURL var="addNewEmployee">
    <portlet:param name="mvcPath" value="/management/edit_employee_entry.jsp" />
</portlet:renderURL>
<portlet:renderURL var="managersArea">
    <portlet:param name="mvcPath" value="/management/pages/managers_area.jsp" />
</portlet:renderURL>

<body class="dashboard-body">
    <div class="dashboard-header">
        Employees Area, Welcome <%= employeeDisplay.getFullName() %>
    </div>
    <div class="dashboard-buttons">
        <button onclick="location.href='/web/portal/details'">Details</button>
        <button onclick="location.href='about.jsp'">About</button>
        <button onclick="location.href='/web/portal/requests'">Requests</button>

        <%
            assert employeeDisplay != null;

            String position = employeeDisplay.getPosition();

            if (position.equals("Human Resources") || position.equals("Administrator")) {
        %>
                <button onclick="location.href='<%= addNewEmployee %>'">Add New Employee</button>
        <%
            }
        %>

        <%
            if (managementDisplayContext.isManager()) {
        %>
                <button onclick="location.href='<%= managersArea %>'">Managers Area</button>
        <%
            }
        %>
    </div>

    <div class="dashboard-content">
        <h2 class="title-dashboard">Active Staff Members</h2>

        <clay:sheet-section
                id="employeeDetails"
        >
            <%@ include file="view_details_employee.jsp" %>
        </clay:sheet-section>
    </div>

    <div class="dashboard-content">
        <h2 class="title-dashboard">Search by an employee</h2>

        <clay:sheet-section
                id="employeeSearch"
        >
            <%@ include file="view_all_employee_entry.jsp" %>
        </clay:sheet-section>
    </div>
</body>

<style>
    .dashboard-header {
        background-color: #333;
        color: #fff;
        padding: 10px 20px;
        text-align: left;
        font-size: 18px;
    }
    .dashboard-buttons {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 300px;
    }
    .dashboard-buttons button {
        margin: 0 15px;
        padding: 15px 30px;
        font-size: 18px;
        border: none;
        background-color: #333;
        color: #fff;
        cursor: pointer;
        border-radius: 5px;
        transition: background-color 0.3s;
    }
    .dashboard-buttons button:hover {
        background-color: #555;
    }
    .dashboard-content {
        padding: 20px;
        background-color: #f4f4f4;
    }
    .title-dashboard {
        text-align: center;
        padding-bottom: 10px;
    }
</style>