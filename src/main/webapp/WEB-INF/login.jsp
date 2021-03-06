<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title><fmt:message key="login.title"/> </title>
</head>
<body>
<div class="container">
    <div class="backside" style="background-image: url(../images/loginBG.jpg)">
        <div class="block">
            <div class="langForm">
                <form action="Controller" method="get">
                    <input type="hidden" name="action" value="changeLocale"/>
                    <c:choose>
                        <c:when test="${sessionScope.locale.toString() == 'ru_KZ'}">
                            <input type="hidden" name="locale" value="en"/>
                            <input type="hidden" name="currentPage" value="/WEB-INF/login.jsp"/>
                            <button class="ChangeLang" type="submit"> <img src="/images/flag-en.png" style="height:30px" width="30px"> </button>
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" name="locale" value="ru"/>
                            <input type="hidden" name="currentPage" value="/WEB-INF/login.jsp"/>
                            <button class="ChangeLang" type="submit"> <img src="/images/flag-ru.png" style="height:30px" width="30px"> </button>
                        </c:otherwise>
                    </c:choose>
                </form>
            </div>
            <form action="Controller" method="post">
                <input type="hidden" name="action" value="login"/>
                 <span class="loginTitle">
                    <fmt:message key="login.loginTitle"/>
                 </span>

                <div class="wrap">
                    <input class="input" type="text" name="login"  placeholder="<fmt:message key="name"/>">
                </div>

                <div class="wrap">
                    <input class="input" type="password" name="password"  placeholder="<fmt:message key="password"/>">
                </div>
                <span class="messages">

                    <c:if test="${not empty loginError}">
                     <fmt:message key="login.${loginError}"/>
                    </c:if>
                </span>

                <div class="login">
                    <button class="login-button" type="submit" >
                        <fmt:message key="login-button"/>
                    </button>
                </div>
            </form>
            <div class="login">
            <form action="Controller" method="get">
                <input type="hidden" name="action" value="registrationPage"/>
                <button class="logRegBtn" type="submit">
                    <fmt:message key="registration"/>
                </button>
            </form>
                <form action="Controller" method="get">
                    <input type="hidden" name="action" value="loginLikeGuest"/>
                    <button class="logRegBtn" type="submit" >
                        <fmt:message key="login.guestMode"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
<link type="text/css" rel="stylesheet" href="../assets/css/style.css"/>
</body>
</html>