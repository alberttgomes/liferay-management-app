<%@ page import="management.web.display.EmployeeDisplay" %>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchContainer" %>
<%@ page import="management.web.internal.dao.search.ManagementDisplaySearchContainerFactory" %>
<%@ page import="management.web.display.context.ViewEmployeesManagementToolbarDisplayContext" %>
<%@ include file="../init.jsp" %>

<%
    SearchContainer<EmployeeDisplay> employeeEntryDisplaySearchContainer = ManagementDisplaySearchContainerFactory.create(liferayPortletRequest, liferayPortletResponse);
    ViewEmployeesManagementToolbarDisplayContext viewEmployeesManagementToolbarDisplayContext = new ViewEmployeesManagementToolbarDisplayContext(request, liferayPortletRequest, liferayPortletResponse, employeeEntryDisplaySearchContainer);
%>

<clay:management-toolbar
        managementToolbarDisplayContext="<%= viewEmployeesManagementToolbarDisplayContext %>"
/>

<clay:container-fluid>
    <liferay-ui:search-container
            searchContainer="<%= employeeEntryDisplaySearchContainer %>"
    >
        <liferay-ui:search-container-row
                className="management.web.display.EmployeeDisplay"
                keyProperty="employeeId"
                modelVar="employeeDisplay"
        >
            <liferay-ui:search-container-column-text
                    cssClass="table-cell-expand"
                    name="fullName"
            >
                <clay:label
                        label="<%= employeeDisplay.getFullName() %>"
                />
            </liferay-ui:search-container-column-text>

            <liferay-ui:search-container-column-text
                    cssClass="table-cell-expand"
                    name="position"
            >
                <clay:label
                        label="<%= employeeDisplay.getPosition() %>"
                />
            </liferay-ui:search-container-column-text>

            <liferay-ui:search-container-column-text
                    cssClass="table-cell-expand"
                    name="position"
            >
                <clay:label
                        label="<%= employeeDisplay.getDepartment() %>"
                />
            </liferay-ui:search-container-column-text>

        </liferay-ui:search-container-row>

        <liferay-ui:search-iterator
                markupView="lexicon"
        />

    </liferay-ui:search-container>
</clay:container-fluid>