<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="categoryLabel" code="label.category" />
<spring:message var="artistsLabel" code="label.artist.plural" />
<spring:message var="titleLabel" code="label.title" />
<spring:message var="commentsLabel" code="label.comment.plural" />
<spring:message var="ascLabel" code="label.ascending" />
<spring:message var="descLabel" code="label.descending" />
<spring:message var="deleteLabel" code="label.delete" />
<spring:message var="editLabel" code="label.edit" />
<spring:message var="confirmLabel" code="label.delete.confirm" />
<spring:message var="confirmMessage" code="label.item.delete.confirm" />
<div class="content">
	<c:choose>
		<c:when test="${pageHeaderAttribute==null}"><h3><spring:message code="${pageHeader}" /></h3></c:when>
		<c:otherwise><h3><spring:message code="${pageHeader}" arguments="${pageHeaderAttribute}" /></h3></c:otherwise>
	</c:choose>
	<div class="table-responsive">
  		<table class="table table-striped">
			<thead>
				<tr>
					<th class="text-center hidden-xs">${categoryLabel}<br /></th>
					<th>
						${artistsLabel}
						<c:if test="${not empty param.searchFor}">			
							<a href="<c:url value='list?searchFor=${param.searchFor}&searchIn=${param.searchIn}&property=artist&order=ASC&view=list' />" title="${ascLabel}"><i class="fa fa-arrow-down"></i></a>
							<a href="<c:url value='list?searchFor=${param.searchFor}&searchIn=${param.searchIn}&property=artist&order=DESC&view=list' />" title="${descLabel}"><i class="fa fa-arrow-up"></i></a>
						</c:if>
					</th>
					<th>
						${titleLabel}
						<c:if test="${empty param.artist}">
							<a href="<c:url value='list?artist=${param.artist}&searchFor=${param.searchFor}&searchIn=${param.searchIn}&property=titleEng&order=ASC&view=list' />" title="${ascLabel}"><i class="fa fa-arrow-down"></i></a>
							<a href="<c:url value='list?artist=${param.artist}&searchFor=${param.searchFor}&searchIn=${param.searchIn}&property=titleEng&order=DESC&view=list' />" title="${descLabel}"><i class="fa fa-arrow-up"></i></a>
						</c:if>
					</th>
					<th class="text-center">${commentsLabel}</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="items" items="${itemList}">
					<tr>
						<td class="text-center hidden-xs">
							<c:out value="${items.category.title}" />
						</td>
						<td>
							<c:choose>
								<c:when test="${items.artistsString!=null}">
									<c:out value="${items.artistsString}" />
								</c:when>
								<c:otherwise>-</c:otherwise>
							</c:choose>
						</td>
						<td><c:out value="${items.titleEng}" /></td>
						<td class="text-center">
							<c:choose>
								<c:when test="${items.commentsString!=null}">
									<c:out value="${items.commentsString}" />
								</c:when>
								<c:otherwise>-</c:otherwise>
							</c:choose>
						</td>
						<td class="text-center text-nowrap">
							<a href="<c:url value='/admin/item/update/${items.id}' />" title="${editLabel}"><i class="fa fa-pencil-square-o"></i></a>
							<a href="<c:url value='/admin/item/delete/${items.id}' />" title="${deleteLabel}" class="confirm-dialog" dialog="${confirmMessage}" accept="${confirmLabel}"><i class="fa fa-times"></i></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>