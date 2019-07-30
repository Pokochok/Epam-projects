<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isErrorPage="true" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
Request from ${pageContext.errorData.requestURI} is failed <br/>
Servlet name or type: ${pageContext.errorData.servletName} <br/>
Status code: ${pageContext.errorData.statusCode} <br/>
Exception: ${pageContext.errorData.throwable} <br/>
<br/>
<a href="controller?command=Back_To_Main">Go to home page</a>

</body>
</html>
