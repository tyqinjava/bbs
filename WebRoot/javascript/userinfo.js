window.onload=function(){
	
	
	var button=getElement(".changeButton"); //为控制按钮添加单击事件
	for(var i=0;i<button.length;i++){
		button[i].onclick=control;
	}
	var showImageDialog1=getElement(".show_image")[0];//更改头像的大的对话框
	var showImageDialog2=getElement(".show_image")[1];//上传头像的大对话框
	var imageDialog1=getElement(".imageDialog")[0];   //更改头像的小的对话框
	var imageDialog2=getElement(".imageDialog")[1];   //上传头像的小对话框
	postionImageDialog();
	var close1=getElement("#close1");//更改头像的小的对话框的关闭按钮
	var close2=getElement("#close2");//上传头像的小的对话框的关闭按钮
	
	//控制对话框的关闭
	close1.onclick=function(){
		showImageDialog1.style.display="none";
	}
	close2.onclick=function(){
		showImageDialog2.style.display="none";
	}
	
	var tb=getElement("table")[0];
	var imageList=tb.getElementsByTagName("img");
	for(var i=0;i<imageList.length;i++){
		imageList[i].onclick=changeIcon;
	}
	/*alert(imageList[0].getAttribute("src"));*/
	
	
	
	//控制上传图片
	var loadImage=getElement(".loadImage")[0];
	var ok=getElement(".uploadIconControl")[0];
	var cancel=getElement(".uploadIconControl")[1];
	var inputFile=getElement(".inputfile")[0];
	
	inputFile.onchange=function(){
		var filter=[".png",".gif",".jpg",".bmp",".jpeg",".ico"];
		var filepath=this.value;
		var isReady=false;
		for(var i=0;i<filter.length;i++){
			if((filepath.indexOf(filter[i]))==-1){
				continue;
			}else{
				isReady=true;
				break;
			}
		}
		if(!isReady){
			alert("格式不支持！");
			return;
		}
		var fm=document.forms["uploadiconForm"];
		var formData=new FormData(fm);
		var xhr=new XMLHttpRequest();
		xhr.open("POST","/bbs/imageupload",true);
		xhr.send(formData);
		var url="";
		xhr.onload=function(){
			if(xhr.readyState==4&&xhr.status==200){
				var json=xhr.responseText;
				if(json=="")return;
				var data=window.eval("("+json+")");
				var state=data["state"];
				url=data["url"];
				if(state=="SUCCESS"){
					if(loadImage.childNodes[0]==undefined){
				       var img=document.createElement("img");
				       img.src="/bbs/images/"+url;
		               img.style.width="80px";
				       img.style.height="80px"
				       loadImage.append(img);
				     }else{
				    	 var img=loadImage.childNodes[0];
				    	 img.src="/bbs/images/"+url;
				     }
				}else{
				    alert("图片上传失败!");
				}
			}
		}
		ok.onclick=function(){
			if(url=="")return;
		    var upurl="/bbs/changeicon";
			var xhr=new　XMLHttpRequest();
			var param="iconname="+url;
			xhr.open("post", upurl,true);
			xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			xhr.send(param);
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4&&xhr.status==200){
					var json=xhr.responseText;
					if(json=="")return;
					var data=window.eval("("+json+")");
					if(data["state"]==0){
						alert("头像更改成功！");
						showImageDialog1.style.display="none";
						window.location=location;
					}else if(data["state"]==1){//session过期
						alert("登录过期！");
						window.location.href="/bbs/index.jsp";
					}
				}
			}
		}
	}
	cancel.onclick=function(){
		showImageDialog2.style.display="none";
	}
	
	
	var inputData=getElement(".input_data");
	var changeUsername=inputData[0];
	var changePasswordOld=inputData[1];
	var changePasswordNew=inputData[2];
	var changeNickName=inputData[3];
	var changeSexM=inputData[4];
	var changeSexW=inputData[5];
	var changeDateYear=inputData[6];
	var changeDateMouth=inputData[7];
	var changeDateDay=inputData[8];
	var changeEmail=inputData[9];
	var submit=getElement(".submit"); //提交按钮
	
	for(var i=0;i<submit.length;i++){
		submit[i].onclick=mySubmit;
	}
	function mySubmit(){
		var Index=this.getAttribute("_sub");
		
		if(Index=="1"){ //提交用户名
			if(changeUsername.value=="")return;
			var match=/^[\w]{6,12}$/g;
			if(!match.test(changeUsername.value)){
				alert("用户名必须是数字，字母，下划线组成，长度为6-12位");
			}else{
				var param="type=changeusername&value="+changeUsername.value;
				sendMsg(param,Index);
			}
		}else if(Index=="2"){//提交密码
			var old=changePasswordOld.value;
			var New=changePasswordNew.value;
			var match=/^[\w]{6,8}$/;
			if(!match.test(changePasswordNew.value)){
				alert("密码必须由6-8字母，数字，下划线组成");
			}else{
				var str=old+","+New;
				var param="type=changepassword&value="+str;
				sendMsg(param,Index);
			}
		}else if(Index=="3"){//提交昵称
			var match=/^[\w\u4e00-\u9fa5]{5,25}$/g;
			if(!match.test(changeNickName.value)){
				alert("昵称必须由中文字符，字母，数字组成，长度为5-25位");
			}else{
				var param="type=changenickname&value="+changeNickName.value;
				sendMsg(param,Index);
			}
		}else if(Index=="4"){//提交性别
			if(!changeSexM.checked&&!changeSexW.checked){
				alert("你还没有选择！");
				return;
			}
			var value="";
		    if(changeSexM.checked){
		    	value="男";
		    }else if(changeSexW.checked){
		    	value="女";
		    }
            var param="type=setsex&value="+value;
            sendMsg(param,Index);
            
		}else if(Index=="5"){//提交出生日期
			var year=changeDateYear.value;
			var mouth=changeDateMouth.value;
			var day=changeDateDay.value;
			var format=year+"-"+mouth+"-"+day;
			var param="type=setbirthday&value="+format;
			sendMsg(param,Index);
		}else if(Index=="6"){//提交email
			var match=/^\w+@([0-9a-zA-Z]+[-0-9a-zA-Z]*)(\.[0-9a-zA-Z]+[-0-9a-zA-Z]*)+$/g;
			if(!match.test(changeEmail.value)){
				alert("请输入正确的邮箱");
			}else{
				var email=changeEmail.value;
				var param="type=changeemail&value="+email;
				sendMsg(param,Index);
			}
		}else {
			return;
		}
		function sendMsg(param,type){
		    var xhr=new XMLHttpRequest();
		    var url="/bbs/changefield";
			xhr.open("POST", url,true);
			xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			xhr.send(param);
			xhr.onreadystatechange=function(){
				if(xhr.readyState===4&&xhr.status==200){
					 var json=xhr.responseText;
					   if(json=="")return;
					   var data=window.eval("("+json+")");
					   switch(type){
					   case "1":
						   if(data["state"]==0){
							   	alert("用户名修改成功");
							   	changeUsername.value="";
							   	window.location=location;
						   }else if(data["state"]==1){
							    alert("登录过期");
							   window.location.href="/bbs/index.jsp";
						   }else {
							   alert("用户名被注册！");
						   }
						  break;
					   case "2":
						   if(data["state"]==0){
							   	alert("更改密码成功");
							   	window.location=location;
							   	changePasswordNew.value="";
							   	changePasswordOld.value="";
						   }else if(data["state"]==1){
							    alert("登录过期");
							   window.location.href="/bbs/index.jsp";
						   }else {
							   alert("密码错误！");
						   }
						   break;
					   case "3":
						   if(data["state"]==0){
							   	alert("昵称修改成功");
							   	window.location=location;
							   	changeNickName.value="";
						   }else if(data["state"]==1){
							    alert("登录过期");
							   window.location.href="/bbs/index.jsp";
						   }else {
							   alert("昵称修改失败！");
						   }
						   break;
					   case "4":
						   if(data["state"]==0){
							   	alert("设置性别成功");
							   	window.location=location;
						   }else if(data["state"]==1){
							    alert("登录过期");
							   window.location.href="/bbs/index.jsp";
						   }else {
							   alert("设置性别失败！");
						   }
						   break;
					   case "5":
						   if(data["state"]==0){
							   	alert("设置出生日期成功");
							   	window.location=location;
						   }else if(data["state"]==1){
							    alert("登录过期");
							   window.location.href="/bbs/index.jsp";
						   }else {
							   alert("设置出生日期失败！");
						   }
						   break;
					   case "6":
						   if(data["state"]==0){
							   	alert("设置邮箱成功");
							   	window.location=location;
							   	changeEmail.value="";
						   }else if(data["state"]==1){
							    alert("登录过期");
							   window.location.href="/bbs/index.jsp";
						   }else {
							   alert("设置邮箱失败！");
						   }
						   break;
						default:return;
					  }
				}
			}
		}
	}
	var hidden=getElement(".hidden");
	/*var inputText;*/
	
	/*alert(inputData.length);
	alert(changeDateYear);
	alert(changeNickName);
	alert(changeSexW);*/
	
	function changeIcon(){ //更改头像
		var str=this.getAttribute("src");
		var start=str.lastIndexOf("/");
		var iconName=str.substring(start+1,str.length);
	    var url="/bbs/changeicon";
		var xhr=new　XMLHttpRequest();
		var param="changeIcon=1&iconname="+iconName;
		xhr.open("post", url,true);
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xhr.send(param);
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4&&xhr.status==200){
				var json=xhr.responseText;
				if(json=="")return;
				var data=window.eval("("+json+")");
				if(data["state"]==0){
					alert("头像更改成功！");
					showImageDialog1.style.display="none";
					window.location=location;
				}else if(data["state"]==1){//session过期
					alert("登录过期！");
					window.location.href="/bbs/index.jsp";
				}
			}
		}
	}
	var isBlockOfUser=false;
	var isBlockOfPw=false;
	var isBlockOfNickName=false;
	var isBlockOfSex=false;
	var isBlockOfDate=false;
	var isBlockOfEmail=false;
	function control(){
		  var buttonIndex=this.getAttribute("_index");
		  if (buttonIndex=="")return;
		   if(buttonIndex=="1"){  //改变头像
			   showImageDialog1.style.display="block";
		   }else if(buttonIndex=="2"){ //上传头像
			   showImageDialog2.style.display="block";
		   }else if(buttonIndex=="3"){//更改用户名
			   if(isBlockOfUser){
				   hidden[0].style.display="none";
				   this.value="更改用户名";
				   isBlockOfUser=false;
			   }else{
			   hidden[0].style.display="block";
			   this.value="收起";
			   isBlockOfUser=true;
		       }
		   }else if(buttonIndex=="4"){//更改密码
			   if(isBlockOfPw){
				   hidden[1].style.display="none";
				   this.value="更改密码";
				   isBlockOfPw=false;
			   }else{
			   hidden[1].style.display="block";
			   this.value="收起";
			   isBlockOfPw=true;
		       }
		   }else if(buttonIndex=="5"){//更改昵称
			   if(isBlockOfNickName){
				   hidden[2].style.display="none";
				   this.value="更改昵称";
				   isBlockOfNickName=false;
			   }else{
			   hidden[2].style.display="block";
			   this.value="收起";
			   isBlockOfNickName=true;
		       }
		   }else if(buttonIndex=="6"){//设置性别
			   if(isBlockOfSex){
				   hidden[3].style.display="none";
				   this.value="设置性别";
				   isBlockOfSex=false;
			   }else{
			   hidden[3].style.display="block";
			   this.value="收起";
			   isBlockOfSex=true;
		       }
		   }else if(buttonIndex=="7"){//更改出生日期
			   if(isBlockOfDate){
				   hidden[4].style.display="none";
				   this.value="更改出生日期";
				   isBlockOfDate=false;
			   }else{
			   hidden[4].style.display="block";
			   this.value="收起";
			   isBlockOfDate=true;
		       }
		   }else if(buttonIndex="8"){//更改邮箱
			   if(isBlockOfEmail){
				   hidden[5].style.display="none";
				   this.value="更改邮箱";
				   isBlockOfEmail=false;
			   }else{
			   hidden[5].style.display="block";
			   this.value="收起";
			   isBlockOfEmail=true;
		       }
		   }else {
			   return;
		   }
	}
	function getElement(attr){
		if(typeof(attr)=="string"){
			if(attr.indexOf("#")==0){
			   attr=attr.slice(1);
			   return document.getElementById(attr);
			}else if(attr.indexOf(".")==0){
				attr=attr.slice(1);
				return document.getElementsByClassName(attr);
			}else{
				return document.getElementsByTagName(attr);
			}
			
		}else{
			return null;
		}
	}
	function postionImageDialog(){  //首先对图片对话框进行定位
		imageDialog1.style.left=window.screen.width/3+"px";
		imageDialog1.style.top=window.screen.height/3.5+"px";
		imageDialog2.style.left=window.screen.width/3+"px";
		imageDialog2.style.top=window.screen.height/3.5+"px";
	}
}