<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="jsp/TourRegistration"/>
<html>
<head>
    <link href="../css/tour-registration-style.css" rel="stylesheet" type="text/css">
    <title><fmt:message key="message.title"/></title>
</head>
<body>
<c:import url="components/another-panel.jsp"/>

<form class="tourRegistrationForm" method="POST" action="controller">


    <div class="tourContent">
        <h2><fmt:message key="message.registration"/></h2>
        <hr/>
        <input type="hidden" name="command" value="tour_register_command"/>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="message.tourName"/>
            </div>

            <label>
                <input type="text" name="tourName" required maxlength="40" minlength="1"/>
            </label>
        </div>
        <br>
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
                <input type="text" name="departureCity" required pattern="^[A-zА-яЁё]+$"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="message.arrivalCity"/>
            </div>

            <label>
                <input type="text" name="arrivalCity" required pattern="^[A-zА-яЁё]+$"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="message.arrivalCountry"/>
            </div>

            <label>
                <input type="text" name="arrivalCountry" required pattern="^[A-zА-яЁё]+$"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="message.hotel"/>
            </div>

            <label>
                <input type="text" name="hotel" required />
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="message.nutrition"/>
            </div>

            <label>
                <input type="text" name="nutrition" required />
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="message.numberOfAdults"/>
            </div>

            <label>
                <input type="text" name="numberOfAdults" required pattern="^\d+$" min="0" max="100"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="message.numberOfChildren"/>
            </div>

            <label>
                <input type="text" name="numberOfChildren" required pattern="^\d+$" min="0" max="100"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="message.price"/>
            </div>

            <label>
                <input type="text" name="price" required pattern="^\d+$" min="1" max="200000"/>
            </label>
        </div>

        <div class="contentItem">
            <label>
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

