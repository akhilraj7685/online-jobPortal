<?xml version="1.0" encoding="ISO-8859-1" ?>

	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" session="false"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Insert title here</title>
<script src="/js/pswdRecovery.js" type="text/javascript" ></script>

<style>
    #help{
        background-color:navajowhite;
        padding: 10px;
        display: block;
        width: 30%;
        height:200px;
        margin-left: 30%;
        margin-top: 15%;
        box-shadow: darkred 5px 5px ;
    }

#box{
    width: 80%;
    height: 20px;
    border-radius: 5px;
    font-size: large;
}

 #msg{
     color:maroon;
     font-family:Arial
 }
</style>
</head>
<body>
   <div id="help">
 enter your email:: <INPUT  type="text" name="email" required="required" id="email" onkeyup="checkMail()"/>
   
   <span id="msg">
     
   </span>
  
   </div> 
   <span id="success"></span>    
    <a href="/index.html">back to home</a>  


</body>
</html>