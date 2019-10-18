<#macro page ticket>
    <#import "security.ftl" as springSecurity/>
    <#import "/spring.ftl" as spring/>
    <#assign param = RequestParameters/>

    <div class="ticketItem">
        <div class="MinInf">
            <div class="flightNumber_flightPlace">
                <div class="flightNumber">
                    <@spring.message "common.message.flightNumber"/>
                    ${ticket.flightNumber!}
                </div>

                <div class="flightPlace">
                    ${ticket.departureCity!} - ${ticket.arrivalCity!}
                </div>
            </div>

            <div class="flightDate">
                ${ticket.departureDateTime!} - ${ticket.arrivalDateTime!}
            </div>
        </div>

        <div class="departureCity">
            <div>


                <#if !(springSecurity.isAdmin)!false>
                    <div class="submitChoose">
                            <form method="post" action="to_booking">
                                <input type="hidden" name="ticketId" value="${ticket.id!}"/>
                                <input type="hidden" name="departureDateTime" value="${ticket.departureDateTime!}"/>
                                <input type="hidden" name="arrivalDateTime" value="${ticket.arrivalDateTime!}"/>
                                <input type="hidden" name="flightNumber" value="${ticket.flightNumber!}"/>
                                <input type="hidden" name="arrivalCity" value="${ticket.arrivalCity!}"/>
                                <input type="hidden" name="departureCity" value="${ticket.departureCity!}"/>

                                <#if !(ticket.tourId??)>
                                <input type="hidden" name="tourId" value="${param.tourId!}"/>
                                <input type="hidden" name="tourName" value="${param.tourName!}"/>
                                <input type="hidden" name="arrivalCountry" value="${param.arrivalCountry!}"/>
                                <input type="hidden" name="departureDate" value="${param.departureDate!}"/>
                                <input type="hidden" name="arrivalDate" value="${param.arrivalDate!}"/>
                                <input type="hidden" name="hotel" value="${param.hotel!}"/>
                                <input type="hidden" name="nutrition" value="${param.nutrition!}"/>
                                <input type="hidden" name="adultsNumber" value="${param.adultsNumber!}"/>
                                <input type="hidden" name="childrenNumber" value="${param.childrenNumber!}"/>
                                <input type="hidden" name="price" value="${param.price!}"/>
                                </#if>
                                <label>
                                    <input class="uui-button transparent blue" type="submit" value="<@spring.message "common.submit.choose"/>">
                                </label>
                            </form>
                    </div>
                </#if>
            </div>

        </div>
    </div>
</#macro>