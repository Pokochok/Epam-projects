<#macro page>
<!DOCTYPE html>
<html>
<head>
<style>
<%@include file="../../../css/componentsstyle/main-navigation.css"%>
    </style>
</head>
<body>

<div class="navigation">
    <a class="navigationRef" href="to_about_company"><fmt:message
            key="common.ref.page.aboutUs"/> </a>
<a class="navigationRef" href="to_tours"> <fmt:message key="common.ref.page.tours"/> </a>
<a class="navigationRef" href="to_tickets"> <fmt:message key="common.ref.page.flights"/> </a>

<#if userRole == 'CLIENT'>
        <a class="navigationRef" href="to_orders"><fmt:message
                key="client.ref.page.myReservation"/> </a>
</#if>

<#if userRole == 'AGENT' || userRole == 'ADMIN'>
        <a class="navigationRef" href="to_orders"><fmt:message
                key="agentAdmin.ref.page.reservation"/> </a>
</#if>
</div>

</body>
</html>
</#macro>