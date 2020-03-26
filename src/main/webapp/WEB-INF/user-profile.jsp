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
                                    <i class="gi gi-brush"></i>User Profile<br><small></small>
                                </h1>
                            </div>
                        </div>
                        <ul class="breadcrumb breadcrumb-top">
                            <li>Managers</li>
                            <li><a href="">User Profile</a></li>
                        </ul>
                        <!-- END Page Header -->

                        <!-- Data tables Content -->
                        <div class="block full">
                            <div class="block-title">

                            </div>

                            <br style="line-height:35px;"></br>
                            <div class="table-responsive" style="padding-top: 20px">
                                <table id="userManagerDT" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                                    <thead>
                                        <tr>

                                            <th hidden>#</th>
                                            <th>Name</th>
                                            <th hidden>Username</th>
                                            <th hidden>alias</th>
                                            <th hidden>member id</th>
                                            <th hidden>member code</th>
                                            <th hidden>E-Mail</th>
                                            <th hidden>image address</th>
                                            <th hidden>Level</th>
                                            <th hidden>Department</th>
                                            <th hidden>Status</th>
                                            <th class="text-center">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>                                    
                                    </tbody>
                                </table>
                            </div>


                        </div>
                        <!-- END Data tables Content -->

                    </div>
                    <!-- END Page Content -->
                    <!-- Create Item Modal -->
                    <div class="modal fade" id="create-item" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">


                        <div class="modal-dialog" role="document">
                            <div class="modal-content">


                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                                    <h4 class="modal-title" id="myModalLabel">Add User</h4>
                                </div>

                                <div class="modal-body">
                                    <form data-toggle="validator" action="/user" method="POST">
                                        <div class="form-group">
                                            <label class="control-label" for="title">Username:</label>
                                            <input name="username" class="form-control" data-error="Please enter description." required></input>
                                            <div class="help-block with-errors"></div>
                                        </div>


                                        <div class="form-group">
                                            <label class="control-label" for="title">Alias:</label>
                                            <input name="alias" class="form-control" data-error="Please enter description." required></input>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label" for="title">Member Code:</label>
                                            <input name="membercode" class="form-control" data-error="Please enter description." required></input>
                                            <div class="help-block with-errors"></div>
                                        </div>


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
                                            <label class="control-label" for="title">Image Address:</label>
                                            <input name="imageaddress" class="form-control" data-error="Please enter description." required></input>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label" for="title">Description:</label>
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
                                            <button type="submit" class="btn crud-submit btn-info">Submit</button>
                                        </div>

                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Edit Item Modal -->
                    <div class="modal fade" id="edit-item" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >


                        <div class="modal-dialog" role="document" style="width: 950px">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                                    <h4 class="modal-title" id="myModalLabel">User Profile</h4>
                                </div>
                                <div class="modal-body">

                                    <div class="block" style="width: 900px">
                                        <div class="block-title">
                                            <div class="block-options pull-right">
                                                <a href="javascript:void(0)" class="btn btn-alt btn-sm btn-default" data-toggle="tooltip" title="Friend Request"><i class="fa fa-plus"></i></a>
                                                <a href="javascript:void(0)" class="btn btn-alt btn-sm btn-default" data-toggle="tooltip" title="Hire"><i class="fa fa-briefcase"></i></a>
                                            </div>
                                            <h2>About <strong >User</strong> <small>&bull; <i class="fa fa-file-text text-primary"></i> <a href="javascript:void(0)" data-toggle="tooltip" title="Download Bio in PDF">Bio</a></small></h2>
                                        </div>
                                        <form data-toggle="validator" action="" method="put" class="form-horizontal" role="form">
                                            <table class="table table-borderless table-striped" id="prof">
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                            <label class="control-label " for="title">Member Name</label>
                                                        </td>
                                                        <td>
                                                            <div class="form-group">


                                                                <div class="col-sm-8">
                                                                    <input style="border: none; background-color: #f9f9f9" type="text" name="membername" class="form-control " data-error="Please enter title." readonly ></input> 
                                                                </div>                                                        

                                                                <div class="help-block with-errors"></div>
                                                            </div>
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td>
                                                            <label class="control-label" for="title">Username</label>
                                                        </td>
                                                        <td>
                                                            <div class="form-group">                                                                
                                                                <div class="col-lg-8">
                                                                    <input style="border: none; background-color: white" name="username" class="form-control" data-error="Please enter description." readonly></input> 
                                                                </div>

                                                                <div class="help-block with-errors"></div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <label class="control-label" for="title">Alias</label>
                                                        </td>
                                                        <td>
                                                            <div class="form-group">                                                                
                                                                <div class="col-lg-8">
                                                                    <input style="border: none; background-color: #f9f9f9" name="alias" class="form-control" data-error="Please enter description." readonly></input> 
                                                                </div>

                                                                <div class="help-block with-errors"></div>
                                                            </div>
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td>
                                                            <label class="control-label" for="title">Member ID</label>
                                                        </td>
                                                        <td>
                                                            <div class="form-group">                                                                
                                                                <div class="col-lg-8">
                                                                    <input style="border: none; width: 500px; background-color: white" name="memberid" class="form-control" data-error="Please enter description." readonly></input> 
                                                                </div>

                                                                <div class="help-block with-errors"></div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                     <tr>
                                                        <td>
                                                            <label class="control-label" for="title">Member Code</label>
                                                        </td>
                                                        <td>
                                                            <div class="form-group">                                                                
                                                                <div class="col-lg-8">
                                                                    <input style="border: none; width: 500px; background-color: #f9f9f9" name="membercode" class="form-control" data-error="Please enter description." readonly></input> 
                                                                </div>

                                                                <div class="help-block with-errors"></div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <label class="control-label " for="title">E-mail</label>
                                                        </td>
                                                        <td>
                                                            <div class="form-group">

                                                                <div class="col-sm-8">
                                                                    <input style="border: none; background-color: white" name="email" class="form-control" data-error="Please enter description." readonly></input>
                                                                </div>                                                                
                                                                <div class="help-block with-errors"></div>
                                                            </div>

                                                        </td>
                                                    </tr>
                                                     <tr>
                                                        <td>
                                                            <label class="control-label " for="title">Image Address</label>
                                                        </td>
                                                        <td>
                                                            <div class="form-group">

                                                                <div class="col-sm-8">
                                                                    <input style="border: none; background-color: #f9f9f9" name="imageaddress" class="form-control" data-error="Please enter description." readonly></input>
                                                                </div>                                                                
                                                                <div class="help-block with-errors"></div>
                                                            </div>

                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <label class="control-label " for="title">Level ID</label>
                                                        </td>
                                                        <td>
                                                            <div class="form-group">

                                                                <div class="col-sm-8">
                                                                    <input style="border: none; background-color: white" name="levelid" class="form-control" data-error="Please enter description." readonly></input>
                                                                </div>                                                                
                                                                <div class="help-block with-errors"></div>
                                                            </div>

                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td>
                                                            <label class="control-label " for="title">Department ID</label>
                                                        </td>
                                                        <td>
                                                            <div class="form-group">

                                                                <div class="col-sm-8">
                                                                    <input style="border: none; background-color: #f9f9f9" name="departmentid" class="form-control" data-error="Please enter description." readonly></input>
                                                                </div>                                                                
                                                                <div class="help-block with-errors"></div>
                                                            </div>

                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td>
                                                            <label class="control-label " for="title">Status</label>
                                                        </td>
                                                        <td>
                                                            <div class="form-group">

                                                                <div class="col-sm-8">
                                                                    <input style="border: none; background-color: #2c82ba; width: 90px; text-align: center; color: white" name="status" class="form-control" data-error="Please enter description." readonly ></input>
                                                                </div>                                                                
                                                                <div class="help-block with-errors"></div>
                                                            </div>

                                                        </td>
                                                    </tr>


                                                </tbody>                                                
                                            </table>


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
        <script type="text/javascript" src="js/user-profile.js"></script>
    </body>
</html>