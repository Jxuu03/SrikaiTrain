package Controller;

import java.sql.*;  // Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package
import Model.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBConnection {

    public boolean insertNewTrainDetails(Train train) {
        boolean result = false;
        Connection connection = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/srikai",
                    "root", "JJmysqllab");

            Statement statement;
            statement = connection.createStatement();

            String query = "INSERT INTO train "
                    + "(Train_Number, Train_Type, Train_Origin, Train_Destination, Departure_Date, Departure_Time, Arrival_Time, Ticket_Price) "
                    + "VALUES('"
                    + train.getNo() + "','"
                    + train.getType() + "','"
                    + train.getOri() + "','"
                    + train.getDes() + "','"
                    + train.getDate() + "','"
                    + train.getDep() + "','"
                    + train.getArr() + "',"
                    + train.getPrice() + ")";
            System.out.println("........SQL: " + query);

            int i = statement.executeUpdate(query);	// executeUpdate returns row count ****
            if (i != 0) {
                result = true;
            }
            statement.close();
            connection.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }

        return result;

    }

    /*
    https://www.geeksforgeeks.org/java-database-connectivity-with-mysql/
     */
    public List<Train> getTrains(String route) {

        List<Train> trains = new ArrayList<>();
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/srikai",
                    "root", "JJmysqllab");

            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            String[] routePath = route.split("/");
            String origin = routePath[1].replace("+", "%20");
            String destination = routePath[2].replace("+", "%20");
            String date = routePath[3];
            System.out.println(">>>>>>>>>>>> Admin ---> DBConnection ");
            String query = "select * from train where Train_Origin='" + origin + "'"
                    + "and Train_Destination='" + destination + "'"
                    + "and Departure_Date='" + date + "'";
            System.out.println(">>>>>>>>>> query=" + query);

            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Train train = new Train();
                train.setNo(resultSet.getInt("Train_Number"));
                train.setType(resultSet.getString("Train_Type").trim());
                train.setOri(resultSet.getString("Train_Origin").trim());
                train.setDes(resultSet.getString("Train_Destination").trim());
                train.setDate(resultSet.getString("Departure_Date").trim());
                train.setDep(resultSet.getString("Departure_Time").trim());
                train.setArr(resultSet.getString("Arrival_Time").trim());
                train.setPrice(resultSet.getInt("Ticket_Price"));
                trains.add(train);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println(exception);
        }
        return trains;
    }

    public boolean insertNewReserv(String reserv) {
        boolean result = false;
        Connection connection = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/srikai",
                    "root", "JJmysqllab");

            Statement statement;
            statement = connection.createStatement();
            String[] newReserv = reserv.split("/");
            String first = newReserv[1];
            String last = newReserv[2];
            String gender = newReserv[3];
            String tel = newReserv[4];
            String email = newReserv[5];
            String trainno = newReserv[6];
            String tickets = newReserv[7];
            String total = newReserv[8];
            System.out.println(">>>>>>>>>>>> Admin ---> DBConnection ");

            String query = "INSERT INTO reserv "
                    + "(First_Name, Last_Name, Gender, Tel, Email, Train_No, Tickets, Total) "
                    + "VALUES('"
                    + first + "','"
                    + last + "','"
                    + gender + "','"
                    + tel + "','"
                    + email + "','"
                    + trainno + "','"
                    + tickets + "',"
                    + total + ")";
            System.out.println(">>>>>>>> SQL: " + query);

            int i = statement.executeUpdate(query);	// executeUpdate returns row count ****
            if (i != 0) {
                result = true;
            }
            statement.close();
            connection.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }

        return result;

    }

    public List<Reserv> getTickets(String user) {
        List<Reserv> reservs = new ArrayList<>();
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/srikai",
                    "root", "JJmysqllab");

            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            String[] data = user.split("/");
            String first = data[1];
            String last = data[2];
            System.out.println(">>>>>>>>>>>> Admin ---> DBConnection ");
            String query = "SELECT reserv.*, train.* "
                    + "FROM reserv "
                    + "JOIN train ON reserv.Train_No = train.Train_Number "
                    + "WHERE reserv.First_Name = '" + first + "' AND reserv.Last_Name = '" + last + "'";
            System.out.println(">>>>>>>>>> query=" + query);
            
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Reserv reserv = new Reserv();
                reserv.setFirst(resultSet.getString("First_Name").trim());
                reserv.setLast(resultSet.getString("Last_Name").trim());
                reserv.setGender(resultSet.getString("Gender").trim());
                reserv.setTel(resultSet.getString("Tel").trim());
                reserv.setEmail(resultSet.getString("Email").trim());
                reserv.setTrainno(resultSet.getInt("Train_No"));
                reserv.setTickets(resultSet.getInt("Tickets"));
                reserv.setTotal(resultSet.getInt("Total"));
                reserv.setNo(resultSet.getInt("Train_Number"));
                reserv.setType(resultSet.getString("Train_Type").trim());
                reserv.setOri(resultSet.getString("Train_Origin").trim());
                reserv.setDes(resultSet.getString("Train_Destination").trim());
                reserv.setDate(resultSet.getString("Departure_Date").trim());
                reserv.setDep(resultSet.getString("Departure_Time").trim());
                reserv.setArr(resultSet.getString("Arrival_Time").trim());
                reserv.setPrice(resultSet.getInt("Ticket_Price"));
                reservs.add(reserv);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println(exception);
        }
        return reservs;
    }

}
