/**
 * 
 */

function email(){
    var rr=document.getElementById('test').value;
    var url="/authen/checkMail/"+rr;
    var req=new  XMLHttpRequest();
    req.onreadystatechange=function () {

        if(req.readyState==4){
            var val=req.responseText;
            
            document.getElementById('msg').innerHTML=myFun(val);
        }//if
    };

    req.open("GET",url,true);
    req.send();


   // alert(rr);
}


function myFun(val){
	
	alert(typeof val);
	return val.getAttribute('name');
	
}