<#macro page>
<!DOCTYPE html>
<html>
<head>
    <%@include file="components/main-panel.jsp" %>
    <fmt:setBundle basename="jsp/home"/>
    <title class="header"><fmt:message key="common.title"/></title>
    <style>
        <%@include file="../css/home-content-style.css"%>
    </style>
</head>
<#include "../home.ftl">
</html>
</#macro>