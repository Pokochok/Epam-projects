<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <%@include file="components/another-panel.jsp"%>
    <style>
        <%@include file="../css/registration-style.css"%>
    </style>
    <fmt:setBundle basename="jsp/ticket-registration"/>
    <title><fmt:message key="message.title.ticketRegistration"/></title>
</head>
<body>

<form class="registrationForm" method="POST" action="controller">

    <div class="ticketContent">
        <h2><fmt:message key="message.title.ticketRegistration"/></h2>
        <hr/>
        <input type="hidden" name="command" value="ticket_register_command"/>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="message.flightNumber"/>
            </div>

            <label>
                <input type="text" name="flightNumber" required pattern="^([1]?\d{1,9}|2000000000)$"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="message.ticketNumber"/>
            </div>

            <label>
                <input type="text" name="ticketNumber" required pattern="^([1]?\d{1,9}|2000000000)$"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="message.departureCity"/>
            </div>

            <label>
                <input type="text" name="departureCity"  pattern="^[a-zA-Z.,_%+-\s]{1,40}$" required/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="message.arrivalCity"/>
            </div>

            <label>
                <input type="text" name="arrivalCity"  pattern="^[a-zA-Z.,_%+-\s]{1,40}$" required/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="message.departureDate"/>
            </div>

            <label>
                <input type="date" name="departureDate" required/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="message.arrivalDate"/>
            </div>

            <label>
                <input type="date" name="arrivalDate" required/>
            </label>
        </div>
        ${errorInvalidDate}

        <br/>
        <fmt:message key="message.submit.registerTicket" var="registerButton"/>
        <label>
            <input type="submit" name="register" value="${registerButton}"/>
        </label>
    </div>
</form>
</body>
<c:import url="components/timestamp.jsp"></c:import>
</html>

