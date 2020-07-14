<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12.07.2020
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
    <h1>Catalog</h1>
    <c:forEach var="product" items="${productsList}">
        <div class>
            <h3>${product.title}</h3>
            <p>Category : ${product.category}</p>
            <p>Price : ${product.price}</p>
        </div>
        <hr>
    </c:forEach>
</body>
</html>
