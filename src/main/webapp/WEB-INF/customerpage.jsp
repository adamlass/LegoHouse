<%-- 
    Document   : customerpage
    Created on : Aug 22, 2017, 2:33:37 PM
    Author     : kasper
--%>

<%@page import="PresentationLayer.Configuration"%>
<%@page import="FunctionLayer.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer home page</title>

        <% User user = (User) session.getAttribute("user");
           Configuration conf = (Configuration) session.getAttribute("configuration");
        %>
    </head>
    <body>
        <div>
            <h1>Hello <%= user.getEmail() %> </h1>
            You are now logged in as a customer of our wonderful site.
        </div>
        <div>
            <h1>Design your LegoHouse</h1>
            <form name="configure" action="FrontController" method="POST">
                <input type="hidden" name="command" value="configure">
                Length:<br>
                <input type="number" name="length" value="4">
                <br>
                Width:<br>
                <input type="number" name="width" value="4">
                <br>
                Height:<br>
                <input type="number" name="height" value="1">
                <br>

                Include:
                <br>
                <input type="checkbox" name="door" value="true" checked>Door

                <br>
                <input type="checkbox" name="window" value="true" checked>Window
                <br>

                <input type="submit" value="Calculate">
            </form>
        </div>
            
            <br>
        <% if (conf != null){ %>
        <div>
            <table>
                <tr>
                    <th></th>
                    <th>4x2 blocks</th>
                    <th>2x2 blocks</th>
                    <th>1x2 blocks</th>
                    <th>Door</th>
                    <th>Window</th>
                </tr>
                <tr>
                    <td>Amount:</td>
                    <td><%= conf.getFourTwo() %></td>
                    <td><%= conf.getTwoTwo() %></td>
                    <td><%= conf.getOneTwo() %></td>
                    <td><%= conf.isDoor() %></td>
                    <td><%= "" + conf.isWindow() %></td>
                </tr>
            </table>
                
                <form name="placeorder" action="FrontController" method="POST">
                    <input type="hidden" name="command" value="placeorder">
                    <input type="submit" value="Place Order">
                </form>

        </div>
        <% } %>
        
    </body>


</html>
