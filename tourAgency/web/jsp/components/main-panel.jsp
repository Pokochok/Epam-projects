<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="jsp/Home"/>
<html>
<head>
    <link href="../../css/componentsstyle/main-panel-container.css" rel="stylesheet" type="text/css">
</head>
<body>
<header  class="container">
    <div class="logo">
        <img src="../../images/logo.png" alt="">
    </div>
    <c:import url="components/home-navigation.jsp"/>
</header>
</body>
</html>
