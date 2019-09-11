<?xml version="1.0" encoding="ISO-8859-1" ?>

	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" session="false"/>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">



<!-- navigation menue -->
<c:import url="/nav.html"></c:import>

<head>
 
    <style>

        #signup1{
            margin:5%;
            padding: 10px;
            width: 25%;

        }

        #signup1Tb{
            background-color:beige;
        }

        #signup1Btn{
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
<c:out value="${returnMsg }"></c:out>



 
 
<div id="signup1">
    <form  action="/authen/signup2" method="post" id="signup1f" >
        <table id="signup1Tb" cellpadding="10">

            <tr >
                <td><i>enter your name </i></td><td><input type="text" name="name" required></td>
            </tr>
            <tr>
                <td><INPUT type="radio" name="gender" value="m" checked/>male</td>
                <td><INPUT type="radio" name="gender" value="f" />female</td>
                <td><INPUT type="radio" name="gender" value="c"/>other</td>

            </tr>
            <tr><td colspan="3" align="center"><button id="signup1Btn" type="submit">o k</button></td></tr>

        </table>
        <br clear="all">
    </form>
    <br clear="all">

    <a href="/index.html"> home</a>
</div>
<!--login div end-->
 
  
</body>
</html>
