<#import "components/main-panel.ftl" as mainPanel/>
<#import "/spring.ftl" as spring/>
<#import "components/security.ftl" as secutity/>
<#assign param = RequestParameters/>
<!DOCTYPE html>
<html>
<head>
    <@mainPanel.page>
        <#include "../css/tour-overview-style.css"/>
    </@mainPanel.page>
    <title>${tourName!param.tourName!}</title>
</head>
<body>
<div class="contentForm">
    <div class="contentContainer">
        <form method="post" action="change_tour_name" id="change_tour_name">
            <input type="hidden" name="id" value="${id!(tourInstance.id)!}"/>
            <input type="hidden" name="tourName" value="${tourName!(tourInstance.tourName)!}"/>
            <input type="hidden" name="arrivalCountry" value="${arrivalCountry!(tourInstance.arrivalCountry)!}"/>
            <input type="hidden" name="arrivalCity" value="${arrivalCity!(tourInstance.arrivalCity)!}"/>
            <input type="hidden" name="departureCity" value="${departureCity!(tourInstance.departureCity)!}"/>
            <input type="hidden" name="departureDate" value="${(departureDate?long?c)!(tourInstance.departureDate?long?c)!}"/>
            <input type="hidden" name="arrivalDate" value="${(arrivalDate?long?c)!(tourInstance.arrivalDate?long?c)!}"/>
            <input type="hidden" name="hotel" value="${hotel!(tourInstance.hotel)!}"/>
            <input type="hidden" name="nutrition" value="${nutrition!(tourInstance.nutrition)!}"/>
            <input type="hidden" name="adultsNumber" value="${adultsNumber!(tourInstance.adultsNumber?int?c)!}"/>
            <input type="hidden" name="childrenNumber" value="${childrenNumber!(tourInstance.childrenNumber?int?c)!}"/>
            <input type="hidden" name="price" value="${(price?long?c)!(tourInstance.price?long?c)!}"/>
            <input type="hidden" name="status" value="${status!(tourInstance.status)!}"/>
            <div class="contentItem">
                <div class="tourName">
                   ${tourName!(tourInstance.tourName)!}
                </div>

                <#if (secutity.isAdmin)!false>
                    <a id="changeTourNameMsg" onclick="document.getElementById('changeTourNameMsg').hidden=true;
                    document.getElementById('changeTourNameDiv').hidden=false;
                    document.getElementById('newTourName').required=true">
                        <@spring.message "admin.change"/>
                    </a>
                    <div hidden id="changeTourNameDiv">
                        <a id="closeTourName" onclick="document.getElementById('changeTourNameMsg').hidden=false;
                    document.getElementById('changeTourNameDiv').hidden=true;
                    document.getElementById('newTourName').required=false">
                            <@spring.message "admin.close"/>
                        </a>

                        <div id="changeTourName">
                            <label>
                                <input class="uui-form-element small" type="text" id="newTourName" name="newTourName"
                                       pattern="^[\sa-zA-Z.,_%+-]{1,40}$">
                                <input class="uui-button" type="submit" value="<@spring.message "admin.changeTourName"/>"
                                       onclick="document.getElementById('change_tour_name').disabled=false">
                            </label>
                        </div>
                    </div>
                    ${errorTourNameExistsMessage!}
                </#if>
            </div>
        </form>

        <form method="post" action="change_arrival_country">
            <input type="hidden" name="id" value="${id!(tourInstance.id)!}"/>
            <input type="hidden" name="tourName" value="${tourName!(tourInstance.tourName)!}"/>
            <input type="hidden" name="arrivalCountry" value="${arrivalCountry!(tourInstance.arrivalCountry)!}"/>
            <input type="hidden" name="arrivalCity" value="${arrivalCity!(tourInstance.arrivalCity)!}"/>
            <input type="hidden" name="departureCity" value="${departureCity!(tourInstance.departureCity)!}"/>
            <input type="hidden" name="departureDate" value="${(departureDate?long?c)!(tourInstance.departureDate?long?c)!}"/>
            <input type="hidden" name="arrivalDate" value="${(arrivalDate?long?c)!(tourInstance.arrivalDate?long?c)!}"/>
            <input type="hidden" name="hotel" value="${hotel!(tourInstance.hotel)!}"/>
            <input type="hidden" name="nutrition" value="${nutrition!(tourInstance.nutrition)!}"/>
            <input type="hidden" name="adultsNumber" value="${adultsNumber!(tourInstance.adultsNumber?int?c)!}"/>
            <input type="hidden" name="childrenNumber" value="${childrenNumber!(tourInstance.childrenNumber?int?c)!}"/>
            <input type="hidden" name="price" value="${(price?long?c)!(tourInstance.price?long?c)!}"/>
            <input type="hidden" name="status" value="${status!(tourInstance.status)!}"/>
            <div class="contentItem">
                <div class="arrivalCountry">
                    <div class="itemMsg">
                        <@spring.message "common.message.arrivalCountry"/>
                    </div>
                    ${arrivalCountry!(tourInstance.arrivalCountry)!}
                </div>

                <#if (secutity.isAdmin)!false>
                    <a id="changeArrivalCountryMsg" onclick="document.getElementById('changeArrivalCountryMsg').hidden=true;
                    document.getElementById('changeArrivalCountryDiv').hidden=false;
                    document.getElementById('newArrivalCountry').required=true">
                        <@spring.message "admin.change"/>
                    </a>
                    <div hidden id="changeArrivalCountryDiv">
                        <a id="closeArrivalCountry" onclick="document.getElementById('changeArrivalCountryMsg').hidden=false;
                    document.getElementById('changeArrivalCountryDiv').hidden=true;
                    document.getElementById('newArrivalCountry').required=false">
                            <@spring.message "admin.close"/>
                        </a>

                        <div id="changeArrivalCountry">
                            <label>
                                <input class="uui-form-element small" type="text" id="newArrivalCountry" name="newArrivalCountry"
                                       pattern="^[\sa-zA-Z.,_%+-]{1,40}$">
                                <input class="uui-button" type="submit" value="<@spring.message "admin.changeArrivalCountry"/>"
                                       onclick="document.getElementById('change_arrival_country').disabled=false">
                            </label>
                        </div>
                    </div>
                </#if>
            </div>
        </form>

        <form method="post" action="change_arrival_city">
           <input type="hidden" name="id" value="${id!(tourInstance.id)!}"/>
            <input type="hidden" name="tourName" value="${tourName!(tourInstance.tourName)!}"/>
            <input type="hidden" name="arrivalCountry" value="${arrivalCountry!(tourInstance.arrivalCountry)!}"/>
            <input type="hidden" name="arrivalCity" value="${arrivalCity!(tourInstance.arrivalCity)!}"/>
            <input type="hidden" name="departureCity" value="${departureCity!(tourInstance.departureCity)!}"/>
            <input type="hidden" name="departureDate" value="${(departureDate?long?c)!(tourInstance.departureDate?long?c)!}"/>
            <input type="hidden" name="arrivalDate" value="${(arrivalDate?long?c)!(tourInstance.arrivalDate?long?c)!}"/>
            <input type="hidden" name="hotel" value="${hotel!(tourInstance.hotel)!}"/>
            <input type="hidden" name="nutrition" value="${nutrition!(tourInstance.nutrition)!}"/>
            <input type="hidden" name="adultsNumber" value="${adultsNumber!(tourInstance.adultsNumber?int?c)!}"/>
            <input type="hidden" name="childrenNumber" value="${childrenNumber!(tourInstance.childrenNumber?int?c)!}"/>
            <input type="hidden" name="price" value="${(price?long?c)!(tourInstance.price?long?c)!}"/>
            <input type="hidden" name="status" value="${status!(tourInstance.status)!}"/>
            <div class="contentItem">
                <div class="arrivalCity">
                    <div class="itemMsg">
                        <@spring.message "common.message.arrivalCity"/>
                    </div>
                    ${arrivalCity!(tourInstance.arrivalCity)!}
                </div>

                <#if (secutity.isAdmin)!false>
                    <a id="changeArrivalCityMsg" onclick="document.getElementById('changeArrivalCityMsg').hidden=true;
                    document.getElementById('changeArrivalCityDiv').hidden=false;
                    document.getElementById('newArrivalCity').required=true">
                        <@spring.message "admin.change"/>
                    </a>
                    <div hidden id="changeArrivalCityDiv">
                        <a id="closeArrivalCity" onclick="document.getElementById('changeArrivalCityMsg').hidden=false;
                    document.getElementById('changeArrivalCityDiv').hidden=true;
                    document.getElementById('newArrivalCity').required=false">
                            <@spring.message "admin.close"/>
                        </a>

                        <div id="changeArrivalCity">
                            <label>
                                <input class="uui-form-element small"  type="text" id="newArrivalCity" name="newArrivalCity"
                                       pattern="^[\sa-zA-Z.,_%+-]{1,40}$">
                                <input class="uui-button" type="submit" value="<@spring.message "admin.changeArrivalCity"/>"
                                       onclick="document.getElementById('change_arrival_city').disabled=false">
                            </label>
                        </div>
                    </div>
                </#if>
            </div>
        </form>


        <form method="post" action="change_departure_city">
            <input type="hidden" name="id" value="${id!(tourInstance.id)!}"/>
            <input type="hidden" name="tourName" value="${tourName!(tourInstance.tourName)!}"/>
            <input type="hidden" name="arrivalCountry" value="${arrivalCountry!(tourInstance.arrivalCountry)!}"/>
            <input type="hidden" name="arrivalCity" value="${arrivalCity!(tourInstance.arrivalCity)!}"/>
            <input type="hidden" name="departureCity" value="${departureCity!(tourInstance.departureCity)!}"/>
            <input type="hidden" name="departureDate" value="${(departureDate?long?c)!(tourInstance.departureDate?long?c)!}"/>
            <input type="hidden" name="arrivalDate" value="${(arrivalDate?long?c)!(tourInstance.arrivalDate?long?c)!}"/>
            <input type="hidden" name="hotel" value="${hotel!(tourInstance.hotel)!}"/>
            <input type="hidden" name="nutrition" value="${nutrition!(tourInstance.nutrition)!}"/>
            <input type="hidden" name="adultsNumber" value="${adultsNumber!(tourInstance.adultsNumber?int?c)!}"/>
            <input type="hidden" name="childrenNumber" value="${childrenNumber!(tourInstance.childrenNumber?int?c)!}"/>
            <input type="hidden" name="price" value="${(price?long?c)!(tourInstance.price?long?c)!}"/>
            <input type="hidden" name="status" value="${status!(tourInstance.status)!}"/>
            <div class="contentItem">
                <div class="departureCity">
                    <div class="itemMsg">
                        <@spring.message "common.message.departureCity"/>
                    </div>
                    ${departureCity!(tourInstance.departureCity)!}
                </div>

                <#if (secutity.isAdmin)!false>
                    <a id="changeDepartureCityMsg" onclick="document.getElementById('changeDepartureCityMsg').hidden=true;
                    document.getElementById('changeDepartureCityDiv').hidden=false;
                    document.getElementById('newDepartureCity').required=true">
                        <@spring.message "admin.change"/>
                    </a>
                    <div hidden
                         id="changeDepartureCityDiv">
                        <a id="closeDepartureCity" onclick="document.getElementById('changeDepartureCityMsg').hidden=false;
                    document.getElementById('changeDepartureCityDiv').hidden=true;
                    document.getElementById('newDepartureCity').required=false">
                            <@spring.message "admin.close"/>
                        </a>

                        <div id="changeDepartureCity">
                            <label>
                                <input class="uui-form-element small" type="text" id="newDepartureCity" name="newDepartureCity"
                                       pattern="^[\sa-zA-Z.,_%+-]{1,40}$">
                                <input class="uui-button" type="submit" value="<@spring.message "admin.changeDepartureCity"/>"
                                       onclick="document.getElementById('change_departure_city').disabled=false">
                            </label>
                        </div>
                    </div>
                </#if>
            </div>
        </form>

        <form method="post" action="change_departure_date">
            <input type="hidden" name="id" value="${id!(tourInstance.id)!}"/>
            <input type="hidden" name="tourName" value="${tourName!(tourInstance.tourName)!}"/>
            <input type="hidden" name="arrivalCountry" value="${arrivalCountry!(tourInstance.arrivalCountry)!}"/>
            <input type="hidden" name="arrivalCity" value="${arrivalCity!(tourInstance.arrivalCity)!}"/>
            <input type="hidden" name="departureCity" value="${departureCity!(tourInstance.departureCity)!}"/>
            <input type="hidden" name="departureDate" value="${(departureDate?long?c)!(tourInstance.departureDate?long?c)!}"/>
            <input type="hidden" name="arrivalDate" value="${(arrivalDate?long?c)!(tourInstance.arrivalDate?long?c)!}"/>
            <input type="hidden" name="hotel" value="${hotel!(tourInstance.hotel)!}"/>
            <input type="hidden" name="nutrition" value="${nutrition!(tourInstance.nutrition)!}"/>
            <input type="hidden" name="adultsNumber" value="${adultsNumber!(tourInstance.adultsNumber?int?c)!}"/>
            <input type="hidden" name="childrenNumber" value="${childrenNumber!(tourInstance.childrenNumber?int?c)!}"/>
            <input type="hidden" name="price" value="${(price?long?c)!(tourInstance.price?long?c)!}"/>
            <input type="hidden" name="status" value="${status!(tourInstance.status)!}"/>
            <div class="contentItem">
                <div class="departureDate">
                    <div class="itemMsg">
                        <@spring.message "common.message.departureDate"/>
                    </div>
                    ${(tourInstance.departureDateString())!}
                </div>

                <#if (secutity.isAdmin)!false>
                    <a id="changeDepartureDateMsg" onclick="document.getElementById('changeDepartureDateMsg').hidden=true;
                    document.getElementById('changeDepartureDateDiv').hidden=false;
                    document.getElementById('newDepartureDate').required=true">
                        <@spring.message "admin.change"/>
                    </a>
                    <div hidden id="changeDepartureDateDiv">
                        <a id="closeDepartureDate" onclick="document.getElementById('changeDepartureDateMsg').hidden=false;
                    document.getElementById('changeDepartureDateDiv').hidden=true;
                    document.getElementById('newDepartureDate').required=false">
                            <@spring.message "admin.close"/>
                        </a>

                        <div id="changeDepartureDate">
                            <label>
                                <input class="uui-form-element small" type="date" id="newDepartureDate" name="newDepartureDate">
                                <input class="uui-button" type="submit" value="<@spring.message "admin.changeDepartureDate"/>"
                                       onclick="document.getElementById('change_departure_date').disabled=false">
                            </label>
                        </div>
                    </div>
                </#if>
            </div>
        </form>

        <form method="post" action="change_arrival_date">
            <input type="hidden" name="id" value="${id!(tourInstance.id)!}"/>
            <input type="hidden" name="tourName" value="${tourName!(tourInstance.tourName)!}"/>
            <input type="hidden" name="arrivalCountry" value="${arrivalCountry!(tourInstance.arrivalCountry)!}"/>
            <input type="hidden" name="arrivalCity" value="${arrivalCity!(tourInstance.arrivalCity)!}"/>
            <input type="hidden" name="departureCity" value="${departureCity!(tourInstance.departureCity)!}"/>
            <input type="hidden" name="departureDate" value="${(departureDate?long?c)!(tourInstance.departureDate?long?c)!}"/>
            <input type="hidden" name="arrivalDate" value="${(arrivalDate?long?c)!(tourInstance.arrivalDate?long?c)!}"/>
            <input type="hidden" name="hotel" value="${hotel!(tourInstance.hotel)!}"/>
            <input type="hidden" name="nutrition" value="${nutrition!(tourInstance.nutrition)!}"/>
            <input type="hidden" name="adultsNumber" value="${adultsNumber!(tourInstance.adultsNumber?int?c)!}"/>
            <input type="hidden" name="childrenNumber" value="${childrenNumber!(tourInstance.childrenNumber?int?c)!}"/>
            <input type="hidden" name="price" value="${(price?long?c)!(tourInstance.price?long?c)!}"/>
            <input type="hidden" name="status" value="${status!(tourInstance.status)!}"/>
            <div class="contentItem">
                <div class="arrivalDate">
                    <div class="itemMsg">
                        <@spring.message "common.message.arrivalDate"/>
                    </div>
                    ${(tourInstance.arrivalDateString())!}
                </div>

                <#if (secutity.isAdmin)!false>
                    <a id="changeArrivalDateMsg" onclick="document.getElementById('changeArrivalDateMsg').hidden=true;
                    document.getElementById('changeArrivalDateDiv').hidden=false;
                    document.getElementById('newArrivalDate').required=true">
                        <@spring.message "admin.change"/>
                    </a>
                    <div hidden id="changeArrivalDateDiv">
                        <a id="closeArrivalDate" onclick="document.getElementById('changeArrivalDateMsg').hidden=false;
                    document.getElementById('changeArrivalDateDiv').hidden=true;
                    document.getElementById('newArrivalDate').required=false">
                            <@spring.message "admin.close"/>
                        </a>

                        <div id="changeArrivalDate">
                            <label>
                                <input class="uui-form-element small" type="date" id="newArrivalDate" name="newArrivalDate">
                                <input class="uui-button" type="submit" value="<@spring.message "admin.changeArrivalDate"/>"
                                       onclick="document.getElementById('change_arrival_date').disabled=false">
                            </label>
                        </div>
                    </div>
                    ${errorInvalidDate!}
                </#if>
            </div>
        </form>

        <form method="post" action="change_hotel">
            <input type="hidden" name="id" value="${id!(tourInstance.id)!}"/>
            <input type="hidden" name="tourName" value="${tourName!(tourInstance.tourName)!}"/>
            <input type="hidden" name="arrivalCountry" value="${arrivalCountry!(tourInstance.arrivalCountry)!}"/>
            <input type="hidden" name="arrivalCity" value="${arrivalCity!(tourInstance.arrivalCity)!}"/>
            <input type="hidden" name="departureCity" value="${departureCity!(tourInstance.departureCity)!}"/>
            <input type="hidden" name="departureDate" value="${(departureDate?long?c)!(tourInstance.departureDate?long?c)!}"/>
            <input type="hidden" name="arrivalDate" value="${(arrivalDate?long?c)!(tourInstance.arrivalDate?long?c)!}"/>
            <input type="hidden" name="hotel" value="${hotel!(tourInstance.hotel)!}"/>
            <input type="hidden" name="nutrition" value="${nutrition!(tourInstance.nutrition)!}"/>
            <input type="hidden" name="adultsNumber" value="${adultsNumber!(tourInstance.adultsNumber?int?c)!}"/>
            <input type="hidden" name="childrenNumber" value="${childrenNumber!(tourInstance.childrenNumber?int?c)!}"/>
            <input type="hidden" name="price" value="${(price?long?c)!(tourInstance.price?long?c)!}"/>
            <input type="hidden" name="status" value="${status!(tourInstance.status)!}"/>
            <div class="contentItem">
                <div class="hotel">
                    <div class="itemMsg">
                        <@spring.message "common.message.hotel"/>
                    </div>
                    ${hotel!(tourInstance.hotel)!}
                </div>

                <#if (secutity.isAdmin)!false>
                    <a id="changeHotelMsg" onclick="document.getElementById('changeHotelMsg').hidden=true;
                    document.getElementById('changeHotelDiv').hidden=false;
                    document.getElementById('newHotel').required=true">
                        <@spring.message "admin.change"/>
                    </a>
                    <div hidden id="changeHotelDiv">
                        <a id="closeHotel" onclick="document.getElementById('changeHotelMsg').hidden=false;
                    document.getElementById('changeHotelDiv').hidden=true;
                    document.getElementById('newHotel').required=false">
                            <@spring.message "admin.close"/>
                        </a>

                        <div id="changeHotel">
                            <label>
                                <input class="uui-form-element small" type="text" id="newHotel" name="newHotel" minlength="1" maxlength="40"
                                       pattern="^[\sa-zA-Z.,_%+-]{1,40}$">
                                <input class="uui-button" type="submit" value="<@spring.message "admin.changeHotel"/>"
                                       onclick="document.getElementById('change_hotel').disabled=false">
                            </label>
                        </div>
                    </div>
                </#if>
            </div>
        </form>

        <form method="post" action="change_nutrition">
            <input type="hidden" name="id" value="${id!(tourInstance.id)!}"/>
            <input type="hidden" name="tourName" value="${tourName!(tourInstance.tourName)!}"/>
            <input type="hidden" name="arrivalCountry" value="${arrivalCountry!(tourInstance.arrivalCountry)!}"/>
            <input type="hidden" name="arrivalCity" value="${arrivalCity!(tourInstance.arrivalCity)!}"/>
            <input type="hidden" name="departureCity" value="${departureCity!(tourInstance.departureCity)!}"/>
            <input type="hidden" name="departureDate" value="${(departureDate?long?c)!(tourInstance.departureDate?long?c)!}"/>
            <input type="hidden" name="arrivalDate" value="${(arrivalDate?long?c)!(tourInstance.arrivalDate?long?c)!}"/>
            <input type="hidden" name="hotel" value="${hotel!(tourInstance.hotel)!}"/>
            <input type="hidden" name="nutrition" value="${nutrition!(tourInstance.nutrition)!}"/>
            <input type="hidden" name="adultsNumber" value="${adultsNumber!(tourInstance.adultsNumber?int?c)!}"/>
            <input type="hidden" name="childrenNumber" value="${childrenNumber!(tourInstance.childrenNumber?int?c)!}"/>
            <input type="hidden" name="price" value="${(price?long?c)!(tourInstance.price?long?c)!}"/>
            <input type="hidden" name="status" value="${status!(tourInstance.status)!}"/>
            <div class="contentItem">
                <div class="nutrition">
                    <div class="itemMsg">
                        <@spring.message "common.message.nutrition"/>
                    </div>
                    ${nutrition!(tourInstance.nutrition)!}
                </div>

                <#if (secutity.isAdmin)!false>
                    <a id="changeNutritionMsg" onclick="document.getElementById('changeNutritionMsg').hidden=true;
                    document.getElementById('changeNutritionDiv').hidden=false;
                    document.getElementById('newNutrition').required=true">
                        <@spring.message "admin.change"/>
                    </a>
                    <div hidden id="changeNutritionDiv">
                        <a id="closeNutrition" onclick="document.getElementById('changeNutritionMsg').hidden=false;
                    document.getElementById('changeNutritionDiv').hidden=true;
                    document.getElementById('newNutrition').required=false">
                            <@spring.message "admin.close"/>
                        </a>

                        <div id="changeNutrition">
                            <label>
                                <input class="uui-form-element small" type="text" id="newNutrition" name="newNutrition" minlength="2" maxlength="3"
                                       pattern="^[A-Z]{2,3}[+]?$">
                                <input class="uui-button" type="submit" value="<@spring.message "admin.changeNutrition"/>"
                                       onclick="document.getElementById('change_nutrition').disabled=false">
                            </label>
                        </div>
                    </div>
                </#if>
            </div>
        </form>

        <form method="post" action="change_adults_number">
            <input type="hidden" name="id" value="${id!(tourInstance.id)!}"/>
            <input type="hidden" name="tourName" value="${tourName!(tourInstance.tourName)!}"/>
            <input type="hidden" name="arrivalCountry" value="${arrivalCountry!(tourInstance.arrivalCountry)!}"/>
            <input type="hidden" name="arrivalCity" value="${arrivalCity!(tourInstance.arrivalCity)!}"/>
            <input type="hidden" name="departureCity" value="${departureCity!(tourInstance.departureCity)!}"/>
            <input type="hidden" name="departureDate" value="${(departureDate?long?c)!(tourInstance.departureDate?long?c)!}"/>
            <input type="hidden" name="arrivalDate" value="${(arrivalDate?long?c)!(tourInstance.arrivalDate?long?c)!}"/>
            <input type="hidden" name="hotel" value="${hotel!(tourInstance.hotel)!}"/>
            <input type="hidden" name="nutrition" value="${nutrition!(tourInstance.nutrition)!}"/>
            <input type="hidden" name="adultsNumber" value="${adultsNumber!(tourInstance.adultsNumber?int?c)!}"/>
            <input type="hidden" name="childrenNumber" value="${childrenNumber!(tourInstance.childrenNumber?int?c)!}"/>
            <input type="hidden" name="price" value="${(price?long?c)!(tourInstance.price?long?c)!}"/>
            <input type="hidden" name="status" value="${status!(tourInstance.status)!}"/>
            <div class="contentItem">
                <div class="adultsNumber">
                    <div class="itemMsg">
                        <@spring.message "common.message.numberOfAdults"/>
                    </div>
                    ${adultsNumber!(tourInstance.adultsNumber?int?c)!}
                </div>

                <#if (secutity.isAdmin)!false>
                    <a id="changeAdultsNumberMsg" onclick="document.getElementById('changeAdultsNumberMsg').hidden=true;
                    document.getElementById('changeAdultsNumberDiv').hidden=false;
                    document.getElementById('newAdultsNumber').required=true">
                        <@spring.message "admin.change"/>
                    </a>
                    <div hidden id="changeAdultsNumberDiv">
                        <a id="closeAdultsNumber" onclick="document.getElementById('changeAdultsNumberMsg').hidden=false;
                    document.getElementById('changeAdultsNumberDiv').hidden=true;
                    document.getElementById('newAdultsNumber').required=false">
                            <@spring.message "admin.close"/>
                        </a>

                        <div id="changeAdultsNumber">
                            <label>
                                <input class="uui-form-element small" type="text" id="newAdultsNumber" name="newAdultsNumber" min="0" max="50"
                                       pattern="^([0-4]?\d|50)$">
                                <input class="uui-button" type="submit" value="<@spring.message "admin.changeAdultsNumber" />"
                                       onclick="document.getElementById('change_adults_number').disabled=false">
                            </label>
                        </div>
                    </div>
                </#if>
            </div>
        </form>

        <form method="post" action="change_children_number">
            <input type="hidden" name="id" value="${id!(tourInstance.id)!}"/>
            <input type="hidden" name="tourName" value="${tourName!(tourInstance.tourName)!}"/>
            <input type="hidden" name="arrivalCountry" value="${arrivalCountry!(tourInstance.arrivalCountry)!}"/>
            <input type="hidden" name="arrivalCity" value="${arrivalCity!(tourInstance.arrivalCity)!}"/>
            <input type="hidden" name="departureCity" value="${departureCity!(tourInstance.departureCity)!}"/>
            <input type="hidden" name="departureDate" value="${(departureDate?long?c)!(tourInstance.departureDate?long?c)!}"/>
            <input type="hidden" name="arrivalDate" value="${(arrivalDate?long?c)!(tourInstance.arrivalDate?long?c)!}"/>
            <input type="hidden" name="hotel" value="${hotel!(tourInstance.hotel)!}"/>
            <input type="hidden" name="nutrition" value="${nutrition!(tourInstance.nutrition)!}"/>
            <input type="hidden" name="adultsNumber" value="${adultsNumber!(tourInstance.adultsNumber?int?c)!}"/>
            <input type="hidden" name="childrenNumber" value="${childrenNumber!(tourInstance.childrenNumber?int?c)!}"/>
            <input type="hidden" name="price" value="${(price?long?c)!(tourInstance.price?long?c)!}"/>
            <input type="hidden" name="status" value="${status!(tourInstance.status)!}"/>
            <div class="contentItem">
                <div class="childrenNumber">
                    <div class="itemMsg">
                        <@spring.message "common.message.numberOfChildren"/>
                    </div>
                    ${childrenNumber!(tourInstance.childrenNumber?int?c)!}
                </div>

                <#if (secutity.isAdmin)!false>
                    <a id="changeChildrenNumberMsg" onclick="document.getElementById('changeChildrenNumberMsg').hidden=true;
                    document.getElementById('changeChildrenNumberDiv').hidden=false;
                    document.getElementById('newChildrenNumber').required=true">
                        <@spring.message "admin.change"/>
                    </a>
                    <div hidden id="changeChildrenNumberDiv">
                        <a id="closeChildrenNumber" onclick="document.getElementById('changeChildrenNumberMsg').hidden=false;
                    document.getElementById('changeChildrenNumberDiv').hidden=true;
                    document.getElementById('newChildrenNumber').required=false">
                            <@spring.message "admin.close"/>
                        </a>

                        <div id="changeChildrenNumber">
                            <label>
                                <input class="uui-form-element small" type="text" id="newChildrenNumber" name="newChildrenNumber" min="0" max="50"
                                       pattern="^([0-4]?\d|50)$">
                                <input class="uui-button" type="submit" value="<@spring.message "admin.changeChildrenNumber" />"
                                       onclick="document.getElementById('change_children_number').disabled=false">
                            </label>
                        </div>
                    </div>
                </#if>
            </div>
        </form>

        <form method="post" action="change_price">
            <input type="hidden" name="id" value="${id!(tourInstance.id)!}"/>
            <input type="hidden" name="tourName" value="${tourName!(tourInstance.tourName)!}"/>
            <input type="hidden" name="arrivalCountry" value="${arrivalCountry!(tourInstance.arrivalCountry)!}"/>
            <input type="hidden" name="arrivalCity" value="${arrivalCity!(tourInstance.arrivalCity)!}"/>
            <input type="hidden" name="departureCity" value="${departureCity!(tourInstance.departureCity)!}"/>
            <input type="hidden" name="departureDate" value="${(departureDate?long?c)!(tourInstance.departureDate?long?c)!}"/>
            <input type="hidden" name="arrivalDate" value="${(arrivalDate?long?c)!(tourInstance.arrivalDate?long?c)!}"/>
            <input type="hidden" name="hotel" value="${hotel!(tourInstance.hotel)!}"/>
            <input type="hidden" name="nutrition" value="${nutrition!(tourInstance.nutrition)!}"/>
            <input type="hidden" name="adultsNumber" value="${adultsNumber!(tourInstance.adultsNumber?int?c)!}"/>
            <input type="hidden" name="childrenNumber" value="${childrenNumber!(tourInstance.childrenNumber?int?c)!}"/>
            <input type="hidden" name="price" value="${(price?long?c)!(tourInstance.price?long?c)!}"/>
            <input type="hidden" name="status" value="${status!(tourInstance.status)!}"/>
            <div class="contentItem">
                <div class="price">
                    <div class="itemMsg">
                        <@spring.message "common.message.price"/>
                    </div>
                    ${(price?long?c)!(tourInstance.price?long?c)!}$
                </div>

                <#if (secutity.isAdmin)!false>
                    <a id="changePriceMsg" onclick="document.getElementById('changePriceMsg').hidden=true;
                    document.getElementById('changePriceDiv').hidden=false;
                    document.getElementById('newPrice').required=true">
                        <@spring.message "admin.change"/>
                    </a>
                    <div hidden id="changePriceDiv">
                        <a id="closePrice" onclick="document.getElementById('changePriceMsg').hidden=false;
                    document.getElementById('changePriceDiv').hidden=true;
                    document.getElementById('newPrice').required=false">
                            <@spring.message "admin.close"/>
                        </a>

                        <div id="changePrice">
                            <label>
                                <input class="uui-form-element small" type="text" id="newPrice" name="newPrice" min="1" max="200000"
                                       pattern="^([1]?\d?\d?\d?\d?\d([.]\d\d)?|200000([.]\d\d)?)$">
                                <input class="uui-button" type="submit" value="<@spring.message "admin.changePrice" />"
                                       onclick="document.getElementById('change_price').disabled=false">
                            </label>
                        </div>
                    </div>
                </#if>
            </div>
        </form>

        <form method="post" action="change_status">
            <input type="hidden" name="id" value="${id!(tourInstance.id)!}"/>
            <input type="hidden" name="tourName" value="${tourName!(tourInstance.tourName)!}"/>
            <input type="hidden" name="arrivalCountry" value="${arrivalCountry!(tourInstance.arrivalCountry)!}"/>
            <input type="hidden" name="arrivalCity" value="${arrivalCity!(tourInstance.arrivalCity)!}"/>
            <input type="hidden" name="departureCity" value="${departureCity!(tourInstance.departureCity)!}"/>
            <input type="hidden" name="departureDate" value="${(departureDate?long?c)!(tourInstance.departureDate?long?c)!}"/>
            <input type="hidden" name="arrivalDate" value="${(arrivalDate?long?c)!(tourInstance.arrivalDate?long?c)!}"/>
            <input type="hidden" name="hotel" value="${hotel!(tourInstance.hotel)!}"/>
            <input type="hidden" name="nutrition" value="${nutrition!(tourInstance.nutrition)!}"/>
            <input type="hidden" name="adultsNumber" value="${adultsNumber!(tourInstance.adultsNumber?int?c)!}"/>
            <input type="hidden" name="childrenNumber" value="${childrenNumber!(tourInstance.childrenNumber?int?c)!}"/>
            <input type="hidden" name="price" value="${(price?long?c)!(tourInstance.price?long?c)!}"/>
            <input type="hidden" name="status" value="${status!(tourInstance.status)!}"/>
            <#if (secutity.isAdmin)!false>
                <div class="contentItem">
                    <#if ((status!(tourInstance.status)!"NOT_AVAILABLE") == "AVAILABLE")>
                        <input class="uui-button raspberry" type="submit" value="<@spring.message "admin.changeStatusNotAvailable"/>"
                               onclick="document.getElementById('change_status').disabled=false">
                    <#else>
                        <input class="uui-button lime-green" type="submit" value="<@spring.message "admin.changeStatusAvailable"/>"
                               onclick="document.getElementById('change_status').disabled=false">
                    </#if>
                </div>
            </#if>
        </form>

        <form method="post" action="to_tours">
            <div class="contentItem">
                <label>
                    <input  class="uui-button raspberry" type="submit" name="back" value="<@spring.message "common.submit.back"/>">
                </label>
            </div>
        </form>

        <form method="post" action="to_tickets" id="to_tickets">
            <input type="hidden" name="id" value="${id!(tourInstance.id)!}"/>
            <input type="hidden" name="tourName" value="${tourName!(tourInstance.tourName)!}"/>
            <input type="hidden" name="arrivalCountry" value="${arrivalCountry!(tourInstance.arrivalCountry)!}"/>
            <input type="hidden" name="arrivalCity" value="${arrivalCity!(tourInstance.arrivalCity)!}"/>
            <input type="hidden" name="departureCity" value="${departureCity!(tourInstance.departureCity)!}"/>
            <input type="hidden" name="departureDate" value="${(departureDate?long?c)!(tourInstance.departureDate?long?c)!}"/>
            <input type="hidden" name="arrivalDate" value="${(arrivalDate?long?c)!(tourInstance.arrivalDate?long?c)!}"/>
            <input type="hidden" name="hotel" value="${hotel!(tourInstance.hotel)!}"/>
            <input type="hidden" name="nutrition" value="${nutrition!(tourInstance.nutrition)!}"/>
            <input type="hidden" name="adultsNumber" value="${adultsNumber!(tourInstance.adultsNumber?int?c)!}"/>
            <input type="hidden" name="childrenNumber" value="${childrenNumber!(tourInstance.childrenNumber?int?c)!}"/>
            <input type="hidden" name="price" value="${(price?long?c)!(tourInstance.price?long?c)!}"/>
            <input type="hidden" name="status" value="${status!(tourInstance.status)!}"/>
            <div class="contentItem">
                <#if !((secutity.isAdmin)!false)>
                    <label>
                        <input class="uui-button lime-green" type="submit" name="ticketSelection" value="<@spring.message "notAdmin.submit.ticketSelection"/>">
                    </label>
                </#if>
            </div>
        </form>

    </div>
</div>
</body>
<#import "components/timestamp.ftl" as timestamp/>
<@timestamp.page/>
</html>