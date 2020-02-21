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
                                    <i class="gi gi-brush"></i>Page Title<br><small>Subtitle</small>
                                </h1>
                            </div>
                        </div>
                        <ul class="breadcrumb breadcrumb-top">
                            <li>Category</li>
                            <li><a href="">Page</a></li>
                        </ul>
                        <!-- END Page Header -->

                        <!-- Example Block -->
                        <div class="block">
                            <!-- Example Title -->
                            <div class="block-title">
                                <div class="block-options pull-right">
                                    <a href="javascript:void(0)" class="btn btn-alt btn-sm btn-default" data-toggle="tooltip" title="Settings"><i class="fa fa-cog"></i></a>
                                    <div class="btn-group btn-group-sm">
                                        <a href="javascript:void(0)" class="btn btn-alt btn-sm btn-default dropdown-toggle enable-tooltip" data-toggle="dropdown" title="Options"><span class="caret"></span></a>
                                        <ul class="dropdown-menu dropdown-custom dropdown-menu-right">
                                            <li>
                                                <a href="javascript:void(0)"><i class="gi gi-cloud pull-right"></i>Simple Action</a>
                                                <a href="javascript:void(0)"><i class="gi gi-airplane pull-right"></i>Another Action</a>
                                            </li>
                                            <li class="divider"></li>
                                            <li>
                                                <a href="javascript:void(0)"><i class="fa fa-wrench fa-fw pull-right"></i>Separated Action</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <h2>Block</h2>
                            </div>
                            <!-- END Example Title -->

                            <!-- Example Content -->
                            <p>...</p>
                            <!-- END Example Content -->
                        </div>
                        <!-- END Example Block -->
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