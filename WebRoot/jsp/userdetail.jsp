<%@ page language="java" import="java.util.*,bean.*,dao.*" pageEncoding="utf-8"%>  
<% 
      String uid=request.getParameter("uid");
      if(uid==null)return;
      UserDao ud=new UserDao();
      int uiid=0;
      try {
      	uiid=Integer.parseInt(uid);
      }catch(Exception e){
      	return;
      }
      User u=ud.getUser(uiid);//当前浏览的用户
      if(u==null)return;
      PostDao pd=new PostDao();
      List<Post> ps=pd.getPosts(u.getUser_id());//用户发表过的帖子
      CommentDao cd=new CommentDao();
      List<Comment> cs=cd.getCommentByUserId(u.getUser_id());//用户发表过的评论；
      
      CollectionDao cld=new CollectionDao();
      List<bean.Collection> cls=cld.getCollectionByUserId(u.getUser_id());
      User user=(User)session.getAttribute("user");
      if(user==null){
      response.sendRedirect("/bbs/index.jsp");return;
      }
      boolean isSame=false;
      if(u.getUser_id()==user.getUser_id()){
      	   isSame=true;
      }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> 
<link rel="stylesheet" type="text/css" href="../css/userdetail.css">
<title>用户详情</title>
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
	<div class="user_intro">
	  <div class="user_intro_left">
      <img src="/bbs/images/<%=u.getUser_icon() %>" width="52px" height="52px">
      <p><%=u.getNickname()%><% 
      	 if(u.getSex().equals("男")){
      	 	out.print("<i class=\"man\"></i>");
      	 }else if(u.getSex().equals("女")){
      	 out.print("<i class=\"woman\"></i>");
      	 }else {
      	 }
      %>&nbsp;&nbsp;<a href="/bbs/jsp/main.jsp">返回主页</a>&nbsp;&nbsp;<a href="/bbs/jsp/fatie.jsp">发表帖子</a></a></p>
      </div>
      <div class="user_intro_right">
      	<p>用户ID:&nbsp;&nbsp;<%=u.getUser_id() %>&nbsp;&nbsp; 注册时间为:<%=u.getReg_time() %></p>
      	<p>邮箱：<%=u.getEmail() %></p>
      </div>
	</div>	
	
	<div class="user_wrap">
		<p class="p_head"><b>
		<% 
			if(isSame){
			 out.print("您");
			}else{
			  out.print(u.getNickname());
			}
		%>
		</b>&nbsp;&nbsp;发表过的帖子</p>
		<ul class="user_ul user_post" _uid="<%=u.getUser_id() %>">	
		</ul>
		<p class="loadmore">加载更多>></p>
	    <script type="text/javascript" src="/bbs/javascript/getuserpost.js"></script>
	</div>
	<div class="user_wrap">
		<p class="p_head"><b><% 
			if(isSame){
			 out.print("您");
			}else{
			  out.print(u.getNickname());
			}%></b>&nbsp;&nbsp;收藏的帖子</p>
		 <ul class="user_ul user_collection" _uid="<%=u.getUser_id()%>">
		</ul>
		<p class="loadmore">加载更多>></p>
		<script type="text/javascript" src="/bbs/javascript/getusercollection.js"></script>
	</div>
	<div class="user_wrap ">
		<p class="p_head"><b>
		<% 
			if(isSame){
			 out.print("您");
			}else{
			  out.print(u.getNickname());
			}%>
		</b>&nbsp;&nbsp;发表过的评论</p>
		<ul class="user_ul user_comment"  _uid="<%=u.getUser_id()%>">
		
		</ul>
		<p class="loadmore">加载更多>></p>
		<script type="text/javascript" src="/bbs/javascript/getusercomment.js"></script>
	</div>
	<a href="#" class="return">返回顶部</a>
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

