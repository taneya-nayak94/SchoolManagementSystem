<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Learners Academy</title>
</head>
<body>

<%
HttpSession mySession=request.getSession(false);
if(mySession==null){
	out.print("<center><h4>Your session seems to be expired</h4></center>");
	response.setHeader("refresh", "5;url='/SchoolManagmentSystem'");
}
else{
	Date createTime = new Date(mySession.getCreationTime());
%>
<h1 style="font-family:Bookman Old Style; color:rgb(255, 0, 0); font-size: 50px;">Learner's Academy</h1><b style="Color:blue">Current login timestamp : <%=createTime %></b>
<div style="text-align:center;">
<h2 style="font-family:Verdana; color:green; font-size: 30px;">My Dashboard</h2>

<table style="text-align:left; margin-left:auto;">
<tr><td>&nbsp;&nbsp;<a href="logout">logout</a>&nbsp;&nbsp;</td></tr>
</table>
<br/>

<div style="color:green;text-align: center; border: 1px outset red;
  background-color: lightblue;" >
<%if(mySession.getAttribute("exception")!=null) {%>
<%=mySession.getAttribute("exception") %>
<%}if(mySession.getAttribute("exceptionSys")!=null) {%>
System Error <%=mySession.getAttribute("exceptionSys") %>
<%} %>
</div>
<br/>
 <table border="3" style="font-family:Bookman Old Style;text-align:left; margin-left:auto;margin-right:auto;">
<tr><td>&nbsp;&nbsp;<a href="student">Manage Students Data</a>&nbsp;&nbsp;</td><td> Get a list of all students
<br/>Add New Students
<br/>Delete an existing student</td></tr>
<tr><td>&nbsp;&nbsp;<a href="classes">Manage Class Data</a>&nbsp;&nbsp;</td><td> Get a list of all Classes
<br/>Add New Class
<br/>Delete an existing Class
<br/>Generate Class Report</td></tr>
<tr><td>&nbsp;&nbsp;<a href="teacher">Manage Teachers Data</a>&nbsp;&nbsp;</td><td> Get a list of all Teachers
<br/>Add New Teachers
<br/>Delete an existing Teacher</td></tr>
<tr><td>&nbsp;&nbsp;<a href="subject">Manage Subject Data</a>&nbsp;&nbsp;</td><td> Get a list of all Subjects
<br/>Add New Subject
<br/>Delete an existing Subject</td></tr>
</table>

</div>
<%@include file='footer.html' %>
<%} %>
<%mySession.removeAttribute("exception");
mySession.removeAttribute("exceptionSys");%>
</body>
</html>
