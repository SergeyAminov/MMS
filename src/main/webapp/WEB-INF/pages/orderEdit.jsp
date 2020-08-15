<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Order</title>
</head>
<body>
    <h3>Edit order (ID: ${order.id})</h3>
    <c:url value="/admin/orders/edit" var="edit"/>
    <form action="${edit}" method="POST">
        <input type="hidden" name="id" value="${order.id}">
        <input type="hidden" name="userId" value="${order.userId}">
        <input type="hidden" name="address" value="${order.address}">
        <input type="hidden" name="paymentMethod" value="${order.paymentMethod}">
        <input type="hidden" name="deliveryMethod" value="${order.deliveryMethod}">

        <p>
            <label for="paymentStatus">Payment status:</label>
            <select size="3" name="paymentStatus" id="paymentStatus">
                <c:forEach var="paymentStatus" items="${paymentStatusMap}">
                    <option value="${paymentStatus.value}">${paymentStatus.value}</option>
                </c:forEach>
            </select>
        </p>

        <p>
            <label for="deliveryStatus">Delivery status:</label>
            <select size="3" name="deliveryStatus" id="deliveryStatus">
                <c:forEach var="deliveryStatus" items="${deliveryStatusMap}">
                    <option value="${deliveryStatus.value}">${deliveryStatus.value}</option>
                </c:forEach>
            </select>
        </p>

        <input type="submit" value="Edit">

    </form>
</body>
</html>
