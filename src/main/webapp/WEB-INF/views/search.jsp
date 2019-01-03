<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="searchLabel" code='label.search' />
<spring:message var="selectLabel" code="label.select" />
<spring:message var="musicLabel" code='label.music' />
<spring:message var="filmsLabel" code='label.films' />
<spring:message var="booksLabel" code='label.books' />
<spring:message var="artistLabel" code='label.artist' />
<form class="navbar-form navbar-right" id="searchForm" role="search" method="get" action="<c:url value="/item/list" />">
    <div class="form-group">
		<input type="text" name="searchFor" id="searchFor" class="form-control" placeholder="${searchLabel}">
	</div>
	<div class="form-group">
		<select name="searchIn" id="searchIn" class="form-control">
			<option value="">${selectLabel}</option>
			<option value="${musicLabel}">${musicLabel}</option>
			<option value="${filmsLabel}">${filmsLabel}</option>
			<option value="${booksLabel}">${booksLabel}</option>
			<option value="${artistLabel}">${artistLabel}</option>
		</select>
	</div>
	<button type="submit" class="btn btn-default">Search</button>
</form>