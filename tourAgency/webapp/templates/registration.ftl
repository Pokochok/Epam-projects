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
                <input type="text" class="uui-form-element" name="name" required pattern="^[^!@#$%^&*().,_\d=|?`~/<>']{1,30}$"/>
            </label>
        </div>

        <div class="contentItem">
            <@spring.message "registration.message.surname"/>
            <label>
                <input type="text" class="uui-form-element" name="surname" required pattern="^[^!@#$%^&*().,_\d=|?`'~/<>]{1,30}$"/>
            </label>
        </div>

        <div class="contentItem">
            <@spring.message "registration.message.email"/>
            <label>
                <input type="text" class="uui-form-element" name="email" minlength="6" maxlength="100"
                       pattern="^[a-zA-Z0-9.,_%+-]+@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}$"
                       placeholder="<@spring.message "registration.placeholder.email" />" />
            </label>
            ${errorEmailExistsMessage!}
        </div>

        <div class="contentItem">
            <@spring.message "registration.message.phoneNumber"/>
            <label>
                <input type="text" class="uui-form-element" name="phoneNumber" pattern="^[+]\d{10,12}$"
                       placeholder="<@spring.message "registration.placeholder.phoneNumber" />" />
            </label>
            ${errorPhoneNumberExistsMessage!}
        </div>

        <div class="contentItem">
            <@spring.message "registration.message.login"/>
            <label>
                <input type="text" class="uui-form-element" name="login" minlength="4" maxlength="50" required
                       placeholder=
                       <@spring.message "registration.placeholder.login"/>/>
            </label>
            ${errorLoginExistsMessage!}
        </div>

        <div class="contentItem">
            <@spring.message "registration.message.password"/>
            <label>
                <input type="password" class="uui-form-element" name="password" minlength="6" maxlength="50" required
                       placeholder="<@spring.message "registration.placeholder.password"/>"/>
            </label>
        </div>

        <div class="contentItem">
                    <label class="uui-input-group vertical radio-button">
                        <p class="uui-radio">
                            <input type="radio" name="userRole" value="client" id="a1" checked />
                            <label for="a1"><@spring.message "registration.radio.client"/></label>
        </p>
        <p class="uui-radio">
                            <input type="radio" name="userRole" value="agent" id="a2" />
                            <label for="a2"><@spring.message "registration.radio.agent"/></label>
        </p>
        </label>
        </div>
        <br/>
        <input type="submit" class="transparent uui-button lime-green" name="register" value="<@spring.message "registration.submit.register"/>"/>
    </div>
</form>
</body>
<#import "components/timestamp.ftl" as timestamp/>
<@timestamp.page/>
</html>
