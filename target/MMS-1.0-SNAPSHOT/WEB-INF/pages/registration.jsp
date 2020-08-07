<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Registration</h1>
    <c:url value="/registration" var="registration"/>
    <form name='user' action="${registration}" method='POST'>
        <label for="name">Name:</label>
        <input type='text' name='name' id="name" value=''>
        <label for="surname">Surname:</label>
        <input type='text' name='surname' id="surname" />
        <label for="email">Email:</label>
        <input type='email' name='email' id="email" />
        <label for="password">Password:</label>
        <input type='password' name='password' id="password" />
        <label for="matching_password">Confirm password:</label>
        <input type='password' name='matchingPassword' id="matching_password" />
        <input name="submit" type="submit" value="submit" />
    </form>
</body>
</html>
