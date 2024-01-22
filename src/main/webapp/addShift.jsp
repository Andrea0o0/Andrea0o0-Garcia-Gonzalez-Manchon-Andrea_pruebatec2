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

<div class="form-container">
    <form id="myForm" class="user" action="SvShift" method="POST">
        <h2 class="center">Add Shift</h2>
        
        <% 
                        List <ProcedureEntity> procedures = (List) request.getSession().getAttribute("proceduresShiftList");
                    %>
        
        <!-- Additional Information -->
        <div class="form-group">
            <input type="text" class="form-control form-control-user" id="additionalInfo" name="additionalInfo" placeholder="Additional Information">
        </div>

        <!-- Procedures -->
        <div class="form-group">
            <label for="procedures">Select Procedure:</label>
            <select id="procedures" name="procedures" class="form-control" size="5" required>
                <% for (ProcedureEntity procedure : procedures) {%>
                <option value="<%= procedure.getId() %>" ><%= procedure.getTitle().toUpperCase() %></option>
                <% } %>
            </select>
        </div>

        <!-- Submit button -->
        <button type="submit" class="btn btn-primary btn-user btn-block">
            Create Shift
        </button>

        <hr>
    </form>
</div>

<%@ include file="components/3_footer_logout.jsp"%>
<%@ include file="components/4_scripts.jsp"%>