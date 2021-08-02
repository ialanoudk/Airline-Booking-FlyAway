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
				            <caption><h2>List of Airlines</h2></caption>
				            <tr>
				                <th>ID</th>
				                <th>Name</th>
				                <th>Edit</th>
				                <th>Delete</th>
				            </tr>
				            <c:forEach var="airline" items="${listAirlines}">
				                <tr>
				                    <td><c:out value="${airline.id}" /></td>
				                    <td><c:out value="${airline.name}" /></td> 
				                    <td>
				                        <a href="./updateairline?ev=edit&id=<c:out value='${airline.id}' />">Edit</a> 
									</td>
									<td>
				                        <a href="./updateairline?ev=delete&id=<c:out value='${airline.id}' />">Delete</a>                     
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
