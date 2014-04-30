<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<spring:message var="homeLabel" code="title.home.short" />
<spring:message var="categoriesLabel" code="label.category.plural" />
<spring:message var="itemsLabel" code="label.item.plural" />
<spring:message var="artistsLabel" code="label.artist.plural" />
<spring:message var="activitiesLabel" code="label.activity.plural" />
<spring:message var="commentsLabel" code="label.comment.plural" />
<spring:message var="subtitlesLabel" code="label.subtitles" />
<spring:message var="usersLabel" code="label.user.plural" />
<spring:message var="editLabel" code="label.edit" />
<spring:message var="moreLabel" code="label.more" />
<ul class='menu' id='jMenu'>
	<li><a href="<c:url value='/' />">${homeLabel}</a></li>
	<c:forEach var="parent" items="${categoriesTree}">
		<li><a href="<c:url value='/item/list?searchFor=*&searchIn=${parent.key}&property=titleEng' />">${parent.key}</a>
			<ul>
				<c:forEach var="child" items="${parent.value}" >
					<li><a href="<c:url value='/item/list?searchFor=*&searchIn=${child}&property=titleEng' />">${child}</a></li>
				</c:forEach>
			</ul>
		</li>
	</c:forEach>
	<security:authorize access="hasRole('Administrator')">
		<li><a href="#">${editLabel}</a>
			<ul>
	    		<li class="hideOptions invisible"><a href="<c:url value='/category/list' />">${categoriesLabel}</a></li>
	    		<li><a href="<c:url value='/item/list?view=list' />">${itemsLabel}</a></li>
	    		<li><a href="<c:url value='/artist/list' />">${artistsLabel}</a></li>
	    		<li class="hideOptions invisible"><a href="<c:url value='/activity/list' />">${activitiesLabel}</a></li>
	    		<li class="hideOptions invisible"><a href="<c:url value='/comment/list' />">${commentsLabel}</a></li>
	    		<li class="hideOptions invisible"><a href="<c:url value='/subtitles/list' />">${subtitlesLabel}</a></li>
	    	</ul>
		</li>
		<li class="hideOptions invisible"><a href="<c:url value='/user/list' />">${usersLabel}</a></li>
		<li class="hideOptions"><a href="#" id="showOptions">${moreLabel}</a></li>
	</security:authorize>
</ul>