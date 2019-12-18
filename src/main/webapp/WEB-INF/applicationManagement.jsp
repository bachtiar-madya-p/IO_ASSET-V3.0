<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
            <main class="right_col" role="main" id="vue">
                <div class="x_panel">
                    <header class="x_title">
                        <div class="row">
                            <h2>Application Management</h2>
                        </div>
                    </header>
                    <article class="x_content">
                        <section class="row">
                            <div class="table-responsive">
                                <table id="applicationDT" class="table table-hover" style="width: 100%;">
                                    <thead>
                                        <tr>
                                            <th class="tableheader_id">ID</th>
                                            <th class="tableheader_name">Name</th>
                                            <th class="tableheader_configurationName">Config Name</th>
                                            <th class="tableheader_recipients">Recipients</th>
                                            <th class="tableheader_attributes">Attributes</th>
                                            <th class="tableheader_updateApplication">Update</th>
                                            <th class="tableheader_deleteApplication">Delete</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- populated by js -->
                                    </tbody>
                                </table>
                            </div>
                        </section>
                    </article>
                </div>
                <!-- addApplicationModal -->
                <div class="modal fade" id="addApplicationModal" tabindex="-1" role="dialog" data-keyboard="false"
                    data-backdrop="static" @keyup.esc="cancelAdd()">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-body">
                                <div class="container-fluid">
                                    <div class="x_title">
                                        <button type="button" class="close" aria-label="Close"
                                            onclick="main.cancelAdd()">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                        <h3 class="modal-title">Add Application</h3>
                                    </div>
                                    <div class="x_content">
                                        <form class="form-horizontal" onsubmit="main.add()">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Name</label>
                                                <div class="col-md-8 row">
                                                    <div class="col-md-12">
                                                        <input type="text" class="form-control"
                                                            v-model="newApplicationName" />
                                                    </div>
                                                    <span class="col-md-12">
                                                        The application name should be the same as what is configured in the IDG universe.
                                                    </span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Configuration Name</label>
                                                <div class="col-md-8 row">
                                                    <div class="col-md-12">
                                                        <input type="text" class="form-control"
                                                            v-model="newApplicationConfigName" />
                                                    </div>
                                                    <span class="col-md-12">
                                                        e.g. S1Master, DatabaseMaster
                                                    </span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">
                                                    Application Owner Emails
                                                </label>
                                                <div class="col-md-8 row">
                                                    <div class="col-md-12">
                                                        <vue-tags-input placeholder="" v-model="newApplicationEmail"
                                                            :tags="newApplicationEmails"
                                                            :validation="emailValidation"
                                                            @tags-changed="email => newApplicationEmails = email"
                                                            :allow-edit-tags="true" />
                                                    </div>
                                                    <span class="col-md-12">
                                                        e.g. owner1@kbtg.tech, owner2@kbtg.tech
                                                    </span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Application Attributes</label>
                                                <div class="col-md-8 row">
                                                    <div class="col-md-12">
                                                        <vue-tags-input placeholder="" v-model="newApplicationAttribute"
                                                            :tags="newApplicationAttributes"
                                                            @tags-changed="attr => newApplicationAttributes = attr"
                                                            :allow-edit-tags="true" />
                                                    </div>
                                                    <span class="col-md-12">
                                                        e.g. Department, Job Code
                                                    </span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Email Subject</label>
                                                <div class="col-md-8 row">
                                                    <div class="col-md-12">
                                                        <input type="text" class="form-control" id="newMailSubject" v-model="newMailSubject"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Email Content</label>
                                                <div class="col-md-8 row">
                                                    <div class="col-md-12">
                                                        <textarea class="form-control" id="newMailBody" v-model="newMailBody"></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="ln_solid"></div>
                                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3 button-align">
                                                <button type="button" class="btn btn-sm btn-primary iconed-button"
                                                    onclick="main.add()">
                                                    <i class="fa fa-fw fa-plus"></i>
                                                    <span>Add</span>
                                                </button>
                                                <button type="button" class="btn btn-sm btn-default iconed-button"
                                                    onclick="main.cancelAdd()">
                                                    <span>Cancel</span>
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /addApplicationModal -->
                <!-- removeApplicationModal -->
                <div class="modal fade" id="removeApplicationModal" tabindex="-1" role="dialog" data-keyboard="false"
                    data-backdrop="static" @keyup.esc="cancelRemove()">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-body">
                                <div class="container-fluid">
                                    <div class="x_title">
                                        <button type="button" class="close" aria-label="Close"
                                            onclick="main.cancelRemove()">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                        <h3 class="modal-title">Remove Application</h3>
                                    </div>
                                    <div class="x_content">
                                        <form class="form-horizontal" onsubmit="main.remove()">
                                            <div class="form-group text-danger remove-app-padding">
                                                <h5>
                                                    Are you sure you want to remove application
                                                    <strong>{{applicationName}}</strong> ?
                                                </h5>
                                                <h5>This operation cannot be undone!</h5>
                                            </div>
                                            <div class="ln_solid"></div>
                                            <div class="col-xs-12 text-right">
                                                <button type="button" class="btn btn-sm btn-danger iconed-button"
                                                    onclick="main.remove()">
                                                    <i class="fa fa-fw fa-trash"></i>
                                                    <span>Remove</span>
                                                </button>
                                                <button type="button" class="btn btn-sm btn-default iconed-button"
                                                    onclick="main.cancelRemove()">
                                                    <span>Cancel</span>
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /removeApplicationModal -->
                <!-- updateApplicationModal -->
                <div class="modal fade" id="updateApplicationModal" tabindex="-1" role="dialog" data-keyboard="false"
                    data-backdrop="static" @keyup.esc="cancelUpdate()">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-body">
                                <div class="container-fluid">
                                    <div class="x_title">
                                        <button type="button" class="close" aria-label="Close"
                                            onclick="main.cancelUpdate()">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                        <h3 class="modal-title">
                                            Update Application {{applicationName}}
                                        </h3>
                                    </div>
                                    <div class="x_content">
                                        <form class="form-horizontal" onsubmit="main.updateApplication()">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Name</label>
                                                <div class="col-md-8 row">
                                                    <div class="col-md-12">
                                                        <input type="text" class="form-control"
                                                            v-model="applicationName" disabled />
                                                    </div>
                                                    <span class="col-md-12">
                                                        The application name should be the same as what is configured in the IDG universe.
                                                    </span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Configuration Name</label>
                                                <div class="col-md-8 row">
                                                    <div class="col-md-12">
                                                        <input type="text" class="form-control"
                                                            v-model="configurationName" disabled />
                                                    </div>
                                                    <span class="col-md-12">
                                                        e.g. S1Master, DatabaseMaster
                                                    </span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">
                                                    Application Owner Emails
                                                </label>
                                                <div class="col-md-8 row">
                                                    <div class="col-md-12">
                                                        <vue-tags-input placeholder="" v-model="recipients"
                                                            :tags="recipientss"
                                                            :validation="emailValidation"
                                                            @tags-changed="recipient => recipientss = recipient"
                                                            :allow-edit-tags="true" />
                                                    </div>
                                                    <span class="col-md-12">
                                                        e.g. owner1@kbtg.tech, owner2@kbtg.tech
                                                    </span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Application Attributes</label>
                                                <div class="col-md-8 row">
                                                    <div class="col-md-12">
                                                        <vue-tags-input placeholder="" v-model="attributes"
                                                            :tags="attributess"
                                                            @tags-changed="attr => attributess = attr"
                                                            :allow-edit-tags="true" />
                                                    </div>
                                                    <span class="col-md-12">
                                                        e.g. Department, Job Code
                                                    </span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Email Subject</label>
                                                <div class="col-md-8 row">
                                                    <div class="col-md-12">
                                                        <input type="text" class="form-control" v-model="mailSubject" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Email Content</label>
                                                <div class="col-md-8 row">
                                                    <div class="col-md-12">
                                                        <textarea class="form-control" v-model="mailBody" rows="5"></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="ln_solid"></div>
                                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3 button-align">
                                                <button type="button" class="btn btn-sm btn-info iconed-button"
                                                    onclick="main.updateApplication()">
                                                    <i class="fa fa-fw fa-pencil"></i>
                                                    <span>Update</span>
                                                </button>
                                                <button type="button" class="btn btn-sm btn-default iconed-button"
                                                    onclick="main.cancelUpdate()">
                                                    <span>Cancel</span>
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /updateApplicationModal -->
            </main>
        </div>
        <!-- /page content -->
        <!-- footer content -->
        <%@include file="pages/footer.jsp"%>
        <!-- /footer content -->
    </div>
    <!-- javascript imports -->
    <%@include file="pages/js_import.jsp"%>
    <script src="/umx/lib/vue-tags-input/js/vue-tags-input.js"></script>
    <script src="js/applicationManagement.js"></script>
</body>

</html>