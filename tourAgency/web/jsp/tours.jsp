<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="jsp/Tours"/>

<html>
<head>
    <link href="../css/componentsstyle/tour-form.css" rel="stylesheet" type="text/css">
    <link href="../css/tours-style.css" rel="stylesheet" type="text/css">
    <title><fmt:message key="common.title.Tours"/></title>
    <jsp:include page="components/main-panel.jsp"/>
</head>
<body>

<form class="contentForm" method="post" action="controller">
    <div class="contentHeader">
        <fmt:message key="common.message.availableTours"/>
        <hr/>
    </div>

    <div class="menu_tours">
        <div class="menu">
            <c:if test="${userRole == 'ADMIN'}">
                <div class="registerNewTour">
                    <a href="controller?command=to_tour_registration">
                        <fmt:message key="admin.ref.registerNewTour"/>
                    </a>
                </div>
            </c:if>
        </div>

        <div class="tours">
            <c:forEach begin="${startIndexOfTours}" end="${startIndexOfTours + toursPerPage}" var="tour"
                       items="${tourList}">
                <c:import url="components/tour-form.jsp">
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
                </c:import>
            </c:forEach>
        </div>
    </div>

    <nav>
        <form class="pagination">
            <c:if test="${startIndexOfTours == 0}">
                <div class="page-item disabled">
                    <a class="page-link"><fmt:message key="common.ref.previousPage"/> </a>
                </div>
            </c:if>
            <c:if test="${startIndexOfTours > 0}">
                <div class="page-item">
                    <form id="previousPageForm" method="post" action="controller">
                            <%--                            <input type="hidden" name="command" value="to_tours">--%>

                        <input type="hidden" name="changePage" value="-1">
                        <input type="hidden" name="index" value="${startIndexOfTours/toursPerPage}">

                        <input type="hidden" name="toursPerPage" value="${toursPerPage}">
                        <a class="page-link" href="controller?command=to_tours">
                                <%--                               onclick="document.getElementById('previousPageForm').submit();">--%>
                            <fmt:message key="common.ref.previousPage"/></a>
                    </form>
                </div>
            </c:if>

            <div class="page-item"><a class="page-link">${index}</a></div>

            <c:if test="${startIndexOfTours + toursPerPage - 1 < numberOfTours}">
                <div class="page-item">
                    <form id="nextPageForm" method="post" action="controller">
                            <%--                            <input type="hidden" name="command" value="to_tours">--%>

                        <input type="hidden" name="changePage" value="1">
                        <input type="hidden" name="index" value="${startIndexOfTours/toursPerPage + 1}">

                        <input type="hidden" name="toursPerPage" value="${toursPerPage}">
                        <a class="page-link" href="controller?command=to_tours">
                            <fmt:message key="common.ref.nextPage"/> </a>
                    </form>
                </div>
            </c:if>
            <c:if test="${startIndexOfTours + toursPerPage - 1 >= numberOfTours}">
                <div class="page-item disabled">
                    <a class="page-link"><fmt:message key="common.ref.nextPage"/></a>
                </div>
            </c:if>
        </form>
    </nav>


</form>

</body>
</html>
