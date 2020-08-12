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
    <title>Edit address</title>
</head>
<body class="d-flex flex-column h-100">

<!-- Header -->
<header>
    <c:url value="/catalog" var="catalog"/>
    <c:url value="/registration" var="registration"/>
    <c:url value="/login" var="login"/>
    <c:url value="/profile" var="profile"/>
    <c:url value="/cart" var="cart"/>
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

    <!-- Set action variable -->
    <c:if test="${empty address.city}">
        <c:url value="/profile/addresses/add" var="var"/>
    </c:if>
    <c:if test="${!empty address.city}">
        <c:url value="/profile/addresses/edit" var="var"/>
    </c:if>

    <form action="${var}" method="POST">

        <c:if test="${!empty address.city}">
            <input type="hidden" name="id" value="${address.id}">
        </c:if>
        <input type="hidden" name="userId" value="${userId}">
        <input type="hidden" name="orderIdList" value="${address.orderIdList}">
        <label for="country">Country</label>
        <input type="text" name="country" id="country" value="${address.country}">
        <label for="city">City</label>
        <input type="text" name="city" id="city" value="${address.city}">
        <label for="street">Street</label>
        <input type="text" name="street" id="street" value="${address.street}">
        <label for="postcode">Postcode</label>
        <input type="number" name="postcode" id="postcode" value="${address.postcode}">
        <label for="building">Building</label>
        <input type="number" name="building" id="building" value="${address.building}">
        <label for="apartNumber">Apartment number</label>
        <input type="number" name="apartNumber" id="apartNumber" value="${address.apartNumber}">

        <c:if test="${!empty address.city}">
            <input type="submit" value="Edit address">
        </c:if>
        <c:if test="${empty address.city}">
            <input type="submit" value="Add new address">
        </c:if>

    </form>
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