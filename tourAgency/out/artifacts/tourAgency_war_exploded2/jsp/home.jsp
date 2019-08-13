<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<html>
<head>
    <%@include file="components/main-panel.jsp" %>
    <fmt:setBundle basename="jsp/home"/>
    <title class="header"><fmt:message key="common.title"/></title>
    <link href="../css/home-content-style.css" rel="stylesheet" type="text/css">
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

    <div class="time-inf">
        <ctg:time-inf/>
    </div>

    <div class="tours">

    </div>
</form>
</body>
</html>
