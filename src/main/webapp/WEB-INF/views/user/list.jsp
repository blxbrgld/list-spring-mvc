<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="pageTitle" code="title.user.list" />
<spring:message var="idLabel" code="label.id" />
<spring:message var="usernameLabel" code="label.username" />
<spring:message var="emailLabel" code="label.email" />
<spring:message var="roleLabel" code="label.role" />
<spring:message var="ascLabel" code="label.ascending" />
<spring:message var="descLabel" code="label.descending" />
<spring:message var="dateLabel" code="label.dateUpdated" />
<spring:message var="deleteLabel" code="label.delete" />
<spring:message var="editLabel" code="label.edit" />
<spring:message var="confirmLabel" code="label.delete.confirm" />
<spring:message var="confirmMessage" code="label.user.delete.confirm" />
<h1>${pageTitle}</h1>
<table class="table-view">
	<tr>
		<th>${idLabel}</th>
		<th>
			${usernameLabel}
			<a href="<c:url value='list?property=username&order=ASC' />" title="${ascLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/darr.png' />" /></a>	
			<a href="<c:url value='list?property=username&order=DESC' />" title="${descLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/uarr.png' />" /></a>	
		</th>
		<th>
			${emailLabel}
			<a href="<c:url value='list?property=email&order=ASC' />" title="${ascLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/darr.png' />" /></a>	
			<a href="<c:url value='list?property=email&order=DESC' />" title="${descLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/uarr.png' />" /></a>	
		</th>
		<th>
			${roleLabel}
			<a href="<c:url value='list?property=role.title&order=ASC' />" title="${ascLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/darr.png' />" /></a>	
			<a href="<c:url value='list?property=role.title&order=DESC' />" title="${descLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/uarr.png' />" /></a>	
		</th>
		<th>${dateLabel}</th>
		<th>&nbsp;</th>	
	</tr>
	<c:forEach var="users" items="${userList}">
		<tr>
			<td><c:out value="${users.id}" /></td>
			<td><c:out value="${users.username}" /></td>
			<td><c:out value="${users.email}" /></td>
			<td><c:out value="${users.role.title}" /></td>
			<td><spring:eval expression="users.dateUpdated" /></td>
			<td>
				<a href="<c:url value='update/${users.id}' />" title="${editLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/edit.png' />" /></a>
				<a href="<c:url value='delete/${users.id}' />" title="${deleteLabel}" class="dialog-confirm" data-open="user-list-delete"><img src="<c:url value='${contextPath}/resources/images/styles/delete.png' />" /></a>
			</td>
		</tr>
	</c:forEach>
</table>
<div class="dialog" id="user-list-delete" title="${confirmLabel}"><p>${confirmMessage}</p></div>