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
                        <!-- Dashboard 2 Header -->
                        <div class="content-header">
                            <ul class="nav-horizontal text-center">
                                <li>
                                    <a href="user-managers"><i class="fa fa-user"></i>User Managers</a>
                                </li>
                                <li>
                                    <a href="settings-governance"><i class="fa fa-cogs"></i> Settings</a>
                                </li>
                                <li>
                                    <a href="asset-master"><i class="fa fa-cubes"></i> Asset Master</a>
                                </li>
                                 <li>
                                    <a href="asset-register"><i class="gi gi-registration_mark"></i> Asset Register</a>
                                </li>
                                <li>
                                    <a href="building"><i class="fa fa-building"></i> Building</a>
                                </li>
                            </ul>
                        </div>
                        <!-- END Dashboard 2 Header -->
                        
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