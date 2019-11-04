<#macro page>
    <#import "main-navigation.ftl" as mainNavigation>
    <#import "role.ftl" as role>
    <#import "language-panel.ftl" as languagePanel>

    <#import "/spring.ftl" as spring/>
    <#import "main-panel-action.ftl" as mainPanelAction>
<script src="uui/js/lib/components/bootstrap3-typeahead.min.js"></script>
<script src="uui/js/uui/uui-autocomplete.min.js"></script>
<script src="uui/js/lib/jquery-1.12.0.min.js"></script>
<link rel="stylesheet" href="uui/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="uui/css/uui-all.css" />
<script src="uui/bootstrap/js/bootstrap.min.js"></script>
<script src="uui/js/uui-core.min.js" type="text/javascript"></script>
<link rel="stylesheet/less" type="text/css" href="uui/less/custom-styles.less" />
<script src="uui/js/lib/less.js" type="text/javascript"></script>
<link rel="stylesheet" href="uui/css/lib/components/jquery.mCustomScrollbar.min.css" />
<script src="uui/js/lib/components/jquery.mCustomScrollbar.concat.min.js"></script>
<link rel = "stylesheet" href = "uui/fonts/font-awesome/css/font-awesome.min.css" />
<style>
    <#include "../../css/componentsstyle/language-panel.css"/>
    <#include "../../css/componentsstyle/role.css"/>
    <#nested/>
</style>
<header>
<div class="row uui-header navigation-header green" >
        <nav>
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
        <a href="back_to_main" class="responsive-brand-logo">
            <span class="arrow fa fa-angle-left"></span>
            <span class="logo">
                <img src="uui/images/ic_logo_UUi.svg" alt=""/>
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
<a href="back_to_main" class="brand-logo">
    <span class="logo">
        <img src="uui/images/ic_logo_UUi.svg" alt="" />
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