<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<html>
<head>
    <%@include file="components/main-panel.jsp" %>
    <fmt:setBundle basename="jsp/home"/>
    <title class="header"><fmt:message key="common.title"/></title>
    <style>
        <%@include file="../css/home-content-style.css"%>
    </style>
</head>
<body>
<div class="startMessage">
    <fmt:message key="common.message.startTravel"/>
</div>

<form class="contentForm">
    <div class="time-inf">
        <ctg:time-inf/>
    </div>

    <div class="contentHeader">
        <a href="controller?command=to_tours"> <fmt:message key="common.message.availableTours"/> </a>
    </div>

</form>
</body>
<c:import url="components/timestamp.jsp"></c:import>
</html>
