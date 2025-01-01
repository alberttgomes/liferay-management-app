<%@ page import="management.web.model.EmployeeDetails" %>
<%@ page import="java.util.Date" %>

<%
    EmployeeDetails details = (EmployeeDetails)request.getAttribute("EmployeeDetails");
%>

<div class="employee-details">
    <div class="row">
        <div class="col-6">
            <div class="items">
                <h2 class="details-title">Employee Details</h2>
                <ul>
                    <li class="item">
                        <p>Name:</p> <%= details.getFullName() %>
                    </li>
                    <li class="item">
                        <p>Email:</p> <%= details.getEmail() %>
                    </li>
                    <li class="item">
                        <p>Position:</p> <%= details.getPosition() %>
                    </li>
                    <li class="item">
                        <p>Department:</p> <%= details.getDepartment() %>
                    </li>
                    <li class="item">
                        <p>Employee Identifier:</p> <%= details.getEmployeeId() %>
                    </li>
                    <li class="item">
                        <p>Start Date:</p> <%= details.getStartDate() %>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-6">
            <h2>Employee Address</h2>
        </div>
    </div>
</div>

<style>
    .employee-details {
        padding: 20px;
        background-color: #f4f4f4;
    }
    .row {
        display: flex;
        flex-wrap: wrap;
        margin: 0 -10px;
    }
    .col-6 {
        padding: 10px;
    }
    .col-6 {
        flex: 0 0 25%;
        max-width: 25%;
    }
    .col-6 {
        flex: 0 0 50%;
        max-width: 50%;
    }
    .items {
        background-color: #fff;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }
    .details-title {
        margin-bottom: 15px;
        font-size: 20px;
        color: #333;
    }
    ul {
        list-style: none;
        padding: 0;
        margin: 0;
    }
    .item {
        margin-bottom: 10px;
        font-size: 16px;
    }
    .item p {
        display: inline;
        font-weight: bold;
        color: #555;
    }
</style>