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
<div class="content">
	<h3>${pageTitle}</h3>
	<div class="table-responsive">
  		<table class="table table-striped">
  			<thead>
				<tr>
					<th class="hidden-xs">${idLabel}</th>
					<th>
						${titleLabel}
						<a href="<c:url value='list?property=title&order=ASC' />" title="${ascLabel}"><i class="fa fa-arrow-down"></i></a>	
						<a href="<c:url value='list?property=title&order=DESC' />" title="${descLabel}"><i class="fa fa-arrow-up"></i></a>	
					</th>
					<th class="hidden-xs">${dateLabel}</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="artists" items="${artistList}">
					<tr>
						<td class="hidden-xs"><c:out value="${artists.id}" /></td>
						<td><c:out value="${artists.title}" /></td>
						<td class="hidden-xs"><spring:eval expression="artists.dateUpdated" /></td>
						<td class="text-center text-nowrap">
							<a href="<c:url value='update/${artists.id}' />" title="${editLabel}"><i class="fa fa-pencil-square-o"></i></a>
							<a href="<c:url value='delete/${artists.id}' />" title="${deleteLabel}" class="confirm-dialog" role="button" dialog="${confirmMessage}" accept="${confirmLabel}"><i class="fa fa-times"></i></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>