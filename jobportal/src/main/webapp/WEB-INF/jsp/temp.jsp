<?xml version="1.0" encoding="ISO-8859-1" ?>

	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" session="false"/>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <style>

        #login{
            margin-left: 20%;
            padding: 10px;
            width: 25%;

        }

        #loginTb{
            background-color:beige;
        }

        #loginTbBtn{
            font-family: "Arial Black";
            width: 100%;
            font-size: larger;
            color:aqua;
            background-color: darkblue;
            border-radius: 10px;

        }


    </style>


</head>

<!-- navigation menue -->
<c:import url="/nav.html"></c:import>

<body>
<c:out value="${returnMsg }"></c:out>





<div id="login">
    <form  action="/authen/signup1" method="post" id="loginf" >
        <table id="loginTb" cellpadding="10">
            <th>
            <td><a href="#">login</a>  / <a href="/authen/signup0">signup</a></td>
            </th>
            <tr >
                <td><i>email</i></td><td><input type="email" name="email" required></td>
            </tr>

           
            <tr>
                <td><i>password</i></td><td><input type="password" name="password" required></td>
            </tr>

            <tr>
                <td><i>date of birth</i></td><td><input type="date" name="dob" required></td>
            </tr>

            <tr>
                <td>jobSeeker:<INPUT type="radio" name="userType" value="CANDIDATE" checked="checked"/></td>
                <TD >employer:<INPUT  type="radio" name="userType" value="EMPLOYER"/></TD>
            </tr>

            <tr><td colspan="2" align="center"><button id="loginTbBtn" type="submit">signup</button></td></tr>

        </table>
        <br clear="all">
    </form>
    <br clear="all">
    <i><a href="/util/login0">already registered ?&nbsp;&nbsp; Login</a></i><br><br>
    <a href="/index.html"> home</a>
</div>
<!--login div end-->


 
  
   
  
</body>
</html>
