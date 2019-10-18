<#macro page>
    <#import "/spring.ftl" as spring/>
    <#import "security.ftl" as springSecurity/>
    <div class="role">
        <a class="userName" href="to_user_profile">
            <p> ${(springSecurity.userName!"")}</p>
            <p>${(springSecurity.userSurname!"")}</p>
        </a>
        <#if (springSecurity.isClient)!false>
            <div class="client">
                <@spring.message "client.message.role"/>
            </div>
        <#elseif (springSecurity.isAgent)!false>
            <div class="agent">
                <@spring.message "agent.message.role"/>
            </div>
        <#elseif (springSecurity.isAdmin)!false>
            <div class="admin">
                <@spring.message "admin.message.role"/>
            </div>
        <#else >
            <div class="guest">
                <@spring.message "guest.message.role"/>
            </div>
        </#if>

        <#if !((springSecurity.isActive)!false)>
            <a class="navigationRef" href="to_login">
                <@spring.message "guest.ref.page.login"/>
            </a>
        </#if>
        <#if (springSecurity.isActive)!false>
            <a class="navigationRef" href="logout">
                <@spring.message "common.ref.logout"/>
            </a>
        </#if>
    </div>
</#macro>

