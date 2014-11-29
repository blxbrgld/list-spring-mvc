<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="pageNotFoundTitle" code="404.title" />
<spring:message var="pageNotFoundText" code="404.text" />
<h1>${pageNotFoundTitle}</h1>
<p>${pageNotFoundText}</p>