<#macro page>
    <#import "/spring.ftl" as spring/>
<footer style="text-align: center; position: relative;">
    <div style="background:white; position:relative; padding:60px 10px;">
        <@spring.message "build.timestamp"/>
    </div>
</footer>
</#macro>