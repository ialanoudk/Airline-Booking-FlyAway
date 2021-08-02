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
				            <caption><h2>List of Flights</h2></caption>
				            <tr>
				                <th>Flight No.</th>
				                <th>from</th>
				                <th>to</th>
				                <th>via</th>
				                <th>date</th>
				                <th>No. of seats</th>
				                <th>Price</th>
				            </tr>
				            <c:forEach var="flight" items="${listFlights}">
				                <tr>
				                    <td><c:out value="${flight.id}" /></td>
				                    <td><c:out value="${flight.fromlocationtitle}" /></td> 
				                    <td><c:out value="${flight.tolocationtitle}" /></td> 
				                    <td><c:out value="${flight.airlinetitle}" /></td> 
				                    <td><c:out value="${flight.date}" /></td> 
				                    <td><c:out value="${flight.totalseats}" /></td> 
				                    <td><c:out value="${flight.price}" /></td> 
									<td>
				                        <a href="./updateflight?ev=delete&id=<c:out value='${flight.id}' />">Delete</a>                     
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
