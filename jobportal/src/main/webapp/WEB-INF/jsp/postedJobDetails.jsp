
<html>
<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
pageEncoding="ISO-8859-1" import="java.util.*"/>
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>






 <!-- check usertype is employer or not -->

   <c:if test="${sessionScope.users.userType=='EMPLOYER'}">
 <%@include file="/recruiternav.jsp" %>
</c:if>
   <c:if test="${sessionScope.users.userType!='EMPLOYER' }">
     <c:out value="you are not logged in as employer. Please login as employer/recruiter"></c:out>
      <c:redirect url="/index.html"></c:redirect>
   </c:if>






 <head>
    <link href="/css/jobs.css" rel="stylesheet" type="text/css">


<style type="text/css">

#mdiv6A td{
         padding: 5px;
            color:black;
            font-family:SansSerif;


        }

        #mdiv6A tr{
            width: 100%;
        }
        #mdiv6A table{
            width: 98%;
            border-radius: 5px ;
            background-color:navajowhite;
        }

</style>

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

            
     </div>






          







 <c:set value="${requestScope.postedJobDetails}" scope="page" var="job"/>
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
                                 <i style="float: left">
                        <c:if test="${!empty job.numOfSeats}">
                        openings: <c:out value=" ${job.numOfSeats}"></c:out>
                        </c:if>
                 </i>
                 <button style="float:right;" onclick="showApplications('mdiv6')">
                  <c:out value="applications: ${fn:length(job.applications) }"></c:out>
                 </button>
             </span>
             </div>
             <br clear="all" >
             <button id="mdiv4vsbl" onclick="showDiv('mdiv4')" hidden="hidden">show hidden element</button>
             <div id="mdiv4">
                  <button onclick="hideDiv('mdiv4')">hide</button>
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
              </div>
              <!-- mdiv4 end -->
              
             <!--company description-->
             
             <br><br>
             <button id="mdiv5vsbl" onclick="showDiv('mdiv5')" hidden="hidden">show hidden element</button>
             <div id="mdiv5">
             <button onclick="hideDiv('mdiv5')">hide</button>
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
             <!-- mdiv5 end --> 

             
             
             
             <!-- div6  for all applicants -->
            <br>
            <div id="mdiv6" class="mdiv6" hidden="hidden">
                
                
                
                
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
                
                
                
                
                <!--for each applicant/jobseeker profile-->
                <div id="mdiv6A" style="background-color:aqua; margin-bottom: 50px;">
                <!-- for each jobSeeker  -->
                <c:forEach var="jsApp" items="${job.applications}">
                <c:set var="js" scope="page" value="${jsApp.jobSeeker }"></c:set>
               
        
                <b>job seeker profile:</b>
                     <br><br>
                <table align="center" border="1" style="text-align:left;">
                   <c:if test="${!empty js.expDtls.exp }">
                    <tr>
                        <td><b>exp:</b></td><td><b>
                                             <c:out value="${js.expDtls.exp }"></c:out> 
                                            </b></td>
                    </tr>
                    </c:if>
                    <c:if test="${!empty js.user.name }">
                    <tr>
                        <td><i>Name: </i></td><td><i>
                                               <c:out value="${js.user.name}"></c:out>
                                               </i></td>
                    </tr>
                    </c:if>
                    <c:if test="${!empty js.eduDtls.highestQuali }">
                    <tr>
                        <td><i>Highest qualification: </i></td><td><i>
                                                                 <c:out value="${js.eduDtls.highestQuali }"></c:out>  
                                                               </i></td>
                    </tr>
                    </c:if>
                    <c:if test="${!empty js.eduDtls.major }">
                    <tr>
                        <td><i>major</i></td><td><i>
                                               <c:out value="${js.eduDtls.major }"></c:out>
                                             </i></td>
                    </tr>
                    </c:if>
                    <c:if test="${!empty js.skills  }">
                    <tr>
                        <td><i>skills</i></td><td><i>
                                                   <c:forEach var="skill" items="${js.skills }">
                                                     <c:out value="${skill.skillName }  "></c:out>
                                                   </c:forEach>
                                                  </i></td>
                    </tr>
                    </c:if>
                    <c:if test="${!empty js.currLoc }">
                    <tr>
                        <td><i>current location</i></td><td><i>
                                                        <c:out value="${js.currLoc }"></c:out>
                                                       </i></td>
                    </tr>
                    </c:if>
                    <c:if test="${!empty js.prefLoc}">
                    <tr>
                        <td><i>preffered location</i></td><td><i>
                                                           <c:out value="${js.prefLoc}"></c:out>
                                                          </i></td>
                    </tr>
                    </c:if>
                    <c:if test="${!empty js.expDtls.company.cmpName }">
                    <tr>
                        <td><i>last job:</i></td><td><i>
                                                  <c:out value="${js.expDtls.company.cmpName }"></c:out>       
                                                  </i></td>
                    </tr>
                    </c:if>
                    <c:if test="${!empty js.expDtls.jobLoc.city }">
                    <tr>
                        <td><i>last designation:</i></td><td><i>
                                                         js.expDtls.jobLoc.city
                                                         </i></td>
                    </tr>
                    </c:if>
                    <c:if test="${!empty js.phn }">
                    <tr>
                        <td><b>phone/mobile</b></td><td><b>
                                                     <c:out value="${js.phn }"></c:out>
                                                     </b></td>
                    </tr>
                    </c:if>
                    <c:if test="${!empty js.user.email }">
                    <tr>
                        <td><b>email:</b></td><td><b>
                                                 <c:out value="${js.user.email}"></c:out>
                                               </b></td>
                    </tr>
                    </c:if>
                </table>
                </c:forEach>
                <br>
                </div>
                <!-- mdiv6A   end -->

                </div>
                <!-- mdiv6 end-->
               


            </div>
            <!-- m div end -->







        <div id="r">

            <h3>right div</h3>

        </div>







    </div>
    <!-- div job end -->
    
    
    
    
    
    
    
    <script>
        function hideDiv(ele) {

       document.getElementById(ele).style.display="none";
       var v=ele+"vsbl";
       document.getElementById(v).style.display="block";


        };

      function showDiv(ele) {
          document.getElementById(ele).style.display="block"
          var v=ele+"vsbl";
          document.getElementById(v).style.display="none";
      };



      function showApplications(mdiv6) {
        document.getElementById(mdiv6).style.display="block";
      };



    </script>
    
</body>
</html>