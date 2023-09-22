package Service;

import Controller.DBConnection;
import java.io.IOException;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Train;
import java.util.List;
import org.json.JSONObject;

public class getTrainData extends HttpServlet {

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
    System.out.println(">>>>>>>>>>>> Admin ---> getTrainData ");
    response.setContentType("application/json;charset=UTF-8"); // Set content type to JSON
    response.setCharacterEncoding("UTF-8");
    String requestUrl = request.getRequestURI();
    String route = requestUrl.substring("SrikaiTrain/getTrainData/".length());        
    route = URLDecoder.decode(route, "UTF-8");
    System.out.println(">>>>>>>>>>> getTrainData: route=" + route);

    // Retrieve train data sets from MySQL based on the route
    DBConnection dbConnection = new DBConnection();
    List<Train> trains = dbConnection.getTrains(route);

    // Construct the JSON array as a string
    StringBuilder jsonArrayStr = new StringBuilder("[");
    for (int i = 0; i < trains.size(); i++) {
        Train train = trains.get(i);
        jsonArrayStr.append("{");
        jsonArrayStr.append("\"Train No.\": ").append(JSONObject.quote(Integer.toString(train.getNo()))).append(",");
        jsonArrayStr.append("\"Train Type\": ").append(JSONObject.quote(train.getType())).append(",");
        jsonArrayStr.append("\"Origin\": ").append(JSONObject.quote(train.getOri())).append(",");
        jsonArrayStr.append("\"Destination\": ").append(JSONObject.quote(train.getDes())).append(",");
        jsonArrayStr.append("\"Departure Date\": ").append(JSONObject.quote(train.getDate())).append(",");
        jsonArrayStr.append("\"Departure Time\": ").append(JSONObject.quote(train.getDep())).append(",");
        jsonArrayStr.append("\"Arrival Time\": ").append(JSONObject.quote(train.getArr())).append(",");
        jsonArrayStr.append("\"Ticket Price\": ").append(JSONObject.quote(Integer.toString(train.getPrice())));
        jsonArrayStr.append("}");
        if (i < trains.size() - 1) {
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
