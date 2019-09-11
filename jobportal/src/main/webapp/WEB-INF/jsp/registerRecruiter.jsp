<?xml version="1.0" encoding="ISO-8859-1" ?>

	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" session="false"/>
	<html xmlns="http://www.w3.org/1999/xhtml">

<body>

<!-- navigation menue -->
<%@include file="/nav.html" %>

<%=request.getAttribute("returnMsg") %>
Form1::
     <FORM action="/rec/reg" method="post">
      enter your details--<br><br>
       name:<INPUT type="text"  name="name"/>
       phone:<INPUT type="number" name="phone"/>
       email:<INPUT type="email" name="email" />
       company name:<INPUT type="text" name="cname"/>
       company email<INPUT type="email" name="cemail"/>
       <INPUT  type="submit" value="save" />
</FORM>


 
</body>
</html>
