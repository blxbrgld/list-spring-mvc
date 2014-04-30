<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="pageTitle" code="title.artist.create" />
<spring:message var="detailsLabel" code="label.artist.details" />
<spring:message var="titleLabel" code="label.title" />
<spring:message var="descriptionLabel" code="label.description" />
<spring:message var="submitLabel" code="label.submit" />
<spring:message var="deleteLabel" code="label.delete" />
<spring:message var="confirmLabel" code="label.delete.confirm" />
<spring:message var="confirmMessage" code="label.artist.delete.confirm" />
<form:form action="." modelAttribute="artist">
	<h1>${pageTitle}</h1>
	<div><form:hidden path="id" /></div>
	<fieldset>
		<legend>${detailsLabel}</legend>
		<div>
			<form:label path="title">${titleLabel}</form:label>
			<form:input path="title" placeholder="${titleLabel}" />
			<form:errors path="title" class="errors" />
		</div>
		<div>
			<form:label path="description">${label.description}</form:label>
			<div class="textarea"><form:textarea path="description" /></div>
			<form:errors path="description" class="errors" />
		</div>
	</fieldset>
	<div class="centered addPadding">
		<input type="submit" value="${submitLabel}" class="btn" />
		<c:if test="${artist.id!=null}"><a href="<c:url value='/artist/delete/${artist.id}' />" class="btn dialog-confirm" data-open="artist-edit-delete">${deleteLabel}</a></c:if>
	</div>
</form:form>
<div class="dialog" id="artist-edit-delete" title="${confirmLabel}"><p>${confirmMessage}</p></div>