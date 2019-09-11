<?xml version="1.0" encoding="ISO-8859-1" ?>

	<%@page import="com.nt.util.JobTypeComparator"%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collections"%>
<%@page import="com.nt.entity.Job.JobType"%>
<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" import="com.nt.entity.Job"/>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f" %>


<head>
<title>Insert title here</title>


    <style>

        #jobPost1{
            margin:5%;
            padding: 10px;
            width: 25%;

        }

        #jobPost1Tb{
            background-color:beige;
        }

        #jobPost1Btn{
            font-family: "Arial Black";
            width: 100%;
            font-size: larger;
            color:aqua;
            background-color: darkblue;
            border-radius: 10px;

        }


    </style>

</head>
<body>
<% JobType[] types=JobType.values();
   List<JobType> list=Arrays.asList(types);
   TreeSet<JobType> treeSet=new TreeSet(new JobTypeComparator());
   treeSet.addAll(list);
   pageContext.setAttribute("jobTypes",treeSet);
%>


<c:out value="${returnMsg}"></c:out>






<div id="jobPost1">
    <form  action="/empr/form1Processor" method="post" id="jobPost1f" >


        <table id="jobPost1Tb" cellpadding="10">

            <tr >
                <td><i>select job type</i></td><td>
                <SELECT name="jobType">
                <c:forEach var="type" items="${jobTypes}">
                 <OPTION  value="${type}"> 
                  <c:out value="${type}"></c:out>
                 </OPTION>
               </c:forEach> 
              </SELECT>
                </td>
            </tr>
            
            <tr>
               <td><i>job title**</i></td><td><INPUT type="text" name="jobTitle" required="required"/></td>
            </tr>
            <tr>
               <td><i>number of openings/seats**</i></td><td><INPUT type="number" name="numOfSeats" required="required"/></td>
            </tr>
            <tr>
                <td><i>minimum salary [LPA]</i></td><td><INPUT type="number"  step="0.01" name="minSal" /></td>
            </tr>
            <tr>
                <td><i>maximum salary [LPA]</i></td><td><INPUT type="number"  step="0.01"  name="maxSal"/></td>
            </tr>
            <tr>
                <td><i>required experience [in years]</i></td><td><input type="number" step="0.01" name="minExp" placeholder="min" >-<input type="number" step="0.01"  name="maxExp" placeholder="max"></td>
            </tr>

            <tr><td colspan="3" align="center"><button id="jobPost1Btn" type="submit">next</button></td></tr>


        </table>
        <br clear="all">
    </form>
    <br clear="all">

    <a href="/index.html"> home</a>
</div>
<!--jobPost1 div end-->








</body>
</html>
