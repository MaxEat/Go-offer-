<%-- 
    Document   : testJSP
    Created on : May 9, 2018, 2:51:57 PM
    Author     : jiahao pan 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Let's test our JSP</h1>

        <form action="testJSP_saveUser" method="POST">
            <center>
            userName: <input type="text" name="username">
            </br>
            password: <input type="password" name="password">
            </br>
            <input type="submit" value="submit">
            </center>
        </form>
    </body>
</html>
