<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<meta charset="UTF-8">
<title>Pennywise® 2016</title>
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/main.css">
</head>
<body>
	<div id="container"> <h1>Pennywise</h1>
		<div id="indexOuter">
			<div class="indexInner">
				<div id="sectionheader"><h1 class="sectionheader">Add Items</h1></div>
				<div class="subHeader">
					<h2>Search Items</h2>
					<p class="help">Add to your list by searching item type or brand</p>
					<form action="request.do" method="GET">
						<input type="text" placeholder="Item Type" name="type" /><br>
						<input class="submit" type="submit" value="Submit" />
					</form>
					<form action="request.do" method="GET">
						<input type="text" placeholder ="item brand" name="brand" /><br>
						<input class="submit" type="submit" value="Submit" />
					</form><br>
				</div>
				<div class="subHeader">
					<h2>Create Item</h2>
					<p class="help">Add to your list by entering a custom item</p>
					<form action="addItem.do" method="GET">
						<label>Store: <select name="store_name">
							<option value="Whole Foods">Whole Foods</option>
							<option value="Safeway">Safeway</option>
							<option value="Sprouts">Sprouts</option>
							<option value="Trader Joes">Trader Joe's</option>
						</select></label><br>
						
						<label>Store Section: <select name="store_section">
							<option value="Alcohol">Alcohol</option>
							<option value="Bread">Bread</option>
							<option value="Fishmonger">Fishmonger</option>
							<option value="Dairy">Dairy</option>
							<option value="Frozen">Frozen</option>
							<option value="Produce">Produce</option>
						</select></label><br>
		
						<input type="text" placeholder="brand" name="brand"><br>
						<input type="text" size="40" placeholder="item Type eg(Whole Milk or Cabernet)" name="type"><br>
						<input type="number" step="0.01" placeholder="price eg(5.99))" name="price"><br>
						
						<label>Item Size:<select name="size">
							<option value="750 ML">750 mL</option>
							<option value="375 ML">375 mL</option>			
						</select></label><br>
						<input type="submit" value="Submit" />
					</form>
				</div>
	
	
			</div>
		</div>
		<c:choose>
			<c:when test="${shoppingList.size()==0 || shoppingList.equals(null)}">
				<div id="shoppingListOuter">
					<div id="emptyShoppingList">
						<div id="sectionheader"><h1 class="sectionheader">Shopping List</h1></div>
						<div id="nothingToSee"><h2>Nothing to see here yet!</h2></div>
						<p class="help">To get started either search for items or</p>
						<p class="help">manually add your own.</p>
						<p class="emptyCart"><img class="emptyCart" src="http://s17.postimg.org/fsg8dkm63/shopping_cart_empty_icon.png" /></p>
					</div>
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