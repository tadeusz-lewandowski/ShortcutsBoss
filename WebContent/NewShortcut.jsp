<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<c:import url="/partials/Header.jsp"></c:import>
<div class="container">
	<h1>Create new shortcut</h1>
	<form method="post" action="<%= request.getContextPath() %>/shortcut">
		<div class="form-group">
			<label for="name">Name</label>
			<input type="text" name="title" class="form-control" id="name">
		</div>
		<div class="form-group">
			<label for="content">Content</label>
			<textarea name="content" class="form-control" id="content" rows="25"></textarea>
		</div>	
		<input type="submit" class="btn btn-default">
	</form>
</div>
<c:import url="/partials/Footer.jsp"></c:import>