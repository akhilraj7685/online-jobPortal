<?xml version="1.0" encoding="ISO-8859-1" ?>

	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" session="false"/>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<head>
<title>Insert title here</title>
</head>
<body>

<c:out value="${errMsg }"></c:out>

<%=request.getAttribute("error") %> 
<%=request.getAttribute("returnMsg") %>




<H1>SORRY !!somthing went wrong! </H1>
<h1>go to home page and try again.</h1>
</body>
<br><br><a href="/util/home">home</a>
</html>
