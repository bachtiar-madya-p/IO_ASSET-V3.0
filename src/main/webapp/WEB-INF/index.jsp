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
                <div class="x_panel tile quicklinks">
                    <div class="x_title">
                        <h3>Welcome to UMX</h3>
                    </div>
                    <div class="x_content">
                        <div class="col-md-3 col-sm-3 col-xs-6">
                            <a class="btn btn-default btn-block panel-body quicklink-body" href="/umx/rules">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-fw fa-book fa-stack-1x"></i>
                                </span>
                                <span class="col-sm-0">Manage Rule</span>
                            </a>
                        </div>
                        <div class="col-md-3 col-sm-3 col-xs-6">
                            <a class="btn btn-default btn-block panel-body quicklink-body" href="/umx/whitelists">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-fw fa-check-square fa-stack-1x"></i>
                                </span>
                                <span class="col-sm-0">Manage Whitelist</span>
                            </a>
                        </div>
                        <div class="col-md-3 col-sm-3 col-xs-6">
                            <a class="btn btn-default btn-block panel-body quicklink-body" href="/umx/executions">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-fw fa-play-circle fa-stack-1x"></i>
                                </span>
                                <span class="col-sm-0">Execution History</span>
                            </a>
                        </div>
                        <div class="col-md-3 col-sm-3 col-xs-6">
                            <a class="btn btn-default btn-block panel-body quicklink-body"
                                href="/umx/configurations/global">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-fw fa-cogs fa-stack-1x"></i>
                                </span>
                                <span class="col-sm-0">Global Settings</span>
                            </a>
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
</body>

</html>