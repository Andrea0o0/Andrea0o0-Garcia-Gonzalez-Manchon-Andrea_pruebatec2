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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvProcedure", urlPatterns = {"/SvProcedure"})
public class SvProcedure extends HttpServlet {

    Controller controller = new Controller();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       List<ProcedureEntity> procedures =  controller.bringProcedures();
       
       HttpSession mySession = request.getSession();
       mySession.setAttribute("proceduresList", procedures);
       response.sendRedirect("viewProcedures.jsp");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
          String title = request.getParameter("title");
          String description = request.getParameter("description");
          String requirements = request.getParameter("requirements");
        System.out.println(requirements);
        List<Shift> shifts = new ArrayList<>();
        Administrator admin = (Administrator) request.getSession().getAttribute("userLoginTrue");
        System.out.println(admin);
        controller.createProcedure(title, description, requirements, admin, shifts);
        response.sendRedirect("SvProcedure");
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
