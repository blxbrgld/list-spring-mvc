<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<spring:message var="createItem" code="label.item.create" />
<spring:message var="listItems" code="title.item.list" />
<spring:message var="randomArtist" code="label.artist.random" />
<spring:message var="orderByArtist" code="label.artist.orderBy" />
<spring:message var="orderByItem" code="label.item.orderBy" />
<spring:message var="viewList" code="label.view.list" />
<div class="sidebar">
	<c:choose>
		<c:when test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/list') && fn:contains(requestScope['javax.servlet.forward.query_string'],'view=list')}">
			<security:authorize access="hasRole('Administrator')">
				<a href="<c:url value='/admin/item/create' />" class="btn btn-primary btn-lg" role="button">${createItem}</a>
			</security:authorize>
			<security:authorize access="!hasRole('Administrator')">
				<a href="#" class="btn btn-primary btn-lg disabled" role="button">${createItem}</a>
			</security:authorize>
		</c:when>
		<c:when test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/list') && fn:contains(requestScope['javax.servlet.forward.query_string'],'artist=')}">
			<a href="<c:url value='/item/list?artist=-1' />" class="btn btn-primary btn-lg" role="button">${randomArtist}</a>
		</c:when>
		<c:when test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/list')}">
			<a href="<c:url value='/item/list?artist=-1' />" class="btn btn-primary btn-lg" role="button">${randomArtist}</a>
			<c:if test="${fn:length(itemList) gt 1}">
				<a href="<c:url value='/item/list?searchFor=${param.searchFor}&searchIn=${param.searchIn}&view=list' />" class="btn btn-primary btn-lg" role="button">${viewList}</a>
				<c:choose>
					<c:when test="${empty param.property}">
						<a href="<c:url value='/item/list?searchFor=${param.searchFor}&searchIn=${param.searchIn}&property=titleEng' />" class="btn btn-primary btn-lg" role="button">${orderByItem}</a>
						<a href="<c:url value='/item/list?searchFor=${param.searchFor}&searchIn=${param.searchIn}&property=artist' />" class="btn btn-primary btn-lg" role="button">${orderByArtist}</a>
					</c:when>
					<c:when test="${fn:contains(requestScope['javax.servlet.forward.query_string'],'property=titleEng')}">
						<a href="<c:url value='/item/list?searchFor=${param.searchFor}&searchIn=${param.searchIn}&property=artist' />" class="btn btn-primary btn-lg" role="button">${orderByArtist}</a>
					</c:when>
					<c:when test="${fn:contains(requestScope['javax.servlet.forward.query_string'],'property=artist')}">
						<a href="<c:url value='/item/list?searchFor=${param.searchFor}&searchIn=${param.searchIn}&property=titleEng' />" class="btn btn-primary btn-lg" role="button">${orderByItem}</a>
					</c:when>
				</c:choose>
			</c:if>
		</c:when>
		<c:otherwise>
			<a href="<c:url value='/item/list?view=list' />" class="btn btn-primary btn-lg" role="button">${listItems}</a>
		</c:otherwise>
	</c:choose>
</div>