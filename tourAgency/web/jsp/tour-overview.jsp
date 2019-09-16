<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${not empty tourName ? tourName : param.tourName}" scope="page" var="tourName"/>
<c:set value="${not empty arrivalCountry ? arrivalCountry : param.arrivalCountry}" scope="page"
       var="arrivalCountry"/>
<c:set value="${not empty arrivalCity ? arrivalCity : param.arrivalCity}" scope="page" var="arrivalCity"/>
<c:set value="${not empty departureCity ? departureCity : param.departureCity}" scope="page" var="departureCity"/>
<c:set value="${not empty departureDate ? departureDate : param.departureDate}" scope="page" var="departureDate"/>
<c:set value="${not empty arrivalDate ? arrivalDate : param.arrivalDate}" scope="page" var="arrivalDate"/>
<c:set value="${not empty hotel ? hotel : param.hotel}" scope="page" var="hotel"/>
<c:set value="${not empty nutrition ? nutrition : param.nutrition}" scope="page" var="nutrition"/>
<c:set value="${not empty adultsNumber ? adultsNumber : param.adultsNumber}" scope="page" var="adultsNumber"/>
<c:set value="${not empty childrenNumber ? childrenNumber : param.childrenNumber}" scope="page" var="childrenNumber"/>
<c:set value="${not empty price ? price : param.price}" scope="page" var="price"/>
<c:set value="${not empty status ? status : param.status}" scope="page" var="status"/>

<!DOCTYPE html>
<head>
    <%@include file="components/main-panel.jsp" %>
    <style>
        <%@include file="../css/tour-overview-style.css"%>
    </style>
    <fmt:setBundle basename="jsp/tour-overview"/>
    <title><c:out value="${tourName}"/></title>
