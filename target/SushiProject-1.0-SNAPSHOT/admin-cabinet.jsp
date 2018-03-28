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
    <title><fmt:message key="admincab.title"/> </title>
</head>
<body>
<div class=wrapper>
    <header class="header">
        <div class="infoRow">
            <div class="basket-login-block">
                <div class="logReg-index">
                    <form action="Controller" method="post">
                        <input type="hidden" name="action" value="logout"/>
                        <button class="logout-button" type="submit" >
                            <fmt:message key="logout"/>
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </header>
    <content class="content">

        <div class="basket-product-div">
            <form method="post" action="Controller">
                <input type="hidden" name="action" value="showAllOrders">
                <button class="showOrders" type="submit">
                    <fmt:message key="admincab.showOrders"/>
                </button>
            </form>
            <table width="100%" class="basket-table">
                <thead>
                <tr>
                    <th><fmt:message key="admincab.orderId"/></th>
                    <th><fmt:message key="admincab.orderUserId"/></th>
                    <th><fmt:message key="admincab.orderCost"/></th>
                    <th><fmt:message key="admincab.orderDateCr"/></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="orders" items="${ordersList}">
                    <tr>
                        <td>${orders.id}</td>
                        <td>${orders.userId}</td>
                        <td>${orders.cost}</td>
                        <td>${orders.dateCreated}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </content>

    <footer class="footer">
        <p><fmt:message key="footer"/></p>
    </footer>
    <link type= "text/css" rel="stylesheet" href="assets/css/style.css"/>
</div>
</body>
</html>