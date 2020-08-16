<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" class="h-100">
<head>
    <title>Title</title>
</head>
<body>
    <h2>Add</h2>
    <c:url value="/admin/products/add" var="add"/>
    <a href="${add}">Add new product</a>

    <h2>All products</h2>
        <table>
            <tr>
                <th>Title</th>
                <th>Category</th>
                <th>Brand</th>
                <th>Color</th>
                <th>Weight (kg)</th>
                <th>Diagonal (inch)</th>
                <th>Storage(Gb)</th>
                <th>RAM(Gb)</th>
                <th>Price (&#36;)</th>
                <th>Count</th>
                <th>Action</th>
            </tr>
            <c:forEach var="product" items="${productsList}">
                <c:url value="/admin/products/edit/${product.id}" var="edit"/>
                <c:url value="/admin/products/delete/${product.id}" var="delete"/>
                <tr>
                    <td>${product.title}</td>
                    <td>${categoryMap.get(product.categoryId)}</td>
                    <td>${product.brand}</td>
                    <td>${product.color}</td>
                    <td>${product.weight}</td>
                    <td>${product.diagonal}</td>
                    <td>${product.storage}</td>
                    <td>${product.ram}</td>
                    <td>${product.price}</td>
                    <td>${product.count}</td>
                    <td><a href="${edit}">edit</a> <a href="${delete}">delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <hr>
</body>
</html>
