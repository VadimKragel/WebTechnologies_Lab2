<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:setLocale value="${requestScope.lang}"/>
<fmt:bundle basename="locale_data">
    <fmt:message key="shopName" var="shopName"/>
    <fmt:message key="admin.users" var="pageName"/>
    <fmt:message key="user.id" var="id"/>
    <fmt:message key="user.role" var="role"/>
    <fmt:message key="user.name" var="name"/>
    <fmt:message key="user.surname" var="surname"/>
    <fmt:message key="user.patronymic" var="patronymic"/>
    <fmt:message key="user.birthDate" var="birthdate"/>
    <fmt:message key="user.username" var="username"/>
    <fmt:message key="user.email" var="email"/>
    <fmt:message key="user.actions" var="actions"/>
    <fmt:message key="user.edit" var="edit"/>
</fmt:bundle>
<html>
<head>
    <jsp:include page="../shared/part-head.jsp"/>
    <title>${shopName} | ${pageName}</title>
</head>
<body>
<jsp:include page="../shared/header.jsp"/>
<table class="object-table">
    <thead>
    <tr>
        <th>${id}</th>
        <th>${role}</th>
        <th>${username}</th>
        <th>${email}</th>
        <th>${name}</th>
        <th>${surname}</th>
        <th>${patronymic}</th>
        <th>${birthdate}</th>
        <th>${actions}</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${requestScope.listUsers}">
        <tr>
            <td>${user.id}</td>
            <td>${user.role.name}</td>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>${user.patronymic}</td>
            <td><fmt:formatDate value="${user.birthDate}" type="date" dateStyle="short"/></td>
            <td>
                <a href="<c:url value='/admin/user'>
                            <c:param name='lang' value='${requestScope.lang}'/>
                            <c:param name="id" value="${user.id}"/>
                         </c:url>">${edit}</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
