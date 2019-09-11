<?xml version="1.0" encoding="ISO-8859-1" ?>

	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1"  isELIgnored="false"/>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>		



 <c:set value="${sessionScope.users.name}" var="name" scope="page"  ></c:set> 
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>navigation</title>
    <link href="/css/nav.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" rel="script" src="/js/nav.js"></script>
    <style>
        
        #head{
            padding: 5px;
            margin-left: 10%;
            border-radius: 2px 45px 3px;
            background-color:navajowhite;
        }
        #headJbSrch input{
            width: 200px;
            height: 30px;
        }
        #headJbSrch input:hover{
            box-shadow: #dddddd 5px 5px 5px;
        }
        
        #headJbSrch button{
            width: 200px;
            height: 30px;
            background-color: slateblue;
            color: ghostwhite;
            font-size: larger;
            box-shadow: inset 3px 3px #dddddd;
            border-radius: 3px 33px 2px;
        }
        </style>
</head>
<body id="nav">
<div id="nav0">
    <div id="nav01">
        <ul>
            <a href="/util/home"><li>home</li></a>
            <a href="/contactus.jsp"><li>contact us</li></a>
            <a href="/jobs/createJsp"><li>update profile</li></a>
            <li onclick="showEle('nav01ul2')" ondblclick="hideEle ('nav01ul2')">jobs <br>
              <ul id="nav01ul2" onmouseleave="hideEle('nav01ul2')">
                  <a href="/job/jobsLoc"><li>jobs by city</li></a>
                  <a href="#"><li>jobs by location</li></a>
                  <a href="#"><li>jobs by skills</li></a>
                  <a href="#"><li>jobs by company</li></a>
                  <a href="/jobs/allJobs"><li>all jobs</li></a>
              </ul>
            </li>
        </ul>
    </div>
    <div id="nav02">
        <ul>
            <a href="/authen/logout"><li>logout</li></a>
            <a href="#" ><li><c:if test="${!empty sessionScope.users}">
                              <c:out value="${sessionScope.users.name}"></c:out>
                              <%-- <c:out value="${sessionScope.sesId }"></c:out> --%>
                            </c:if>
            </li></a>
        </ul>
    </div>
</div>
<hr>
 <div id="nav1">
<div id="head">
    <form id="headJbSrch" action="#" method="post">
        <input type="text" name="skill" placeholder="skill"/><input type="text" name="city" placeholder="city"/>
        <button type="submit">search</button>
    </form>
</div>
 </div>

</html>
