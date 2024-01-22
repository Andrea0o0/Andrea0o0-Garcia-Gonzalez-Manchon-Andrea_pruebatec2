<%-- 
    Document   : index
    Created on : Jan 19, 2024, 3:15:48 AM
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="components/0_header.jsp"%>
<%@ include file="components/1_body_sideBar.jsp"%>
<%@ include file="components/2_pageContent.jsp"%>

<%
    try {
        response.sendRedirect("SvShiftAll");
    } catch (Exception e) {
        e.printStackTrace();
        out.println("Error redirecting to SvShiftAll: " + e.getMessage());
    }
%>

<%@ include file="components/3_footer_logout.jsp"%>
<%@ include file="components/4_scripts.jsp"%>
