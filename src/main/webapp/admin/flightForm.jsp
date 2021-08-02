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
					<h2>Add new flight</h2> 
					
					<form action="./addflight" method="post" class="form">
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
								<td>Price</td>
								<td>
									<input type="number" name="price" required min="1" step ="1" value = "1">
								</td>
							</tr>
							<tr>
								<td colspan="2"><input type="submit" value="Add"></td>
							</tr>
						</table>
					</form>
					 
				</div>
				<div class="clear"></div>				
			</div>
			<div class="footer">
				 <%@ include file='footer.html' %>
			</div>
		</div>
	</body>
</html>

 