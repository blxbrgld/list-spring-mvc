<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="pageTitle" code="title.home" />
<div class="content">
	<h3>${pageTitle}</h3>
	<%--Music Items--%>
	<ul class="list-group">
  		<li class="list-group-item">
  			<i class="fa fa-music"></i>
  			<spring:message code="home.totalMusic" arguments="${totalPopular+totalClassical+totalGreek},${totalPopular},${totalClassical},${totalGreek}" argumentSeparator="," />
  		</li>
  		<li class="list-group-item">
  			<i class="fa fa-calendar-check-o"></i>
			<spring:message code="home.lastMusic" arguments="${lastMusicDate}" />
		</li>
		<li class="list-group-item">
  			<i class="fa fa-database"></i>
			<spring:message code="home.nextMusic" arguments="${nextMusic}" />
		</li>
	</ul>
	<%--Film Items--%>
	<ul class="list-group">
		<li class="list-group-item">
			<i class="fa fa-film"></i>
			<spring:message code="home.totalFilm" arguments="${totalDVD+totalDivX},${totalDVD},${totalDivX}" argumentSeparator="," />
		</li>
		<li class="list-group-item">
			<i class="fa fa-calendar-check-o"></i>
			<spring:message code="home.lastFilm" arguments="${lastFilmDate}" />
		</li>
		<li class="list-group-item">
			<i class="fa fa-database"></i>
			<spring:message code="home.nextFilm" arguments="${nextFilm}" />
		</li>
	</ul>
	<%--Artists--%>
	<ul class="list-group">
		<li class="list-group-item">
			<i class="fa fa-user"></i>
			<spring:message code="home.totalArtists" arguments="${totalArtists},${totalActivities}" argumentSeparator="," />
		</li>
		<li class="list-group-item">
			<i class="fa fa-database"></i>
			<spring:message code="home.lastArtist" arguments="${lastArtistDate}" />
		</li>
	</ul>
</div>