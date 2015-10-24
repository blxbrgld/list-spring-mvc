<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="exceptionTitle" code="exception.title" />
<spring:message var="exceptionText" code="exception.text" />
<div class="content">
	<h3>${exceptionTitle}</h3>
	<c:choose>
		<c:when test="${not empty exceptionMessage}">
			<p><span>Error Message:</span> ${exceptionMessage}</p>
		</c:when>
		<c:otherwise>
			<p>${exceptionText}</p>	
		</c:otherwise>
	</c:choose>
</div>