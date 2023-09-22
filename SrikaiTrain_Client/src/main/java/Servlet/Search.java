package Servlet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author Kamon
 */
public class Search extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            System.out.println(">>>>>>>>>>>> Client ---> Search ");
            String origin = request.getParameter("TrainOrigin");
            String destination = request.getParameter("TrainDestination");
            String date = request.getParameter("TrainDate");
            String params = URLEncoder.encode(origin, "UTF-8") + "/"
                    + URLEncoder.encode(destination, "UTF-8") + "/"
                    + URLEncoder.encode(date, "UTF-8");
            String url = "http://localhost:8080/SrikaiTrain/getTrainData/" + params;
            if (url.contains("+")) {
                url = url.replace("+", "%20");
            }

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                String json = "";
                Scanner scanner = new Scanner(connection.getInputStream());
                while (scanner.hasNextLine()) {
                    json += scanner.nextLine();
                    json += "\n";
                }
                scanner.close();
                System.out.println(">>>>>> Response JSON: " + json);

                JSONArray jsonArray = new JSONArray(json); // Parse as JSONArray
                // Forward to the JSP page for rendering
                
                request.setAttribute("trainData", jsonArray);
                request.getRequestDispatcher("/MakeReservation/SearchResult.jsp").forward(request, response);
            } else {
                // Handle error case
                System.out.println("ERROR ... responseCode=" + responseCode);
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
