<%-- 
    Document   : vendor
    Created on : Mar 31, 2020, 4:05:05 PM
    Author     : Muhammad Zaahid
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="page/css_import.jsp"%>
        <title>Vendor</title>
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
                                    <i class="gi gi-brush"></i>Vendor<br><small></small>
                                </h1>
                            </div>
                        </div>
                        <ul class="breadcrumb breadcrumb-top">
                            <li>Managers</li>
                            <li><a href="">Vendor</a></li>
                        </ul>
                        <!-- END Page Header -->

                        <!-- Datatables Content -->
                        <div class="block full">
                            <div class="block-title">

                            </div>
                            <div class="pull-left" >
                                <button type="button" class="btn btn-info" data-toggle="modal" data-target="#create-item">+ Add Asset</button>
                            </div>
                            <br></br>

                            <div class="table-responsive" style="padding-top: 20px" >
                                <table id="assetMasterDT" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                                    <thead>
                                        <tr>
                                            <th hidden>#</th>
                                            <th>Vendor Code</th>
                                            <th>Vendor Name</th>
                                            <th>Contact</th>   
                                            <th>E-mail</th>   
                                            <th>Address</th>   
                                            <th>Note</th>   
                                            <th>Rate</th>   
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
                    <div class="modal fade" id="create-item" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">


                        <div class="modal-dialog" role="document">
                            <div class="modal-content">


                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                                    <h4 class="modal-title" id="myModalLabel">Add Asset</h4>
                                </div>

                                <div class="modal-body">
                                    <form data-toggle="validator" action="/asset" method="POST" class="form-horizontal" role="form">



                                        <div class="form-group">
                                            <label class="control-label col-sm-3" for="title">Vendor Code :</label>
                                            <div class="col-sm-8">
                                                <input name="vendorcode" class="form-control" data-error="Please enter description." required></input>
                                            </div>
                                            <div class="help-block with-errors"></div>
                                        </div>


                                        <div class="form-group">
                                            <label class="control-label col-sm-3" for="title">Vendor Name :</label>
                                            <div class="col-sm-8">
                                                <input name="vendorname" class="form-control" data-error="Please enter description." required></input>
                                            </div>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-3" for="title">Contact :</label>
                                            <div class="col-sm-8">
                                                <input name="contact" class="form-control" data-error="Please enter description." required></input>
                                            </div>
                                            <div class="help-block with-errors"></div>
                                        </div>


                                        <div class="form-group">
                                            <label class="control-label col-sm-3" for="title">E-mail :</label>
                                            <div class="col-sm-8">
                                                <input type="email" name="email" class="form-control" data-error="Please enter title." required />
                                            </div>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-3" for="title">Address :</label>
                                            <div class="col-sm-8">
                                                <input name="address" class="form-control" data-error="Please enter description." required></input>
                                            </div>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-3" for="title">Note :</label>
                                            <div class="col-sm-8">
                                                <input name="note" class="form-control" data-error="Please enter description." required></input>
                                            </div>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-3" for="title">Rate :</label>
                                            <div class="col-sm-8">
                                                <input type="number" name="rate" class="form-control" data-error="Please enter description." required onkeypress="javascript:return isNumber(event)" placeholder="Enter number"></input>
                                            </div>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <div class="form-group " style="padding-left: 260px; padding-top: 30px" >
                                            <button type="submit" class="btn crud-submit btn-info">Submit</button>
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
                                    <h4 class="modal-title" id="myModalLabel">Edit Asset</h4>
                                </div>
                                <div class="modal-body">
                                    <form data-toggle="validator" action="" method="put" >
                                        <div class="form-group" hidden>
                                            <label class="control-label col-sm-3" for="title">Asset Code :</label>
                                            <div class="col-sm-8">
                                                <input name="assetcode" class="form-control" data-error="Please enter description." required></input>
                                            </div>
                                            <div class="help-block with-errors"></div>
                                        </div>


                                        <div class="form-group">
                                            <label class="control-label col-sm-3" for="title">Asset Name :</label>
                                            <div class="col-sm-8">
                                                <input name="assetname" class="form-control" data-error="Please enter description." required></input>
                                            </div>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-3" for="title">Type :</label>
                                            <div class="col-sm-8">
                                                <input name="typeid" class="form-control" data-error="Please enter description." required></input>
                                            </div>
                                            <div class="help-block with-errors"></div>
                                        </div>


                                        <div class="form-group">
                                            <label class="control-label col-sm-3" for="title">Manufacture :</label>
                                            <div class="col-sm-8">
                                                <input type="text" name="manufacture" class="form-control" data-error="Please enter title." required />
                                            </div>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-3" for="title">Model :</label>
                                            <div class="col-sm-8">
                                                <input name="model" class="form-control" data-error="Please enter description." required></input>
                                            </div>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-3" for="title">Vendor :</label>
                                            <div class="col-sm-8">
                                                <input name="vendor" class="form-control" data-error="Please enter description." required></input>
                                            </div>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <div class="form-group" style="padding-bottom: 20px">
                                            <label class="control-label col-sm-3" for="title">Note :</label>
                                            <div class="col-sm-8">
                                                <input name="note" class="form-control" data-error="Please enter description." required></input>
                                            </div>
                                            <div class="help-block with-errors"></div>
                                        </div>


                                        <div class="form-group" style="padding-left: 155px; padding-top: 250px" >
                                            <button type="submit" class="btn btn-info crud-submit-edit" >Submit</button>
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
    <script type="text/javascript" src="js/vendor.js"></script>
</body>
</html>
