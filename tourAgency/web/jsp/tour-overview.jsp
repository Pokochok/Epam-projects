<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="jsp/TourOverview"/>
<html>
<head>
    <link href="../css/tour-overview-style.css" rel="stylesheet" type="text/css">
    <title><c:out value="${param.tourName}"/></title>
    <jsp:include page="components/main-panel.jsp"/>
</head>
<body>
<form class="contentForm">
    <div class="tourName">
        <c:out value="${param.tourName}"/>
    </div>

    <div class="contentItem">
        <div class="itemMsg">
            <fmt:message key="common.message.arrivalCountry"/>
        </div>
        <c:out value="${param.arrivalCountry}"/>
    </div>

    <div class="contentItem">
        <div class="itemMsg">
            <fmt:message key="common.message.arrivalCity"/>
        </div>
        <c:out value="${param.arrivalCity}"/>
    </div>

    <div class="contentItem">
        <div class="itemMsg">
            <fmt:message key="common.message.departureCity"/>
        </div>
        <c:out value="${param.departureCity}"/>
    </div>

    <div class="contentItem">
        <div class="itemMsg">
            <fmt:message key="common.message.departureDate"/>
        </div>
        <c:out value="${param.departureDate}"/>
    </div>

    <div class="contentItem">
        <div class="itemMsg">
            <fmt:message key="common.message.arrivalDate"/>
        </div>
        <c:out value="${param.arrivalDate}"/>
    </div>

    <div class="contentItem">
        <div class="itemMsg">
            <fmt:message key="common.message.hotel"/>
        </div>
        <c:out value="${param.hotel}"/>
    </div>

    <div class="contentItem">
        <div class="itemMsg">
            <fmt:message key="common.message.nutrition"/>
        </div>
        <c:out value="${param.nutrition}"/>
    </div>

    <div class="contentItem">
        <div class="itemMsg">
            <fmt:message key="common.message.numberOfAdults"/>
        </div>
        <c:out value="${param.adultsNumber}"/>
    </div>

    <div class="contentItem">
        <div class="itemMsg">
            <fmt:message key="common.message.numberOfChildren"/>
        </div>
        <c:out value="${param.childrenNumber}"/>
    </div>

    <div class="contentItem">
        <div class="itemMsg">
            <fmt:message key="common.message.price"/>
        </div>
        <c:out value="${param.price}"/>
    </div>
</form>
</body>
</html>
