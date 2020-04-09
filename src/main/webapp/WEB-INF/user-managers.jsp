
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="en">
    <head>
        <%@include file="page/css_import.jsp"%>
        <meta charset="UTF-8">

        <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.5.2/css/buttons.bootstrap4.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css">
        <link rel="stylesheet" href="css/bootstrap.min-3.6.css">
        <link rel="stylesheet" href="css/plugins-3.8.css">
        <link rel="stylesheet" href="css/main-3.8.css">
        <link rel="stylesheet" href="css/themes-3.1.css">
        <link rel="stylesheet" href="js/vendor/modernizr.min-3.6.js">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css"/>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="https://editor.datatables.net/extensions/Editor/css/editor.dataTables.min.css">
        <link rel="stylesheet" href="https://editor.datatables.net/extensions/Editor/css/editor.dataTables.min.css">



    </head>
    <body id="reload">
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
                            <div class="pull-left">
                                <button type="button" class="btn btn-info" data-toggle="modal" data-target="#create-item">+ Add User</button>
                            </div>
                            <br style="line-height:35px;"></br>
                            <div class="table-responsive" style="padding-top: 20px" >
                                <table id="userManagerDT" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                                    <thead>
                                        <tr>
                                            <th hidden>#</th>
                                            <th>#</th>
                                            <th>Name</th>

                                            <th>E-Mail</th>
                                            <th>Image</th>
                                            <th>Description</th>
                                            <th>Level</th>
                                            <th>Department</th>
                                            <th class="text-center">Status</th>
                                            <th class="text-center">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>                                    
                                    </tbody>
                                </table>
                            </div>


                        </div>
                        <!-- END Datatables Content -->

                    </div>
                    <!-- END Page Content -->
                    <!-- Create Item Modal -->
                    <div class="modal fade" id="create-item" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >


                        <div class="modal-dialog" role="document" >
                            <div class="modal-content">


                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                                    <h4 class="modal-title" id="myModalLabel">Add <strong>User</strong></h4>
                                </div>

                                <div class="modal-body" >
                                    <form data-toggle="validator" action="/user" method="POST" class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-3" for="title" style="text-align: left; padding-left: 30px">Username</label>
                                            <div class="col-sm-7">
                                            <input name="username" class="form-control" data-error="Please enter description." required placeholder="Enter username here"></input>
                                            </div>
                                            <div class="help-block with-errors"></div>
                                        </div>


                                        <div class="form-group">
                                            <label class="control-label col-sm-3" for="title" style="text-align: left; padding-left: 30px">Alias</label>
                                            <div class="col-sm-7">
                                            <input name="alias" class="form-control" data-error="Please enter description." required placeholder="Enter alias"></input>
                                            </div>
                                            <div class="help-block with-errors"></div>
                                        </div>


                                        <div class="form-group">
                                            <label class="control-label col-sm-3" for="title" style="text-align: left; padding-left: 30px">Member Name</label>
                                            <div class="col-sm-7">
                                            <input type="text" name="membername" class="form-control" data-error="Please enter title." required placeholder="Enter your name here"></input>
                                            </div>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-3" for="title" style="text-align: left; padding-left: 30px">E-mail</label>
                                            <div class="col-sm-7">
                                            <input name="email" class="form-control" data-error="Please enter description." required placeholder="example@email.com"></input>
                                            </div>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-3" for="title" style="text-align: left; padding-left: 30px">Image Address</label>
                                            <div class="col-sm-7">
                                            <input name="imageaddress" class="form-control" data-error="Please enter description." required Placeholder="Enter Image Address"></input>
                                            </div>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <div class="form-group" >
                                            <label class="control-label col-sm-3" for="title" style="text-align: left; padding-left: 30px">Description</label>
                                            <div class="col-sm-7">
                                            <input name="description" class="form-control" data-error="Please enter description." required placeholder="Enter Description"></input>
                                            </div>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-3" for="title" style="text-align: left; padding-left: 30px">Level ID</label>
                                            <div class="col-sm-7">
                                            <select name="levelid" class="form-control" data-error="Please enter description." required  type="text" id="LvlID"></select>
                                            </div>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-3" for="title" style="text-align: left; padding-left: 30px">Department ID</label>
                                            <div class="col-sm-7">
                                            <select name="departmentid" class="form-control" data-error="Please enter description." required id="DptID"></select>
                                            </div>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <div class="form-group" style="padding-left: 260px; padding-top: 20px">
                                            <button type="submit" class="btn crud-submit btn-info" style="width: 100px">Submit</button>
                                        </div>

                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Edit Item Modal -->
                    <div class="modal fade" id="edit-item" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">


                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                                    <h4 class="modal-title" id="myModalLabel">Edit Item</h4>
                                </div>
                                <div class="modal-body">
                                    <form data-toggle="validator" action="" method="put">
                                        <div class="form-group">
                                            <label class="control-label" for="title">Member Name:</label>
                                            <input type="text" name="membername" class="form-control" data-error="Please enter title." required />
                                            <div class="help-block with-errors"></div>
                                        </div>


                                        <div class="form-group">
                                            <label class="control-label" for="title">E-mail:</label>
                                            <input name="email" class="form-control" data-error="Please enter description." required></input>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label" for="title">Image Address :</label>
                                            <input name="imageaddress" class="form-control" data-error="Please enter description." required></input>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label" for="title">Description</label>
                                            <input name="description" class="form-control" data-error="Please enter description." required></input>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label" for="title">Level ID:</label>
                                            <input name="levelid" class="form-control" data-error="Please enter description." required></input>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label" for="title">Department ID:</label>
                                            <input name="departmentid" class="form-control" data-error="Please enter description." required></input>
                                            <div class="help-block with-errors"></div>
                                        </div>


                                        <div class="form-group">
                                            <button type="submit" class="btn btn-info crud-submit-edit">Submit</button>
                                        </div>


                                    </form>


                                </div>
                            </div>
                        </div>
                    </div>


                </div>
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

    <script src="assets/js/vendor/jquery.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
    <script src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
    <script src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap4.min.js"></script>

    <script src="js/pages/tablesDatatables.js"></script>
    <script type="text/javascript" src="js/user-manager.js"></script>
</body>
</html>