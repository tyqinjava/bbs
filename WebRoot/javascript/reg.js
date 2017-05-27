window.onload=function(){	
			var email=getElement(".form_input")[0];
			var username=getElement(".form_input")[1];
			var nickname=getElement(".form_input")[2];
			var password=getElement(".form_input")[3];
			var confirm=getElement(".form_input")[4];
			var hidden_div=getElement(".form_hidden");
			var again=document.getElementsByClassName("again")[0];
			var verimg=document.getElementsByClassName("verimg")[0];
			var vcmsg=document.getElementsByClassName("vcmsg")[0];
			var checkEmail=false;
			var checkUserName=false;
			var checkNickName=false;
			var checkPassword=false;
			var checkConfirm=false;
			var checkVerCode=false;
			var passwordAgain="";
			var vercode=document.getElementsByClassName("ident")[0];
			again.onclick=function(){
				verimg.src="/bbs/vercode?"+Math.random();//防止缓存图片
			}
			vercode.onchange=function(){
				var xhr=new XMLHttpRequest();
				var url="/bbs/regsiter";
				var param="vercode="+this.value;
				xhr.open("post", url,true);
				xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
				xhr.send(param);
				xhr.onreadystatechange=function(){
					   if(xhr.readyState==4&&xhr.status===200){
						   var json=xhr.responseText;
						   if(json=="")return;
						   var data=window.eval("("+json+")");
						   if(data["state"]==0){
							   vcmsg.innerHTML="";
								checkVerCode=true;
						   }else{
							   vcmsg.innerHTML="验证码输入错误！";
						   }
					   }
				}
			}
			var myForm=getElement("#submit");
			myForm.onsubmit=function(){
				var isRead=getElement(".read")[0].checked;
				if(checkEmail&&checkUserName&&checkNickName&&checkPassword&&checkConfirm&&isRead&&checkVerCode){
					alert("注册成功！");
					this.submit();
				}else{
					return false;
				}
			}
			email.onchange=function(){
				var match=/^\w+@([0-9a-zA-Z]+[-0-9a-zA-Z]*)(\.[0-9a-zA-Z]+[-0-9a-zA-Z]*)+$/g;
				if(!match.test(this.value)){
					hidden_div[0].innerHTML="请输入正确的邮箱";
				}else{
					hidden_div[0].innerHTML="";
					checkEmail=true;
				}
			}
			username.onchange=function(){
				var match=/^[\w]{6,12}$/g;
				if(!match.test(this.value)){
					hidden_div[1].innerHTML="用户名必须是数字，字母，下划线组成，长度为6-12位";
				}else{
					var xhr=new XMLHttpRequest();
					var url="/bbs/regsiter";
					var param="username="+this.value;
					xhr.open("post", url,true);
					xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
					xhr.send(param);
					/*
					 * {
					 *  state:0 成功
					 *  state:1 失败
					 * }
					 */
					xhr.onreadystatechange=function(){
						   if(xhr.readyState==4&&xhr.status===200){
							   var json=xhr.responseText;
							   if(json=="")return;
							   var data=window.eval("("+json+")");
							   if(data["state"]==0){
								    hidden_div[1].innerHTML="";
									checkUserName=true;
							   }else if(data['state']==1){
								   hidden_div[1].innerHTML="用户名已被注册了！";
							   }
						   }
					}
				}
				
			}
			nickname.onchange=function(){
				/*5-25个字符*/
				/*\u4e00-\u9fa5表示中文unicode*/
				var match=/^[\w\u4e00-\u9fa5]{4,25}$/g
				if(!match.test(this.value)){
					hidden_div[2].innerHTML="昵称必须由中文字符，字母，数字组成，长度为5-25位";
				}else{ 0
					hidden_div[2].innerHTML="";
					checkNickName=true;
				}
			}
			password.onchange=function(){
				var match=/^[\w]{6,8}$/;
				if(!match.test(this.value)){
					hidden_div[3].innerHTML="密码必须由6-8字母，数字，下划线组成";
				}else{
					passwordAgain=this.value;
					hidden_div[3].innerHTML="";
					checkPassword=true;
				}
			}
			confirm.onchange=function(){
				if(passwordAgain!=this.value){
					hidden_div[4].innerHTML="两次输入的密码不一致！";
				}else{
					hidden_div[4].innerHTML="";
					checkConfirm=true;
				}
			}
			confirm.onfocus=function(){
				if(this.value==passwordAgain){
				   hidden_div[4].innerHTML="";
				   checkConfirm=true;
				}
			}
			function getElement(attr){
				/*
					必须是str类型
					判断attr类型获取element
					#表示id
					.表示class
					没有表示element
				*/
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
		}