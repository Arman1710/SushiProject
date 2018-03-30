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
                    <input type="hidden" name="action" value="indexPage"/>
                    <button class="headerBtn" type="submit">
                        <fmt:message key="header.mainMenu"/>
                    </button>
                </form>
            </div>
        </div>

        <span class="guestMesg">
                <fmt:message key="index.guestMesg"/>
        </span>

        <div class="basket-login-block">
            <div class="logReg-index">
                <form action="Controller" method="post">
                    <input type="hidden" name="action" value="loginPage"/>
                    <button class="headerBtn" type="submit">
                        <fmt:message key="header.login"/><br>
                    </button>
                </form>
                <form action="Controller" method="post">
                    <input type="hidden" name="action" value="registrationPage"/>
                    <button class="headerBtn" type="submit">
                        <fmt:message key="header.registration"/>
                    </button>
                </form>
            </div>
        </div>
    </div>

    <div class="menuRow">
        <div class="headerBtnDiv">
            <form action="Controller" method="post">
                <input type="hidden" name="action" value="rollsPage"/>
                <button class="headerBtn" type="submit">
                    <fmt:message key="header.rolls"/>
                </button>
            </form>
        </div>
        <div class="headerBtnDiv">
            <form action="Controller" method="post">
                <input type="hidden" name="action" value="setsPage"/>
                <button class="headerBtn" type="submit">
                    <fmt:message key="header.sets"/>
                </button>
            </form>
        </div>
    </div>
</header>