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

  <tagFile:header/>

  <content class="content">
    <div class="index-picture">
        <a href="#">
            <img src="../images/index-main.jpg" alt="Картинка суши">
        </a>
    </div>
      <div class="product-column-wrapper">
          <div class="product-column">
              <div class="product-container">
                  <div class="product-image">
                      <div class="headerBtnDiv">
                          <form action="Controller" method="post">
                              <input type="hidden" name="action" value="rollsPage"/>
                              <button class="headerBtn" type="submit">
                                  <img src="/images/rolls.png" style="height:150px; width:200px">
                                  <fmt:message key="header.rolls"/>
                              </button>
                          </form>
                      </div>
                  </div>
              </div>
              <div class="product-container">
                  <div class="product-image">
                      <div class="headerBtnDiv">
                          <form action="Controller" method="post">
                              <input type="hidden" name="action" value="setsPage"/>
                              <button class="headerBtn" type="submit">
                                  <img src="/images/sets.png" style="height:150px; width:200px">
                                  <fmt:message key="header.sets"/>
                              </button>
                          </form>
                      </div>
                  </div>
              </div>
          </div>
      </div>
  </content>

  <footer class="footer">
    <p><fmt:message key="footer"/></p>
  </footer>
</div>
<link type= "text/css" rel="stylesheet" href="../assets/css/style.css"/>
</body>
</html>