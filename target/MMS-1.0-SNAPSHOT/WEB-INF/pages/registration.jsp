<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
    <link href="<c:url value="/res/navbar-styles.css"/>" rel="stylesheet" type="text/css"/>
    <title>Registration</title>
</head>
<body class="d-flex flex-column h-100">

    <c:url value="/registration" var="registration"/>
    <form name='user' class="form-login container" action="${registration}" method='POST'>

        <h1 class="h3 mb-3 font-weight-bold text-center">Registration</h1>

        <label for="inputName" class="sr-only">Name</label>
        <input type="text" id="inputName" class="form-control mt-2" placeholder="Name" required name='name'>

        <label for="inputSurname" class="sr-only">Surname</label>
        <input type="text" id="inputSurname" class="form-control mt-2" placeholder="Surname" required name='surname'>

        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" class="form-control mt-2" placeholder="Email address" required name='email'>

        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control mt-2" placeholder="Password" name='password' required>

        <label for="inputMatchingPassword" class="sr-only">Password</label>
        <input type="password" id="inputMatchingPassword" class="form-control mt-2" placeholder="Matching password" name='matching_password' required>

        <button class="btn btn-lg btn-warning btn-block mt-2 text-light w-50 mx-auto font-weight-bold" type="submit">Registration</button>
        <p class="mt-5 mb-3 text-muted">&copy; 2020 Copyright: <a class="text-warning" href="#">Sergey Aminov</a></p>

    </form>
</body>
</html>
