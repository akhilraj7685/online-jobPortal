<?xml version="1.0" encoding="ISO-8859-1" ?>

	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" session="false"/>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>	
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Insert title here</title>
</head>

<body>
<!-- navigation menue -->
<c:import url="/nav.html"></c:import>
<c:out value="${returnMsg }"></c:out>
----- candidate    SignUp-----

<FORM action="/auth/candiSignup1" method="post">
 
 <TABLE>
  <TR>
    <td> name</td><TD><INPUT type="text" name="name"/></TD>
  </TR>
  
    <TR>
    <td> name</td><TD><INPUT type="text" name="name"/></TD>
   </TR>
 </TABLE>
  <INPUT  type="submit" value="signup"/>
  </FORM>
  
  
  
  
  already registered? <a href="/jobs/login0">login</a>
</body>
</html>
