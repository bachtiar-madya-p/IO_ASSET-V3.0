<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="pages/css_import.jsp"%>
<link href="/umx/lib/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">
<title>UMX</title>
</head>

<body class="nav-md">
	<!-- Loading Screen -->
	<div class="loader"></div>
	<div class="container body">
		<div class="main_container">
			<!-- side nav -->
			<%@include file="pages/side_menu.jsp"%>
			<!-- /side nav -->
			<!-- top navigation -->
			<%@include file="pages/top_nav.jsp"%>
			<!-- /top navigation -->
			<!-- page content -->
			<div class="right_col" role="main" id="vue">
				<div class="">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel">
							<header class="x_title">
								<div class="row">
									<h2>Execution History</h2>
								</div>
								<div class="row">
									<h1>
										<select data-toggle="dropdown" id="application-selection"
											name="applicationSelection" aria-expanded="true"
											v-model="applicationId" v-on:load="applicationChanged"
											@change="applicationChanged">
											<option value="">Select an application</option>
											<option v-for="item in applications" :key="item.id"
												:value="item.id">{{item.name}}</option>
										</select>
									</h1>
									<div class="row">
										<input type="text" class="form-control" id="dateRange"
											placeholder="" style="display: inline-block; width: 18.5em;"
											onchange="main.applicationChanged();" />
									</div>
								</div>
							</header>
							<div class="x_content">
								<div style="margin-bottom: 5px; display: none;" class="row">
									<div class="col-sm-2 col-xs-12">
										<label>Day Mode:</label>
									</div>
									<div class="col-sm-4 col-xs-12">
										<label> <input type="checkbox" class="js-switch"
											id="dayMode" />
										</label>
									</div>
								</div>
								<span>
									<div class="row">
										<div class="table-responsive" style="margin-top: 1em" :hidden="!applicationId">
											<table id="executionHistoryTable" class="table table-hover"
												style="width: 100%;">
												<thead>
													<tr>
														<th>Name</th>
														<th>Status</th>
														<th>Created</th>
														<th>Completed</th>
														<th>Download Report</th>
														<th>Delete Execution</th>
													</tr>
												</thead>
												<tbody>
												</tbody>
											</table>
										</div>
									</div>
								</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /page content -->
		<!-- footer content -->
		<%@include file="pages/footer.jsp"%>
		<!-- /footer content -->
	</div>
	<!-- javascript imports -->
	<%@include file="pages/js_import.jsp"%>
	<script src="/umx/lib/moment/js/moment.min.js"></script>
	<script src="/umx/lib/bootstrap-notify/js/bootstrap-notify.min.js"></script>
	<script src="/umx/lib/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script src="/umx/js/executionHistory.js"></script>
</body>

</html>