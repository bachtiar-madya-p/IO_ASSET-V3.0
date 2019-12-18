<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
                            <h2>Manage Rule</h2>
                        </div>
                        <div class="row iconed-button">
                            <h1>
                                <select data-toggle="dropdown" id="application-selection" name="applicationSelection"
                                    aria-expanded="true" v-model="applicationId" v-on:load="applicationChanged"
                                    @change="applicationChanged">
                                    <option value="">Select an application</option>
                                    <option v-for="item in applications" :key="item.id" :value="item.id">
                                        {{item.name}}
                                    </option>
                                </select>
                            </h1>
                        </div>
                    </header>
                    <article class="x_content">
                        <section class="row">
                            <div class="table-responsive">
                                <table id="ruleDT" class="table table-hover" style="width: 100%;"></table>
                            </div>
                        </section>
                    </article>
                </div>
                <!-- addRuleModal -->
                <div class="modal fade" id="addRuleModal" tabindex="-1" role="dialog" data-keyboard="false"
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
                                        <h3 class="modal-title">Add Rule</h3>
                                    </div>
                                    <div class="x_content">
                                        <form class="form-horizontal" @keyup.enter="add()">
                                            <div class="form-group" v-for="field in fields">
                                                <label class="control-label col-md-3" :for="field">{{field}}</label>
                                                <div class="col-md-6">
                                                    <input type="text" class="form-control col-md-7 col-xs-12"
                                                        :key="'input-' + field" :id="field" v-model="newRule[field]" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Role Name</label>
                                                <div class="col-md-6">
                                                    <input type="text" class="form-control col-md-7 col-xs-12"
                                                        v-model="newRule['Role Name']" />
                                                </div>
                                            </div>
                                            <div class="ln_solid"></div>
                                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3 button-align">
                                                <button type="button" class="btn btn-sm btn-primary iconed-button"
                                                    onclick="main.add()">
                                                    <i class="fa fa-fw fa-plus"></i>
                                                    <span>Add</span>
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /addRuleModal -->
                <!-- importModal -->
                <div class="modal fade" id="importModal" tabindex="-1" role="dialog" data-keyboard="false"
                    data-backdrop="static" @keyup.esc="cancelImport()">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-body">
                                <div class="container-fluid">
                                    <div class="x_title">
                                        <button type="button" class="close" aria-label="Close"
                                            onclick="main.cancelImport()">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                        <h3 class="modal-title">Import Rule</h3>
                                    </div>
                                    <div class="x_content">
                                        <div class="row">
                                            <p>
                                                <span class="badge badge-danger col-xs-12">
                                                    Note : Rules to be imported will replace the existing list.
                                                </span>
                                            </p>
                                            <div class="col-xs-12">
                                                <h5>You must include the following:</h5>
                                                <ul>
                                                    <li>
                                                        <p>
                                                            <h5>Column names:</h5>
                                                            <span>
                                                                "<em>Department</em>",
                                                                "<em>Job Code</em>",
                                                                "<em>Role Name</em>"
                                                            </span>
                                                        </p>
                                                    </li>
                                                    <li>
                                                        <p>
                                                            <h5>Available user attributes are:</h5>
                                                            <span> {{String(fields)}} </span>
                                                        </p>
                                                    </li>
                                                </ul>
                                                <p>
                                                    <br />
                                                    <h5>For example:</h5>
                                                    <div class="well">
                                                        <p>
                                                            "Department","Job Code","Role Name"<br />
                                                            Sales,Manager,Branch<br /> Marketing,Manager,BranchVoid
                                                        </p>
                                                    </div>
                                                    <em>
                                                        Tip : To retain the existing list, export the current list, append to the list and import.
                                                    </em>
                                                    <textarea v-model="data" class="form-control"
                                                        style="resize: vertical;" @keyup.ctrl.enter="doImport()">
                                                    </textarea>
                                                    <div class="ln_solid"></div>
                                                    <button class="btn btn-sm btn-primary iconed-button" type="button"
                                                        onclick="main.doImport()">
                                                        <i class="fa fa-fw fa-upload"></i>
                                                        <span>Import</span>
                                                    </button>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /importModal -->
            </main>
            <!-- /page content -->
        </div>
        <!-- footer content -->
        <%@include file="pages/footer.jsp"%>
        <!-- /footer content -->
    </div>
    <!-- javascript imports -->
    <%@include file="pages/js_import.jsp"%>
    <script src="/umx/js/ruleManagement.js"></script>
</body>

</html>