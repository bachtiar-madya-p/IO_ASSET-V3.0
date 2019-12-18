<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <%@include file="pages/css_import.jsp"%>
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
                <header class="x_title">
                    <div class="row">
                        <h2>
                            Created on {{ startDate }}
                        </h2>
                        <a v-if="completed" :href="violationsHref" class="btn btn-info" style="float: right" v-cloak>
                            <i class="fa fa-download"></i>
                            Violations Report (CSV)
                        </a>
                    </div>
                </header>
                <div class="container">
                    <div class="row">
                        <div class="col-md-4 col-xs-12">
                            <div class="panel panel-default">
                                <div class="panel-body text-center">
                                    <h1 v-cloak>
                                        {{ accountsProcessed }}
                                        <small style="font-size: .5em; display: block; margin-top: .3em">
                                            Accounts
                                        </small>
                                    </h1>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-xs-12">
                            <div class="panel panel-default">
                                <div class="panel-body text-center">
                                    <h1 v-cloak>
                                        {{ rulesProcessed }}
                                        <small style="font-size: .5em; display: block; margin-top: .3em">
                                            Rules
                                        </small>
                                    </h1>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-xs-12">
                            <div class="panel panel-default">
                                <div class="panel-body text-center">
                                    <h1 v-cloak>
                                        {{ whitelistProcessed }}
                                        <small style="font-size: .5em; display: block; margin-top: .3em">
                                            Whitelisted
                                        </small>
                                    </h1>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <span class="text-muted">
                                                Start Date
                                            </span>
                                            <h4 style="display: block" v-cloak>
                                                {{ startDate }}
                                            </h4>
                                        </div>
                                        <div class="col-md-6">
                                            <span class="text-muted">
                                                End Date
                                            </span>
                                            <h4 style="display: block" v-cloak>
                                                {{ endDate }}
                                            </h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 col-xs-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    Whitelisted & Rule
                                </div>
                                <div class="panel-body">
                                    <canvas id="dispensationRuleChart" style="height: 150px; width: 80%;"></canvas>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-xs-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    Compliant Accounts
                                </div>
                                <div class="panel-body">
                                    <canvas id="compliantChart" style="width: 100%; height: auto"></canvas>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-xs-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    Whitelisted Accounts
                                </div>
                                <div class="panel-body">
                                    <canvas id="dispensationChart" style="width: 100%; height: auto"></canvas>
                                </div>
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
    <script src="/umx/js/executionReport.js"></script>
</body>

</html>