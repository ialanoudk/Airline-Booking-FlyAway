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
					<h2>Book your trip</h2> 
					
					<form action="./" method="post" class="form">
						<table border="1" width="100%">
							<tr>
								<td>From</td>
								<td>
									<select name="from">
										 <c:forEach var="loc" items="${locations}">
										 	<option value="<c:out value="${loc.id}" />"><c:out value="${loc.name}" /></option> 
							            </c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td>To</td>
								<td>
									<select name="to">
										<c:forEach var="loc" items="${locations}">
										 	<option value="<c:out value="${loc.id}" />"><c:out value="${loc.name}" /></option> 
							            </c:forEach>
									</select>
								</td>
							</tr> 
							<tr>
								<td>Airline</td>
								<td>
									<select name="airline">
										<c:forEach var="airline" items="${airList}">
										 	<option value="<c:out value="${airline.id}" />"><c:out value="${airline.name}" /></option> 
							            </c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td>Date Time</td>
								<td>
									<input type="date" name="date" required>
								</td>
							</tr>
							<tr>
								<td>No. of seats</td>
								<td>
									<input type="number" name="no_seats" required min="1" step ="1" value = "1">
								</td>
							</tr>
							<tr>
								<td colspan="2"><input type="submit" value="Search"></td>
							</tr>
						</table>
					</form>
					
					
					<c:if test="${submit == true}"> 
						<h3>Search Result</h3>
						<c:choose>
						    <c:when test="${flight == null}">
								<h2>No flight available</h2>
						    </c:when>    
						    <c:otherwise>
								<h2>Trip Info</h2>
						        <table border="1" width="100%">
						        	<tr>
						        		<th>From</th>
						        		<th>To</th>
						        		<th>Via airline</th>
						        		<th>Available Seats</th>
						        		<th>Book Now</th>
						        	</tr>
						        	<tr>
						        		<td><c:out value="${flight.fromlocationtitle}" /></td>
						        		<td><c:out value="${flight.tolocationtitle}" /></td>
						        		<td>Via airline</td>
										<c:if test="${availabe_seats == true}"> 
						        			<td>Yes</td>
						        			<td><a href="./book?id=<c:out value="${flight.id}" />&seats_no=<c:out value="${no_seats}" />">Book Now</a></td>
			        					</c:if>
										<c:if test="${availabe_seats != true}"> 
						        			<td>No</td>
						        			<td>Search Again</td>
			        					</c:if>
						        	</tr>
						        </table>
						    </c:otherwise>
						</c:choose> 
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

 