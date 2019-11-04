<#import "components/main-panel.ftl" as mainPanel/>
<#import "/spring.ftl" as spring/>
<#import "components/security.ftl" as secutity/>
<#import "components/tour-form.ftl" as tourForm/>

<!DOCTYPE html>
<html>
<head>
    <@mainPanel.page>
        <#include "../css/componentsstyle/tour-form.css"/>
        <#include "../css/tours-style.css"/>
    </@mainPanel.page>
    <title><@spring.message "common.title.Tours"/></title>
</head>
<body>

<div class="contentForm" >

    <div class="menu">
        <#if (secutity.isAdmin)!false>
            <div class="registerNewTour">
                <a class="menuRef" href="to_tour_registration">
                    <@spring.message "admin.ref.registerNewTour"/>
                </a>
            </div>
        </#if>
    </div>
    <#assign tourList = (Request.tourList)/>

    <div class="framing">

        <div class="contentContainer">
            <div class="contentHeader">
                <@spring.message "common.message.availableTours"/>
                <hr/>
            </div>

            <div class="tours">
                <#list tourList>
                    <ul>
                        <#items as tourItem>
                            <li type="none"><@tourForm.page tour=tourItem/></li>
                        </#items>
                    </ul>
                </#list>
            </div>
            <form method="post" action="to_tours">
                <ul class="uui-pagination">
                    <li class="actions-wrapper">
                        <ul class="pagination-items">
                            <#if ((startIndexOfTours!0) > 0)>
                                <li class="back-pages">
                                    <input type="hidden" id="previousPage" name="changePage" value="-1">
                                    <a href="to_tours?changePage=-1&index=${startIndexOfTours/toursPerPage + 1}">
                                        <i class="fa fa-angle-double-left"></i>
                                    </a>
                                </li>
                            <#else>
                                <li class="back-pages disable">
                                    <a href="" disabled>
                                        <i class="fa fa-angle-double-left"></i>
                                    </a>
                                </li>
                            </#if>
                        </ul>
                    </li>
                    <li class="pages-wrapper">
                        <ul class="pagination-items">
                            <#if ((index!1) >= 3)>
                                <li>
                                    <a href="to_tours?changePage=0&index=${(index!1)-2}">${(index!1)-2}</a>
                                </li>
                            </#if>
                            <#if ((index!1) >= 2)>
                                <li>
                                    <a href="to_tours?changePage=0&index=${(index!1)-1}">${(index!1)-1}</a>
                                </li>
                            </#if>
                            <li class="active">
                                <a href="to_tours?changePage=0&index=${(index!1)}">${(index!1)}</a>
                            </li>
                            <#if ((startIndexOfTours!0) + (toursPerPage!8) < (numberOfTours!8))>
                                <li>
                                    <a href="to_tours?changePage=0&index=${(index!1)+1}">${(index!1)+1}</a>
                                </li>
                            </#if>
                            <#if ((startIndexOfTours!0) + (toursPerPage!8)*2 < (numberOfTours!8))>
                                <li>
                                    <a href="to_tours?changePage=0&index=${(index!1)+2}">${(index!1)+2}</a>
                                </li>
                            </#if>
                        </ul>
                    </li>
                    <li class="actions-wrapper">
                        <ul class="pagination-items">
                            <#if ((startIndexOfTours!0) + (toursPerPage!8) < (numberOfTours!8))>
                                <li class="next-page">
                                    <a href="to_tours?changePage=1&index=${startIndexOfTours/toursPerPage + 1}">
                                        <i class="fa fa-angle-right"></i>
                                    </a>
                                </li>
                            <#else>
                                <li class="next-page disable">
                                    <a href="" disabled>
                                        <i class="fa fa-angle-right"></i>
                                    </a>
                                </li>
                            </#if>
                        </ul>
                    </li>
                </ul>
            </form>
        </div>
    </div>
</div>

</body>
<#import "components/timestamp.ftl" as timestamp/>
<@timestamp.page/>
</html>
