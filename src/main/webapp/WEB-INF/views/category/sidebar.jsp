<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="createCategory" code="label.category.create" />
<spring:message var="listCategories" code="title.category.list" />
<div class="sidebar">
	<c:choose>
		<c:when test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/list')}">
			<a href="<c:url value='/admin/category/create' />" class="btn btn-primary btn-lg" role="button">${createCategory}</a>
		</c:when>
		<c:otherwise>
			<a href="<c:url value='/admin/category/list' />" class="btn btn-primary btn-lg" role="button">${listCategories}</a>
		</c:otherwise>
	</c:choose>
</div>