<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="date" class="java.util.Date" />
<div class="footer text-center">
	<%-- AddThis Follow Buttons --%>
	<div class="socials">
		<div class="addthis_horizontal_follow_toolbox"></div>
	</div>
	<p class="copyright">Copyright &copy <fmt:formatDate value="${date}" pattern="yyyy" /> nikolaos.i.papadopoulos@gmail.com</p>
</div>