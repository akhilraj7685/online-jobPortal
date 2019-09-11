<!-- jspForm1 means =jobSeekerProfile form1 -->

<?xml version="1.0" encoding="ISO-8859-1" ?>

<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
pageEncoding="ISO-8859-1" session="false"/>
<head>

    <style>

        #jspForm1{
            margin:5%;
            padding: 10px;
            width: 25%;

        }

        #jspForm1Tb{
            background-color:beige;
        }

        #jspForm1Btn{
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
<!--this div is for form-->
<div id="jspForm1">
    <form  action="/jobs/jspForm1" method="post" id="jspForm1f" >
        <table id="jspForm1Tb" cellpadding="10">


                <THEAD><td>education details</td></THEAD>
                <tr>
                    <td><i>highest qualification*</i></td><TD><INPUT type="text" name="hQuli" required="required"/></TD>
                </tr>

                <tr>
                    <td><i>major subject/stream</i></td><td><input type="text" name="major"> </td>
                </tr>

                <TR >
                    <TD><i>duration of this cource</i></TD><td>from<INPUT type="date" name="startDate"/><br>
                    to <INPUT  type="date" name="endDate"/>
                </td>
                </TR>

                <tr>
                    <TD><i>institute name</i></TD> <td><input type="text" name="institute"></td>
                </tr>

                <tr>
                    <td><i>marks (% or cgpa)*</i></td><td><INPUT type="number" name="marks" step="0.01" required="required"/></td>
                </tr>
                <tr>
                 <td colspan="3" align="center"><button id="jspForm1Btn" type="submit">next</button></td>
                </tr>


        </table>
        <br clear="all">
    </form>
    <br clear="all">

    <a href="/index.html"> home</a>
</div>
<!--jspForm1 div end-->
</body>
</html>
