<%-- 
    Document   : index
    Created on : Aug 22, 2017, 2:01:06 PM
    Author     : kasper
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Adams LegoHouse</title>
        <%@include file="bootstrap.jsp" %>
    </head>
    <body>
        <div class="container container-fluid">
            <div class="well">
                <h1>Adams LegoHouse</h1>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <img src="Pictures/LegoHouse2.png" width="100%" alt="LegoHouse" />
                </div>
                <div class="col-lg-6">
                    <div class="row">
                        <div class="col-sm-6">
                            <h4>Login</h4>
                            <form name="login" action="FrontController" method="POST">
                                <input type="hidden" name="command" value="login">
                                <label>
                                    Email<br>
                                </label>

                                <input type="text" class="form-control" name="email" placeholder="admin@LegoHouse.com">
                                <br>
                                <label>
                                    Password<br>
                                </label>

                                <input type="password" class="form-control" name="password" placeholder="1234">
                                <br>
                                <input type="submit" class="btn btn-primary" value="Submit">
                            </form>
                            <br><br>
                        </div>
                        <div class="col-sm-6">
                            <h4>Create User</h4>
                            <form name="register" action="FrontController" method="POST">
                                <input type="hidden" name="command" value="register">

                                <label>
                                    Email<br>
                                </label>

                                <input type="text" class="form-control" name="email" placeholder="customer@LegoHouse.com">
                                <br>
                                <label>
                                Password<br>
                                </label>
                                <input type="password"  class="form-control" name="password1" placeholder="********">
                                <br>
                                <label>
                                Retype Password<br>
                                </label>
                                
                                <input type="password" class="form-control" name="password2" placeholder="********">
                                <br>
                                <input type="submit" class="btn btn-primary" value="Submit">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <br><br>

            <% String error = (String) request.getAttribute("error");
                if (error != null) {%>
            <div class="alert alert-danger">
                <strong>Error!</strong>
                <%= error%>
            </div>
            <% }
            %>
        </div>
    </body>
</html>
