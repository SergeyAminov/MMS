<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" class="h-100">
<head>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
    <c:if test="${empty product.title}">
        <title>Add</title>
    </c:if>
    <c:if test="${!empty product.title}">
        <title>Edit</title>
    </c:if>
</head>
<body class="d-flex flex-column h-100">

<!-- Header -->
<header>
    <c:url value="/admin/products" var="catalog"/>
    <c:url value="/admin/orders" var="orders"/>
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
                </ul>
            </div>
        </nav>
    </div>
</header>

<!-- Main -->
<main class="flex-shrink-0 container">

    <!-- Set action variable -->
    <c:if test="${empty product.title}">
        <c:url value="/admin/products/add" var="var"/>
    </c:if>
    <c:if test="${!empty product.title}">
        <c:url value="/admin/products/edit" var="var"/>
    </c:if>

    <form class="item-card card container mt-3 mb-3 w-50" action="${var}" method="POST">

        <c:if test="${!empty product.title}">
            <input type="hidden" name="id" value="${product.id}">
        </c:if>

        <div class="form-group">
            <label for="count">Count</label>
            <input class="form-control" type="number" name="count" id="count" value="${product.count}">
        </div>

        <div class="form-group">
            <label for="title">Title</label>
            <input class="form-control" type="text" name="title" id="title" value="${product.title}">
        </div>

        <div class="form-group">
            <label for="price">Price</label>
            <input class="form-control" type="number" step=any name="price" id="price" value="${product.price}">
        </div>

        <div class="form-group">
            <label for="brand">Brand</label>
            <input class="form-control" type="text" name="brand" id="brand" value="${product.brand}">
        </div>

        <div class="form-group">
            <label for="color">Color</label>
            <input class="form-control" type="text" name="color" id="color" value="${product.color}">
        </div>

        <div class="form-group">
            <label for="weight">Weight</label>
            <input class="form-control" type="number" step=any name="weight" id="weight" value="${product.weight}">
        </div>

        <div class="form-group">
            <label for="diagonal">Diagonal</label>
            <input class="form-control" type="number" step=any name="diagonal" id="diagonal" value="${product.diagonal}">
        </div>

        <div class="form-group">
            <label for="storage">Storage</label>
            <input class="form-control" type="number" step=any name="storage" id="storage" value="${product.storage}">
        </div>

        <div class="form-group">
            <label for="ram">RAM</label>
            <input class="form-control" type="number" name="ram" id="ram" value="${product.ram}">
        </div>

        <div class="form-group">
            <label for="category">Choose category</label>
            <select class="form-control" name="categoryId" id="category">
                <c:forEach var="category" items="${categoryMap}">
                    <option value="${category.key}">${category.value}</option>
                </c:forEach>
            </select>
        </div>

        <c:if test="${!empty product.title}">
            <input type="submit" class="btn btn-warning text-light mb-1 w-50 mx-auto font-weight-bold" value="Edit product">
        </c:if>
        <c:if test="${empty product.title}">
            <input type="submit" class="btn btn-warning mb-1 text-light w-50 mx-auto font-weight-bold" value="Add new product">
        </c:if>

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
