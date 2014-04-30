<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="pageTitle" code="title.category.create" />
<spring:message var="detailsLabel" code="label.category.details" />
<spring:message var="titleLabel" code="label.title" />
<spring:message var="parentLabel" code="label.parent" />
<spring:message var="parentSelectLabel" code="label.parent.select" />
<spring:message var="submitLabel" code="label.submit" />
<spring:message var="deleteLabel" code="label.delete" />
<spring:message var="confirmLabel" code="label.delete.confirm" />
<spring:message var="confirmMessage" code="label.category.delete.confirm" />
<form:form action="." modelAttribute="category">
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
			<form:label path="parent">${parentLabel}</form:label>
			<form:select path="parent">
				<form:option value="" label="${parentSelectLabel}" />			
				<form:options items="${selectParent}" itemValue="id" itemLabel="title" />
			</form:select>
			<form:errors path="parent" class="errors" />
		</div>
	</fieldset>
	<div class="centered addPadding">
		<input type="submit" value="${submitLabel}" class="btn" />
		<c:if test="${category.id!=null}"><a href="<c:url value='/category/delete/${category.id}' />" class="btn dialog-confirm" data-open="category-edit-delete">${deleteLabel}</a></c:if>
	</div>
</form:form>
<div class="dialog" id="category-edit-delete" title="${confirmLabel}"><p>${confirmMessage}</p></div>