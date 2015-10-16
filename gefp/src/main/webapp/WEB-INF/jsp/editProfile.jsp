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
<title>Edit your Profile</title>
</head>
<body background="back_image.jpg">
	<header class="navbar navbar-inverse navbar-static-top"> <span
		class="navbar-brand">Golden Eagle Flight Plan </span> <span
		class="navbar-brand pull-right">Signed in as <sec:authentication
			property="principal.firstName" />, <a href="<c:url value='/j_spring_security_logout' />">Sign out</a></span>
	</header>
	<div class="container" style="margin-top: 100px; margin-left: 400px">
		<div class="col-md-5">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<strong>Edit your profile </strong>
					</h3>
				</div>

				<div class="panel-body">

					<form:form class="form-signin">

						<table class="table ">
							<tr>
								<th>Username</th>
								<td>${user.username}${user.id}</td>
							</tr>
							<tr>
								<th>Change Major</th>
								<td><select name="newDepartment"
									class="btn btn-default dropdown-toggle ">
										<c:forEach items="${departments}" var="dept">
											<c:choose>
												<c:when test="${dept.id.equals(user.major.id)}">
													<option value="${dept.id}" selected="selected">${dept.name}</option>
												</c:when>
												<c:otherwise>
													<option value="${dept.id}">${dept.name}</option>
												</c:otherwise>
											</c:choose>

										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<th>Change EmailId</th>
								<td><input name="emailId" value="${user.emailId}" /> <input
									type="hidden" name="userId" value="${user.id}"></td>
							</tr>
						</table>

						<a href="changePassword.html?id=${user.id}" class="pull-right">Change
							Password</a>
						<br />
						<br />
						<input class="btn btn-primary btn-block" type="submit"
							value="Edit Profile" style="border-radius: 20px;" />
					</form:form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>