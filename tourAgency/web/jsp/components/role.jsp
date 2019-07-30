<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="userName"
       value="${not empty param.userName ? param.userName : userName}"
       scope="session"/>
<c:set var="userSurname"
       value="${not empty param.userSurname ? param.userSurname : userSurname}"
       scope="session"/>
<c:set var="userPhoneNumber"
       value="${not empty param.userPhoneNumber ? param.userPhoneNumber : userPhoneNumber}"
       scope="session"/>
<c:set var="userEmail"
       value="${not empty param.userEmail ? param.userEmail : userEmail}"
       scope="session"/>
<c:set var="userLogin"
       value="${not empty param.userLogin ? param.userLogin : userLogin}"
       scope="session"/>
<c:set var="userRole"
       value="${not empty param.userRole ? param.userRole : not empty userRole ? userRole : 'GUEST'}"
       scope="session"/>

<fmt:setBundle basename="jsp/Home"/>
<html>
<head>
    <link href="../../css/componentsstyle/role.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="role">
    <div class="userName"> <a class="userName" href="controller?command=to_user_profile"> ${userName} ${userSurname} </a></div>
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
