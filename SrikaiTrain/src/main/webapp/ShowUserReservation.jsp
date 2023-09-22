<%-- 
    Document   : ShowUserReservation
    Created on : Aug 14, 2023, 6:40:15 PM
    Author     : MYCOM
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
        <title>All Reservation Details</title>
    </head>
    <body>
    <center>
        <h1>All Reservation Details</h1>
        <table border="1">
            <tr>
                <th> First Name </th>
                <th> Last Name </th>
                <th> Gender </th>
                <th> Tel </th>
                <th> Email </th>
                <th> Tickets </th>
                <th> Total (฿) </th>
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
                    String query = "SELECT reserv.* , train.* "
                            + "FROM reserv "
                            + "JOIN train ON reserv.Train_No = train.Train_Number";
                    ResultSet resultSet;
                    resultSet = statement.executeQuery(query);
                    while (resultSet.next()) {
            %>
            <tr>
                <td><%=resultSet.getString("First_Name")%></td>
                <td><%=resultSet.getString("Last_Name")%></td>
                <td><%=resultSet.getString("Gender")%></td>
                <td><%=resultSet.getString("Tel")%></td>
                <td><%=resultSet.getString("Email")%></td>
                <td><%=resultSet.getInt("Tickets")%></td>
                <td><%=resultSet.getInt("Total")%></td>
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
        <a href="/SrikaiTrain/index.html"><button>Back</button></a>
    </center>
</body>
</html>
