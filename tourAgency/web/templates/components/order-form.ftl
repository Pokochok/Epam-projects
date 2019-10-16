<#macro page order>
    <#import "security.ftl" as springSecurity/>
    <#import "/spring.ftl" as spring/>
    <div class="tourItem">

        <div class="MinInf">
            <div class="TourName"
                    <#if order.paymentState>
                        style="color: green"
                    <#else>
                        style="color: orange"
                    </#if>
            >
                <div class="MinInfMsg">
                    <@spring.message "common.message.tourName"/>
                </div>
                <#if order.tourName == 'not defined'>
                    <c:out value="--"/>
                </#if>
                <#if order.tourName != 'not defined'>
                    <c:out value="${order.tourName}"/>
                </#if>
            </div>

            <div class="ticketInf">
                <div class="MinInfMsg">
                    <@spring.message "common.message.ticketInf"/>
                </div>
                <#if order.ticketId != '0'>
                    <div class="ticketPlace">
                        ${order.departureCity} - ${order.arrivalCity}"
                    </div>
                    <div class="ticketDate">
                        ${order.departureDate} - ${order.arrivalDate}"
                    </div>
                <#elseif order.ticketId == '0'>
                    --
                </#if>
            </div>

            <div class="clientInf">
                <div class="MinInfMsg">
                    <@spring.message "common.message.clientInf"/>
                </div>
                <div>
                    ${order.clientName}"
                </div>
                <div>
                    ${order.clientEmail}"
                </div>
            </div>


            <div class="agentInf">
                <div class="MinInfMsg">
                    <@spring.message "common.message.agentInf"/>
                </div>
                <#if order.agentName != 'not defined'>
                    <div>
                        ${order.agentName}
                    </div>
                    <div>
                        ${order.agentEmail}
                    </div>
                <#else>
                    --
                </#if>
            </div>
        </div>

        <div class="orderMore">
            <form method="POST" action="pay_order">
                <input type="hidden" name="orderId" value="${order.orderId}"/>

                <#if order.paymentState == 'false' && springSecurity.isClient>
                    <div class="submitMore">
                        <label>
                            <input type="submit" value="<@spring.message "clientAgent.orderForm.submit.pay"/>">
                        </label>
                    </div>
                </#if>
            </form>

            <form class="remove" method="post" action="remove_order">
                <#if springSecurity.isAgent || springSecurity.isClient>
                    <a href="remove_order?orderId=${order.orderId}">
                        <@spring.message "clientAgent.orderForm.ref.removeOrder"/>
                    </a>
                </#if>
            </form>
        </div>
    </div>
</#macro>