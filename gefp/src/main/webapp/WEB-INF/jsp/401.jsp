<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" />
<link rel="stylesheet" href="core.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UnAuthorized User</title>
</head>
<body background="back_image.jpg">
	<header class="navbar navbar-inverse navbar-static-top"> <span
		class="navbar-brand">Golden Eagle Flight Plan </span></header>
	<div class="container">
		<div class="row col-md-16 col-md-offset-3">
			<h2>Invalid Login Credentials.</h2>
			<a href="login.html"
						class="btn btn-success btn-sm "  ><span
						class="glyphicon glyphicon-log-in"></span> Back to
						Login</a>
	</div>
</body>
</html>