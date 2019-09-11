<?xml version="1.0" encoding="ISO-8859-1" ?>

	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" session="false"/>
	<html xmlns="http://www.w3.org/1999/xhtml">

<body>
<!-- navigation menue -->
<%@include file="/nav.html" %>

<%=request.getAttribute("returnMsg") %>
Form1::
     <FORM action="/jobs/reg" method="post" >
      enter your details--<br><br>
       name:<INPUT type="text"  name="name"/>
       phone:<INPUT type="number" name="phone"/>
       age: <INPUT type="number" max=3 name=age/>
       qualification:<INPUT type="text" name="qualification"/>
       <INPUT  type="submit" value="save" />
</FORM>


 
</body>
</html>
