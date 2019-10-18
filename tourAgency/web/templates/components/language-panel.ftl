<#macro page>
    <#import "/spring.ftl" as spring/>
    <#assign language = (.locale)! "en"/>
    <form class="language" action="/tour-agency/lang" method="get">
        <@spring.message "common.message.language"/>
        <#if language??>
            <label for=${language}>
                <select id="language" class="selection-handle" name="language" onchange="submit()">
                    <option value="en" <#if language == 'en'> selected </#if> >
                                <@spring.message "common.message.english"/>
                    </option>
                    <option value="ru" <#if language == 'ru'> selected </#if> >
                                <@spring.message "common.message.russian"/>
                    </option>
                    <option value="pl" <#if language == 'pl'> selected </#if> >
                                <@spring.message "common.message.polski"/>
                    </option>
                </select>
            </label>
        </#if>
    </form>
</#macro>
