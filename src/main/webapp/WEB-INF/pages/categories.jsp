<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>Categories</h3>

    <c:url value="/categories/add" var="add"/>
    <form action="${add}" method="POST">
        <label for="title">Category</label>
        <input type="text" name="title" id="title">
        <input type="submit" value="Add category">
    </form>

    <c:forEach var="category" items="${categoriesList}">
        <c:url value="/categories/delete/${category.id}" var="delete"/>
        <c:url value="/categories/edit" var="edit"/>
        <hr>
        <p>${category.title}
            <form action="${edit}" method="POST">
                <input type="hidden" name="id" value="${category.id}">
                <label for="category">Change title:</label>
                <input type="text" name="title" id="category">
                <input type="submit" value="Change category">
            </form>
            <a href="${delete}">Delete category</a>
        </p>
    </c:forEach>
    <hr>
</body>
</html>