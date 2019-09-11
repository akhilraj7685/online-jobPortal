<!-- view for recruiter -->
<!-- login required -->
<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
pageEncoding="ISO-8859-1" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<?xml version="1.0" encoding="ISO-8859-1" ?>


<body>
<!-- navigation  menu -->
<c:if test="${sessionScope.users.userType=='EMPLOYER'}">
 <%@include file="/recruiternav.jsp" %>
</c:if>


<%=session.getAttribute("sesId") %>

<% String sesId=(String)session.getAttribute("sesId");  %>

<c:if test="${sessionScope.sesId==null}">
   <h1>you are not logged in</h1>
   
  <c:redirect url="/authen/login"></c:redirect>

</c:if>

<c:out value="${msg }"></c:out>




</body>
</html>
