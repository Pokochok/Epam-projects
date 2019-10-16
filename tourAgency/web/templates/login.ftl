<#import "components/another-panel.ftl" as anotherPanel/>
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html>
<head>
    <@anotherPanel.page>
        <#include "../css/login-style.css"/>
    </@anotherPanel.page>
    <title>
        <@spring.message "login.title"/>
    </title>
</head>
<body>

<form class="loginForm" method="POST" action="login">

    <div class="userContent">
        <h2 class="header">
            <@spring.message "login.message.authorization"/>
        </h2>
        <hr/>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "login.message.login"/>
            </div>
            <label>
                <input type="text" name="login"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "login.message.password"/>
            </div>
            <label>
                <input type="password" name="password"/>
            </label>
        </div>

        <div class="InfMsg">
            <br> ${(param.notAuthorized)!}
            <br> ${errorLoginPassMessage!}
            <br> ${wrongAction!}
            <br> ${nullPage!}
        </div>

        <label>
            <input type="submit" name="logIn" value="<@spring.message "login.submit.login"/>"/>
        </label>
        <br/>
        <br/>
        <a href="to_registration">
            <@spring.message "login.ref.page.register"/>
        </a>
    </div>
</form>
</body>
<#import "components/timestamp.ftl" as timestamp/>
<@timestamp.page/>
</html>