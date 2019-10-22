<#import "components/main-panel.ftl" as mainPanel/>
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html>
<head>
    <@mainPanel.page>
        <#include "../css/booking-style.css"/>
    </@mainPanel.page>
    <title><@spring.message "aboutCompany.title"/></title>
</head>

<body>

<form class="contentForm">
    <div class="contentContainer">
        <div class="contentHeader">
            <h3><@spring.message "aboutCompany.message.headline"/></h3>
        </div>
        <div class="companyInf">
            <@spring.message "aboutCompany.message.text"/>
        </div>
    </div>
</form>
</body>

<#import "components/timestamp.ftl" as timestamp/>
<@timestamp.page/>
</html>
