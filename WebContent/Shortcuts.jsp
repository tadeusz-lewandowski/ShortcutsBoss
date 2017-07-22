<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:import url="/partials/Header.jsp"></c:import>
<div class="container">
	<c:if test="${error != null}">
		<div class="alert alert-danger" role="alert">${ error }</div>
	</c:if>
	<c:if test="${ loggedOut != null}">
		<div class="alert alert-success" role="alert">${ loggedOut }</div>      	
    </c:if>
    <c:if test="${ registrationSuccess != null}">
		<div class="alert alert-success" role="alert">${ registrationSuccess }</div>      	
    </c:if>
	<h1>Shortcuts Boss</h1>	
	<form action="/ShortcutsBoss/" method="GET" class="form-inline">
		<input type="text" name="search" class="form-control">
		<input type="submit" class="btn btn-default">
	</form>
	<ul>
		<c:forEach var = "shortcut" items="${ shortcuts }">
			<li>
				<a href="<%= request.getContextPath() %>/shortcut/${ shortcut.id }"><c:out value = "${shortcut.name}"/></a>
				<a href="#">@<c:out value = "${shortcut.authorName}"/></a>
	        	<div>
	        		<i class="glyphicon glyphicon-star"></i> <c:out value = "${shortcut.stars}"/>
	        	</div>
			</li>	
	    </c:forEach>	
	</ul>
</div>
<c:import url="/partials/Footer.jsp"></c:import>