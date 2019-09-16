<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <%@include file="components/main-panel.jsp" %>
    <style>
        <%@include file="../css/profile-style.css"%>
    </style>
    <fmt:setBundle basename="jsp/user-profile"/>
    <title><fmt:message key="common.title"/></title>
</head>
<body>

<div class="mainProfileContainer">
    <div class="userContent">
        <form class="userContentItem" method="POST" action="controller">
            <div class="userContentItemMsg"><fmt:message key="common.message.name"/></div>

            <c:out value="${userName}"/>

            <a id="changeUserNameMsg" onclick="document.getElementById('changeUserNameMsg').hidden=true;
               document.getElementById('changeUserNameDiv').hidden=false;
               document.getElementById('newUserName').required=true">
                <fmt:message key="common.change"/>
            </a>

            <div hidden
                 id="changeUserNameDiv">
                <a id="closeUserName" onclick="document.getElementById('changeUserNameMsg').hidden=false;
                document.getElementById('changeUserNameDiv').hidden=true;
                document.getElementById('newUserName').required=false">
                    <fmt:message key="common.close"/>
                </a>

                <div id="changeUserName">
                    <label>
                        <input type="hidden" name="command" value="change_user_name"/>

                        <input type="text" id="newUserName" name="newUserName"
                               pattern="^[^!@#$%^&*().,_\d=|?`~/<>]{1,30}$">

                        <fmt:message key="common.changeUserName" var="changeUserNameButton"/>

                        <input type="submit" value="${changeUserNameButton}">
                    </label>
                </div>
            </div>
            ${errorChangeUserName}
        </form>

        <form class="userContentItem" method="POST" action="controller">
            <div class="userContentItemMsg"><fmt:message key="common.message.surname"/></div>

            <c:out value="${userSurname}"/>

            <a id="changeUserSurnameMsg" onclick="document.getElementById('changeUserSurnameMsg').hidden=true;
               document.getElementById('changeUserSurnameDiv').hidden=false;
               document.getElementById('newUserSurname').required=true">
                <fmt:message key="common.change"/>
            </a>

            <div hidden
                 id="changeUserSurnameDiv">
                <a id="closeUserSurname" onclick="document.getElementById('changeUserSurnameMsg').hidden=false;
                document.getElementById('changeUserSurnameDiv').hidden=true;
                document.getElementById('newUserSurname').required=false">
                    <fmt:message key="common.close"/>
                </a>

                <div id="changeUserSurname">
                    <label>
                        <input type="hidden" name="command" value="change_user_surname"/>

                        <input type="text" id="newUserSurname" name="newUserSurname"
                               pattern="^[^!@#$%^&*().,_\d=|?`~/<>]{1,30}$">

                        <fmt:message key="common.changeUserSurname" var="changeUserSurnameButton"/>

                        <input type="submit" value="${changeUserSurnameButton}">
                    </label>
                </div>
            </div>
            ${errorChangeUserSurname}
        </form>

        <form class="userContentItem" method="POST" action="controller">
            <div class="userContentItemMsg"><fmt:message key="common.message.phoneNumber"/></div>

            <c:out value="${userPhoneNumber}"/>

            <a id="changePhoneNumberMsg" onclick="document.getElementById('changePhoneNumberMsg').hidden=true;
               document.getElementById('changePhoneNumberDiv').hidden=false;
               document.getElementById('newPhoneNumber').required=true">
                <fmt:message key="common.change"/>
            </a>

            <div hidden
                 id="changePhoneNumberDiv">
                <a id="closeChangePhoneNumber" onclick="document.getElementById('changePhoneNumberMsg').hidden=false;
                document.getElementById('changePhoneNumberDiv').hidden=true;
                document.getElementById('newPhoneNumber').required=false">
                    <fmt:message key="common.close"/>
                </a>

                <div id="changePhoneNumber">
                    <label>
                        <input type="hidden" name="command" value="change_phone_number"/>

                        <input type="text" id="newPhoneNumber" name="newPhoneNumber" pattern="^[+]\d{10,12}$">

                        <fmt:message key="common.changePN" var="changePNButton"/>

                        <input type="submit" value="${changePNButton}">
                    </label>
                </div>
            </div>
            ${errorChangePhoneNumber}
            ${errorPNExistsMessage}
        </form>

        <form class="userContentItem" method="POST" action="controller">
            <div class="userContentItemMsg"><fmt:message key="common.message.email"/></div>

            <c:out value="${userEmail}"/>

            <a id="changeEmailMsg" onclick="document.getElementById('changeEmailMsg').hidden=true;
               document.getElementById('changeEmailDiv').hidden=false;
               document.getElementById('newEmail').required=true">
                <fmt:message key="common.change"/>
            </a>

            <div hidden
                 id="changeEmailDiv">
                <a id="closeChangeEmail" onclick="document.getElementById('changeEmailMsg').hidden=false;
                document.getElementById('changeEmailDiv').hidden=true;
                document.getElementById('newEmail').required=false">
                    <fmt:message key="common.close"/>
                </a>

                <div id="changeEmail">
                    <label>
                        <input type="hidden" name="command" value="change_email"/>

                        <input type="text" id="newEmail" name="newEmail"
                               pattern="^[a-zA-Z0-9.,_%+-]+@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}$">

                        <fmt:message key="common.changeEmail" var="changeEmailButton"/>

                        <input type="submit" value="${changeEmailButton}">
                    </label>
                </div>
            </div>
            ${errorChangeEmail}
            ${errorEmailExistsMessage}
        </form>

        <form class="userContentItem" method="POST" action="controller">
            <div class="userContentItemMsg"><fmt:message key="common.message.login"/></div>

            <c:out value="${userLogin}"/>

            <a id="changeLoginMsg" onclick="document.getElementById('changeLoginMsg').hidden=true;
               document.getElementById('changeLoginDiv').hidden=false;
               document.getElementById('newLogin').required=true">
                <fmt:message key="common.change"/>
            </a>

            <div hidden
                 id="changeLoginDiv">
                <a id="closeChangeLogin" onclick="document.getElementById('changeLoginMsg').hidden=false;
                document.getElementById('changeLoginDiv').hidden=true;
                document.getElementById('newLogin').required=false">
                    <fmt:message key="common.close"/>
                </a>

                <div id="changeLogin">
                    <label>
                        <input type="hidden" name="command" value="change_login"/>

                        <input type="text" id="newLogin" name="newLogin">

                        <fmt:message key="common.changeLogin" var="changeLoginButton"/>

                        <input type="submit" value="${changeLoginButton}">
                    </label>
                </div>
            </div>
            ${errorChangeLogin}
            ${errorLoginExistsMessage}
        </form>

        <form class="userContentItem" method="POST" action="controller">
            <div class="userContentItemMsg">
                <fmt:message key="common.message.password"/>
            </div>
            <a id="changePasswordMsg"
               onclick="document.getElementById('changePassword').hidden=false;
               document.getElementById('closeChangePassword').hidden=false;
               document.getElementById('changePasswordMsg').hidden=true;
               document.getElementById('password').required=false;document.getElementById('newPassword').required=false">
                <fmt:message key="common.change"/>
            </a>

            <div id="changePassword" hidden>
                <label>
                    <input type="hidden" name="command" value="change_password"/>
                    <fmt:message key="common.message.oldPassword"/>
                    <input type="password" id="password" name="password" minlength="6" maxlength="50">
                    <br>
                    <fmt:message key="common.message.newPassword"/>
                    <input type="password" id="newPassword" name="newPassword" minlength="6" maxlength="50">
                    <fmt:message key="common.changePassword" var="changePNButton"/>
                    <input type="submit" value="${changePNButton}">
                </label>
            </div>
            ${errorChangePassword}
            ${resultChangePassword}

            <a id="closeChangePassword" hidden
               onclick="document.getElementById('changePassword').hidden=true;
               document.getElementById('closeChangePassword').hidden=true;
               document.getElementById('changePasswordMsg').hidden=false;
               document.getElementById('password').required=false;document.getElementById('newPassword').required=false">
                <fmt:message key="common.close"/>
            </a>
        </form>
    </div>
</div>

</body>
<c:import url="components/timestamp.jsp"></c:import>
</html>