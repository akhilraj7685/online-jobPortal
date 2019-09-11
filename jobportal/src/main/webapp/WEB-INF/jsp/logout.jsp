<?xml version="1.0" encoding="ISO-8859-1" ?>

	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" session="true"/>
	

<body>
 <%session.invalidate();
  RequestDispatcher rd=request.getRequestDispatcher("/index.html");
  rd.forward(request, response);
  
  
 %>
</body>


</html>
