<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<html>
	<head>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> 
		<style>
		<%@ include file='style.css' %>
		</style>
		
	</head>
	
	<body>
		<div class="body">
			<div class="header">
				<div class="header"> 
					<div class="title"> 
					</div> 
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
			</div>
			
			
			<div class="menuBox"> 
			</div>
			
			<div class="mainContent"> 
				<div class="main"> 
					  
					
					<div class="box"> 
					        <h1>Error</h1>
					        <h2><%=exception.getMessage() %><br/> </h2> 
					</div>
				</div>
				<div class="clear"></div>				
			</div>
			
			<div class="footer"> 
				<div class="copy">
					<p> Copy right @ 2021</p>
				</div>
				<div class="contacts">
					<i class="fa fa-envelope"></i> &nbsp; &nbsp;<a href="#"> email@company.com</a>
					<br/>
					<i class="fa fa-phone"></i>&nbsp; &nbsp; <a href="#">011 5555 55555</a>
					
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</body>
</html>