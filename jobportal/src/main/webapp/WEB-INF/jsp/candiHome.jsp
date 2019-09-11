<!-- view for candidate -->
<!-- login required -->
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"  %>




<c:if test="${sessionScope.users.userType=='CANDIDATE'}">
 <%@include file="/candinav.jsp" %>
</c:if>

<c:out value="${sessionScope.users.userType }"></c:out>
<body>
<!-- verify user is logged-in or not -->
<c:if test="${sessionScope.sesId==null}">
   <h1>you are not logged in</h1>
   
  <c:redirect url="/authen/login0"></c:redirect>

</c:if>
<c:out value="${requestScope.msg}"></c:out>







</body>
</html>
