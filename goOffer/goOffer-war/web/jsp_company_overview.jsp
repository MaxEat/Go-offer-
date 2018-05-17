<%-- 
    Document   : company_overview
    Created on : May 11, 2018, 1:21:59 PM
    Author     : jiahao pan 
--%>

<%@page import="java.util.List"%>
<%@page import="goOffer.entities.Job"%>
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
                <td>ID</td>
                <td>Name</td>
                <td>Location</td>
                <td>Description</td>
                <td>Expiration Date</td>
            </tr>
            <%
                List<Job> jobs = (List) request.getAttribute("jobs");
                for (Job u : jobs) {
            %>
            <tr>
                <td><%=u.getJobID()%></td>
                <td><%=u.getJobName()%></td>
                <td><%=u.getLocation()%></td>
                <td><%=u.getDescription()%></td>
                <td><%=u.getExpirationDate()%></td>
                <td>
                    <form action="servlet_company_deleteJob" method="POST">
                        <input type="hidden" name=jobID value=<%=u.getJobID()%>>
                        <input type="submit" name=operations value=delete>
                    </form>
                </td>
                <%
                    }
                %>
        </table>
        <%
            if (request.getAttribute("addNewJob") == "set") {
        %>
        <form action="servlet_company_addJob" method="POST">
            job name: <input type="text" name="jobName">
            </br>
            location: <input type="text" name="location">
            </br>
            description: <input type="text" name="description">
            expiration date: <input type="datetime-local" name="expirationDate">
            </br>
            <input type="submit" value="submit">
        </form>

        <%
        } else {
        %>
        <form action="servlet_company_overview" method="POST">
            <input type="submit"name=operations value=addNewJob>
        </form>
        <%
            }
        %>
    </body>
</html>
