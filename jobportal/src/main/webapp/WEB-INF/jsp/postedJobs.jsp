<!-- view for public -->

<?xml version="1.0" encoding="ISO-8859-1" ?>
<html>
<%@page import="com.nt.entity.Job"%>
<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
pageEncoding="ISO-8859-1"  import="java.util.*"/>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>

	
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





 <!-- check usertype is employer or not -->

   <c:if test="${sessionScope.users.userType=='EMPLOYER'}">
 <%@include file="/recruiternav.jsp" %>
</c:if>
   <c:if test="${sessionScope.users.userType!='EMPLOYER' }">
     <c:out value="you are not logged in as employer. Please login as employer/recruiter"></c:out>
      <c:redirect url="/index.html"></c:redirect>
   </c:if>

     
     
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
     <c:forEach items="${requestScope.postedJobs}" var="job" >
     <a href = "<c:url value = "/empr/jobDetails/${job.jobId}"/>">
    <div id="div2" style="padding: 5px; margin-bottom:15px; background-color:ghostwhite;
     box-shadow: bisque 5px 5px;">
     <b style="text-align: center;margin-left:40%;font-size:25px">
        <c:if test="${!empty job.jobTitle }">
       <b><c:out value="${job.jobTitle }"></c:out></b><br>
    </c:if>
     </b></br>
     
     <i>
      <c:if test="${!empty job.company.cmpName }">
            <c:out value="${job.company.cmpName }"></c:out> 
      </c:if>
     </i><br>
     
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
    <br clear="all"> <img src="/img/salary.png" style="width: 20px;height: auto"> &#8377;
          <c:if test="${!empty job.minSal}">
     		:&nbsp;&nbsp;<c:out value="${job.minSal}"></c:out>LPA 
    		</c:if>
    		<c:if test="${!empty job.minSal && !empty job.maxSal}">
		    <c:out value=" - "></c:out>
    		</c:if> 
    		<c:if test="${!empty job.maxSal}">
     		&nbsp;&nbsp;<c:out value="${job.maxSal}"></c:out>LPA 
    		</c:if> 
    
     <i style="float: right">
     <c:if test="${!empty job.user.name }">
           posted by:<c:out value="${job.user.name }"></c:out>&nbsp;&nbsp;&nbsp;
     </c:if>


             <c:if test="${!empty job.postingDate}">
     <c:set scope="page" value="${job.postingDate }" var="pd"></c:set>
     </c:if>
     <!-- clt->current time in long,plt-> job posting time in long, dlt->difference long time -->
     <c:set var="plt" value="${job.postingDate.time}"></c:set>
     <c:set var="clt" value="<%=new Date().getTime() %>"></c:set>
     <c:set var="dlt" value="${clt-plt }"></c:set>
     <b>posted: </b>
     <c:choose>
        <c:when test="${dlt<(1000*60)}">
                <c:out value="${fn:substringBefore(Math.floor(dlt/(1000.0)),'.') }seconds ago"></c:out> 
        </c:when>
                <c:when test="${dlt<(1000*60*60)}">
                <c:out value="${fn:substringBefore(Math.floor(dlt/(1000*60) ),'.')}minutes ago"></c:out> 
        </c:when>
                <c:when test="${dlt<(1000*60*60*24)}">
                <c:out value="${fn:substringBefore(Math.floor(dlt/(1000*60*60)),'.') }hours ago"></c:out> 
        </c:when>
        <c:otherwise>
                <c:out value="${fn:substringBefore(Math.floor(dlt/(60*60*60*24)),'.')}days ago "></c:out>
        </c:otherwise>
     
     
     
     </c:choose>
     
     
     <br clear="all"><br><span style="margin-bottom: ">

                 <i style="margin-left:0%;float: left">
                        openings: 222
                 </i>
                 <i style="float:right;margin-left: 20%">applicants:366</i>
             </span>
              </i>
     <br clear="all" ><br>
     <hr>
    

    
 </div><!-- div2 end -->
    </a>
</c:forEach>



</div>
<!-- divm end -->






<div id="r" style="float:left;width:29%">

    <h3>right div</h3>

</div>







    </div>
</body>
</html>
     
     
     
     
     
     
     
     
     
     