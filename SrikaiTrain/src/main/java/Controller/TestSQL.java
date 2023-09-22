package Controller;

import Model.Train;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestSQL {
    public static void main(String arg[])
    {
        Connection connection = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/srikai",
                    "root", "JJmysqllab");
 
            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(
                "select * from train where Train_Origin='Chiang Mai' and Train_Destination='Ubon Ratchathani' and Departure_Date='2023-08-11'");
            int trainno, trainprice;
            String traintype, trainori, traindes, traindate, traindep, trainarr;
            while (resultSet.next()) {
                trainno = resultSet.getInt("Train_Number");
                traintype = resultSet.getString("Train_Type");
                trainori = resultSet.getString("Train_Origin");
                traindes = resultSet.getString("Train_Destination");
                traindate = resultSet.getString("Departure_Date");
                traindep = resultSet.getString("Departure_Time");
                trainarr = resultSet.getString("Arrival_Time");
                trainprice = resultSet.getInt("Ticket_Price");
                System.out.println("Train No.:" + trainno +
                                            "Train Type:" + traintype + 
                                            "Train Origin:" + trainori + "Train Destination:" + traindes + "Departure Date:" + traindate +
                                            "Departure Time:" + traindep + "Arrival Time:" + trainarr + "Ticket Price:" + trainprice);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
