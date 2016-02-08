<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hotline Database</title>
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/main.css">
</head>
<body>
	<div id="container"><h1>Hotline Database</h1>
		<div id="searchResultsOuter">
			<div id="searchResultsInner">
				<div id="sectionheader"><h1 class="sectionheader">Results</h1></div>
				<div id="storeList">
					<table>
						<tbody>
					<c:forEach var="row" items="${tableList}">
						${row.toString()}
					</c:forEach>
						</tbody>
					</table>
					<br>
					<input type="submit" value="Add Items To List" name="add" />
					<input type="submit" value="Remove Item(s) From List" name="remove" /><br><br>
					<!-- Select ONE Item To <input type="submit" value="Update Item (price/availability)" name="update" /> -->
					</form>
				
				</div>
				<a href="hothome.jsp">Return to Query or Update ${loginCredentials.database} </a>
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