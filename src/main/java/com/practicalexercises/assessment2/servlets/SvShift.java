package com.practicalexercises.assessment2.servlets;

import com.practicalexercises.assessment2.logic.Administrator;
import com.practicalexercises.assessment2.logic.Citizen;
import com.practicalexercises.assessment2.logic.Controller;
import com.practicalexercises.assessment2.logic.ProcedureEntity;
import com.practicalexercises.assessment2.logic.Shift;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvShift", urlPatterns = {"/SvShift"})
public class SvShift extends HttpServlet {

    Controller controller = new Controller();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Shift> shifts = controller.bringShifts();
        LocalDate date = LocalDate.parse(request.getParameter("dateFilter"));
        boolean state = Boolean.parseBoolean(request.getParameter("filterstate"));
        Citizen citizen = (Citizen) request.getSession().getAttribute("userLoginTrue");

        shifts = shifts.stream()
                .filter(shift -> 
                        shift.getCitizen().getId() == citizen.getId() &&
                        shift.isShiftStatus() == state
                && shift.getDateHour().getYear() == date.getYear()
                && shift.getDateHour().getMonth() == date.getMonth()
                && shift.getDateHour().getDayOfMonth() == date.getDayOfMonth())
                .collect(Collectors.toList());

        System.out.println(shifts);

        HttpSession mySession = request.getSession();
        mySession.setAttribute("shiftsList", shifts);
        response.sendRedirect("viewShifts.jsp");
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String additionalInfo = request.getParameter("additionalInfo");
        Integer procedureId = Integer.valueOf(request.getParameter("procedures"));
        Citizen citizen = (Citizen) request.getSession().getAttribute("userLoginTrue");
        ProcedureEntity procedure = controller.searchProcedure((long) procedureId);
        controller.createShift(LocalDateTime.now(), false, additionalInfo, procedure, citizen);
        response.sendRedirect("SvShiftAll");
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
