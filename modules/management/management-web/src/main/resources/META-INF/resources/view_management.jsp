<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="management.web.display.context.ManagementDisplayContext" %>
<%@ page import="management.web.constants.ManagementPortletKeys" %>
<%@ page import="management.web.display.EmployeeDisplay" %>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchContainer" %>
<%@ page import="management.web.internal.dao.search.ManagementDisplaySearchContainerFactory" %>
<%@ page import="management.web.display.context.ViewEmployeesManagementToolbarDisplayContext" %>
<%@ include file="init.jsp" %>

<%
    ManagementDisplayContext managementDisplayContext = (ManagementDisplayContext)request.getAttribute(ManagementPortletKeys.MANAGEMENT_DISPLAY_CONTEXT);

    SearchContainer<EmployeeDisplay> employeeEntryDisplaySearchContainer = ManagementDisplaySearchContainerFactory.create(liferayPortletRequest, liferayPortletResponse);

    ViewEmployeesManagementToolbarDisplayContext viewEmployeesManagementToolbarDisplayContext = new ViewEmployeesManagementToolbarDisplayContext(request, liferayPortletRequest, liferayPortletResponse, employeeEntryDisplaySearchContainer);

    portletDisplay.setShowBackIcon(true);
    portletDisplay.setURLBack(ParamUtil.getString(request, "backURL", String.valueOf(renderResponse.createRenderURL())));

    renderResponse.setTitle(managementDisplayContext.getName());
%>

<clay:management-toolbar
        managementToolbarDisplayContext="<%= viewEmployeesManagementToolbarDisplayContext %>"
/>