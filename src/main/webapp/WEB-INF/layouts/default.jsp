<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<spring:url value="/resources/images/styles/favicon.ico" var="faviconUrl" />
<spring:url value="/resources/bootstrap-3.3.5/dist/css/bootstrap.min.css" var="bootstrapCSSUrl" />
<spring:url value="/resources/styles/styling.css" var="stylingUrl" />
<spring:url value="/resources/bootstrap-3.3.5/dist/js/bootstrap.min.js" var="bootstrapJSUrl" />
<spring:url value="/resources/bootbox-4.4.0/bootbox.min.js" var="bootboxUrl" />
<spring:url value="/resources/js/scripts.js" var="scriptsUrl" />
<spring:url value="/resources/tinymce/tinymce.min.js" var="tinymceUrl" />
<spring:url value="/resources/font-awesome-4.4.0/css/font-awesome.min.css" var="fontawesomeUrl" />
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />	
		<%--Title HTML Tag As A Tile--%>
		<title><tiles:getAsString name="title" /></title>
		<link rel="shortcut icon" type="image/x-icon" href="${faviconUrl}" />
		<%--Bootstrap + Custom CSS--%>
	    <link href="${bootstrapCSSUrl}" rel="stylesheet">
	    <link href="${stylingUrl}" rel="stylesheet" />
	    <link href="${fontawesomeUrl}" rel="stylesheet" />
	    <%--Fonts--%>
	    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic&subset=latin,greek,greek-ext' rel='stylesheet' type='text/css'>
	    <%--HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries--%>
	    <%--WARNING: Respond.js doesn't work if you view the page via file://--%>
	    <!--[if lt IE 9]>
	      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	    <![endif]-->
	    <%--Default Image In As An Application Scope Parameter--%>
		<c:set var="noImage" value="${contextPath}/resources/images/items/no-image.png" scope="application"/>
	</head>
	<body>
		<%--AddThis Follow Buttons--%>
		<script type="text/javascript" src="//s7.addthis.com/js/300/addthis_widget.js#pubid=ra-55defec8b285f550" async="async"></script>
		<tiles:insertAttribute name="menu" /><%--Navigation Bar--%>
		<tiles:insertAttribute name="credentials" /><%--Credentials--%>
		<div class="container-fluid">
			<div class="row">
				<%--Main Content--%>
				<tiles:insertAttribute name="wallpaper" />
		   		<div class="col-xs-12 col-sm-10">
			   		<%--Alert Messages--%>
			   		<c:if test="${not empty successMessage or not empty errorMessage or not empty warningMessage}">
						<c:choose>
							<c:when test="${not empty successMessage}">
								<c:set var="flashClass" value="alert-success" scope="request" />
								<spring:message var="flashMessage" code="${successMessage}" />
	 						</c:when>
							<c:when test="${not empty errorMessage}">
								<c:set var="flashClass" value="alert-danger" scope="request" />
								<spring:message var="flashMessage" code="${errorMessage}" />
	 						</c:when>
							<c:when test="${not empty warningMessage}">
								<c:set var="flashClass" value="alert-warning" scope="request" />
								<spring:message var="flashMessage" code="${warningMessage}" />
							</c:when>
						</c:choose>
				   		<div class="alert ${flashClass} alert-dismissible" role="alert">
		  					<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  					${flashMessage}
						</div>
		   			</c:if>
					<tiles:insertAttribute name="body" />
					<tiles:insertAttribute name="pagination" ignore="true" />
				</div>
				<div class="col-xs-12 col-sm-2">
		    		<tiles:insertAttribute name="sidebar" />
				</div>
			</div>
			<div class="row">
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
		<%--jQuery, Bootstrap + Other JS--%>
	    <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	   	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	    <script src="${bootstrapJSUrl}"></script>
	    <script src="${bootboxUrl}" type="text/javascript"></script>
	    <script src="${tinymceUrl}" type="text/javascript"></script>
	    <script src="${scriptsUrl}" type="text/javascript"></script>
	</body>
</html>