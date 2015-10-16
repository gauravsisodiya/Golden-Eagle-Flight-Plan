<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" />
<link rel="stylesheet" href="core.css" />


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Checkpoint</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.3/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
<script src="../ckeditor/ckeditor.js"></script>
</head>

<body background="../back_image.jpg">
	<header class="navbar navbar-inverse navbar-static-top"> <span
		class="navbar-brand">Golden Eagle Flight Plan </span> <span
		class="navbar-brand pull-right">Signed in as <sec:authentication
			property="principal.firstName" />, <a
		href="<c:url value='/j_spring_security_logout' />">Sign out</a></span> </header>
	<form action="addCheckpoint.html" method="post">

		<div class="container">
			<div class="row col-md-16 ">
				<h2>Add New Checkpoint</h2>
				<div class="row col-md-6 col-md-offset-1 ">
					<table class="table ">
						<tr>
							<th>Select Runway :</th>
							<td><select name="runway"
								class="btn btn-default dropdown-toggle ">
									<c:forEach items="${runways}" var="rwy">
										<option value="${rwy.id}">${rwy.name}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<th>Select Stage:</th>
							<td><select name="stage"
								class="btn btn-default dropdown-toggle ">
									<c:forEach items="${stages}" var="stg">
										<option value="${stg.id}">${stg.name}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<th>Checkpoint Name:</th>
							<td><textarea cols="80" id="checkpoint" name="checkpoint"
									rows="10" class="ckeditor"></textarea> <input type="hidden" name="plan_id"
								value="${plan_id}"></td>
						</tr>

					</table>
					<input class="btn btn-primary pull-right" type="submit"
						name="addCheckpoint" style="border-radius: 20px;"
						value="Add Checkpoint to the Plan">
				</div>
			</div>
		</div>


	</form>
</body>
</html>