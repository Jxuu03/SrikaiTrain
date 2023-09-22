<%-- 
    Document   : AddDetailsSuccess
    Created on : Aug 11, 2023, 7:16:05 PM
    Author     : Kamon
--%>

<%@page import="java.util.Arrays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Train" %>
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
                text-align: left;
            }
            body, button, option, input {
            	font-family: 'Sarabun';
            }
        </style>    
        <title>Reservation</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
    <center>
        <table>
            <h1>Add New Train Details Success</h1>
            <%
                Train train = (Train) session.getAttribute("train");
            %>
            <tr>
                <th>Train No.:</th>
                <td><%= train.getNo()%></td>
            </tr>
            <tr>
                <th>Train Type:</th>
                <td><%= train.getType()%></td>
            </tr>
            <tr>
                <th>Origin:</th>
                <td><%=train.getOri()%></td>
            </tr>
            <tr>
                <th>Destination:</th>
                <td><%= train.getDes()%></td>
            </tr>
            <tr>
                <th>Departure Date:</th>
                <td><%= train.getDate()%></td>
            </tr>
            <tr>
                <th>Departure Time:</th>
                <td><%= train.getDep()%></td>
            </tr>
            <tr>
                <th>Arrival Time:</th>
                <td><%= train.getArr()%></td>
            </tr>
            <tr>
                <th>Ticket Price (฿) :</th>
                <td><%= train.getPrice()%></td>
            </tr>
        </table>
        <br>
        <a href="/SrikaiTrain/AddTrainDetails.html"><button>Add More</button></a>
        <a href="/SrikaiTrain/TrainData.html"><button>Back</button></a>    
    </center>
    </body>
</html>
