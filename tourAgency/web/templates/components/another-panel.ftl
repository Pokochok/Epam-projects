<#macro page>
    <#import "main-navigation.ftl" as mainNavigation>
    <#import "/spring.ftl" as spring/>
    <style>
        <#nested>
        <#include "../../css/componentsstyle/main-navigation.css"/>
        <#include "../../css/componentsstyle/main-panel-container.css"/>
    </style>
    <header class="container">
        <div class="logo">
            <a href="back_to_main"> <img src="/images/logo.png" alt=""/></a>
        </div>
        <@mainNavigation.page/>
    </header>
</#macro>