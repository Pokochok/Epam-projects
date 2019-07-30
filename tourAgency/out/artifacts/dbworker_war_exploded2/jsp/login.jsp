<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="jsp/Login"/>

<html>
<head>
    <link href="../css/login-style.css" rel="stylesheet" type="text/css">
    <title><fmt:message key="login.title"/></title>
</head>
<body>
<c:import url="components/another-panel.jsp"/>

<form class="loginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="Login"/>

    <div class="userContent">
        <h2 class="header"><fmt:message key="login.message.authorization"/></h2>
        <hr/>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="login.message.login"/>
            </div>
            <label>
                <input type="text" name="login"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <fmt:message key="login.message.password"/>
            </div>
            <label>
                <input type="password" name="password"/>
            </label>
        </div>

        <br> ${errorLoginPassMessage}
        <br> ${wrongAction}
        <br> ${nullPage}

        <fmt:message key="login.submit.login" var="logInButton"/>
        <label>
            <input type="submit" name="logIn" value="${logInButton}"/>
        </label>
        <br/>
        <br/>
        <a href="controller?command=to_registration"> <fmt:message key="login.ref.page.register"/> </a>
    </div>
</form>
</body>
</html>
