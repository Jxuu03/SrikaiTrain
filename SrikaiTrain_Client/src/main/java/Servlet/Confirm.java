/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MYCOM
 */
public class Confirm extends HttpServlet {

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
        try {
            System.out.println(">>>>>>>> Client --> Confirm");
            /* Train Data. */
            String numTicketsStr = request.getParameter("Tickets");
            String trainNo = request.getParameter("TrainNo");
            String trainType = request.getParameter("TrainType");
            String ori = request.getParameter("Origin");
            String des = request.getParameter("Destination");
            String date = request.getParameter("Date");
            String dep = request.getParameter("DepTime");
            String arr = request.getParameter("ArrTime");
            String TicketPrice = request.getParameter("Price");
            String TotalPrice = request.getParameter("Total");

            /* Passenger Data*/
            String first = request.getParameter("first");
            String last = request.getParameter("last");
            String gender = request.getParameter("gender");
            String tel = request.getParameter("tel");
            String email = request.getParameter("email");

            System.out.println("Received Train: " + trainNo);
            System.out.println("Received Passenger: " + first + last + gender + tel + email);

            if (numTicketsStr != null) {
                int TrainTickets = Integer.parseInt(numTicketsStr);
                int trainNum = Integer.parseInt(trainNo);
                int price = Integer.parseInt(TicketPrice);
                int total = Integer.parseInt(TotalPrice);

                /*Add to database*/
                String params = URLEncoder.encode(first, "UTF-8") + "/"
                        + URLEncoder.encode(last, "UTF-8") + "/"
                        + URLEncoder.encode(gender, "UTF-8") + "/"
                        + URLEncoder.encode(tel, "UTF-8") + "/"
                        + URLEncoder.encode(email, "UTF-8") + "/"
                        + URLEncoder.encode(String.valueOf(trainNum), "UTF-8") + "/"
                        + URLEncoder.encode(String.valueOf(TrainTickets), "UTF-8") + "/"
                        + URLEncoder.encode(String.valueOf(total), "UTF-8");
                String url = "http://localhost:8080/SrikaiTrain/addReserv/" + params;
                System.out.println(">>>>>>>> params " + params);
                System.out.println(">>>>>>> url " + url);
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                int responseCode = connection.getResponseCode();
                System.out.println("Response Code: " + responseCode);

                if (responseCode == 200) {
                    /*Session*/
                    HttpSession session = request.getSession();
                    /*Train*/
                    session.setAttribute("TrainTickets", TrainTickets);
                    session.setAttribute("TrainNo", trainNum);
                    session.setAttribute("TrainType", trainType);
                    session.setAttribute("Origin", ori);
                    session.setAttribute("Destination", des);
                    session.setAttribute("Date", date);
                    session.setAttribute("DepTime", dep);
                    session.setAttribute("ArrTime", arr);
                    session.setAttribute("Price", price);
                    session.setAttribute("Total", total);

                    /*Passenger*/
                    session.setAttribute("First", first);
                    session.setAttribute("Last", last);
                    session.setAttribute("Gender", gender);
                    session.setAttribute("Tel", tel);
                    session.setAttribute("Email", email);

                    // Forward to PassengerDetails.jsp
                    request.getRequestDispatcher("/MakeReservation/ReservSuccess.jsp").forward(request, response);
                } else {
                    // Handle error case
                    System.out.println("ERROR ... responseCode=" + responseCode);
                }
            }
        } catch (Exception e) {
            // Handle exceptions
            System.out.println("Exception: " + e.getMessage());
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
