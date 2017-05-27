
	var next=document.getElementsByClassName("next")[0];
	var upper=document.getElementsByClassName("upper")[0];
	var sortButton=document.getElementsByClassName("sort_button")[0];
	var pageCounter=document.getElementsByClassName("pageCounter")[0];
	var INDEX=next.getAttribute("_index");
	var MAX=pageCounter.getAttribute("_max");
	var type=next.getAttribute("_type");
	var sort=false;
	if(sortButton.checked){
		sort=true;
	}
	var contentNav=document.getElementsByClassName("content_nav")[0];
	var lists=contentNav.getElementsByTagName("li");
	for(var i=0;i<lists.length;i++){
		lists[i].onclick=changeTab;
	}
	function changeTab(){
		if(this.className=="sort")return;
		var type=this.getAttribute("_type");
		TYPE=type;

		window.location.href="/bbs/jsp/main.jsp?type="+type+"&start=0"+"&sort="+sort;
	}
	function getPageCount(){
		if(MAX%15==0){
			return MAX/15;
		}else{
			return Math.floor(MAX/15)+1;
		}
	}
	next.onclick=function(){
		
		INDEX++;
		if(INDEX>getPageCount()-1){
			INDEX=getPageCount()-1;
			return;
		}
		window.location.href="/bbs/jsp/main.jsp?type="+type+"&start="+INDEX+"&sort="+sort;
	}
	upper.onclick=function(){
		INDEX--;
		if(INDEX<0){
         INDEX=0;
	     return;
		}
		window.location.href="/bbs/jsp/main.jsp?type="+type+"&start="+INDEX+"&sort="+sort;
	}
	sortButton.onclick=function(){
		if(this.checked){
	     window.location.href="/bbs/jsp/main.jsp?type="+type+"&start="+INDEX+"&sort=true";
		}else{
		window.location.href="/bbs/jsp/main.jsp?type="+type+"&start="+INDEX+"&sort=false";
		}
	}
