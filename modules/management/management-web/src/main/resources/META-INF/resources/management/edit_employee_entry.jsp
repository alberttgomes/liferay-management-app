<%@ include file="../init.jsp" %>

<portlet:actionURL name="/management/edit_employee_management" var="addEmployeeURL">
    <portlet:param name="mvcRenderCommandName" value="/management/edit_employee_management" />
    <portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" />
</portlet:actionURL>

<div class="dashboard-header">
    Add a new employee entry
</div>

<aui:form action="<%= addEmployeeURL %>" method="post" name="fm">
    <clay:sheet>
        <clay:sheet-section>
            <div class="form-group">
                <clay:row>
                    <clay:col
                            cssClass="clay-col-custom"
                            md="12"
                    >
                        <aui:input label="Department" name="department" required="<%= true %>" size="30" value="" />
                    </clay:col>
                </clay:row>
            </div>

            <div class="form-group">
                <clay:row>
                    <clay:col
                            cssClass="clay-col-custom"
                            md="12"
                    >
                        <aui:input label="First Name" name="firstName" required="<%= true %>" size="30" value="" />
                    </clay:col>
                </clay:row>
            </div>

            <div class="form-group">
                <clay:row>
                    <clay:col
                            cssClass="clay-col-custom"
                            md="12"
                    >
                        <aui:input label="Last Name" name="lastName" required="<%= true %>" size="30" value="" />
                    </clay:col>
                </clay:row>
            </div>

            <div class="form-group">
                <clay:row>
                    <clay:col
                            cssClass="clay-col-custom"
                            md="12"
                    >
                        <aui:input label="Is manager?" name="isManager" required="<%= true %>" size="30" value="false" />
                    </clay:col>
                </clay:row>
            </div>

            <div class="form-group">
                <clay:row>
                    <clay:col
                            cssClass="clay-col-custom"
                            md="12"
                    >
                        <aui:input label="Level" name="level" required="<%= true %>" size="30" value="1" />
                    </clay:col>
                </clay:row>
            </div>

            <div class="form-group">
                <clay:row>
                    <clay:col
                            cssClass="clay-col-custom"
                            md="12"
                    >
                        <aui:input label="Position" name="position" required="<%= true %>" size="30" value="" />
                    </clay:col>
                </clay:row>
            </div>
            <div class="form-group">
                <clay:row>
                    <clay:col
                            cssClass="clay-col-custom"
                            md="12"
                    >
                        <aui:input label="Manager ID" name="managerId" required="<%= true %>" size="30" value="" />
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

<style>
    .clay-col-custom {
        align-items: center;
        align-self: center;
    }

    .dashboard-header {
        background-color: #333;
        color: #fff;
        padding: 10px 20px;
        text-align: center;
        font-size: 18px;
        margin-bottom: 25px;
    }
</style>