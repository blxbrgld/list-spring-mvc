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
<form:form action="." modelAttribute="item" enctype="multipart/form-data">
	<h1>${pageTitle}</h1>
	<div><form:hidden path="id" /></div>
	<fieldset>
		<legend>${categoryLabel}</legend>
		<div>
			<form:label path="category">${categoryLabel}</form:label>
			<form:select path="category">
				<form:option value="" label="${categorySelectLabel}" />			
				<form:options items="${selectCategory}" itemValue="id" itemLabel="title" />
			</form:select>
			<form:errors path="category" class="errors" />
		</div>
	</fieldset>
	<fieldset>
		<legend>${detailsLabel}</legend>
		<div>
			<form:label path="titleEng">${titleEngLabel}</form:label>
			<form:input path="titleEng" placeholder="${titleEngLabel}" />
			<form:errors path="titleEng" class="errors" />
		</div>
		<div>
			<form:label path="titleEll">${titleEllLabel}</form:label>
			<form:input path="titleEll" placeholder="${titleEllLabel}" />
			<form:errors path="titleEll" class="errors" />
		</div>
		<div>
			<form:label path="year">${yearLabel}</form:label>
			<form:input path="year" placeholder="${yearLabel}" />
			<form:errors path="year" class="errors" />
		</div>		
		<div>
			<form:label path="place">${placeLabel}</form:label>
			<form:input path="place" placeholder="${placeLabel}" />
			<form:errors path="place" class="errors" />
		</div>		
		<div>
			<form:label path="discs">${discsLabel}</form:label>
			<form:input path="discs" placeholder="${discsLabel}" />
			<form:errors path="discs" class="errors" />
		</div>		
		<div>
			<form:label path="subtitles">${subtitlesLabel}</form:label>
			<form:select path="subtitles">
				<form:option value="" label="${subtitlesSelectLabel}" />			
				<form:options items="${selectSubtitles}" itemValue="id" itemLabel="title" />
			</form:select>
			<form:errors path="subtitles" class="errors" />
		</div>	
		<div>
			<form:label path="rating">${ratingLabel}</form:label>
			<form:select path="rating">
				<form:option value="" label="${ratingSelectLabel}" />			
				<form:options items="${selectRating}" />
			</form:select>
			<form:errors path="rating" class="errors" />
		</div>
		<div>
			<form:label path="description">${descriptionLabel}</form:label>
			<div class="textarea"><form:textarea path="description" /></div>
			<form:errors path="description" class="errors" />
		</div>		
	</fieldset>
	<!-- Artist / Activities -->
	<fieldset>
		<legend>${artistsLabel}</legend>
		<form:errors path="artistActivityItems" element="div" class="errors" />
		<!-- Hidden Div To Clone As Response To 'Add Artist' Event -->
		<div id="hiddenArtist" class="invisible">
			<label for="artist">${artistLabel}</label>
			<input name="artists" type="text" value="" class="shortInput autoComplete" />
			<select name="activities">
				<option value="">${activitySelectLabel}</option>
				<c:forEach var="ai" items="${selectActivity}">
					<option value="${ai.id}">${ai.title}</option>
				</c:forEach>
			</select>
			<a class="deleteArtist btn btnSmall">${deleteLabel}</a>
		</div>
		<c:forEach var="loopArtists" items="${item.artistActivityItems}" varStatus="loop">
			<div>
				<label for="artist">${artistLabel}</label>
				<input name="artists" type="text" value="${loopArtists.idArtist.title}" class="shortInput autoComplete" />
				<select name="activities">
					<option value="">${activitySelectLabel}</option>
					<c:forEach var="ai" items="${selectActivity}">
						<option <c:if test="${loopArtists.idActivity.id==ai.id}">selected='selected'</c:if> value="${ai.id}">${ai.title}</option>
					</c:forEach>
				</select>
				<a class="deleteArtist btn btnSmall">${deleteLabel}</a>
			</div>
		</c:forEach>
		<div id="addArtistDiv" class="centered addPadding"><a id="addArtist" class="btn">${addArtistLabel}</a></div>
	</fieldset>
	<!-- Comments -->
	<fieldset>
		<legend>${commentsLabel}</legend>
		<form:errors path="commentItems" element="div" class="errors" />
		<!-- Hidden Div To Clone As Response To 'Add Comments' Event -->
		<div id="hiddenComment" class="invisible">
			<label for="comment">${commentLabel}</label>
			<select name="comments">
				<option value="">${commentSelectLabel}</option>
				<c:forEach var="ci" items="${selectComment}">
					<option value="${ci.id}">${ci.title}</option>
				</c:forEach>
			</select>
			<a class="deleteComment btn btnSmall">${deleteLabel}</a>
		</div>
		<c:forEach var="loopComments" items="${item.commentItems}" varStatus="loop">
			<div>
				<label for="comment">${commentLabel}</label>
				<select name="comments">
					<option value="">${commentSelectLabel}</option>
					<c:forEach var="ci" items="${selectComment}">
						<option <c:if test="${loopComments.idComment.id==ci.id}">selected='selected'</c:if> value="${ci.id}">${ci.title}</option>
					</c:forEach>
				</select>
				<a class="deleteComment btn btnSmall">${deleteLabel}</a>
			</div>
		</c:forEach>
		<div id="addCommentDiv" class="centered addPadding"><a id="addComment" class="btn">${addCommentLabel}</a></div>		
	</fieldset>
	<fieldset>
		<legend>${photoLabel}</legend>
		<!-- Display Current Photo On Update and Input For Uploading A New One -->
		<c:choose>
			<c:when test="${item.id!=null}">
				<div><img src="${item.id}/photo" /></div>
			</c:when>
			<c:otherwise>
				<img src="<c:url value='${contextPath}/resources/images/items/no-image.png' />" />
			</c:otherwise>
		</c:choose>
		<div>
			<form:hidden path="photoPath" class="addPadding" />
			<form:label path="photo"></form:label>
			<form:input path="photo" type="file" />
		</div>
		<form:errors path="photo" element="div" class="errors" />
	</fieldset>
	<div class="centered addPadding">
		<input type="submit" value="${submitLabel}" class="btn" />
		<c:if test="${item.id!=null}"><a href="<c:url value='/item/delete/${item.id}' />" class="btn dialog-confirm" data-open="item-edit-delete">${deleteLabel}</a></c:if>
	</div>
</form:form>
<div class="dialog" id="item-edit-delete" title="${confirmLabel}"><p>${confirmMessage}</p></div>