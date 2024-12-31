<%@ include file="../init.jsp" %>

<%
    ManagementDisplayContext managementDisplayContext = (ManagementDisplayContext) request.getAttribute(ManagementPortletKeys.EMPLOYEE_DISPLAY);
    long[] selectedClassNameIds = managementDisplayContext.getSelectedRequestIds();
    List<String> indexes = new ArrayList<>();
    long type = 0;
%>

<portlet:actionURL name="/management/open_request_employee" var="openRequestEmployeeURL">
    <portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" />
    <portlet:param name="mvcRenderCommandName" value="/management/view_details_employee" />
</portlet:actionURL>

<aui:form action="<%= openRequestEmployeeURL %>" method="post" name="fm">
    <liferay-frontend:fieldset
            collapsed="<%= true %>"
            collapsible="<%= true %>"
            id='<%= liferayPortletResponse.getNamespace() + "extraFields" %>'
            label="associated-asset-types"
    >
        <clay:sheet>
            <clay:sheet-section>
                <h3 class="sheet-subtitle">Open a new request to the employee</h3>

                <div class="row-fields">

                    <%
                        for (int index = 0; index < selectedClassNameIds.length; index++) {
                            indexes.add(String.valueOf(index));

                            long selectedEmployeeRequestId = selectedClassNameIds[index];

                            if (index == selectedClassNameIds.length - 1) {
                                type = selectedEmployeeRequestId;
                    %>
                                <clay:select
                                        cssClass="types-of-request-select"
                                        label="Request types"
                                        name='type'
                                        options="<%= managementDisplayContext.getEmployeeOptions(selectedEmployeeRequestId) %>"
                                        value="<%= selectedEmployeeRequestId %>"
                                />
                    <%
                            }
                        }
                    %>
                </div>

                <div class="form-group">
                    <clay:row>
                        <clay:col
                                md="12"
                        >
                            <aui:input label="Description" name="description" required="<%= true %>" size="300" value="" />
                        </clay:col>
                    </clay:row>
                </div>

                <div class="form-group">
                    <clay:row>
                        <clay:col
                                md="12"
                        >
                            <aui:input label="Reason" name="reason" required="<%= true %>" size="150" value="" />
                        </clay:col>
                    </clay:row>
                </div>

                <c:choose>
                    <c:when test="${type == EmployeeRequestConstant.PROMOTION}">
                        <div class="form-group">
                            <clay:row>
                                <clay:col
                                        md="12"
                                >
                                    <aui:input label="New Level" name="newLevel" required="<%= true %>" size="150" value="" />
                                </clay:col>
                            </clay:row>
                        </div>

                        <div class="form-group">
                            <clay:row>
                                <clay:col
                                        md="12"
                                >
                                    <aui:input label="New Position" name="newPosition" required="<%= true %>" size="150" value="" />
                                </clay:col>
                            </clay:row>
                        </div>
                    </c:when>
                </c:choose>

                <div class="form-group">
                    <clay:row>
                        <clay:col
                                md="12"
                        >
                            <aui:input label="EmployeeId" name="employeeId" required="<%= true %>" size="150" value="" />
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

        <aui:input name="option" type="hidden" value="<%= StringUtil.merge(indexes) %>" />
    </liferay-frontend:fieldset>
</aui:form>