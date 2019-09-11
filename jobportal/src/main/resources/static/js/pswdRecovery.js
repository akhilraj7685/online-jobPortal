/**
 * 
 */

function checkMail(){
	
	var mail=document.getElementById('email').value;
	var result=document.getElementById('msg').innerHTML=mail;
	var url="/authen/checkMail/"+mail;
	 var req=new  XMLHttpRequest();
	
	 
	 req.onreadystatechange=function () {

		 if(req.readyState<4){
			 document.getElementById('msg').innerHTML="please wait!! <br> processing....";
		 }//if
		 
	        if(req.readyState==4){
	           
	        	var val=req.responseText;
	            
	            
	            document.getElementById('msg').innerHTML=mailRegistered(val);
	        }//if
	    };

	    req.open("POST",url,true);
	    req.send();
	
	
}



function mailRegistered(val){
	
	if(val.toString().includes("false")){
		var msg="this email not exists"
        return msg;
}
else if(val.toString().includes("true")){
	document.getElementById('help').style.display="none";
	var msg="verification link is sent to your email id";
	document.getElementById('success').innerHTML=msg;
	
	return msg;
};
	
}



