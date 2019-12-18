<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- side nav -->
<div class="col-md-3 left_col menu_fixed">
    <div class="left_col scroll-view ">
        <!-- site title -->
        <div class="navbar nav_title clearfix">
            <a onclick="index" class="site_title">
                <i class="fa fa-cube"></i>
                <span style="font-family: 'Segoe UI', sans-serif;">UMX</span>
            </a>
        </div>
        <!-- /site title -->
        <div class="clearfix"></div>
        <br />
        <!-- sidebar menu -->
        <div id="sidebar-menu" class="main_menu_side main_menu">
            <div class="menu_section">
                <ul class="nav side-menu">
                    <li>
                        <a href="/umx/applications">
                            <i class="fa fa-fw fa-flag"></i>
                            Applications
                        </a>
                    </li>

                    <li>
                        <a href="/umx/rules">
                            <i class="fa fa-fw fa-book"></i>
                            Rules
                        </a>
                    </li>

                    <li>
                        <a href="/umx/whitelists">
                            <i class="fa fa-fw fa-check-square"></i>
                            Whitelists
                        </a>
                    </li>

                    <li>
                        <a href="/umx/executions">
                            <i class="fa fa-fw fa-play-circle"></i>
                            Execution History
                        </a>
                    </li>

                    <li>
                        <a href="/umx/configurations/global">
                            <i class="fa fa-fw fa-cogs"></i>
                            Global Settings
                        </a>
                    </li>
                    <li>
                        <a href="/umx/system/authentication/logout">
                            <i class="fa fa-fw fa-sign-out"></i>
                            Log Out
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- /sidebar menu -->
    </div>
</div>
<!-- /side nav -->