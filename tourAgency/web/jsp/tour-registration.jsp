<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <%@include file="components/another-panel.jsp" %>
    <style>
        <%@include file="../css/registration-style.css"%>
    </style>
    <fmt:setBundle basename="jsp/tour-registration"/>
    <title><fmt:message key="message.title"/></title>
</head>
<body>

<form class="registrationForm" method="POST" action="controller">


    <div class="tourContent">
        <h2><fmt:message key="message.registration"/></h2>
        <hr/>
        <input type="hidden" name="command" value="tour_register_command"/>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="message.tourName"/>
            </div>

            <label>
                <input type="text" name="tourName" required maxlength="40" minlength="1"
                       pattern="^[\sa-zA-Z.,_%+-]{1,40}$"/>
            </label>
        </div>
        ${errorTourNameExists}

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

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="message.departureCity"/>
            </div>

            <label>
                <input type="text" name="departureCity" required pattern="^[\sa-zA-Z.,_%+-]{1,40}$""/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="message.arrivalCity"/>
            </div>

            <label>
                <input type="text" name="arrivalCity" required pattern="^[\sa-zA-Z.,_%+-]{1,40}$"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="message.arrivalCountry"/>
            </div>

            <label>
                <input type="text" name="arrivalCountry" required pattern="^[\sa-zA-Z.,_%+-]{1,40}$"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="message.hotel"/>
            </div>

            <label>
                <input type="text" name="hotel" required pattern="^[\sa-zA-Z.,_%+-]{1,40}$"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="message.nutrition"/>
            </div>

            <label>
                <input type="text" name="nutrition" required pattern="^[A-Z]{2,3}[+]?$"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="message.numberOfAdults"/>
            </div>

            <label>
                <input type="text" name="adultsNumber" required pattern="^([0-4]?\d|50)$" min="0" max="50"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="message.numberOfChildren"/>
            </div>

            <label>
                <input type="text" name="childrenNumber" required pattern="^([0-4]?\d|50)$" min="0" max="50"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="message.price"/>
            </div>

            <label>
                <input type="text" name="price" required pattern="^([1]?\d?\d?\d?\d?\d([.]\d\d)?|200000([.]\d\d)?)$" min="1" max="200000"/>
            </label>
        </div>

        <div class="contentItem">
            <label class="radio-button">
                <input type="radio" name="isAvailable" value="AVAILABLE" checked><fmt:message
                    key="message.radio.available"/> <br>
                <input type="radio" name="isAvailable" value="NOT_AVAILABLE"><fmt:message
                    key="message.radio.notAvailable"/> <br>
            </label>
        </div>

        <br/>
        <fmt:message key="message.submit.register" var="registerButton"/>
        <label>
            <input type="submit" name="register" value="${registerButton}"/>
        </label>
    </div>
</form>
</body>
</html>

