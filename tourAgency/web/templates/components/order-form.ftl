<#macro page order>
    <#import "security.ftl" as springSecurity/>
    <#import "/spring.ftl" as spring/>
    <div class="tourItem">

        <div class="MinInf">
            <div class="TourName"
                    <#if order.paymentState!>
                        style="color: green"
                    <#else>
                        style="color: orange"
                    </#if>
            >
                <div class="MinInfMsg">
                    <@spring.message "common.message.tourName"/>
                </div>
                <#if ((order.tour.tourName!"not defined") == "not defined")>
                    --
                <#else>
                    ${order.tour.tourName!}
                </#if>
            </div>

            <div class="ticketInf">
                <div class="MinInfMsg">
                    <@spring.message "common.message.ticketInf"/>
                </div>
                <#if ((order.getTicketId()!0) != 0)>
                    <div class="ticketPlace">
                        ${order.ticket.departureCity!} - ${order.ticket.arrivalCity!}
                    </div>
                    <div class="ticketDate">
                        ${order.ticket.getArrivalDateTime()!} - ${order.ticket.getDepartureDateTime()!}
                    </div>
                <#elseif ((order.getTicketId()!0) == 0)>
                    --
                </#if>
            </div>

            <div class="clientInf">
                <div class="MinInfMsg">
                    <@spring.message "common.message.clientInf"/>
                </div>
                <div>
                    ${order.client.name!}
                </div>
                <div>
                    ${order.client.email!}
                </div>
            </div>


            <div class="agentInf">
                <div class="MinInfMsg">
                    <@spring.message "common.message.agentInf"/>
                </div>
                <#if ((order.agentName!"not defined") != "not defined")>
                    <div>
                        ${order.agent.name!}
                    </div>
                    <div>
                        ${order.agent.email!}
                    </div>
                <#else>
                    --
                </#if>
            </div>
        </div>

        <div class="orderMore">
            <form method="POST" action="pay_order">
                <input type="hidden" name="orderId" value="${order.id!1}"/>

                <#if (!(order.paymentState!false) && (springSecurity.isClient!false))>
                    <div class="submitMore">
                        <label>
                            <input type="submit" value="<@spring.message "clientAgent.orderForm.submit.pay"/>">
                        </label>
                    </div>
                </#if>
            </form>

            <form class="remove" method="post" action="remove_order">
                <#if ((springSecurity.isAgent!false) || (springSecurity.isClient!false))>
                    <a href="remove_order?orderId=${order.id!1}">
                        <@spring.message "clientAgent.orderForm.ref.removeOrder"/>
                    </a>
                </#if>
            </form>
        </div>
    </div>
</#macro>