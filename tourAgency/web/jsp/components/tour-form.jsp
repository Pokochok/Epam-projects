<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="jsp/tours"/>
<!DOCTYPE html>

<body>

<div class="tourItem">

    <div class="MinInf">
        <div class="TourName" <c:if test="${param.status == 'NOT_AVAILABLE'}"> style="color: red" </c:if> >
            <c:out value="${param.tourName}"/>
        </div>

        <div class="arrivalPlace">
            <c:out value="${param.arrivalCountry}, ${param.arrivalCity}"/>
        </div>
    </div>

    <div class="departureCity">
        <form method="POST" action="controller">
            <input type="hidden" name="command" value="to_tour_overview"/>
            <input type="hidden" name="tourName" value="${param.tourName}"/>
            <input type="hidden" name="tourId" value="${param.tourId}"/>
            <input type="hidden" name="arrivalCountry" value="${param.arrivalCountry}"/>
            <input type="hidden" name="arrivalCity" value="${param.arrivalCity}"/>
            <input type="hidden" name="departureCity" value="${param.departureCity}"/>
            <input type="hidden" name="departureDate" value="${param.departureDate}"/>
            <input type="hidden" name="arrivalDate" value="${param.arrivalDate}"/>
            <input type="hidden" name="hotel" value="${param.hotel}"/>
            <input type="hidden" name="nutrition" value="${param.nutrition}"/>
            <input type="hidden" name="adultsNumber" value="${param.adultsNumber}"/>
            <input type="hidden" name="childrenNumber" value="${param.childrenNumber}"/>
            <input type="hidden" name="price" value="${param.price}"/>
            <input type="hidden" name="status" value="${param.status}"/>

            <fmt:message key="common.tourForm.submit.from" var="from"/>
            <div>
                <c:out value="${from} ${param.departureCity}"/>
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
</body>
</html>