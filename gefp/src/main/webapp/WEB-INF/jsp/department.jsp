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
<title>${department.name}DepartmentHomepage</title>
</head>

<body background="back_image.jpg">
	<header class="navbar navbar-inverse navbar-static-top"> <span
		class="navbar-brand">Golden Eagle Flight Plan </span> <span
		class="navbar-brand pull-right">Signed in as <sec:authentication
			property="principal.firstName" />, <a
		href="<c:url value='/j_spring_security_logout' />">Sign out</a></span> </header>


	<div class="container">
		<div class="row col-md-16 ">
			<h2>Welcome to ${department.name} Department</h2>
			<br />
			<sec:authorize
				access="hasRole('ROLE_ADMIN') or hasRole('ROLE_ADVISOR')">
				<div class="row col-md-2  pull-right">
					<a href="homepage.html" style="border-radius: 20px;"
						class="btn btn-success btn-sm"><span
						class="glyphicon glyphicon-backward"></span> Back to Homepage</a>
				</div>
			</sec:authorize>
			<br /> <br /> <br />
			<div class="row col-md-6 col-md-offset--2 ">
				<table class="table  custab">

					<tr>
						<th>Current Plan :</th>
					</tr>
					<tr>
						<td>${department.currentPlan.name}</td>
						<td><a href="plan.html?id=${department.currentPlan.id}"
							style="border-radius: 20px;" class="btn btn-success btn-xs">
								<span class="glyphicon glyphicon-eye-open"></span> View
						</a></td>
					</tr>
					<tr>
						<td>
					</tr>
					<tr>
						<th>Other Plan :
					</tr>

					<c:forEach items="${department.plans}" var="plan">
						<c:choose>
							<c:when test="${plan.publishedDate != null}">
								<tr>
									<c:if test="${plan.id != department.currentPlan.id}">
										<td>${plan.name}</td>
										<td><a href="plan.html?id=${plan.id}"
											style="border-radius: 20px;"
											class=" btn btn-info btn-warning btn-xs"> <span
												class="glyphicon glyphicon-eye-open"></span> View
										</a> <sec:authorize access="hasRole('ROLE_ADMIN')">

												<c:choose>
													<c:when test="${plan.publishedDate  != null}">
														<a href="admin/setActive.html?id=${plan.id}"
															style="border-radius: 20px;"
															class="btn btn-info btn-primary btn-xs"> <span
															class="glyphicon glyphicon-home"></span> Set Active
														</a>
													</c:when>
													<c:otherwise>
														<a href="admin/publishPlan.html?id=${plan.id}"
															style="border-radius: 20px;"
															class="btn btn-danger btn-xs"> <span
															class="glyphicon glyphicon-cloud-upload"></span> Publish
															Plan
														</a>
													</c:otherwise>
												</c:choose>
											</sec:authorize>
									</c:if>

								</tr>
							</c:when>
							<c:otherwise>
								<sec:authorize access="hasRole('ROLE_ADMIN')">
									<tr>
										<c:if test="${plan.id != department.currentPlan.id}">
											<td>${plan.name}</td>
											<td><a href="plan.html?id=${plan.id}"
												style="border-radius: 20px;"
												class=" btn btn-info btn-warning btn-xs"> <span
													class="glyphicon glyphicon-eye-open"></span> View
											</a> <c:choose>
													<c:when test="${plan.publishedDate  != null}">
														<a href="admin/setActive.html?id=${plan.id}"
															style="border-radius: 20px;"
															class="btn btn-info btn-primary btn-xs"> <span
															class="glyphicon glyphicon-home"></span> Set Active
														</a>
													</c:when>
													<c:otherwise>
														<a href="admin/publishPlan.html?id=${plan.id}"
															style="border-radius: 20px;"
															class="btn btn-danger btn-xs"> <span
															class="glyphicon glyphicon-cloud-upload"></span> Publish
															Plan
														</a>
													</c:otherwise>
												</c:choose>
										</c:if>
									</tr>

								</sec:authorize>
							</c:otherwise>
						</c:choose>
					</c:forEach>

				</table>
			</div>

			<div class="row col-md-3 col-md-offset--2 ">
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<a href="admin/addPlan.html?department_id=${department.id}"
						style="border-radius: 20px;"
						class="btn btn-info btn-primary btn-large pull-right"> <span
						class="glyphicon glyphicon-plus"></span> Add New Plan
					</a>
				</sec:authorize>
			</div>

		</div>
	</div>
</body>
</html>