<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" class="h-100">
<head>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
    <title>Edit Order</title>
</head>
<body class="d-flex flex-column h-100">

<!-- Header -->
<header>
    <c:url value="/admin/products" var="catalog"/>
    <c:url value="/admin/orders" var="orders"/>
    <c:url value="/admin/statistic" var="statistic"/>
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <span class="navbar-brand font-weight-bold">Administrator</span>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link font-weight-bold" href="${catalog}">Products</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link font-weight-bold" href="${orders}">Orders</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link font-weight-bold" href="${statistic}">Statistic</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</header>

<!-- Main -->
<main class="flex-shrink-0 container">

    <h3>Edit order (ID: ${order.id})</h3>

    <c:url value="/admin/orders/edit" var="edit"/>

    <form class="item-card card container mt-3 mb-3 w-50" action="${edit}" method="POST">

        <input type="hidden" name="id" value="${order.id}">
        <input type="hidden" name="userId" value="${order.userId}">
        <input type="hidden" name="address" value="${order.address}">
        <input type="hidden" name="paymentMethod" value="${order.paymentMethod}">
        <input type="hidden" name="deliveryMethod" value="${order.deliveryMethod}">

        <div class="form-group">
            <label for="paymentStatus">Payment status:</label>
            <select class="form-control" name="paymentStatus" id="paymentStatus">
                <c:forEach var="paymentStatus" items="${paymentStatusMap}">
                    <option value="${paymentStatus.value}">${paymentStatus.value}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="deliveryStatus">Delivery status:</label>
            <select class="form-control" name="deliveryStatus" id="deliveryStatus">
                <c:forEach var="deliveryStatus" items="${deliveryStatusMap}">
                    <option value="${deliveryStatus.value}">${deliveryStatus.value}</option>
                </c:forEach>
            </select>
        </div>

        <input type="submit" class="btn btn-warning text-light mb-1 w-50 mx-auto font-weight-bold" value="Edit">

    </form>

</main>

<!-- Footer -->
<footer class="footer mt-auto bg-dark">
    <div class="footer-copyright text-center text-white py-3">© 2020 Copyright:
        <a class="text-warning" href="#"> Sergey Aminov</a>
    </div>
</footer>

<!-- Bootstrap scripts -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous">
</script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous">
</script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous">
</script>

</body>
</html>
