<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:url value="/login" var="login"/>
    <form name='f' action="${login}" method='POST'>
        <table>
            <tr>
                <td>User:</td>
                <td><input type='text' name='email' value=''></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='password' /></td>
            </tr>
            <tr>
                <td><input name="submit" type="submit" value="submit" /></td>
            </tr>
        </table>
    </form>
</body>
</html>
