<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spring MVC with JSP Crud Example</title>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<link rel="icon"
	href="<c:url value="/resources/images/sudarshan-logo.png"/>" />
<link rel="shortcut icon"
	href="<c:url value="/resources/images/ico/favicon.ico"/>"
	type="image/x-icon">

<style type="text/css">
.page-title {
	font-family: 'Open Sans', sans-serif;
	font-style: normal;
	font-weight: 600;
	font-size: 24px;
	color: #4C4C4C;
}

/* .dataTables_filter {
	text-align: right;
} */
.content_register {
	width: 98.9%;
	height: auto;
	float: left;
	margin-top: 55px;
}

div.dataTables_wrapper div.dataTables_filter label {
	font-weight: normal;
	white-space: nowrap;
	text-align: left;
}

div.dataTables_wrapper div.dataTables_filter {
	text-align: right;
}

.mt-1 {
	margin-top: 1rem !important;
}

.mb-2, .my-2 {
	margin-bottom: 0.5rem !important;
}

div.dataTables_wrapper div.dataTables_length select {
	width: 75px;
	display: inline-block;
}

div.dataTables_wrapper div.dataTables_filter {
	text-align: right;
}

div.dataTables_wrapper div.dataTables_filter input {
	margin-left: 0.5em;
	display: inline-block;
	width: auto;
}
</style>

</head>


<body>
	<div id="fullpage">
		<div class="header">

			<%@include file="../../views/common/welcome-header.jspf"%>

		</div>

		<div class="container">
				<div class="row" style="margin-top: 20px;">
					<%@include file="../../views/common/message.jspf"%>
				</div>
		</div>

		<div class="container" style="margin-top: 50px;">
			<div class="row">

				<div class="col-sm-10">
					<div class="page-title">Manage Employee List</div>
				</div>


				<div class="col-sm-2">

					<div class="btn-group" role="group"
						aria-label="Button group with nested dropdown">

						<a href="javascript:void(0)" class="btn btn-primary"
							data-toggle="tooltip" data-placement="bottom" title=""
							data-original-title="Add New"
							onclick="location.href='${pageContext.request.contextPath}/employee/add'">Add
							New</a>
					</div>
				</div>

				<!-- 	<div class="col-sm-12" style="margin-bottom: 30px">
					<div class=" mt-3 mb-3" style="border-bottom: 1px dashed #064d76;"></div>
				</div> -->
			</div>
		</div>

		<div class="content_register">

			<div class="container">

				<!-- <div class="row">
					<div class="page-title">Manage Employee List</div>
				</div> -->

				<div class="row">

					<div class="panel-body">

						<%-- <c:choose>
							<c:when
								test="${dtos!= null && dtos != '' && not empty dtos && dtos.size() != 0}"> --%>
						<table class="table table-striped table-bordered table-hover"
							id="example-table">
							<thead>
								<tr class="table_header">
									<th>Employee Name</th>
									<th>Designation</th>
									<th>Department</th>
									<th>Address</th>
									<th style="width: 200px;">Actions</th>
								</tr>
							</thead>
							<tbody>

								<c:choose>
									<c:when
										test="${dtos!= null && dtos != '' && not empty dtos && dtos.size() != 0}">

										<c:forEach items="${dtos}" var="employee">
											<tr>
												<td><a
													href="${pageContext.request.contextPath}/employee/view?radio=${employee.empId}">${employee.empName}</a>
												</td>
												<td>${employee.empDesign}</td>
												<td>${employee.empDept}</td>
												<td>${employee.empAddress}</td>
												<td><a
													href="${pageContext.request.contextPath}/employee/view?radio=${employee.empId}">View</a>
													| <a
													href="${pageContext.request.contextPath}/employee/modify?radio=${employee.empId}">Update</a>
													| <a
													href="${pageContext.request.contextPath}/employee/delete?radio=${employee.empId}">Delete</a></td>
											</tr>
										</c:forEach>

									</c:when>
									<c:otherwise>
										<tr class="odd">
											<td valign="top" colspan="6" class="dataTables_empty"
												style="text-align: center;">No Records to Display</td>
										</tr>
									</c:otherwise>
								</c:choose>

							</tbody>
						</table>
						<%-- 
							</c:when>
							<c:otherwise>
								<div class="alert alert-info mb-2" role="alert">
									<strong>No Records Found!</strong>
								</div>
							</c:otherwise>
						</c:choose> --%>

					</div>

				</div>

			</div>

		</div>

	</div>


	<script type="text/javascript">
		$(function() {
			$('#example-table')
					.DataTable(
							{
								pageLength : 5,
								"oLanguage" : {
									"sSearch" : "<spring:message code='label.order.search.submit'/>",
									"sZeroRecords" : "<spring:message code='label.zerorec'/>",
									"sLengthMenu" : "<spring:message code='label.rowsDisplay'/>&nbsp;_MENU_",
									"sInfo" : "<spring:message code='label.showing'/> _START_ - _END_ <spring:message code='label.of'/> _TOTAL_ <spring:message code='label.records'/>",
									"sInfoEmpty" : "<spring:message code='label.showing'/> 0 <spring:message code='label.records'/>",
									"sProcessing" : "<spring:message code='label.Processing'/>",
									"sInfoFiltered" : "",
									"oPaginate" : {
										"sFirst" : "<spring:message code='label.first'/>",
										"sPrevious" : "<spring:message code='label.previous'/>",
										"sNext" : "<spring:message code='label.next'/>",
										"sLast" : "<spring:message code='label.last'/>"
									},
								},
								"columns" : [ {
									"orderable" : false
								}, {
									"data" : "audtEvent"
								}, {
									"data" : "audtTimestamp"
								}, {
									"data" : "audtUid"
								}, {
									"data" : "audtUname"
								} ]
							});
		})
	</script>

	<%@ include file="/WEB-INF/views/common/audit_script.jspf"%>


	<div class="footer">
		<%@include file="../../views/login-footer.jsp"%>
	</div>
</body>
</html>