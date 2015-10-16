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
<title>Login Homepage</title>
</head>

<body background="back_image.jpg">
	<header class="navbar navbar-inverse navbar-static-top"> <span
		class="navbar-brand">Golden Eagle Flight Plan </span> <span
		class="navbar-brand pull-right">Signed in as <sec:authentication property="principal.firstName"/>, <a
		href="<c:url value='/j_spring_security_logout' />">Sign out</a></span> </header>

	
	<!-- /widget-header -->

	<div class="col-xs-3 pull-center" style="margin-top: 100px; margin-left: 480px" align="center">
		<table class="table" align="center">
			<thead>
				<tr>
					<th class="text-center"> Department List</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${departments}" var="dept">
					<tr>
					<td class="text-center">
						<a href="department.html?id=${dept.id}" style="border-radius: 20px;" class="btn btn btn-info btn-lg btn-primary">${dept.name}</a>
					</td>	
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
	<!-- /widget-content -->
</body>
</html>