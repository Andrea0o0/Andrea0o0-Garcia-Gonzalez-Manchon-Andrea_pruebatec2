package com.practicalexercises.assessment2.servlets;

import com.practicalexercises.assessment2.logic.Administrator;
import com.practicalexercises.assessment2.logic.Citizen;
import com.practicalexercises.assessment2.logic.Controller;
import com.practicalexercises.assessment2.logic.ProcedureEntity;
import com.practicalexercises.assessment2.logic.Shift;
import java.io.IOException;
import java.text.SimpleDateFormat;
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


@WebServlet(name = "SvCitizen", urlPatterns = {"/SvCitizen"})
public class SvCitizen extends HttpServlet {

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
        Citizen citezenFound = controller.searchCitizenByUserPassword(username, password);
        Map<String,Citizen> mapCitizen = new HashMap<>();
        mapCitizen.put("citizen", citezenFound);
        HttpSession mySession = request.getSession();
        mySession.setAttribute("citizen", mapCitizen);
        if (citezenFound != null) {
            mySession.setAttribute("userLoginTrue", citezenFound);
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("loginCitizen.jsp");
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("inside post");
        String username = request.getParameter("username");
        String password = request.getParameter("inputPassword");
        String nameCitizen = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String dateOfBirthString = request.getParameter("dateOfBirth");
        LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString);
        List<Shift> shifts = new ArrayList<>();
        System.out.println(" "+nameCitizen + " "+ lastName + " "+ dateOfBirth + " "+ shifts + " "+ username + " "+ password);
        controller.createCitizen(nameCitizen, lastName, dateOfBirth, shifts, username, password);
        System.out.println(controller.bringCitizens());
        response.sendRedirect("loginCitizen.jsp");
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
