<#macro page>
    <#import "main-navigation.ftl" as mainNavigation>
    <#import "/spring.ftl" as spring/>
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
    <style>
        <#nested>
    <#include "/uui/bootstrap/css/bootstrap.min.css"/>
    <#include "/uui/css/uui-all.css"/>
    <#include "/uui/css/lib/components/jquery.mCustomScrollbar.min.css"/>
    </style>
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
<a href="/tour-agency/back_to_main" class="brand-logo">
    <span class="logo">
        <img src="/images/logo.png" alt="" />
    </span>
    EUROTOUR
</a>
    <#--    <div class="navbar-nav n"-->

<ul class="uui-navigation nav navbar-nav">
           <@mainNavigation.page/>
</ul>

</nav>
</div>
</header>
</#macro>