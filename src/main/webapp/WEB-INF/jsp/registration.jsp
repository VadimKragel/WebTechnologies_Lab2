<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${requestScope.lang}"/>
<fmt:bundle basename="locale_data">
    <fmt:message key="shopName" var="shopName"/>
    <fmt:message key="page.registration" var="pageName"/>
    <fmt:message key="username" var="username"/>
    <fmt:message key="password" var="password"/>
    <fmt:message key="email" var="email"/>
    <fmt:message key="confirmPassword" var="confirmPassword"/>
    <fmt:message key="btnRegister" var="btnRegister"/>
    <fmt:message key="passwordMatchError" var="passwordMatchError"/>
    <fmt:message key="registration.success" var="successfulRegistration"/>
    <fmt:message key="registration.error" var="failedRegistration"/>
</fmt:bundle>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="shared/part-head.jsp"/>
    <script defer src="${pageContext.request.contextPath}/static/js/registration.js"></script>
    <title>${shopName} | ${pageName}</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty sessionScope.userId}">
        <c:redirect url="/">
            <c:param name="lang" value="${requestScope.lang}"/>
        </c:redirect>
    </c:when>
    <c:otherwise>
        <jsp:include page="shared/header.jsp"/>
        <form action="<c:url value='/actions/register'/>" method="post" id="register-form">
            <div class="form-container mx-auto">
                <label for="email">${email}</label>
                <input type="email" name="email" id="email" required>
                <label for="username">${username}</label>
                <input type="text" name="username" id="username" required maxlength="20">
                <label for="password">${password}</label>
                <input type="password" name="password" id="password" required minlength="7" maxlength="25">
                <label for="confirmPassword">${confirmPassword}</label>
                <input type="password" name="confirmPassword" id="confirmPassword" required minlength="7"
                       maxlength="25">
                <div id="validationMessage" class="error" hidden>${passwordMatchError}</div>
                <input class="align-self-start" type="submit" id="submit" value="${btnRegister}">
                <c:if test="${not empty applicationScope.isRegisterError}">
                    <c:choose>
                        <c:when test="${applicationScope.isRegisterError eq false}">
                            <div id="reg-message" class="f-bold success">${successfulRegistration}!</div>
                        </c:when>
                        <c:otherwise>
                            <div id="reg-message" class="f-bold error">${failedRegistration}</div>
                        </c:otherwise>
                    </c:choose>
                    <c:remove var="isRegisterError" scope="application"/>
                </c:if>
            </div>
        </form>
    </c:otherwise>
</c:choose>
</body>
</html>