<#macro page>
    <#import "main-navigation.ftl" as mainNavigation>
    <#import "role.ftl" as role>
    <#import "language-panel.ftl" as languagePanel>

    <#import "/spring.ftl" as spring/>
    <#import "main-panel-action.ftl" as mainPanelAction>
<style>
                <#include "../../css/componentsstyle/main-panel-container.css"/>
                <#include "../../css/componentsstyle/main-panel-action-style.css"/>
                <#include "../../css/componentsstyle/main-navigation.css"/>
    <#include "../../css/componentsstyle/language-panel.css"/>
    <#include "../../css/componentsstyle/role.css"/>
    <#include "/uui/bootstrap/css/bootstrap.min.css"/>
    <#include "/uui/fonts/font-awesome/css/font-awesome.min.css"/>
    <#include "/uui/css/uui-all.css"/>
    <#include "/uui/css/lib/components/jquery.mCustomScrollbar.min.css"/>
    <#nested/>
</style>
<#--<header class="container">-->
<#--        <div class="logo">-->
<#--            <a href="back_to_main"> <img src="images/logo.png" alt=""/></a>-->
<#--</div>-->
<#--    --><#--        <@mainPanelAction.page/>-->-->
<#--</header>-->
<header>
<div class="row uui-header navigation-header green" >
        <nav>
<!--Responsive html-layout-->
<div class="uui-responsive-header">
    <div class="responsive-header">
        <div class="responsive-toggle-box">
            <span></span>
            <span></span>
            <span></span>
        </div>
        <div class="responsive-hide-menu">
            <span></span>
            <span></span>
        </div>
        <a href="#" class="responsive-brand-logo">
            <span class="arrow fa fa-angle-left"></span>
            <span class="logo">
                <img src="/images/logo.png" alt="" />
            </span>
        <span class="title">EUROTOUR</span>
        </a>
    </div>
    <div class="responsive-menu">
        <div class="menu-wrapper">
            <div class="menu-scroll">
                <ul class="nav navbar-nav">
                        <@mainNavigation.page/>
                </ul>
            </div>
        </div>
    </div>
</div>
<!---->
<a href="#" class="brand-logo">
    <span class="logo">
        <img src="/images/logo.png" alt="" />
    </span>
    EUROTOUR
</a>
<#--    <div class="navbar-nav n"-->
        <ul class="uui-navigation nav navbar-nav navbar-right">
           <@role.page/>
            <@languagePanel.page/>
        </ul>
        <ul class="uui-navigation nav navbar-nav">
           <@mainNavigation.page/>
        </ul>

</nav>
</div>
</header>
</#macro>