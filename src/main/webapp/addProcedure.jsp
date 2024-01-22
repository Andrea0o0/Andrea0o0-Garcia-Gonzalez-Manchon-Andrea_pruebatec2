<%-- 
    Document   : index
    Created on : Jan 19, 2024, 3:15:48 AM
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="components/0_header.jsp"%>
<%@ include file="components/1_body_sideBar.jsp"%>
<%--<%@ include file="components/2_body_contentWrapper.jsp"%>--%>
<%@ include file="components/2_pageContent.jsp"%>

<div class="form-container">
    <form id="myForm" class="user" action="SvProcedure" method="POST">
        <h2 class="center">Add procedure</h2>
        <h3 class="center">This form is only for user admins!</h3>
        
        <!-- Title -->
        <div class="form-group">
            <input type="text" class="form-control form-control-user" id="title" name="title" placeholder="Title" required>
        </div>

        <!-- Description -->
        <div class="form-group">
            <textarea class="form-control form-control-user" id="description" name="description" placeholder="Description" required></textarea>
        </div>

        <!-- Requirements -->
        <div class="form-group">
            <textarea class="form-control form-control-user" id="requirements" name="requirements" placeholder="Requirements" required></textarea>
        </div>

        <!-- Submit button -->
        <button  type="submit" class="btn btn-primary btn-user btn-block" onclick="submitForm()" >
            Add Procedure
        </button>

        <hr>
    </form>
</div>

<%@ include file="components/3_footer_logout.jsp"%>
<%@ include file="components/4_scripts.jsp"%>
