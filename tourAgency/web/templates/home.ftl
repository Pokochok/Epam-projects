<!DOCTYPE html>
<html>
<head>
<#import "parts/common_part.ftl" as common>
<title class="header"><fmt:message key="common.title"/></title>
<style>
<%@include file="../../css/home-content-style.css"%>
    </style>
</head>
<body>
<@common>
<div class="startMessage">
    <fmt:message key="common.message.startTravel"/>
</div>

<form class="contentForm">
    <div class="time-inf">
<#--        <ctg:time-inf/>-->
    </div>

<div class="contentHeader">
        <a href="to_tours"> <fmt:message key="common.message.availableTours"/> </a>
</div>

</form>
</@common>
</body>
<#--<#import "components/timestamp.jsp"></c:import>-->
</html>
<#assign known=Session.SPRING_SECURITY_CONTEXT??>