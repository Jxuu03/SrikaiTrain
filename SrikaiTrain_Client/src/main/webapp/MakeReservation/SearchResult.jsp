<%-- 
    Document   : SearchResult
    Created on : Aug 12, 2023, 9:56:04 PM
    Author     : MYCOM
--%>

<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Search Result</title>
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
        <form action="Choose">
            <h1>Search Result</h1>
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
                    <th> Choose </th>
                </tr>
                <%
                    JSONArray jsonArray = (JSONArray) request.getAttribute("trainData");
                    if (jsonArray.length() == 0) {
                %>
                <tr>
                    <td colspan="9">No train data found.</td>
                </tr>
                <%
                } else {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject train = jsonArray.getJSONObject(i);
                %>
                <tr>
                    <td><%= train.getString("Train No.")%></td>
                    <td><%= train.getString("Train Type")%></td>
                    <td><%= train.getString("Origin")%></td>
                    <td><%= train.getString("Destination")%></td>
                    <td><%= train.getString("Departure Date")%></td>
                    <td><%= train.getString("Departure Time")%></td>
                    <td><%= train.getString("Arrival Time")%></td>
                    <td><%= train.getString("Ticket Price")%></td>
                    <td><input type="radio" name="TrainNo" value="<%= train.getString("Train No.")%>"></td>
                <input type="hidden" name="TrainType" value="<%= train.getString("Train Type")%>">
                <input type="hidden" name="Origin" value="<%= train.getString("Origin")%>">
                <input type="hidden" name="Destination" value="<%= train.getString("Destination")%>">
                <input type="hidden" name="Date" value="<%= train.getString("Departure Date")%>">
                <input type="hidden" name="DepTime" value="<%= train.getString("Departure Time")%>">
                <input type="hidden" name="ArrTime" value="<%= train.getString("Arrival Time")%>">
                <input type="hidden" name="Price" value="<%= train.getString("Ticket Price")%>">
                </tr>
                <%
                        }
                    }
                %>
            </table>
            <br>
            <% if (jsonArray.length() > 0) { %>
            <label for="numTickets">Number of Ticket (s) :</label>
            <input type="number" name="TrainTickets" min="1" value="1" max="10">
            <input type="submit" value="Next">
            <% }%>
        </form>
        <br>
        <a href="MakeReservation.html"><button>Back</button></a>
    </center>
</body>
</html>