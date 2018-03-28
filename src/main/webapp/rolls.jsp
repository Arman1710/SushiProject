<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tagFile" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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
    <title>Роллы</title>
</head>
<body bgcolor="#FFFFFF">
<div class=wrapper>

    <tagFile:header/>

    <content class="content">
        <div class="product-column-wrapper">
            <div class="title-hits">
                <fmt:message key="content.rolls"/>
            </div>
            <div class="product-column">
                <c:forEach var="rolls" items="${rolls}">
                    <div class="product-container">
                        <div class="product-image">
                            <img src="images/${rolls.image}">
                        </div>
                        <div class="product-name">
                                ${rolls.name}
                        </div>
                        <div class="index-price">
                                ${rolls.cost} <fmt:message key="tenge"/>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </content>

    <footer class="footer">
        <p><fmt:message key="footer"/></p>
    </footer>
</div>
<link type= "text/css" rel="stylesheet" href="assets/css/style.css"/>
</body>
</html>
