<!-- this view is only if country name is same all the time  -->
<?xml version="1.0" encoding="ISO-8859-1" ?>

<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
pageEncoding="ISO-8859-1" session="false"/>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>






<!-- add navigation bar -->
<!-- check usertype for setting header -->

    
     <c:choose>
        <c:when test="${empty sessionScope.users}">
         <%@include file="/nav.html" %>
        </c:when>
        
        <c:when test="${sessionScope.users.userType=='CANDIDATE'}">
         <%@include file="/candinav.jsp"%>
        </c:when>
        
        <c:when test="${sessionScope.users.userType=='EMPLOYER'}">
         <%@include file="/recruiternav.jsp"%>
        </c:when>
        
        
     </c:choose>

<head>
  
    <style>

    #ctl1 ul  li{
        padding: 50px;
        display: inline-block;

    }
    #ctl1 ul li a{
        text-decoration: none;
        color: maroon;
    }
    
   

    #ctl2 ul {
        margin:5px;
        background-color: aqua;
        list-style: none;
        float: left;
        padding: 50px;
        display: inline-block;
    }
 
    #ctl2 ul  li a{
        text-decoration: none;
        color: maroon;
    } 
    </style>

</head>
<body>

   
<div id="ctl"><!--list of cities-->
    <!--jobs by top cities-->
    <div id="ctl1" style="background-color:aqua; margin: 5%" >
    <ul>
        <li><a href="/job/jobsByLoc/delhi/delhi">jobs in Delhi</a></li>
        <li><a href="/job/jobsByLoc/maharashtra/mumbai">jobs in Mumbai</a></li>
        <li><a href="/job/jobsByLoc/maharashtra/pune">jobs in Pune</a></li>
        <li><a href="/job/jobsByLoc/karnataka/bangalore">jobs in Bangalore</a></li>
        <li><a href="/job/jobsByLoc/tamilnadu/chennai">jobs in Chennai</a></li>
        <li><a href="/job/jobsByLoc/punjab/gurugram">jobs in Gurugram</a></li>
        <li><a href="/job/jobsByLoc/west bengal/kolkata">jobs in Kolkata</a></li>
        <li><a href="/job/jobsByLoc/delhi/noida">jobs in Noida</a></li>
        <li><a href="/job/jobsByLoc/telangana/hyderabad">jobs in Hyderabad</a></li>
        <li><a href="/job/jobsByLoc/gujarat/ahmedabad">jobs in Ahmedabad</a></li>
    </ul>

    </div>
    <!--ctl1 div end-->
    <!--jobs by state-->
    <div id="ctl2" style="margin: 5%">
    
    <c:set var="preSta" scope="page"> </c:set>
    <c:set var="preCit" scope="page"></c:set>
    <c:set var="preCnt" scope="page"/>
    
    <ul>
    <c:forEach var="loc" items="${requestScope.loc}">
    
       
    
        <b>
        
        <%-- <c:out value="pre----${preSta }"></c:out> --%>
          
          <c:if test="${! fn:containsIgnoreCase(preSta,loc.state) }">
           </ul><ul> <c:out value="${loc.state }"></c:out>
           <c:set var="preSta" value="${loc.state}" scope="page"></c:set>
          </c:if>
        </b>
        <c:if test="${!fn:containsIgnoreCase(preCit,loc.city) }">
         <li><a href="/job/jobsByLoc/${loc.state}/${loc.city}">
 			<c:out value="${loc.city }"></c:out>
 			<c:set var="preCit" value="${loc.city }" scope="page"></c:set>
		</a></li>
		</c:if>
        
        

    
    </c:forEach>
    </ul>
     
    </div>
    <!--ctl2 div end-->
</div>
<!--ctl div end-->
     


</body>
</html>
