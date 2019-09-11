<!--create private job-->
<!-- login required -->
<?xml version="1.0" encoding="ISO-8859-1" ?>

	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" />
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
	
<head>
<title>Insert title here</title>
</head>
<% HttpSession ses=(HttpSession)session.getAttribute("sesId");
   HttpSession ses2=session; 
%>
<c:if test="${ses!=ses2}">
   <h1>you are not logged in</h1>
  <c:redirect url="/rec/login"></c:redirect>

</c:if>

<body>

<FORM action="/emp/postJob2" method="post" enctype="application/x-www-form-urlencoded">

   create private  job::<br><br>
   
    job title::<INPUT  type="text" name="jobTitle"/><br>
    
   choose sector of your job :: <SELECT name="sector" >
    <OPTION value="it" selected="selected">it sector</OPTION>
    <OPTION value="banking">banking sector</OPTION>
    <OPTION value="animation">media and animation</OPTION>
   </SELECT>
  
   last date to apply::<INPUT type="date" name="lastDate"/>
   
   skills::
   <SELECT multiple="multiple" name="skill">
     <OPTGROUP label="java" >
      <OPTION value="core java">core java</OPTION>
      <OPTION value="adv java">adv java</OPTION>
      <OPTION value="spring">spring</OPTION>
      <OPTION value="spring mvc">spring mvc</OPTION>
      <OPTION value="hibernate">hibernate</OPTION>
      <OPTION value="restful ws">restful webservice</OPTION>
      <OPTION value="soap ws">soap based webservice</OPTION>
      <OPTION value="xml">xml</OPTION>
     </OPTGROUP> 
     
     <OPTGROUP label=".net" >
      <OPTION value="asp.net">asp.net</OPTION>
      <OPTION .net mvc>asp.net mvc</OPTION>
      <OPTION angularjs>angular js</OPTION>
     </OPTGROUP>
     
     <OPTGROUP label="php">
       <OPTION value="php">php</OPTION>
       <OPTION value="larvel">larvel</OPTION>
       <OPTION value="mysql">mysql</OPTION>
     </OPTGROUP>
   </SELECT>
   
   
   qualifications::
   
   <SELECT name="qualification">
     <OPTION value="bca">BCA</OPTION>
     <OPTION value="mca">MCA</OPTION>
     <OPTION value="btech">Btech</OPTION>
     <OPTION value="degree">degree in any stream</OPTION>
   </SELECT>
   
   
   
   minimum salary::
   <INPUT  type="number" min="2"  name="minSal"/>
   
   maximum  salary::
   <INPUT  type="number" min="2"  step="any" name="maxSal"/>
   
   
   job location::
  <SELECT name="location">
    <OPTION value="bihar">bihar</OPTION>
    <OPTION value="up">utter pradesh</OPTION>
    <OPTION value="delhi">delhi</OPTION>
    <OPTION value="kolkata">kolkata</OPTION>
    <OPTION value="mumbai">mumbai</OPTION>
  </SELECT>
   
   
  <TEXTAREA rows="10" cols="10" name="jobDetails"></TEXTAREA>
   
      <INPUT type="submit" value="create"/> 

</FORM>
</body>
<br><br><a href="/jobs/logout">logout</a>
<a href="/recHome">home</a>
</html>
