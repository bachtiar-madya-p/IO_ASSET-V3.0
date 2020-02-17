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
                                    <i class="gi gi-brush"></i>User Manager<br><small></small>
                                </h1>
                            </div>
                        </div>
                        <ul class="breadcrumb breadcrumb-top">
                            <li>Managers</li>
                            <li><a href="">User Manager</a></li>
                        </ul>
                        <!-- END Page Header -->

                        <!-- Working Tabs Block -->
                        <div class="block full">
                            <!-- Working Tabs Title -->
                            <div class="block-title">
                                <h2>Working Tabs <small>Block and Simple</small></h2>
                            </div>
                            <!-- END Working Tabs Title -->

                            <!-- Working Tabs Content -->
                            <div class="row">
                                <div class="col-md-6">
                                    <!-- Block Tabs -->
                                    <div class="block full">
                                        <!-- Block Tabs Title -->
                                        <div class="block-title">
                                            <div class="block-options pull-right">
                                                <div class="btn-group">
                                                    <a class="btn btn-alt btn-sm btn-default dropdown-toggle" data-toggle="dropdown" href="javascript:void(0)"><i class="fa fa-angle-down"></i></a>
                                                    <ul class="dropdown-menu dropdown-custom dropdown-menu-right">
                                                        <li>
                                                            <a href="javascript:void(0)">Action</a>
                                                            <a href="javascript:void(0)">Action 2</a>
                                                        </li>
                                                        <li class="divider"></li>
                                                        <li><a href="javascript:void(0)">Another Action</a></li>
                                                    </ul>
                                                </div>
                                            </div>
                                            <ul class="nav nav-tabs" data-toggle="tabs">
                                                <li class="active"><a href="#example-tabs2-activity">Activity</a></li>
                                                <li><a href="#example-tabs2-profile">Profile</a></li>
                                                <li><a href="#example-tabs2-options" data-toggle="tooltip" title="Settings"><i class="fa fa-cogs"></i></a></li>
                                            </ul>
                                        </div>
                                        <!-- END Block Tabs Title -->

                                        <!-- Tabs Content -->
                                        <div class="tab-content">
                                            <div class="tab-pane active" id="example-tabs2-activity">Block tabs..</div>
                                            <div class="tab-pane" id="example-tabs2-profile">Profile..</div>
                                            <div class="tab-pane" id="example-tabs2-options">Settings..</div>
                                        </div>
                                        <!-- END Tabs Content -->
                                    </div>
                                    <!-- END Block Tabs -->
                                </div>
                                <div class="col-md-6">
                                    <!-- Default Tabs -->
                                    <ul class="nav nav-tabs push" data-toggle="tabs">
                                        <li class="active"><a href="#example-tabs-news">Activity</a></li>
                                        <li><a href="#example-tabs-profile">Profile</a></li>
                                        <li><a href="#example-tabs-messages" data-toggle="tooltip" title="Messages"><i class="fa fa-envelope-o"></i></a></li>
                                        <li><a href="#example-tabs-settings" data-toggle="tooltip" title="Settings"><i class="fa fa-cog"></i></a></li>
                                    </ul>
                                    <div class="tab-content">
                                        <div class="tab-pane active" id="example-tabs-news">News..</div>
                                        <div class="tab-pane" id="example-tabs-profile">Profile..</div>
                                        <div class="tab-pane" id="example-tabs-messages">Messages..</div>
                                        <div class="tab-pane" id="example-tabs-settings">Settings..</div>
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