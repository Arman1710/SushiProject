<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<header class="header">
    <div class="infoRow">
        <div class="description">
            <a href="user-index.jsp" class="sushiName">
                <fmt:message key="header.mainMenu"/>
            </a>
            <div class="telNumber">
                <fmt:message key="header.tel"/>
            </div>
        </div>

        <div class="basket-login-block">
            <div class="guestMesg">
                <fmt:message key="header.welcome"/> <br>
                ${user.login}
            </div>

            <div class="logReg-index">
                <form action="Controller" method="post">
                    <input type="hidden" name="action" value="logout"/>
                    <button class="logout-button" type="submit" >
                        <fmt:message key="logout"/>
                    </button>
                </form>
            </div>

            <div class="basket-block">
                <div class="basket-icon">
                </div>
                <div class="total">
                    <c:out value="${totalCost}" default="0"/> <fmt:message key="tenge"/>
                </div>
                <a class="orders-bottom" href="basket.jsp" >
                    <fmt:message key="header.basket"/>
                </a>
            </div>
        </div>
    </div>
    <div class="menuRow">
        <div class="product-menuRow">
            <div class="product-menuRow">
                <a href="user-rolls.jsp" class="rolls-menuRow">
                    <fmt:message key="header.rolls"/>
                </a>
                <a href="user-sets.jsp" class="sets-menuRow">
                    <fmt:message key="header.sets"/>
                </a>
        </div>
        <link type= "text/css" rel="stylesheet" href="../assets/css/style.css"/>
    </div>
    </div>
</header>