<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>Categories</h3>

    <c:url value="categories/add" var="var"/>
    <form action="${var}" method="POST">
        <label for="title">Category</label>
        <input type="text" name="category" id="title">
        <input type="submit" value="Add category">
    </form>

    <c:forEach var="category" items="${categoriesList}">
        <p>${category.category} : <a href="categories/delete/${category.category}">delete</a></p>
    </c:forEach>
</body>
</html>