<#import "components/main-panel.ftl" as mainPanel/>
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html>
<head>
<!-- jQuery Core -->
<script src="/uui/js/lib/jquery-1.12.0.min.js"></script>

<!-- Bootstrap Core -->
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<script src="uui/bootstrap/js/bootstrap.min.js"></script>

<!-- EPAM UUI JavaScript Core -->
<script src="/uui/js/uui-core.min.js" type="text/javascript"></script>

<!-- EPAM UUI Styles Core -->
<link rel="stylesheet" href="css/uui-all.css" />
<!-- Your custom CSS Styles -->
<#--<link rel="stylesheet" href="css/custom-styles.css" />-->

<!-- Scroll for UUI Sidebar -->
<link rel="stylesheet" href="css/lib/components/jquery.mCustomScrollbar.min.css" />
<script src="/uui/js/lib/components/jquery.mCustomScrollbar.concat.min.js"></script>

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

<div class="container-fluid">
    <h1> 3 diff blocks</h1>
    <div class="row">
        <div class="col-md-4" style="background-color: $ff9999">left</div>
        <div class="col-md-4" style="background-color: $ff99CC">middle</div>
        <div class="col-md-4" style="background-color: $ff99ff">right</div>
    </div>
</div>
<#--<form class="contentForm">-->
    <div class="time-inf">
        <#--        <ctg:time-inf/>-->
    </div>

    <div class="contentHeader">
        <a href="to_tours">
            <@spring.message "common.message.availableTours"/>
        </a>
    </div>

<#--</form>-->
</body>
<#import "components/timestamp.ftl" as timestamp/>
<#--<@timestamp.page/>-->
</html>
<#assign known=Session.SPRING_SECURITY_CONTEXT??>