<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="userId" value="${userId}" scope="session"/>
<c:set var="userName" value="${userName}" scope="session"/>
<c:set var="userSurname" value="${userSurname}" scope="session"/>
<c:set var="userPhoneNumber" value="${userPhoneNumber}" scope="session"/>
<c:set var="userEmail" value="${userEmail}" scope="session"/>
<c:set var="userLogin" value="${userLogin}" scope="session"/>
<c:set var="userRole" value="${not empty userRole ? userRole : 'GUEST'}" scope="session"/>

<fmt:setBundle basename="jsp/home"/>
<html>
<head>
    <link href="../../css/componentsstyle/role.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="role">
    <a class="userName" href="controller?command=to_user_profile">
        <div>${userName}</div>
        <div>${userSurname}</div>
    </a>
    <div class="client"><c:if test="${userRole == 'CLIENT'}"> <fmt:message key="client.message.role"/> </c:if></div>
    <div class="agent"><c:if test="${userRole == 'AGENT'}"> <fmt:message key="agent.message.role"/> </c:if></div>
    <div class="admin"><c:if test="${userRole == 'ADMIN'}"> <fmt:message key="admin.message.role"/> </c:if></div>
    <div class="guest"><c:if test="${userRole == 'GUEST'}"> <fmt:message key="guest.message.role"/> </c:if></div>

    <c:if test="${userRole == 'GUEST'}">
        <a class="navigationRef" href="controller?command=to_login"><fmt:message key="guest.ref.page.login"/> </a>
    </c:if>
    <c:if test="${userRole != 'GUEST'}">
        <a class="navigationRef" href="controller?command=Logout"> <fmt:message key="common.ref.logout"/> </a>
    </c:if>
</div>


</body>
</html>
