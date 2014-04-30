<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="createSubtitles" code="label.subtitles.create" />
<spring:message var="listSubtitles" code="title.subtitles.list" />
<c:choose>
	<c:when test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/list')}">
		<a href="<c:url value='/subtitles/create' />" class='btn'>${createSubtitles}</a>
	</c:when>
	<c:otherwise>
		<a href="<c:url value='/subtitles/list' />" class='btn'>${listSubtitles}</a>
	</c:otherwise>
</c:choose>