<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<c:import url="/partials/Header.jsp"></c:import>

<div class="container">
	<h1><c:out value="${ shortcut.name }"></c:out></h1>
	<c:if test="${loggedIn != null}">
		<div>
			<form method="post" action="<%= request.getContextPath() %>/stars">
				<input type="hidden" name="shortcut_ID" value="${ shortcut.id }">
				<button type="submit" class="btn btn-default"><i class="glyphicon glyphicon-star${ starSelected }"></i> <c:out value = "${shortcut.stars} - give a star"/></button>
			</form>     	
	    </div>
	</c:if>	
	<article id="shortcuts" data-content="${shortcut.content}">
		<pre>${shortcut.content}</pre>
	</article>
	<div id="content"></div>
</div>
<c:import url="/partials/Footer.jsp"></c:import>