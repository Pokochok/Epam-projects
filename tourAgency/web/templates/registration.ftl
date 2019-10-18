<#import "components/another-panel.ftl" as anotherPanel/>
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html>
<head>
    <@anotherPanel.page>
        <#include "../css/registration-style.css"/>
    </@anotherPanel.page>
    <title><@spring.message "registration.title"/></title>
</head>
<body>

<form name="registrationForm" method="POST" action="registration">
    <div class="userContent">
        <h2><@spring.message "registration.message.headline"/></h2>
        <hr/>

        <div class="contentItem">
            <@spring.message "registration.message.name"/>
            <label>
                <input type="text" name="name" required pattern="^[^!@#$%^&*().,_\d=|?`~/<>']{1,30}$"/>
            </label>
        </div>

        <div class="contentItem">
            <@spring.message "registration.message.surname"/>
            <label>
                <input type="text" name="surname" required pattern="^[^!@#$%^&*().,_\d=|?`'~/<>]{1,30}$"/>
            </label>
        </div>

        <div class="contentItem">
            <@spring.message "registration.message.email"/>
            <label>
                <input type="text" name="email" minlength="6" maxlength="100"
                       pattern="^[a-zA-Z0-9.,_%+-]+@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}$"
                       placeholder="<@spring.message "registration.placeholder.email" />" />
            </label>
            ${errorEmailExistsMessage!}
        </div>

        <div class="contentItem">
            <@spring.message "registration.message.phoneNumber"/>
            <label>
                <input type="text" name="phoneNumber" pattern="^[+]\d{10,12}$"
                       placeholder="<@spring.message "registration.placeholder.phoneNumber" />" />
            </label>
            ${errorPhoneNumberExistsMessage!}
        </div>

        <div class="contentItem">
            <@spring.message "registration.message.login"/>
            <label>
                <input type="text" name="login" minlength="4" maxlength="50" required
                       placeholder=
                       <@spring.message "registration.placeholder.login"/>/>
            </label>
            ${errorLoginExistsMessage!}
        </div>

        <div class="contentItem">
            <@spring.message "registration.message.password"/>
            <label>
                <input type="password" name="password" minlength="6" maxlength="50" required
                       placeholder="<@spring.message "registration.placeholder.password"/>"/>
            </label>
        </div>

        <div class="contentItem">
            <label class="radio-button">
                <input type="radio" name="userRole" value="client" checked><@spring.message "registration.radio.client"/>
                <br>
                <input type="radio" name="userRole" value="agent"> <@spring.message "registration.radio.agent"/> <br>
            </label>
        </div>

        <br/>
        <input type="submit" name="register" value="<@spring.message "registration.submit.register"/>"/>
    </div>
</form>
</body>
<#import "components/timestamp.ftl" as timestamp/>
<@timestamp.page/>
</html>
