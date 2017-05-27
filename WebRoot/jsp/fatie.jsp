<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>发表帖子</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="pragma" content="no-cache"><!-- HTTP1.0-->
	<meta http-equiv="cache-control" content="no-cache"><!-- HTTP1.1-->
	<meta http-equiv="expires" content="0"> 
    <link href="/bbs/editor/themes/default/_css/umeditor.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="/bbs/editor/third-party/jquery.min.js"></script>
    <script type="text/javascript" src="/bbs/editor/third-party/template.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="/bbs/editor/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/bbs/editor/editor_api.js"></script>
    <script type="text/javascript" src="/bbs/editor/lang/zh-cn/zh-cn.js"></script>
  	<link rel="stylesheet" type="text/css" href="/bbs/css/fatie.css">
</head>
<body>
<div id="header">
		<div class="header_wrap center">
			<div class="left">
				<ul>
					<li><a href="/bbs/jsp/main.jsp" class="logo">飞光社区</a></li>
				</ul>
			</div>
			<div class="right">
			    <form action="/bbs/jsp/result.jsp" method="get">
				   <input type="submit" value="搜索">
				   <input name="keyword" placeholder="搜索..." type="text">
				</form>
			</div>
		</div>
	</div>
<div id="content" class="center">
		<div class="title">
			<p>
				<ul>
					<li><a href="/bbs/jsp/userinfo.jsp"><img width="80px" height="80px" src="/bbs/images/${sessionScope['user'].getUser_icon()}"></a></li>
					<li><a href="/bbs/jsp/userinfo.jsp">${sessionScope['user'].getNickname()}</a><span>
					<%
					   Date now=new Date();
					   SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		    		   String date=dateFormat.format(now);
					  out.print(date); 
					 %></span></li>
					<li><a href="/bbs/jsp/main.jsp">返回首页</a></li>
				</ul>
			<div class="title_tab"><p><b>发帖</b></p></div>
			<div class="theme">
				<span>选择主题分类</span>
				<select id="postType">
					<option value="学习">学习</option>
					<option value="分享">分享</option>
					<option value="身边">身边</option>
					<option value="音乐">音乐</option>
					<option value="视频">视频</option>
					<option value="搞笑">搞笑</option>
				</select>
				<span>标题：</span><input id="postTitle" type="text">
				<span id="counter">还可以输入40个字</span>
		    </div>
		</div>
		<script type="text/plain" id="editor" style="width:1000px;height:360px;">
        </script>
    	<p class="content_ft"><input id="sub" type="button" value="提交" class="button"></p>
    	<script type="text/javascript" src="/bbs/javascript/fatie.js" charset="utf-8"></script>
</div>
<div id="footer">
		<div class="foot_wrap center">
		<ul>
			<li><span>版本1.0</span></li>
			<li><a href="#">关于作者</a></li>
			<li><a href="#">支持作者</a></li>
			<li><a href="#">联系作者</a></li>
			<li><a href="#">意见反馈</a></li>
			<li><a href="#">帮助中心</a></li>
		</ul>
		<p>Copyright © 2017 飞光BBS All rights reserved.</p>
	 </div>
</div>
</body>
</html>