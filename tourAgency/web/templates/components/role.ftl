<#macro page>
    <#import "/spring.ftl" as spring/>
    <#import "security.ftl" as springSecurity/>
    <#if (springSecurity.userName)??>
        <li><a class="userName" href="to_user_profile">
            ${(springSecurity.userName!"")}
            ${(springSecurity.userSurname!"")}
        </a></li>
    </#if>
        <#if (springSecurity.isClient)!false>
            <li class="client role">
                <span><@spring.message "client.message.role"/></span>
            </li>
        <#elseif (springSecurity.isAgent)!false>
            <li class="agent role">
                <span><@spring.message "agent.message.role"/></span>
            </li>
        <#elseif (springSecurity.isAdmin)!false>
            <li class="admin role">
                <span><@spring.message "admin.message.role"/></span>
            </li>
        <#else >
            <li class="guest role">
                <span><@spring.message "guest.message.role"/></span>
            </li>
        </#if>

        <#if !((springSecurity.isActive)!false)>
            <li class="login"><a class="navigationRef" href="to_login"><i class="fa fa-sign-in"></i>
                <@spring.message "guest.ref.page.login"/>
            </a></li>
        </#if>
        <#if (springSecurity.isActive)!false>
            <li><a class="navigationRef" href="logout"><i class="fa fa-sign-out"></i>
                <@spring.message "common.ref.logout"/>
            </a></li>
        </#if>
</#macro>

