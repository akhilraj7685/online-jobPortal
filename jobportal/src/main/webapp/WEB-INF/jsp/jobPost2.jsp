<?xml version="1.0" encoding="ISO-8859-1" ?>

	<%@page import="com.nt.entity.Job.JobType"%>
<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" import="com.nt.entity.Job"/>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
<title>Insert title here</title>

<style>

        #jobPost2{
            margin:5%;
            padding: 10px;
            width: 25%;

        }

        #jobPost2Tb{
            background-color:beige;
        }

        #jobPost2Btn{
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
 
 
 <div id="jobPost2">
    <form  action="/empr/form2Processor" method="post" id="jobPost2f" >
        <table id="jobPost2Tb" cellpadding="10">

            <tr>
                <td><i>country</i></td><td><INPUT type="text" name="country" required="required" placeholder="INDIA" readonly value="INDIA"/></td>
            </tr>
            <tr>
                <td><i>state*</i></td><td><INPUT type="text" name="state" required="required"/></td>
            </tr>
            <tr>
                <td><i>city / distric* </i></td><td><INPUT type="text" name="city" required="required"/></td>
            </tr>
            <tr>
                <td><i>street address</i></td><td><INPUT type="text" name="streetAddr"/></td>
            </tr>
            <tr>
                <td><i>pincode</i></td><td><INPUT type="text" name="pin"/></td>
            </tr>
            <tr>
            <td>do you want to add more locations?</td>
            <td><INPUT  type="radio" value="yes" name="moreLoc"/> yes &nbsp;&nbsp;
                <INPUT  type="radio" value="no" name="moreLoc" checked="checked"/> no</td>
            </tr>
            <tr><td colspan="3" align="center"><button id="jobPost2Btn" type="submit">next</button></td></tr>


        </table>
        <br clear="all">
    </form>
    <br clear="all">

    <a href="/index.html"> home</a>
</div>
<!--jobPost2 div end-->
 


</body>
</html>
