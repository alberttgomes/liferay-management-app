<%@ page import="management.web.display.helper.ArticlesContentHelper" %>
<%@ page import="management.web.display.ArticlesDisplay" %>
<%@ include file="../../init.jsp" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" />

<%
    ManagementDisplayContext managementDisplayContext = (ManagementDisplayContext) request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<div class="managers-area">
    <div class="dashboard-header">
        <h2 class="title-dashboard">Manager Area</h2>
    </div>

    <div class="dashboard-buttons">
        <button onclick="location.href='/web/portal/employees'">Employee Area</button>
        <button onclick="location.href='/web/portal/manager-requests'">Manager Requests</button>
    </div>

    <div class="manager-area-body">
        <div class="row">
            <div class="col-6">
                <div class="manager-initiatives">
                    <h2 class="initiatives-title">Managers Initiatives</h2>

                    <div id="initiativesCarousel" class="carousel slide" data-bs-ride="carousel">
                        <div class="carousel-inner">
                            <%
                                int activeIndex = 0;

                                for (ArticlesDisplay articlesDisplay : ArticlesContentHelper.getArticlesDisplay(
                                        "Initiatives", themeDisplay.getScopeGroupId())) {
                            %>
                                <div class="carousel-item <%= (activeIndex == 0) ? "active" : "" %>">
                                    <div class="initiative-content">
                                        <h2 class="initiative-title"><%= articlesDisplay.getTitle() %></h2>
                                        <div class="initiative-files d-flex">
                                            <%
                                                for (String imageURL : articlesDisplay.getImagesURL()) {
                                            %>
                                                    <div class="initiative-img me-2">
                                                        <img
                                                            alt="initiative-img"
                                                            class="img-fluid initiative-img"
                                                            src="<%= imageURL %>"
                                                        />
                                                    </div>
                                            <%
                                                }
                                            %>
                                        </div>
                                        <div class="initiative-description"><%= articlesDisplay.getDescription() %></div>
                                    </div>
                                </div>
                            <%
                                    activeIndex++;
                                }
                            %>
                        </div>

                        <button class="carousel-control-prev" type="button" data-bs-target="#initiativesCarousel" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#initiativesCarousel" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="col-6">
                <div class="manager-initiatives">
                    <h2 class="initiatives-title">Guide to Managers</h2>

                    <div id="guidesCarousel" class="carousel slide" data-bs-ride="carousel">
                        <div class="carousel-inner">
                            <%
                                int activeIndex_= 0;

                                for (ArticlesDisplay articlesDisplay : ArticlesContentHelper.getArticlesDisplay(
                                        "Guides", themeDisplay.getScopeGroupId())) {
                            %>
                            <div class="carousel-item <%= (activeIndex_ == 0) ? "active" : "" %>">
                                <div class="initiative-content">
                                    <h2 class="initiative-title"><%= articlesDisplay.getTitle() %></h2>
                                    <div class="initiative-files d-flex">
                                        <%
                                            for (String imageURL : articlesDisplay.getImagesURL()) {
                                        %>
                                        <div class="initiative-img me-2">
                                            <img
                                                    alt="initiative-img"
                                                    class="img-fluid initiative-img"
                                                    src="<%= imageURL %>"
                                            />
                                        </div>
                                        <%
                                            }
                                        %>
                                    </div>
                                    <div class="initiative-description"><%= articlesDisplay.getDescription() %></div>
                                </div>
                            </div>
                            <%
                                    activeIndex_++;
                                }
                            %>
                        </div>

                        <button class="carousel-control-prev" type="button" data-bs-target="#guidesCarousel" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#guidesCarousel" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="manager-area-leaders">
        <div class="dashboard-header center">
            <h2 class="title-dashboard">Leaders at the Moment</h2>
        </div>

        <clay:sheet-section
                id="employeeDetails"
        >
            <frontend-data-set:classic-display
                    creationMenu="<%= managementDisplayContext.getCreationMenu() %>"
                    dataProviderKey="<%= EmployeeDetailsFDSNames.EMPLOYEE_DETAILS %>"
                    id="<%= EmployeeDetailsFDSNames.EMPLOYEE_DETAILS %>"
                    itemsPerPage="<%= 10 %>"
                    showSearch="<%= false %>"
                    style="fluid"
            />
        </clay:sheet-section>
    </div>
</div>

<style>
    .center {
        text-align: center !important;
    }
    .dashboard-header {
        background-color: #333;
        color: #fff;
        padding: 10px 20px;
        font-size: 18px;
        text-align: left;
    }
    .dashboard-buttons {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 300px;
    }
    .dashboard-buttons button {
        margin: 0 15px;
        padding: 15px 30px;
        font-size: 18px;
        border: none;
        background-color: #333;
        color: #fff;
        cursor: pointer;
        border-radius: 5px;
        transition: background-color 0.3s;
    }
    .dashboard-buttons button:hover {
        background-color: #555;
    }

    .manager-initiatives {
        margin-bottom: 55px;
        padding: 20px;
        background-color: #1c1c1c;
        border-radius: 8px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
    }

    .initiatives-title {
        font-size: 24px;
        font-weight: bold;
        text-align: center;
        margin-bottom: 20px;
        color: #fff;
    }

    .carousel-item {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: space-between;
        padding: 20px;
        background-color: #292929;
        border-radius: 10px;
        color: #fff;
    }

    .initiative-title {
        font-size: 20px;
        font-weight: bold;
        margin-bottom: 15px;
        color: #007bff;
        text-align: center;
    }

    .initiative-img {
        align-items: center;
        width: 100%;
        height: auto;
        max-height: 350px;
        object-fit: cover;
        border-radius: 8px;
        margin-bottom: 25px;
    }

    .initiative-description {
        font-size: 13px;
        line-height: 1.5;
        text-align: justify;
        color: #d9d9d9;
        padding: 10px;
        /*background-color: #1f1f1f;*/
        border-radius: 6px;
    }

    .row {
        margin: 0 !important;
    }

    @media (max-width: 768px) {
        .carousel-item {
            padding: 15px;
        }

        .initiative-title {
            font-size: 18px;
        }

        .initiative-description {
            font-size: 14px;
        }
    }
</style>