package Servlet;
import Model.Train;
import Controller.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kamon
 */
public class Add extends HttpServlet {

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
        int TrainNo = Integer.parseInt(request.getParameter("TrainNo"));
        String TrainType = request.getParameter("TrainType");
        String TrainOrigin = request.getParameter("TrainOrigin");
        String TrainDestination = request.getParameter("TrainDestination");
        String TrainDate = request.getParameter("TrainDate");
        String TrainDepTime = request.getParameter("TrainDepTime");
        String TrainArrTime = request.getParameter("TrainArrTime");
        int TrainPrice = Integer.parseInt(request.getParameter("TrainPrice"));
        out.println("Add New Train Details:<br/> " + "Train No.: " + TrainNo + "<br/>");
        out.println("Train Type: " + TrainType + "<br/>");
        out.println("Origin: " + TrainOrigin + "<br/>");
        out.println("Destination: " + TrainDestination + "<br/>");
        out.println("Departure Date: " + TrainDate + "<br/>");
        out.println("Departure Time: " + TrainDepTime + "<br/>");
        out.println("Arrival Time: " + TrainArrTime + "<br/>");
        out.println("Ticket Price: " + TrainPrice + " ฿");

        Train train = new Train();
        train.setNo(TrainNo);
        train.setType(TrainType);
        train.setOri(TrainOrigin);
        train.setDes(TrainDestination);
        train.setDate(TrainDate);
        train.setDep(TrainDepTime);
        train.setArr(TrainArrTime);
        train.setPrice(TrainPrice);
        
        DBConnection dbConnection = new DBConnection();
        if(!dbConnection.insertNewTrainDetails(train)){
            System.out.println(">>> AddNewTrainDetails ERROR");
        }
       HttpSession session = request.getSession();
        session.setAttribute("train", train);
        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/TrainData/AddDetailsSuccess.jsp");
        rd.forward(request, response);
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
