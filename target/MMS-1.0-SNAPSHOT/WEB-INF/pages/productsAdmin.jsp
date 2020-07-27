<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" class="h-100">
<head>
    <title>Title</title>
</head>
<body>
    <h2>Add</h2>
    <c:url value="products/add" var="add"/>
    <a href="${add}">Add new product</a>

    <h2>All products</h2>
    <c:forEach var="product" items="${productsList}">
        <div class="product">
            <h5>${product.title}</h5>
            <p>${product.category}</p>
            <p>${product.parameters}</p>
            <p>${product.price} &#36;</p>
            <p>${product.count}</p>
            <p>
                <a href="products/edit/${product.id}">edit</a>
                <a href="products/delete/${product.id}">delete</a>
            </p>
        </div>
        <hr>
    </c:forEach>
</body>
</html>
