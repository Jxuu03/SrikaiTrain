/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Service;

import Controller.DBConnection;
import Model.Reserv;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author MYCOM
 */
public class getMyTicket extends HttpServlet {

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
        System.out.println(">>>>>>>>>>>> Admin ---> getMyTicket ");
        response.setContentType("application/json;charset=UTF-8"); // Set content type to JSON
        response.setCharacterEncoding("UTF-8");
        String requestUrl = request.getRequestURI();
        String data = requestUrl.substring("SrikaiTrain/getMyTicket/".length());
        data = URLDecoder.decode(data, "UTF-8");
        System.out.println(">>>>>>>>>>> getMyTicket: data=" + data);

        // Retrieve train data sets from MySQL based on the route
        DBConnection dbConnection = new DBConnection();
        List<Reserv> reservs = dbConnection.getTickets(data);
        System.out.println(">>>>>>>>>" + reservs);

        // Construct the JSON array as a string
        StringBuilder jsonArrayStr = new StringBuilder("[");
        for (int i = 0; i < reservs.size(); i++) {
            Reserv reserv = reservs.get(i);
            jsonArrayStr.append("{");
            jsonArrayStr.append("\"First_Name\": ").append(JSONObject.quote(reserv.getFirst())).append(",");
            jsonArrayStr.append("\"Last_Name\": ").append(JSONObject.quote(reserv.getLast())).append(",");
            jsonArrayStr.append("\"Gender\": ").append(JSONObject.quote(reserv.getGender())).append(",");
            jsonArrayStr.append("\"Tel\": ").append(JSONObject.quote(reserv.getTel())).append(",");
            jsonArrayStr.append("\"Email\": ").append(JSONObject.quote(reserv.getEmail())).append(",");
            jsonArrayStr.append("\"Train No\": ").append(JSONObject.quote(Integer.toString(reserv.getTrainno()))).append(",");
            jsonArrayStr.append("\"Tickets\": ").append(JSONObject.quote(Integer.toString(reserv.getTickets()))).append(",");
            jsonArrayStr.append("\"Total\": ").append(JSONObject.quote(Integer.toString(reserv.getTotal()))).append(",");
            jsonArrayStr.append("\"Train Type\": ").append(JSONObject.quote(reserv.getType())).append(",");
            jsonArrayStr.append("\"Origin\": ").append(JSONObject.quote(reserv.getOri())).append(",");
            jsonArrayStr.append("\"Destination\": ").append(JSONObject.quote(reserv.getDes())).append(",");
            jsonArrayStr.append("\"Departure Date\": ").append(JSONObject.quote(reserv.getDate())).append(",");
            jsonArrayStr.append("\"Departure Time\": ").append(JSONObject.quote(reserv.getDep())).append(",");
            jsonArrayStr.append("\"Arrival Time\": ").append(JSONObject.quote(reserv.getArr())).append(",");
            jsonArrayStr.append("\"Ticket Price\": ").append(JSONObject.quote(Integer.toString(reserv.getPrice())));
            jsonArrayStr.append("}");
            if (i < reservs.size() - 1) {
                jsonArrayStr.append(",");
            }
        }
        jsonArrayStr.append("]");

        // Send the JSON array response
        response.getWriter().print(jsonArrayStr.toString());
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
