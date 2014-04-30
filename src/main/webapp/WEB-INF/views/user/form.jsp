<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="pageTitle" code="title.user.create" />
<spring:message var="detailsLabel" code="label.user.details" />
<spring:message var="usernameLabel" code="label.username" />
<spring:message var="passwordLabel" code="label.password" />
<spring:message var="confirmPasswordLabel" code="label.confirmPassword" />
<spring:message var="emailLabel" code="label.email" />
<spring:message var="roleLabel" code="label.role" />
<spring:message var="roleSelectLabel" code="label.role.select" />
<spring:message var="submitLabel" code="label.submit" />
<spring:message var="deleteLabel" code="label.delete" />
<spring:message var="confirmLabel" code="label.delete.confirm" />
<spring:message var="confirmMessage" code="label.user.delete.confirm" />
<form:form action="." modelAttribute="user">
	<h1>${pageTitle}</h1>
	<div><form:hidden path="id" /></div>
	<fieldset>
		<legend>${detailsLabel}</legend>
		<div>
			<form:label path="username">${usernameLabel}</form:label>
			<form:input path="username" placeholder="${usernameLabel}" />
			<form:errors path="username" class="errors" />
		</div>
		<div>
			<form:label path="password">${passwordLabel}</form:label>
			<form:password path="password" placeholder="${passwordLabel}" />
			<form:errors path="password" class="errors" />
		</div>
		<div>
			<form:label path="confirmPassword">${confirmPasswordLabel}</form:label>
			<form:password path="confirmPassword" placeholder="${confirmPasswordLabel}" />
			<form:errors path="confirmPassword" class="errors" />
		</div>
		<div>
			<form:label path="email">${emailLabel}</form:label>
			<form:input path="email" placeholder="${emailLabel}" />
			<form:errors path="email" class="errors" />
		</div>
	</fieldset>
	<fieldset>
		<legend>${roleLabel}</legend>
		<div>
			<form:label path="role">${roleLabel}</form:label>
			<form:select path="role">
				<form:option value="" label="${roleSelectLabel}" />			
				<form:options items="${selectRole}" itemValue="id" itemLabel="title" />
			</form:select>
			<form:errors path="role" class="errors" />
		</div>
	</fieldset>
	<div class="centered addPadding">
		<input type="submit" value="${submitLabel}" class="btn" />
		<c:if test="${user.id!=null}"><a href="<c:url value='/user/delete/${user.id}' />" class="btn dialog-confirm" data-open="user-edit-delete">${deleteLabel}</a></c:if>
	</div>
</form:form>
<div class="dialog" id="user-edit-delete" title="${confirmLabel}"><p>${confirmMessage}</p></div>