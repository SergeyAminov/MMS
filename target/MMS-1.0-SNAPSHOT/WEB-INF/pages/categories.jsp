<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>Categories</h3>

    <c:url value="/categories/add" var="var"/>
    <form action="${var}" method="POST">
        <label for="title">Category</label>
        <input type="text" name="category" id="title">
        <input type="submit" value="Add category">
    </form>

    <c:forEach var="category" items="${categoriesList}">
        <c:url value="/categories/delete/${category.category}" var="delete"/>
        <c:url value="/categories/edit" var="edit"/>
        <hr>
        <p>${category.category}
            <form action="${edit}" method="POST">
                <label for="category">Category</label>
                <input type="text" name="category" id="category">
                <input type="submit" value="Change category">
            </form>
            <a href="${delete}">Delete category</a>
        </p>
    </c:forEach>
    <hr>
</body>
</html>