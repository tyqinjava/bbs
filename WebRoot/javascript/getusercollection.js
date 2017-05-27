getusercollection_init();
function getusercollection_init(){
	var  respData=[];
	var  Length=10;
	var  loadMore=document.getElementsByClassName("loadMore")[1];
	var  ul=document.getElementsByClassName("user_collection")[0];
	var  uid=ul.getAttribute("_uid");
	var  url="/bbs/getuserdetail";
	var xhr=new XMLHttpRequest();
	xhr.open("POST", url,true);
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xhr.send("type=getusercollection&uid="+uid);
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
		var delList=document.getElementsByClassName("span_del_col");
	    for(var i=0;i<delList.length;i++){
	       delList[i].onclick=delCollection;
	    }
	    function delCollection(){
		    
    		if(!confirm("真的取消收藏？")){
	    		return;
	    	}
	    	var cl_id=this.getAttribute("_clid");
			var x=new XMLHttpRequest();
			x.open("POST", url,true);
			x.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			x.send("type=delcollection&clid="+cl_id);
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

	function insertLi(data){
		    /*
		     *  time
		     *  pid
		     *  title
		     *  isSamll
		     *  uid
		     *  unickname
		     * **/
		    var start='<li>';
		    var c1='<p>';
		    var c2='收藏于:'+data['time'];
		    var c22='&nbsp;&nbsp;by&nbsp;&nbsp;<a href="/bbs/jsp/userdetail.jsp?uid='+data['uid']+'">'+
		    data['unickname']+'</a>';
		    var c3='</p>';
		    var c4='<p>';
		    var c5='<a href="/bbs/jsp/postdetail.jsp?pid='+data['pid']+'">';
		    var c6=data['title'];
		    var c7='</a>';
		    var c8='';
		    if(data['isSmall']=="true"){
		      c8='<span class="span_del_col" _clid='+data['cl_id']+'>取消收藏</span>';
		    }
		    var c9="</p>";
			var end="</li>";	
			var html=start+c1+c2+c22+c3+c4+c5+c6+c7+c8+c9+end;
			return html;
		}
}
