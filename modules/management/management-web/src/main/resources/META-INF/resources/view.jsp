<%@ page import="com.liferay.portal.kernel.model.UserConstants" %>
<%@ include file="/init.jsp" %>

<portlet:renderURL var="editEmployeeEntryRequestURL">
    <portlet:param name="mvcPath" value="/management/edit_employee_entry.jsp" />
</portlet:renderURL>

<portlet:renderURL var="viewEmployeeEntryRequestURL">
    <portlet:param name="mvcPath" value="/management/view_all_employee_entry.jsp" />
</portlet:renderURL>

<%
    Employee employee = null;
    EmployeeDisplay employeeDisplay;

    if (user != null && user.getType() != UserConstants.TYPE_GUEST) {
        employee = EmployeeLocalServiceUtil.fetchEmployeeByUuidAndGroupId(user.getUuid(), user.getGroupId());
        employeeDisplay = EmployeeDisplay.of(employee);
    }
    else {
        employeeDisplay = EmployeeDisplay.of(employee);
    }
%>

<c:choose>

    <c:when test="${themeDisplay.isSignedIn() || currentURL.contains(ManagementPortletKeys.MANAGER_PATH)}">
        <div class="management-main-view">
            <h5 class="management-main-view-title">Managers Area</h5>

            <br />

            <clay:container-fluid>
                <clay:row>
                    <clay:col md="3">
                        <img src="" alt="banner-manager-view" />
                    </clay:col>

                    <clay:col md="6">
                        <div class="button-group">
                            <aui:form action="<%= editEmployeeEntryRequestURL %>" method="post" name="fm">
                                <aui:input label="permission" name="permission" type="hidden" value="<%= true %>" />

                                <aui:button-row>
                                    <aui:button type="submit" value="Create Employee Here" />
                                </aui:button-row>
                            </aui:form>

                            <aui:form action="<%= viewEmployeeEntryRequestURL %>" method="post" name="fm">
                                <aui:input label="permission" name="permission" type="hidden" value="<%= true %>" />

                                <aui:button-row>
                                    <aui:button type="submit" value="Edit Employees Here" />
                                </aui:button-row>
                            </aui:form>
                        </div>
                    </clay:col>

                    <clay:col md="6">
                        <h1>Information</h1>

                        <aui:fieldset>
                            <c:if test="${employeeDisplay != null && employeeDisplay.getFullName() != null}">
                                <h3> '<%= employeeDisplay.getFullName() %>' </h3>
                            </c:if>
                            <c:if test="${employeeDisplay != null && employeeDisplay.getDepartment() != null}">
                                <br />
                                <h5>'<%= employeeDisplay.getDepartment() %>'</h5>
                            </c:if>
                            <c:if test="${employeeDisplay != null && employeeDisplay.getPosition() != null}">
                                <br />
                                <h5>'<%= employeeDisplay.getPosition() %>'</h5>
                            </c:if>
                        </aui:fieldset>
                    </clay:col>
                </clay:row>
            </clay:container-fluid>
        </div>

    </c:when>

    <c:otherwise>
        <c:redirect url="/management/view_guest_users.jsp" />
    </c:otherwise>

</c:choose>