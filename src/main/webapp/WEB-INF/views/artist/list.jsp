<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="pageTitle" code="title.artist.list" />
<spring:message var="idLabel" code="label.id" />
<spring:message var="titleLabel" code="label.title" />
<spring:message var="ascLabel" code="label.ascending" />
<spring:message var="descLabel" code="label.descending" />
<spring:message var="dateLabel" code="label.dateUpdated" />
<spring:message var="deleteLabel" code="label.delete" />
<spring:message var="editLabel" code="label.edit" />
<spring:message var="confirmLabel" code="label.delete.confirm" />
<spring:message var="confirmMessage" code="label.artist.delete.confirm" />
<h1>${pageTitle}</h1>
<table class="table-view">
	<tr>
		<th>${idLabel}</th>
		<th>
			${titleLabel}
			<a href="<c:url value='list?property=title&order=ASC' />" title="${ascLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/darr.png' />" /></a>	
			<a href="<c:url value='list?property=title&order=DESC' />" title="${descLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/uarr.png' />" /></a>	
		</th>
		<th>${dateLabel}</th>
		<th>&nbsp;</th>
	</tr>
	<c:forEach var="artists" items="${artistList}">
		<tr>
			<td><c:out value="${artists.id}" /></td>
			<td><c:out value="${artists.title}" /></td>
			<td><spring:eval expression="artists.dateUpdated" /></td>
			<td>
				<a href="<c:url value='update/${artists.id}' />" title="${editLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/edit.png' />" /></a>
				<a href="<c:url value='delete/${artists.id}' />" title="${deleteLabel}" class="dialog-confirm" data-open="artist-list-delete"><img src="<c:url value='${contextPath}/resources/images/styles/delete.png' />" /></a>
			</td>
		</tr>
	</c:forEach>
</table>
<div class="dialog" id="artist-list-delete" title="${confirmLabel}"><p>${confirmMessage}</p></div>