<%-- 
    Document   : userSaveSuccess
    Created on : May 10, 2018, 4:08:41 PM
    Author     : jiahao pan 
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="goOffer.entities.Usertable"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1">
            <tr>
                <td>id</td>
                <td>username</td>
                <td>password</td>
            </tr>
            <%
                List<Usertable> users = (List)request.getAttribute("users");
                for (Usertable u : users) {
            %>
            <tr>
                <td><%=u.getUserid()%></td>
                <td><%=u.getUsername()%></td>
                <td><%=u.getPassword()%></td>
                <%
                    }
                %>
        </table>

    </body>
</html>
