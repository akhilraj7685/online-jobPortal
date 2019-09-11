<!-- view for public -->

<?xml version="1.0" encoding="ISO-8859-1" ?>
<html>
<%@page import="com.nt.entity.Job"%>
<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
pageEncoding="ISO-8859-1"  import="java.util.*"/>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"  %>

	
<head>
  <link href="/css/jobs.css" rel="stylesheet" type="text/css"/>

		<STYLE type="text/css">
		
		#div1 a{
		 color:buttonface;
		 border-radius:5px 5px;
		 text-decoration:none;
		 font-size=large;
		 background-color:maroon;
		 margin-left: 5px;
		 display: inline-block;
		}
		
		
		</STYLE>		

</head>
<BODY>





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

     
     
    <div id="job">
<div id="l" style="float:left;width:29%">
<b>hello</b>
<div>
    <h1>ttttesting</h1>
</div>
</div>







<div id="m" style="float:left;width:39%">
 <div id="div1" style="background-color: aquamarine;"> 
 
 <!-- set page related values into page scope variables for easy use -->
 <c:if test="${!empty requestScope.pageNumber }">
 <c:set var="pn" value="${requestScope.pageNumber}" scope="page"></c:set>
 </c:if>
  <c:if test="${!empty requestScope.totalJobs }">
 <c:set var="tj" value="${requestScope.totalJobs}" scope="page"></c:set>
 </c:if>
 <c:if test="${!empty requestScope.totalPages }">
 <c:set var="tp" value="${requestScope.totalPages}" scope="page"></c:set>
 </c:if>
 <c:if test="${!empty requestScope.pageSize }">
 <c:set var="ps" value="${requestScope.pageSize}" scope="page"></c:set>
 </c:if>

<c:out value="${(pn+1)*ps} - ${tj } "></c:out>

 <!-- set previous and next buttons -->
 <SPAN style="float:right;">
  <c:if test="${pn > 0 }">
   <a href="/jobs/allJobs/${pn-1}/${ps}"> previous </a>
 </c:if>
 <c:if test="${pn < tp-1 }">
   <a href="/jobs/allJobs/${pn+1}/${ps}"> next</a>
 </c:if>
</SPAN>
 
 
  
 </div>

     <!--for each job-->
     <c:forEach items="${requestScope.jobs}" var="job" >
     <a href = "<c:url value = "/jobs/jobDetail/${job.jobId}"/>">
    <div id="div2" style="padding: 5px; border-bottom: solid cadetblue 2px">
     <b style="text-align: center;margin-left:40%;font-size:25px">
        <c:if test="${!empty job.jobTitle }">
       <b><c:out value="${job.jobTitle }"></c:out></b><br>
    </c:if>
     </b></br>
     
     <i>company name</i><br>
     
     <!--job location-->
     <img src="/img/location.png" style="width:20px;height: auto">
     <i>
         <c:if test="${!empty job.locations}">
          location: <c:forEach  items="${job.locations}" var="loc">
          <c:out value="${loc.city}"></c:out> 
       </c:forEach> 
    </c:if>
     </i>
     
     <!--job skills -->
     <br clear="all"><img src="/img/Skill.png" style="width: 20px;height: auto ">
     <i>
         <c:forEach  items="${job.skills }" var="skill1">
          <c:out value="${skill1.skillName}"></c:out> 
       </c:forEach>
     </i>
     
     <!--experience required-->
     &nbsp;<img src="/img/exp.png" style="width: 20px;height: auto">
     <i>
           <c:if test="${!empty job.minExp }">
           <c:out value="${job.minExp} years"></c:out>
           </c:if>
    
           <c:if test="${!empty job.minExp && !empty job.maxExp }">
           <c:out value=" - "></c:out>
           </c:if>
           <c:if test="${!empty job.maxExp}">
           <c:out value="${job.maxExp} years"></c:out>
           </c:if>
     
     </i>

     <hr>
     <!--salary and posted time-->
    <br clear="all"> <img src="/img/salary.png" style="width: 20px;height: auto">&#8377;
          <c:if test="${!empty job.minSal}">
     		:&nbsp;&nbsp;<c:out value="${job.minSal}"></c:out>LPA 
    		</c:if>
    		<c:if test="${!empty job.minSal && !empty job.maxSal}">
		    <c:out value=" - "></c:out>
    		</c:if> 
    		<c:if test="${!empty job.maxSal}">
     		&nbsp;&nbsp;<c:out value="${job.maxSal}"></c:out>LPA 
    		</c:if> 
     <i style="float: right">posted by:ramesh

             <c:if test="${!empty job.postingDate}">
     <c:set scope="page" value="${job.postingDate }" var="pd"></c:set>
     
     <%  Date date=(Date)pageContext.getAttribute("pd");
         Calendar cal=Calendar.getInstance();
         Calendar cal0=Calendar.getInstance();
         cal.setTime(date);
         int mon=cal.get(Calendar.MONTH);
         int day=cal.get(Calendar.DAY_OF_MONTH);
         int monDiff=0;
         int dayDiff=0;
         String msg=null;
         //compare month
         if((monDiff=(cal0.get(Calendar.MONTH))-mon)==0){
        	 //compare day
        	 if((dayDiff=(cal0.get(Calendar.DAY_OF_MONTH))-day)==0)
                msg="today";
        	 else
        	  msg=dayDiff+"day ago";
         }//if
         else
        	msg=monDiff+"month ago"; 
        	//set message to page scope 
         pageContext.setAttribute("pd2",msg);
     %>
     &nbsp;&nbsp;<c:out value="${pd2}"></c:out>
    </c:if>
  
    </i>

    
 </div><!-- div2 end -->
    </a>
</c:forEach>



</div>






<div id="r" style="float:left;width:29%">

    <h3>right div</h3>

</div>







    </div>
</body>
</html>
     
     
     
     
     
     
     
     
     
     