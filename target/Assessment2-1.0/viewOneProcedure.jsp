<%-- 
    Document   : index
    Created on : Jan 19, 2024, 3:15:48 AM
    Author     : andre
--%>

<%@page import="com.practicalexercises.assessment2.logic.ProcedureEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="components/0_header.jsp"%>
<%@ include file="components/1_body_sideBar.jsp"%>
<%@ include file="components/2_pageContent.jsp"%>

<% 
                       ProcedureEntity procedure = (ProcedureEntity) request.getSession().getAttribute("idProcedure");
                    %>

<!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-primary shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-s font-weight-bold text-primary text-uppercase mb-1">
                                                <%= procedure.getTitle() %></div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

<!-- Collapsable Card Example -->
<div class="card shadow mb-4">
    <!-- Card Header - Accordion -->
    <a href="#collapseDescription" class="d-block card-header py-3" data-toggle="collapse"
       role="button" aria-expanded="true" aria-controls="collapseDescription">
        <h6 class="m-0 font-weight-bold text-primary" style="padding-bottom: 2%;">Description</h6>
    </a>
    <!-- Card Content - Collapse -->
    <div class="collapse show" id="collapseDescription">
        <div class="card-body">
            <textarea class="custom-textarea" readonly><%= procedure.getDescription() %></textarea>
        </div>
    </div>
</div>
        
<!-- Collapsable Card Example -->
<div class="card shadow mb-4">
    <!-- Card Header - Accordion -->
    <a href="#collapseRequirements" class="d-block card-header py-3" data-toggle="collapse"
       role="button" aria-expanded="true" aria-controls="collapseRequirements">
        <h6 class="m-0 font-weight-bold text-primary" style="padding-bottom: 2%;">Requirements</h6>
    </a>
    <!-- Card Content - Collapse -->
    <div class="collapse show" id="collapseRequirements">
        <div class="card-body">
            <textarea class="custom-textarea" readonly><%= procedure.getRequirements() %></textarea>
        </div>
    </div>
</div>

<%@ include file="components/3_footer_logout.jsp"%>
<%@ include file="components/4_scripts.jsp"%>