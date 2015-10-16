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
<title>Welcome ${uname}</title>
</head>
<body background="back_image.jpg">

	<header class="navbar navbar-inverse navbar-static-top"> <span
		class="navbar-brand">Golden Eagle Flight Plan </span> <span
		class="navbar-brand pull-right"><a href="editProfile.html">Edit
			Profile</a> | <a href="<c:url value='/j_spring_security_logout' />">Sign out</a></span> </header>
	<form:form action="success.html">
		<div class="container">
			<br />
			<div class="row col-md-7 col-md-offset-3 ">
				<h3>Thank you, your Profile Changes have been saved.</h3>
			</div>

			<div class="row col-md-2 col-md-offset-5 ">
				<input class="btn btn-primary btn-block" style="border-radius: 20px;" type="submit" value="Back to HomePage" />
			</div>
		</div>
	</form:form>

</body>
</html>