<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:bundle basename="locale_data">
    <fmt:message key="user.name" var="name"/>
    <fmt:message key="user.surname" var="surname"/>
    <fmt:message key="user.patronymic" var="patronymic"/>
    <fmt:message key="user.birthDate" var="birthDate"/>
    <fmt:message key="user.username" var="username"/>
    <fmt:message key="user.email" var="email"/>
    <fmt:message key="user.passwordHash" var="passwordHash"/>
    <fmt:message key="user.edit" var="edit"/>
</fmt:bundle>
<html>
<head>
    <title>Редактируем пользователя</title>
</head>
<body>
<jsp:include page="../shared/header.jsp"/>
<form action="<c:url value="admin/users/change-user"/> ">
    <input type="hidden" name="userID" value="${requestScope.user.userId}">
    <div>
        <label for="name">${name}</label>
        <input type="text" id="name" name="name" value="${requestScope.user.name}">
    </div>
    <div>
        <label for="surname">${surname}</label>
        <input type="text" id="surname" name="surname" value="${requestScope.user.surname}">
    </div>
    <div>
        <label for="patronymic">${patronymic}</label>
        <input type="text" id="patronymic" name="patronymic" value="${requestScope.user.patronymic}">
    </div>
    <div>
        <label for="birthDate">${birthDate}</label>
        <input type="date" id="birthDate" name="birthDate" value="${requestScope.user.birthDate}">
    </div>
    <div>
        <label for="username">${username}</label>
        <input type="text" id="username" name="username" value="${requestScope.user.username}" required>
    </div>
    <div>
        <label for="email">${email}</label>
        <input type="email" id="email" name="email" value="${requestScope.user.email}" required>
    </div>
    <input type="submit" value="${edit}">
</form>
</body>
</html>
