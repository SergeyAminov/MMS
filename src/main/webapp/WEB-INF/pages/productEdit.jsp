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
        <c:url value="/products/add" var="var"/>
    </c:if>
    <c:if test="${!empty product.title}">
        <c:url value="/products/edit" var="var"/>
    </c:if>

    <form action="${var}" method="POST">

        <c:if test="${!empty product.title}">
            <input type="hidden" name="id" value="${product.id}">
            <input type="hidden" name="title" value="${product.title}">
            <input type="hidden" name="price" value="${product.price}">
            <input type="hidden" name="parameters" value="${product.parametersDto}">
        </c:if>

        <%--

        <c:if test="${empty product.title}">
            <label for="title">Title</label>
            <input type="text" name="title" id="title" value="${product.title}">
            <label for="price">Price</label>
            <input type="number" name="price" id="price" value="${product.price}">
            <input type="hidden" name="parameters" value="${product.parameters}">
        </c:if>

        --%>

        <label for="count">Count</label>
        <input type="number" name="count" id="count" value="${product.count}">
        <p>
            <label for="category">Choose category</label>
            <select size="3" multiple name="category" id="category">
                <c:forEach var="category" items="${categoriesList}">
                    <option value="${category.id}">${category.title}</option>
                </c:forEach>
            </select>
        </p>

        <c:if test="${!empty product.title}">
            <input type="submit" value="Edit product">
        </c:if>

        <%--

        <c:if test="${empty product.title}">
            <input type="submit" value="Add new product">
        </c:if>

        --%>

    </form>
</body>
</html>
