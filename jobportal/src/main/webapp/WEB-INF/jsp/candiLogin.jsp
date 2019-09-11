<!-- view for public -->
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


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


<body>
<!-- navigation menue -->
 <%@include file="/nav.html" %>
 
<c:out value="${returnMsg }"></c:out>



 
    <div id="login">
    <form  action="/authen/login1" method="post" id="loginf" >
       <table id="loginTb" cellpadding="10">
           <th>job-seeker
               <td><a href="#">login</a>  / <a href="/authen/signup0">signup</a></td>
           </th>
           <tr >
               <td><i>email</i></td><td><input type="email" name="email" required></td>
           </tr>

           <tr>
               <td><INPUT type="text" readonly="readonly" name="userType" value="CANDIDATE" hidden="true"/></td>

           </tr>

           <tr>
               <td><i>password</i></td><td><input type="password" name="password" required></td>
           </tr>
           <tr><td colspan="2" align="center"><button id="loginTbBtn" type="submit">login</button></td></tr>

       </table>
       <br clear="all">
    </form>
        <br clear="all">
        <i><a href="/authen/pswdRecover">forgot password?</a></i><br><br>
        <a href="/index.html"> home</a>
    </div>
    <!--login div end-->



</body>


</html>







