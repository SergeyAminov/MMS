<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>Categories</h3>
    <c:forEach var="category" items="${categoriesList}">
        <p>${category.title}</p>
    </c:forEach>
</body>
</html>
