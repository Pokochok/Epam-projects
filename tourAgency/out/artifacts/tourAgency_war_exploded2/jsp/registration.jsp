<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="jsp/Registration"/>
<html>
<head>
    <title><fmt:message key="registration.title"/></title>
</head>
<body>
<c:import url="components/another-panel.jsp"/>

<form name="registration" method="POST" action="controller">
    <h2><fmt:message key="registration.message.headline"/></h2>

    <input type="hidden" name="command" value="Registration"/>

    <fmt:message key="registration.message.name"/>
    <label>
        <input type="text" name="name" required pattern="^[^!@#$%^&*().,_\d=|?`~/<>]{1,30}$"/>
    </label>
    <br><br>

    <fmt:message key="registration.message.surname"/>
    <label>
        <input type="text" name="surname" required pattern="^[^!@#$%^&*().,_\d=|?`~/<>]{1,30}$"/>
    </label>
    <br><br>

    <fmt:message key="registration.message.email"/>
    <label>
        <input type="text" name="email" minlength="6" maxlength="100"  pattern="^[a-zA-Z0-9.,_%+-]+@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}$"
               placeholder=<fmt:message key="registration.placeholder.email"/> />
    </label>
    <br> ${errorEmailExistsMessage}
    <br><br>

    <fmt:message key="registration.message.phoneNumber"/>
    <label>
        <input type="text" name="phoneNumber" pattern="^[+]\d{10,12}$" placeholder=<fmt:message key="registration.placeholder.phoneNumber" /> />
    </label>
    <br> ${errorPhoneNumberExistsMessage}
    <br><br>

    <fmt:message key="registration.message.login"/>
    <label>
        <input type="text" name="login" minlength="4" maxlength="50" required
               placeholder=<fmt:message key="registration.placeholder.login"/> />
    </label>
    <br> ${errorLoginExistsMessage}
    <br><br>

    <fmt:message key="registration.message.password"/>
    <label>
        <input type="password" name="password" minlength="6" maxlength="50" required
               placeholder=<fmt:message key="registration.placeholder.password"/> />
    </label>
    <br><br>

    <label>
        <input type="radio" name="userRole" value="client" checked><fmt:message key="registration.radio.client"/> <br>
        <input type="radio" name="userRole" value="agent" > <fmt:message key="registration.radio.agent"/> <br>
    </label>

    <br/>
    <fmt:message key="registration.submit.register" var="registerButton"/>
    <input type="submit" name="register" value=${registerButton} />
</form>
</body>
</html>
