<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" />
<link rel="stylesheet" href="core.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="../ckeditor/ckeditor.js"></script>
<title>Edit Checkpoint</title>
</head>

<body background="../back_image.jpg">
	<header class="navbar navbar-inverse navbar-static-top"> <span
		class="navbar-brand">Golden Eagle Flight Plan </span> <span
		class="navbar-brand pull-right">Signed in as <sec:authentication property="principal.firstName"/>, <a
		href="<c:url value='/j_spring_security_logout' />">Sign out</a></span> </header>
	<form action="editCheckpoint.html" method="post">
		<div class="container">
			<div class="row col-md-6 col-md-offset-1 ">
				<table class="table ">
					<tr>
						<th>Change Runway :</th>
						<td><select name="newRunway"
							class="btn btn-default dropdown-toggle ">
								<c:forEach items="${runways}" var="rwy">
									<c:choose>
										<c:when test="${rwy.id.equals(cell.runway.id) }">
											<option value="${rwy.id}" selected="selected">${rwy.name}</option>	
										</c:when>
										<c:otherwise>
											<option value="${rwy.id}">${rwy.name}</option>
										</c:otherwise>
									</c:choose>
									
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<th>Change Stage:</th>
						<td><select name="newStage"
							class="btn btn-default dropdown-toggle ">
								<c:forEach items="${stages}" var="stg">
									<c:choose>
										<c:when test="${stg.id.equals(cell.stage.id) }">
											<option value="${stg.id}" selected="selected">${stg.name}</option>	
										</c:when>
										<c:otherwise>
											<option value="${stg.id}">${stg.name}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<th>Checkpoint Name:</th>
						<td><textarea cols="80" id="checkpoint" name="newCheckpoint"
									rows="10" class="ckeditor">${checkpoint.description}</textarea> 
						
							<input type="text" name="newCheckpoint" class="btn"
							value="${checkpoint.description}"> <input type="hidden"
							name="plan_id" value="${plan_id}"> <input type="hidden"
							name="chk_id" value="${checkpoint.id}"> <input
							type="hidden" name="cell_id" value="${cell_id}"></td>
					</tr>
				</table>
				<input class="btn btn-primary pull-right" type="submit" name="editCheckpoint" style="border-radius: 20px;"
					value="Edit Checkpoint ">
			</div>
		</div>
	</form>
</body>
</html>