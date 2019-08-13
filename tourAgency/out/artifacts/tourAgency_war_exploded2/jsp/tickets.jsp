<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <%@include file="components/main-panel.jsp"%>
    <link href="../css/componentsstyle/ticket-form.css" rel="stylesheet" type="text/css">
    <link href="../css/flights-style.css" rel="stylesheet" type="text/css">
    <fmt:setBundle basename="jsp/tickets"/>
    <title><fmt:message key="common.title.flights"/></title>
</head>
<body>

<form class="contentForm" method="post" action="controller">

    <div class="menu">
        <c:if test="${sessionScope.userRole == 'ADMIN'}">
            <div class="registerNewTicket">
                <a class="menuRef" href="controller?command=to_ticket_registration">
                    <fmt:message key="admin.ref.registerNewTicket"/>
                </a>
            </div>
        </c:if>
    </div>

    <div class="framing">
        <div class="contentContainer">
            <div class="contentHeader">
                <fmt:message key="common.message.ticketFilter"/>
                <hr/>
            </div>


            <c:if test="${ticketList != ''}">
                <div class="tickets">
                    <c:forEach begin="${startIndexOfTicket}" end="${startIndexOfTicket + ticketsPerPage}" var="ticket"
                               items="${ticketList}">
                        <c:import url="components/ticket-form.jsp">
                            <c:param name="flightNumber" value="${ticket.getFlightNumber()}"/>
                            <c:param name="ticketId" value="${ticket.getId()}"/>
                            <c:param name="ticketNumber" value="${ticket.getTicketNumber()}"/>
                            <c:param name="departureCity" value="${ticket.getDepartureCity()}"/>
                            <c:param name="arrivalCity" value="${ticket.getArrivalCity()}"/>
                            <c:param name="departureDateTime" value="${ticket.getDepartureDateTime()}"/>
                            <c:param name="arrivalDateTime" value="${ticket.getArrivalDateTime()}"/>
                        </c:import>
                    </c:forEach>
                </div>

                <fmt:message key="common.message.tour"/>
                <c:if test="${param.toutId == '' || param.tourId == null}">
                    <fmt:message key="common.message.notChosen"/>
                </c:if>
                <c:out value="${param.tourName}"/>

                <c:if test="${param.tourId != null}">
                    <form class="continueBooking" method="post">
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

                        <c:if test="${sessionScope.userRole != 'GUEST'}">
                            <input type="hidden" name="command" value="to_booking"/>
                        </c:if>
                        <c:if test="${sessionScope.userRole == 'GUEST'}">
                            <input type="hidden" name="command" value="to_login"/>
                            <fmt:message key="guest.attrValue.notAuthorized" var="notAuthorized"/>
                            <input type="hidden" name="notAuthorized" value="${notAuthorized}"/>
                        </c:if>

                        <label>
                            <fmt:message key="common.submit.continueBooking" var="continueBooking"/>
                            <input type="submit" name="continueBooking" value="${continueBooking}">
                        </label>
                    </form>
                </c:if>


                <nav>
                    <div class="pagination">
                        <c:if test="${startIndexOfTicket == 0}">
                            <div class="page-item" >
                                <a class="page-link"><fmt:message key="common.ref.previousPage"/> </a>
                            </div>
                        </c:if>
                        <c:if test="${startIndexOfTicket > 0}">
                            <div class="page-item">
                                <form id="previousPageForm" method="post" action="controller">
                                    <a class="page-link"
                                       href="controller?command=to_tickets&changePage=-1&index=${startIndexOfTicket/ticketsPerPage + 1}">
                                        <fmt:message key="common.ref.previousPage"/></a>
                                </form>
                            </div>
                        </c:if>

                        <div class="page-item"><a class="page-link">${index}</a></div>

                        <c:if test="${startIndexOfTicket + ticketsPerPage < numberOfTickets}">
                            <div class="page-item">
                                <form id="nextPageForm" method="post" action="controller">
                                    <a class="page-link"
                                       href="controller?command=to_tickets&changePage=1&index=${startIndexOfTicket/ticketsPerPage + 1}">
                                        <fmt:message key="common.ref.nextPage"/></a>
                                </form>
                            </div>
                        </c:if>
                        <c:if test="${startIndexOfTicket + ticketsPerPage >= numberOfTickets}">
                            <div class="page-item" >
                                <a class="page-link"><fmt:message key="common.ref.nextPage"/></a>
                            </div>
                        </c:if>
                    </div>
                </nav>
            </c:if>
        </div>
    </div>
</form>
</body>
</html>
