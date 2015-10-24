<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="createArtist" code="label.artist.create" />
<spring:message var="listArtists" code="title.artist.list" />
<div class="sidebar">
	<c:choose>
		<c:when test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/list')}">
			<a href="<c:url value='/admin/artist/create' />" class="btn btn-primary btn-lg" role="button">${createArtist}</a>
		</c:when>
		<c:otherwise>
			<a href="<c:url value='/admin/artist/list' />" class="btn btn-primary btn-lg" role="button">${listArtists}</a>
		</c:otherwise>
	</c:choose>
</div>