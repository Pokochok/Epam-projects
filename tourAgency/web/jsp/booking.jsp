<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <%@include file="components/main-panel.jsp"%>
    <style>
        <%@include file="../css/booking-style.css"%>
    </style>
    <fmt:setBundle basename="jsp/booking"/>
    <title><fmt:message key="common.title.booking"/></title>
</head>
<body>
<form class="contentForm" method="post" action="controller">
    <input type="hidden" name="command" value="booking"/>
    <c:if test="${userRole == 'CLIENT' || userRole == 'AGENT'}">
        <div class="contentContainer">
            <div class="allInf">
                <div class="tourContent">
                    <c:if test="${param.tourId != null}">
                        <input type="hidden" name="tourId" value="${param.tourId}"/>
                        <div class="tourName">
                            <c:out value="${param.tourName}"/>
                        </div>

                        <div class="contentItem">
                            <div class="itemMsg">
                                <fmt:message key="common.message.arrivalCountry"/>
                            </div>
                            <c:out value="${param.arrivalCountry}"/>
                        </div>

                        <div class="contentItem">
                            <div class="itemMsg">
                                <fmt:message key="common.message.arrivalCity"/>
                            </div>
                            <c:out value="${param.arrivalCity}"/>
                        </div>

                        <div class="contentItem">
                            <div class="itemMsg">
                                <fmt:message key="common.message.departureCity"/>
                            </div>
                            <c:out value="${param.departureCity}"/>
                        </div>

                        <div class="contentItem">
                            <div class="itemMsg">
                                <fmt:message key="common.message.departureDate"/>
                            </div>
                            <c:out value="${param.departureDate}"/>
                        </div>

                        <div class="contentItem">
                            <div class="itemMsg">
                                <fmt:message key="common.message.arrivalDate"/>
                            </div>
                            <c:out value="${param.arrivalDate}"/>
                        </div>

                        <div class="contentItem">
                            <div class="itemMsg">
                                <fmt:message key="common.message.hotel"/>
                            </div>
                            <c:out value="${param.hotel}"/>
                        </div>

                        <div class="contentItem">
                            <div class="itemMsg">
                                <fmt:message key="common.message.nutrition"/>
                            </div>
                            <c:out value="${param.nutrition}"/>
                        </div>

                        <div class="contentItem">
                            <div class="itemMsg">
                                <fmt:message key="common.message.numberOfAdults"/>
                            </div>
                            <c:out value="${param.adultsNumber}"/>
                        </div>

                        <div class="contentItem">
                            <div class="itemMsg">
                                <fmt:message key="common.message.numberOfChildren"/>
                            </div>
                            <c:out value="${param.childrenNumber}"/>
                        </div>

                        <div class="contentItem">
                            <div class="itemMsg">
                                <fmt:message key="common.message.price"/>
                            </div>
                            <c:out value="${param.price}$"/>
                        </div>
                    </c:if>
                    <c:if test="${param.tourId == null}">
                        <div class="notDefined">
                            <fmt:message key="clientAgent.message.notDefined"/>
                        </div>
                    </c:if>
                </div>


                <div class="ticketContent">
                    <c:if test="${param.ticketId != null}">
                        <input type="hidden" name="ticketId" value="${param.ticketId}"/>
                        <div class="flightPlace">
                            <c:out value="${param.departureCity} - ${param.arrivalCity}"/>
                        </div>

                        <div class="contentItem">
                            <div class="itemMsg">
                                <fmt:message key="common.message.flightNumber"/>
                            </div>
                            <c:out value="${param.flightNumber}"/>
                        </div>

                        <div class="contentItem">
                            <c:out value="${param.departureDateTime} - ${param.arrivalDateTime}"/>
                        </div>
                    </c:if>
                    <c:if test="${param.ticketId == null}">
                        <div class="notDefined">
                            <fmt:message key="clientAgent.message.notDefined"/>
                        </div>
                    </c:if>
                </div>

            </div>

            <c:if test="${userRole == 'AGENT'}">
                <input type="hidden" name="agentId" value="${userId}">
                <div class="emailInput">
                    <fmt:message key="agent.message.inputClientEmail"/>
                    <label>
                        <input type="text" name="clientEmail" required
                               minlength="6" maxlength="100"  pattern="^[a-zA-Z0-9.,_%+-]+@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}$">
                    </label>
                    <br>${errorEmail}
                </div>
            </c:if>
            <c:if test="${userRole != 'AGENT'}">
                <input type="hidden" name="clientId" value="${userId}">
            </c:if>

            <fmt:message key="clientAgent.submit.backToTicketSelection" var="ticketSelection"/>
            <label>
                <input type="button" name="ticketSelection" onclick="history.back()" value="${ticketSelection}">
            </label>

            <fmt:message key="clientAgent.submit.confirmReservation" var="confirmReservation"/>
            <label>
                <input type="submit" name="confirmReservation" value="${confirmReservation}">
            </label>
        </div>
    </c:if>

</form>
</body>
<c:import url="components/timestamp.jsp"></c:import>
</html>
