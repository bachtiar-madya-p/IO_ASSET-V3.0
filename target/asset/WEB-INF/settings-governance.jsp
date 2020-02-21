<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="en">
    <head>
        <%@include file="page/css_import.jsp"%>
        <title>IO-T - Dashboard</title>
    </head>
    <body>
        <div id="page-wrapper">
            <!-- Preloader -->
            <%@include file="page/preloader.jsp"%>
            <!-- END Preloader -->

            <div id="page-container" class="sidebar-partial sidebar-visible-lg sidebar-no-animations">
                <!-- Main Sidebar -->
                <%@include file="page/side-nav.jsp"%>
                <!-- END Main Sidebar -->

                <!-- Main Container -->
                <div id="main-container">
                    <%@include file="page/header.jsp"%>
                    <!-- END Header -->

                    <!-- Page content -->
                    <div id="page-content">
                        <!-- Page Header -->
                        <div class="content-header">
                            <div class="header-section">
                                <h1>
                                    <i class="gi gi-brush"></i>Setting<br><small>Governance</small>
                                </h1>
                            </div>
                        </div>
                        <ul class="breadcrumb breadcrumb-top">
                            <li>Settings</li>
                            <li><a href="">Governance</a></li>
                        </ul>
                        <!-- END Page Header -->

                        <!-- Working Tabs Block -->
                        <div class="block full">
                            <!-- Working Tabs Content -->
                            <div class="row">
                                <div>
                                    <!-- Default Tabs -->
                                    <ul class="nav nav-tabs push" data-toggle="tabs">
                                        <li class="active"><a href="#settings-database">Database</a></li>
                                        <li><a href="#settings-mail-server">Email Server</a></li>
                                        <li><a href="#settings-fm-config">FM Config</a></li>
                                        <li><a href="#settings-uss-config">USS Config</a></li>
                                    </ul>
                                    <div class="tab-content">
                                        <!-- Database configuration Tabs -->
                                        <div class="tab-pane active" id="settings-database">
                                            <div class="row">
                                                <!-- Basic Form Elements Block -->
                                                <div class = "col-sm-4">
                                                    <!-- Basic Form Elements Content -->
                                                    <form action="page_forms_general.html" method="post" enctype="multipart/form-data" class="form-horizontal" onsubmit="return false;">
                                                        <div class=" form-group">
                                                            <label class="col-md-3 control-label" for="example-text-input">Db Host</label>
                                                            <div class="col-md-9">
                                                                <input type="text" id="example-text-input" name="example-text-input" class="form-control" placeholder="Text">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-md-3 control-label" for="example-text-input">Db Port</label>
                                                            <div class="col-md-9">
                                                                <input type="text" id="example-text-input" name="example-text-input" class="form-control" placeholder="Text">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-md-3 control-label" for="example-text-input">Db Name</label>
                                                            <div class="col-md-9">
                                                                <input type="text" id="example-text-input" name="example-text-input" class="form-control" placeholder="Text">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-md-3 control-label" for="example-text-input">Db Username</label>
                                                            <div class="col-md-9">
                                                                <input type="text" id="example-text-input" name="example-text-input" class="form-control" placeholder="Text">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-md-3 control-label" for="example-text-input">Db Password</label>
                                                            <div class="col-md-9">
                                                                <input type="text" id="example-text-input" name="example-text-input" class="form-control" placeholder="Text">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-md-3 control-label" for="example-text-input">Pool Size</label>
                                                            <div class="col-md-9">
                                                                <input type="text" id="example-text-input" name="example-text-input" class="form-control" placeholder="Text">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-md-3 control-label" for="example-text-input">Max Connection</label>
                                                            <div class="col-md-9">
                                                                <input type="text" id="example-text-input" name="example-text-input" class="form-control" placeholder="Text">
                                                            </div>
                                                        </div>
                                                        <div class="form-group form-actions">
                                                            <div class="col-sm-9 col-md-offset-3">
                                                                <button type="Save" class="col-md-2 btn btn-sm btn-success">Save</button>
                                                            </div>
                                                        </div>
                                                    </form>
                                                    <!-- END Basic Form Elements Content -->
                                                </div>
                                                <!-- END Basic Form Elements Block -->
                                            </div>

                                        </div>
                                        <!-- END Database configuration Tabs -->
                                        <!-- Email Server configuration Tabs -->
                                        <div class="tab-pane active" id="settings-mail-server">

                                        </div>
                                        <!-- END Email Server configuration Tabs -->
                                        <!-- Database configuration Tabs -->
                                        <div class="tab-pane active" id="settings-fm-config">

                                        </div>
                                        <!-- END Database configuration Tabs -->
                                        <!-- Database configuration Tabs -->
                                        <div class="tab-pane active" id="settings-database">

                                        </div>
                                        <!-- END Database configuration Tabs -->
                                    </div>
                                    <!-- END Default Tabs -->
                                </div>
                            </div>
                            <!-- END Working Tabs Content -->
                        </div>
                        <!-- END Working Tabs Block -->

                    </div>
                    <!-- END Page Content -->

                    <!-- Footer -->
                    <%@include file="page/footer.jsp"%>
                    <!-- END Footer -->
                </div>
                <!-- END Main Container -->
            </div>
            <!-- END Page Container -->
        </div>
        <!-- END Page Wrapper -->

        <!-- Scroll to top link, initialized in js/app.js - scrollToTop() -->
        <a href="#" id="to-top"><i class="fa fa-angle-double-up"></i></a>
            <%@include file="page/js_import.jsp"%>
    </body>
</html>