getuserpost_init();
function getuserpost_init(){
	var respData=[];
	var Length=10;
	var loadMore=document.getElementsByClassName("loadMore")[2];
	var ul=document.getElementsByClassName("user_comment")[0];
	var uid=ul.getAttribute("_uid");
	var url="/bbs/getuserdetail";
	var xhr=new XMLHttpRequest();
	xhr.open("POST", url,true);
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xhr.send("type=getusercomment&uid="+uid);
	xhr.onreadystatechange=function(){
		if(xhr.readyState===4&xhr.status==200){
			var json=xhr.responseText;
			if(json=="")return;
			respData=JSON.parse(json);
			showData(10);
		}
		
	}
	loadMore.onclick=function(){
	    Length+=10;
		showData(Length);
	}
	function showData(dataLength){
		var html="";
		if(respData.length<=dataLength){
			for(var i=0;i<respData.length;i++){
				var temp=insertLi(respData[i]);
				html+=temp;
			}
			loadMore.style.display="none";
		}else{
			for(var i=0;i<dataLength;i++){
				var temp=insertLi(respData[i]);
				html+=temp;
			}
		    loadMore.style.display="block";
		}
		ul.innerHTML=html;
		var delList=document.getElementsByClassName("span_del_com");
	    for(var i=0;i<delList.length;i++){
	       delList[i].onclick=delComment;
	    }
	}

	  function insertLi(data){
		    /**
		     * {
		     *  cid time isSmall content uid title unickname pid isExist
		     * 
		     * }
		     * 
		     */
		    var start='<li class="user_comment_li">';
		    var c1='<p user_comment_p>';
		    var c2=data['time']+'发表在&nbsp;&nbsp;';
		    var c21='';
		    var c22='';
		    
		    if(data['isExist']=='true'){
		    var c22='<a class="user_comment_a" href="/bbs/jsp/postdetail.jsp?pid='+data['pid']+'">'+data['title']+'</a>&nbsp;&nbsp;by';
		    var c21='<a class="user_comment_a" href="/bbs/jsp/userdetail.jsp?uid='+data['uid']+'">'+data['unickname']+'</a>&nbsp;&nbsp;';
		   
		    }else{
		    	c21='&nbsp;&nbsp;<i>该帖子已被删除！</i>';
		    }
		    
		    var c33='';
		    if(data['isSmall']=="true"){
			      c33='<span class="span_del_com user_comment_span" _cid='+data['cid']+'>删除</span>';
			    }
		    var c3='</p>';
		    var c4='<div class="user_comment_div">';
		    var c5=data['content'];
		    var c8='</div>';
			var end="</li>";	
			var html=start+c1+c2+c22+c21+c33+c3+c4+c5+c8+end;
			return html;
		}
	    function delComment(){
	    	if(!confirm("真的要删除？")){
	    		return;;
	    	}
	    	var cid=this.getAttribute("_cid");
			var x=new XMLHttpRequest();
			x.open("POST", url,true);
			x.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			x.send("type=delcomment&cid="+cid);
			x.onreadystatechange=function(){
				if(x.readyState===4&&x.status==200){
					var json=x.responseText;
					if(json=="")return;
					if(json=="ok"){
						window.location=location;
					}
				}
				
			}
	    }
}

