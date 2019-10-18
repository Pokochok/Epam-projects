<#import "components/main-panel.ftl" as mainPanel/>
<#import "/spring.ftl" as spring/>
<#import "components/security.ftl" as secutity/>
<!DOCTYPE html>
<html>
<head>
    <@mainPanel.page>
        <#include "../css/profile-style.css"/>
    </@mainPanel.page>
    <title><@spring.message "common.title"/></title>
</head>
<body>

<div class="mainProfileContainer">
    <div class="userContent">
        <form class="userContentItem" method="POST" action="change_user_name">
            <div class="userContentItemMsg"><@spring.message "common.message.name"/></div>

            ${secutity.userName!}

            <a id="changeUserNameMsg" onclick="document.getElementById('changeUserNameMsg').hidden=true;
               document.getElementById('changeUserNameDiv').hidden=false;
               document.getElementById('newUserName').required=true">
                <@spring.message "common.change"/>
            </a>

            <div hidden
                 id="changeUserNameDiv">
                <a id="closeUserName" onclick="document.getElementById('changeUserNameMsg').hidden=false;
                document.getElementById('changeUserNameDiv').hidden=true;
                document.getElementById('newUserName').required=false">
                    <@spring.message "common.close"/>
                </a>

                <div id="changeUserName">
                    <label>
                        <input type="text" id="newUserName" name="newUserName"
                               pattern="^[^!@#$%^&*().,_\d=|?`~/<>]{1,30}$">

                        <input type="submit" value="<@spring.message "common.changeUserName" />">
                    </label>
                </div>
            </div>
            ${errorChangeUserName!}
        </form>

        <form class="userContentItem" method="POST" action="change_user_surname">
            <div class="userContentItemMsg"><@spring.message "common.message.surname"/></div>

            ${secutity.userSurname!}

            <a id="changeUserSurnameMsg" onclick="document.getElementById('changeUserSurnameMsg').hidden=true;
               document.getElementById('changeUserSurnameDiv').hidden=false;
               document.getElementById('newUserSurname').required=true">
                <@spring.message "common.change"/>
            </a>

            <div hidden
                 id="changeUserSurnameDiv">
                <a id="closeUserSurname" onclick="document.getElementById('changeUserSurnameMsg').hidden=false;
                document.getElementById('changeUserSurnameDiv').hidden=true;
                document.getElementById('newUserSurname').required=false">
                    <@spring.message "common.close"/>
                </a>

                <div id="changeUserSurname">
                    <label>

                        <input type="text" id="newUserSurname" name="newUserSurname"
                               pattern="^[^!@#$%^&*().,_\d=|?`~/<>]{1,30}$">

                        <input type="submit" value="<@spring.message "common.changeUserSurname"/>">
                    </label>
                </div>
            </div>
            ${errorChangeUserSurname!}
        </form>

        <form class="userContentItem" method="POST" action="change_phone_number">
            <div class="userContentItemMsg"><@spring.message "common.message.phoneNumber"/></div>

            ${secutity.userPhoneNumber!}

            <a id="changePhoneNumberMsg" onclick="document.getElementById('changePhoneNumberMsg').hidden=true;
               document.getElementById('changePhoneNumberDiv').hidden=false;
               document.getElementById('newPhoneNumber').required=true">
                <@spring.message "common.change"/>
            </a>

            <div hidden
                 id="changePhoneNumberDiv">
                <a id="closeChangePhoneNumber" onclick="document.getElementById('changePhoneNumberMsg').hidden=false;
                document.getElementById('changePhoneNumberDiv').hidden=true;
                document.getElementById('newPhoneNumber').required=false">
                    <@spring.message "common.close"/>
                </a>

                <div id="changePhoneNumber">
                    <label>
                        <input type="text" id="newPhoneNumber" name="newPhoneNumber" pattern="^[+]\d{10,12}$">

                        <input type="submit" value="<@spring.message "common.changePN"/>">
                    </label>
                </div>
            </div>
            ${errorChangePhoneNumber!}
            ${errorPNExistsMessage!}
        </form>

        <form class="userContentItem" method="POST" action="change_email">
            <div class="userContentItemMsg"><@spring.message "common.message.email"/></div>

            ${secutity.userEmail!}

            <a id="changeEmailMsg" onclick="document.getElementById('changeEmailMsg').hidden=true;
               document.getElementById('changeEmailDiv').hidden=false;
               document.getElementById('newEmail').required=true">
                <@spring.message "common.change"/>
            </a>

            <div hidden
                 id="changeEmailDiv">
                <a id="closeChangeEmail" onclick="document.getElementById('changeEmailMsg').hidden=false;
                document.getElementById('changeEmailDiv').hidden=true;
                document.getElementById('newEmail').required=false">
                    <@spring.message "common.close"/>
                </a>

                <div id="changeEmail">
                    <label>
                        <input type="text" id="newEmail" name="newEmail"
                               pattern="^[a-zA-Z0-9.,_%+-]+@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}$">

                        <input type="submit" value="<@spring.message "common.changeEmail"/>">
                    </label>
                </div>
            </div>
            ${errorChangeEmail!}
            ${errorEmailExistsMessage!}
        </form>

        <form class="userContentItem" method="POST" action="change_login">
            <div class="userContentItemMsg"><@spring.message "common.message.login"/></div>

            ${secutity.userLogin!}

            <a id="changeLoginMsg" onclick="document.getElementById('changeLoginMsg').hidden=true;
               document.getElementById('changeLoginDiv').hidden=false;
               document.getElementById('newLogin').required=true">
                <@spring.message "common.change"/>
            </a>

            <div hidden
                 id="changeLoginDiv">
                <a id="closeChangeLogin" onclick="document.getElementById('changeLoginMsg').hidden=false;
                document.getElementById('changeLoginDiv').hidden=true;
                document.getElementById('newLogin').required=false">
                    <@spring.message "common.close"/>
                </a>

                <div id="changeLogin">
                    <label>
                        <input type="text" id="newLogin" name="newLogin">
                        <input type="submit" value="<@spring.message "common.changeLogin"/>">
                    </label>
                </div>
            </div>
            ${errorChangeLogin!}
            ${errorLoginExistsMessage!}
        </form>

        <form class="userContentItem" method="POST" action="change_password">
            <div class="userContentItemMsg">
                <@spring.message "common.message.password"/>
            </div>
            <a id="changePasswordMsg"
               onclick="document.getElementById('changePassword').hidden=false;
               document.getElementById('closeChangePassword').hidden=false;
               document.getElementById('changePasswordMsg').hidden=true;
               document.getElementById('password').required=false;document.getElementById('newPassword').required=false">
                <@spring.message "common.change"/>
            </a>

            <div id="changePassword" hidden>
                <label>
                    <@spring.message "common.message.oldPassword"/>
                    <input type="password" id="password" name="password" minlength="6" maxlength="50">
                    <br>
                    <@spring.message "common.message.newPassword"/>
                    <input type="password" id="newPassword" name="newPassword" minlength="6" maxlength="50">
                    <input type="submit" value="<@spring.message "common.changePassword"/>">
                </label>
            </div>
            ${errorChangePassword!}
            ${resultChangePassword!}

            <a id="closeChangePassword" hidden
               onclick="document.getElementById('changePassword').hidden=true;
               document.getElementById('closeChangePassword').hidden=true;
               document.getElementById('changePasswordMsg').hidden=false;
               document.getElementById('password').required=false;document.getElementById('newPassword').required=false">
                <@spring.message "common.close"/>
            </a>
        </form>
    </div>
</div>

</body>
<#import "components/timestamp.ftl" as timestamp/>
<@timestamp.page/>

</html>