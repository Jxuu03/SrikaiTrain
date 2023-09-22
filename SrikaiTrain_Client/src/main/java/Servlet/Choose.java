/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author MYCOM
 */
public class Choose extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String numTicketsStr = request.getParameter("TrainTickets");
        String trainNo = request.getParameter("TrainNo");
        String trainType = request.getParameter("TrainType");
        String ori = request.getParameter("Origin");
        String des = request.getParameter("Destination");
        String date = request.getParameter("Date");
        String dep = request.getParameter("DepTime");
        String arr = request.getParameter("ArrTime");
        String price = request.getParameter("Price");
        
        System.out.println("Received No: " + trainNo);
        System.out.println("Received numTicketsStr: " + numTicketsStr);
        System.out.println("Received Type: " + trainType);
        
        if (numTicketsStr != null) {
            int TrainTickets = Integer.parseInt(numTicketsStr);
            int trainNum = Integer.parseInt(trainNo);
            int ticket = Integer.parseInt(price);

            // Store the selected train data and numTickets in session
            HttpSession session = request.getSession();
            session.setAttribute("TrainTickets", TrainTickets);
            session.setAttribute("TrainNo", trainNum);
            session.setAttribute("TrainType", trainType);
            session.setAttribute("Origin", ori);
            session.setAttribute("Destination", des);
            session.setAttribute("Date", date);
            session.setAttribute("DepTime", dep);
            session.setAttribute("ArrTime", arr);
            session.setAttribute("Price", ticket);
            
            // Forward to PassengerDetails.jsp
            request.getRequestDispatcher("/MakeReservation/PassengerDetails.jsp").forward(request, response);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
