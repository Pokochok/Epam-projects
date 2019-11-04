<#import "components/another-panel.ftl" as anotherPanel/>
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html>
<head>
    <@anotherPanel.page>
        <#include "../css/login-style.css"/>
    </@anotherPanel.page>
    <title><@spring.message "common.companyName"/></title>
</head>
<body>

<form class="loginForm" method="POST" action="back_to_main">

    <div class="userContent">
        <h2 class="header"><@spring.message "common.message.notification"/></h2>
        <hr/>
        ${resultInformation!}
        <br>
        <br>
        <label>
            <input class="uui-button blue" type="submit" value="<@spring.message "common.message.backToMain"/>"/>
        </label>

    </div>
</form>

</body>
<#import "components/timestamp.ftl" as timestamp/>
<@timestamp.page/>
</html>