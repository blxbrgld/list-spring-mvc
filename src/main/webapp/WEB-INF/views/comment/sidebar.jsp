<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="createComment" code="label.comment.create" />
<spring:message var="listComments" code="title.comment.list" />
<div class="sidebar">
	<c:choose>
		<c:when test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/list')}">
			<a href="<c:url value='/admin/comment/create' />" class="btn btn-primary btn-lg" role="button">${createComment}</a>
		</c:when>
		<c:otherwise>
			<a href="<c:url value='/admin/comment/list' />" class="btn btn-primary btn-lg" role="button">${listComments}</a>
		</c:otherwise>
	</c:choose>
</div>