
<html>
<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
pageEncoding="ISO-8859-1" import="java.util.*"/>
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>







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
    <link href="/css/jobs.css" rel="stylesheet" type="text/css">
</head>














<body>
    <div id="job">
        <div id="l">
            <b>hello</b>
            <div>
                <h1>ttttesting</h1>
            </div>
        </div>





<!--inside m div one div will show basic overview of job same as on previous page
next div will show detailed descriptions and apply link
-->

        <div id="m">
            <div id="mdiv1" style="background-color: aquamarine;"> for sorting data and utility purpose
            </div>


 <c:set value="${requestScope.jobDto}" scope="page" var="job"/>
  <!--for each job-->
     
     
       <a href = "<c:url value = "/jobs/jobDetail/${job.jobId}"/>">
       <div id="div2" style="padding: 5px; border-bottom: solid cadetblue 2px">
      
       <b style="text-align: center;margin-left:40%;font-size:25px">
         <c:if test="${!empty job.jobTitle }">
         <c:out value="${job.jobTitle }"></c:out><br>
       </c:if>
       </b>
       </br>
     
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
          <c:out value="${loc.city}  "></c:out> 
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






            <!--mdiv3  it will describe the job in details-->
            <div id="mdiv3" style="padding: 5px">
            <!-- applybutton job applicants, openings all in same line-->
             <span>
                 <a href="/jobs/applyForJob/${job.jobId}"><button type="submit" name="apply" id="mapply">apply</button></a>
                 <i style="margin-left: 20%;float: left">
                        <c:if test="${!empty job.numOfSeats}">
                        openings: <c:out value=" ${job.numOfSeats}"></c:out>
                        </c:if>
                 </i>
                 <i style="float: left;margin-left: 20%">applicants:366</i>
             </span>
             <br clear="all" >
             <i>
                       <c:if test="${!empty job.jobDescription}">
                       <c:set var="desc" value="${fn:split(job.jobDescription, Character.valueOf(10))}"></c:set>
                      <br><b>description: </b><br> <c:forEach var="line" items="${desc}">
                      <br><c:out value="${line }"></c:out>
                       </c:forEach> 
                       </c:if>
             </i>
             <br><br>
             <!--salary-->
             <table>
                 <tr>
                     <td><i>salary:</i></td><td><i>
                                                <c:if test="${!empty job.minSal}">
                                                 INR<c:out value=" ${job.minSal} "></c:out>LPA 
                                                  </c:if>
    
    
                                                   <c:if test="${!empty job.minSal && !empty job.maxSal}">
                                                   <c:out value=" - "></c:out>
                                                   </c:if> 
    
    
                                                   <c:if test="${!empty job.maxSal}">
                                                   <c:out value=" ${job.maxSal}"></c:out>LPA 
                                                   </c:if>    
                                                 
                                                </i>
                                            </td>
                 </tr>
                 <tr>
                    
                  <c:if test="${!empty job.company.industryType }"></c:if>
                        <td><i>industry type</i></td>
                        <td><i>
                        <c:out value="${job.company.industryType}"></c:out>
                        </i>
                        </td>                                 
                 </tr>
                 
                 <tr>
                    <c:if test="${!empty job.jobType }">
                          <td><i>role:</i></td>   
                          <td><i><c:out value="${job.jobType}"></c:out></i></td>
                    </c:if>
                 </tr>
                 
                 <tr>
                    <c:if test="${!empty job.employmentType }">
                          <td><i>employment type</i></td> 
                          <td><i><c:out value="${job.employmentType}"></c:out></i></td>  
                    </c:if>
                 </tr>

             </table>

             <!--company description-->
             <br><br>
             <b>
                 company profile: <br><br>
                 <c:if test="${!empty job.company.cmpName }">
                 <c:out value="${job.company.cmpName }"></c:out>
                 </c:if>
             </b><br>
             <i>
                  <c:if test="${!empty job.company.cmpDescription }">
                  <c:out value="${job.company.cmpDescription }"></c:out>
                  </c:if>
             
             </i>





            </div>



        </div>






        <div id="r">

            <h3>right div</h3>

        </div>







    </div>
</body>
</html>