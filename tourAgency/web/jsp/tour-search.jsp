<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="jsp/Home"/>
<html>
<head>
    <title>Tour search</title>
</head>
<body>
<c:import url="components/main-panel.jsp">
    <c:param name="language"/>
</c:import>
</body>
</html>
