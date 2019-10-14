<#macro page>
<!DOCTYPE html>
<html>
<head>
<style>
<%@include file="../../../css/componentsstyle/role.css"%>
    </style>
</head>
<body>

<div class="role">
    <a class="userName" href="to_user_profile">
        <div>${userName}</div>
<div>${userSurname}</div>
</a>
<div class="client"><#if userRole == 'CLIENT'> <fmt:message key="client.message.role"/> </#if></div>
<div class="agent"><#if userRole == 'AGENT'> <fmt:message key="agent.message.role"/> </#if></div>
<div class="admin"><#if userRole == 'ADMIN'> <fmt:message key="admin.message.role"/> </#if></div>
<div class="guest"><#if userRole == 'GUEST'> <fmt:message key="guest.message.role"/> </#if></div>

<#if userRole == 'GUEST'>
        <a class="navigationRef" href="to_login"><fmt:message key="guest.ref.page.login"/> </a>
</#if>
<#if userRole != 'GUEST'>
        <a class="navigationRef" href="logout"> <fmt:message key="common.ref.logout"/> </a>
</#if>
</div>

</body>
</html>
</#macro>

