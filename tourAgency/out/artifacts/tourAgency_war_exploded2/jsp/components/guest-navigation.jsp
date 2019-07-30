<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="jsp/Home"/>
<html>
<head>
    <link href="../../css/componentsstyle/guest-navigation.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="navigation">
    <a class="navigationRef" href="controller?command=to_about_company"><fmt:message key="common.ref.page.aboutUs"/> </a>
    <a class="navigationRef" href="controller?command=to_tour_search"><fmt:message key="common.ref.page.tourSearch"/> </a>
    <a class="navigationRef" href="controller?command=to_tours"> <fmt:message key="common.ref.page.tours"/> </a>
</div>

</body>
</html>
