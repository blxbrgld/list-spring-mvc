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
<spring:message var="administratorLabel" code="label.administrator" />
<spring:message var="luceneRebuild" code="label.lucene.rebuild" />
<spring:message var="confirmLabel" code="label.delete.confirm" />
<spring:message var="confirmMessage" code="message.lucene.confirm" />
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<%--Toggle Button--%>
    	<div class="navbar-header">
      		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#fixed-navbar-collapse" aria-expanded="false">
        		<span class="sr-only">Toggle navigation</span>
        		<span class="icon-bar"></span>
        		<span class="icon-bar"></span>
        		<span class="icon-bar"></span>
      		</button>
      		<a class="navbar-brand" href="<c:url value='/' />">${homeLabel}</a>
    	</div>
		<%--Links--%>
    	<div class="collapse navbar-collapse" id="fixed-navbar-collapse">
      		<ul class="nav navbar-nav">
        		<c:forEach var="parent" items="${categoriesTree}">
        			<li class="dropdown">
	        			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${parent.key} <span class="caret"></span></a>
	         		 	<ul class="dropdown-menu">
	        				<c:forEach var="child" items="${parent.value}">
	        					<li><a href="<c:url value='/item/list?searchFor=*&searchIn=${child}&property=titleEng' />">${child}</a></li>
	        				</c:forEach>
	        			</ul>
        			</li>
        		</c:forEach>
        		<security:authorize access="hasRole('Administrator')">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${editLabel} <span class="caret"></span></a>
	         		 	<ul class="dropdown-menu">
				    		<li><a href="<c:url value='/admin/category/list' />">${categoriesLabel}</a></li>
				    		<li><a href="<c:url value='/item/list?view=list' />">${itemsLabel}</a></li>
				    		<li><a href="<c:url value='/admin/artist/list' />">${artistsLabel}</a></li>
				    		<li><a href="<c:url value='/admin/activity/list' />">${activitiesLabel}</a></li>
				    		<li><a href="<c:url value='/admin/comment/list' />">${commentsLabel}</a></li>
				    		<li><a href="<c:url value='/admin/subtitles/list' />">${subtitlesLabel}</a></li>
				    		<li role="separator" class="divider"></li>
               	 			<li><a href="<c:url value='/admin/user/list' />">${usersLabel}</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${administratorLabel} <span class="caret"></span></a>
	         		 	<ul class="dropdown-menu">
	         		 		<c:forEach var="categoriesTree" items="${categoriesTree}">
								<li><a href="<c:url value='/item/export?parent=${categoriesTree.key}' />"><spring:message code="label.export.items" arguments="${categoriesTree.key}" /></a></li>
							</c:forEach>
							<li role="separator" class="divider"></li>
							<li><a href="<c:url value='/admin/administrator/lucene?mode=synchronously' />" class="confirm-dialog" dialog="${confirmMessage}" accept="${confirmLabel}">${luceneRebuild}</a></li>
						</ul>
					</li>
				</security:authorize>
      		</ul>
			<%--Search Form--%>
			<jsp:include page="search.jsp" />
    	</div>
	</div>
</nav>