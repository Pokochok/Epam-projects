<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="jsp/home"/>
<html>
<head>
    <link href="../../css/componentsstyle/main-navigation.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="navigation">
    <a class="navigationRef" href="controller?command=to_about_company"><fmt:message
            key="common.ref.page.aboutUs"/> </a>
    <a class="navigationRef" href="controller?command=to_tours"> <fmt:message key="common.ref.page.tours"/> </a>
    <a class="navigationRef" href="controller?command=to_tickets"> <fmt:message key="common.ref.page.flights"/> </a>

    <c:if test="${sessionScope.userRole == 'CLIENT'}">
        <a class="navigationRef" href="controller?command=to_orders"><fmt:message
                key="client.ref.page.myReservation"/> </a>
    </c:if>

    <c:if test="${sessionScope.userRole == 'AGENT' || sessionScope.userRole == 'ADMIN'}">
        <a class="navigationRef" href="controller?command=to_orders"><fmt:message
                key="agentAdmin.ref.page.reservation"/> </a>
    </c:if>
</div>

</body>
</html>
