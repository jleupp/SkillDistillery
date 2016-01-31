<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>A Window For Elena</title>
</head>
<body>
	<h1>
	<c:choose>
		<c:when test="${method.equals('getItemByType')}">
		${itemList[0].type}
		</c:when>
		<c:otherwise>
		${itemList[0].brand}
		</c:otherwise>
	</c:choose></h1>
	<h3>
		<form action="∆List.do" method="GET">
		<c:forEach var="item" items="${itemList}">
			<br>
			<input type="checkbox" name="modifyList" value="${item}"/>
			Store Section: ${item.storeSection} <br>
			Brand: ${item.brand} <br>
			Type: ${item.type} <br>
			Price: ${item.price} <br>
		</c:forEach>
		<input type="submit" value="Add Items To List" name="add" />
		<input type="submit" value="Remove Item(s) From List" name="remove" />
		</form>
	</h3>
	<a href="index.html">Go back to Select More Items</a>
</body>
</html>