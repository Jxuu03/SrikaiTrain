<%-- 
    Document   : ShowTrainDetails
    Created on : Aug 11, 2023, 7:41:08 PM
    Author     : Kamon
--%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href='https://fonts.googleapis.com/css?family=Sarabun' rel='stylesheet'>
        <style>
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse;
            }
            th, td {
                padding: 5px;
                text-align: center;
            }
            body, button, option, input {
                font-family: 'Sarabun';
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Train Details</title>
    </head>
    <body>
    <center>
        <h1>All Train Details</h1>
        <table border="1">
            <tr>
                <th> Train Number </th>
                <th> Train Type </th>
                <th> Origin </th>
                <th> Destination </th>
                <th> Departure Date </th>
                <th> Departure Time </th>
                <th> Arrival Time </th>
                <th> Ticket Price (฿) </th>
            </tr>
            <%
                Connection connection = null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/srikai",
                            "root", "JJmysqllab");
                    Statement statement;
                    statement = connection.createStatement();
                    String query = "select * from train order by train_number asc";
                    ResultSet resultSet;
                    resultSet = statement.executeQuery(query);
                    while (resultSet.next()) {
            %>
            <tr>
                <td><%=resultSet.getInt("Train_Number")%></td>
                <td><%=resultSet.getString("Train_Type")%></td>
                <td><%=resultSet.getString("Train_Origin")%></td>
                <td><%=resultSet.getString("Train_Destination")%></td>
                <td><%=resultSet.getString("Departure_Date")%></td>
                <td><%=resultSet.getString("Departure_Time")%></td>
                <td><%=resultSet.getString("Arrival_Time")%></td>
                <td><%=resultSet.getInt("Ticket_Price")%></td>
            </tr>
            <% }
                    } catch (Exception e) {
                    out.print(e.getMessage());
                }
            %>
        </table>
        <br>
        <a href="/SrikaiTrain/AddTrainDetails.html"><button>Add New Train Details</button></a>
        <a href="/SrikaiTrain/TrainData.html"><button>Back</button></a>
    </center>
</body>
</html>
