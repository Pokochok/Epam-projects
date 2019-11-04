<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    userName = (user.getRealUserName())!""
    userSurname = (user.getUserSurname())!""
    userLogin = (user.getUsername())!""
    userEmail = (user.getUserEmail())!""
    userPhoneNumber = (user.getUserPhoneNumber())!""
    role = (user.getUserRole())!""
    userId = (user.getUserId())!0
    isAdmin= role == "ADMIN"
    isClient= role == "CLIENT"
    isAgent= role == "AGENT"
    isAnonymous=false
    isActive = true
    >
<#else>
    <#assign
    name = ""
    isActive = false
    isAdmin=false
    isClient=false
    isAgent=false
    isAnonymous=true
    >
</#if>
