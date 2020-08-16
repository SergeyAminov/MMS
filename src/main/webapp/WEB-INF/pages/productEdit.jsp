<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" class="h-100">
<head>
    <c:if test="${empty product.title}">
        <title>Add</title>
    </c:if>
    <c:if test="${!empty product.title}">
        <title>Edit</title>
    </c:if>
</head>
<body>

    <!-- Set action variable -->
    <c:if test="${empty product.title}">
        <c:url value="/admin/products/add" var="var"/>
    </c:if>
    <c:if test="${!empty product.title}">
        <c:url value="/admin/products/edit" var="var"/>
    </c:if>

    <form action="${var}" method="POST">

        <c:if test="${!empty product.title}">
            <input type="hidden" name="id" value="${product.id}">
        </c:if>
        <label for="count">Count</label>
        <input type="number" name="count" id="count" value="${product.count}">
        <label for="title">Title</label>
        <input type="text" name="title" id="title" value="${product.title}">
        <label for="price">Price</label>
        <input type="number" step=any name="price" id="price" value="${product.price}">
        <label for="brand">Brand</label>
        <input type="text" name="brand" id="brand" value="${product.brand}">
        <label for="color">Color</label>
        <input type="text" name="color" id="color" value="${product.color}">
        <label for="weight">Weight</label>
        <input type="number" step=any name="weight" id="weight" value="${product.weight}">
        <label for="diagonal">Diagonal</label>
        <input type="number" step=any name="diagonal" id="diagonal" value="${product.diagonal}">
        <label for="storage">Storage</label>
        <input type="number" step=any name="storage" id="storage" value="${product.storage}">
        <label for="ram">RAM</label>
        <input type="number" name="ram" id="ram" value="${product.ram}">

        <p>
            <label for="category">Choose category</label>
            <select size="3" name="categoryId" id="category">
                <c:forEach var="category" items="${categoryMap}">
                    <option value="${category.key}">${category.value}</option>
                </c:forEach>
            </select>
        </p>

        <c:if test="${!empty product.title}">
            <input type="submit" value="Edit product">
        </c:if>
        <c:if test="${empty product.title}">
            <input type="submit" value="Add new product">
        </c:if>

    </form>
</body>
</html>
