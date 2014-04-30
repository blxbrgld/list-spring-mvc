<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="pageTitle" code="title.category.list" />
<spring:message var="idLabel" code="label.id" />
<spring:message var="titleLabel" code="label.title" />
<spring:message var="parentLabel" code="label.parent" />
<spring:message var="ascLabel" code="label.ascending" />
<spring:message var="descLabel" code="label.descending" />
<spring:message var="dateLabel" code="label.dateUpdated" />
<spring:message var="deleteLabel" code="label.delete" />
<spring:message var="editLabel" code="label.edit" />
<spring:message var="confirmLabel" code="label.delete.confirm" />
<spring:message var="confirmMessage" code="label.category.delete.confirm" />
<h1>${pageTitle}</h1>
<table class="table-view">
	<tr>
		<th>${idLabel}</th>
		<th>
			${titleLabel}
			<a href="<c:url value='list?property=title&order=ASC' />" title="${ascLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/darr.png' />" /></a>	
			<a href="<c:url value='list?property=title&order=DESC' />" title="${descLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/uarr.png' />" /></a>	
		</th>
		<th>
			${parentLabel}
			<a href="<c:url value='list?property=parent.title&order=ASC' />" title="${ascLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/darr.png' />" /></a>	
			<a href="<c:url value='list?property=parent.title&order=DESC' />" title="${descLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/uarr.png' />" /></a>	
		</th>
		<th>${dateLabel}</th>
		<th>&nbsp;</th>	
	</tr>
	<c:forEach var="categories" items="${categoryList}">
		<tr>
			<td><c:out value="${categories.id}" /></td>
			<td><c:out value="${categories.title}" /></td>
			<td>
				<c:choose>
					<c:when test="${categories.parent!=null}">
						<c:out value="${categories.parent.title}" />
					</c:when>
					<c:otherwise>-</c:otherwise>
				</c:choose>
			</td>
			<td><spring:eval expression="categories.dateUpdated" /></td>
			<td>
				<a href="<c:url value='update/${categories.id}' />" title="${editLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/edit.png' />" /></a>
				<a href="<c:url value='delete/${categories.id}' />" title="${deleteLabel}" class="dialog-confirm" data-open="category-list-delete"><img src="<c:url value='${contextPath}/resources/images/styles/delete.png' />" /></a>
			</td>
		</tr>
	</c:forEach>
</table>
<div class="dialog" id="category-list-delete" title="${confirmLabel}"><p>${confirmMessage}</p></div>