</head>
<body>
<div class="contentForm">
    <form class="contentContainer" method="post" action="controller">
        <input type="hidden" name="tourId" value="${param.tourId}"/>
        <input type="hidden" name="tourName" value="${tourName}"/>
        <input type="hidden" name="arrivalCountry" value="${arrivalCountry}"/>
        <input type="hidden" name="arrivalCity" value="${arrivalCity}"/>
        <input type="hidden" name="departureCity" value="${departureCity}"/>
        <input type="hidden" name="departureDate" value="${departureDate}"/>
        <input type="hidden" name="arrivalDate" value="${arrivalDate}"/>
        <input type="hidden" name="hotel" value="${hotel}"/>
        <input type="hidden" name="nutrition" value="${nutrition}"/>
        <input type="hidden" name="adultsNumber" value="${adultsNumber}"/>
        <input type="hidden" name="childrenNumber" value="${childrenNumber}"/>
        <input type="hidden" name="price" value="${price}"/>
        <input type="hidden" name="status" value="${status}"/>

        <div class="contentItem">
            <div class="tourName">
                <c:out value="${tourName}"/>
            </div>

            <c:if test="${userRole == 'ADMIN'}">
                <a id="changeTourNameMsg" onclick="document.getElementById('changeTourNameMsg').hidden=true;
                document.getElementById('changeTourNameDiv').hidden=false;
                document.getElementById('newTourName').required=true">
                    <fmt:message key="admin.change"/>
                </a>
                <div hidden id="changeTourNameDiv">
                    <a id="closeTourName" onclick="document.getElementById('changeTourNameMsg').hidden=false;
                document.getElementById('changeTourNameDiv').hidden=true;
                document.getElementById('newTourName').required=false">
                        <fmt:message key="admin.close"/>
                    </a>

                    <div id="changeTourName">
                        <label>
                            <input type="hidden" name="command" value="change_tour_name" id="change_tour_name"
                                   disabled/>
                            <input type="text" id="newTourName" name="newTourName"
                                   pattern="^[\sa-zA-Z.,_%+-]{1,40}$">

                            <fmt:message key="admin.changeTourName" var="changeTourNameButton"/>

                            <input type="submit" value="${changeTourNameButton}"
                                   onclick="document.getElementById('change_tour_name').disabled=false">
                        </label>
                    </div>
                </div>
                ${errorTourNameExistsMessage}
            </c:if>
        </div>

        <div class="contentItem">
            <div class="arrivalCountry">
                <div class="itemMsg">
                    <fmt:message key="common.message.arrivalCountry"/>
                </div>
                <c:out value="${arrivalCountry}"/>
            </div>

            <c:if test="${userRole == 'ADMIN'}">
                <a id="changeArrivalCountryMsg" onclick="document.getElementById('changeArrivalCountryMsg').hidden=true;
                document.getElementById('changeArrivalCountryDiv').hidden=false;
                document.getElementById('newArrivalCountry').required=true">
                    <fmt:message key="admin.change"/>
                </a>
                <div hidden id="changeArrivalCountryDiv">
                    <a id="closeArrivalCountry" onclick="document.getElementById('changeArrivalCountryMsg').hidden=false;
                document.getElementById('changeArrivalCountryDiv').hidden=true;
                document.getElementById('newArrivalCountry').required=false">
                        <fmt:message key="admin.close"/>
                    </a>

                    <div id="changeArrivalCountry">
                        <label>
                            <input type="hidden" name="command" value="change_arrival_country"
                                   id="change_arrival_country" disabled/>
                            <input type="text" id="newArrivalCountry" name="newArrivalCountry"
                                   pattern="^[\sa-zA-Z.,_%+-]{1,40}$">


                            <fmt:message key="admin.changeArrivalCountry" var="changeArrivalCountryButton"/>

                            <input type="submit" value="${changeArrivalCountryButton}"
                                   onclick="document.getElementById('change_arrival_country').disabled=false">
                        </label>
                    </div>
                </div>

            </c:if>
        </div>

        <div class="contentItem">
            <div class="arrivalCity">
                <div class="itemMsg">
                    <fmt:message key="common.message.arrivalCity"/>
                </div>
                <c:out value="${arrivalCity}"/>
            </div>

            <c:if test="${userRole == 'ADMIN'}">
                <a id="changeArrivalCityMsg" onclick="document.getElementById('changeArrivalCityMsg').hidden=true;
                document.getElementById('changeArrivalCityDiv').hidden=false;
                document.getElementById('newArrivalCity').required=true">
                    <fmt:message key="admin.change"/>
                </a>
                <div hidden id="changeArrivalCityDiv">
                    <a id="closeArrivalCity" onclick="document.getElementById('changeArrivalCityMsg').hidden=false;
                document.getElementById('changeArrivalCityDiv').hidden=true;
                document.getElementById('newArrivalCity').required=false">
                        <fmt:message key="admin.close"/>
                    </a>

                    <div id="changeArrivalCity">
                        <label>
                            <input type="hidden" name="command" value="change_arrival_city" id="change_arrival_city"
                                   disabled/>
                            <input type="text" id="newArrivalCity" name="newArrivalCity"
                                   pattern="^[\sa-zA-Z.,_%+-]{1,40}$">

                            <fmt:message key="admin.changeArrivalCity" var="changeArrivalCityButton"/>

                            <input type="submit" value="${changeArrivalCityButton}"
                                   onclick="document.getElementById('change_arrival_city').disabled=false">
                        </label>
                    </div>
                </div>

            </c:if>
        </div>

        <div class="contentItem">
            <div class="departureCity">
                <div class="itemMsg">
                    <fmt:message key="common.message.departureCity"/>
                </div>
                <c:out value="${departureCity}"/>
            </div>

            <c:if test="${userRole == 'ADMIN'}">
                <a id="changeDepartureCityMsg" onclick="document.getElementById('changeDepartureCityMsg').hidden=true;
                document.getElementById('changeDepartureCityDiv').hidden=false;
                document.getElementById('newDepartureCity').required=true">
                    <fmt:message key="admin.change"/>
                </a>
                <div hidden
                     id="changeDepartureCityDiv">
                    <a id="closeDepartureCity" onclick="document.getElementById('changeDepartureCityMsg').hidden=false;
                document.getElementById('changeDepartureCityDiv').hidden=true;
                document.getElementById('newDepartureCity').required=false">
                        <fmt:message key="admin.close"/>
                    </a>

                    <div id="changeDepartureCity">
                        <label>
                            <input type="hidden" name="command" value="change_departure_city"
                                   id="change_departure_city" disabled/>
                            <input type="text" id="newDepartureCity" name="newDepartureCity"
                                   pattern="^[\sa-zA-Z.,_%+-]{1,40}$">

                            <fmt:message key="admin.changeDepartureCity" var="changeDepartureCityButton"/>

                            <input type="submit" value="${changeDepartureCityButton}"
                                   onclick="document.getElementById('change_departure_city').disabled=false">
                        </label>
                    </div>
                </div>

            </c:if>
        </div>

        <div class="contentItem">
            <div class="departureDate">
                <div class="itemMsg">
                    <fmt:message key="common.message.departureDate"/>
                </div>
                <c:out value="${departureDate}"/>
            </div>

            <c:if test="${userRole == 'ADMIN'}">
                <a id="changeDepartureDateMsg" onclick="document.getElementById('changeDepartureDateMsg').hidden=true;
                document.getElementById('changeDepartureDateDiv').hidden=false;
                document.getElementById('newDepartureDate').required=true">
                    <fmt:message key="admin.change"/>
                </a>
                <div hidden id="changeDepartureDateDiv">
                    <a id="closeDepartureDate" onclick="document.getElementById('changeDepartureDateMsg').hidden=false;
                document.getElementById('changeDepartureDateDiv').hidden=true;
                document.getElementById('newDepartureDate').required=false">
                        <fmt:message key="admin.close"/>
                    </a>

                    <div id="changeDepartureDate">
                        <label>
                            <input type="hidden" name="command" value="change_departure_date"
                                   id="change_departure_date" disabled/>
                            <input type="date" id="newDepartureDate" name="newDepartureDate">

                            <fmt:message key="admin.changeDepartureDate" var="changeDepartureDateButton"/>

                            <input type="submit" value="${changeDepartureDateButton}"
                                   onclick="document.getElementById('change_departure_date').disabled=false">
                        </label>
                    </div>
                </div>
            </c:if>
        </div>

        <div class="contentItem">
            <div class="arrivalDate">
                <div class="itemMsg">
                    <fmt:message key="common.message.arrivalDate"/>
                </div>
                <c:out value="${arrivalDate}"/>
            </div>

            <c:if test="${userRole == 'ADMIN'}">
                <a id="changeArrivalDateMsg" onclick="document.getElementById('changeArrivalDateMsg').hidden=true;
                document.getElementById('changeArrivalDateDiv').hidden=false;
                document.getElementById('newArrivalDate').required=true">
                    <fmt:message key="admin.change"/>
                </a>
                <div hidden id="changeArrivalDateDiv">
                    <a id="closeArrivalDate" onclick="document.getElementById('changeArrivalDateMsg').hidden=false;
                document.getElementById('changeArrivalDateDiv').hidden=true;
                document.getElementById('newArrivalDate').required=false">
                        <fmt:message key="admin.close"/>
                    </a>

                    <div id="changeArrivalDate">
                        <label>
                            <input type="hidden" name="command" value="change_arrival_date"
                                   id="change_arrival_date" disabled/>
                            <input type="date" id="newArrivalDate" name="newArrivalDate">

                            <fmt:message key="admin.changeArrivalDate" var="changeArrivalDateButton"/>

                            <input type="submit" value="${changeArrivalDateButton}"
                                   onclick="document.getElementById('change_arrival_date').disabled=false">
                        </label>
                    </div>
                </div>
                ${errorInvalidDate}
            </c:if>
        </div>

        <div class="contentItem">
            <div class="hotel">
                <div class="itemMsg">
                    <fmt:message key="common.message.hotel"/>
                </div>
                <c:out value="${hotel}"/>
            </div>

            <c:if test="${userRole == 'ADMIN'}">
                <a id="changeHotelMsg" onclick="document.getElementById('changeHotelMsg').hidden=true;
                document.getElementById('changeHotelDiv').hidden=false;
                document.getElementById('newHotel').required=true">
                    <fmt:message key="admin.change"/>
                </a>
                <div hidden id="changeHotelDiv">
                    <a id="closeHotel" onclick="document.getElementById('changeHotelMsg').hidden=false;
                document.getElementById('changeHotelDiv').hidden=true;
                document.getElementById('newHotel').required=false">
                        <fmt:message key="admin.close"/>
                    </a>

                    <div id="changeHotel">
                        <label>
                            <input type="hidden" name="command" value="change_hotel"
                                   id="change_hotel" disabled/>
                            <input type="text" id="newHotel" name="newHotel" minlength="1" maxlength="40"
                                   pattern="^[\sa-zA-Z.,_%+-]{1,40}$">

                            <fmt:message key="admin.changeHotel" var="changeHotelButton"/>

                            <input type="submit" value="${changeHotelButton}"
                                   onclick="document.getElementById('change_hotel').disabled=false">
                        </label>
                    </div>
                </div>

            </c:if>
        </div>

        <div class="contentItem">
            <div class="nutrition">
                <div class="itemMsg">
                    <fmt:message key="common.message.nutrition"/>
                </div>
                <c:out value="${nutrition}"/>
            </div>

            <c:if test="${userRole == 'ADMIN'}">
                <a id="changeNutritionMsg" onclick="document.getElementById('changeNutritionMsg').hidden=true;
                document.getElementById('changeNutritionDiv').hidden=false;
                document.getElementById('newNutrition').required=true">
                    <fmt:message key="admin.change"/>
                </a>
                <div hidden id="changeNutritionDiv">
                    <a id="closeNutrition" onclick="document.getElementById('changeNutritionMsg').hidden=false;
                document.getElementById('changeNutritionDiv').hidden=true;
                document.getElementById('newNutrition').required=false">
                        <fmt:message key="admin.close"/>
                    </a>

                    <div id="changeNutrition">
                        <label>
                            <input type="hidden" name="command" value="change_nutrition"
                                   id="change_nutrition" disabled/>
                            <input type="text" id="newNutrition" name="newNutrition" minlength="2" maxlength="3"
                                   pattern="^[A-Z]{2,3}[+]?$">

                            <fmt:message key="admin.changeNutrition" var="changeNutritionButton"/>

                            <input type="submit" value="${changeNutritionButton}"
                                   onclick="document.getElementById('change_nutrition').disabled=false">
                        </label>
                    </div>
                </div>

            </c:if>
        </div>

        <div class="contentItem">
            <div class="adultsNumber">
                <div class="itemMsg">
                    <fmt:message key="common.message.numberOfAdults"/>
                </div>
                <c:out value="${adultsNumber}"/>
            </div>

            <c:if test="${userRole == 'ADMIN'}">
                <a id="changeAdultsNumberMsg" onclick="document.getElementById('changeAdultsNumberMsg').hidden=true;
                document.getElementById('changeAdultsNumberDiv').hidden=false;
                document.getElementById('newAdultsNumber').required=true">
                    <fmt:message key="admin.change"/>
                </a>
                <div hidden id="changeAdultsNumberDiv">
                    <a id="closeAdultsNumber" onclick="document.getElementById('changeAdultsNumberMsg').hidden=false;
                document.getElementById('changeAdultsNumberDiv').hidden=true;
                document.getElementById('newAdultsNumber').required=false">
                        <fmt:message key="admin.close"/>
                    </a>

                    <div id="changeAdultsNumber">
                        <label>
                            <input type="hidden" name="command" value="change_adults_number"
                                   id="change_adults_number" disabled/>
                            <input type="text" id="newAdultsNumber" name="newAdultsNumber" min="0" max="50"
                                   pattern="^([0-4]?\d|50)$">

                            <fmt:message key="admin.changeAdultsNumber" var="changeAdultsNumberButton"/>

                            <input type="submit" value="${changeAdultsNumberButton}"
                                   onclick="document.getElementById('change_adults_number').disabled=false">
                        </label>
                    </div>
                </div>

            </c:if>
        </div>

        <div class="contentItem">
            <div class="childrenNumber">
                <div class="itemMsg">
                    <fmt:message key="common.message.numberOfChildren"/>
                </div>
                <c:out value="${childrenNumber}"/>
            </div>

            <c:if test="${userRole == 'ADMIN'}">
                <a id="changeChildrenNumberMsg" onclick="document.getElementById('changeChildrenNumberMsg').hidden=true;
                document.getElementById('changeChildrenNumberDiv').hidden=false;
                document.getElementById('newChildrenNumber').required=true">
                    <fmt:message key="admin.change"/>
                </a>
                <div hidden id="changeChildrenNumberDiv">
                    <a id="closeChildrenNumber" onclick="document.getElementById('changeChildrenNumberMsg').hidden=false;
                document.getElementById('changeChildrenNumberDiv').hidden=true;
                document.getElementById('newChildrenNumber').required=false">
                        <fmt:message key="admin.close"/>
                    </a>

                    <div id="changeChildrenNumber">
                        <label>
                            <input type="hidden" name="command" value="change_children_number"
                                   id="change_children_number" disabled/>
                            <input type="text" id="newChildrenNumber" name="newChildrenNumber" min="0" max="50"
                                   pattern="^([0-4]?\d|50)$">

                            <fmt:message key="admin.changeChildrenNumber" var="changeChildrenNumberButton"/>

                            <input type="submit" value="${changeChildrenNumberButton}"
                                   onclick="document.getElementById('change_children_number').disabled=false">
                        </label>
                    </div>
                </div>

            </c:if>
        </div>

        <div class="contentItem">
            <div class="price">
                <div class="itemMsg">
                    <fmt:message key="common.message.price"/>
                </div>
                <c:out value="${price}$"/>
            </div>

            <c:if test="${userRole == 'ADMIN'}">
                <a id="changePriceMsg" onclick="document.getElementById('changePriceMsg').hidden=true;
                document.getElementById('changePriceDiv').hidden=false;
                document.getElementById('newPrice').required=true">
                    <fmt:message key="admin.change"/>
                </a>
                <div hidden id="changePriceDiv">
                    <a id="closePrice" onclick="document.getElementById('changePriceMsg').hidden=false;
                document.getElementById('changePriceDiv').hidden=true;
                document.getElementById('newPrice').required=false">
                        <fmt:message key="admin.close"/>
                    </a>

                    <div id="changePrice">
                        <label>
                            <input type="hidden" name="command" value="change_price"
                                   id="change_price" disabled/>
                            <input type="text" id="newPrice" name="newPrice" min="1" max="200000"
                                   pattern="^([1]?\d?\d?\d?\d?\d([.]\d\d)?|200000([.]\d\d)?)$">

                            <fmt:message key="admin.changePrice" var="changePriceButton"/>

                            <input type="submit" value="${changePriceButton}"
                                   onclick="document.getElementById('change_price').disabled=false">
                        </label>
                    </div>
                </div>

            </c:if>
        </div>

        <c:if test="${userRole == 'ADMIN'}">
            <div class="contentItem">
                <c:if test="${status == 'AVAILABLE'}">
                    <fmt:message key="admin.changeStatusNotAvailable" var="changeStatusMsg"/>
                </c:if>
                <c:if test="${status == 'NOT_AVAILABLE'}">
                    <fmt:message key="admin.changeStatusAvailable" var="changeStatusMsg"/>
                </c:if>
                <input type="hidden" name="command" value="change_status" id="change_status" disabled>
                <input type="submit" value="${changeStatusMsg}"
                       onclick="document.getElementById('change_status').disabled=false">
            </div>
        </c:if>

        <div class="contentItem">
            <label>
                <input type="hidden" name="command" value="to_tours" id="to_tours" disabled/>
                <fmt:message key="common.submit.back" var="back"/>
                <input type="submit" name="back"
                       onclick="document.getElementById('to_tours').disabled=false" value="${back}">
            </label>

            <c:if test="${userRole != 'ADMIN'}">
                <input type="hidden" name="command" value="to_tickets"/>
                <fmt:message key="notAdmin.submit.ticketSelection" var="ticketSelection"/>
                <label>
                    <input type="submit" name="ticketSelection" value="${ticketSelection}">
                </label>
            </c:if>
        </div>
    </form>

</div>
</body>
<c:import url="components/timestamp.jsp"></c:import>
</html>
