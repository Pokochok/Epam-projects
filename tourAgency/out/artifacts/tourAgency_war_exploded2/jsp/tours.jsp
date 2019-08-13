<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <%@include file="components/main-panel.jsp" %>
    <link href="../css/componentsstyle/tour-form.css" rel="stylesheet" type="text/css">
    <link href="../css/tours-style.css" rel="stylesheet" type="text/css">
    <fmt:setBundle basename="jsp/tours"/>
    <title><fmt:message key="common.title.Tours"/></title>
</head>
<body>

<form class="contentForm" method="post" action="controller">

    <div class="menu">
        <c:if test="${userRole == 'ADMIN'}">
            <div class="registerNewTour">
                <a class="menuRef" href="controller?command=to_tour_registration">
                    <fmt:message key="admin.ref.registerNewTour"/>
                </a>
            </div>
        </c:if>
    </div>

    <div class="framing">

        <div class="contentContainer">
            <div class="contentHeader">
                <fmt:message key="common.message.availableTours"/>
                <hr/>
            </div>

            <div class="tours">
                <c:forEach begin="${startIndexOfTours}" end="${startIndexOfTours + toursPerPage - 1}" var="tour"
                           items="${tourList}">
                    <c:import url="components/tour-form.jsp">
                        <c:param name="tourId" value="${tour.getId()}"/>
                        <c:param name="tourName" value="${tour.getTourName()}"/>
                        <c:param name="arrivalCountry" value="${tour.getArrivalCountry()}"/>
                        <c:param name="arrivalCity" value="${tour.getArrivalCity()}"/>
                        <c:param name="departureCity" value="${tour.getDepartureCity()}"/>
                        <c:param name="departureDate" value="${tour.getDepartureDate()}"/>
                        <c:param name="arrivalDate" value="${tour.getArrivalDate()}"/>
                        <c:param name="hotel" value="${tour.getHotel()}"/>
                        <c:param name="nutrition" value="${tour.getNutrition()}"/>
                        <c:param name="adultsNumber" value="${tour.getAdultsNumber()}"/>
                        <c:param name="childrenNumber" value="${tour.getChildrenNumber()}"/>
                        <c:param name="price" value="${tour.getPrice()}"/>
                        <c:param name="status" value="${tour.getStatus()}"/>
                    </c:import>
                </c:forEach>
            </div>

            <nav>
                <div class="pagination">
                    <c:if test="${startIndexOfTours == 0}">
                        <div class="page-item disabled">
                            <a class="page-link"><fmt:message key="common.ref.previousPage"/> </a>
                        </div>
                    </c:if>
                    <c:if test="${startIndexOfTours > 0}">
                        <div class="page-item">
                            <form id="previousPageForm" method="post" action="controller">
                                <a class="page-link"
                                   href="controller?command=to_tours&changePage=-1&index=${startIndexOfTours/toursPerPage + 1}">
                                    <fmt:message key="common.ref.previousPage"/></a>
                            </form>
                        </div>
                    </c:if>

                    <div class="page-item"><a class="page-link">${index}</a></div>

                    <c:if test="${startIndexOfTours + toursPerPage < numberOfTours}">
                        <div class="page-item">
                            <form id="nextPageForm" method="post" action="controller">
                                <a class="page-link"
                                   href="controller?command=to_tours&changePage=1&index=${startIndexOfTours/toursPerPage + 1}" >
                                    <fmt:message key="common.ref.nextPage"/> </a>
                            </form>
                        </div>
                    </c:if>
                    <c:if test="${startIndexOfTours + toursPerPage >= numberOfTours}">
                        <div class="page-item disabled">
                            <a class="page-link"><fmt:message key="common.ref.nextPage"/></a>
                        </div>
                    </c:if>
                </div>
            </nav>
        </div>
    </div>
</form>

</body>
</html>
