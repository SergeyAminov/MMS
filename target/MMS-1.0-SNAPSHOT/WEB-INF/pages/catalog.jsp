<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" class="h-100">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
    <link href="<c:url value="/res/navbar-styles.css"/>" rel="stylesheet" type="text/css"/>
    <title>Home</title>
</head>
<body class="d-flex flex-column h-100">

<!-- Header -->
<header>
    <c:url value="/catalog" var="catalog"/>
    <c:url value="/cart" var="cart"/>
    <c:url value="/registration" var="registration"/>
    <c:url value="/login" var="login"/>
    <c:url value="/profile" var="profile"/>
    <c:url value="/perform_logout" var="logout"/>
    <div class="navigation-header container">
        <nav class="navbar nav navbar-light bg-warning">
            <span class="navbar-brand mb-0 h1 font-weight-bold text-uppercase">
                Online-shop
            </span>
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link text-white font-weight-bold" href="${catalog}">Catalog</a>
                </li>
            </ul>
            <a class="nav-link text-white font-weight-bold" href="${cart}">Cart</a>
            <c:choose>
                <c:when test="${!empty authentication}">
                    <a class="nav-link text-white font-weight-bold" href="${profile}">Profile</a>
                    <a class="nav-link text-white font-weight-bold" href="${logout}">Log Out</a>
                </c:when>
                <c:otherwise>
                    <a href="${registration}">
                        <button type="button" class="btn btn-outline-light font-weight-bold">Registration</button>
                    </a>
                    <a class="nav-link text-white font-weight-bold" href="${login}">Log In</a>
                </c:otherwise>
            </c:choose>
        </nav>
    </div>
</header>

<!-- Main -->
<main class="flex-shrink-0 container">
    <h1 class="container text-dark font-weight-bold">Catalog</h1>
    <!-- Filters menu -->
    <div class="dropdown-menu-block d-flex flex-row bd-highlight mb-3">
        <!-- Price filter -->
        <div class="dropdown filter-price">
            <button class="btn btn-link dropdown-toggle text-dark font-weight-bold"
                    type="button"
                    id="filterPrice"
                    data-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false">
                Price
            </button>
            <c:url value = "/catalog" var = "priceRange1">
                <c:param name = "min-price" value = "99"/>
                <c:param name = "max-price" value = "499"/>
            </c:url>
            <c:url value = "/catalog" var = "priceRange2">
                <c:param name = "min-price" value = "500"/>
                <c:param name = "max-price" value = "999"/>
            </c:url>
            <c:url value = "/catalog" var = "priceRange3">
                <c:param name = "min-price" value = "1000"/>
                <c:param name = "max-price" value = "1499"/>
            </c:url>
            <div class="dropdown-menu" aria-labelledby="filterPrice">
                <a class="dropdown-item" href="${priceRange1}">99 - 499 &#36;</a>
                <a class="dropdown-item" href="${priceRange2}">500 - 999 &#36;</a>
                <a class="dropdown-item" href="${priceRange3}">1000 - 1499 &#36;</a>
            </div>
        </div>
        <!-- Weight filter -->
        <div class="dropdown filter-weight">
            <button class="btn btn-link dropdown-toggle text-dark font-weight-bold"
                    type="button"
                    id="filterWeight"
                    data-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false">
                Weight
            </button>
            <c:url value = "/catalog" var = "weightRange1">
                <c:param name = "min-weight" value = "0.01"/>
                <c:param name = "max-weight" value = "0.99"/>
            </c:url>
            <c:url value = "/catalog" var = "weightRange2">
                <c:param name = "min-weight" value = "1"/>
                <c:param name = "max-weight" value = "1.99"/>
            </c:url>
            <c:url value = "/catalog" var = "weightRange3">
                <c:param name = "min-weight" value = "2"/>
                <c:param name = "max-weight" value = "2.99"/>
            </c:url>
            <div class="dropdown-menu" aria-labelledby="filterWeight">
                <a class="dropdown-item" href="${weightRange1}">< 1 kg</a>
                <a class="dropdown-item" href="${weightRange2}">1 - 1.99 kg</a>
                <a class="dropdown-item" href="${weightRange3}">> 2 kg</a>
            </div>
        </div>
        <!-- Weight filter -->
        <div class="dropdown filter-category">
            <button class="btn btn-link dropdown-toggle text-dark font-weight-bold"
                    type="button"
                    id="filterCategory"
                    data-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false">
                Category
            </button>
            <div class="dropdown-menu" aria-labelledby="filterWeight">
                <c:forEach var="categoryMap" items="${categoryMap}">
                    <c:url value = "/catalog" var = "category">
                        <c:param name = "category" value = "${categoryMap.key}"/>
                    </c:url>
                    <a class="dropdown-item" href="${category}">${categoryMap.value}</a>
                </c:forEach>
            </div>
        </div>
    </div>
    <!-- All items container -->
    <div class="shop-items container">
        <c:forEach var="product" items="${productsList}">
            <!-- Item -->
            <hr>
            <div class="item-card card container">
                <div class="card-body">
                    <h5 class="card-title font-weight-bold text-warning">${product.title}</h5>
                    <p class="card-text text-secondary">Category: ${categoryMap.get(product.categoryId)}</p>
                    <p class="card-text text-secondary">Left : ${product.count}</p>
                    <p class="card-text font-weight-bold text-dark">
                        Parameters: ${product.brand}, ${product.color}, ${product.weight} kg, ${product.diagonal} inch,
                        ${product.storage} Gb memory, ${product.ram} Gb RAM;
                    </p>
                    <p class="card-text font-weight-bold text-dark">${product.price} &#36;</p>
                    <c:url value="/add/${product.id}" var="add"/>
                    <a href="${add}">
                        <button type="button" class="btn btn-warning text-white font-weight-bold">To cart</button>
                    </a>
                </div>
            </div>
        </c:forEach>
        <hr>
    </div>
</main>

<!-- Footer -->
<footer class="footer mt-auto bg-secondary">
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