<%@ page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %>
<%@ include file="../init.jsp" %>

<%
    liferayPortletRequest.setAttribute("employeeId", user.getUserId());

    SearchContainer<Employee> employeeEntryDisplaySearchContainer = ManagementDisplaySearchContainerFactory.create(liferayPortletRequest, liferayPortletResponse);

    ViewEmployeesManagementToolbarDisplayContext viewEmployeesManagementToolbarDisplayContext = new ViewEmployeesManagementToolbarDisplayContext(request, liferayPortletRequest, liferayPortletResponse, employeeEntryDisplaySearchContainer);
%>

<clay:management-toolbar
        searchContainerId="<%= viewEmployeesManagementToolbarDisplayContext.getSearchContainerId() %>"
        searchFormName="fm"
        searchActionURL="<%= viewEmployeesManagementToolbarDisplayContext.getSearchActionURL() %>"
        selectable = "false"
        itemsTotal="<%= viewEmployeesManagementToolbarDisplayContext.getTotalItems() %>"
        filterDropdownItems="<%= viewEmployeesManagementToolbarDisplayContext.getFilterDropdownItems() %>"
/>

<clay:container-fluid>
    <liferay-ui:search-container
            id="<%= viewEmployeesManagementToolbarDisplayContext.getSearchContainerId() %>"
            searchContainer="<%= viewEmployeesManagementToolbarDisplayContext.getSearchContainer() %>"
            total="<%= viewEmployeesManagementToolbarDisplayContext.getTotalItems() %>"
    >
        <liferay-ui:search-container-row
                className="com.management.app.model.Employee"
                keyProperty="employeeId"
                modelVar="employee"
        >
            <liferay-ui:search-container-column-text
                    cssClass="table-cell-expand"
                    name="Id"
            >
                <clay:label
                        label="<%= String.valueOf(employee.getEmployeeId()) %>"
                />
            </liferay-ui:search-container-column-text>

            <liferay-ui:search-container-column-text
                    cssClass="table-cell-expand"
                    name="Name"
            >
                <clay:label
                        label="<%= employee.getFirstName() %>"
                />
            </liferay-ui:search-container-column-text>

            <liferay-ui:search-container-column-text
                    cssClass="table-cell-expand"
                    name="Position"
            >
                <clay:label
                        label="<%= employee.getPosition() %>"
                />
            </liferay-ui:search-container-column-text>

            <liferay-ui:search-container-column-text
                    cssClass="table-cell-expand"
                    name="Department"
            >
                <clay:label
                        label="<%= employee.getDepartment() %>"
                />
            </liferay-ui:search-container-column-text>

        </liferay-ui:search-container-row>

        <liferay-ui:search-iterator
                markupView="lexicon"
        />
    </liferay-ui:search-container>
</clay:container-fluid>