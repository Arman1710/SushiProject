<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
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
    <title><fmt:message key="registration"/></title>
</head>
<body>
<div class="container" >
    <div class="backside" style="background-image: url(../images/loginBG.jpg)">
        <div class="block">
            <div class="langForm">
                <form action="Controller" method="post">
                    <input type="hidden" name="action" value="changeLocale"/>
                    <c:choose>
                        <c:when test="${sessionScope.locale.toString() == 'ru_KZ'}">
                            <input type="hidden" name="locale" value="en"/>
                            <input type="hidden" name="currentPage" value="/WEB-INF/registration.jsp"/>
                            <button class="ChangeLang" type="submit"> <img src="/images/flag-en.png" style="height:30px" width="30px"> </button>
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" name="locale" value="ru"/>
                            <input type="hidden" name="currentPage" value="/WEB-INF/registration.jsp"/>
                            <button class="ChangeLang" type="submit"> <img src="/images/flag-ru.png" style="height:30px" width="30px"> </button>
                        </c:otherwise>
                    </c:choose>
                </form>
            </div>
            <form action="Controller" method="POST">
                <span class="loginTitle">
                    <fmt:message key="registration"/>
                </span>
                <div class="wrap">
                <input class="input" type="text" name="email"  placeholder="<fmt:message key="registration.email"/>"><br>
                </div>
                <span class="messages">
                </span>

                <div class="wrap">
                    <input class="input" type="text" name="address" placeholder="*<fmt:message key="registration.address"/>"><br>
                </div>
                <span class="messages">
                </span>

                <div class="wrap">
                    <input class="input" type="text" name="phone" placeholder="*<fmt:message key="registration.phone"/>"><br>
                </div>
                <span class="messages">
                </span>

                <div class="wrap">
                    <input class="input" type="date" name="birthday" placeholder="<fmt:message key="registration.birthday"/>"><br>
                </div>

                <div class="wrap">
                    <input class="input" type="text" name="login" placeholder="*<fmt:message key="registration.login"/>"><br>
                </div>
                <span class="messages">
                </span>

                <div class="wrap">
                    <input class="input" type="password" name="password"  placeholder="*<fmt:message key="registration.password"/>"><br>
                </div>
                <span class="messages">
                    <c:if test="${not empty errorMsg}">
                        <fmt:message key="registration.${errorMsg}"/>
                    </c:if>
                </span>
                <div class="login">
                    <form>
                        <input type="hidden" name="action" value="registration"/>
                        <button class="login-button" type="submit" >
                            <fmt:message key="registration.checkIn"/>
                        </button>
                    </form>
                </div>
                <div class="textReg">
                    <form action="Controller" method="post">
                        <input type="hidden" name="action" value="loginPage"/>
                        <button class="logRegBtn" type="submit">
                            <fmt:message key="registration.loginExists"/>
                        </button>
                    </form>

                </div>
            </form>
        </div>
    </div>
</div>
<link type= "text/css" rel="stylesheet" href="../assets/css/style.css"/>
</body>
</html>
