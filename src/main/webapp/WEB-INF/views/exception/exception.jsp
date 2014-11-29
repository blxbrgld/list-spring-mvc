<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="exceptionTitle" code="exception.title" />
<spring:message var="exceptionText" code="exception.text" />
<h1>${exceptionTitle}</h1>
<c:choose>
	<c:when test="${not empty exceptionMessage}">
		<p class="exception-message"><span>Error Message:</span> ${exceptionMessage}</p>
	</c:when>
	<c:otherwise>
		<p>${exceptionText}</p>	
	</c:otherwise>
</c:choose>