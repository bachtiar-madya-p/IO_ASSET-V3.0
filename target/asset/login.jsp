<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html class="no-js" lang="en"> 
    <head>
        <meta charset="utf-8">

        <title>IO-T - Governor</title>            

        <meta name="description" content="ProUI is a Responsive Bootstrap Admin Template created by pixelcave and published on Themeforest.">
        <meta name="author" content="pixelcave">
        <meta name="robots" content="noindex, nofollow">
        <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=0">

        <!-- Icons -->
        <!-- The following icons can be replaced with your own, they are used by desktop and mobile browsers -->
        <link rel="shortcut icon" href="assets/img/favicon.png">
        <link rel="apple-touch-icon" href="assets/img/icon57.png" sizes="57x57">
        <link rel="apple-touch-icon" href="assets/img/icon72.png" sizes="72x72">
        <link rel="apple-touch-icon" href="assets/img/icon76.png" sizes="76x76">
        <link rel="apple-touch-icon" href="assets/img/icon114.png" sizes="114x114">
        <link rel="apple-touch-icon" href="assets/img/icon120.png" sizes="120x120">
        <link rel="apple-touch-icon" href="assets/img/icon144.png" sizes="144x144">
        <link rel="apple-touch-icon" href="assets/img/icon152.png" sizes="152x152">
        <link rel="apple-touch-icon" href="assets/img/icon180.png" sizes="180x180">
        <!-- END Icons -->

        <!-- Stylesheets -->
        <!-- Bootstrap is included in its original form, unaltered -->
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">

        <!-- Related styles of various icon packs and plugins -->
        <link rel="stylesheet" href="assets/css/plugins.css">

        <!-- The main stylesheet of this template. All Bootstrap overwrites are defined in here -->
        <link rel="stylesheet" href="assets/css/main.css">

        <!-- Include a specific file here from css/themes/ folder to alter the default theme of the template -->

        <!-- The themes stylesheet of this template (for using specific theme color in individual elements - must included last) -->
        <link rel="stylesheet" href="assets/css/themes.css">
        <!-- END Stylesheets -->

        <!-- Modernizr (browser feature detection library) -->
        <script src="assets/js/vendor/modernizr.min.js"></script>
    </head>
    <body>

        <!-- Login Full Background -->
        <!-- For best results use an image with a resolution of 1280x1280 pixels (prefer a blurred image for smaller file size) -->
        <img src="assets/img/placeholders/backgrounds/login_full_bg.jpg" alt="Login Full Background" class="full-bg animation-pulseSlow">
        <!-- END Login Full Background -->

        <!-- Login Container -->
        <div id="login-container" class="animation-fadeIn">
            <!-- Login Title -->
            <div class="login-title text-center">
                <h1><i class="fa fa-xing"></i> <strong>IO-T</strong><br><small>Please <strong>Login</strong></small></h1>
            </div>
            <!-- END Login Title -->

            <!-- Login Block -->
            <div class="block push-bit">
                <!-- Login Form -->
                <form id="login-form" class="form-horizontal form-bordered form-control-borderless login-form">
                    <div class="form-group">
                        <div class="col-xs-12">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <input type="text" id="username" name="username" class="form-control input-lg" placeholder="Username">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="gi gi-asterisk"></i></span>
                                <input type="password" id="password" name="password" class="form-control input-lg" placeholder="Password">
                            </div>
                        </div>
                    </div>
                    <div class="form-group form-actions">
                        <div class="col-xs-4">
                            <label class="switch switch-primary" data-toggle="tooltip" title="Remember Me?">
                                <input type="checkbox" id="login-remember-me" name="login-remember-me" checked>
                                <span></span>
                            </label>
                        </div>
                        <div class="col-xs-8 text-right">
                            <button type="submit" class="btn btn-sm btn-primary" onclick="login();"><i class="fa fa-angle-right"></i> Login to Dashboard</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12 text-center">
                            <a href="javascript:void(0)" id="link-reminder-login"><small>Forgot password?</small></a>
                        </div>
                    </div>
                </form>
                <!-- END Login Form -->

                <!-- Reminder Form -->
                <form action="login_full.html#reminder" method="post" id="form-reminder" class="form-horizontal form-bordered form-control-borderless display-none">
                    <div class="form-group">
                        <div class="col-xs-12">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="gi gi-envelope"></i></span>
                                <input type="text" id="reminder-email" name="reminder-email" class="form-control input-lg" placeholder="Email">
                            </div>
                        </div>
                    </div>
                    <div class="form-group form-actions">
                        <div class="col-xs-12 text-right">
                            <button type="submit" class="btn btn-sm btn-primary"><i class="fa fa-angle-right"></i> Reset Password</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12 text-center">
                            <small>Did you remember your password?</small> <a href="javascript:void(0)" id="link-reminder"><small>Login</small></a>
                        </div>
                    </div>
                </form>
                <!-- END Reminder Form -->
            </div>
            <!-- END Login Block -->
        </div>
        <!-- END Login Container -->

        <!-- jQuery, Bootstrap.js, jQuery plugins and Custom JS code -->
        <script src="assets/js/vendor/jquery.min.js"></script>
        <script src="assets/js/vendor/bootstrap.min.js"></script>
        <script src="assets/js/plugins.js"></script>
        <script src="assets/js/app.js"></script>

        <script type="text/javascript" src="js/login.js"></script>
    </body>
</html>
