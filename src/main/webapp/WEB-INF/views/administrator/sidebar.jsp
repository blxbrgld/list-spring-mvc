<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="randomArtist" code="label.artist.random" />
<a href="<c:url value='/item/list?artist=-1' />" class='btn'>${randomArtist}</a>