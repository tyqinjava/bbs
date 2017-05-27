<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*,bean.*,dao.*,java.net.*" pageEncoding="utf-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> 
<link rel="stylesheet" type="text/css" href="../css/result.css">
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
	   <p class="p_head">为您找到帖子</p>
	   <div>
	     <%
	     	String key = request.getParameter("keyword");
	     	if (key == null)
	     		return;
	     	String encodeKey=new String(key.getBytes("ISO-8859-1"),"utf-8");
	     	PostDao pd = new PostDao();
	     	UserDao ud=new UserDao();
	     	List<Post> ps = pd.getPostsByTtile("%" + encodeKey + "%");
	     	if (ps.size() == 0) {
	     		out.print("<p style='text-align:center;line-height:24px;'>没有找到相应的帖子</p>");
	     	} else {
	     		for (Post p : ps) {
	     		    User u=ud.getUser(p.getUser_id());
	     			out.print("<div class='today_p_wrap'>");
	     			out.print("<img src='/bbs/images/" + u.getUser_icon() + "' width='36px' height='36px'>");
	     			out.print("<p class='today_p'>");
	     			out.print("&nbsp;<a href='/bbs/jsp/postdetail.jsp?pid=" + p.getPost_id() + "'>");
	     			out.print(p.getTitle() + "</a>");
	     			out.print("</p>");
	     			out.print("<p class='today_p'>");
	     			out.print("&nbsp; by&nbsp;<a href='/bbs/jsp/userdetail.jsp?uid=" + u.getUser_id() + "'>");
	     			out.print(u.getNickname() + "</a></p>");
	     			out.print("</p>");
	     			out.print("</div>");
	     		}
	     	}
	     %>
	     <p class="p_head" style="margin-top:20px">为您找到用户</p>
	       <%
	     	List<User> uu = ud.getUserByNickName("%" + encodeKey + "%");
	     	if (uu.size() == 0) {
	     		out.print("<p style='text-align:center;line-height:24px;'>没有找到相应的用户</p>");
	     	} else {
	     		for (User ur : uu) {
	     			out.print("<div class='today_p_wrap'>");
	     			out.print("<img src='/bbs/images/" + ur.getUser_icon() + "' width='36px' height='36px'>");
	     			out.print("<p class='today_p u_p'>");
	     			out.print("&nbsp;<a href='/bbs/jsp/userdetail.jsp?uid=" +ur.getUser_id()+ "'>");
	     			out.print(ur.getNickname()+ "</a>");
	     			out.print("</p>");
	     			out.print("</div>");
	     		}
	     	}
	     %>
	     
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

