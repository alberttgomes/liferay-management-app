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

<style>
    .management-main {
        font-family: Arial, sans-serif;
        background-image: url('http://localhost:8080/documents/d/portal/587-agenda-analysis-business-plan-990818');
        background-size: cover;
        background-repeat: no-repeat;
        background-attachment: fixed;
        padding: 20px;
        background-color: rgba(255, 255, 255, 0.9);
        border-radius: 10px;
        margin: 20px auto;
        max-width: 1200px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    .management-header {
        margin-bottom: 20px;
        padding: 15px;
        background-color: #007BFF;
        color: white;
        border-radius: 5px;
    }

    .management-title {
        font-size: 24px;
        margin: 0;
    }

    .row {
        display: flex;
        gap: 20px;
        flex-wrap: wrap;
    }

    .col-3, .col-6 {
        padding: 15px 20px;
        flex-basis: calc(33.33% - 20px); /* For col-3 */
        max-width: calc(33.33% - 20px);
    }

    .col-6 {
        flex-basis: calc(50% - 20px);
        max-width: calc(50% - 20px);
    }

    .management-employee-identifier,
    .management-employee-screen-name {
        font-size: 16px;
        font-weight: 500;
    }

    .management-paragraph {
        margin-top: 0;
        font-size: 16px;
        line-height: 1.5;
    }

    .management-banner {
        margin-top: 20px;
        width: 100%;
        border-radius: 5px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .management-banner[alt="image-banner"] {
        display: block;
    }
</style>