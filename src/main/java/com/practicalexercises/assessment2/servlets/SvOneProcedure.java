package com.practicalexercises.assessment2.servlets;

import com.practicalexercises.assessment2.logic.Administrator;
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


@WebServlet(name = "SvOneProcedure", urlPatterns = {"/SvOneProcedure"})
public class SvOneProcedure extends HttpServlet {

    Controller controller = new Controller();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
       ProcedureEntity procedureId = controller.searchProcedure((long) id);
       HttpSession mySession = request.getSession();
        System.out.println(procedureId.getDescription());
       mySession.setAttribute("idProcedure", procedureId);
       response.sendRedirect("viewOneProcedure.jsp");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
          String title = request.getParameter("title");
          String description = request.getParameter("description");
          String requirements = request.getParameter("requirements");

        System.out.println(title + " " + description + " " + requirements);
        List<Shift> shifts = new ArrayList<>();
        Administrator admin = controller.searchAdmin((long) 1);
        controller.createProcedure(title, description, requirements, admin, shifts);
        response.sendRedirect("SvProcedure");
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
