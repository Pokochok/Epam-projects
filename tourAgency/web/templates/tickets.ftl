<#import "components/main-panel.ftl" as mainPanel/>
<#import "/spring.ftl" as spring/>
<#import "components/security.ftl" as secutity/>
<#import "components/ticket-form.ftl" as ticketForm/>

<#assign param = RequestParameters/>

<!DOCTYPE html>
<html>
<head>
    <@mainPanel.page>
    <#include "../css/componentsstyle/ticket-form.css"/>
    <#include "../css/flights-style.css"/>
    <@mainPanel.page>
    <title><@spring.message "common.title.flights"/></title>
</head>
<body>

<div class="contentForm">

    <div class="menu">
        <#if secutity.isAdmin>
            <div class="registerNewTicket">
                <a class="menuRef" href="to_ticket_registration">
                    <@spring.message "admin.ref.registerNewTicket"/>
                </a>
            </div>
        </#if>
    </div>

    <div class="framing">
        <div class="contentContainer">
            <div class="contentHeader">
                <@spring.message "common.message.ticketFilter"/>
                <hr/>
            </div>

            <#assign ticketList = Request.ticketList/>
            <#if (ticketList)??>
                <div class="tickets">
                    <#list ticketList as ticketItem>
                        <@ticketForm.page ticket=ticketItem/>
                    </#list>
                    <#--                    <c:forEach begin="${startIndexOfTicket}" end="${startIndexOfTicket + ticketsPerPage}" var="ticket"-->
                    <#--                               items="${ticketList}">-->
                    <#--                        <c:import url="components/ticket-form.jsp">-->
                    <#--                            <c:param name="flightNumber" value="${ticket.getFlightNumber()}"/>-->
                    <#--                            <c:param name="ticketId" value="${ticket.getId()}"/>-->
                    <#--                            <c:param name="ticketNumber" value="${ticket.getTicketNumber()}"/>-->
                    <#--                            <c:param name="departureCity" value="${ticket.getDepartureCity()}"/>-->
                    <#--                            <c:param name="arrivalCity" value="${ticket.getArrivalCity()}"/>-->
                    <#--                            <c:param name="departureDateTime" value="${ticket.getDepartureDateTime()}"/>-->
                    <#--                            <c:param name="arrivalDateTime" value="${ticket.getArrivalDateTime()}"/>-->
                    <#--                        </c:import>-->
                    <#--                    </c:forEach>-->
                </div>

                <@spring.message "common.message.tour"/>
                <#if param.toutId == '' || param.tourId??>
                    <@spring.message "common.message.notChosen"/>
                </#if>
                ${(param.tourName)!}

                <#if !(param.tourId??)>
                    <div class="continueBooking">

                        <#if !secutity.isAnonymous>
                            <form method="post" action="to_booking">
                                <input type="hidden" name="tourId" value="${param.tourId}"/>
                                <input type="hidden" name="tourName" value="${param.tourName}"/>
                                <input type="hidden" name="arrivalCountry" value="${param.arrivalCountry}"/>
                                <input type="hidden" name="arrivalCity" value="${param.arrivalCity}"/>
                                <input type="hidden" name="departureCity" value="${param.departureCity}"/>
                                <input type="hidden" name="departureDate" value="${param.departureDate}"/>
                                <input type="hidden" name="arrivalDate" value="${param.arrivalDate}"/>
                                <input type="hidden" name="hotel" value="${param.hotel}"/>
                                <input type="hidden" name="nutrition" value="${param.nutrition}"/>
                                <input type="hidden" name="adultsNumber" value="${param.adultsNumber}"/>
                                <input type="hidden" name="childrenNumber" value="${param.childrenNumber}"/>
                                <input type="hidden" name="price" value="${param.price}"/>
                            </form>
                        </#if>
                        <#if secutity.isAnonymous>
                            <form method="post" action="to_login">
                                <input type="hidden" name="tourId" value="${param.tourId}"/>
                                <input type="hidden" name="tourName" value="${param.tourName}"/>
                                <input type="hidden" name="arrivalCountry" value="${param.arrivalCountry}"/>
                                <input type="hidden" name="arrivalCity" value="${param.arrivalCity}"/>
                                <input type="hidden" name="departureCity" value="${param.departureCity}"/>
                                <input type="hidden" name="departureDate" value="${param.departureDate}"/>
                                <input type="hidden" name="arrivalDate" value="${param.arrivalDate}"/>
                                <input type="hidden" name="hotel" value="${param.hotel}"/>
                                <input type="hidden" name="nutrition" value="${param.nutrition}"/>
                                <input type="hidden" name="adultsNumber" value="${param.adultsNumber}"/>
                                <input type="hidden" name="childrenNumber" value="${param.childrenNumber}"/>
                                <input type="hidden" name="price" value="${param.price}"/>
                                <input type="hidden" name="notAuthorized"
                                       value="<@spring.message "guest.attrValue.notAuthorized"/>"/>
                            </form>
                        </#if>

                        <label>
                            <input type="submit" name="continueBooking"
                                   value="<@spring.message "common.submit.continueBooking"/>">
                        </label>
                    </div>
                </#if>

                <nav>
                    <form class="pagination" id="paginationForm" method="post" action="to_tickets">
                        <input type="hidden" name="index" value="${startIndexOfTicket/ticketsPerPage + 1}"/>

                        <input type="hidden" name="tourId" value="${param.tourId}"/>
                        <input type="hidden" name="tourName" value="${param.tourName}"/>
                        <input type="hidden" name="arrivalCountry" value="${param.arrivalCountry}"/>
                        <input type="hidden" name="arrivalCity" value="${param.arrivalCity}"/>
                        <input type="hidden" name="departureCity" value="${param.departureCity}"/>
                        <input type="hidden" name="departureDate" value="${param.departureDate}"/>
                        <input type="hidden" name="arrivalDate" value="${param.arrivalDate}"/>
                        <input type="hidden" name="hotel" value="${param.hotel}"/>
                        <input type="hidden" name="nutrition" value="${param.nutrition}"/>
                        <input type="hidden" name="adultsNumber" value="${param.adultsNumber}"/>
                        <input type="hidden" name="childrenNumber" value="${param.childrenNumber}"/>
                        <input type="hidden" name="price" value="${param.price}"/>
                        <#if startIndexOfTicket == 0>
                            <div class="page-item">
                                <a class="page-link"><@spring.message "common.ref.previousPage"/> </a>
                            </div>
                        </#if>
                        <#if startIndexOfTicket > 0>
                            <div class="page-item">
                                <div id="previousPageForm">
                                    <input type="hidden" id="previousPage" name="changePage" value="-1"
                                           disabled>
                                    <a class="page-link" href="#" onclick="document.getElementById('previousPage').disabled=false;
                                    document.getElementById('paginationForm').submit()">
                                        <@spring.message "common.ref.previousPage"/></a>
                                </div>
                            </div>
                        </#if>

                        <div class="page-item"><a class="page-link">${index}</a></div>

                        <#if startIndexOfTicket + ticketsPerPage < numberOfTickets>
                            <div class="page-item">
                                <div id="nextPageForm">
                                    <input type="hidden" id="nextPage" name="changePage" value="1" disabled>
                                    <a class="page-link" href="#" onclick="document.getElementById('nextPage').disabled = false;
                                    document.getElementById('paginationForm').submit()">
                                        <@spring.message "common.ref.nextPage"/></a>
                                </div>
                            </div>
                        </#if>
                        <#if startIndexOfTicket + ticketsPerPage >= ${numberOfTickets}>
                            <div class="page-item">
                                <a class="page-link"><@spring.message "common.ref.nextPage"/></a>
                            </div>
                        </#if>
                    </form>
                </nav>
            </#if>
        </div>
    </div>
</div>
</body>
<#import "components/timestamp.ftl" as timestamp/>
<@timestamp.page/>
</html>