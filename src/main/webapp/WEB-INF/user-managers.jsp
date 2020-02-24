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

                        <!-- Datatables Content -->
                        <div class="block full">
                            <div class="block-title">
                                
                            </div>

                            <div class="table-responsive">
                                <table id="userManagerDT" class="table table-vcenter table-condensed table-bordered">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th class="text-center">Name</th>
                                            <th>Username</th>
                                            <th>E-Mail</th>
                                            <th>Level</th>
                                            <th>Department</th>
                                            <th>Status</th>
                                            <th class="text-center">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody id="list-list"></tbody>
                                </table>
                            </div>
                        </div>
                        <!-- END Datatables Content -->

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
        <script src="assets/js/pages/tablesDatatables.js"></script>
        <script>$(function () {
                TablesDatatables.init();
            });</script>
        <script type="text/javascript" src="js/user-manager.js"></script>
    </body>
</html>