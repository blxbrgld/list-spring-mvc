<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="createCategory" code="label.category.create" />
<spring:message var="listCategories" code="title.category.list" />
<c:choose>
	<c:when test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/list')}">
		<a href="<c:url value='/category/create' />" class='btn'>${createCategory}</a>
	</c:when>
	<c:otherwise>
		<a href="<c:url value='/category/list' />" class='btn'>${listCategories}</a>
	</c:otherwise>
</c:choose>