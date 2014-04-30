<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="createUser" code="label.user.create" />
<spring:message var="listUsers" code="title.user.list" />
<spring:message var="listRoles" code="title.role.list" />
<c:choose>
	<c:when test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/list')}">
		<a href="<c:url value='/user/create' />" class='btn'>${createUser}</a>	
		<a href="<c:url value='/role/list' />" class='btn'>${listRoles}</a>
	</c:when>
	<c:otherwise>
		<a href="<c:url value='/user/list' />" class='btn'>${listUsers}</a>
	</c:otherwise>
</c:choose>