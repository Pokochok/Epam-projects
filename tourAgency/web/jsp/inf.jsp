<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
    <%@include file="components/another-panel.jsp"%>
    <style>
        <%@include file="../css/login-style.css"%>
    </style>
    <fmt:setBundle basename="jsp/home"/>
    <title><fmt:message key="common.companyName"/></title>
</head>
<body>

<form class="loginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="back_to_main"/>

    <div class="userContent">
        <h2 class="header"><fmt:message key="common.message.notification"/></h2>
        <hr/>
        ${resultInformation}
        <br>
        <br>
        <label>
            <fmt:message key="common.message.backToMain" var="toMain"/>
            <input type="submit" value="${toMain}"/>
        </label>

    </div>
</form>

</body>
<c:import url="components/timestamp.jsp"></c:import>
</html>
