<%-- 
    Document   : MyTicket
    Created on : Aug 14, 2023, 4:56:48 PM
    Author     : MYCOM
--%>

<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>My Ticket</title>
    </head>
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
    <body>
    <center>
            <h1>My Ticket</h1>
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
                    JSONArray jsonArray = (JSONArray) request.getAttribute("MyTicket");
                    if (jsonArray.length() == 0) {
                %>
                <tr>
                    <td colspan="15">No reservation found.</td>
                </tr>
                <%
                } else {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject ticket = jsonArray.getJSONObject(i);
                %>
                <tr>
                    <td><%= ticket.getString("First_Name")%></td>
                    <td><%= ticket.getString("Last_Name")%></td>
                    <td><%= ticket.getString("Gender")%></td>
                    <td><%= ticket.getString("Tel")%></td>
                    <td><%= ticket.getString("Email")%></td>
                    <td><%= ticket.getString("Tickets")%></td>
                    <td><%= ticket.getString("Total")%></td>
                    <td><%= ticket.getString("Train No")%></td>
                    <td><%= ticket.getString("Train Type")%></td>
                    <td><%= ticket.getString("Origin")%></td>
                    <td><%= ticket.getString("Destination")%></td>
                    <td><%= ticket.getString("Departure Date")%></td>
                    <td><%= ticket.getString("Departure Time")%></td>
                    <td><%= ticket.getString("Arrival Time")%></td>
                    <td><%= ticket.getString("Ticket Price")%></td>
                </tr>
                <%
                        }
                    }
                %>
            </table>
            <br>
        <a href="MyTicket.html"><button>Back</button></a>
    </center>
</body>
</html>
