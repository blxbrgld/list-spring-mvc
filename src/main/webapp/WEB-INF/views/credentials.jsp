<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<spring:message var="loginLabel" code="label.login" />
<spring:message var="logoutLabel" code="label.logout" />
<c:url var="loginUrl" value="/login.html" />
<c:url var="logoutUrl" value="/j_spring_security_logout" />
<div class="credentials text-right">
	<security:authorize access="isAnonymous()">
	 	<a href="${loginUrl}">${loginLabel}</a>
	</security:authorize>
	<security:authorize access="isAuthenticated()">
		<security:authentication property="principal.username" />
		[<security:authentication property="principal.role.title" />]
		<a href="${logoutUrl}">${logoutLabel}</a>
	</security:authorize>
</div>