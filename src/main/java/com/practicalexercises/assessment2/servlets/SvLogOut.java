package com.practicalexercises.assessment2.servlets;

import com.practicalexercises.assessment2.logic.Administrator;
import com.practicalexercises.assessment2.logic.Citizen;
import com.practicalexercises.assessment2.logic.Controller;
import com.practicalexercises.assessment2.logic.ProcedureEntity;
import com.practicalexercises.assessment2.logic.Shift;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvLogout", urlPatterns = {"/SvLogout"})
public class SvLogOut extends HttpServlet {

    Controller controller = new Controller();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("in logout");

        HttpSession mySession = request.getSession();
        mySession.removeAttribute("userLoginTrue");
        response.sendRedirect("loginCitizen.jsp");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
