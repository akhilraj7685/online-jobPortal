<?xml version="1.0" encoding="ISO-8859-1" ?>

	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" session="false"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>navigation</title>
    <link href="nav.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" rel="script" src="nav.js"></script>
</head>
<body id="nav">
<div id="nav0">
    <div id="nav01">
        <ul>
            <a href="#"><li>home</li></a>
            <a href="#"><li>contact us</li></a>
            <li onclick="showEle('nav01ul2')" ondblclick="hideEle ('nav01ul2')">jobs <br>
              <ul id="nav01ul2" onmouseleave="hideEle('nav01ul2')">
                  <a href="#"><li>jobs by city</li></a>
                  <a href="#"><li>jobs by location</li></a>
                  <a href="#"><li>jobs by skills</li></a>
                  <a href="#"><li>jobs by company</li></a>
                  <a href="#"><li>all jobs</li></a>
              </ul>
            </li>
        </ul>
    </div>
    <div id="nav02">
        <ul>
            <a href="#"><li>login</li></a>
            <a href="#"><li>recruiter login</li></a>
        </ul>
    </div>
</div>
<hr>
 <div id="nav1">
     <b>new</b>
     <b2>new</b2>
 </div>

<hr>
<hr>

