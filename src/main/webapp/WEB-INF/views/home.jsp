<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="pageTitle" code="title.home" />
<h1>${pageTitle}</h1>
<table class="info-view">
	<tr>
		<td><img src="<c:url value='${contextPath}/resources/images/styles/headphones.png' />" /></td>
		<td><spring:message code="home.totalMusic" arguments="${totalPopular+totalClassical+totalGreek},${totalPopular},${totalClassical},${totalGreek}" argumentSeparator="," /></td>
	</tr>
	<tr>
		<td><img src="<c:url value='${contextPath}/resources/images/styles/calendar.png' />" /></td>
		<td><spring:message code="home.lastMusic" arguments="${lastMusicDate}" /></td>
	</tr>
	<tr>
		<td><img src="<c:url value='${contextPath}/resources/images/styles/comment.png' />" /></td>
		<td><spring:message code="home.nextMusic" arguments="${nextMusic}" /></td>
	</tr>
</table>
<table class="info-view">
	<tr>
		<td><img src="<c:url value='${contextPath}/resources/images/styles/movie.png' />" /></td>
		<td><spring:message code="home.totalFilm" arguments="${totalDVD+totalDivX},${totalDVD},${totalDivX}" argumentSeparator="," /></td>
	</tr>
	<tr>
		<td><img src="<c:url value='${contextPath}/resources/images/styles/calendar.png' />" /></td>
		<td><spring:message code="home.lastFilm" arguments="${lastFilmDate}" /></td>
	</tr>
	<tr>
		<td><img src="<c:url value='${contextPath}/resources/images/styles/comment.png' />" /></td>
		<td><spring:message code="home.nextFilm" arguments="${nextFilm}" /></td>
	</tr>
</table>
<table class="info-view">
	<tr>
		<td><img src="<c:url value='${contextPath}/resources/images/styles/user.png' />" /></td>
		<td><spring:message code="home.totalArtists" arguments="${totalArtists},${totalActivities}" argumentSeparator="," /></td>
	</tr>
	<tr>
		<td><img src="<c:url value='${contextPath}/resources/images/styles/calendar.png' />" /></td>
		<td><spring:message code="home.lastArtist" arguments="${lastArtistDate}" /></td>
	</tr>
</table>