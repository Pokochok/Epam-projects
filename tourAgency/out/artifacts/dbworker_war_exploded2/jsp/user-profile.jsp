<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="jsp/userProfile"/>

<html>
<head>
    <link href="../css/profile-style.css" rel="stylesheet" type="text/css"/>
    <title><fmt:message key="common.title"/></title>
</head>
<body>
<c:import url="components/main-panel.jsp"/>

<form class="mainProfileContainer">
    <div class="userContent">
        <div class="userContentItem">
            <div class="userContentItemMsg">
                <fmt:message key="common.message.name"/>
            </div>
            <c:out value="${userName}"/> <a href=""> <fmt:message key="common.change"/></a>
        </div>
        <div class="userContentItem">
            <div class="userContentItemMsg">
                <fmt:message key="common.message.surname"/>
            </div>
            <c:out value="${userSurname}"/> <a href=""> <fmt:message key="common.change"/> </a>
        </div>

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
        </form>

        <div class="userContentItem">
            <div class="userContentItemMsg">
                <fmt:message key="common.message.email"/>
            </div>
            <c:out value="${userEmail}"/> <a href=""> <fmt:message key="common.change"/> </a>
        </div>

        <div class="userContentItem">
            <div class="userContentItemMsg">
                <fmt:message key="common.message.login"/>
            </div>
            <c:out value="${userLogin}"/> <a href=""> <fmt:message key="common.change"/> </a>
        </div>

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

            <a id="closeChangePassword" hidden
               onclick="document.getElementById('changePassword').hidden=true;
               document.getElementById('closeChangePassword').hidden=true;
               document.getElementById('changePasswordMsg').hidden=false;
               document.getElementById('password').required=false;document.getElementById('newPassword').required=false">
                <fmt:message key="common.close"/>
            </a>

            <div id="changePassword" hidden>
                <label>
                    <input type="hidden" name="command" value="change_password"/>
                    <fmt:message key="common.message.oldPassword"/>
                    <input type="password" id="password" name="password" minlength="6" maxlength="50">
                    <fmt:message key="common.message.newPassword"/>
                    <input type="password" id="newPassword" name="newPassword" minlength="6" maxlength="50">
                    <fmt:message key="common.changePassword" var="changePNButton"/>
                    <input type="submit" value="${changePNButton}">
                </label>
            </div>
            ${errorChangePassword}
            ${resultChangePassword}
        </form>
    </div>
</form>

</body>
</html>