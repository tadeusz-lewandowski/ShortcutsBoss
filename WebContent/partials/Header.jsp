<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>         
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<title>${ title }</title>
<style>
	html {
		height: 100%;
  		box-sizing: border-box;
	}
	body {
		position: relative;
		min-height: 100%;
	}
	footer {
		line-height: 60px;
		text-align: center;
		position: absolute;
		right: 0;
		bottom: 0;
		left: 0;
		background: #f8f8f8;
	}
</style>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href=<%= request.getContextPath() %>>Shortcuts Boss</a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

      <ul class="nav navbar-nav navbar-right">
      	<c:if test="${ loggedIn != null }">
	        <li><a href="<%= request.getContextPath() %>/shortcut/new">Write shortcuts</a></li>      	
      	</c:if>      	
        <li class="dropdown">
	      	<c:choose>
			    <c:when test="${ loggedIn != null }">
			        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${ user.username }<span class="caret"></span></a>
			        <ul class="dropdown-menu">
			            <li>
			            	<form method="post" class="navbar-form" action="<%= request.getContextPath() %>/logout">		            				            	
			           			<input type="submit" class="btn btn-default navbar-btn" value="Log out">		            		
			            	</form>
			            </li>            
			        </ul> 
			    </c:when>    
			    <c:otherwise>
			    	  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Sign in <span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li>
			            	<form method="post" class="navbar-form" action="<%= request.getContextPath() %>/auth">
			            		<div class="form-group">
				            		<label for="username">Username</label>
				            		<input type="text" id="username" name="username" class="form-control">
			            		</div>
			            		<div class="form-group">
				            		<label for="password">Password</label>
				            		<input type="password" id="password" name="password" class="form-control">
			            		</div>
			            		
			           			<input type="submit" class="btn btn-default navbar-btn">
			            		
			            	</form>
			            </li>            
			          </ul>
			    </c:otherwise>
			</c:choose>
          
        </li>
        <c:if test="${ loggedIn == null }">
        	<li class="dropdown">
        		<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Sign up <span class="caret"></span></a>
	         	<ul class="dropdown-menu">
	            	<li>
	            		<form method="post" class="navbar-form" action="<%= request.getContextPath() %>/users">
	            			<div class="form-group">
		            			<label for="username">Username</label>
		            			<input type="text" id="username" name="username" class="form-control">
	            			</div>
	            			<div class="form-group">
		            			<label for="password">Password</label>
		            			<input type="password" id="password" name="password" class="form-control">
	            			</div>
	            			
	            			<div class="form-group">
		            			<label for="passwordRepeat">Repeat password</label>
		            			<input type="password" id="passwordRepeat" name="passwordRepeat" class="form-control">
	            			</div>
	            		
	           				<input type="submit" class="btn btn-default navbar-btn">
	            		
	            		</form>
	            	</li>            
	          	</ul>
        	</li>
        </c:if>
      </ul>
    </div>
  </div>
</nav>