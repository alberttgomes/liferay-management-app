<%@ page import="management.web.display.EmployeeDisplay" %>
<%@ page import="management.web.constants.ManagementPortletKeys" %>
<%@ include file="../init.jsp" %>

<%
    EmployeeDisplay employeeDisplay = (EmployeeDisplay) request.getAttribute(ManagementPortletKeys.EMPLOYEE_DISPLAY);
    String description = (String) request.getAttribute("description");
    long employeeId = employeeDisplay == null ? 0 : employeeDisplay.getEmployeeId();
%>

<div class="management-main">
    <div class="management-header">
        <h1 class="management-title">Hello Dear, <%= employeeDisplay.getFullName() %>!</h1>

        <div class="row">
            <div class="col-3">
                <h5 class="management-employee-identifier">
                    Identifier: <%= employeeId %>
                </h5>
            </div>
            <div class="col-3">
                <h6 class="management-employee-screen-name">
                    Nickname: <%= employeeDisplay.getScreenName() %>
                </h6>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-6">
            <p class="management-paragraph"><%= description %></p>
        </div>
    </div>

    <img src="" class="management-banner"  alt="image-banner" />
</div>