
var um=UM.getEditor("editor");
var postType=document.getElementById("postType"); //select
var postTitle=document.getElementById("postTitle");//input
var counter=document.getElementById("counter");//span
var submit=document.getElementById("sub");
var titleValue="";
check();
postTitle.onchange=check;
function check(){
	setTimeout(check,100);
	var str=postTitle.value
	if(str.length>40){
		titleValue=str.slice(0,40);
        postTitle.value=str.slice(0,40);
	}else{
		counter.innerHTML="你还可以输入"+(40-str.length)+"个字";
		titleValue=str;
	}
}
submit.onclick=function() {
	if(titleValue==""){
	    alert("你还没有输入标题");
	    return;
	}
	var match1 = /<[\w]+/g;
	var match2 =/<![\w]+/g;
	var match3=/<\/[\w]+/g;
	if (match1.test(titleValue) || match2.test(titleValue) || match3.test(titleValue)) {
		alert("你输入的标题不规范！");
		return;
	}
	var content=um.getContent();
	if(content.length==0){
		alert("内容为空，不允许提交");
		return;
	}
	var fm=document.createElement("form");
	fm.action="/bbs/publish";
	fm.method="POST";
	fm.enctype="application/form-urlencoded";
	fm.style.display="none";
	var type="";
	var options=postType.options;
	for(var i=0;i<options.length;i++){
		if(options[i].selected){
			type=options[i].value;
		}
	}
	addItem("content",content);
	addItem("type",type);
	addItem("title",titleValue);
	document.body.appendChild(fm);
	function addItem(name,value){
		var textArea=document.createElement("textarea");
		textArea.name=name;
		textArea.value=value;
		fm.appendChild(textArea);
	}
	fm.submit();
}


