<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="jsp/orders"/>
<!DOCTYPE html>

<body>

<div class="tourItem">

    <div class="MinInf">
        <div class="TourName"
                <c:if test="${param.paymentState == 'false'}"> style="color: orange" </c:if>
                <c:if test="${param.paymentState == 'true'}"> style="color: green"</c:if> >
            <div class="MinInfMsg">
                <fmt:message key="common.message.tourName"/>
            </div>
            <c:if test="${param.tourName == 'not defined'}">
                <c:out value="--"/>
            </c:if>
            <c:if test="${param.tourName != 'not defined'}">
                <c:out value="${param.tourName}"/>
            </c:if>
        </div>

        <div class="ticketInf">
            <div class="MinInfMsg">
                <fmt:message key="common.message.ticketInf"/>
            </div>
            <c:if test="${param.ticketId != '0'}">
                <div class="ticketPlace">
                    <c:out value="${param.departureCity} - ${param.arrivalCity}"/>
                </div>
                <div class="ticketDate">
                    <c:out value="${param.departureDate} - ${param.arrivalDate}"/>
                </div>
            </c:if>
            <c:if test="${param.ticketId == '0'}">
                <c:out value="--"/>
            </c:if>
        </div>

        <div class="clientInf">
            <div class="MinInfMsg">
                <fmt:message key="common.message.clientInf"/>
            </div>
            <div>
                <c:out value="${param.clientName}"/>
            </div>
            <div>
                <c:out value="${param.clientEmail}"/>
            </div>
        </div>


        <div class="agentInf">
            <div class="MinInfMsg">
                <fmt:message key="common.message.agentInf"/>
            </div>
            <c:if test="${param.agentName != 'not defined'}">
                <div>
                    <c:out value="${param.agentName}"/>
                </div>
                <div>
                    <c:out value="${param.agentEmail}"/>
                </div>
            </c:if>
            <c:if test="${param.agentName == 'not defined'}">
                <c:out value="--"/>
            </c:if>
        </div>
    </div>

    <div class="orderMore">
        <form method="POST" action="controller">
            <input type="hidden" name="command" value="pay_order"/>
            <input type="hidden" name="orderId" value="${param.orderId}"/>

            <c:if test="${param.paymentState == 'false' && userRole == 'CLIENT'}">
                <fmt:message key="clientAgent.orderForm.submit.pay" var="pay"/>
                <div class="submitMore">
                    <label>
                        <input type="submit" value="${pay}">
                    </label>
                </div>
            </c:if>
        </form>

        <form class="remove" method="post" action="controller">
            <c:if test="${userRole == 'AGENT' || userRole == 'CLIENT'}">
                <a href="controller?command=remove_order&orderId=${param.orderId}">
                    <fmt:message key="clientAgent.orderForm.ref.removeOrder"/>
                </a>
            </c:if>
        </form>
    </div>
</div>
</body>
</html>
