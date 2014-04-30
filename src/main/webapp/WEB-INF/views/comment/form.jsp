<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="pageTitle" code="title.comment.create" />
<spring:message var="detailsLabel" code="label.comment.details" />
<spring:message var="titleLabel" code="label.title" />
<spring:message var="submitLabel" code="label.submit" />
<spring:message var="deleteLabel" code="label.delete" />
<spring:message var="confirmLabel" code="label.delete.confirm" />
<spring:message var="confirmMessage" code="label.comment.delete.confirm" />
<form:form action="." modelAttribute="comment">
	<h1>${pageTitle}</h1>
	<div><form:hidden path="id" /></div>
	<fieldset>
		<legend>${detailsLabel}</legend>
		<div>
			<form:label path="title">${titleLabel}</form:label>
			<form:input path="title" placeholder="${titleLabel}" />
			<form:errors path="title" class="errors" />
		</div>
	</fieldset>
	<div class="centered addPadding">
		<input type="submit" value="${submitLabel}" class="btn" />
		<c:if test="${comment.id!=null}"><a href="<c:url value='/comment/delete/${comment.id}' />" class="btn dialog-confirm" data-open="comment-edit-delete">${deleteLabel}</a></c:if>
	</div>	
</form:form>
<div class="dialog" id="comment-edit-delete" title="${confirmLabel}"><p>${confirmMessage}</p></div>