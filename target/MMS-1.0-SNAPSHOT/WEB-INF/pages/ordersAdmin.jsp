<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" class="h-100">
<head>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
    <title>Order History</title>
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

    <h3 class="font-weight-bold mt-2">Order list</h3>
    <c:forEach var="order" items="${orderMap}">
        <div class="item-card card mx-auto container mt-3">
            <table class="table table-sm">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">User ID</th>
                    <th scope="col">Delivery method</th>
                    <th scope="col">Delivery status</th>
                    <th scope="col">Payment method</th>
                    <th scope="col">Payment status</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody>
                <c:url value="/admin/orders/edit/${order.key.id}" var="edit"/>
                <tr>
                    <td>${order.key.id}</td>
                    <td>${order.key.userId}</td>
                    <td>${order.key.deliveryMethod}</td>
                    <td>${order.key.deliveryStatus}</td>
                    <td>${order.key.paymentMethod}</td>
                    <td>${order.key.paymentStatus}</td>
                    <td><a href="${edit}">edit</a></td>
                </tr>
                </tbody>
            </table>
            <h5 class="font-weight-bold">Items:</h5>
            <ul>
                <c:forEach var="product" items="${order.value}">
                    <li>${product.brand}, ${product.color}, ${product.weight} kg, ${product.diagonal} inch,
                            ${product.storage} Gb memory, ${product.ram} Gb RAM;</li>
                </c:forEach>
            </ul>
        </div>
    </c:forEach>

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
