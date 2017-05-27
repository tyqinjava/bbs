 var um=UM.getEditor("editor");
 var counter1=document.getElementsByClassName("counter")[0];//顶贴
 var counter2=document.getElementsByClassName("counter")[1];//收藏
                                                           //counter3点赞
 var counter1Text=document.getElementsByClassName("handle")[0];
 var counter2Text=document.getElementsByClassName("handle")[1];
 var submit=document.getElementsByClassName("submit")[0]; //发表评论
 var strtemp=um.getContent();
 var dataListOfHot=document.getElementById("comment_ul_hot");
 var commentHot=document.getElementById("comment_hot");
 var isCounter1Click=false;
 var isCounter2Click=false;
 var isCounter3Click=false;
 
 var dataList=document.getElementById("comment_ul");
 var showDataLength=15;
 var pid=submit.getAttribute("pid");
 var respData=[];
 var loadMore=document.getElementById("loadmore");
 
 init();//初始化数据
 
 //发表评论功能
 submit.onclick=function(){
	   var str=um.getContent();
	    if(str==strtemp||str==""){
	    	alert("你还没有输入内容！");
	    	return;
	    }
	    if(str.length>15000){
		    alert("你输入的内容过长！");
		    return;
	    }
	    var fm=document.createElement("form");
		fm.action="/bbs/sendcomment";
		fm.method="POST";
		fm.enctype="application/form-urlencoded";
		fm.style.display="none";
		document.body.appendChild(fm);
	    addItem("content",str);
	    addItem("type","sendcomment");
	    addItem("pid",pid);
	    fm.submit();
	    function addItem(name,value){
			var textArea=document.createElement("textarea");
			textArea.name=name;
			textArea.value=value;
			fm.appendChild(textArea);
		}
       
 }
 function getCommentList(length){
	 dataList.innerHTML="";
	 respData=[];
	 var url="/bbs/getcomment";
	 var xhr=new XMLHttpRequest();
	 xhr.open("POST",url,true);
	 xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	 var param="start=0&end="+length+"&pid="+pid;
	 xhr.send(param);
	 xhr.onreadystatechange=function(){
		 if(xhr.readyState===4&&xhr.status==200){
			 var json=xhr.responseText;
			 if(json=='[]'||json=="")return;
			 respData=JSON.parse(json);
			 if(respData.length<showDataLength){
				 loadMore.style.display="none";
			 }else{
				 loadMore.style.display="block";
			 }
			 respData.sort(sortDataDesc);
			 //test
			 var html="";
			 for(var i=0;i<respData.length;i++){
				 var str=inserLi(respData[i]);
				 html+=str;
			 }
		     dataList.innerHTML=html;
		     var counter3=document.getElementsByClassName("agree");
		     for(var i=0;i<counter3.length;i++){
		    	 counter3[i].onclick=agreeClick;
		     }
		 }
	 }
 }
 function agreeClick(){
	 var $=this;
	 var cid=this.getAttribute("_cid");
	 if(isCounter3Click){
		 var str="type=agree&isadd=false&cid="+cid;
		 sendMsg(str);
		 isCounter3Click=false;
	 }else{ 
		 var str="type=agree&isadd=true&cid="+cid;
		 sendMsg(str);
		 isCounter3Click=true;
	 }
	 function sendMsg(param){
		 var xhr=new XMLHttpRequest();
		 var url="/bbs/handle";
		 xhr.open("POST",url,true);
		 xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		 xhr.send(param);
		 xhr.onreadystatechange=function(){
			 if(xhr.readyState===4&&xhr.status==200){
				 var json=xhr.responseText;
				 if(json=="")return;
				 if(json=="session"){
					 alert("登录过期!");
					 window.location.href="/bbs/index.jsp";
					 return;
				 }
				 if(json=="agree"){
					 alert("你已经赞过了！");
					 return;
				 }
				 $.innerHTML="赞("+json+")";
			 }
			 
		 }
	 }
	 
 }
 counter1.onclick=function(){
	 var param="type=hot&pid="+pid;
	 sendMsg(param);
	 function sendMsg(param){
		 var xhr=new XMLHttpRequest();
		 var url="/bbs/handle";
		 xhr.open("POST",url,true);
		 xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		 xhr.send(param);
		 xhr.onreadystatechange=function(){
			 if(xhr.readyState===4&&xhr.status==200){
				 var json=xhr.responseText;
				 if(json=="")return;
				 if(json=="session"){
					 alert("登录过期!");
					 window.location.href="/bbs/index.jsp";
					 return;
				 }
				 if(json=='ok'){
					 var str=counter1Text.innerHTML;
					 var start=str.indexOf("(");
					 var end=str.lastIndexOf(")");
					 str=str.slice(start+1, end);
					 var i=str-0;
					 counter1Text.innerHTML="顶一下("+(i+1)+")";
				 }else if(json=='again'){
					 alert('你已经顶过了该帖!');
				 }	
			 }
			 
		 }
	 }
 }
 counter2.onclick=function(){
	 var param="type=collection&pid="+pid;
	 sendMsg(param);
	 
	 function sendMsg(param){
		 var xhr=new XMLHttpRequest();
		 var url="/bbs/handle";
		 xhr.open("POST",url,true);
		 xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		 xhr.send(param);
		 xhr.onreadystatechange=function(){
			 if(xhr.readyState===4&&xhr.status==200){
				 var json=xhr.responseText;
				 if(json=="")return;
				 if(json=="session"){
					 alert("登录过期!");
					 window.location.href="/bbs/index.jsp";
					 return;
				 }
				 if(json=="ok"){
					 alert("收藏成功!");
					 var str=counter2Text.innerHTML;
					 var start=str.indexOf("(");
					 var end=str.lastIndexOf(")");
					 str=str.slice(start+1, end);
					 var i=str-0;
					 counter2Text.innerHTML="收藏("+(i+1)+")";
				 }else if(json=="again"){
					 alert("你已经收藏了该帖子！");
				 }else{
					 alert("收藏失败!");
				 }
			 }
			 
		 }
	 }
 }
 
 loadMore.onclick=function(){
	 showDataLength+=15;
	 getCommentList(showDataLength);
 }
 //排序方式
 function sortDataDesc(a,b){
		if(a['cid']>b['cid']){
			return -1;
		}
		else if(a['cid']==b['cid']){
			return 0;
		}else{
			return 1;
		}
	}
	function sortDatabyHot(a,b){
		if(a['agree']>b['agreee']){
			return -1;
		}else if(a['agree']==b['agree']){
			return 0;
		}else {
       return 1;
     }
  }
	
 function init(){
	getCommentList(showDataLength);
	
	 var url="/bbs/getcomment";
	 var xhr=new XMLHttpRequest();
	 xhr.open("POST",url,true);
	 xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	 var param="start=0&end="+length+"&pid="+pid+"&hot=0";
	 xhr.send(param);
	 
	 xhr.onreadystatechange=function(){
		 if(xhr.readyState===4&&xhr.status==200){
			 var json=xhr.responseText;
			 if(json=='[]'||json=="")return;
			 
			 respData=JSON.parse(json);
			 if(respData.length>0){
				 commentHot.style.display="block";
			 }else{
				 commentHot.style.display="none";
			 }
			 respData.sort(sortDatabyHot);
			 //test
			 var html="";
			 for(var i=0;i<respData.length;i++){
				 var str=inserLi(respData[i]);
				 html+=str;
			 }
		     dataListOfHot.innerHTML=html;
		     var counter3=document.getElementsByClassName("agree");
		     for(var i=0;i<counter3.length;i++){
		    	 counter3[i].onclick=agreeClick;
		     }
		 }
	 }
 }
 function inserLi(data){
	 /*
	  * {
	  *     cid
	  *     uid
	  *     nickname
	  *     uicon
	  *     time
	  *     agree
	  *     content
	  *     size
	  * }
	  * */
	 var uLink='/bbs/jsp/userdetail.jsp?uid='+data['uid'];
	 var imgLink='/bbs/images/'+data['uicon'];
	 
	 var start='<li class="comment_li">';
	 var c1='<div class="comment_icon">'
	 var c2='<a href="'+uLink+'"><img src="'+imgLink+'" width="48px" height="48px"></a>';
	 var c3='</div>';
	 var c4='<div>';
	 var c5='<p class="comment_p"><a href="'+uLink+'">'+data['nickname']+
	 '</a>&nbsp;&nbsp;&nbsp;'+data['time']+'<a href="javascript:;" class="agree" _cid="'+data['cid']+
	 '">赞('+data['agree']+')</a>';
	 var c7='</p>';
	 var c8='<div id="comment_data">'+data['content'];
	 var c9='</div>';
	 var c10='</div>';
	 var c11='</li>';
	 var html=start+c1+c2+c3+c4+c5+c7+c8+c9+c10+c11;
	 return html;
 }