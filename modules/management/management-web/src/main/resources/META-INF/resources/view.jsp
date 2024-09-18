<%@ include file="/init.jsp" %>

<portlet:renderURL var="editEmployeeEntryRequestURL">
    <portlet:param name="mvcPath" value="/management/edit_employee_entry.jsp" />
</portlet:renderURL>

<portlet:renderURL var="viewEmployeeEntryRequestURL">
    <portlet:param name="mvcPath" value="/management/view_employee_entry.jsp" />
</portlet:renderURL>

<c:choose>

    <c:when test="${themeDisplay.isSignedIn() || currentURL.contains(ManagementPortletKeys.MANAGER_PATH)}">
        <div class="management-main-view">
            <h5 class="management-main-view-title">Managers Area</h5>

            <br />

            <clay:container-fluid>
                <clay:row>
                    <clay:col
                            md="9"
                    >
                        <img src="" alt="banner-manager-view" />
                    </clay:col>

                    <clay:col
                            md="3"
                    >
                        <aui:form action="<%= editEmployeeEntryRequestURL %>" method="post" name="fm">
                            <aui:input label="permission" name="permission" type="hidden" value="<%= true %>" />

                            <div class="button-group">
                                <aui:button-row>
                                    <aui:button type="submit" value="Create Employee Here" />
                                </aui:button-row>
                            </div>
                        </aui:form>

                        <aui:form action="<%= viewEmployeeEntryRequestURL %>" method="post" name="fm">
                            <aui:input label="permission" name="permission" type="hidden" value="<%= true %>" />

                            <div class="button-group">
                                <aui:button-row>
                                    <aui:button type="submit" value="Edit Employees Here" />
                                </aui:button-row>
                            </div>
                        </aui:form>
                    </clay:col>
                </clay:row>
            </clay:container-fluid>
        </div>

    </c:when>

    <c:otherwise>
        <c:redirect url="/error.jsp" />
    </c:otherwise>

</c:choose>