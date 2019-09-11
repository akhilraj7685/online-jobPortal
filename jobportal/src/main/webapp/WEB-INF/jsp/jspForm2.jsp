<!-- jspForm2 means jobSeekerProfileForm2 -->
<?xml version="1.0" encoding="ISO-8859-1" ?>

<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
pageEncoding="ISO-8859-1" session="false"/>
<head>
    <style>

        #jspForm2{
            margin:5%;
            padding: 10px;
            width: 25%;

        }

        #jspForm2Tb{
            background-color:beige;
        }

        #jspForm2Btn{
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
<div id="jspForm2">
    
    <form  action="/jobs/jspForm2" method="post" id="jspForm2f" >
    <B>are you a fresher<INPUT type="radio" name="rdExp" value="fre" checked="checked"/>
        or experienced<INPUT type="radio" name="rdExp" value="exp" /></B>
        <table id="jspForm2Tb" cellpadding="10">


            <h3>Experience Details! don't fill this form if you are a fresher</h3>

                <THEAD><tr><TD><i> details of last job</i></TD></tr></THEAD>
                <tr>
                    <td><i>your last designation/position</i></td><td><INPUT type="text" name="desg" /></td>
                </tr>

                <tr>
                    <td><i>company name</i></td><td><INPUT type="text" name="cmpName" /></td>
                </tr>



                <tr>
                    <td><i>work experience in this company</i></td><td><input type="number" name="lexp" step="0.01"/></td>
                </tr>

                <tr>
                    <td><i>your total work experience</i></td><td><input type="number" name="exp" step="0.01"/></td>
                </tr>



                <tr>
                <td colspan="3" align="center"><button id="jspForm2Btn" type="submit">next</button></td>
            </tr>


        </table>
        <br clear="all">
    </form>
    <br clear="all">

    <a href="/index.html"> home</a>
</div>
<!--jspForm2 div end-->

</body>
</html>

