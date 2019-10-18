<#import "components/main-panel.ftl" as mainPanel/>
<#import "/spring.ftl" as spring/>
<#import "components/security.ftl" as secutity/>
<#import "components/tour-form.ftl" as tourForm/>

<!DOCTYPE html>
<html>
<head>
    <@mainPanel.page>
        <#include "../css/componentsstyle/tour-form.css"/>
        <#include "../css/tours-style.css"/>
    </@mainPanel.page>
    <title><@spring.message "common.title.Tours"/></title>
</head>
<body>

<div class="contentForm" >

    <div class="menu">
        <#if (secutity.isAdmin)!false>
            <div class="registerNewTour">
                <a class="menuRef" href="to_tour_registration">
                    <@spring.message "admin.ref.registerNewTour"/>
                </a>
            </div>
        </#if>
    </div>
    <#assign tourList = (Request.tourList)/>

    <div class="framing">

        <div class="contentContainer">
            <div class="contentHeader">
                <@spring.message "common.message.availableTours"/>
                <hr/>
            </div>

            <div class="tours">
                <#list tourList>
                    <ul>
                        <#items as tourItem>
                            <li type="none"><@tourForm.page tour=tourItem/></li>
                        </#items>
                    </ul>
                </#list>
<#--                <c:forEach begin="${startIndexOfTours}" end="${startIndexOfTours + toursPerPage - 1}" var="tour"-->
<#--                           items="${tourList}">-->
<#--                    <c:import url="components/tour-form.jsp">-->
<#--                        <c:param name="tourId" value="${tour.getId()}"/>-->
<#--                        <c:param name="tourName" value="${tour.getTourName()}"/>-->
<#--                        <c:param name="arrivalCountry" value="${tour.getArrivalCountry()}"/>-->
<#--                        <c:param name="arrivalCity" value="${tour.getArrivalCity()}"/>-->
<#--                        <c:param name="departureCity" value="${tour.getDepartureCity()}"/>-->
<#--                        <c:param name="departureDate" value="${tour.getDepartureDate()}"/>-->
<#--                        <c:param name="arrivalDate" value="${tour.getArrivalDate()}"/>-->
<#--                        <c:param name="hotel" value="${tour.getHotel()}"/>-->
<#--                        <c:param name="nutrition" value="${tour.getNutrition()}"/>-->
<#--                        <c:param name="adultsNumber" value="${tour.getAdultsNumber()}"/>-->
<#--                        <c:param name="childrenNumber" value="${tour.getChildrenNumber()}"/>-->
<#--                        <c:param name="price" value="${tour.getPrice()}"/>-->
<#--                        <c:param name="status" value="${tour.getStatus()}"/>-->
<#--                    </c:import>-->
<#--                </c:forEach>-->
            </div>

            <nav>
                <form class="pagination" id="paginationForm" method="post" action="to_tours">
                    <input type="hidden" name="index" value="${startIndexOfTours/toursPerPage + 1}"/>

                    <#if ((startIndexOfTours!0) == 0)>
                        <div class="page-item disabled">
                            <a class="page-link"><@spring.message "common.ref.previousPage"/> </a>
                        </div>
                    </#if>
                    <#if ((startIndexOfTours!0) > 0)>
                        <div class="page-item">
                            <div id="previousPageForm">
                                <input type="hidden" id="previousPage" name="changePage" value="-1" disabled>
                                <a class="page-link" href="#" onclick="document.getElementById('previousPage').disabled = false;
                                    document.getElementById('paginationForm').submit()">
                                    <@spring.message "common.ref.previousPage"/></a>
                            </div>
                        </div>
                    </#if>

                    <div class="page-item"><a class="page-link">${index}</a></div>

                    <#if ((startIndexOfTours!0) + (toursPerPage!8) < (numberOfTours!8))>
                        <div class="page-item">
                            <div id="nextPageForm">
                                <input type="hidden" id="nextPage" name="changePage" value="1" disabled>
                                <a class="page-link" href="#" onclick="document.getElementById('nextPage').disabled = false;
                                    document.getElementById('paginationForm').submit()">
                                    <@spring.message "common.ref.nextPage"/> </a>
                            </div>
                        </div>
                    </#if>
                    <#if ((startIndexOfTours!0) + (toursPerPage!8) >= (numberOfTours!8))>
                        <div class="page-item disabled">
                            <a class="page-link"><@spring.message "common.ref.nextPage"/></a>
                        </div>
                    </#if>
                </form>
            </nav>
        </div>
    </div>
</div>

</body>
<#import "components/timestamp.ftl" as timestamp/>
<@timestamp.page/>
</html>
