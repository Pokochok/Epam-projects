<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="jsp/AboutCompany"/>
<html>
<head>
    <title><fmt:message key="aboutCompany.title"/> </title>
</head>

<body>
<jsp:include page="components/main-panel.jsp"/>

<form style="width: 20cm">
    <h4><fmt:message key="aboutCompany.message.headline"/> </h4>
    <fmt:message key="aboutCompany.message.text"/>
</form>
</body>
</html>
