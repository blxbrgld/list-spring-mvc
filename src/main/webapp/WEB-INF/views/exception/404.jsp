<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="pageNotFoundTitle" code="404.title" />
<spring:message var="pageNotFoundText" code="404.text" />
<div class="content">
	<h3>${pageNotFoundTitle}</h3>
	<p>${pageNotFoundText}</p>
</div>