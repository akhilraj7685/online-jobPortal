<?xml version="1.0" encoding="ISO-8859-1" ?>


<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1"  isELIgnored="false"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
    		



<c:set value="${sessionScope.name}" var="name" scope="page"  ></c:set>
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
    <div id="nav01" >
        <ul>
            <a href="/util/home"><li>home</li></a>
            <a href="/contactus.jsp"><li>contact us</li></a>
            <li onclick="showEle('nav01ul2')" ondblclick="hideEle ('nav01ul2')">jobs <br>
                <ul id="nav01ul2" onmouseleave="hideEle('nav01ul2')" >
                    <a href="/job/jobsLoc"><li>jobs by city</li></a>
                    <a href="#"><li>jobs by location</li></a>
                    <a href="/job/byJobTypes"><li>jobs by skills</li></a>
                    <a href="#"><li>jobs by company</li></a>
                    <a href="/jobs/allJobs"><li>all jobs</li></a>

                </ul>
            </li>
            <a href="/empr/createJob"><li>create job</li></a>
            <c:if test="${!sessionScope.users.verified }">
            <a><li>update phone number</li></a>
            </c:if>

        </ul>
    </div>


    <div id="nav02">
        <ul>
            <a href="/authen/logout"><li>logout</li></a>
            <a href="#" ><li onclick="showEle('nav02ul2')" ondblclick="hideEle ('nav02ul2')"><c:if test="${!empty sessionScope.users.name }">
                <c:out value="${sessionScope.users.name}"></c:out>
            </c:if></li>
            </a>
            <ul id="nav02ul2" onmouseleave="hideEle('nav02ul2')" hidden="hidden">
                <a href=""><li>my profile</li></a>
                <a href="/empr/postedJobs"><li>posted jobs</li>

                </a>
            </ul>
        </ul>
    </div>
</div>
<hr>

<div id="nav1">
  <div id="nav1">
     <div id="head">
    <form id="headJbSrch" action="#" method="post">
        <input type="text" name="skill" placeholder="skill"/><input type="text" name="city" placeholder="city"/>
        <button type="submit">search</button>
    </form>
</div>
 </div>
</div>

</body>
</html>
