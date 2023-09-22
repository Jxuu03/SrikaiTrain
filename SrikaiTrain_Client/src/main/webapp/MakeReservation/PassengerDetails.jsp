<%-- 
    Document   : PassengerDetails
    Created on : Aug 13, 2023, 4:46:53 PM
    Author     : Kamon
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href='https://fonts.googleapis.com/css?family=Sarabun' rel='stylesheet'>
        <style>
            .table-container {
                display: flex;
            }
            .container {
                display: flex;
                justify-content: center;
            }
            .button {
                margin-right: 10px;
            }
            .table {
                border-collapse: collapse;
                width: 50%;
                border: 1px solid black;
                margin-right: 10px;
            }
            .table th, .table td {
                border: 1px solid black;
                padding: 5px;
                text-align: left;
            }
            body, button, option, input, select {
                font-family: 'Sarabun';
            }
            body {
                display: flex;
                justify-content: center;
                align-items: center;
                margin: 0;
            }
        </style>
        <%
            int trainTickets = (int) session.getAttribute("TrainTickets");
            int trainNo = (int) session.getAttribute("TrainNo");
            String trainType = (String) session.getAttribute("TrainType");
            String ori = (String) session.getAttribute("Origin");
            String des = (String) session.getAttribute("Destination");
            String date = (String) session.getAttribute("Date");
            String dep = (String) session.getAttribute("DepTime");
            String arr = (String) session.getAttribute("ArrTime");
            int price = (int) session.getAttribute("Price");
            int total = trainTickets * price;
        %>
        <meta charset="UTF-8">
        <title>Passenger Details</title>
    </head>
    <body>
    <center>
        <form action="Confirm">
            <h1>Passenger Details</h1>
            <div class="table-container">
                <table class="table">
                    <tr><th>Train No.:</th><td><%= trainNo%></td></tr>
                    <tr><th>Train Type:</th><td><%= trainType%></td></tr><tr>
                        <th>Origin:</th><td><%= ori%></td></tr>
                    <tr><th>Destination:</th><td><%= des%></td></tr> 
                    <tr><th>Departure Date:</th><td><%= date%></td></tr>
                    <tr><th>Departure Time:</th><td><%= dep%></td></tr>
                    <tr><th>Arrival Time:</th><td><%= arr%></td></tr>
                    <tr><th>Ticket Price (฿) :</th><td><%= price%> x <%= trainTickets%></td></tr>
                    <input type="hidden" name="TrainNo" value="<%= trainNo%>"></td>
                    <input type="hidden" name="TrainType" value="<%= trainType%>">
                    <input type="hidden" name="Origin" value="<%= ori%>">
                    <input type="hidden" name="Destination" value="<%= des%>">
                    <input type="hidden" name="Date" value="<%= date%>">
                    <input type="hidden" name="DepTime" value="<%= dep%>">
                    <input type="hidden" name="ArrTime" value="<%= arr%>">
                    <input type="hidden" name="Price" value="<%= price%>">
                    <input type="hidden" name="Tickets" value="<%= trainTickets%>">
                    <input type="hidden" name="Total" value="<%= total%>">
                </table>

                <table class="table">
                    <tr><th>First Name:</th><td><input type="text" name="first"></td></tr>
                    <tr><th>Last Name.:</th><td><input type="text" name="last"></td></tr>
                    <tr><th>Gender:</th><td>
                            <select name="gender">
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                            </select></td></tr>
                    <tr><th>Tel. Number:</th><td><input type="tel" name="tel"></td></tr>
                    <tr><th>Email:</th><td><input type="email" name="email"></td></tr><br>
                </table>
            </div>
            <br>
            <div class="container">
                <table class="table">
                    <tr><th>Total (฿) :</th><td><%= total%></td></tr>
                    <tr><input type="submit" class="button" value="Confirm"></tr>
                </table>
            </div>   
            <br>
        </form>
        <a href="MakeReservation.html"><button>Back</button></a>
    </center>
</body>
</html>