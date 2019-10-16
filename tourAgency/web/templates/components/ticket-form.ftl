<#macro page ticket>
    <#import "security.ftl" as springSecurity/>
    <#import "/spring.ftl" as spring/>

    <div class="ticketItem">
        <div class="MinInf">
            <div class="flightNumber_flightPlace">
                <div class="flightNumber">
                    <@spring.message "common.message.flightNumber"/>
                    ${ticket.flightNumber}
                </div>

                <div class="flightPlace">
                    <c:out value="${ticket.departureCity} - ${ticket.arrivalCity}"/>
                </div>
            </div>

            <div class="flightDate">
                <c:out value="${ticket.departureDateTime} - ${ticket.arrivalDateTime}"/>
            </div>
        </div>

        <div class="departureCity">
            <div>


                <#if !springSecurity.isAdmin>
                    <div class="submitChoose">
                        <#if !springSecurity.isAnonymous>
                            <form method="post" action="to_booking">
                                <input type="hidden" name="ticketId" value="${ticket.ticketId}"/>
                                <input type="hidden" name="departureDateTime" value="${ticket.departureDateTime}"/>
                                <input type="hidden" name="arrivalDateTime" value="${ticket.arrivalDateTime}"/>
                                <input type="hidden" name="flightNumber" value="${ticket.flightNumber}"/>
                                <input type="hidden" name="arrivalCity" value="${ticket.arrivalCity}"/>
                                <input type="hidden" name="departureCity" value="${ticket.departureCity}"/>

                                <#if !(ticket.tourId??)>
                                    <input type="hidden" name="tourId" value="${ticket.tourId}"/>
                                    <input type="hidden" name="tourName" value="${ticket.tourName}"/>
                                    <input type="hidden" name="arrivalCountry" value="${ticket.arrivalCountry}"/>
                                    <input type="hidden" name="departureDate" value="${ticket.departureDate}"/>
                                    <input type="hidden" name="arrivalDate" value="${ticket.arrivalDate}"/>
                                    <input type="hidden" name="hotel" value="${ticket.hotel}"/>
                                    <input type="hidden" name="nutrition" value="${ticket.nutrition}"/>
                                    <input type="hidden" name="adultsNumber" value="${ticket.adultsNumber}"/>
                                    <input type="hidden" name="childrenNumber" value="${ticket.childrenNumber}"/>
                                    <input type="hidden" name="price" value="${ticket.price}"/>
                                </#if>
                            </form>
                        </#if>
                        <#if springSecurity.isAnonymous>
                            <form method="post" action="to_login">
                                <input type="hidden" name="ticketId" value="${ticket.ticketId}"/>
                                <input type="hidden" name="departureDateTime" value="${ticket.departureDateTime}"/>
                                <input type="hidden" name="arrivalDateTime" value="${ticket.arrivalDateTime}"/>
                                <input type="hidden" name="flightNumber" value="${ticket.flightNumber}"/>
                                <input type="hidden" name="arrivalCity" value="${ticket.arrivalCity}"/>
                                <input type="hidden" name="departureCity" value="${ticket.departureCity}"/>

                                <#if !(ticket.tourId??)>
                                    <input type="hidden" name="tourId" value="${ticket.tourId}"/>
                                    <input type="hidden" name="tourName" value="${ticket.tourName}"/>
                                    <input type="hidden" name="arrivalCountry" value="${ticket.arrivalCountry}"/>
                                    <input type="hidden" name="departureDate" value="${ticket.departureDate}"/>
                                    <input type="hidden" name="arrivalDate" value="${ticket.arrivalDate}"/>
                                    <input type="hidden" name="hotel" value="${ticket.hotel}"/>
                                    <input type="hidden" name="nutrition" value="${ticket.nutrition}"/>
                                    <input type="hidden" name="adultsNumber" value="${ticket.adultsNumber}"/>
                                    <input type="hidden" name="childrenNumber" value="${ticket.childrenNumber}"/>
                                    <input type="hidden" name="price" value="${ticket.price}"/>
                                </#if>
                                <input type="hidden" name="notAuthorized"
                                       value="<@spring.message "guest.attrValue.notAuthorized"/>"/>
                            </form>
                        </#if>

                        <label>
                            <input type="submit" value="<@spring.message "common.submit.choose"/>">
                        </label>
                    </div>
                </#if>
            </div>

        </div>
    </div>
</#macro>