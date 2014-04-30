<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="searchLabel" code='label.search' />
<spring:message var="selectLabel" code="label.select" />
<spring:message var="musicLabel" code='label.music' />
<spring:message var="filmsLabel" code='label.films' />
<spring:message var="artistLabel" code='label.artist' />
<form class="search-form" method="get" action="<c:url value="/item/list" />" >
	<input type="text" name="searchFor" id="searchFor" placeholder="${searchLabel}" />
	<select name="searchIn">
		<option value="">${selectLabel}</option>
		<option value="${musicLabel}">${musicLabel}</option>
		<option value="${filmsLabel}">${filmsLabel}</option>
		<option value="${artistLabel}">${artistLabel}</option>
	</select>
	<input type="button" value="" class="submitImage" onclick="validateSearchFor()" />
</form>