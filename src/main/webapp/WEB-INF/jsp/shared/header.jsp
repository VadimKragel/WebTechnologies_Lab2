<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${requestScope.lang}"/>
<fmt:bundle basename="locale_data">
    <fmt:message key="page.home" var="home"/>
    <fmt:message key="page.login" var="login"/>
    <fmt:message key="page.registration" var="registration"/>
    <fmt:message key="page.logout" var="logout"/>
    <fmt:message key="admin.users" var="users"/>
</fmt:bundle>
<fmt:setBundle basename="list_languages" var="langNames"/>
<header>
    <c:choose>
        <c:when test="${not empty requestScope.supportedLanguages}">
            <label for="lang-select">Language:</label>
            <select id="lang-select" onchange="window.location.href = this.value">
                <c:forEach var="supportLanguage" items="${requestScope.supportedLanguages}">
                    <option value="?lang=${supportLanguage}"
                            <c:if test="${supportLanguage eq requestScope.lang}">selected</c:if>
                    ><fmt:message key="${supportLanguage}" bundle="${langNames}"/></option>
                </c:forEach>
            </select>
        </c:when>
    </c:choose>
    <a href="<c:url value='/'><c:param name='lang' value='${requestScope.lang}'/></c:url>">${home}</a>
    <c:choose>
        <c:when test="${not empty sessionScope.user}">
            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                <a href="<c:url value="/admin/users"/>">${users}</a>
                <span>${sessionScope.user.role}</span>
            </c:if>
            <span>${sessionScope.user.username}</span>
            <a href="<c:url value='/actions/logout'/>">${logout}</a>
        </c:when>
        <c:otherwise>
            <a href="<c:url value='/login'><c:param name='lang' value='${requestScope.lang}'/></c:url>">${login}</a>
            <a href="<c:url value='/register'><c:param name='lang' value='${requestScope.lang}'/></c:url>">${registration}</a>
        </c:otherwise>
    </c:choose>
    <br>
</header>