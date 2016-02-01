<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Items</title>
</head>
<body>
	<h3>
		<strong><u>${itemUpdate.store}</u></strong><br>
		Store Section: ${itemUpdate.storeSection} <br>
		Brand: ${itemUpdate.brand} <br>
		Type: ${itemUpdate.type} <br>
		Price:<fmt:formatNumber value="${itemUpdate.price}" type="currency" /><br>
	</h3>
	
	<h3>
	Update Item | Update Item Availability
		<form action="∆Item.do" method="GET">
		Update Price: <input type="number" step="0.01" value="${itemUpdate.price}" name="updatePrice" /> <br>
		<input type="hidden" name="removeItem" value="false" />
		<input type="checkbox" name="removeItem" value="true" /> Remove Item from Store <br>
			Add Item to another Store<Label>Different Store: <select name="store_name">
				<option value="Dont">Don't Add New Store</option>
				<option value="Whole Foods">Whole Foods</option>
				<option value="Safeway">Safeway</option>
				<option value="Sprouts">Sprouts</option>
				<option value="Trader Joes">Trader Joe's</option>
			</select></Label><br>
			<input type="hidden" value="${itemUpdate}" name="itemAddress" />
			<input type="submit" value="Submit Update" name="update" />
	
		</form>
	</h3>
	<a href="index.html">Go back to Select More Items</a>
</body>
</html>