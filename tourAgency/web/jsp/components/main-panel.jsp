<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="jsp/home"/>
<html>
<head>
    <link href="../../css/componentsstyle/main-panel-container.css" rel="stylesheet" type="text/css">
</head>
<body>
<header  class="container">
    <div class="logo">
        <a href="controller?command=back_to_main"> <img src="../../images/logo.png" alt=""></a>
    </div>
    <c:import url="components/main-panel-action.jsp"/>
</header>
</body>
</html>
