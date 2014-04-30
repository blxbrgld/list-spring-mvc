<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- Set Page, Size Parameters If They Are Not Properly Set -->
<c:choose>
	<c:when test="${empty param.page || param.page lt 1}">
		<c:set var="page" value="1" />
	</c:when>
	<c:otherwise>
		<c:set var="page" value="${param.page}" />
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${empty param.size || param.size lt 1}">
		<c:set var="size" value="50" />
	</c:when>
	<c:otherwise>
		<c:set var="size" value="${param.size}" />
	</c:otherwise>
</c:choose>
<!-- Select Results Per Page -->
<c:if test="${maxPages ne 1}">
	<spring:message code="listSize" var="listSize" />
	<c:out value="${listSize} " />
	<c:forEach var="i" begin="50" end="200" step="50">
	  <c:choose>
	    <c:when test="${size==i}">
	      <c:out value="${i}" />
	    </c:when>
	    <c:otherwise>
	      <spring:url value="" var="sizeUrl">
	      	<c:if test="${param.view!=null}">
	      		<spring:param name="view" value="${param.view}" />
	      	</c:if>
	      	<c:if test="${param.artist!=null}">
	      		<c:choose>
	      			<c:when test="${param.artist==-1}">
	      				<spring:param name="artist" value="${paginateRandom}" />
	      			</c:when>
	      			<c:otherwise>
	      				<spring:param name="artist" value="${param.artist}" />	
	      			</c:otherwise>
	      		</c:choose>
	      	</c:if>
	      	<c:if test="${param.searchFor!=null}">
	      		<spring:param name="searchFor" value="${param.searchFor}" />
	      	</c:if>
	      	<c:if test="${param.searchIn!=null}">
	      		<spring:param name="searchIn" value="${param.searchIn}" />
	      	</c:if>
	        <spring:param name="page" value="1" />
	        <spring:param name="size" value="${i}" />
	      	<c:if test="${param.property!=null}">
	      		<spring:param name="property" value="${param.property}" />
	      	</c:if>
	      	<c:if test="${param.order!=null}">
	      		<spring:param name="order" value="${param.order}" />
	      	</c:if>
	      </spring:url>
	      <a href="${sizeUrl}">${i}</a>
	    </c:otherwise>
	  </c:choose>
	  <c:out value=" " />
	</c:forEach>
	<c:out value="| " />
</c:if>
<!-- Navigate Through Pages -->
<c:if test="${page ne 1}">
  <spring:url value="" var="first">
	<c:if test="${param.view!=null}">
   		<spring:param name="view" value="${param.view}" />
   	</c:if>
   	<c:if test="${param.artist!=null}">
   		<c:choose>
   			<c:when test="${param.artist==-1}">
   				<spring:param name="artist" value="${paginateRandom}" />
   			</c:when>
   			<c:otherwise>
   				<spring:param name="artist" value="${param.artist}" />	
   			</c:otherwise>
   		</c:choose>
   	</c:if>
   	<c:if test="${param.searchFor!=null}">
   		<spring:param name="searchFor" value="${param.searchFor}" />
   	</c:if>
   	<c:if test="${param.searchIn!=null}">
   		<spring:param name="searchIn" value="${param.searchIn}" />
   	</c:if>
    <spring:param name="page" value="1" />
    <spring:param name="size" value="${size}" />
   	<c:if test="${param.property!=null}">
   		<spring:param name="property" value="${param.property}" />
   	</c:if>
   	<c:if test="${param.order!=null}">
   		<spring:param name="order" value="${param.order}" />
   	</c:if>
  </spring:url>
  <spring:url value="/resources/images/styles/resultsFirst.png" var="firstImage" />
  <a href="${first}" title="First"><img src="${firstImage}" /></a>
