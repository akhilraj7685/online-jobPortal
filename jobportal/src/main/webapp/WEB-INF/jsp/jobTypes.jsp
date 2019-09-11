<?xml version="1.0" encoding="ISO-8859-1" ?>

<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
pageEncoding="ISO-8859-1" session="false" />
<%@ page import="com.nt.entity.Job.JobType" %>
<%@ page import="java.util.*" %>
<%@ page import="com.nt.util.JobTypeComparator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<head>
<title>Insert title here</title>
 <style>
        #jt{
            background-color: ghostwhite;
            border: 1px solid #dddddd;
            margin: 5%;
        }
        #jt ul{
            float: left;
            display: inline-block;
            margin-left: 5px;
        }

        #jt ul li{
            list-style: none;
            padding: 5px;
        }
        a{
            text-decoration: none;
        }

    </style>
 </head>
 
 
 
 
 
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
 
 
 
 
 
<body>

<c:if test="${!empty requestScope.jobTypes }">
 <c:forEach var="type" items="${requestScope.jobTypes }">
     <c:out value="${type }"></c:out><br>
 </c:forEach>

</c:if>
 
 <% JobType[] types=JobType.values();
   List<JobType> list=Arrays.asList(types);
   TreeSet<JobType> treeSet=new TreeSet(new JobTypeComparator());
   treeSet.addAll(list);
   pageContext.setAttribute("jobTypes",treeSet);
%>



<!--jobTyp<!--jobType div-->
<c:set var="count" scope="page" value="0"></c:set>
<div id="jt">
<UL>
<c:forEach var="jt" items="${jobTypes}">
 
    
    <c:choose>
    <c:when test="${count %10==0 }">
        </ul><ul><li><a href="byJobType/${jt}"><c:out value="${jt }"></c:out></a></li>
        </c:when>
        <c:otherwise>
        <li><a href="byJobType/${jt}"><c:out value="${jt}"></c:out></a></li>
        </c:otherwise>
        </c:choose>
       
    

    
    <c:set var="count" scope="page" value="${count+1 }"></c:set>
</c:forEach>
</ul>
<br clear="all">
</div>
<!-- jt div end-->


</body>
</html>
