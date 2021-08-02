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
					
					<c:if test="${listBooks != null}">  
						
						<h2>My Booking List</h2> 
							<table border="1" width="100%">
				                <tr>
				                    <th>Passenger Name</th>
				                    <th>Book date</th>  
				                    <th>Flight Number</th>  
				                    <th>From</th>  
				                    <th>To</th>  
				                    <th>Airline</th>  
				                    <th>No. of persons</th>  
				                </tr>
					            <c:forEach var="book" items="${listBooks}">
					                <tr>
					                    <td><c:out value="${book.passenger.name}" /></td>
					                    <td><c:out value="${book.bookdate}" /></td>  
					                    <td><c:out value="${book.flight.id}" /></td> 
					                    <td><c:out value="${book.flight.fromlocationtitle}" /></td>  
					                    <td><c:out value="${book.flight.tolocationtitle}" /></td>  
					                    <td><c:out value="${book.flight.airlinetitle}" /></td>  
					                    <td><c:out value="${book.noseats}" /></td>  
					                </tr>
					            </c:forEach>
							</table>
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

 