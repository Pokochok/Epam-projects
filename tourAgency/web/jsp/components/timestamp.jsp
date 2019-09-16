<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="jsp/timestamp"/>
<footer style="text-align: center; position: relative;">
    <div style="background:white; position:relative; padding:60px 10px;">
        <fmt:message key="build.timestamp"/>
    </div>
</footer>
