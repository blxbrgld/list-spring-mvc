<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="createActivity" code="label.activity.create" />
<spring:message var="listActivities" code="title.activity.list" />
<div class="sidebar">
	<c:choose>
		<c:when test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/list')}">
			<a href="<c:url value='/admin/activity/create' />" class="btn btn-primary btn-lg" role="button">${createActivity}</a>
		</c:when>
		<c:otherwise>
			<a href="<c:url value='/admin/activity/list' />" class="btn btn-primary btn-lg" role="button">${listActivities}</a>
		</c:otherwise>
	</c:choose>
</div>