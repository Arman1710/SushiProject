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
        <div class="indexPageDiv">
            <div class="headerBtnDiv">
                <form action="Controller" method="post">
                    <input type="hidden" name="action" value="user-indexPage"/>
                    <button class="headerBtn" type="submit">
                        <fmt:message key="header.mainMenu"/>
                    </button>
                </form>
            </div>
        </div>

        <div class="guestMesg">
            <fmt:message key="header.welcome"/> <br>
            ${login}
        </div>

        <div class="basket-login-block">
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
                <form action="Controller" method="post">
                    <input type="hidden" name="action" value="basketPage"/>
                    <button class="headerBtn" type="submit">
                        <fmt:message key="header.basket"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
    <div class="menuRow">
        <div class="headerBtnDiv">
            <form action="Controller" method="post">
                <input type="hidden" name="action" value="user-rollsPage"/>
                <button class="headerBtn" type="submit">
                    <fmt:message key="header.rolls"/>
                </button>
            </form>
        </div>
        <div class="headerBtnDiv">
            <form action="Controller" method="post">
                <input type="hidden" name="action" value="user-setsPage"/>
                <button class="headerBtn" type="submit">
                    <fmt:message key="header.sets"/>
                </button>
            </form>
        </div>
    </div>
</header>