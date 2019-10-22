<#macro page>
    <#import "/spring.ftl" as spring/>
    <#import "/WEB-INF/tld/customtag.tld" as ctg/>
<footer id="Footer" style="bottom:0; position: fixed;">
<div class="uui-footer">
        <div class="footer-logo-copyright">
            <div class="epam-logo">
                <img src="/images/Logo_Epam_Color.svg" alt="" />
            </div>
<p class="copyright">© 2019 EPAM Systems. </p>
</div>
<div class="footer-build">
            <p class="build"><@spring.message "build.timestamp"/></p>
</div>
</div>
</footer>
</#macro>