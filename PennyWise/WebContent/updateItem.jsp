<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Items</title>
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/main.css">
</head>
<body>
	<div id="container">
			<div id="itemUpdateOuter">
				<div class="itemUpdateInner">
					<div id="sectionheader"><h1 class="sectionheader">Update Item</h1></div>
						<form action="∆Item.do" method="GET">
							<div id="originalItem">
								<p>${itemUpdate.store}</p>
								<p>Store Section: ${itemUpdate.storeSection}</p>
								<p>Brand: ${itemUpdate.brand} </p>
								<p>Type: ${itemUpdate.type} </p>
								<p>Price:$<input type="number" step="0.01" value="${itemUpdate.price}" name="updatePrice" /></p>
							</div>
							<div id="sectionheader"><h1 class="sectionheader">Update Availability</h1></div>
							<div id="updateAvailability">
								<p><Label>Add Item to Another Store: <select name="store_name">
									<option value="Dont">Available Stores</option>
									<option value="Whole Foods">Whole Foods</option>
									<option value="Safeway">Safeway</option>
									<option value="Sprouts">Sprouts</option>
									<option value="Trader Joes">Trader Joe's</option>
								</select></Label></p>
								<p>OR</p>
								<input type="hidden" name="removeItem" value="false" />
								<input type="checkbox" name="removeItem" value="true" /> Remove Item from Store <br>
							</div>
					
					
							<div class="submitButtons">
								<input type="hidden" value="${itemUpdate}" name="itemAddress" />
								<input type="submit" value="Submit Update" name="update" />
							</div>	
					
						</form>
					
					<a href="index.html">Go back to Select More Items</a>
				</div>
		</div>
		<c:choose>
			<c:when test="${shoppingList.size()==0}">
				<div id="emptyShoppingList">
				hello empty list
				</div>
			</c:when>
			<c:otherwise>
				<div id="shoppingListOuter">
					<div id="shoppingListInner">
						<div id="sectionheader"><h1 class="sectionheader">Shopping List</h1></div>
						<div id="storelist">
							<h2 class="storename">Safeway</h2>
							<c:forEach var="item" items="${shoppingList}">
								<c:choose>							
									<c:when test="${item.store.equalsIgnoreCase('Safeway')}">									
										<div id="itemlist">
											<p>Section: ${item.storeSection}</p>
											<p>Brand: ${item.brand}</p>
											<p>Type: ${item.type}</p>
											<p>Price:<fmt:formatNumber value="${item.price}" type="currency" /></p>
										</div>
									</c:when>
								</c:choose>
							</c:forEach>
						</div>
						<div id="storelist">
							<h2 class="storename">Sprouts</h2>
							<c:forEach var="item" items="${shoppingList}">
								<c:choose>							
									<c:when test="${item.store.equalsIgnoreCase('Sprouts')}">									
										<div id="itemlist">
											<p>Section: ${item.storeSection}</p>
											<p>Brand: ${item.brand}</p>
											<p>Type: ${item.type}</p>
											<p>Price:<fmt:formatNumber value="${item.price}" type="currency" /></p>
										</div>
									</c:when>
								</c:choose>
							</c:forEach>
						</div>
						<div id="storelist">
							<h2 class="storename">Trader Joe's</h2>
							<c:forEach var="item" items="${shoppingList}">
								<c:choose>							
									<c:when test="${item.store.equalsIgnoreCase('Trader Joes')}">									
										<div id="itemlist">
											<p>Section: ${item.storeSection}</p>
											<p>Brand: ${item.brand}</p>
											<p>Type: ${item.type}</p>
											<p>Price:<fmt:formatNumber value="${item.price}" type="currency" /></p>
										</div>
									</c:when>
								</c:choose>
							</c:forEach>
						</div>
						<div id="storelist">
							<h2 class="storename">Whole Foods</h2>
							<c:forEach var="item" items="${shoppingList}">
								<c:choose>							
									<c:when test="${item.store.equalsIgnoreCase('Whole Foods')}">									
										<div id="itemlist">
											<p>Section: ${item.storeSection}</p>
											<p>Brand: ${item.brand}</p>
											<p>Type: ${item.type}</p>
											<p>Price:<fmt:formatNumber value="${item.price}" type="currency" /></p>
										</div>
									</c:when>
								</c:choose>
							</c:forEach>
						</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>	
</body>
</html>