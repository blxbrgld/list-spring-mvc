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
<spring:message var="originalTitleLabel" code="label.titleOriginal" />
<spring:message var="publisherLabel" code="label.publisher" />
<spring:message var="descriptionLabel" code='label.description' />
<spring:message var="noResultsLabel" code="title.item.search.noResults" />
<spring:message var="editLabel" code="label.edit" />
<div class="content">
	<c:choose>
		<c:when test="${pageHeaderAttribute==null}"><h3><spring:message code="${pageHeader}" /></h3></c:when>
		<c:otherwise><h3><spring:message code="${pageHeader}" arguments="${pageHeaderAttribute}" /></h3></c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${fn:length(itemList) gt 0}">
			<c:forEach var="items" items="${itemList}">
				<c:choose>
					<%-- Music Items --%>
					<c:when test="${items.category.id==1 || items.category.parent.id==1}">
						<div class="row item music">
							<div class="col-xs-4 col-sm-2 text-center left"><%--Left Column--%>
								<div class="row">
									<div class="col-xs-12 picture"><%--Image--%>
										<c:choose>
											<c:when test="${items.photoPath!=null}">
												<img class="img-thumbnail" src="<c:url value='/item/image/${items.photoPath}' />" onError="this.src='<c:url value="${contextPath}/resources/images/styles/no-image.jpg" />'" />
											</c:when>
											<c:otherwise>
												<img class="img-thumbnail" src="<c:url value='${contextPath}/resources/images/styles/no-image.jpg' />" />
											</c:otherwise>
										</c:choose>
									</div>
									<div class="col-xs-12 info"><%--Info--%>
										<c:if test="${items.place!=null}">
											<span><i class="fa fa-database"></i>${items.place}</span>
										</c:if>
										<c:choose>
											<c:when test="${items.discs!=null && items.discs>1}">
												<c:set var="discs" value="${items.discs} Discs" />
											</c:when>
											<c:otherwise>
												<c:set var="discs" value="1 Disc" />
											</c:otherwise>
										</c:choose>
										<span><i class="fa fa-clone"></i>${discs}</span>
										<security:authorize access="hasRole('Administrator')">
											<a href="<c:url value='/admin/item/update/${items.id}' />">${editLabel}</a>
										</security:authorize>
									</div>
								</div>
							</div>
							<div class="col-xs-8 col-sm-10 right"><%--Right Column--%>
								<div class="row">
									<%--Loop Through Artists & Check if Artists With Activity='Musician' Exist--%>
									<c:set var="artistFound" value="0" />
									<c:forEach var="loopArtists" items="${items.artistActivityItems}">
										<c:if test="${loopArtists.idActivity.title=='Musician'}">
											<c:set var="artistFound" value="1" />
										</c:if>
									</c:forEach>
									<div class="col-xs-12 musician">	
										<c:choose>
											<c:when test="${artistFound==1}">
												<h4>
													<c:set var="artistFound" value="0" />
													<c:forEach var="loopArtists" items="${items.artistActivityItems}">
														<c:if test="${loopArtists.idActivity.title=='Musician'}">
															<c:if test="${artistFound==1}"> | </c:if>
															<c:set var="artistFound" value="1" />
															<a href="<c:url value='/item/list?artist=${loopArtists.idArtist.id}' />">${loopArtists.idArtist.title}</a>
														</c:if>
													</c:forEach>
												</h4>
											</c:when>
											<c:otherwise>
												<h4>${noneListedLabel}</h4>
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
										<div class="col-xs-12 conductor">
											<c:set var="artistFound" value="0" />
											<c:forEach var="loopArtists" items="${items.artistActivityItems}">
												<c:if test="${loopArtists.idActivity.title=='Conductor'}">
													<c:if test="${artistFound==1}"> | </c:if>
													<c:set var="artistFound" value="1" />
													<a href="<c:url value='/item/list?artist=${loopArtists.idArtist.id}' />">${loopArtists.idArtist.title}</a>
												</c:if>
											</c:forEach>
											<span>[ ${conductorsLabel} ]</span>
										</div>
									</c:if>
									<div class="col-xs-12 title">
										${items.titleEng} <c:if test="${items.year!=null}">(${items.year})</c:if>
									</div>
									<div class="col-xs-12">
					  					<span class="category">
					  						${items.category.title}
					  					</span>
										<c:if test="${items.commentItems!=null}">
											<c:forEach var="loopComments" items="${items.commentItems}">
												<span class="comment">${loopComments.idComment.title}</span>
											</c:forEach>
										</c:if>
										<c:if test="${items.description!=null}">
											<span class="description toggle"><i class="fa fa-file-text-o"></i>Description</span>
										</c:if>
										<c:if test="${items.rating!=null}">
											<span class="rating rating${items.rating}"></span>
										</c:if>
									</div>
									<div class="col-xs-12 description hidden">
										${items.description}
									</div>
								</div>
							</div>
						</div>						
					</c:when>
					<%-- Film Items --%>
					<c:when test="${items.category.id==2 || items.category.parent.id==2}">
						<div class="row item music">
							<div class="col-xs-4 col-sm-2 text-center left"><%--Left Column--%>
								<div class="row">
									<div class="col-xs-12 picture"><%--Image--%>
										<c:choose>
											<c:when test="${items.photoPath!=null}">
												<img class="img-thumbnail" src="<c:url value='/item/image/${items.photoPath}' />" onError="this.src='<c:url value="${contextPath}/resources/images/styles/no-image.jpg" />'" />
											</c:when>
											<c:otherwise>
												<img class="img-thumbnail" src="<c:url value='${contextPath}/resources/images/styles/no-image.jpg' />" />
											</c:otherwise>
										</c:choose>
									</div>
									<div class="col-xs-12 info"><%--Info--%>
										<c:if test="${items.subtitles!=null}">
											<c:choose>
												<c:when test="${items.subtitles.id==1}">
													<img class="language" src="<c:url value='${contextPath}/resources/images/styles/x.png' />" title="${noSubsLabel}" />
												</c:when>
												<c:when test="${items.subtitles.id==2}">
													<img class="language" src="<c:url value='${contextPath}/resources/images/styles/greece.gif' />" title="${greekSubsLabel}" />
												</c:when>
												<c:otherwise>
													<img class="language" src="<c:url value='${contextPath}/resources/images/styles/usa.gif' />" title="${englishSubsLabel}" />
												</c:otherwise>
											</c:choose>
										</c:if>
										<c:if test="${items.place!=null}">
											<span><i class="fa fa-database"></i>${items.place}</span>
										</c:if>
										<c:choose>
											<c:when test="${items.discs!=null && items.discs>1}"><c:set var="discs" value="${items.discs} Discs" /></c:when>
											<c:otherwise><c:set var="discs" value="1 Disc" /></c:otherwise>
										</c:choose>
										<span><i class="fa fa-clone"></i>${discs}</span>
										<security:authorize access="hasRole('Administrator')">
											<a href="<c:url value='/admin/item/update/${items.id}' />">${editLabel}</a>
										</security:authorize>
									</div>
								</div>
							</div>
							<div class="col-xs-8 col-sm-10 right"><%--Right Column--%>
								<div class="row">
									<div class="col-xs-12 title">
										<h4>${items.titleEng} <c:if test="${items.year!=null}">(${items.year})</c:if></h4>
									</div>
									<c:if test="${items.titleEll!=null}">
										<div class="col-xs-12 subtitle">
											<h5>${items.titleEll}</h5>
										</div>
									</c:if>
									<div class="col-xs-12">
										<span class="category">${items.category.title}</span>
										<c:if test="${items.commentItems!=null}">
											<c:forEach var="loopComments" items="${items.commentItems}">
												<span class="comment">${loopComments.idComment.title}</span>
											</c:forEach>
										</c:if>
										<c:if test="${items.description!=null}">
											<span class="description toggle"><i class="fa fa-file-text-o"></i>Description</span>
										</c:if>
										<c:if test="${items.rating!=null}">
											<span class="rating rating${items.rating}"></span>
										</c:if>
									</div>
									<%--Loop Through Artists & Check if Artists With Activity='Director' Exist--%>
									<c:set var="artistFound" value="0" />
									<c:forEach var="loopArtists" items="${items.artistActivityItems}">
										<c:if test="${loopArtists.idActivity.title=='Director'}">
											<c:set var="artistFound" value="1" />
										</c:if>
									</c:forEach>
									<%--Director Row--%>
									<div class="col-xs-12 director">
										<div class="row">
											<div class="col-xs-12 col-sm-1 row-label">
												${directorsLabel} :
											</div>
											<div class="col-xs-12 col-sm-11">
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
										</div>
									</div>
									<%--Loop Through Artists & Check if Artists With Activity='Actor' Exist--%>
									<c:set var="artistFound" value="0" />
									<c:forEach var="loopArtists" items="${items.artistActivityItems}">
										<c:if test="${loopArtists.idActivity.title=='Actor'}">
											<c:set var="artistFound" value="1" />
										</c:if>
									</c:forEach>
									<div class="col-xs-12 actor"><%--Actor Row Always Present--%>
										<div class="row">
											<div class="col-xs-12 col-sm-1 row-label">
												${actorsLabel} : 
											</div>
											<div class="col-xs-12 col-sm-11">
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
									</div>
									<div class="col-xs-12 description hidden">
										${items.description}
									</div>
								</div>
							</div>
						</div>
					</c:when>
					<%-- Book Items --%>
					<c:when test="${items.category.id==8}">
						<div class="row item book">
							<div class="col-xs-4 col-sm-2 text-center left"><%--Left Column--%>
								<div class="row">
									<div class="col-xs-12 picture"><%--Image--%>
										<c:choose>
											<c:when test="${items.photoPath!=null}">
												<img class="img-thumbnail" src="<c:url value='/item/image/${items.photoPath}' />" onError="this.src='<c:url value="${contextPath}/resources/images/styles/no-image.jpg" />'" />
											</c:when>
											<c:otherwise>
												<img class="img-thumbnail" src="<c:url value='${contextPath}/resources/images/styles/no-image.jpg' />" />
											</c:otherwise>
										</c:choose>
									</div>
									<div class="col-xs-12 info"><%--Info--%>
										<security:authorize access="hasRole('Administrator')">
											<a href="<c:url value='/admin/item/update/${items.id}' />">${editLabel}</a>
										</security:authorize>
									</div>
								</div>
							</div>
							<div class="col-xs-8 col-sm-10 right"><%--Right Column--%>
								<div class="row">
									<div class="col-xs-12 title">
										<h4>${items.titleEng}</h4>
									</div>
									<div class="col-xs-12 author">
										<c:forEach var="loopArtists" items="${items.artistActivityItems}" varStatus="loop">
											<a href="<c:url value='/item/list?artist=${loopArtists.idArtist.id}' />">${loopArtists.idArtist.title}</a>
											<c:if test="${!loop.last}"> | </c:if>
										</c:forEach>
									</div>
									<div class="col-xs-12">
										<span class="category">${items.category.title}</span>
										<c:if test="${items.description!=null}">
											<span class="description toggle"><i class="fa fa-file-text-o"></i>Description</span>
										</c:if>
										<c:if test="${items.rating!=null}">
											<span class="rating rating${items.rating}"></span>
										</c:if>
									</div>
									<div class="col-xs-12 original-title">
										<c:if test="${items.titleEll!=null}">
											<strong>${originalTitleLabel} :</strong>
											${items.titleEll}
										</c:if>
									</div>
									<div class="col-xs-12 publisher">
										<strong>${publisherLabel} :</strong>
										${items.publisher.title}<c:if test="${items.year!=null}">, ${items.year}</c:if><c:if test="${items.pages!=null}">, ${items.pages} Pages</c:if>
									</div>
									<div class="col-xs-12 description hidden">
										${items.description}
									</div>
								</div>
							</div>
						</div>
					</c:when>
				</c:choose>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<p>${noResultsLabel}</p>
		</c:otherwise>
	</c:choose>
</div>