<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="jsp/home"/>
<html>
<head>
    <link href="../../css/componentsstyle/main-panel-container.css" rel="stylesheet" type="text/css">
</head>
<body>
<form class="container">
    <div class="logo">
        <a href="controller?command=back_to_main"> <img src="../../images/logo.png" alt=""></a>
    </div>
    <jsp:include page="components/main-navigation.jsp"/>
    <div id="auxiliary">    </div>
</form>
</body>
</html>
