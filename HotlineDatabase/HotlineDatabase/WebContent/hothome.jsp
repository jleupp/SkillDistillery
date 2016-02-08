<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'><meta charset="UTF-8">
<title>Hotline Database</title>
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/main.css">
</head>
<body>
	<div id="container">
		<header id="hotlineHeader"><h1>Hotline Database</h1></header>
		<div id="hothomeOuter">
			<div class="hothomeInner">
				<div id="sectionheader"><h1 class="sectionheader">Query ${loginCredentials.database}</h1></div>
				<div class="subHeader">
					<h2>Enter a SQL query</h2>
					
					<form action="dbQuery.do" method="POST">
						<textarea class="textArea" placeholder="Query statement" name="query" ></textarea><br>
						<input class="submit" type="submit" value="Query" />
					</form>
				</div>
				<div id="sectionheader"><h1 class="sectionheader">Update ${loginCredentials.database}</h1></div>
				<div class="subHeader">
					<h2>Enter a SQL update statement</h2>
					
					<form action="dbUpdate.do" method="POST">
						<textarea class="textArea" placeholder ="Update Statement" name="update"></textarea><br>
						<input class="submit" type="submit" value="Update" />
					</form><br>
				</div>
				<div id="sectionheader"><h1 class="sectionheader">Prepared Statements</h1></div>
				<div class="subHeader">
					<h2>Query or update from the list of prepared statements</h2>

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
						<input class="submit" type="submit" value="Prime Phrase" />
					</form>
				</div>
	
	
			</div>
		</div>
	</div>
</body>
</html>