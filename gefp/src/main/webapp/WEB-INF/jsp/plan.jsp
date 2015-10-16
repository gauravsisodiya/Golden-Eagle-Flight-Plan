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
<title>${plan.name}Plan</title>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
<script>
	$(function() {
		$("ul.check").sortable(
				{
					update : function(event, ui) {
						$.ajax({
							url : "admin/reorderCheckpoint.html",
							data : "checkpointId=" + ui.item.attr('id')
									+ "&newIndex=" + ui.item.index()
						});
					}
				});
		$("ol.stage_list").sortable(
				{
					update : function(event, ui) {
						alert("stage alert")
						$.ajax({
							url : "admin/reorderCheckpoint.html",
							data : "checkpointId=" + ui.item.attr('id')
									+ "&newIndex=" + ui.item.index()
						});
					}
				});
	});
</script>
</head>

<body background="back_image.jpg">
	<header class="navbar navbar-inverse navbar-static-top"> <span
		class="navbar-brand">Golden Eagle Flight Plan </span> <span
		class="navbar-brand pull-right">Signed in as <sec:authentication
			property="principal.firstName" />, <a
		href="<c:url value='/j_spring_security_logout' />">Sign out</a></span> </header>
	<form:form>
		<div class="container">
			<div class="row col-md-16">
				<h2>You are viewing : ${plan.name}</h2>
				<br /> <a href="department.html?id=${plan.department.id}"
					class="btn btn-success btn-sm pull-right"><span
					class="glyphicon glyphicon-backward"></span> Back to
					${plan.department.name} Department Homepage</a> <br /> <br /> <br />
				<div class="row col-md-4 col-md-offset-2 pull-right">
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<a href="admin/selectRunway.html?plan_id=${plan.id}"
							class="btn btn-info btn-primary btn-xs"><span
							class="glyphicon glyphicon-plus"></span> Add Runway</a> | <a
							href="admin/selectStage.html?plan_id=${plan.id}"
							class="btn btn-info btn-primary btn-xs"> <span
							class="glyphicon glyphicon-plus"></span>Add Stage
						</a> | <a href="admin/addCheckpoint.html?plan_id=${plan.id}"
							class="btn btn-info btn-primary btn-xs"><span
							class="glyphicon glyphicon-plus"></span> Add Checkpoint</a>
					</sec:authorize>
				</div>
				<br /> <br />
				<div class="row col-md-16 col-md-offset--2 ">
					<table border="1">
						<tr>
							<th></th>
							<c:forEach items="${plan.runways}" var="runway">
								<th class="text-center">${runway.name}<sec:authorize
										access="hasRole('ROLE_ADMIN')">
										<a
											href="admin/editRunway.html?id=${runway.id}&plan_id=${plan.id}"
											class="btn btn-info btn-warning btn-xs"> <span
											class="glyphicon glyphicon-edit"></span> Edit
										</a>
									</sec:authorize></th>
							</c:forEach>
						</tr>
						<ol id="sortable" class="stage_list">
							<c:forEach items="${plan.stages}" var="stage">
								<tr>
									<th class="text-center">${stage.name}<sec:authorize
											access="hasRole('ROLE_ADMIN')">
											<a
												href="admin/editStage.html?id=${stage.id}&plan_id=${plan.id}"
												class="btn btn-info btn-warning btn-xs"> <span
												class="glyphicon glyphicon-edit"></span> Edit
											</a>
										</sec:authorize></th>
									<c:forEach items="${plan.runways}" var="runway">
										<c:set var="status" value="false" />

										<c:forEach items="${plan.cells}" var="cell">

											<c:if
												test="${stage.id.equals(cell.stage.id) && runway.id.equals(cell.runway.id)}">
												<c:set var="status" value="true" />
												<td><ul class="check">
														<c:forEach items="${cell.checkpoints}" var="checkpoint">
															<li id="${checkpoint.id}"><input type="checkbox">${checkpoint.description}
																<sec:authorize access="hasRole('ROLE_ADMIN')">
																	<a
																		href="admin/editCheckpoint.html?chk_id=${checkpoint.id}&cell_id=${cell.id}"
																		class="btn btn-info btn-warning btn-xs"> <span
																		class="glyphicon glyphicon-edit"></span> Edit
																	</a>
																	<a
																		href="admin/removeCheckpoint.html?chk_id=${checkpoint.id}&cell_id=${cell.id}"
																		class="btn btn-info btn-danger btn-xs"> <span
																		class="glyphicon glyphicon-edit"></span> Remove
																	</a>
																</sec:authorize></li>


														</c:forEach>
													</ul></td>
											</c:if>
										</c:forEach>

										<c:choose>
											<c:when test="${status == true }">
												<c:set var="status" value="false" />
											</c:when>
											<c:otherwise>
												<td></td>
											</c:otherwise>
										</c:choose>

									</c:forEach>
								</tr>
							</c:forEach>
							</ol>
					</table>
				</div>
			</div>
		</div>
		<br />
	</form:form>
</body>
</html>