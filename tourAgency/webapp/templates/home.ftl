<#import "components/main-panel.ftl" as mainPanel/>
<#import "components/timestamp.ftl" as timestamp/>
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html>
<head>
    <title class="header">
        <@spring.message "common.title"/>
    </title>
    <@mainPanel.page>
        <#include "../css/home-content-style.css"/>
    </@mainPanel.page>
</head>

<body>
<div class="startMessage">
    <@spring.message "common.message.startTravel"/>
</div>
    <div class="contentHeader">
        <a href="to_tours">
            <@spring.message "common.message.availableTours"/>
        </a>
        <img src="images/logo.png" alt=""/>

    </div>

<@timestamp.page/>
</body>
</html>
<#assign known=Session.SPRING_SECURITY_CONTEXT??>