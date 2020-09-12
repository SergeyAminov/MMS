<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" class="h-100">
<head>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
    <title>Products</title>
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
<main class="flex-shrink-0 mx-auto mw-90">

    <table class="table table-sm mt-2">
        <thead>
            <tr>
                <th scope="col">Title</th>
                <th scope="col">Category</th>
                <th scope="col">Brand</th>
                <th scope="col">Color</th>
                <th scope="col">Weight (kg)</th>
                <th scope="col">Diagonal (inch)</th>
                <th scope="col">Storage(Gb)</th>
                <th scope="col">RAM(Gb)</th>
                <th scope="col">Price (&#36;)</th>
                <th scope="col">Count</th>
                <th scope="col">Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${productsList}">
                <c:url value="/admin/products/edit/${product.id}" var="edit"/>
                <c:url value="/admin/products/delete/${product.id}" var="delete"/>
                <tr>
                    <td>${product.title}</td>
                    <td>${categoryMap.get(product.categoryId)}</td>
                    <td>${product.brand}</td>
                    <td>${product.color}</td>
                    <td>${product.weight}</td>
                    <td>${product.diagonal}</td>
                    <td>${product.storage}</td>
                    <td>${product.ram}</td>
                    <td>${product.price}</td>
                    <td>${product.count}</td>
                    <td><a href="${edit}">edit</a> <a href="${delete}">delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <c:url value="/admin/products/add" var="add"/>
    <a class="container mt-2" href="${add}">Add new product</a>

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
