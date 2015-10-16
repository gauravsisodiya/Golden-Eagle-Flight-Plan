<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Homepage</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.3/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script>
	$(function() {

		$("#searchTerm").autocomplete(
				{
					source : "<c:url value='autocomplete.html'/>",
					select : function(event, ui) {
						if (ui.item)
							window.location.href = "../studentView.html?id="
									+ ui.item.id;
					}
				});

	});
</script>
</head>

<body background="../back_image.jpg">
	<header class="navbar navbar-inverse navbar-static-top"> <span
		class="navbar-brand">Golden Eagle Flight Plan </span> <span
		class="navbar-brand pull-right">Signed in as <sec:authentication property="principal.firstName"/>, <a
		href="<c:url value='/j_spring_security_logout' />">Sign out</a></span> </header>

	<div class="container ">
		<div class="row col-md-16">
			<a href="../homepage.html" style="border-radius: 20px;"
				class="btn btn-lg btn-success pull-right">View Department Plans</a>
		</div>
		<br /> <br />

		<div class="row col-md-3 col-md-offset-4 ">

			<form action="search.html" method="get">
				<h3 align="center">Search Student</h3>
				<p>
					<input id="searchTerm" name="term" type="text" class="form-control"
						value="${param.term}" /><br /> <input name="search"
						type="submit" class="btn btn-primary pull-right" value="Search"
						style="border-radius: 20px;" />
				</p>
			</form>
		</div>
		<br /> <br />
		<br />
		<br />
		<div class="row col-md-8 col-md-offset-2 ">
		
		<br />
		<br />
		<c:if test="${not empty users}">
				<form id="usersForm" method="post">
					<table class="table  custab">
						<tr>
							<th></th>
							<th>CIN</th>
							<th>Name</th>
							<th>Email</th>
							<th></th>
						</tr>
						<c:forEach items="${users}" var="user">
							<tr>
								<td class="center"></td>

								<td>${user.cin}</td>
								<td>${user.name}</td>
								<td>${user.emailId}</td>
								<td><a href="../studentView.html?id=${user.id}"
										style="border-radius: 20px;"
										class=" btn btn-info btn-warning btn-xs"> <span
											class="glyphicon glyphicon-eye-open"></span> View Student Plan
									</a></td>
							</tr>
						</c:forEach>
					</table>
					<input type="hidden" name="backUrl" value="/user/search" />
				</form>
			</c:if>

		</div>

	</div>
</body>
</html>