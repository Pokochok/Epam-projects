<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <%@include file="components/main-panel.jsp" %>
    <link href="../css/componentsstyle/order-form.css" rel="stylesheet" type="text/css">
    <link href="../css/orders-style.css" rel="stylesheet" type="text/css">
    <fmt:setBundle basename="jsp/orders"/>
    <c:if test="${userRole == 'CLIENT'}">
        <title><fmt:message key="client.title.orders"/></title>
    </c:if>
    <c:if test="${userRole != 'CLIENT'}">
        <title><fmt:message key="adminAgent.title.order"/></title>
    </c:if>
</head>
<body>

<div class="contentForm">

    <div class="contentContainer">
        <div class="contentHeader">
            <c:if test="${userRole == 'CLIENT'}">
                <fmt:message key="client.title.orders"/>
            </c:if>
            <c:if test="${userRole != 'CLIENT'}">
                <fmt:message key="adminAgent.title.order"/>
            </c:if>
            <hr/>
        </div>

        <div class="orders">
            <c:if test="${empty orderList}"> <fmt:message key="common.message.noReservations"/> </c:if>

            <c:forEach begin="${startIndexOfOrders}" end="${startIndexOfOrders + ordersPerPage}" var="order"
                       items="${orderList}">
                <c:import url="components/order-form.jsp">
                    <c:param name="orderId" value="${order.getId()}"/>
                    <c:param name="ticketId" value="${order.getTicket().getId()}"/>
                    <c:param name="tourName" value="${order.getTour().getTourName()}"/>
                    <c:param name="arrivalDate" value="${order.getTicket().getArrivalDateTime()}"/>
                    <c:param name="departureDate" value="${order.getTicket().getDepartureDateTime()}"/>
                    <c:param name="arrivalCity" value="${order.getTicket().getArrivalCity()}"/>
                    <c:param name="departureCity" value="${order.getTicket().getDepartureCity()}"/>
                    <c:param name="clientName" value="${order.getClient().getName()}"/>
                    <c:param name="agentName" value="${order.getAgent().getName()}"/>
                    <c:param name="clientEmail" value="${order.getClient().getEmail()}"/>
                    <c:param name="agentEmail" value="${order.getAgent().getEmail()}"/>
                    <c:param name="paymentState" value="${order.getPaymentState()}"/>
                </c:import>
            </c:forEach>
        </div>

        <nav>
            <form class="pagination">
                <c:if test="${startIndexOfOrders == 0}">
                    <div class="page-item disabled">
                        <a class="page-link"><fmt:message key="common.ref.previousPage"/> </a>
                    </div>
                </c:if>
                <c:if test="${startIndexOfOrders > 0}">
                    <div class="page-item">
                        <form id="previousPageForm" method="post" action="controller">
                            <input type="hidden" name="toursPerPage" value="${ordersPerPage}">
                            <a class="page-link"
                               href="controller?command=to_orders&changePage=-1&index=${startIndexOfOrders/startIndexOfOrders + 1}">
                                <fmt:message key="common.ref.previousPage"/></a>
                        </form>
                    </div>
                </c:if>

                <div class="page-item"><a class="page-link">${index}</a></div>

                <c:if test="${startIndexOfOrders + ordersPerPage < numberOfOrders}">
                    <div class="page-item">
                        <form id="nextPageForm" method="post" action="controller">
                            <a class="page-link"
                               href="controller?command=to_orders&changePage=1&index=${startIndexOfOrders/startIndexOfOrders + 1}">
                                <fmt:message key="common.ref.nextPage"/></a>
                        </form>
                    </div>
                </c:if>
                <c:if test="${startIndexOfOrders + ordersPerPage >= numberOfOrders}">
                    <div class="page-item disabled">
                        <a class="page-link"><fmt:message key="common.ref.nextPage"/></a>
                    </div>
                </c:if>
            </form>
        </nav>
    </div>
</div>

</body>
</html>
