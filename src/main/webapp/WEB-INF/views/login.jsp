<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:url var="postLoginUrl" value="/j_spring_security_check" />
<spring:message var="pageTitle" code="title.login" />
<spring:message var="loginLabel" code="label.login" />
<spring:message var="loginFailedLabel" code="error.login.failed" />
<spring:message var="usernameLabel" code="label.username" />
<spring:message var="passwordLabel" code="label.password" />
<spring:message var="rememberMeLabel" code="label.rememberMe" />
<h1>${pageTitle}</h1>
<form action="${postLoginUrl}" method="post">
	<fieldset>
		<legend>${loginLabel}</legend>
		<c:if test="${param.failed==true}">
			<div class="errors">${loginFailedLabel}</div>
		</c:if>	
		<div>
			<label for="j_username">${usernameLabel}</label>
			<input type="text" name="j_username" placeholder="${usernameLabel}" />
		</div>
		<div>
			<label>${passwordLabel}</label>
			<input type="password" name="j_password" placeholder="${passwordLabel}" />
		</div>
		<div>
			<label for="_spring_security_remember_me"></label>
			<input type="checkbox" name="_spring_security_remember_me" />${rememberMeLabel}
		</div>
	</fieldset>
	<div class="centered addPadding">
		<input type="submit" value="${loginLabel}" class="btn" />
	</div>
</form>