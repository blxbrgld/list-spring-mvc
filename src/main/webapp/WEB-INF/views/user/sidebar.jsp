<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="createUser" code="label.user.create" />
<spring:message var="listUsers" code="title.user.list" />
<spring:message var="listRoles" code="title.role.list" />
<div class="sidebar">
	<c:choose>
		<c:when test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/list')}">
			<a href="<c:url value='/admin/user/create' />" class="btn btn-primary btn-lg" role="button">${createUser}</a>	
			<a href="<c:url value='/admin/role/list' />" class="btn btn-primary btn-lg" role="button">${listRoles}</a>
		</c:when>
		<c:otherwise>
			<a href="<c:url value='/admin/user/list' />" class="btn btn-primary btn-lg" role="button">${listUsers}</a>
		</c:otherwise>
	</c:choose>
</div>