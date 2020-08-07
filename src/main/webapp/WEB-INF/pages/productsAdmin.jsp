<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" class="h-100">
<head>
    <title>Title</title>
</head>
<body>
    <h2>Add</h2>
    <c:url value="/products/edit" var="var"/>
    <a href="${var}">Add new product</a>

    <h2>All products</h2>
    <c:forEach var="product" items="${productsList}">
        <div class="product">
            <h5>${product.title}</h5>
            <p>Category: ${product.categoryId}</p>
            <p>Parameters: ${product.brand}, ${product.color}, ${product.weight} kg, ${product.diagonal} inch,
                    ${product.storage} Gb memory, ${product.ram} Gb RAM;
            </p>
            <p>${product.price} &#36;</p>
            <p>${product.count}</p>
            <p>
                <c:url value="/products/edit/${product.id}" var="edit"/>
                <a href="${edit}">edit</a>
                <c:url value="/products/delete/${product.id}" var="delete"/>
                <a href="${delete}">delete</a>
            </p>
        </div>
        <hr>
    </c:forEach>
</body>
</html>
