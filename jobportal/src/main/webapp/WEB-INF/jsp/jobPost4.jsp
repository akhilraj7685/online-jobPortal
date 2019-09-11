<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@page import="com.nt.entity.Job.JobType"%>
<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
pageEncoding="ISO-8859-1" import="com.nt.entity.Job"/>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
<title>Insert title here</title>


    <style>

        #jobPost4{
            margin:5%;
            padding: 10px;
            width: 25%;

        }

        #jobPost4Tb{
            background-color:beige;
        }

        #jobPost4Btn{
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


 <c:out value="${returnMsg}"></c:out>
 
 



<!--this div is for form-->
<div id="jobPost4">
    <form  action="/empr/form4Processor" method="post" id="jobPost4f" >


            <textarea rows="10" cols="50">

            </textarea>

            <button id="jobPost4Btn" type="submit">next</button>



        <br clear="all">
    </form>
    <br clear="all">

    <a href="/index.html"> home</a>
</div>
<!--jobPost4 div end-->





</body>
</html>
