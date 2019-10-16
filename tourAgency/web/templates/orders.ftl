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
    <#if secutity.isClient>
        <title><@spring.message "client.title.orders"/></title>
    </#if>
    <#if !secutity.isClient>
        <title><@spring.message "adminAgent.title.order"/></title>
    </#if>
</head>
<body>

<div class="contentForm">

    <div class="contentContainer">
        <div class="contentHeader">
            <#if secutity.isClient>
                <@spring.message "client.title.orders"/>
            </#if>
            <#if !secutity.isClient>
                <@spring.message "adminAgent.title.order"/>
            </#if>
            <hr/>
        </div>

        <#assign orderList = (Request.orderList)/>
        <div class="orders">
            <#if orderList.isEmpty>
                <@spring.message "common.message.noReservations"/>
            </#if>

            <#list orderList as orderItem>
                <@orderForm.page order = orderItem/>
<#--            <import url="components/order-form.jsp">-->
<#--                <c:param name="orderId" value="${order.getId()}"/>-->
<#--                <c:param name="ticketId" value="${order.getTicket().getId()}"/>-->
<#--                <c:param name="tourName" value="${order.getTour().getTourName()}"/>-->
<#--                <c:param name="arrivalDate" value="${order.getTicket().getArrivalDateTime()}"/>-->
<#--                <c:param name="departureDate" value="${order.getTicket().getDepartureDateTime()}"/>-->
<#--                <c:param name="arrivalCity" value="${order.getTicket().getArrivalCity()}"/>-->
<#--                <c:param name="departureCity" value="${order.getTicket().getDepartureCity()}"/>-->
<#--                <c:param name="clientName" value="${order.getClient().getName()}"/>-->
<#--                <c:param name="agentName" value="${order.getAgent().getName()}"/>-->
<#--                <c:param name="clientEmail" value="${order.getClient().getEmail()}"/>-->
<#--                <c:param name="agentEmail" value="${order.getAgent().getEmail()}"/>-->
<#--                <c:param name="paymentState" value="${order.getPaymentState()}"/>-->
<#--            </import>-->
            </#list>
        </div>

        <nav>
            <form class="pagination" id="paginationForm" method="get" action="to_orders">
                <input type="hidden" name="index" value="${startIndexOfOrders/ordersPerPage + 1}"/>

                <#if startIndexOfOrders == 0>
                    <div class="page-item disabled">
                        <a class="page-link">
                            <@secutity.message "common.ref.previousPage"/>
                        </a>
                    </div>
                </#if>
                <#if startIndexOfOrders > 0>
                    <div class="page-item">
                        <div id="previousPageForm">
                            <input type="hidden" id="previousPage" name="changePage" value="-1" disabled>
                            <a class="page-link" href="#" onclick="document.getElementById('previousPage').disabled=false;
                                    document.getElementById('paginationForm').submit()">
                                <@secutity.message "common.ref.previousPage"/>
                            </a>
                        </div>
                    </div>
                </#if>

                <div class="page-item"><a class="page-link">${index}</a></div>

                <#if startIndexOfOrders + ordersPerPage < numberOfOrders>
                    <div class="page-item">
                        <div id="nextPageForm">
                            <input type="hidden" id="nextPage" name="changePage" value="1" disabled>
                            <a class="page-link" href="#" onclick="document.getElementById('nextPage').disabled = false;
                                    document.getElementById('paginationForm').submit()">
                                <@secutity.message "common.ref.nextPage"/>
                            </a>
                        </div>
                    </div>
                </#if>
                <#if startIndexOfOrders + ordersPerPage >= numberOfOrders>
                    <div class="page-item disabled">
                        <a class="page-link">
                            <@secutity.message "common.ref.nextPage"/>
                        </a>
                    </div>
                </#if>
            </form>
        </nav>
    </div>
</div>

</body>
<#import "components/timestamp.ftl" as timestamp/>
<@timestamp.page/>
</html>
