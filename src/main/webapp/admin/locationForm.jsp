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
				        <c:if test="${location != null}">
				            <form action="./updatelocation" method="post">
				        </c:if>
				        <c:if test="${location == null}">
				            <form action="./addlocation" method="post">
				        </c:if>
				        <table border="1" cellpadding="5">
				            <caption>
				                <h2>
				                    <c:if test="${location != null}">Edit Location</c:if>
				                    <c:if test="${location == null}">Add New Location</c:if>
				                </h2>
				            </caption>
				                <c:if test="${location != null}">
				                    <input type="hidden" name="id" value="<c:out value='${location.id}' />" />
				                </c:if>           
				            <tr>
				                <th>Name: </th>
				                <td>
				                    <input type="text" name="name" size="45" value="<c:out value='${location.name}' />" />
				                </td>
				            </tr>
				            <tr>
				                <td colspan="2" align="center">
				                    <input type="submit" value="Save" />
				                </td>
				            </tr>
				        </table>
				        </form>
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
