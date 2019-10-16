<#import "components/main-panel.ftl" as mainPanel/>
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

<form class="contentForm">
    <div class="time-inf">
        <#--        <ctg:time-inf/>-->
    </div>

    <div class="contentHeader">
        <a href="to_tours">
            <@spring.message "common.message.availableTours"/>
        </a>
    </div>

</form>
</body>
<#import "components/timestamp.ftl" as timestamp/>
<@timestamp.page/>
</html>
<#assign known=Session.SPRING_SECURITY_CONTEXT??>