<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Order History</title>
</head>
<body>
    <h2>All products</h2>
    <hr>
    <c:forEach var="order" items="${orderMap}">
        <table>
            <tr>
                <th>ID</th>
                <th>User ID</th>
                <th>Delivery method</th>
                <th>Delivery status</th>
                <th>Payment method</th>
                <th>Payment status</th>
            </tr>
            <tr>
                <td>${order.key.id}</td>
                <td>${order.key.userId}</td>
                <td>${order.key.deliveryMethod}</td>
                <td>${order.key.deliveryStatus}</td>
                <td>${order.key.paymentMethod}</td>
                <td>${order.key.paymentStatus}</td>
            </tr>
        </table>
        <h3>Items:</h3>
        <c:forEach var="product" items="${order.value}">
            <p>${product.brand}, ${product.color}, ${product.weight} kg, ${product.diagonal} inch,
                    ${product.storage} Gb memory, ${product.ram} Gb RAM;
            </p>
        </c:forEach>
        <c:url value="/admin/orders/edit/${order.key.id}" var="edit"/>
        <a href="${edit}">Edit</a>
        <hr>
    </c:forEach>
</body>
</html>
