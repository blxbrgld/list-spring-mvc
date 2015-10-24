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
<div class="content">
	<h3>${pageTitle}</h3>
	<form:form action="." modelAttribute="user">
		<form:hidden path="id" />
		<fieldset>
			<legend>${detailsLabel}</legend>
			<div class="form-group">
				<form:label path="username">${usernameLabel}</form:label>
				<form:input path="username" class="form-control" placeholder="${usernameLabel}" />
				<form:errors path="username" class="errors" />
			</div>
			<div class="form-group">
				<form:label path="password">${passwordLabel}</form:label>
				<form:password path="password" class="form-control" placeholder="${passwordLabel}" />
				<form:errors path="password" class="errors" />
			</div>
			<div class="form-group">
				<form:label path="confirmPassword">${confirmPasswordLabel}</form:label>
				<form:password path="confirmPassword" class="form-control" placeholder="${confirmPasswordLabel}" />
				<form:errors path="confirmPassword" class="errors" />
			</div>
			<div class="form-group">
				<form:label path="email">${emailLabel}</form:label>
				<form:input path="email" class="form-control" placeholder="${emailLabel}" />
				<form:errors path="email" class="errors" />
			</div>
		</fieldset>
		<fieldset>
			<legend>${roleLabel}</legend>
			<div class="form-group">
				<form:label path="role">${roleLabel}</form:label>
				<form:select path="role" class="form-control">
					<form:option value="" label="${roleSelectLabel}" />			
					<form:options items="${selectRole}" itemValue="id" itemLabel="title" />
				</form:select>
				<form:errors path="role" class="errors" />
			</div>
		</fieldset>
		<button class="btn btn-lg btn-primary" type="submit">${submitLabel}</button>
		<c:if test="${user.id!=null}">
			<a href="<c:url value='/admin/user/delete/${user.id}' />" class="btn btn-danger btn-lg confirm-dialog" role="button" dialog="${confirmMessage}" accept="${confirmLabel}">${deleteLabel}</a>
		</c:if>
	</form:form>
</div>