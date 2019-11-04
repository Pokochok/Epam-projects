<#import "components/main-panel.ftl" as mainPanel/>
<#import "/spring.ftl" as spring/>
<#import "components/security.ftl" as secutity/>
<#import "components/order-form.ftl" as orderForm/>

<!DOCTYPE html>
<html>
<head>
    <@mainPanel.page>
        <#include "../css/componentsstyle/order-form.css"/>
        <#include "../css/orders-style.css"/>
    </@mainPanel.page>
    <#if (secutity.isClient)!false>
        <title><@spring.message "client.title.orders"/></title>
    </#if>
    <#if !((secutity.isClient)!false)>
        <title><@spring.message "adminAgent.title.order"/></title>
    </#if>
</head>
<body>
<div class = "uui-loader spinner">
    <div  class = "dot dot-1"></div>
<div  class = "dot dot-2"></div>
<div  class = "dot dot-3"></div>
<div  class = "dot dot-4"></div>
<div  class = "dot dot-5"></div>
<div  class = "dot dot-6"></div>
<div class = "dot dot-7"></div>
<div class = "dot dot-8"></div>
<div class = "dot dot-9"></div>
<div  class = "dot dot-10"></div>
<div  class = "dot dot-11"></div>
<div  class = "dot dot-12"></div>
</div>
<div class="contentForm">

    <div class="contentContainer">
        <div class="contentHeader">
            <#if (secutity.isClient)!false>
                <@spring.message "client.title.orders"/>
            </#if>
            <#if !((secutity.isClient)!false)>
                <@spring.message "adminAgent.title.order"/>
            </#if>
            <hr/>
        </div>

        <#assign orderList = (Request.orderList)/>
        <div class="orders">

            <#list orderList as orderItem>
                <@orderForm.page order = orderItem/>
            <#else>
                <@spring.message "common.message.noReservations"/>
            </#list>
        </div>
        <form method="post" action="to_orders">
            <ul class="uui-pagination">
                <li class="actions-wrapper">
                    <ul class="pagination-items">
                        <#if ((startIndexOfOrders!0) > 0)>
                            <li class="back-pages">
                                <input type="hidden" id="previousPage" name="changePage" value="-1">
                                <a href="to_orders?changePage=-1&index=${startIndexOfOrders/ordersPerPage + 1}">
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
                                <a href="to_orders?changePage=0&index=${(index!1)-2}">${(index!1)-2}</a>
                            </li>
                        </#if>
                        <#if ((index!1) >= 2)>
                            <li>
                                <a href="to_orders?changePage=0&index=${(index!1)-1}">${(index!1)-1}</a>
                            </li>
                        </#if>
                        <li class="active">
                            <a href="to_orders?changePage=0&index=${(index!1)}">${(index!1)}</a>
                        </li>
                        <#if ((startIndexOfOrders!0) + (ordersPerPage!8) < (numberOfOrders!8))>
                            <li>
                                <a href="to_orders?changePage=0&index=${(index!1)+1}">${(index!1)+1}</a>
                            </li>
                        </#if>
                        <#if ((startIndexOfOrders!0) + (ordersPerPage!8)*2 < (numberOfOrders!8))>
                            <li>
                                <a href="to_orders?changePage=0&index=${(index!1)+2}">${(index!1)+2}</a>
                            </li>
                        </#if>
                    </ul>
                </li>
                <li class="actions-wrapper">
                    <ul class="pagination-items">
                        <#if ((startIndexOfOrders!0) + (ordersPerPage!8) < (numberOfOrders!8))>
                            <li class="next-page">
                                <a href="to_orders?changePage=1&index=${startIndexOfOrders/ordersPerPage + 1}">
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

</body>
<#import "components/timestamp.ftl" as timestamp/>
<@timestamp.page/>
</html>
