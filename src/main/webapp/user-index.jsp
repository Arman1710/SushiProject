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
    <title><fmt:message key="index.title"/></title>
</head>
<body bgcolor="#FFFFFF">
<div class=wrapper>
zeze
    <tagFile:user-header/>

    <content class="content">
        <div class="index-picture">
            <a href="user-rolls.jsp">
                <img src="images/index-main.jpg" alt="Картинка суши">
            </a>
        </div>
        <div class="product-column-wrapper">
            <div class="product-column">
                <div class="product-container">
                    <div class="product-image">
                        <a href="user-rolls.jsp" >
                            <img src="/images/rolls.png" style="height:150px; width:200px">
                        </a>
                    </div>
                    <span class="sushiName">
                         <fmt:message key="content.rolls"/>
                    </span>
                </div>
                <div class="product-container">
                    <div class="product-image">
                        <a href="user-sets.jsp" >
                            <img src="/images/sets.png" style="height:150px; width:200px">
                        </a>
                    </div>
                    <span class="sushiName">
                         <fmt:message key="content.sets"/>
                    </span>
                </div>
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