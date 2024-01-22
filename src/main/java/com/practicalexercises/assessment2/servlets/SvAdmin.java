package com.practicalexercises.assessment2.servlets;

import com.practicalexercises.assessment2.logic.Administrator;
import com.practicalexercises.assessment2.logic.Citizen;
import com.practicalexercises.assessment2.logic.Controller;
import com.practicalexercises.assessment2.logic.ProcedureEntity;
import com.practicalexercises.assessment2.logic.Shift;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvAdmin", urlPatterns = {"/SvAdmin"})
public class SvAdmin extends HttpServlet {

    Controller controller = new Controller();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("inputPassword");
        Administrator adminFound = controller.searchAdminByUserPassword(username, password);
        Map<String,Administrator> mapAdmin = new HashMap<>();
        mapAdmin.put("admin", adminFound);
        HttpSession mySession = request.getSession();
        mySession.setAttribute("admin", mapAdmin);
        if (adminFound != null) {
            mySession.setAttribute("userLoginTrue", adminFound);
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("loginAdmin.jsp");
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
          String username = request.getParameter("username");
          String password = request.getParameter("inputPassword");

        System.out.println(username + " " + password);
        List<ProcedureEntity> procedures = new ArrayList<>();
        controller.createAdmin(procedures, username, password);
        response.sendRedirect("loginAdmin.jsp");
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
