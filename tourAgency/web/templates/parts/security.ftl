<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    isAdmin=user.getRole() == "ADMIN"
    isClient=user.getRole() == "CLIENT"
    isAgent=user.getRole() == "AGENT"
    isActive = true
    >
<#else>
    <#assign
    name = ""
    isActive = false
    isAdmin=false
    isClient=false
    isAgent=false
    >
</#if>
