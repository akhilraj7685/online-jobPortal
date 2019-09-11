<?xml version="1.0" encoding="ISO-8859-1" ?>

	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" session="false"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Insert title here</title>
</head>
<body>
<form action="/authen/update" method="POST">

  enter new password::<INPUT  type="password" name="password1" id="pswd1"/>
  enter password again::<INPUT  type="password" name="password2" id="pswd2" onkeyup="matchPassword()"/>
   <SPAN id="confirm"></SPAN>
    
    <INPUT type="submit" value="ok"/>
</form>
</body>

<SCRIPT type="text/javascript">

   function matchPassword(){
	   var pswd1=document.getElementById("pswd1").value;
	   var pswd2=document.getElementById("pswd2").value;
	   
	   if(pswd2!=pswd1){
		   document.getElementById("confirm").innerHTML="password not matched";
	   }//if
	   else{
		   document.getElementById("confirm").innerHTML="confirmed /n/nyour password changed successfully!!/n/n go to home page and login with your new password";
	   }
   }

</SCRIPT>
</html>
