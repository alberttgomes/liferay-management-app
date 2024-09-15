<%@ include file="../init.jsp" %>

<%
    String screenName = request.getParameter("screenName");
    long employeeId = Long.parseLong(request.getParameter("employeeId"));
    String description = request.getParameter("description");
%>

<div class="management-main">
    <div class="management-header">
        <h1 class="management-title">Hello Dear, <%= screenName %>!</h1>

        <h5 class="management-employee-identifier"><%= employeeId %></h5>
    </div>

    <p class="management-paragraph"><%= description %></p>

    <img src="" class="management-banner"  alt="image-banner" />
</div>