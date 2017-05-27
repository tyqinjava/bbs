<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>登录页面</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
<script type="text/javascript" src="javascript/login.js" charset="utf-8"></script>
</head>
<body>
	<div id="content">
		<div class="form">
			<div class="content_item lable">
				<span>用户名：</span>
			</div>
			<div class="content_item">
				<input type="text" class="content_input" placeholder="用户名">
				<div class="msg"></div>
			</div>
			<div class="content_item lable">
				<span>密码：</span>
			</div>
			<div class="content_item">
				<input autocomplete="off" name="username" type="password"
					class="content_input" placeholder="密码">
				<div class="msg"></div>
			</div>
			<div class="content_item">
				<input autocomplete="off" name="password" type="checkbox"
					class="checkbox"><small>记住我</small>
			</div>
			<div class="content_item">
				<div class="button_item">
					<button class="content_button button_one" href="javscript:;">登录</button>
					<a href="/bbs/jsp/reg.jsp" class="content_button button_two">注册</a>
				</div>
			</div>
			<br>
			<div class="content_item">
				<span class="custom">飞光BBS©2017 tyq</span>
			</div>
		</div>
	</div>
	</div>
	<div id="font">
	   <p>故事的小黄花 </p>
	   <p>从出生那年就飘着</p>
	   <p>童年的荡秋千</p>
	   <p>随记忆一直晃到现在</p>
	    <p style="text-align: right">-周杰伦《晴天》</p>
	</div>
	<div id=footer>
		<div class="footer_wrap">
			<ul>
				<li class="no_border"><a href="javascript:;">版本1.0</a></li>
				<li><a href="/bbs/about.html">关于作者</a></li>
				<li><a href="/bbs/cantact.html">联系作者</a></li>
				<li><a href="/bbs/feedback.hml">意见反馈</a></li>
				<li><a href="/bbs/help.html">帮助中心</a></li>
			</ul>
			<p>Copyright © 2017 飞光BBS All rights reserved.</p>
		</div>
</body>
</html>
