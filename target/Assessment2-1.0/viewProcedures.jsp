<%-- 
    Document   : index
    Created on : Jan 19, 2024, 3:15:48 AM
    Author     : andre
--%>

<%@page import="java.util.List"%>
<%@page import="com.practicalexercises.assessment2.logic.ProcedureEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="components/0_header.jsp"%>
<%@ include file="components/1_body_sideBar.jsp"%>
<%@ include file="components/2_pageContent.jsp"%>


             
<!-- Begin Page Content -->
<div class="container-fluid">
    
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">View Procedures</h1>
    <p class="mb-4">Here you can view the complete list of procedures</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Procedures</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Requirements</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>Id</th>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Requirements</th>
                            <th>Actions</th>
                        </tr>
                    </tfoot>
                    
                    <% 
                        List <ProcedureEntity> procedures = (List) request.getSession().getAttribute("proceduresList");
                    %>
                    
                    <tbody>
                        <% for( ProcedureEntity procedure : procedures){ %>
                        <tr>
                            <td><%= procedure.getId()%> </td>
                            <td><%= procedure.getTitle()%></td>
                            <td><%= truncateText(procedure.getDescription())%></td>
                            <td><%= truncateText(procedure.getRequirements())%></td>

                            <td class="actions-btns">
                                <% if (citizenUser != null) { %>
                                <!--CITIZEN ACTIONS-->
                                <form name="view" action="SvOneProcedure" style="width: 80%;" method="GET"> 
                                    <button type="submit" class="btn btn-user btn-block btn-secondary">
                                        <i class="fas fa-solid fa-eye" style="color: #ffffff;"></i> View
                                    </button>
                                    <input type="hidden" name="id" value="<%= procedure.getId()%>">
                                </form>
                                <% } else {%>
                                <!--ADMIN ACTIONS-->
                                <form name="delete" action="SvDeleteProcedure" method="POST"> 
                                    <button type="submit" class="btn btn-user btn-block delete-btn">
                                        <i class="fas fa-trash-alt" style="color:white"></i> Delete
                                    </button>
                                    <input type="hidden" name="id" value="<%= procedure.getId()%>">
                                </form>
                                <form name="edit" action="SvEditProcedure" method="GET"> 
                                    <button type="submit" class="btn btn-primary btn-user btn-block">
                                        <i class="fas fa-pencil-alt"></i> Edit
                                    </button>
                                    <input type="hidden" name="id" value="<%= procedure.getId()%>">
                                </form>
                                <form name="view" action="SvOneProcedure" method="GET"> 
                                    <button type="submit" class="btn btn-user btn-block btn-secondary">
                                        <i class="fas fa-solid fa-eye" style="color: #ffffff;"></i> View
                                    </button>
                                    <input type="hidden" name="id" value="<%= procedure.getId()%>">
                                </form>
                                <% }%>
                            </td>
                        </tr>
                                
                                <% }%>
                    </tbody>

                    <%!
                        // Truncate or cut text area until 25 index, and after ... (3 points)
                        public String truncateText(String text) {
                            if (text != null && text.length() > 25) {
                                return text.substring(0, 25) + "...";
                            }
                            return text;
                        }
                    %>

                </table>
            </div>
        </div>
    </div>

</div>
<!-- /.container-fluid -->

<%@ include file="components/3_footer_logout.jsp"%>
<%@ include file="components/4_scripts.jsp"%>