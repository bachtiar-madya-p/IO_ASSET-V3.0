 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="en">
    <head>
        <%@include file="page/css_import.jsp"%>
        <meta charset="UTF-8">
        <title>IO-T - Dashboard</title>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.5.2/css/buttons.bootstrap4.min.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        
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
                            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal">Add User</button>
                            <input type="search" class="form-control" placeholder="Search" aria-controls="example-datatable">
                            <span class="input-group-addon"><i class="fa fa-search"></i></span>
                         <div class="col-sm-6 col-xs-5"><div class="dataTables_length" id="example-datatable_length"><label><select name="example-datatable_length" aria-controls="example-datatable" class="form-control"><option value="10">10</option><option value="20">20</option><option value="30">30</option><option value="-1">All</option></select></label></div></div>
                            <br></br>
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          
          <h1
              


<p>Add User</p>

          </h1>
        </div>
        <div class="modal-body">
          <form action="add_user.html" method="post">
<table border="0" align="center">
<tbody>

<tr>
<td><label for="username">User Name: </label></td>
<td><input id="id" maxlength="50" name="username" type="text" /></td>
</tr>

<tr>
<td><label for="FullName">Full Name: </label></td>
<td><input id="Fullname" maxlength="50" Fullname="FullName" type="text" /></td>
</tr>

<tr>
<td><label for="Alternatename">Alternate Name: </label></td>
<td><input id="Alternatename" maxlength="50" Alternatename="Alternatename" type="text" /></td>
</tr>

<tr>
<td><label for="email">Email: </label></td>
<td><input id="email" maxlength="50" email="email" type="text" /></td>
</tr>

<tr>
<td><label for="department">Department: </label></td>
<td><input id="department" maxlength="50" department="department" type="text" /></td>
</tr>

<tr>
<td><label for="manager">Manager:</label></td>
<td><input id="manager" maxlength="50" manager="manager" type="text" /></td>
</tr>







<tr>
<td 
   
    align="left"><input name="Submit" type="Submit" value="Add" />
    
    

</td>


</tr>

</tbody>
</table>
</form>
        </div>
        <div class="modal-footer">
          <button type="button" class="close" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>

                               <table id="userManagerDT" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                                        <thead>
                                          
                                        <tr>
                                             
                                            <th>User ID</th>
                                            <th>Full Name</th>
                                            <th>Alternative Name</th>
                                            <th>E-Mail</th>
                                            
                                            <th>Department</th>
                                            <th>Status</th>
                                             <th>Action</th>
                                            <th class="text-center">Actions</th>
                                        </tr>
                                        </thead>
                            <tbody>
                            <tr>
                                <td></td>
                                <td>Bachtiar MP</td>
                                <td>bmp</td>
                                <td>bmp@ic.sg</td>
                                <td>068f2565-a0fe-528f-80b3-783d9f40cc3e</td>
                                <td>3f46b266-d159-5e69-8473-a50ddc65f6b7</td>
                                <td>false</td>  
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>Bachtiar MP</td>
                                <td>asdf</td>
                                <td>bmp@ic.sg</td>
                                <td>068f2565-a0fe-528f-80b3-783d9f40cc3e</td>
                                <td>3f46b266-d159-5e69-8473-a50ddc65f6b7</td>
                                <td>false</td>  
                                <td></td>
                            </tr>  
                            <tr>
                                <td></td>
                                <td>Bachtiar Madya Permadi</td>
                                <td>permadi</td>
                                <td>bachtiar.madya.p@gmail.com</td>
                                <td>fcc139c3-2b83-5141-8cfb-3fe6f02d8c16</td>
                                <td>fcc139c3-2b83-5141-8cfb-3fe6f02d8c16</td>
                                <td>true</td> 
                                <td></td>
                            </tr>  
                            <tr>
                                <td></td>
                                <td>User Testing 1</td>
                                <td>user</td>
                                <td>permadi.works@gmail.com</td>
                                <td>068f2565-2b83-5141-8cfb-3fe6f02d8c16</td>
                                <td>3f46b266-d159-5e69-8473-a50ddc65f6b7</td>
                                <td>false</td>  
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>John Smith</td>
                                <td>jsmith</td>
                                <td>bmp@ic.sg</td>
                                <td>fcc139c3-2b83-5141-8cfb-3fe6f02d8c16</td>
                                <td>fcc139c3-2b83-5141-8cfb-3fe6f02d8c16</td>
                                <td>true</td> 
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>Bachtiar MP</td>
                                <td>bpermadi</td>
                                <td>permadi.works@gmail.com</td>
                                <td>068f2565-a0fe-528f-80b3-783d9f40cc3e</td>
                                <td>3f46b266-d159-5e69-8473-a50ddc65f6b7</td>
                                <td>false</td>   
                                <td></td>
                                
                            </tr>
                            </tbody>
                            </table>
                            
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
        
            <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.bootstrap4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.html5.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.print.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.colVis.min.js"></script>
    <script src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
    <script src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap4.min.js"></script>
    
    
 
    <script type="text/javascript" src="js/user-manager.js"></script>
    <script type="text/javascript" src="js/adduser.js"></script>
    </body>
</html>