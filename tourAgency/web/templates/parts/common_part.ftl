<#macro page>
<!DOCTYPE html>
<html>
<head>
    <#include "components/main-panel.ftl" />
    <fmt:setBundle basename="jsp/home"/>
    <title class="header"><fmt:message key="common.title"/></title>
    <style>
        <#include "../css/home-content-style.css"/>
    </style>
</head>
<body>
<#nested/>
</body>
</html>
</#macro>