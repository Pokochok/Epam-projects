<#macro page>
    <#import "/spring.ftl" as spring/>
<footer >
<div class="companyBlue uui-footer green">
        <div class="footer-logo-copyright">
            <div class="epam-logo">
                <img src="/images/logo.png" alt="" />
            </div>
<p class="copyright">© 2016 EPAM Systems. All Rights Reserved.</p>
</div>
<div class="footer-build">
            <p class="build"><@spring.message "build.timestamp"/></p>
</div>
</div>
</footer>
</#macro>