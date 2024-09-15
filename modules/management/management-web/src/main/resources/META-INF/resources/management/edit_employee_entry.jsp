
<%@ include file="../init.jsp" %>

<%
    String redirect = ParamUtil.getString(request, "redirect");
//    Manager manager = ManagerLocalServiceUtil.fetchManager(0);
//    long managerIdFK = manager.getManagerId();
%>

<portlet:actionURL name="/management/edit_employee_management" var="addEmployeeURL">
    <portlet:param name="mvcRenderCommandName" value="/management/edit_employee_management" />
    <portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" />
</portlet:actionURL>

<aui:form action="<%= addEmployeeURL %>" method="post" name="fm">
    <aui:input name="redirect" type="hidden" value="<%= redirect %>" />
<%--    <aui:input name="managerIdFK" type="hidden" value="<%= managerIdFK %>" />--%>

    <clay:sheet>
        <clay:sheet-section>
            <h3 class="sheet-subtitle"><liferay-ui:message key="add-new-employee" /></h3>

            <div class="form-group">
                <clay:row>
                    <clay:col
                            md="6"
                    >
                        <aui:input label="department" name="department" required="<%= true %>" size="30" value="" />
                    </clay:col>
                </clay:row>
            </div>

            <div class="form-group">
                <clay:row>
                    <clay:col
                            md="6"
                    >
                        <aui:input label="firstName" name="firstName" required="<%= true %>" size="30" value="" />
                    </clay:col>
                </clay:row>
            </div>

            <div class="form-group">
                <clay:row>
                    <clay:col
                            md="6"
                    >
                        <aui:input label="lastName" name="lastName" required="<%= true %>" size="30" value="" />
                    </clay:col>
                </clay:row>
            </div>

            <div class="form-group">
                <clay:row>
                    <clay:col
                            md="6"
                    >
                        <aui:input label="isManager" name="isManager" required="<%= true %>" size="30" value="false" />
                    </clay:col>
                </clay:row>
            </div>

            <div class="form-group">
                <clay:row>
                    <clay:col
                            md="6"
                    >
                        <aui:input label="level" name="level" required="<%= true %>" size="30" value="1" />
                    </clay:col>
                </clay:row>
            </div>

            <div class="form-group">
                <clay:row>
                    <clay:col
                            md="6"
                    >
                        <aui:input label="position" name="position" required="<%= true %>" size="30" value="" />
                    </clay:col>
                </clay:row>
            </div>

            <div class="form-group">
                <aui:button-row>
                    <aui:button type="submit" />
                </aui:button-row>
            </div>
        </clay:sheet-section>
    </clay:sheet>
</aui:form>