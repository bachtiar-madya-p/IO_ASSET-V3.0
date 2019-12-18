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
            <div class="right_col" role="main" id="vue">
                <div class="">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <header class="x_title">
                                <div class="row">
                                    <h1>Global Settings</h1>
                                </div>
                            </header>
                            <div class="x_content">
                                <div class="row">
                                    <form class="form-horizontal form-label-left" @submit.prevent="saveSetting()">
                                        <div v-for="setting in settings" class="form-group">
                                            <label class="control-label col-sm-3 col-xs-12">{{settingsMap[setting.name]}}</label>
                                            <div class="col-sm-6 col-xs-12">
                                                <textarea class="form-control" v-model="setting.value" v-if="settingsMap[setting.name] == 'Email Content'"
                                                    style="resize:vertical;height:100px;"></textarea>
                                                <input :type="settingsMap[setting.name] == 'Email Sender' ? 'email' : 'text'" required class="form-control" v-model="setting.value"
                                                    v-else>
                                            </div>
                                        </div>
                                        <div class="ln_solid"></div>
                                        <div class="form-group">
                                            <div class="col-sm-6 col-xs-12 col-sm-offset-3">
                                                <button class="btn btn-primary" type="submit">Save</button>
                                                <button class="btn" type="button" @click="resetSetting()">Reset</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /page content -->
        <!-- footer content -->
        <%@include file="pages/footer.jsp"%>
        <!-- /footer content -->
    </div>
    <!-- javascript imports -->
    <%@include file="pages/js_import.jsp"%>
	<script src="/umx/js/configurationSetting.js"></script>
</body>

</html>
