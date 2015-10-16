<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" />
<link rel="stylesheet" href="core.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body background="login1.jpg">

	<div class="container" style="margin-top: 200px; margin-left: 980px">
		<div class="col-md-3">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<strong>Sign In </strong>
					</h3>
				</div>

				<div class="panel-body">

					<form name="login" action="<c:url value='/j_spring_security_check' />" method="post">
						<label class="sr-only">Username</label>
						<input type="text" name="j_username" class="forminput  btn" cssClass="form-control"
							placeholder="Username" />
						<form:errors path="username" />
						<br /><br />
						<label class="sr-only">Password</label>
						<input type="password" name="j_password" class="forminput btn" cssClass="form-control"
							placeholder="Password" />
						<form:errors path="password" />
						<br />
						<div class="checkbox">
							<label> <input type="checkbox" value="remember-me" name="_spring_security_remember_me">
								Remember me
							</label>
						</div>

						<input class="submit btn btn-primary btn-block" type="submit" name="login"  style="border-radius: 20px;"
							value="Login" />
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>