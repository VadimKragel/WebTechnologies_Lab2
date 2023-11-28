<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${requestScope.lang}"/>
<fmt:bundle basename="locale_data">
    <fmt:message key="shopName" var="shopName"/>
    <fmt:message key="page.login" var="pageName"/>
    <fmt:message key="username" var="username"/>
    <fmt:message key="password" var="password"/>
    <fmt:message key="email" var="email"/>
    <fmt:message key="btnLogin" var="btnLogin"/>
    <fmt:message key="login.error" var="failedLogin"/>
</fmt:bundle>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="shared/part-head.jsp"/>
    <title>${shopName} | ${pageName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
</head>
<body>
<c:choose>
    <c:when test="${not empty sessionScope.user}">
        <c:redirect url="/">
            <c:param name="lang" value="${requestScope.lang}"/>
        </c:redirect>
    </c:when>
    <c:otherwise>
        <jsp:include page="shared/header.jsp"/>
        <form action="<c:url value='/actions/login'/>" method="post">
            <div class="form-container">
                <label for="login">${username}/${email}</label>
                <input type="text" name="login" id="login" required maxlength="60">
                <label for="password">${password}</label>
                <input type="password" name="password" id="password" required minlength="7">
                <input type="submit" value="${btnLogin}">
                <c:if test="${not empty applicationScope.isLoginError}">
                    <c:choose>
                        <c:when test="${applicationScope.isLoginError eq true}">
                            <div id="login-message" class="login-message login-error">${failedLogin}</div>
                        </c:when>
                    </c:choose>
                    <c:remove var="login_message" scope="application"/>
                </c:if>
            </div>
        </form>
    </c:otherwise>
</c:choose>
</body>
</html>