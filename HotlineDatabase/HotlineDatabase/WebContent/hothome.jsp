<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<meta charset="UTF-8">
<title>Hotline Database</title>
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/main.css">
</head>
<body>
	<div id="container"> <h1>Hotline Database</h1>
		<div id="hothomeOuter">
			<div class="hothomeInner">
				<div id="sectionheader"><h1 class="sectionheader">To Query || To Update</h1></div>
				<div class="subHeader">
					<h2>Query ${loginCredentials.database}</h2>
					<p class="help">Enter a SQL query</p>
					<form action="dbQuery.do" method="POST">
						<input type="text" placeholder="Query statement" name="query" /><br>
						<input class="submit" type="submit" value="Query ${loginCredentials.database}" />
					</form>
					<form action="dbUpdate.do" method="POST">
						<input type="text" placeholder ="Update Statement" name="update" /><br>
						<input class="submit" type="submit" value="Update ${loginCredentials.database}" />
					</form><br>
				</div>
				<div class="subHeader">
					<h2>Prepared Statements</h2>
					<p class="help">Query || Update </p>
					<p class="help">From the list of available prepared Statements</p>
					<form action="preparedStatements.do" method="GET">
						<label>Store: <select name="prep_state">
							<option value="ShowAll">Select All</option>
							<option value="ShowFirst">First Name</option>
						</select></label><br>
						
						<label>Table: <select name="table">
							<option value="assignments">Assignments</option>
							<option value="departments">Departments</option>
							<option value="employees">Employees</option>
							<option value="jobs">Jobs</option>
							<option value="locations">Locations</option>
							<option value="projects">Projects</option>
						</select></label><br>
						<input type="submit" value="Prime Prepared Phrase" />
					</form>
				</div>
	
	
			</div>
		</div>
		<c:choose>
			<c:when test="${shoppingList.size() > 0}">
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
			</c:when>
			<c:otherwise>
				<div id="shoppingListOuter">
					<div id="emptyShoppingList">
						<div id="sectionheader"><h1 class="sectionheader">Shopping List</h1></div>
						<div id="nothingToSee"><h2>Nothing to see here yet!</h2></div>
						<p class="help">To get started either search for items or</p>
						<p class="help">manually add your own.</p>
						<p class="emptyCart"><img class="emptyCart" src="http://s17.postimg.org/fsg8dkm63/shopping_cart_empty_icon.png" /></p>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>