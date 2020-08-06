<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Registration</h1>
    <c:url value="/login" var="login"/>
    <form name='f' action="${login}" method='POST'>
        <label for="user">Name:</label>
        <input type='text' name='username' id="user" value=''>
        <label for="surname">Surname:</label>
        <input type='text' name='surname' id="surname" />
        <label for="email">Email:</label>
        <input type='email' name='email' id="email" />
        <label for="password">Password:</label>
        <input type='password' name='password' id="password" />
        <label for="confirm_password">Confirm password:</label>
        <input type='password' name='confirm_password' id="confirm_password" />
        <input name="submit" type="submit" value="submit" />
    </form>
</body>
</html>
