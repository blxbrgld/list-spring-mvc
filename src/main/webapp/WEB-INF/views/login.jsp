<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:url var="postLoginUrl" value="/j_spring_security_check" />
<spring:message var="pageTitle" code="title.login" />
<spring:message var="loginLabel" code="label.login" />
<spring:message var="loginFailedLabel" code="error.login.failed" />
<spring:message var="usernameLabel" code="label.username" />
<spring:message var="passwordLabel" code="label.password" />
<spring:message var="rememberMeLabel" code="label.rememberMe" />
<div class="content">
	<h3>${pageTitle}</h3>
	<c:if test="${param.failed==true}">
		<div class="errors">${loginFailedLabel}</div>
	</c:if>
	<form action="${postLoginUrl}" method="post">
		<label for="j_username" class="sr-only">${usernameLabel}</label>
        <input type="text" id="j_username" name="j_username" class="form-control" placeholder="${usernameLabel}" required autofocus>
        <label for="j_password" class="sr-only">${passwordLabel}</label>
        <input type="password" id="j_password" name="j_password" class="form-control" placeholder="${passwordLabel}" required>
        <div class="checkbox">
        	<label for="_spring_security_remember_me">
            	<input type="checkbox" name="_spring_security_remember_me">${rememberMeLabel}
          	</label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">${loginLabel}</button>
	</form>
</div>