<%-- 
    Document   : result
    Created on : Aug 12, 2023, 7:56:30 PM
    Author     : MYCOM
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Train Search Results</title>
</head>
<body>
    <h1>Train Search Results</h1>
    
    <table border="1">
        <tr>
            <th>Train Number</th>
            <th>Train Type</th>
            <th>Origin</th>
            <th>Destination</th>
            <th>Departure Date</th>
            <th>Departure Time</th>
            <th>Arrival Time</th>
            <th>Ticket Price</th>
        </tr>
        
        <c:forEach var="train" items="${trains}">
            <tr>
                <td>${train.Train_Number}</td>
                <td>${train.Train_Type}</td>
                <td>${train.Train_Origin}</td>
                <td>${train.Train_Destination}</td>
                <td>${train.Departure_Date}</td>
                <td>${train.Departure_Time}</td>
                <td>${train.Arrival_Time}</td>
                <td>${train.Ticket_Price}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