</c:if>
<c:if test="${page gt 1}">
  <spring:url value="" var="previous">
   	<c:if test="${param.view!=null}">
   		<spring:param name="view" value="${param.view}" />
   	</c:if>
   	<c:if test="${param.artist!=null}">
   		<c:choose>
   			<c:when test="${param.artist==-1}">
   				<spring:param name="artist" value="${paginateRandom}" />
   			</c:when>
   			<c:otherwise>
   				<spring:param name="artist" value="${param.artist}" />	
   			</c:otherwise>
   		</c:choose>
   	</c:if>
   	<c:if test="${param.searchFor!=null}">
   		<spring:param name="searchFor" value="${param.searchFor}" />
   	</c:if>
   	<c:if test="${param.searchIn!=null}">
   		<spring:param name="searchIn" value="${param.searchIn}" />
   	</c:if>   	
    <spring:param name="page" value="${page-1}" />
    <spring:param name="size" value="${size}" />
   	<c:if test="${param.property!=null}">
   		<spring:param name="property" value="${param.property}" />
   	</c:if>
   	<c:if test="${param.order!=null}">
   		<spring:param name="order" value="${param.order}" />
   	</c:if>
  </spring:url>
  <spring:url value="/resources/images/styles/resultsPrevious.png" var="previousImage" />
  <a href="${previous}" title="Previous"><img src="${previousImage}" /></a>
</c:if>
<c:out value=" " />
<span><spring:message code="listPage" arguments="${page},${maxPages}" argumentSeparator="," /></span>
<c:out value=" " />
<c:if test="${page lt maxPages}">
  <spring:url value="" var="next">
   	<c:if test="${param.view!=null}">
   		<spring:param name="view" value="${param.view}" />
   	</c:if>
   	<c:if test="${param.artist!=null}">
   		<c:choose>
   			<c:when test="${param.artist==-1}">
   				<spring:param name="artist" value="${paginateRandom}" />
   			</c:when>
   			<c:otherwise>
   				<spring:param name="artist" value="${param.artist}" />	
   			</c:otherwise>
   		</c:choose>
   	</c:if>      	
   	<c:if test="${param.searchFor!=null}">
   		<spring:param name="searchFor" value="${param.searchFor}" />
   	</c:if>
   	<c:if test="${param.searchIn!=null}">
   		<spring:param name="searchIn" value="${param.searchIn}" />
   	</c:if>
    <spring:param name="page" value="${page+1}" />
    <spring:param name="size" value="${size}" />
   	<c:if test="${param.property!=null}">
   		<spring:param name="property" value="${param.property}" />
   	</c:if>
   	<c:if test="${param.order!=null}">
   		<spring:param name="order" value="${param.order}" />
   	</c:if>
  </spring:url>
  <spring:url value="/resources/images/styles/resultsNext.png" var="nextImage" />
  <a href="${next}" title="Next"><img src="${nextImage}" /></a>
</c:if>
<c:if test="${page ne maxPages}">
  <spring:url value="" var="last">
	<c:if test="${param.view!=null}">
   		<spring:param name="view" value="${param.view}" />
   	</c:if>
   	<c:if test="${param.artist!=null}">
   		<c:choose>
   			<c:when test="${param.artist==-1}">
   				<spring:param name="artist" value="${paginateRandom}" />
   			</c:when>
   			<c:otherwise>
   				<spring:param name="artist" value="${param.artist}" />	
   			</c:otherwise>
   		</c:choose>
   	</c:if>
   	<c:if test="${param.searchFor!=null}">
   		<spring:param name="searchFor" value="${param.searchFor}" />
   	</c:if>
   	<c:if test="${param.searchIn!=null}">
   		<spring:param name="searchIn" value="${param.searchIn}" />
   	</c:if>
    <spring:param name="page" value="${maxPages}" />
    <spring:param name="size" value="${size}" />
   	<c:if test="${param.property!=null}">
   		<spring:param name="property" value="${param.property}" />
   	</c:if>
   	<c:if test="${param.order!=null}">
   		<spring:param name="order" value="${param.order}" />
   	</c:if>
  </spring:url>
  <spring:url value="/resources/images/styles/resultsLast.png" var="lastImage" />
  <a href="${last}" title="Last"><img src="${lastImage}" /></a>
</c:if>