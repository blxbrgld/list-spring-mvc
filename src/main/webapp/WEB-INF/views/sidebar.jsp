<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="randomArtist" code="label.artist.random" />
<div class="sidebar">
	<a href="<c:url value='/item/list?artist=-1' />" class="btn btn-primary btn-lg" role="button">${randomArtist}</a>
</div>