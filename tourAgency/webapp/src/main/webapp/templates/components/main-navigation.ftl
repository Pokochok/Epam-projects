<#macro page>
    <#import "security.ftl" as springSecurity/>
    <#import "/spring.ftl" as spring/>
        <li><a class="navigationRef" href="to_about_company">
            <span><@spring.message "common.ref.page.aboutUs"/></span>
        </a></li>
        <li><a class="navigationRef" href="to_tours">
            <span><@spring.message "common.ref.page.tours"/></span>
        </a></li>
        <li><a class="navigationRef" href="to_tickets">
            <span><@spring.message "common.ref.page.flights"/></span>
        </a></li>

        <#if (springSecurity.isClient)!false>
            <li><a class="navigationRef" href="to_orders">
                <span><@spring.message "client.ref.page.myReservation"/></span>
            </a></li>
        </#if>

        <#if (((springSecurity.isAgent)!false) || ((springSecurity.isAdmin)!false))>
            <li><a class="navigationRef" href="to_orders">
                <span><@spring.message "agentAdmin.ref.page.reservation"/></span>
            </a></li>
        </#if>

</#macro>