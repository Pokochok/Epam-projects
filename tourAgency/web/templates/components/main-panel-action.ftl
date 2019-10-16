<#macro page>
    <#import "main-navigation.ftl" as mainNavigation>
    <#import "role.ftl" as role>
    <#import "language-panel.ftl" as languagePanel>
    <@mainNavigation.page/>
    <@role.page/>
    <@languagePanel.page/>
</#macro>