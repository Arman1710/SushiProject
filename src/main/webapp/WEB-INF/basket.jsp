<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tagFile" tagdir="/WEB-INF/tags" %>
<c:choose>
    <c:when test="${sessionScope.locale.toString() == 'ru_KZ'}">
        <fmt:setLocale value="ru_KZ"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en_US"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="header.basket"/></title>
</head>
<body>
<div class=wrapper>
    <tagFile:user-header/>
    <content class="content">
        <div class="basket-product-div">
            <p class="success">
                <c:if test="${not empty success}">
                    <fmt:message key="${success}"/>
                </c:if>
            </p>
            <table width="100%" class="basket-table">
                <thead>
                <tr>
                    <th><fmt:message key="basket.prodName"/></th>
                    <th><fmt:message key="basket.prodDescription"/></th>
                    <th><fmt:message key="basket.prodPrice"/></th>
                    <th><fmt:message key="basket.prodAmount"/></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="product" items="${productList}">
                    <tr>
                        <td>${product.name}</td>
                        <td>${product.description}</td>
                        <td>${product.cost}</td>
                        <td>${product.count}</td>
                        <td>
                            <form action="Controller" method="post">
                                <input type="hidden" name="action" value="productRemove"/>
                                <input type="hidden" name="prodId" value="${product.id}"/>
                                <button class="removeProduct" type="submit">
                                    <img src="/images/remove.png" style="width: 20px; height: 15px"/>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <fmt:message key="basket.totalCost"/> : ${totalCost} <fmt:message key="tenge"/>
            <c:if test="${not empty productList}">
                <form action="Controller" method="post">
                    <input type="hidden" name="action" value="checkout"/>
                    <button class="addToBasket" type="submit">
                        <fmt:message key="basket.checkout"/>
                    </button>
                </form>
            </c:if>
        </div>
    </content>

    <footer class="footer">
        <p><fmt:message key="footer"/></p>
    </footer>
</div>
<link type= "text/css" rel="stylesheet" href="../assets/css/style.css"/>
</body>
</html>
