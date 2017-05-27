<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">  
<title>注册页面</title>
<link rel="stylesheet" type="text/css" href="../css/reg.css">
<script type="text/javascript" src="../javascript/reg.js" charset="utf-8"></script>
</head>
<body>
<div id="reg">
		<h4 dirdir="ltr">注册</h4>
		<form method="post" action="/bbs/regsiter" id="submit">
		  <div class="form_display">
			 <label class="form_label">邮箱：</label><input class="form_input" type="text" name="email" size="50">
			</div>
			<div class="form_hidden"></div>
		  <div class="form_display">
			 <label class="form_label">用户名：</label><input class="form_input" type="text" name="username" size="50">
			</div>
			<div class="form_hidden"></div>
			<div class="form_display">
			 <label class="form_label">昵称：</label><input class="form_input" type="text" name="nickname" size="50">
			</div>
			<div class="form_hidden"></div>
			<div class="form_display">
			 <label  class="form_label">密码：</label><input class="form_input" type="password" name="password" size="50">
			</div>
			<div class="form_hidden"></div>
			<div class="form_display">
			 <label  class="form_label">确认密码：</label><input class="form_input" type="password"  size="50">
			</div>
			<div class="form_hidden"></div>
			<div class="form_display">
			 <label class="form_label">验证码：</label><input class="form_input ident" type="text" name="identifying " size="15">
			 <p style="padding-left:10px;font-size:14px;color:red;line-height:25px" class="vcmsg"></p>			 
			 </div>
			<div class="img_div">
				<img src="/bbs/vercode" class="verimg" width="100px" height="32px">
				<a href="javascript:;" class="again">看不清?</a>
			</div>
			<div class="div_agree">
				<input type="checkbox" name="agree" class="read"><label>我已看过并完全同意 <a href="#">《会员服务条款》</a></label>
			</div>
			<div class="form_hidden"></div>
			<div class="div_submit">
				<button type="submit" class="button_submit"><span>立即注册</span></button>
			</div>
		 </form>
    </div>
</body>
</html>
  
    
  
