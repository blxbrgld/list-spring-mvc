<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="deniedLabel" code="label.denied" />
<spring:message var="deniedMessage" code="message.denied" />
<div class="content">
	<h3><spring:message code="${deniedLabel}" /></h3>
	<p><spring:message code="${deniedMessage}" /></p>
</div>