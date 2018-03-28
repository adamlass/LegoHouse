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
        <%@include file="bootstrap.jsp" %>

        <% User user = (User) session.getAttribute("user");
            Configuration conf = (Configuration) session.getAttribute("configuration");
        %>
    </head>
    <body>
        <div class="container">
            <div>
                <h1>Hello <%= user.getEmail()%> </h1>
                You are now logged in as a customer of our wonderful site.
            </div>
            <div class="row">
                <div class="col-xs-6">
                    <h1>Design your LegoHouse</h1>
                    <a>Made possible by a recursive Lego Builder AI!</a>
                    <br>
                    <br>

                    <form name="configure" action="FrontController" method="POST" >

                        <input type="hidden" class="form-control" name="command" value="configure">
                        Length:<br>
                        <input type="number" name="length" value="8">
                        <br>
                        Width:<br>
                        <input type="number" name="width" value="6">
                        <br>
                        Height:<br>
                        <input type="number" name="height" value="6">
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
                <div class="col-xs-6">
                    <% if (conf != null) {%>

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
                            <td><%= conf.getFourTwo()%></td>
                            <td><%= conf.getTwoTwo()%></td>
                            <td><%= conf.getOneTwo()%></td>
                            <td><%= conf.isDoor()%></td>
                            <td><%= "" + conf.isWindow()%></td>
                        </tr>
                    </table>

                    <form name="placeorder" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="placeorder">
                        <input type="submit" value="Place Order">
                    </form>


                    <% } else if (session.getAttribute("orderok") != null) { %>
                    <h2>Order Was Successfully Submitted!</h2>
                    <a>To view your order go to Orders</a>
                    <%@include file="seeorders.jsp" %>

                    <% }%>
                    
                </div>
            </div>
            <div>
                <%@include file="seeorders.jsp" %>
                <%@include file="logout.jsp" %>
            </div>
        </div>
    </body>


</html>
