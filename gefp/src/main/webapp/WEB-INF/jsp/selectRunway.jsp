<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" />
<link rel="stylesheet" href="core.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select Runway</title>
</head>
<body>
<body background="../back_image.jpg">
	<header class="navbar navbar-inverse navbar-static-top"> <span
		class="navbar-brand">Golden Eagle Flight Plan </span> <span
		class="navbar-brand pull-right">Signed in as <sec:authentication property="principal.firstName"/>, <a
		href="<c:url value='/j_spring_security_logout' />">Sign out</a></span> </header>

	<form method="post" action="selectRunway.html">
		<div class="form-group">
			<div class="container">
				<div class="row col-md-6 col-md-offset-1 ">
					<table class="table ">
						<tr>
							<th>Select Existing Runway</th>
							<td><select name="runway" class="btn btn-default dropdown-toggle ">
									<c:forEach items="${runways}" var="rwy">
										<option value="${rwy.id}">${rwy.name}</option>
									</c:forEach>
							</select><input type="hidden" name="plan_id" value="${plan.id}"></td>
						</tr>
					</table>
					<input class="btn btn-primary pull-right" type="submit" style="border-radius: 20px;"
						name="selectRunway" value="Add Runway">
					<br/>
					<br/>	
						
					<div class="row col-md-3 col-md-offset-5 ">
					<h2>Or</h2>
					<br/>
					<br/>	
					
					<a href="addRunway.html?plan_id=${plan.id}"
						class="btn btn-primary btn-large pull-right" style="border-radius: 20px;"> <span
						class="glyphicon glyphicon-plus"></span> Add New Runway
					</a>
					
				</div>	
						
				</div>
				
			</div>
			</div>
	</form>
</body>
</html>