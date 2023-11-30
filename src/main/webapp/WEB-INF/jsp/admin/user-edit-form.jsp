<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="by.bsuir.lab2.controller.data.UpdateState" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${requestScope.lang}"/>
<fmt:bundle basename="locale_data">
    <fmt:message key="shopName" var="shopName"/>
    <fmt:message key="page.edit" var="pageName"/>
    <fmt:message key="user.name" var="name"/>
    <fmt:message key="user.surname" var="surname"/>
    <fmt:message key="user.patronymic" var="patronymic"/>
    <fmt:message key="user.birthDate" var="birthDate"/>
    <fmt:message key="user.username" var="username"/>
    <fmt:message key="user.email" var="email"/>
    <fmt:message key="user.role" var="role"/>
    <fmt:message key="user.save" var="save"/>
    <fmt:message key="user.save" var="save"/>
    <fmt:message key="update.error" var="failedUpdate"/>
    <fmt:message key="update.success" var="successfulUpdate"/>
    <fmt:message key="update.notChanged" var="notChangedUpdate"/>
    <fmt:message key="update.lastAdmin" var="lastAdminUpdate"/>
</fmt:bundle>
<html>
<head>
    <jsp:include page="../shared/part-head.jsp"/>
    <title>${shopName} | ${pageName}</title>
</head>
<body>
<jsp:include page="../shared/header.jsp"/>
<c:set var="user" value="${requestScope.user}" />
<form action="<c:url value="/actions/user/edit"/>" method="post">
    <div class="edit-form-container">
        <input type="hidden" name="id" value="${user.id}">
        <table>
            <tbody>
            <c:if test="${not empty requestScope.availableRoles}">
                <td><label for="role-select">${role}</label></td>
                <td><select id="role-select" name="roleId">
                    <c:forEach var="availableRole" items="${requestScope.availableRoles}">
                        <option value="${availableRole.id}"
                                <c:if test="${availableRole.id eq user.role.id}">selected</c:if>
                        >${availableRole.name}</option>
                    </c:forEach>
                </select></td>
            </c:if>
            <tr>
                <td><label for="username">${username}</label></td>
                <td><input type="text" id="username" name="username" value="${user.username}" required></td>
            </tr>
            <tr>
                <td><label for="email">${email}</label></td>
                <td><input type="email" id="email" name="email" value="${user.email}" required></td>
            </tr>
            <tr>
                <td><label for="name">${name}</label></td>
                <td><input type="text" id="name" name="name" value="${user.name}"></td>
            </tr>
            <tr>
                <td><label for="surname">${surname}</label>
                <td><input type="text" id="surname" name="surname" value="${user.surname}"></td>
            </tr>
            <tr>
                <td><label for="patronymic">${patronymic}</label>
                <td><input type="text" id="patronymic" name="patronymic" value="${user.patronymic}"></td>
            </tr>
            <tr>
                <td><label for="birthDate">${birthDate}</label></td>
                <td><input type="date" id="birthDate" name="birthDate" value="${user.birthDate}"></td>
            </tr>
            </tbody>
        </table>
        <input type="submit" value="${save}">
        <c:if test="${not empty applicationScope.updateUserState}">
            <c:choose>
                <c:when test="${applicationScope.updateUserState eq UpdateState.UPDATED}">
                    <div id="update-message" class="f-bold success">${successfulUpdate}!</div>
                </c:when>
                <c:when test="${applicationScope.updateUserState eq UpdateState.NOT_CHANGED}">
                    <div id="update-message" class="f-bold info">${notChangedUpdate}</div>
                </c:when>
                <c:when test="${applicationScope.updateUserState eq UpdateState.LAST_ADMIN}">
                    <div id="update-message" class="f-bold error">${lastAdminUpdate}</div>
                </c:when>
                <c:otherwise>
                    <div id="update-message" class="f-bold error">${failedUpdate}</div>
                </c:otherwise>
            </c:choose>
            <c:remove var="updateUserState" scope="application"/>
        </c:if>
    </div>
</form>
</body>
</html>
