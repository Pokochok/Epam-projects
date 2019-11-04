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
    </@mainPanel.page>
    <title><@spring.message "common.title.flights"/></title>
</head>
<body>

<div class="contentForm">

    <div class="menu">
        <#if (secutity.isAdmin)!false>
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
                </div>

                <#if (secutity.isAdmin)!false>
                    <form action="remove_tickets" method="post">
                        <input class="uui-button transparent raspberry" type="submit" value="<@spring.message "admin.submit.deleteInvalidTicketsAndOrders"/>">
                    </form>
                </#if>

                <@spring.message "common.message.tour"/>
                ${(param.tourName)!}
                <#if ((param.tourId??) && ((param.tourId!"0") != "0"))>
                    <form class="continueBooking" method="post" action="to_booking">
                        <input type="hidden" name="tourId" value="${param.tourId!}"/>
                        <input type="hidden" name="tourName" value="${param.tourName!}"/>
                        <input type="hidden" name="arrivalCountry" value="${param.arrivalCountry!}"/>
                        <input type="hidden" name="arrivalCity" value="${param.arrivalCity!}"/>
                        <input type="hidden" name="departureCity" value="${param.departureCity!}"/>
                        <input type="hidden" name="departureDate" value="${param.departureDate!}"/>
                        <input type="hidden" name="arrivalDate" value="${param.arrivalDate!}"/>
                        <input type="hidden" name="hotel" value="${param.hotel!}"/>
                        <input type="hidden" name="nutrition" value="${param.nutrition!}"/>
                        <input type="hidden" name="adultsNumber" value="${param.adultsNumber!}"/>
                        <input type="hidden" name="childrenNumber" value="${param.childrenNumber!}"/>
                        <input type="hidden" name="price" value="${param.price!}"/>
                        <label>
                            <button class="uui-button blue left-icon">
                                <img src="uui/images/icons/search-icon-white.svg" alt="" /><@spring.message "common.submit.continueBooking"/>
                            </button>
                        </label>
                    </form>
                <#else>
                        <@spring.message "common.message.notChosen"/>
                </#if>
                <form method="post" action="to_tickets">
                    <ul class="uui-pagination">
                        <li class="actions-wrapper">
                            <ul class="pagination-items">
                                <#if ((startIndexOfTicket!0) > 0)>
                                    <li class="back-pages">
                                        <input type="hidden" id="previousPage" name="changePage" value="-1">
                                        <a href="to_tickets?changePage=-1&index=${(startIndexOfTicket!0)/(ticketsPerPage!0) + 1}&
                                            tourName=${param.tourName!}&arrivalCountry=${param.arrivalCountry!}&arrivalCity=${param.arrivalCity!}
                                            &departureCity=${param.departureCity!}&departureDate=${param.departureDate!}&arrivalDate=${param.arrivalDate!}
                                            &hotel=${param.hotel!}&nutrition=${param.nutrition!}&adultsNumber=${param.adultsNumber!}
                                            &childrenNumber=${param.childrenNumber!}&price=${param.price!}">
                                            <i class="fa fa-angle-double-left"></i>
                                        </a>
                                    </li>
                                <#else>
                                    <li class="back-pages disable">
                                        <a href="" disabled>
                                            <i class="fa fa-angle-double-left"></i>
                                        </a>
                                    </li>
                                </#if>
                            </ul>
                        </li>
                        <li class="pages-wrapper">
                            <ul class="pagination-items">
                                <#if ((index!1) >= 3)>
                                    <li>
                                        <a href="to_tickets?changePage=0&index=${(index!1)-2}&tourName=${param.tourName!}
                                            &arrivalCountry=${param.arrivalCountry!}&arrivalCity=${param.arrivalCity!}
                                            &departureCity=${param.departureCity!}&departureDate=${param.departureDate!}&arrivalDate=${param.arrivalDate!}
                                            &hotel=${param.hotel!}&nutrition=${param.nutrition!}&adultsNumber=${param.adultsNumber!}
                                            &childrenNumber=${param.childrenNumber!}&price=${param.price!}">${(index!1)-2}</a>
                                    </li>
                                </#if>
                                <#if ((index!1) >= 2)>
                                    <li>
                                        <a href="to_tickets?changePage=0&index=${(index!1)-1}&
                                        tourName=${param.tourName!}&arrivalCountry=${param.arrivalCountry!}&arrivalCity=${param.arrivalCity!}
                                            &departureCity=${param.departureCity!}&departureDate=${param.departureDate!}&arrivalDate=${param.arrivalDate!}
                                            &hotel=${param.hotel!}&nutrition=${param.nutrition!}&adultsNumber=${param.adultsNumber!}
                                            &childrenNumber=${param.childrenNumber!}&price=${param.price!}">${(index!1)-1}</a>
                                    </li>
                                </#if>
                                <li class="active">
                                    <a href="to_tickets?changePage=0&index=${(index!1)}&tourName=${param.tourName!}&
                                            &arrivalCountry=${param.arrivalCountry!}&arrivalCity=${param.arrivalCity!}
                                            &departureCity=${param.departureCity!}&departureDate=${param.departureDate!}&arrivalDate=${param.arrivalDate!}
                                            &hotel=${param.hotel!}&nutrition=${param.nutrition!}&adultsNumber=${param.adultsNumber!}
                                            &childrenNumber=${param.childrenNumber!}&price=${param.price!}">${(index!1)}</a>
                                </li>
                                <#if ((startIndexOfTicket!0) + (ticketsPerPage!8) < (numberOfTickets!8))>
                                    <li>
                                        <a href="to_tickets?changePage=0&index=${(index!1)+1}&
                                    tourName=${param.tourName!}&arrivalCountry=${param.arrivalCountry!}&arrivalCity=${param.arrivalCity!}
                                            &departureCity=${param.departureCity!}&departureDate=${param.departureDate!}&arrivalDate=${param.arrivalDate!}
                                            &hotel=${param.hotel!}&nutrition=${param.nutrition!}&adultsNumber=${param.adultsNumber!}
                                            &childrenNumber=${param.childrenNumber!}&price=${param.price!}">${(index!1)+1}</a>
                                    </li>
                                </#if>
                                <#if ((startIndexOfTicket!0) + (ticketsPerPage!8)*2 < (numberOfTickets!8))>
                                    <li>
                                        <a href="to_tickets?changePage=0&index=${(index!1)+2}&tourName=${param.tourName!}
                                            &arrivalCountry=${param.arrivalCountry!}&arrivalCity=${param.arrivalCity!}
                                            &departureCity=${param.departureCity!}&departureDate=${param.departureDate!}&arrivalDate=${param.arrivalDate!}
                                            &hotel=${param.hotel!}&nutrition=${param.nutrition!}&adultsNumber=${param.adultsNumber!}
                                            &childrenNumber=${param.childrenNumber!}&price=${param.price!}">${(index!1)+2}</a>
                                    </li>
                                </#if>
                            </ul>
                        </li>
                    <li class="actions-wrapper">
                        <ul class="pagination-items">
                            <#if ((startIndexOfTicket!0) + (ticketsPerPage!8) < (numberOfTickets!8))>
                                <li class="next-page">
                                    <a href="to_tickets?changePage=1&index=${(startIndexOfTicket!0)/(ticketsPerPage!0) + 1}&
                                    tourName=${param.tourName!}&arrivalCountry=${param.arrivalCountry!}&arrivalCity=${param.arrivalCity!}
                                            &departureCity=${param.departureCity!}&departureDate=${param.departureDate!}&arrivalDate=${param.arrivalDate!}
                                            &hotel=${param.hotel!}&nutrition=${param.nutrition!}&adultsNumber=${param.adultsNumber!}
                                            &childrenNumber=${param.childrenNumber!}&price=${param.price!}">
                                        <i class="fa fa-angle-right"></i>
                                    </a>
                                </li>
                            <#else>
                                <li class="next-page disable">
                                    <a href="" disabled>
                                        <i class="fa fa-angle-right"></i>
                                    </a>
                                </li>
                            </#if>
                        </ul>
                    </li>
                    </ul>
                </form>
            </#if>
        </div>
    </div>
</div>
</body>
<#import "components/timestamp.ftl" as timestamp/>
<@timestamp.page/>
</html>