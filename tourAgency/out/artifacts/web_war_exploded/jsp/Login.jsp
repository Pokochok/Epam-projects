<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>LogIn</title>
</head>
<body>
<form name="LoginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="Login"/>
    Login: <br>
    <input type="text" name="login" /> <!-- value-->
    <br>Password: <br>
    <label>
        <input type="password" name="password" />
    </label>
    <br> ${errorLoginPassMessage}
    <br> ${wrongAction}
    <br> ${nullPage}
    <br/>
    <input type="submit" name="logIn" value="Log in"/>

</form><hr/>
</body>
</html>
