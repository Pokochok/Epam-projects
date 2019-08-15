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

<div class="contentForm" >

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
                <form class="pagination" id="paginationForm" method="post" action="controller">
                    <input type="hidden" id="command" name="command" value="to_tours">
                    <input type="hidden" name="index" value="${startIndexOfTours/toursPerPage + 1}"/>

                    <c:if test="${startIndexOfTours == 0}">
                        <div class="page-item disabled">
                            <a class="page-link"><fmt:message key="common.ref.previousPage"/> </a>
                        </div>
                    </c:if>
                    <c:if test="${startIndexOfTours > 0}">
                        <div class="page-item">
                            <div id="previousPageForm">
                                <input type="hidden" id="previousPage" name="changePage" value="-1" disabled>
                                <a class="page-link" href="#" onclick="document.getElementById('previousPage').disabled = false;
                                    document.getElementById('paginationForm').submit()">
                                    <fmt:message key="common.ref.previousPage"/></a>
                            </div>
                        </div>
                    </c:if>

                    <div class="page-item"><a class="page-link">${index}</a></div>

                    <c:if test="${startIndexOfTours + toursPerPage < numberOfTours}">
                        <div class="page-item">
                            <div id="nextPageForm">
                                <input type="hidden" id="nextPage" name="changePage" value="1" disabled>
                                <a class="page-link" href="#" onclick="document.getElementById('nextPage').disabled = false;
                                    document.getElementById('paginationForm').submit()">
                                    <fmt:message key="common.ref.nextPage"/> </a>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${startIndexOfTours + toursPerPage >= numberOfTours}">
                        <div class="page-item disabled">
                            <a class="page-link"><fmt:message key="common.ref.nextPage"/></a>
                        </div>
                    </c:if>
                </form>
            </nav>
        </div>
    </div>
</div>

</body>
</html>
