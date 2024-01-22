<%-- 
    Document   : pageWrapper_sideBar_1
    Created on : Jan 21, 2024, 4:25:49 AM
    Author     : andre
--%>

<%@page import="com.practicalexercises.assessment2.logic.Administrator"%>
<%@page import="com.practicalexercises.assessment2.logic.Citizen"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<body id="page-top">
    
    <!--Validation session-->
    
    <% HttpSession mySession = request.getSession();
        Object user = (Object) request.getSession().getAttribute("userLoginTrue");

        if (user == null) {
            response.sendRedirect("signinCitizen.jsp");
        }
        
        Administrator adminUser = null;
        Citizen citizenUser = null;
        String username = null;
        if (user instanceof Administrator) {
            adminUser = (Administrator) request.getSession().getAttribute("userLoginTrue");
            username = adminUser.getUsername();
        } else if (user instanceof Citizen) {
            citizenUser = (Citizen) request.getSession().getAttribute("userLoginTrue");
            username = citizenUser.getUsername();
        }
    %>

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="SvHome">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-solid fa-envelope"></i>
                </div>
                <div class="sidebar-brand-text mx-3">Electronic Office<sup>Shifts</sup></div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
               Management
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-regular fa-folder-open"  style="color: #839DE9;"></i>
                    <span>Procedures</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">ACTIONS:</h6>
                        <% if(citizenUser != null) { %>
                        <!--CITIZEN ACTIONS-->
                        <a class="collapse-item" href="SvProcedure">See all</a>
                        <% } else { %>
                        <!--ADMIN ACTIONS-->
                        <a class="collapse-item" href="SvProcedure">See all</a>
                        <a class="collapse-item" href="addProcedure.jsp">Create</a>
                        <% } %>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                    aria-expanded="true" aria-controls="collapseUtilities">
<!--                    <i class="fas fa-solid fa-envelope" style="color: #ffffff;"></i>-->
                    <i class="fas fa-solid fa-envelope" style="color: #839DE9;"></i>
                    <span>Shifts</span>
                </a>
                <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">ACTIONS:</h6>
                        <% if(citizenUser != null) { %>
                        <!--CITIZEN ACTIONS-->
                        <a class="collapse-item" href="SvProceduresShift">Create Shift</a>
                        <a class="collapse-item" href="SvShiftAll">See all</a>
                        <a class="collapse-item" href="SvUserShifts">My shifts</a>
                        <% } else { %>
                        <!--ADMIN ACTIONS-->
                        <a class="collapse-item" href="SvShiftAll">See all</a>
                        <% } %>
                    </div>
                </div>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">


        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    
                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%= username%></span>
                                <img class="img-profile rounded-circle"
                                     src="img/undraw_profile.svg">
                            </a>
    <!-- Dropdown - User Information -->
<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" id="userDropdown" aria-labelledby="userDropdown">
    <a class="dropdown-item" onclick="toggleDropdownAndShowModal()">
        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
        Logout
    </a>
</div>

<div class="modal fade" id="logoutModalId" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="SvLogout">Logout</a>
            </div>
        </div>
    </div>
</div>
    
    
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->
