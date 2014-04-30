<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<spring:message var="noneListedLabel" code="label.activity.noneListed" />
<spring:message var="noSubsLabel" code='label.subtitles.none' />
<spring:message var="greekSubsLabel" code='label.subtitles.greek' />
<spring:message var="englishSubsLabel" code='label.subtitles.english' />
<spring:message var="conductorsLabel" code="label.activity.conductor.plural" />
<spring:message var="actorsLabel" code="label.activity.actor.plural" />
<spring:message var="directorsLabel" code="label.activity.director.plural" />
<spring:message var="descriptionLabel" code='label.description' />
<spring:message var="noResultsLabel" code="title.item.search.noResults" />
<spring:message var="editLabel" code="label.edit" />
<c:choose>
	<c:when test="${pageHeaderAttribute==null}"><h1><spring:message code="${pageHeader}" /></h1></c:when>
	<c:otherwise><h1><spring:message code="${pageHeader}" arguments="${pageHeaderAttribute}" /></h1></c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${fn:length(itemList) gt 0}">
		<c:forEach var="items" items="${itemList}">
			<c:choose>
				<c:when test="${items.category.id==1 || items.category.parent.id==1}">
					<!-- Music Items -->
					<div class="itemContainer">
						<div class="thumbsLeft">
							<c:choose>
								<c:when test="${items.photoPath!=null}">
									<img class="photo" src="<c:url value='${contextPath}/resources/images/items/${items.photoPath}' />" />
								</c:when>
								<c:otherwise>
									<img class="photo" src="<c:url value='${contextPath}/resources/images/items/no-image.png' />" />
								</c:otherwise>
							</c:choose>
							<div class="info">
								<c:if test="${items.place!=null}">
									<img src="<c:url value='${contextPath}/resources/images/styles/database.png' />" />${items.place}
								</c:if>
								<c:choose>
									<c:when test="${items.discs!=null && items.discs>1}"><c:set var="discs" value="${items.discs} Discs" /></c:when>
									<c:otherwise><c:set var="discs" value="1 Disc" /></c:otherwise>
								</c:choose>
								<img src="<c:url value='${contextPath}/resources/images/styles/disk.png' />" />${discs}
								<security:authorize access="hasRole('Administrator')">
									<a class="edit" href="<c:url value='update/${items.id}' />">${editLabel}</a>
								</security:authorize>
							</div>
						</div>
						<div class="thumbsRight">
							<%-- Loop Through Artists & Check if Artists With Activity='Musician' Exist --%>
							<c:set var="artistFound" value="0" />
							<c:forEach var="loopArtists" items="${items.artistActivityItems}">
								<c:if test="${loopArtists.idActivity.title=='Musician'}">
									<c:set var="artistFound" value="1" />
								</c:if>
							</c:forEach>
							<div class="title-main">	
								<c:choose>
									<c:when test="${artistFound==1}">
										<c:set var="artistFound" value="0" />
										<c:forEach var="loopArtists" items="${items.artistActivityItems}">
											<c:if test="${loopArtists.idActivity.title=='Musician'}">
												<c:if test="${artistFound==1}"> | </c:if>
												<c:set var="artistFound" value="1" />
												<a href="<c:url value='/item/list?artist=${loopArtists.idArtist.id}' />">${loopArtists.idArtist.title}</a>
											</c:if>
										</c:forEach>
									</c:when>
									<c:otherwise>
										${noneListedLabel}
									</c:otherwise>
								</c:choose>
							</div>
							<%-- Loop Through Artists & Check if Artists With Activity='Conductor' Exist --%>
							<c:set var="artistFound" value="0" />
							<c:forEach var="loopArtists" items="${items.artistActivityItems}">
								<c:if test="${loopArtists.idActivity.title=='Conductor'}">
									<c:set var="artistFound" value="1" />
								</c:if>
							</c:forEach>
							<!-- Conductors Row Only If Conductors Exist -->
							<c:if test="${artistFound==1}">
								<div class="title-main">
									<c:set var="artistFound" value="0" />
									<c:forEach var="loopArtists" items="${items.artistActivityItems}">
										<c:if test="${loopArtists.idActivity.title=='Conductor'}">
											<c:if test="${artistFound==1}"> | </c:if>
											<c:set var="artistFound" value="1" />
											<a href="<c:url value='/item/list?artist=${loopArtists.idArtist.id}' />">${loopArtists.idArtist.title}</a>
										</c:if>
									</c:forEach>
									[ ${conductorsLabel} ]
								</div>			
							</c:if>			
							<div class="title-sub">${items.titleEng} <c:if test="${items.year!=null}">(${items.year})</c:if></div>
							<div class="tags">
			  					<span class="tag">${items.category.title}</span>
								<c:if test="${items.commentItems!=null}">
									<c:forEach var="loopComments" items="${items.commentItems}">
										<span class="tag short">${loopComments.idComment.title}</span>
									</c:forEach>
								</c:if>
								<c:if test="${items.description!=null}">
									<img id="descriptionImage" src="<c:url value='${contextPath}/resources/images/styles/document.png' />" title="${descriptionLabel}" />
								</c:if>
								<c:if test="${items.rating!=null}">
									<img class="rating${items.rating}" src="<c:url value='${contextPath}/resources/images/styles/blank.gif' />" />
								</c:if>
							</div>
						</div>
						<div class="description invisible">${items.description}</div>
					</div>
				</c:when>
				<c:when test="${items.category.id==2 || items.category.parent.id==2}">
					<!--  Film Items -->
					<div class="itemContainer">
						<div class="thumbsLeft">
							<c:choose>
								<c:when test="${items.photoPath!=null}">
									<img class="photo taller" src="<c:url value='${contextPath}/resources/images/items/${items.photoPath}' />" />
								</c:when>
								<c:otherwise>
									<img class="photo" src="<c:url value='${contextPath}/resources/images/items/no-image.png' />" />
								</c:otherwise>
							</c:choose>
							<div class="info">
								<c:if test="${items.subtitles!=null}">
									<span>
										<c:choose>
											<c:when test="${items.subtitles.id==1}">
												<img src="<c:url value='${contextPath}/resources/images/styles/x.png' />" title="${noSubsLabel}" />
											</c:when>
											<c:when test="${items.subtitles.id==2}">
												<img src="<c:url value='${contextPath}/resources/images/styles/greece.gif' />" title="${greekSubsLabel}" />
											</c:when>
											<c:otherwise>
												<img src="<c:url value='${contextPath}/resources/images/styles/usa.gif' />" title="${englishSubsLabel}" />
											</c:otherwise>
										</c:choose>
									</span>
								</c:if>
								<c:if test="${items.place!=null}">
									<span><img src="<c:url value='${contextPath}/resources/images/styles/database.png' />" />${items.place}</span>
								</c:if>
								<c:choose>
									<c:when test="${items.discs!=null && items.discs>1}"><c:set var="discs" value="${items.discs} Discs" /></c:when>
									<c:otherwise><c:set var="discs" value="1 Disc" /></c:otherwise>
								</c:choose>
								<span><img src="<c:url value='${contextPath}/resources/images/styles/disk.png' />" />${discs}</span>
								<security:authorize access="hasRole('Administrator')">
									<span><a class="edit" href="<c:url value='update/${items.id}' />">${editLabel}</a></span>
								</security:authorize>
							</div>
						</div>
						<div class="thumbsRight">
							<div class="title-main">${items.titleEng} <c:if test="${items.year!=null}">(${items.year})</c:if></div>
							<c:if test="${items.titleEll!=null}"><div class="title-sub">${items.titleEll}</div></c:if>		
							<div class="tags">
								<span class="tag">${items.category.title}</span>
								<c:if test="${items.commentItems!=null}">
									<c:forEach var="loopComments" items="${items.commentItems}">
										<span class="tag short">${loopComments.idComment.title}</span>
									</c:forEach>
								</c:if>
								<c:if test="${items.description!=null}">
									<img id="descriptionImage" src="<c:url value='${contextPath}/resources/images/styles/document.png' />" title="${descriptionLabel}" />
								</c:if>
								<c:if test="${items.rating!=null}">
									<img class="rating${items.rating}" src="<c:url value='${contextPath}/resources/images/styles/blank.gif' />" />
								</c:if>
							</div>
							<%-- Loop Through Artists & Check if Artists With Activity='Director' Exist --%>
							<c:set var="artistFound" value="0" />
							<c:forEach var="loopArtists" items="${items.artistActivityItems}">
								<c:if test="${loopArtists.idActivity.title=='Director'}">
									<c:set var="artistFound" value="1" />
								</c:if>
							</c:forEach>
							<div class="film"><!-- Director Row Always Present -->
								<div>${directorsLabel} :</div>
								<c:choose>
									<c:when test="${artistFound==1}">
										<c:set var="artistFound" value="0" />
										<c:forEach var="loopArtists" items="${items.artistActivityItems}">
											<c:if test="${loopArtists.idActivity.title=='Director'}">
												<c:if test="${artistFound==1}"> | </c:if>
												<c:set var="artistFound" value="1" />
												<a href="<c:url value='/item/list?artist=${loopArtists.idArtist.id}' />">${loopArtists.idArtist.title}</a>
											</c:if>
										</c:forEach>
									</c:when>
									<c:otherwise>
										${noneListedLabel}
									</c:otherwise>
								</c:choose>
							</div>
							<%-- Loop Through Artists & Check if Artists With Activity='Actor' Exist --%>
							<c:set var="artistFound" value="0" />
							<c:forEach var="loopArtists" items="${items.artistActivityItems}">
								<c:if test="${loopArtists.idActivity.title=='Actor'}">
									<c:set var="artistFound" value="1" />
								</c:if>
							</c:forEach>
							<div class="film"><!-- Actor Row Always Present -->
								<div>${actorsLabel} : </div>
								<c:choose>
									<c:when test="${artistFound==1}">
										<c:set var="artistFound" value="0" />
										<c:forEach var="loopArtists" items="${items.artistActivityItems}">
											<c:if test="${loopArtists.idActivity.title=='Actor'}">
												<c:if test="${artistFound==1}"> | </c:if>
												<c:set var="artistFound" value="1" />
												<a href="<c:url value='/item/list?artist=${loopArtists.idArtist.id}' />">${loopArtists.idArtist.title}</a>
											</c:if>
										</c:forEach>
									</c:when>
									<c:otherwise>
										${noneListedLabel}
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="description invisible">${items.description}</div>
					</div>
				</c:when>
			</c:choose>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<p>${noResultsLabel}</p>
	</c:otherwise>
</c:choose>
