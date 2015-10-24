<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="createRole" code="label.role.create" />
<spring:message var="listRoles" code="title.role.list" />
<spring:message var="listUsers" code="title.user.list" />
<div class="sidebar">
	<c:choose>
		<c:when test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/list')}">
			<a href="<c:url value='/admin/role/create' />" class="btn btn-primary btn-lg" role="button">${createRole}</a>	
			<a href="<c:url value='/admin/user/list' />" class="btn btn-primary btn-lg" role="button">${listUsers}</a>
		</c:when>
		<c:otherwise>
			<a href="<c:url value='/admin/role/list' />" class="btn btn-primary btn-lg" role="button">${listRoles}</a>
		</c:otherwise>
	</c:choose>
</div>