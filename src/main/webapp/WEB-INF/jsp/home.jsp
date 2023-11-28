<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${requestScope.lang}"/>
<fmt:bundle basename="locale_data">
    <fmt:message key="shopName" var="shopName"/>
    <fmt:message key="page.home" var="pageName"/>
</fmt:bundle>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="shared/part-head.jsp"/>
    <title>${shopName} | ${pageName}</title>
</head>
<body>
<jsp:include page="shared/header.jsp"/>

</body>
</html>