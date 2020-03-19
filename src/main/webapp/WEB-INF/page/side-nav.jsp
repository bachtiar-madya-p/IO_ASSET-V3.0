<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="sidebar">
    <!-- Wrapper for scrolling functionality -->
    <div id="sidebar-scroll">
        <!-- Sidebar Content -->
        <div class="sidebar-content">
            <!-- Brand -->
            <a href="index-admin" class="sidebar-brand">
                <i class="gi gi-flash"></i><span class="sidebar-nav-mini-hide"><strong>IO-</strong>T</span>
            </a>
            <!-- END Brand -->

          
            <!-- Sidebar Navigation -->
            <ul class="sidebar-nav">
                <li>
                    <a href="#"><i class="fa fa-ticket sidebar-nav-icon "></i><span class="sidebar-nav-mini-hide">Ticket</span></a>
                </li>
                <li>
                    <a href="#" class="sidebar-nav-menu"><i class="fa fa-angle-left sidebar-nav-indicator sidebar-nav-mini-hide"></i><i class="hi hi-list-alt sidebar-nav-icon"></i><span class="sidebar-nav-mini-hide">Managers</span></a>
                    <ul>
                        <li>
                            <a href="#" class="sidebar-nav-submenu"><i class="fa fa-angle-left  sidebar-nav-indicator"></i></i> User Managers</a>
                            <ul>
                                <li>
                                    <a href="user-managers">User</a>
                                </li>
                                <li>
                                    <a href="user-upload">Bulk Upload</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="asset-master">Asset Master</a>
                        </li>
                        <li>
                            <a href="asset-register">Asset Register</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="sidebar-nav-menu"><i class="fa fa-angle-left sidebar-nav-indicator sidebar-nav-mini-hide"></i><i class="gi gi-settings sidebar-nav-icon"></i><span class="sidebar-nav-mini-hide">Settings</span></a>
                    <ul>
                        <li>
                            <a href="settings-governance">Governance</a>
                        </li>
                        <li>
                            <a href="#">Application</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <!-- END Sidebar Navigation -->
        </div>
        <!-- END Sidebar Content -->
    </div>
    <!-- END Wrapper for scrolling functionality -->
</div>