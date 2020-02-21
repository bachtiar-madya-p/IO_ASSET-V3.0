<%@page import=".JOConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<%
String user = request.getParameter("user");
String pswd = request.getParameter("pswd");
JOConnection.JConnect();
String selectquery = "select * from register where user='"  + "' and pswd='" + pswd + "'";
JOConnection.rslt = JOConnection.stmt.executeQuery(selectquery);
if (JOConnection.rslt.next()) {
%>
<script>
alert("Record Found");
window.location.href="http://localhost:8084/WebApplication9/index.html";
</script>
<%} else {%>
<script>
alert("No Record Found, Try again");
window.location.href="http://localhost:8084/WebApplication9/login.html";
</script>
<%}%>