<#macro page>
<!DOCTYPE html>
<html>
<head>
<style>
<%@include file="../../../css/componentsstyle/main-panel-container.css"%>
    </style>
</head>
<body>
<header  class="container">
    <div class="logo">
        <a href="back_to_main"> <img src="images/logo.png" alt=""/></a>
</div>
<#include "components/main-panel-action.ftl"/>
</header>
</body>
</html>
</#macro>