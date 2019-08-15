<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <%@include file="components/main-panel.jsp" %>
    <link href="../css/componentsstyle/ticket-form.css" rel="stylesheet" type="text/css">
    <link href="../css/flights-style.css" rel="stylesheet" type="text/css">
    <fmt:setBundle basename="jsp/tickets"/>
    <title><fmt:message key="common.title.flights"/></title>
</head>
<body>

<div class="contentForm">

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
                    <form class="continueBooking" method="post" action="controller">
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
                    <form class="pagination" id="paginationForm" method="post" action="controller">
                        <input type="hidden" id="command" name="command" value="to_tickets">
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
                        <c:if test="${startIndexOfTicket == 0}">
                            <div class="page-item">
                                <a class="page-link"><fmt:message key="common.ref.previousPage"/> </a>
                            </div>
                        </c:if>
                        <c:if test="${startIndexOfTicket > 0}">
                            <div class="page-item">
                                <div id="previousPageForm">
                                    <input type="hidden" id="previousPage" name="changePage" value="-1" disabled>
                                    <a class="page-link" href="#" onclick="document.getElementById('previousPage').disabled=false;
                                    document.getElementById('paginationForm').submit()">
                                        <fmt:message key="common.ref.previousPage"/></a>
                                </div>
                            </div>
                        </c:if>

                        <div class="page-item"><a class="page-link">${index}</a></div>

                        <c:if test="${startIndexOfTicket + ticketsPerPage < numberOfTickets}">
                            <div class="page-item">
                                <div id="nextPageForm">
                                    <input type="hidden" id="nextPage" name="changePage" value="1" disabled>
                                    <a class="page-link" href="#" onclick="document.getElementById('nextPage').disabled = false;
                                    document.getElementById('paginationForm').submit()">
                                        <fmt:message key="common.ref.nextPage"/></a>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${startIndexOfTicket + ticketsPerPage >= numberOfTickets}">
                            <div class="page-item">
                                <a class="page-link"><fmt:message key="common.ref.nextPage"/></a>
                            </div>
                        </c:if>
                    </form>
                </nav>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
