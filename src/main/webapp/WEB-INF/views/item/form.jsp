<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="pageTitle" code="title.item.create" />
<spring:message var="categoryLabel" code="label.category" />
<spring:message var="categorySelectLabel" code="label.category.select" />
<spring:message var="detailsLabel" code="label.item.details" />
<spring:message var="titleEngLabel" code="label.titleEng" />
<spring:message var="titleEllLabel" code="label.titleEll" />
<spring:message var="yearLabel" code="label.year" />
<spring:message var="placeLabel" code="label.place" />
<spring:message var="discsLabel" code="label.discs" />
<spring:message var="subtitlesLabel" code="label.subtitles" />
<spring:message var="subtitlesSelectLabel" code="label.subtitles.select" />
<spring:message var="ratingLabel" code="label.rating" />
<spring:message var="ratingSelectLabel" code="label.rating.select" />
<spring:message var="descriptionLabel" code="label.description" />
<spring:message var="artistsLabel" code="label.artist.plural" />
<spring:message var="artistLabel" code="label.artist" />
<spring:message var="activitySelectLabel" code="label.activity.select" />
<spring:message var="addArtistLabel" code="label.artist.add" />
<spring:message var="commentsLabel" code="label.comment.plural" />
<spring:message var="commentLabel" code="label.comment" />
<spring:message var="commentSelectLabel" code="label.comment.select" />
<spring:message var="addCommentLabel" code="label.comment.add" />
<spring:message var="photoLabel" code="label.photo" />
<spring:message var="submitLabel" code="label.submit" />
<spring:message var="deleteLabel" code="label.delete" />
<spring:message var="confirmLabel" code="label.delete.confirm" />
<spring:message var="confirmMessage" code="label.item.delete.confirm" />
<div class="content">
	<h3>${pageTitle}</h3>
	<form:form action="." modelAttribute="item" enctype="multipart/form-data">
		<form:hidden path="id" />
		<fieldset>
			<legend>${categoryLabel}</legend>
			<div class="form-group">
				<form:label path="category">${categoryLabel}</form:label>
				<form:select path="category" class="form-control">
					<form:option value="" label="${categorySelectLabel}" />			
					<form:options items="${selectCategory}" itemValue="id" itemLabel="title" />
				</form:select>
				<form:errors path="category" class="errors" />
			</div>
		</fieldset>
		<fieldset>
			<legend>${detailsLabel}</legend>
			<div class="form-group">
				<form:label path="titleEng">${titleEngLabel}</form:label>
				<form:input path="titleEng" class="form-control" placeholder="${titleEngLabel}" />
				<form:errors path="titleEng" class="errors" />
			</div>
			<div class="form-group">
				<form:label path="titleEll">${titleEllLabel}</form:label>
				<form:input path="titleEll" class="form-control" placeholder="${titleEllLabel}" />
				<form:errors path="titleEll" class="errors" />
			</div>
			<div class="form-group">
				<form:label path="year">${yearLabel}</form:label>
				<form:input path="year" class="form-control" placeholder="${yearLabel}" />
				<form:errors path="year" class="errors" />
			</div>		
			<div class="form-group">
				<form:label path="place">${placeLabel}</form:label>
				<form:input path="place" class="form-control" placeholder="${placeLabel}" />
				<form:errors path="place" class="errors" />
			</div>		
			<div class="form-group">
				<form:label path="discs">${discsLabel}</form:label>
				<form:input path="discs" class="form-control" placeholder="${discsLabel}" />
				<form:errors path="discs" class="errors" />
			</div>		
			<div class="form-group">
				<form:label path="subtitles">${subtitlesLabel}</form:label>
				<form:select path="subtitles" class="form-control">
					<form:option value="" label="${subtitlesSelectLabel}" />			
					<form:options items="${selectSubtitles}" itemValue="id" itemLabel="title" />
				</form:select>
				<form:errors path="subtitles" class="errors" />
			</div>	
			<div class="form-group">
				<form:label path="rating">${ratingLabel}</form:label>
				<form:select path="rating" class="form-control">
					<form:option value="" label="${ratingSelectLabel}" />			
					<form:options items="${selectRating}" />
				</form:select>
				<form:errors path="rating" class="errors" />
			</div>
			<div class="form-group">
				<form:label path="description">${descriptionLabel}</form:label>
				<form:textarea path="description" class="form-control textarea" rows="5" placeholder="${descriptionLabel}" />
				<form:errors path="description" class="errors" />
			</div>		
		</fieldset>
		<%--Artists/Activities--%>
		<fieldset>
			<legend>${artistsLabel}</legend>
			<form:errors path="artistActivityItems" element="div" class="errors" />
			<%--Hidden Div To Clone As Response To 'Add Artist' Event--%>
			<div id="hiddenArtist" class="form-group clearfix hidden item-artists">
				<input name="artists" type="text" value="" class="form-control autoComplete" placeholder="${artistLabel}" />
				<select name="activities" class="form-control">
					<option value="">${activitySelectLabel}</option>
					<c:forEach var="ai" items="${selectActivity}">
						<option value="${ai.id}">${ai.title}</option>
					</c:forEach>
				</select>
				<a class="deleteArtist btn btn-danger" role="button">${deleteLabel}</a>
			</div>
			<c:forEach var="loopArtists" items="${item.artistActivityItems}" varStatus="loop">
				<div class="form-group clearfix item-artists">
					<input name="artists" type="text" value="<c:out value='${loopArtists.idArtist.title}' />" class="form-control autoComplete" placeholder="${artistLabel}" />
					<select name="activities" class="form-control">
						<option value="">${activitySelectLabel}</option>
						<c:forEach var="ai" items="${selectActivity}">
							<option <c:if test="${loopArtists.idActivity.id==ai.id}">selected='selected'</c:if> value="${ai.id}">${ai.title}</option>
						</c:forEach>
					</select>
					<a class="deleteArtist btn btn-danger" role="button">${deleteLabel}</a>
				</div>
			</c:forEach>
			<div id="addArtistDiv" class="text-center"><%--Placeholder--%>
				<a id="addArtist" class="btn btn-primary btn-lg" role="button">${addArtistLabel}</a>
			</div>
		</fieldset>
		<%--Comments--%>
		<fieldset>
			<legend>${commentsLabel}</legend>
			<form:errors path="commentItems" element="div" class="errors" />
			<%--Hidden Div To Clone As Response To 'Add Comments' Event--%>
			<div id="hiddenComment" class="form-group clearfix hidden item-comments">
				<select name="comments" class="form-control">
					<option value="">${commentSelectLabel}</option>
					<c:forEach var="ci" items="${selectComment}">
						<option value="${ci.id}">${ci.title}</option>
					</c:forEach>
				</select>
				<a class="deleteComment btn btn-danger" role="button">${deleteLabel}</a>
			</div>
			<c:forEach var="loopComments" items="${item.commentItems}" varStatus="loop">
				<div class="form-group clearfix item-comments">
					<select name="comments" class="form-control">
						<option value="">${commentSelectLabel}</option>
						<c:forEach var="ci" items="${selectComment}">
							<option <c:if test="${loopComments.idComment.id==ci.id}">selected='selected'</c:if> value="${ci.id}">${ci.title}</option>
						</c:forEach>
					</select>
					<a class="deleteComment btn btn-danger" role="button">${deleteLabel}</a>
				</div>
			</c:forEach>
			<div id="addCommentDiv" class="text-center"><%--Placeholder--%>
				<a id="addComment" class="btn btn-primary btn-lg" role="button">${addCommentLabel}</a>
			</div>		
		</fieldset>
		<%--Photo--%>
		<fieldset>
			<legend>${photoLabel}</legend>
			<!-- Display Current Photo On Update and Input For Uploading A New One -->
			<div class="form-group">
				<c:choose>
					<c:when test="${item.id!=null && item.photoPath!=null}">
						<img src="<c:url value='/item/image/${item.photoPath}' />" class="img-thumbnail" onError="this.src='<c:url value='${contextPath}/resources/images/styles/no-image.jpg' />'" />
					</c:when>
					<c:otherwise>
						<img src="<c:url value='${contextPath}/resources/images/styles/no-image.jpg' />" class="img-thumbnail" />
					</c:otherwise>
				</c:choose>
			</div>
			<div class="form-group">
				<form:hidden path="photoPath" class="addPadding" />
				<form:label path="photo">Upload Photo</form:label>
				<form:input path="photo" type="file" />
			</div>
			<form:errors path="photo" element="div" class="errors" />
		</fieldset>
		<button class="btn btn-lg btn-primary" type="submit">${submitLabel}</button>
		<c:if test="${item.id!=null}">
			<a href="<c:url value='/admin/item/delete/${item.id}' />" class="btn btn-danger btn-lg confirm-dialog" role="button" dialog="${confirmMessage}" accept="${confirmLabel}">${deleteLabel}</a>
		</c:if>
	</form:form>
</div>