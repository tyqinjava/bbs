window.onload = function() {
	
	
    window.onresize=function(){
    	var width = window.innerWidth;
    	var height = window.innerHeight;
    	content.style.top = (height-282)/2 + "px"; + "px";
    	content.style.left = width / 7 + "px";
	}
    var width = window.innerWidth;
	var height = window.innerHeight;
	var content = document.getElementById("content");
	content.style.top = (height-282)/2 + "px";
    content.style.left = width / 7 + "px";
	document.body.style.width = window.screen.width + "px";
	document.body.style.height = window.screen.height + "px";
	
	
	
	
	/*处理用用户请求
	 */
	
	var msg=document.getElementsByClassName("msg");
	var confirm=document.getElementsByClassName("content_input"); //input
	confirm[0].onfocus=function(){
		msg[0].innerHTML="";
	}
	confirm[1].onfocus=function(){
		msg[1].innerHTML="";
	}
	var submitBtn=document.getElementsByClassName("button_one")[0]; //提交
	window.onkeydown=function(){
		if(event.keyCode==13){
			submitBtn.onclick();
		}
	}
	submitBtn.onclick=function(){
		var username=confirm[0].value;
		var password=confirm[1].value;
		if(username==""||password=="")return;
		var xhr=new XMLHttpRequest();
		var url="/bbs/login";
		var param="username="+username+"&password="+password;
		xhr.open("post", url,true);
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xhr.send(param);
		xhr.onreadystatechange=function(){
			if(this.readyState===4&&this.status==200){
				if(this.responseText=="")return;
				var data=window.eval("("+this.responseText+")");
				if(data["state"]==0){//等于0成功
					msg[0].innerHTML="";
					msg[1].innerHTML="";
					window.location.href="/bbs/jsp/main.jsp";
				}else if(data["state"]==1){
				    msg[0].innerHTML="账号不存在";
				}else{
					msg[1].innerHTML="密码错误";
				}
			}
		}
	}
}