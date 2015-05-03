<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/resources/js/jquery-1.11.0.min.js" var="jqueryUrl" />
<spring:url value="/resources/js/jquery-ui-1.10.4.custom.min.js" var="jqueryUiUrl" />
<spring:url value="/resources/js/menu.js" var="menuJs" />
<spring:url value="/resources/tinymce/tinymce.min.js" var="tinymceUrl" />
<spring:url value="/resources/js/scripts.js" var="scriptsUrl" />
<spring:url value="/resources/styles/styles.css" var="stylesUrl" />
<spring:url value="/resources/styles/smoothness/jquery-ui-1.10.4.custom.min.css" var="smoothnessUrl" />
<spring:url value="/resources/images/styles/favicon.ico" var="faviconUrl" />
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />	
	<title><tiles:getAsString name="title" /></title>
	<link rel="shortcut icon" type="image/x-icon" href="${faviconUrl}" />
	<script src="${jqueryUrl}" type="text/javascript"><!-- /required for FF3 and Opera --></script>
	<script src="${jqueryUiUrl}" type="text/javascript"><!-- /required for FF3 and Opera --></script>
	<script src="${menuJs}" type="text/javascript"><!-- /required for FF3 and Opera --></script>
	<script src="${tinymceUrl}" type="text/javascript"><!-- /required for FF3 and Opera --></script>
	<script src="${scriptsUrl}" type="text/javascript"><!-- /required for FF3 and Opera --></script>
	<link href="${stylesUrl}" rel="stylesheet" />
	<link href="${smoothnessUrl}" rel="stylesheet" />
</head>
<body>
	<c:choose>
		<c:when test="${not empty successMessage}">
			<div class="flashMessage"><spring:message code="${successMessage}" /></div>
		</c:when>
		<c:when test="${not empty errorMessage}">
			<div class="flashMessage flashError"><spring:message code="${errorMessage}" /></div>
		</c:when>
		<c:when test="${not empty warningMessage}">
			<div class="flashMessage flashWarning"><spring:message code="${warningMessage}" /></div>
		</c:when>
	</c:choose>
	<div class="container">
		<div class="navigation">
			<tiles:insertAttribute name="menu" />
			<tiles:insertAttribute name="search" />
		</div>
	   	<div class="administrator hideOptions invisible"><tiles:insertAttribute name="administrator" /></div>
	   	<div class="credentials"><tiles:insertAttribute name="credentials" /></div>
	   	<div class="wallpaper"><tiles:insertAttribute name="wallpaper" /></div>
	   	<div class="left"><tiles:insertAttribute name="body" /></div>
	    <div class="right"><tiles:insertAttribute name="sidebar" /></div>
	    <div class="pagination"><tiles:insertAttribute name="pagination" ignore="true" /></div>
	</div>
	<div class="footer"><tiles:insertAttribute name="footer" /></div>
</body>
</html>