<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="luceneLabel" code="label.lucene.rebuild" />
<spring:message var="confirmLabel" code="label.delete.confirm" />
<spring:message var="confirmMessage" code="message.lucene.confirm" />
<spring:message var="favoritesLabel" code="label.favorites" />
<a href="<c:url value='/item/list?searchFor=-1&searchIn=Lists' />">${favoritesLabel}</a>
<c:forEach var="categoriesTree" items="${categoriesTree}">
	<a href="<c:url value='/item/export?parent=${categoriesTree.key}' />"><spring:message code="label.export" arguments="${categoriesTree.key}" /></a>
</c:forEach>
<a href="<c:url value='/administrator/lucene?mode=synchronously' />" class="dialog-confirm" data-open="lucene-dialog">${luceneLabel}</a>
<div class="dialog" id="lucene-dialog" title="${confirmLabel}"><p>${confirmMessage}</p></div>