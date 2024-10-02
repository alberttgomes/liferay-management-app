<%@ include file="../init.jsp" %>

<%
    liferayPortletRequest.setAttribute("employeeId", user.getUserId());
    SearchContainer<EmployeeDisplay> employeeEntryDisplaySearchContainer = ManagementDisplaySearchContainerFactory.create(liferayPortletRequest, liferayPortletResponse);
    ViewEmployeesManagementToolbarDisplayContext viewEmployeesManagementToolbarDisplayContext = new ViewEmployeesManagementToolbarDisplayContext(request, liferayPortletRequest, liferayPortletResponse, employeeEntryDisplaySearchContainer);
%>

<clay:management-toolbar
        managementToolbarDisplayContext="<%= viewEmployeesManagementToolbarDisplayContext %>"
/>

<clay:container-fluid>
    <liferay-ui:search-container
            id="<%= viewEmployeesManagementToolbarDisplayContext.getSearchContainerId() %>"
            searchContainer="<%= employeeEntryDisplaySearchContainer %>"
            total="<%= employeeEntryDisplaySearchContainer.getTotal() %>"
    >
        <liferay-ui:search-container-row
                className="management.web.display.EmployeeDisplay"
                keyProperty="employeeId"
                modelVar="employeeDisplay"
        >
            <liferay-ui:search-container-column-text
                    cssClass="table-cell-expand"
                    name="Id"
            >
                <clay:label
                        label="<%= String.valueOf(employeeDisplay.getEmployeeId()) %>"
                />
            </liferay-ui:search-container-column-text>

            <liferay-ui:search-container-column-text
                    cssClass="table-cell-expand"
                    name="Name"
            >
                <clay:label
                        label="<%= employeeDisplay.getFullName() %>"
                />
            </liferay-ui:search-container-column-text>

            <liferay-ui:search-container-column-text
                    cssClass="table-cell-expand"
                    name="Position"
            >
                <clay:label
                        label="<%= employeeDisplay.getPosition() %>"
                />
            </liferay-ui:search-container-column-text>

            <liferay-ui:search-container-column-text
                    cssClass="table-cell-expand"
                    name="Department"
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