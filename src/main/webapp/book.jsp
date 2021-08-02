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
					 	<%@ include file='user_menu.html' %>
					</ul>
					
				</div>
				<div class="main">
					
					<c:if test="${confirm != null && confirm == true}"> 
						<h1>Your booking is confirmed</h1>
						<div><a href="./mybooking">View My Booking</a></div>
			        </c:if>
					<c:if test="${confirm == null}">  
						
						<h2>Confirm your booking</h2> 
						
						<form action="./book" method="post" class="form">
							<table border="1" width="100%">
								<tr>
									<td>From</td>
									<td><c:out value="${flight.fromlocationtitle}" /></td>
								</tr>
								<tr>
									<td>To</td>
									<td><c:out value="${flight.tolocationtitle}" /></td> 
								</tr>
								<tr>
									<td>AirLine</td>
									<td><c:out value="${flight.airlinetitle}" /></td>
								</tr>
								<tr>
									<td>No. of persons/seats</td>
									<td><c:out value="${sessionScope.seats_no}" /></td>
								</tr>
								<tr>
									<td>Ticket Price</td>
									<td><c:out value="${flight.price}" /></td>
								</tr>
								<tr>
									<td>Total Price</td>
									<td><c:out value="${sessionScope.seats_no * flight.price}" /></td>
								</tr>
								<tr>
									<td>Customer/Passenger Name</td>
									<td><c:out value="${passenger.name}" /></td>
								</tr>
								<tr>
									<td>Customer/Passenger Email</td>
									<td><c:out value="${passenger.email}" /></td>
								</tr>
								<tr>
									<td>Customer/Passenger Phone</td>
									<td><c:out value="${passenger.phone}" /></td>
								</tr>
								<tr>
									<td colspan="2"><input type="submit" value="Do Pay / Confirm"></td>
								</tr>
							</table>
						</form>
			        </c:if>
					
				</div>
				<div class="clear"></div>				
			</div>
			
			<div class="footer">
				 <%@ include file='footer.html' %>
			</div>
		</div>
	</body>
</html>

 