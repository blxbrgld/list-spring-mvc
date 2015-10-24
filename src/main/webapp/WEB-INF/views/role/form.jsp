<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="pageTitle" code="title.role.create" />
<spring:message var="detailsLabel" code="label.role.details" />
<spring:message var="titleLabel" code="label.title" />
<spring:message var="deleteLabel" code="label.delete" />
<spring:message var="submitLabel" code="label.submit" />
<spring:message var="confirmLabel" code="label.delete.confirm" />
<spring:message var="confirmMessage" code="label.role.delete.confirm" />
<div class="content">
	<h3>${pageTitle}</h3>
	<form:form action="." modelAttribute="role">
		<form:hidden path="id" />
		<div class="form-group">
			<form:label path="title">${titleLabel}</form:label>
			<form:input path="title" class="form-control" placeholder="${titleLabel}" />
			<form:errors path="title" class="errors" />
		</div>
		<button class="btn btn-lg btn-primary" type="submit">${submitLabel}</button>
		<c:if test="${role.id!=null}">
			<a href="<c:url value='/admin/role/delete/${role.id}' />" class="btn btn-danger btn-lg confirm-dialog" role="button" dialog="${confirmMessage}" accept="${confirmLabel}">${deleteLabel}</a>
		</c:if>
	</form:form>
</div>