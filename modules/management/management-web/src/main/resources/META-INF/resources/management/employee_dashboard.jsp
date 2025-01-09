<%@ include file="../init.jsp" %>

<%
    ManagementDisplayContext managementDisplayContext = (ManagementDisplayContext) request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
    EmployeeDisplay employeeDisplay = managementDisplayContext.getEmployeeDisplay();
%>

<portlet:renderURL var="addNewEmployee">
    <portlet:param name="mvcPath" value="/management/edit_employee_entry.jsp" />
</portlet:renderURL>
<portlet:renderURL var="managersArea">
    <portlet:param name="mvcPath" value="/management/pages/managers_area.jsp" />
</portlet:renderURL>

<body class="dashboard-body">
    <div class="dashboard-header">
        Employees Area, Welcome <%= employeeDisplay.getFullName() %>.
    </div>
    <div class="dashboard-buttons">
        <button onclick="location.href='/web/portal/details'">Details</button>
        <button onclick="location.href='/web/portal/frequency'">Frequency</button>
        <button onclick="location.href='/web/portal/salary'">Salary</button>
        <button onclick="location.href='/web/portal/requests'">Requests</button>

        <%
            String position = employeeDisplay.getPosition();
        %>

        <c:choose>
            <c:when test='<%= position.equals(EmployeeStructureConstants.TITLE_HUMAN_RESOURCES) || position.equals("Administrator") %>'>
                <button onclick="location.href='<%= addNewEmployee %>'">Add New Employee</button>
            </c:when>

            <c:when test='<%= managementDisplayContext.isManager() %>'>
                <button onclick="location.href='<%= managersArea %>'">Managers Area</button>
            </c:when>
        </c:choose>
    </div>

    <c:choose>
        <c:when test='<%= employeeDisplay.getDepartment().contains(EmployeeStructureConstants.DEPARTMENT_HUMAN_RESOURCES) %>'>
            <div class="dashboard-content">
                <h2 class="title-dashboard">Search by an employee</h2>

                <clay:sheet-section
                        id="employeeSearch"
                >
                    <%@ include file="view_all_employee_entry.jsp" %>
                </clay:sheet-section>
            </div>
        </c:when>
        <c:otherwise>
            <div class="dashboard-content">
                <h2 class="title-dashboard">Active Staff Members</h2>

                <clay:sheet-section
                        id="employeeDetails"
                >
                    <%@ include file="view_details_employee.jsp" %>
                </clay:sheet-section>
            </div>
        </c:otherwise>
    </c:choose>

    <div class="employee-articles">
        <%
            try {
                for (ArticlesDisplay articlesDisplay : ArticlesContentHelper.getArticlesDisplay(
                        "ArtEmployee", themeDisplay.getScopeGroupId(), "ArtEmployee")) {
        %>
                    <div class="article-background" style="background-image: '<%= articlesDisplay.getImagesURL().get(0) %>'">
                        <h3 class="article-title"><%= articlesDisplay.getTitle() %></h3>

                        <div class="article-description"><%= articlesDisplay.getDescription() %></div>
                    </div>
        <%
                }
            }
            catch (Exception exception) {
                System.out.println("ERROR: " + exception.getMessage());
        %>
                <div class="dashboard-error">
                    <h1>Fail to load your request. Try again! ;(</h1>
                </div>
        <%
            }
        %>
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