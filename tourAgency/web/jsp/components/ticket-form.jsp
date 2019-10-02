<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="jsp/tickets"/>
<!DOCTYPE html>
<html>

<body>

<div class="ticketItem">
    <div class="MinInf">
        <div class="flightNumber_flightPlace">
            <div class="flightNumber">
                <fmt:message key="common.message.flightNumber" var="flightNumber"/>
                <c:out value="${flightNumber} ${param.flightNumber}"/>
            </div>

            <div class="flightPlace">
                <c:out value="${param.departureCity} - ${param.arrivalCity}"/>
            </div>
        </div>

        <div class="flightDate">
            <c:out value="${param.departureDateTime} - ${param.arrivalDateTime}"/>
        </div>
    </div>

    <div class="departureCity">
        <div>


            <c:if test="${sessionScope.userRole != 'ADMIN'}">
                <fmt:message key="common.submit.choose" var="choose"/>
                <div class="submitChoose">
                    <c:if test="${sessionScope.userRole != 'GUEST'}">
                        <form method="post" action="to_booking">
                        <input type="hidden" name="ticketId" value="${param.ticketId}"/>
                                    <input type="hidden" name="departureDateTime" value="${param.departureDateTime}"/>
                                    <input type="hidden" name="arrivalDateTime" value="${param.arrivalDateTime}"/>
                                    <input type="hidden" name="flightNumber" value="${param.flightNumber}"/>
                                    <input type="hidden" name="arrivalCity" value="${param.arrivalCity}"/>
                                    <input type="hidden" name="departureCity" value="${param.departureCity}"/>

                                    <c:if test="${param.tourId != null}">
                                        <input type="hidden" name="tourId" value="${param.tourId}"/>
                                        <input type="hidden" name="tourName" value="${param.tourName}"/>
                                        <input type="hidden" name="arrivalCountry" value="${param.arrivalCountry}"/>
                                        <input type="hidden" name="departureDate" value="${param.departureDate}"/>
                                        <input type="hidden" name="arrivalDate" value="${param.arrivalDate}"/>
                                        <input type="hidden" name="hotel" value="${param.hotel}"/>
                                        <input type="hidden" name="nutrition" value="${param.nutrition}"/>
                                        <input type="hidden" name="adultsNumber" value="${param.adultsNumber}"/>
                                        <input type="hidden" name="childrenNumber" value="${param.childrenNumber}"/>
                                        <input type="hidden" name="price" value="${param.price}"/>
                                    </c:if>
                        <form/>
                    </c:if>
                    <c:if test="${sessionScope.userRole == 'GUEST'}">
                        <form method="post" action="to_login">
                        <input type="hidden" name="ticketId" value="${param.ticketId}"/>
                                    <input type="hidden" name="departureDateTime" value="${param.departureDateTime}"/>
                                    <input type="hidden" name="arrivalDateTime" value="${param.arrivalDateTime}"/>
                                    <input type="hidden" name="flightNumber" value="${param.flightNumber}"/>
                                    <input type="hidden" name="arrivalCity" value="${param.arrivalCity}"/>
                                    <input type="hidden" name="departureCity" value="${param.departureCity}"/>

                                    <c:if test="${param.tourId != null}">
                                        <input type="hidden" name="tourId" value="${param.tourId}"/>
                                        <input type="hidden" name="tourName" value="${param.tourName}"/>
                                        <input type="hidden" name="arrivalCountry" value="${param.arrivalCountry}"/>
                                        <input type="hidden" name="departureDate" value="${param.departureDate}"/>
                                        <input type="hidden" name="arrivalDate" value="${param.arrivalDate}"/>
                                        <input type="hidden" name="hotel" value="${param.hotel}"/>
                                        <input type="hidden" name="nutrition" value="${param.nutrition}"/>
                                        <input type="hidden" name="adultsNumber" value="${param.adultsNumber}"/>
                                        <input type="hidden" name="childrenNumber" value="${param.childrenNumber}"/>
                                        <input type="hidden" name="price" value="${param.price}"/>
                                    </c:if>
                        <fmt:message key="guest.attrValue.notAuthorized" var="notAuthorized"/>
                        <input type="hidden" name="notAuthorized" value="${notAuthorized}"/>
                        <form/>
                    </c:if>

                    <label>
                        <input type="submit" value="${choose}">
                    </label>
                </div>
            </c:if>
        </div>

    </div>
</div>
</body>
</html>
