<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Order</title>
</head>
<body>

    <h3>Cart list:</h3>

    <c:forEach var="product" items="${cartList}">
        <p>${product.brand}, ${product.color}, ${product.weight} kg, ${product.diagonal} inch,
                ${product.storage} Gb memory, ${product.ram} Gb RAM</p>
    </c:forEach>

    <c:url value="/order" var="var"/>
    <form action="${var}" method="POST">
        <input type="hidden" name="userId" value="${userId}">
        <input type="hidden" name="paymentStatusId" value="${1}">
        <input type="hidden" name="deliveryStatusId" value="${1}">

        <!-- Choose address -->
        <p>
            <label for="address">Choose address:</label>
            <select size="3" name="address" id="address">
                <c:forEach var="address" items="${addressMap}">
                    <option value="${address.value}">${address.value}</option>
                </c:forEach>
            </select>
        </p>

        <!-- Choose payment method -->
        <p>
            <label for="paymentMethod">Choose payment method:</label>
            <select size="3" name="paymentMethodId" id="paymentMethod">
                <c:forEach var="paymentMethod" items="${paymentMethodMap}">
                    <option value="${paymentMethod.key}">${paymentMethod.value}</option>
                </c:forEach>
            </select>
        </p>

        <!-- Choose delivery method -->
        <p>
            <label for="deliveryMethod">Choose delivery method:</label>
            <select size="3" name="deliveryMethodId" id="deliveryMethod">
                <c:forEach var="deliveryMethod" items="${deliveryMethodMap}">
                    <option value="${deliveryMethod.key}">${deliveryMethod.value}</option>
                </c:forEach>
            </select>
        </p>

        <input type="submit" value="Order">

    </form>

</body>
</html>
