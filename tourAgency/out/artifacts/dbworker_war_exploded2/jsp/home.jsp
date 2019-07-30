<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="jsp/Home"/>

<html>
<head>
    <link href="../css/home-content-style.css" rel="stylesheet" type="text/css">
    <title class="header"><fmt:message key="common.title"/></title>
    <c:import url="components/main-panel.jsp"/>
</head>
<body>
<div class="startMessage">
    <fmt:message key="common.message.startTravel"/>
</div>

<form class="contentForm">
    <div class="contentHeader">
        <fmt:message key="common.message.availableTours"/>
        <hr/>
    </div>

    <div class="tours">
<%--        <c:forEach begin="${startIndexOfTours}" end="${startIndexOfTours + 8 - 1}" var="tour"--%>
<%--                   items="tourList">--%>
<%--        </c:forEach>--%>

    </div>
</form>
<%--добавить передачу команды по POST--%>
</body>
</html>
