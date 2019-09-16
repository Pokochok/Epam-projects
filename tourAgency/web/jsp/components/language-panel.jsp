<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="jsp/home"/>
<!DOCTYPE html>
<html>
<head>
    <style>
        <%@include file="../../css/componentsstyle/language-panel.css"%>
    </style>
</head>
<body>

<form class="language">
    <fmt:message key="common.message.language"/>
    <label for="${language}">
        <select id="language" class="selection-handle" name="language" onchange="submit()">
            <option value="en" <c:if test="${language == 'en'}"> selected </c:if> ><fmt:message
                    key="common.message.english"/></option>
            <option value="ru" <c:if test="${language == 'ru'}"> selected </c:if> ><fmt:message
                    key="common.message.russian"/></option>
            <option value="pl" <c:if test="${language == 'pl'}"> selected </c:if> ><fmt:message
                    key="common.message.polski"/></option>
        </select>
    </label>
</form>
</body>
</html>
