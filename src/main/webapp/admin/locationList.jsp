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
				    <div align="center">
				        <table border="1" cellpadding="5">
				            <caption><h2>List of Locations</h2></caption>
				            <tr>
				                <th>ID</th>
				                <th>Name</th>
				                <th>Edit</th>
				                <th>Delete</th>
				            </tr>
				            <c:forEach var="location" items="${listLocations}">
				                <tr>
				                    <td><c:out value="${location.id}" /></td>
				                    <td><c:out value="${location.name}" /></td> 
				                    <td>
				                        <a href="./updatelocation?ev=edit&id=<c:out value='${location.id}' />">Edit</a> 
									</td>
									<td>
				                        <a href="./updatelocation?ev=delete&id=<c:out value='${location.id}' />">Delete</a>                     
				                    </td>
				                </tr>
				            </c:forEach>
				        </table>
				    </div>   
				</div>
				<div class="clear"></div>				
			</div>
			
			<div class="footer">
				 <%@ include file='footer.html' %>
			</div>
		</div>
	</body>
</html>
