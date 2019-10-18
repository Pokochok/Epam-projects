<#import "components/another-panel.ftl" as anotherPanel/>
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html>
<head>
<!-- jQuery Core -->
<script src="/uui/js/lib/jquery-1.12.0.min.js"></script>

<!-- Bootstrap Core -->
<link rel="stylesheet" href="/uui/bootstrap/css/bootstrap.min.css" />
<script src="/uui/bootstrap/js/bootstrap.min.js"></script>

<!-- EPAM UUI JavaScript Core -->
<script src="/uui/js/uui-core.min.js" type="text/javascript"></script>

<!-- EPAM UUI Styles Core -->
<link rel="stylesheet" href="/uui/css/uui-all.css" />
<!-- Your custom CSS Styles -->
<#--<link rel="stylesheet" href="css/custom-styles.css" />-->

<!-- Scroll for UUI Sidebar -->
<link rel="stylesheet" href="/uui/css/lib/components/jquery.mCustomScrollbar.min.css" />
<script src="/uui/js/lib/components/jquery.mCustomScrollbar.concat.min.js"></script>
    <@anotherPanel.page>
        <#include "../css/login-style.css"/>

    </@anotherPanel.page>
</head>
<title>
<@spring.message "login.title"/>
</title>
<body>
<div class = "uui-loader spinner">
    <div  class = "dot dot-1"></div>
    <div  class = "dot dot-2"></div>
    <div  class = "dot dot-3"></div>
    <div  class = "dot dot-4"></div>
    <div  class = "dot dot-5"></div>
    <div  class = "dot dot-6"></div>
    <div class = "dot dot-7"></div>
    <div class = "dot dot-8"></div>
    <div class = "dot dot-9"></div>
    <div  class = "dot dot-10"></div>
    <div  class = "dot dot-11"></div>
    <div  class = "dot dot-12"></div>
</div>
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
                <input type="text" class="uui-form-element" name="login"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "login.message.password"/>
            </div>
            <label>
                <input type="password" class="uui-form-element" name="password"/>
            </label>
        </div>

        <div class="InfMsg">
            <br> ${(param.notAuthorized)!}
            <br> ${errorLoginPassMessage!}
            <br> ${wrongAction!}
            <br> ${nullPage!}
        </div>

        <label>
            <input type="submit" class="blue uui-button" name="logIn" value="<@spring.message "login.submit.login"/>"/>
        </label>

        <a href="to_registration" class="lime-green uui-button">
            <@spring.message "login.ref.page.register"/>
        </a>
    </div>
</form>
</body>
<#import "components/timestamp.ftl" as timestamp/>
<@timestamp.page/>
</html>