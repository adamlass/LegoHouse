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
        <div class="container container-fluid">
            <div class="well">
                <h1>Hello <%= user.getEmail()%> </h1>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <h2>Design your LegoHouse</h2>
                    <a>Made possible by a recursive Lego Builder AI!</a>
                    <br>
                    <br>

                    <form name="configure" action="FrontController" method="POST" >


                        <input type="hidden" name="command" value="configure">

                        <label>
                            Length<br>
                            <input type="number" class="form-control" name="length" placeholder="min. 8">
                        </label>
                        <br>

                        <label>
                            Width
                            <input type="number" class="form-control" name="width" placeholder="min. 6">
                        </label>
                        <br>

                        <label>
                            Height
                            <input type="number" class="form-control" name="height" placeholder="min. 6">
                        </label>
                        <br>

                        <label>
                            Door & Window
                            <br>
                            <label class="form-control">
                                <input type="checkbox" name="door" value="true"> Door
                            </label>
                            <label class="form-control">
                                <input type="checkbox" name="window" value="true"> Window
                            </label>
                        </label>

                        <br>

                        <input type="submit" class="btn btn-primary" value="Calculate">
                    </form>
                </div>

                <br>
                <div class="col-lg-6">
                    <% if (conf != null) {%>

                    <table class="table table-bordered">
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
                        <input type="submit" class="btn btn-success" value="Place Order">
                    </form>


                    <% } else if (session.getAttribute("orderok") != null) { %>
                    <div class="alert alert-success">
                        <strong>Success!</strong>
                        Your Order Was Successfully Submitted!
                        <br><br>To view your order go to Orders
                        <br><br>
                        <%@include file="seeorders.jsp" %>
                    </div>
                   
                    
                    


                    

                    <% }%>

                </div>
            </div>
            <br>
            <br>
            <div>
                <%@include file="seeorders.jsp" %>
                <%@include file="logout.jsp" %>
            </div>
        </div>
    </body>


</html>
