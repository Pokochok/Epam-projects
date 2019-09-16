<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<head>
    <%@include file="components/main-panel.jsp" %>
    <style>
            <%@include file="../css/booking-style.css"%>
    </style>
    <fmt:setBundle basename="jsp/about-company"/>
    <title><fmt:message key="aboutCompany.title"/></title>
</head>

<body>

<form class="contentForm">
    <div class="contentContainer">
        <div class="contentHeader">
            <h3><fmt:message key="aboutCompany.message.headline"/></h3>
        </div>
        <div class="companyInf">
            <fmt:message key="aboutCompany.message.text"/>
        </div>
    </div>
</form>
</body>
<c:import url="components/timestamp.jsp"></c:import>
</html>
