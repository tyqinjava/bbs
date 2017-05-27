<%@ page language="java" import="bean.User" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css" href="../css/userinfo.css">
<script type="text/javascript" src="../javascript/userinfo.js"
	charset="utf-8"></script>
<title>主题内容</title>
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
		<div class="content_nav">
			<p>用户详情</p>
		</div>
		<div class="show">
			<tr>
				<p class="item">用户头像:</p>
				<div class="user_icon">
					<p>
						当前头像：<img width="80px" height="80px"
							src="/bbs/images/${sessionScope['user'].getUser_icon()}">
					</p>
					<p>
						<input type="button" value="更改头像" _index="1" class="button changeButton"> <input
							type="button" _index="2" value="上传头像" class="button changeButton">
					</p>
				</div>
				<div class="show_image">
			     	<div class="imageDialog">
			     		<div class="dialog_header">
						<p>更改头像</p>
						<div id="close1"></div>
						</div>		
							<table>
								<tr>
									<td><img width="80px" height="80px" src="../images/face/1.png"></td>
									<td><img width="80px" height="80px" src="../images/face/2.png"></td>
									<td><img width="80px" height="80px" src="../images/face/3.png"></td>
									<td><img width="80px" height="80px" src="../images/face/4.png"></td>
									<td><img width="80px" height="80px" src="../images/face/5.png"></td>
								</tr>
								<tr>
									<td><img width="80px" height="80px" src="../images/face/6.png"></td>
									<td><img width="80px" height="80px" src="../images/face/7.png"></td>
									<td><img width="80px" height="80px" src="../images/face/8.png"></td>
									<td><img width="80px" height="80px" src="../images/face/9.png"></td>
									<td><img width="80px" height="80px" src="../images/face/10.png"></td>
								</tr>
								<tr>
									<td><img width="80px" height="80px" src="../images/face/11.png"></td>
									<td><img width="80px" height="80px" src="../images/face/12.png"></td>
									<td><img width="80px" height="80px" src="../images/face/13.png"></td>
									<td><img width="80px" height="80px" src="../images/face/14.png"></td>
									<td><img width="80px" height="80px" src="../images/face/15.png"></td>
								</tr>
							</table>
					 </div>
				</div>
				<div class="show_image">
			     	<div class="imageDialog">
						<div class="dialog_header">
						<p>上传头像</p>
						<div id="close2"></div>
						</div>
						<div class="dialog_content">
						         最好选择80x80像素的头像，图像的文件不能超过4M
							<form name="uploadiconForm" action="/bbs/imageupload" method="post" enctype="multipart/form-data">
							   <input type="file" name="uploadicon" value="上传图片" class="inputfile">
							</form>
							<div class="loadImage"></div>
							<input type="button" value="确定" class="uploadIconControl"><input class="uploadIconControl" type="button" value="取消">
						</div>		
					</div>
				</div>
				<p class="item">
					用户名：${sessionScope['user'].getUser_name()}<input type="button"
						value="更改用户名" _index="3" class="button changeButton">
				</p>
				<p class="hidden">
					<input type="text" class="input_data"><input type="button" value="提交" class="button submit" _sub="1">
				</p>

				<p class="item">
					密码：<%
					User user = (User) session.getAttribute("user");
					if (user != null) {
						for (int i = 0; i < user.getPassword().length(); i++) {
							out.print("*");
						}
					}
				%><input type="button" value="更改密码" _index="4" class="button changeButton">
				</p>

				<div class="hidden pw">
					<p>
						旧密码：<input type="text" class="input_data"">
					</p>
					<p>
						新密码：<input type="text" class="input_data"">
					</p>
					<p>
						<input type="button" value="提交" class="button submit" _sub="2">
					</p>
				</div>

				<p class="item">
					昵称：${sessionScope['user'].getNickname()}<input type="button"
						value="更改昵称"  _index="5" class="button changeButton">
				</p>

				<p class="hidden">
					<input type="text" class="input_data"><input type="button" value="提交"
						class="button submit" _sub="3">
				</p>

				<p class="item">
					性别：${sessionScope['user'].getSex()}<input type="button"
						value="设置性别" _index="6" class="button changeButton">
				</p>

				<p class="hidden">
					<input type="radio" name="sex" value="m" class="input_data">男<input
						type="radio" name="sex" value="w" class="input_data">女 <input type="button"
						value="提交" _sub="4" class="button submit">
				</p>

				<p class="item">
					出生日期：${sessionScope['user'].getBirthday()}<input type="button"
						value="更改出生日期" _index="7" class="button changeButton">
				</p>
				<p class="hidden">
					年份：<select class="input_data">
						<option value="2017">2017年</option>
						<option value="2016">2016年</option>
						<option value="2015">2015年</option>
						<option value="2014">2014年</option>
						<option value="2013">2013年</option>
						<option value="2012">2012年</option>
						<option value="2011">2011年</option>
						<option value="2010">2010年</option>
						<option value="2009">2009年</option>
						<option value="2008">2008年</option>
						<option value="2007">2007年</option>
						<option value="2006">2006年</option>
						<option value="2005">2005年</option>
						<option value="2004">2004年</option>
						<option value="2003">2003年</option>
						<option value="2002">2002年</option>
						<option value="2001">2001年</option>
						<option value="2000">2000年</option>
						<option value="1999">1999年</option>
						<option value="1998">1998年</option>
						<option value="1997">1997年</option>
						<option value="1996">1996年</option>
						<option value="1995">1995年</option>
						<option value="1994">1994年</option>
						<option value="1993">1993年</option>
						<option value="1992">1992年</option>
						<option value="1991">1991年</option>
						<option value="1990">1990年</option>
						<option value="1989">1989年</option>
						<option value="1988">1988年</option>
						<option value="1987">1987年</option>
						<option value="1986">1986年</option>
						<option value="1985">1985年</option>
						<option value="1984">1984年</option>
						<option value="1983">1983年</option>
						<option value="1982">1982年</option>
						<option value="1981">1981年</option>
						<option value="1980">1980年</option>
						<option value="1979">1979年</option>
						<option value="1978">1978年</option>
						<option value="1977">1977年</option>
						<option value="1976">1976年</option>
						<option value="1975">1975年</option>
						<option value="1974">1974年</option>
						<option value="1973">1973年</option>
						<option value="1972">1972年</option>
						<option value="1971">1971年</option>
						<option value="1970">1970年</option>
						<option value="1969">1969年</option>
						<option value="1968">1968年</option>
						<option value="1967">1967年</option>
						<option value="1966">1966年</option>
						<option value="1965">1965年</option>
						<option value="1964">1964年</option>
						<option value="1963">1963年</option>
						<option value="1962">1962年</option>
						<option value="1961">1961年</option>
						<option value="1960">1960年</option>

					</select> 月<select class="input_data">
						<option value="1">1月</option>
						<option value="2">2月</option>
						<option value="3">3月</option>
						<option value="4">4月</option>
						<option value="5">5月</option>
						<option value="6">6月</option>
						<option value="7">7月</option>
						<option value="8">8月</option>
						<option value="9">9月</option>
						<option value="10">10月</option>
						<option value="11">11月</option>
						<option value="12">12月</option>
					</select> 日 <select class="input_data">
						<option value="1">1日</option>
						<option value="2">2日</option>
						<option value="3">3日</option>
						<option value="4">4日</option>
						<option value="5">5日</option>
						<option value="6">6日</option>
						<option value="7">7日</option>
						<option value="8">8日</option>
						<option value="9">9日</option>
						<option value="10">10日</option>
						<option value="11">11日</option>
						<option value="12">12日</option>
						<option value="13">13日</option>
						<option value="14">14日</option>
						<option value="15">15日</option>
						<option value="16">16日</option>
						<option value="17">17日</option>
						<option value="18">18日</option>
						<option value="19">19日</option>
						<option value="20">20日</option>
						<option value="21">21日</option>
						<option value="22">22日</option>
						<option value="23">23日</option>
						<option value="24">24日</option>
						<option value="25">25日</option>
						<option value="26">26日</option>
						<option value="27">27日</option>
						<option value="28">28日</option>
						<option value="29">29日</option>
						<option value="30">30日</option>
						<option value="31">31日</option>
					</select>
					<input type="button" class="button submit" value="提交" _sub="5">
				</p>


				<p class="item">
					邮箱：${sessionScope['user'].getEmail()}<input type="button"
						value="更改邮箱" _index="8" class="button changeButton">
				</p>
				<p class="hidden">
					<input type="text" class="input_data"><input type="button" value="提交" class="button submit" _sub="6">
				</p>
				<p class="item buttom">注册时间：${sessionScope['user'].getReg_time()}</p>
				<p class="item buttom">
					<a href="/bbs/jsp/main.jsp">返回主页</a>
				</p>
		</div>
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

