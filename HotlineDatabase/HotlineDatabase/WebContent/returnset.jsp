<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hotline Database</title>
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/main.css">
</head>
<body>
	<div id="container">
		<header id="hotlineHeader">
			<h1>Hotline Database</h1>
		</header>
			<div id="queryResultsOuter">
				<div id="queryResultsInner">
					<div id="sectionheader"><h1 class="sectionheader">Results</h1></div>
					<div id="storeList">
						<div class="subHeader"><h2>${rowsUpdated} results returned from query: ${prevCommands.get(0)}; </h2>
							<h2><a href="hothome.jsp">Enter New Query Statement</a></h2>
						</div>

						<div style="overflow-x:auto;">
							<table id="tableList">
								<tbody>
							<c:forEach var="row" items="${tableList}">
								${row.toString()}
							</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
	</div>
</body>
</html>