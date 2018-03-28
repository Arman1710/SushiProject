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
            <a href="index.jsp" class="sushiName">
                <fmt:message key="header.mainMenu"/>
            </a>
            <div class="telNumber">
                <fmt:message key="header.tel"/>
            </div>
        </div>

        <div class="basket-login-block">
            <span class="guestMesg">
                <fmt:message key="index.guestMesg"/>
            </span>

            <div class="logReg-index">
                <a class="logReg" href="login.jsp">
                    <fmt:message key="header.login"/><br>
                </a>
                <a class="logReg" id="logReg-id" href="registration.jsp">
                    <fmt:message key="header.registration"/>
                </a>
            </div>
        </div>
    </div>
    <div class="menuRow">
        <div class="product-menuRow">
            <a href="rolls.jsp" class="rolls-menuRow">
                <fmt:message key="header.rolls"/>
            </a>
            <a href="sets.jsp" class="sets-menuRow">
                <fmt:message key="header.sets"/>
            </a>
        </div>
        <link type= "text/css" rel="stylesheet" href="../assets/css/style.css"/>
    </div>
</header>