<#macro page>
    <#import "/spring.ftl" as spring/>
    <#import "main-panel-action.ftl" as mainPanelAction>
        <style>
            <#include "../../css/componentsstyle/main-panel-container.css"/>
            <#include "../../css/componentsstyle/main-panel-action-style.css"/>
            <#include "../../css/componentsstyle/main-navigation.css"/>
            <#include "../../css/componentsstyle/language-panel.css"/>
            <#include "../../css/componentsstyle/role.css"/>
            <#nested/>
        </style>
    <header class="container">
        <div class="logo">
            <a href="back_to_main"> <img src="images/logo.png" alt=""/></a>
        </div>
        <@mainPanelAction.page/>
    </header>
</#macro>