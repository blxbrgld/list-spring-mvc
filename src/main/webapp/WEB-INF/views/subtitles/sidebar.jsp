<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="createSubtitles" code="label.subtitles.create" />
<spring:message var="listSubtitles" code="title.subtitles.list" />
<div class="sidebar">	
	<c:choose>
		<c:when test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/list')}">
			<a href="<c:url value='/admin/subtitles/create' />" class="btn btn-primary btn-lg" role="button">${createSubtitles}</a>
		</c:when>
		<c:otherwise>
			<a href="<c:url value='/admin/subtitles/list' />" class="btn btn-primary btn-lg" role="button">${listSubtitles}</a>
		</c:otherwise>
	</c:choose>
</div>