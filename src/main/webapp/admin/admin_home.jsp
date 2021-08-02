<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head> 
		<style>
			<%@ include file='style.css' %>
		</style>
	</head>
	
	<body>
		<div class="body">
			<div class="header">
				 <img src="https://i.ibb.co/QkRMC5g/banner.jpg"/> 
			</div>
			
			
			<div class="mainContent">
				<div class="leftSide">
					<ul>
					 	<%@ include file='admin_menu.html' %>
					</ul>
					
				</div>
				<div class="main">
					<h2>Welcome to administrator</h2> 
					
				</div>
				<div class="clear"></div>				
			</div>
			<div class="footer">
				 <%@ include file='footer.html' %>
			</div>
		</div>
	</body>
</html>

 