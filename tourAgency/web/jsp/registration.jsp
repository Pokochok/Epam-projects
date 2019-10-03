<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <%@include file="components/another-panel.jsp" %>
    <style>
        <%@include file="../css/registration-style.css"%>
    </style>
    <fmt:setBundle basename="jsp/registration"/>
    <title><fmt:message key="registration.title"/></title>
</head>
<body>

<form name="registrationForm" method="POST" action="registration">
    <div class="userContent">
        <h2><fmt:message key="registration.message.headline"/></h2>
        <hr/>

        <div class="contentItem">
            <fmt:message key="registration.message.name"/>
            <label>
                <input type="text" name="name" required pattern="^[^!@#$%^&*().,_\d=|?`~/<>']{1,30}$"/>
            </label>
        </div>

        <div class="contentItem">
            <fmt:message key="registration.message.surname"/>
            <label>
                <input type="text" name="surname" required pattern="^[^!@#$%^&*().,_\d=|?`'~/<>]{1,30}$"/>
            </label>
        </div>

        <div class="contentItem">
            <fmt:message key="registration.message.email"/>
            <label>
                <input type="text" name="email" minlength="6" maxlength="100"
                       pattern="^[a-zA-Z0-9.,_%+-]+@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}$"
                       placeholder="<fmt:message key="registration.placeholder.email" />" />
            </label>
            ${errorEmailExistsMessage}
        </div>

        <div class="contentItem">
            <fmt:message key="registration.message.phoneNumber"/>
            <label>
                <input type="text" name="phoneNumber" pattern="^[+]\d{10,12}$"
                       placeholder="<fmt:message key="registration.placeholder.phoneNumber" />" />
            </label>
            ${errorPhoneNumberExistsMessage}
        </div>

        <div class="contentItem">
            <fmt:message key="registration.message.login"/>
            <label>
                <input type="text" name="login" minlength="4" maxlength="50" required
                       placeholder=
                        <fmt:message key="registration.placeholder.login"/>/>
            </label>
            ${errorLoginExistsMessage}
        </div>

        <div class="contentItem">
            <fmt:message key="registration.message.password"/>
            <label>
                <input type="password" name="password" minlength="6" maxlength="50" required
                       placeholder="<fmt:message key="registration.placeholder.password"/>"/>
            </label>
        </div>

        <div class="contentItem">
            <label class="radio-button">
                <input type="radio" name="userRole" value="client" checked><fmt:message
                    key="registration.radio.client"/>
                <br>
                <input type="radio" name="userRole" value="agent"> <fmt:message key="registration.radio.agent"/> <br>
            </label>
        </div>

        <br/>
        <fmt:message key="registration.submit.register" var="registerButton"/>
        <input type="submit" name="register" value="${registerButton}"/>
    </div>
</form>
</body>
<c:import url="components/timestamp.jsp"></c:import>
</html>
