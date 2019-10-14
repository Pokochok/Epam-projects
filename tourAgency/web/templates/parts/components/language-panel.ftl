<#macro page>
<!DOCTYPE html>
<html>
<head>
<style>
<%@include file="../../../css/componentsstyle/language-panel.css"%>
    </style>
</head>
<body>

<form class="language">
    <fmt:message key="common.message.language"/>
    <label for=${language}>
        <select id="language" class="selection-handle" name="language" onchange="submit()">
            <option value="en" <#if language == 'en'> selected </#if> ><fmt:message
                    key="common.message.english"/></option>
<option value="ru" <#if language == 'ru'> selected </#if> ><fmt:message
                    key="common.message.russian"/></option>
<option value="pl" <#if language == 'pl'> selected </#if> ><fmt:message
                    key="common.message.polski"/></option>
</select>
</label>
</form>
</body>
</html>
</#macro>
