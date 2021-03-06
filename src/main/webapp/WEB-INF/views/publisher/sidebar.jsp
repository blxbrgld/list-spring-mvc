<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="createPublisher" code="label.publisher.create" />
<spring:message var="listPublishers" code="title.publisher.list" />
<div class="sidebar">
	<c:choose>
		<c:when test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/list')}">
			<a href="<c:url value='/admin/publisher/create' />" class="btn btn-primary btn-lg" role="button">${createPublisher}</a>
		</c:when>
		<c:otherwise>
			<a href="<c:url value='/admin/publisher/list' />" class="btn btn-primary btn-lg" role="button">${listPublishers}</a>
		</c:otherwise>
	</c:choose>
</div>