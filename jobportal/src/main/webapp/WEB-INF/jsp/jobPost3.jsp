<?xml version="1.0" encoding="ISO-8859-1" ?>

	<%@page import="com.nt.entity.Job.JobType"%>
<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" import="com.nt.entity.Job"/>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
<title>Insert title here</title>



    <style>

        #jobPost3{
            margin:5%;
            padding: 10px;
            width: 25%;

        }

        #jobPost3Tb{
            background-color:beige;
        }

        #jobPost3Btn{
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
<div id="jobPost3">
    <form  action="/empr/form3Processor" method="post" id="jobPost3f" >
        <table id="jobPost3Tb" cellpadding="10">

            <tr>
                <td><i>skill1**</i></td><td><INPUT type="text" name="skill1" required="required"/></td>
            </tr>

            <tr>
                <td><i>skill2</i></td><td><INPUT type="text" name="skill2" /></td>
            </tr>
            <tr>
                <td><i>skill3</i></td><td><INPUT type="text" name="skill3" /></td>
            </tr>
            <tr>
                <td><i>skill4</i></td><td><INPUT type="text" name="skill4" /></td>
            </tr>
            <tr>
                <td><i>skill5</i></td><td><INPUT type="text" name="skill5" /></td>
            </tr>
            <tr>
                <td><i>skill6</i></td><td><INPUT type="text" name="skill6" /></td>
            </tr>
            <tr>
                <td><i>skill7</i></td><td><INPUT type="text" name="skill7" /></td>
            </tr>
            <tr>
                <td><i>skill18</i></td><td><INPUT type="text" name="skill8" /></td>
            </tr>
            <tr><td colspan="3" align="center"><button id="jobPost3Btn" type="submit">next</button></td></tr>


        </table>
        <br clear="all">
    </form>
    <br clear="all">

    <a href="/index.html"> home</a>
</div>
<!--jobPost3 div end-->
 

</body>
</html>
