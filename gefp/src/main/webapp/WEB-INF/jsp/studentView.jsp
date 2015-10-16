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
<title>Welcome ${uname}</title>
<script type="text/javascript" src="javascript/jquery-2.1.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("input[type='checkbox']").click(function() {
			var checkId = $(this).val();

			if ($(this).is(":checked")) {
				var flag = true;
			} else {
				var flag = false;
			}
			$.ajax({
				url : "saveCheckpoint.html",
				data : "checkpoints=" + checkId + "&flag=" + flag
			})

		});
	});
</script>
</head>
<body background="back_image.jpg">
	<header class="navbar navbar-inverse navbar-static-top"> <span
		class="navbar-brand">Golden Eagle Flight Plan </span> <span
		class="navbar-brand pull-right">Signed as <sec:authentication
				property="principal.firstName" />, <a
		href="<c:url value='/j_spring_security_logout' />">Sign out</a></span> </header>
	<form:form>


		<div class="container">
		<div class="row col-md-16 ">
		<h2>Welcome to ${user.major.name}</h2>
			<div class="row col-md-16 col-md-offset-9">
				
				<a href="department.html?id=${user.major.id}"
					style="border-radius: 20px;"
					class="btn btn-sm btn-success">View Department Plans </a>
					<sec:authorize access="hasRole('ROLE_STUDENT')">|
				   <a href="editProfile.html?id=${user.id}"
					style="border-radius: 20px;"
					class="btn btn-sm btn-success">Edit Profile</a></sec:authorize>  
			</div>
			<br /> <br />
			<div class="row col-md-16">
				<table border="1">
					<tr>
						<th></th>

						<c:forEach items="${plan.runways}" var="runway">
							<th class="text-center">${runway.name}</th>
						</c:forEach>
					</tr>
					<c:forEach items="${plan.stages}" var="stage">
						<tr>
							<th class="text-center">${stage.name}</th>
							<c:forEach items="${plan.runways}" var="runway">
								<c:set var="status" value="false" />
								<c:forEach items="${plan.cells}" var="cell">
									<c:if
										test="${stage.id.equals(cell.stage.id) && runway.id.equals(cell.runway.id)}">
										<c:set var="status" value="true" />
										<td><c:forEach items="${cell.checkpoints}"
												var="checkpoint">
												<c:set var="flag" value="false" />
												<c:forEach items="${user.checkpoints}" var="uchecked">
													<c:if test="${uchecked.id.equals(checkpoint.id)}">
														<c:set var="flag" value="true" />
													</c:if>
												</c:forEach>
												<c:choose>
													<c:when test="${flag == true}">
														<input id="chkbtn" type="checkbox" name="checkpt"
															value="${checkpoint.id}" checked="checked">${checkpoint.description}
													<br />
														<c:set var="flag" value="false" />
													</c:when>
													<c:otherwise>
														<input id="chkbtn" type="checkbox" name="checkpt"
															value="${checkpoint.id}">${checkpoint.description}
													<br />
													</c:otherwise>
												</c:choose>


											</c:forEach></td>
									</c:if>
								</c:forEach>
								<c:choose>
									<c:when test="${status == true }">
										<c:set var="status" value="false" />
									</c:when>
									<c:otherwise>
										<td>&nbsp;</td>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
				<br />

			</div>
		</div>
		</div>
		<br />
	</form:form>

</body>
</html>