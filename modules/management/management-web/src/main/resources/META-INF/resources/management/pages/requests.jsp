<%@include file="../../init.jsp"%>

<portlet:renderURL var="newRequest">
    <portlet:param name="mvcPath" value="/management/view_request_employee.jsp" />
</portlet:renderURL>

<portlet:renderURL var="myRequests">
    <portlet:param name="mvcPath" value="/management/view_all_employee_entry.jsp" />
</portlet:renderURL>

<portlet:renderURL var="backToEmployeeAreaURL">
    <portlet:param name="mvcPath" value="employee_dashboard.jsp"/>
</portlet:renderURL>

<div class="dashboard-main">
    <div class="dashboard-header">
        Requests Area
    </div>
    <div class="dashboard-buttons">
        <button onclick="location.href='/web/portal/'">Employee Area</button>
        <button onclick="location.href='<%= newRequest %>'">New Request</button>
        <button onclick="location.href='<%= myRequests %>'">My Requests</button>
    </div>
</div>

<style>
    .back-to-employee-dashboard {
        text-align: left;
        padding: 3px;
    }
    .dashboard-main {
        margin: 0;
        font-family: Arial, sans-serif;
        background-image: url('/META-INF/resources/data/banners-managers-1.jpeg');
        background-size: cover;
        background-repeat: no-repeat;
        background-attachment: fixed;
    }
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
</style>