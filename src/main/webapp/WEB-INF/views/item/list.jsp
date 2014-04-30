<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="categoryLabel" code="label.category" />
<spring:message var="artistsLabel" code="label.artist.plural" />
<spring:message var="titleLabel" code="label.title" />
<spring:message var="commentsLabel" code="label.comment.plural" />
<spring:message var="placeLabel" code="label.place" />
<spring:message var="discsLabel" code="label.discs" />
<spring:message var="ascLabel" code="label.ascending" />
<spring:message var="descLabel" code="label.descending" />
<spring:message var="deleteLabel" code="label.delete" />
<spring:message var="editLabel" code="label.edit" />
<spring:message var="confirmLabel" code="label.delete.confirm" />
<spring:message var="confirmMessage" code="label.item.delete.confirm" />
<c:choose>
	<c:when test="${pageHeaderAttribute==null}"><h1><spring:message code="${pageHeader}" /></h1></c:when>
	<c:otherwise><h1><spring:message code="${pageHeader}" arguments="${pageHeaderAttribute}" /></h1></c:otherwise>
</c:choose>
<table class="table-view">
	<tr>
		<th>${categoryLabel}<br /></th>
		<th>
			${artistsLabel}
			<c:if test="${not empty param.searchFor}">			
				<a href="<c:url value='list?searchFor=${param.searchFor}&searchIn=${param.searchIn}&property=artist&order=ASC&view=list' />" title="${ascLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/darr.png' />" /></a>	
				<a href="<c:url value='list?searchFor=${param.searchFor}&searchIn=${param.searchIn}&property=artist&order=DESC&view=list' />" title="${descLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/uarr.png' />" /></a>	
			</c:if>
		</th>
		<th>
			${titleLabel}
			<c:if test="${empty param.artist}">
				<a href="<c:url value='list?artist=${param.artist}&searchFor=${param.searchFor}&searchIn=${param.searchIn}&property=titleEng&order=ASC&view=list' />" title="${ascLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/darr.png' />" /></a>	
				<a href="<c:url value='list?artist=${param.artist}&searchFor=${param.searchFor}&searchIn=${param.searchIn}&property=titleEng&order=DESC&view=list' />" title="${descLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/uarr.png' />" /></a>	
			</c:if>
		</th>
		<th>${commentsLabel}</th>
		<th>
			${placeLabel}
			<c:if test="${empty param.searchFor}">
				<br />
				<a href="<c:url value='list?searchFor=${param.searchFor}&searchIn=${param.searchIn}&property=place&order=ASC&view=list' />" title="${ascLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/darr.png' />" /></a>	
				<a href="<c:url value='list?searchFor=${param.searchFor}&searchIn=${param.searchIn}&property=place&order=DESC&view=list' />" title="${descLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/uarr.png' />" /></a>	
			</c:if>
		</th>
		<th>
			${discsLabel}
			<c:if test="${empty param.searchFor}">
				<br />
				<a href="<c:url value='list?searchFor=${param.searchFor}&searchIn=${param.searchIn}&property=discs&order=ASC&view=list' />" title="${ascLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/darr.png' />" /></a>	
				<a href="<c:url value='list?searchFor=${param.searchFor}&searchIn=${param.searchIn}&property=discs&order=DESC&view=list' />" title="${descLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/uarr.png' />" /></a>	
			</c:if>
		</th>
		<th>&nbsp;</th>
	</tr>
	<c:forEach var="items" items="${itemList}">
		<tr>
			<td class="category"><c:out value="${items.category.title}" /></td>
			<td class="artist">
				<c:choose>
					<c:when test="${items.artistsString!=null}">
						<c:out value="${items.artistsString}" />
					</c:when>
					<c:otherwise>-</c:otherwise>
				</c:choose>
			</td>
			<td><c:out value="${items.titleEng}" /></td>
			<td>
				<c:choose>
					<c:when test="${items.commentsString!=null}">
						<c:out value="${items.commentsString}" />
					</c:when>
					<c:otherwise>-</c:otherwise>
				</c:choose>
			</td>
			<td class="pink">
				<c:choose>
					<c:when test="${items.place!=null}">
						<img src="<c:url value='${contextPath}/resources/images/styles/database.png' />" />
						<c:out value="${items.place}" />
					</c:when>
					<c:otherwise>-</c:otherwise>
				</c:choose>
			</td>
			<td class="pink">
				<c:choose>
					<c:when test="${items.discs!=null}">
						<img src="<c:url value='${contextPath}/resources/images/styles/disk.png' />" />
						<c:out value="${items.discs}" />
					</c:when>
					<c:otherwise>-</c:otherwise>
				</c:choose>
			</td>
			<td>
				<a href="<c:url value='update/${items.id}' />" title="${editLabel}"><img src="<c:url value='${contextPath}/resources/images/styles/edit.png' />" /></a>
				<a href="<c:url value='delete/${items.id}' />" title="${deleteLabel}" class="dialog-confirm" data-open="item-list-delete"><img src="<c:url value='${contextPath}/resources/images/styles/delete.png' />" /></a>
			</td>
		</tr>
	</c:forEach>
</table>
<div class="dialog" id="item-list-delete" title="${confirmLabel}"><p>${confirmMessage}</p></div>