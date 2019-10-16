<#macro page tour>
    <#import "security.ftl" as springSecurity/>
    <#import "/spring.ftl" as spring/>
    <div class="tourItem">
        <div class="MinInf">
            <div class="TourName"
            <#if tour.status == 'NOT_AVAILABLE'> style="color: red"</#if>
            ${tour.tourName}
        </div>

        <div class="arrivalPlace">
            ${tour.arrivalCountry}, ${tour.arrivalCity}
        </div>
    </div>

    <div class="departureCity">
        <form method="POST" action="to_tour_overview">
            <input type="hidden" name="tourName" value="${tour.tourName}"/>
            <input type="hidden" name="tourId" value="${tour.tourId}"/>
            <input type="hidden" name="arrivalCountry" value="${tour.arrivalCountry}"/>
            <input type="hidden" name="arrivalCity" value="${tour.arrivalCity}"/>
            <input type="hidden" name="departureCity" value="${tour.departureCity}"/>
            <input type="hidden" name="departureDate" value="${tour.departureDate}"/>
            <input type="hidden" name="arrivalDate" value="${tour.arrivalDate}"/>
            <input type="hidden" name="hotel" value="${tour.hotel}"/>
            <input type="hidden" name="nutrition" value="${tour.nutrition}"/>
            <input type="hidden" name="adultsNumber" value="${tour.adultsNumber}"/>
            <input type="hidden" name="childrenNumber" value="${tour.childrenNumber}"/>
            <input type="hidden" name="price" value="${tour.price}"/>
            <input type="hidden" name="status" value="${tour.status}"/>

            <fmt:message key="common.tourForm.submit.from" var="from"/>
            <div>
                <c:out value="${from} ${tour.departureCity}"/>
            </div>
            <fmt:message key="common.tourForm.submit.more" var="more"/>
            <div class="submitMore">
                <label>
                    <input type="submit" value="${more}">
                </label>
            </div>
        </form>

    </div>
    </div>
</#macro>