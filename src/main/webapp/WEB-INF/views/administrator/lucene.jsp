<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="pageTitle" code="title.lucene" />
<spring:message var="synchLabel" code="message.lucene.synchronously" />
<spring:message var="asynchLabel" code="message.lucene.asynchronously" />
<h1>${pageTitle}</h1>
<c:choose>
	<c:when test="${mode==true}">
		<p>${synchLabel}</p>
	</c:when>
	<c:otherwise>
		<p>${asynchLabel}</p>
	</c:otherwise>
</c:choose>
