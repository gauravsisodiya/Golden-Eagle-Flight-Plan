<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" />
<link rel="stylesheet" href="core.css" />


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Change Password</title>

</head>
<body background="back_image.jpg">
	<header class="navbar navbar-inverse navbar-static-top"> <span
		class="navbar-brand">Golden Eagle Flight Plan </span> <span
		class="navbar-brand pull-right">Signed in as <sec:authentication
			property="principal.firstName" />, <a href="<c:url value='/j_spring_security_logout' />">Sign out</a></span>
	</header>
	<div class="container" style="margin-top: 100px; margin-left: 430px">
		<div class="col-md-5">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<strong>Change Password </strong>
					</h3>
				</div>

				<div class="panel-body">

					<form:form class="form-signin" modelAttribute="user">

						<table class="table ">
							<tr>
								<th>New Password</th>
								<td><form:input path="username" placeHolder="New Password" /><br />
									<form:errors path="username" /></td>
							</tr>
							<tr>
								<th>Confirm Password</th>
								<td><form:input path="password"
										placeHolder="Confirm Password" /></td>
							</tr>
						</table>
						<input class="btn btn-primary btn-block" type="submit"
							style="border-radius: 20px;" value="Done" />
					</form:form>

				</div>
			</div>
		</div>
	</div>
</body>
</html>