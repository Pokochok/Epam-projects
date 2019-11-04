<#macro page>
    <#import "/spring.ftl" as spring/>
    <#assign language = (.locale)! "en"/>
        <li class="role"><@spring.message "common.message.language"/></li>
        <#if language??>
            <li>
                <form style="padding: 12px; padding-right: 20px" method="get" action="lang">
                    <label  for=${language} >
                        <select id="language" class="selectpicker uui-form-element" name="language" onchange="submit()">
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
                </form>
            </li>
        </#if>
</#macro>
