<?xml version="1.0" encoding="ISO-8859-1" ?>

	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/nav.html" %>

<head>
    <style>
        a{
            text-decoration: none;
        }
        #div1{
            width: 100%;
            height: 30%;
            background-color: mediumvioletred;
        }
        #div1 span{
           margin-left: 10%;
            padding: 10%;

        }
        #div1 span a{
            font-size: large;
            background-color:darkcyan;
            text-decoration: none;
            color:ghostwhite;
            margin-left: 50px;
            box-shadow: #dddddd 5px 5px 5px;

        }

        #div1A{
            display: inline-block;
            font-size: larger;
            float: right;
            margin: 5%;
        }
    </style>
</head>
<body >

<!--div1-->
<div id="div1">

    <span >
 <a href="/jobs/all">all jobs</a><a href="/job/byTypes/IT">IT jobs</a> <a href="/job/byTypes/goverment">govt jobs</a>
    </span>
    <a href="#" style="float: left;padding: 5px;margin-left: 10%;margin-top:10%;font-size:x-large;color:ghostwhite;font-style: italic;
     box-shadow: darkcyan 5px 10px 5px 10px ;border-radius: 50% 50% 25px 5px;
    ">now post your jobs free!!</a>
    <br clear="all">

    <div id="div1A">

        <b style="background-color: maroon;color: ghostwhite;border-radius: 5px;padding: 5px">register free!!</b>
              <br><br>

        <b style="padding: 5px;border: ridge 1px">sign in</b>
    </div>
</div>
<!--div1 end-->

</body>
</html>

