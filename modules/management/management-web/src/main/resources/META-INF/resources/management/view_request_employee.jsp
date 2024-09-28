<%@ include file="../init.jsp" %>

<%
    ManagementDisplayContext managementDisplayContext = (ManagementDisplayContext) request.getAttribute(ManagementPortletKeys.EMPLOYEE_DISPLAY);
    long[] selectedClassNameIds = managementDisplayContext.getSelectedRequestIds();
    List<String> indexes = new ArrayList<>();
%>

<portlet:actionURL name="/management/view_request_employee" var="openRequestEmployeeURL">
    <portlet:param name="mvcRenderCommandName" value="/management/view_request_employee" />
    <portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" />
</portlet:actionURL>

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

                %>
                    <clay:select
                            cssClass="types-of-request-select"
                            label="Request types"
                            name='type'
                            options="<%= managementDisplayContext.getEmployeeOptions(selectedEmployeeRequestId) %>"
                    />
                <%
                }
                %>
            </div>

            <div class="form-group">
                <clay:row>
                    <clay:col
                            md="6"
                    >
                        <aui:input label="description" name="Description" required="<%= true %>" size="300" value="" />
                    </clay:col>
                </clay:row>
            </div>

            <div class="form-group">
                <clay:row>
                    <clay:col
                            md="6"
                    >
                        <aui:input label="reason" name="Reason" required="<%= true %>" size="150" value="" />
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

    <aui:input name="indexes" type="hidden" value="<%= StringUtil.merge(indexes) %>" />
</liferay-frontend:fieldset>