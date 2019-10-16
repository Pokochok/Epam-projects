<#macro page>
    <#import "security.ftl" as springSecurity/>
    <#import "/spring.ftl" as spring/>
    <div class="navigation">
        <a class="navigationRef" href="to_about_company">
            <@spring.message "common.ref.page.aboutUs"/>
        </a>
        <a class="navigationRef" href="to_tours">
            <@spring.message "common.ref.page.tours"/>
        </a>
        <a class="navigationRef" href="to_tickets">
            <@spring.message "common.ref.page.flights"/>
        </a>

        <#if springSecurity.isClient>
            <a class="navigationRef" href="to_orders">
                <@spring.message "client.ref.page.myReservation"/>
            </a>
        </#if>

        <#if springSecurity.isAgent || springSecurity.isAdmin>
            <a class="navigationRef" href="to_orders">
                <@spring.message "agentAdmin.ref.page.reservation"/>
            </a>
        </#if>
    </div>

</#macro>