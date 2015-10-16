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
<title>Add Stage</title>
</head>

<body background="../back_image.jpg">
	<header class="navbar navbar-inverse navbar-static-top"> <span
		class="navbar-brand">Golden Eagle Flight Plan </span> <span
		class="navbar-brand pull-right">Signed in as <sec:authentication property="principal.firstName"/>, <a
		href="<c:url value='/j_spring_security_logout' />">Sign out</a></span> </header>
	<form:form modelAttribute="stage">
		<div class="form-group">
			<div class="container">
				<div class="row col-md-16 ">
					<h2>Add New Stage</h2>
					<div class="row col-md-4 col-md-offset-1 ">
						<table class="table ">
							<tr>
								<th>Stage Name:</th>
								<td><input type="text" name="name" class="btn"> <input
									type="hidden" name="plan_id" value="${plan_id}"></td>
							</tr>
						</table>

						<input class="btn btn-primary pull-right glyphicon glyphicon-plus"
							type="submit" style="border-radius: 20px;" name="addStage"
							value="Add Stage to The Plan">
					</div>
				</div>
			</div>
		</div>
	</form:form>

</body>
</html>