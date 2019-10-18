<#import "components/main-panel.ftl" as mainPanel/>
<#import "/spring.ftl" as spring/>
<#import "components/security.ftl" as secutity/>
<#assign param = RequestParameters/>
<!DOCTYPE html>
<html>
<head>
    <@mainPanel.page>
        <#include "../css/booking-style.css"/>
    </@mainPanel.page>
    <title><@spring.message "common.title.booking"/></title>
</head>
<body>
<form class="contentForm" method="post" action="booking">
        <div class="contentContainer">
            <div class="allInf">
                <div class="tourContent">
                    <#if param.tourId??>
                        <input type="hidden" name="tourId" value="${param.tourId!}"/>
                        <div class="tourName">
                            ${param.tourName!}
                        </div>

                        <div class="contentItem">
                            <div class="itemMsg">
                                <@spring.message "common.message.arrivalCountry"/>
                            </div>
                            ${param.arrivalCountry!}
                        </div>

                        <div class="contentItem">
                            <div class="itemMsg">
                                <@spring.message "common.message.arrivalCity"/>
                            </div>
                            ${param.arrivalCity!}
                        </div>

                        <div class="contentItem">
                            <div class="itemMsg">
                                <@spring.message "common.message.departureCity"/>
                            </div>
                            ${param.departureCity!}
                        </div>

                        <div class="contentItem">
                            <div class="itemMsg">
                                <@spring.message "common.message.departureDate"/>
                            </div>
                            ${param.departureDate!}
                        </div>

                        <div class="contentItem">
                            <div class="itemMsg">
                                <@spring.message "common.message.arrivalDate"/>
                            </div>
                            ${param.arrivalDate!}
                        </div>

                        <div class="contentItem">
                            <div class="itemMsg">
                                <@spring.message "common.message.hotel"/>
                            </div>
                            ${param.hotel!}
                        </div>

                        <div class="contentItem">
                            <div class="itemMsg">
                                <@spring.message "common.message.nutrition"/>
                            </div>
                            ${param.nutrition!}
                                                    </div>

                        <div class="contentItem">
                            <div class="itemMsg">
                                <@spring.message "common.message.numberOfAdults"/>
                            </div>
                            ${param.adultsNumber!}
                        </div>

                        <div class="contentItem">
                            <div class="itemMsg">
                                <@spring.message "common.message.numberOfChildren"/>
                            </div>
                            ${param.childrenNumber!}
                        </div>

                        <div class="contentItem">
                            <div class="itemMsg">
                                <@spring.message "common.message.price"/>
                            </div>
                            ${param.price!}$
                        </div>
                    <#else>
                        <div class="notDefined">
                            <@spring.message "clientAgent.message.notDefined"/>
                        </div>
                    </#if>
                </div>


                <div class="ticketContent">
                    <#if param.ticketId??>
                        <input type="hidden" name="ticketId" value="${param.ticketId!}"/>
                        <div class="flightPlace">
                            ${param.departureCity!} - ${param.arrivalCity!}
                        </div>

                        <div class="contentItem">
                            <div class="itemMsg">
                                <@spring.message "common.message.flightNumber"/>
                            </div>
                            ${param.flightNumber!}
                        </div>

                        <div class="contentItem">
                            ${param.departureDateTime!} - ${param.arrivalDateTime!}
                        </div>
                    <#else>
                        <div class="notDefined">
                            <@spring.message "clientAgent.message.notDefined"/>
                        </div>
                    </#if>
                </div>

            </div>

            <#if (secutity.isAgent)!false>
                <input type="hidden" name="agentId" value="${userId!}">
                <div class="emailInput">
                    <@spring.message "agent.message.inputClientEmail"/>
                    <label>
                        <input type="text" name="clientEmail" required
                               minlength="6" maxlength="100"  pattern="^[a-zA-Z0-9.,_%+-]+@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}$">
                    </label>
                    <br>${errorEmail!}
                </div>
            <#else>
                <input type="hidden" name="clientId" value="${userId!}">
            </#if>

            <label>
                <input class="uui-button blue" type="button" name="ticketSelection" onclick="history.back()" value="<@spring.message "clientAgent.submit.backToTicketSelection"/>">
            </label>

            <label>
                <input class="uui-button lime-green" type="submit" name="confirmReservation" value="<@spring.message "clientAgent.submit.confirmReservation" />">
            </label>
        </div>
</form>
</body>

<#import "components/timestamp.ftl" as timestamp/>
<@timestamp.page/>
</html>
