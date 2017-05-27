<%@ page language="java" import="java.util.*,java.io.*,bean.*,bean.Comment
 ,bean.Collection,dao.*" pageEncoding="utf-8"%> 
<%
	User user=(User)session.getAttribute("user");
	if(user==null){
	   response.sendRedirect("/bbs/index.jsp");
	   return;
	}
	String pid=request.getParameter("pid");
	if(pid==null)return;
	int ppid=0;
	PostDao pd=new PostDao();
	try{
	  ppid=Integer.parseInt(pid);
	}catch(Exception e){
		return;
	}
	Post post= pd.getPost(ppid);
	CommentDao cd=new CommentDao();
	UserDao ud=new UserDao();
	if(post==null)
	   return;
	User u=ud.getUser(post.getUser_id());
	List<Comment> cs=cd.getComment(post.getPost_id());
	CollectionDao cld=new CollectionDao();
	List<bean.Collection> cls=cld.getCollectionPostId(post.getPost_id());
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> 
<link href="/bbs/editor/themes/default/_css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="/bbs/javascript/comment/editor/third-party/jquery.min.js"></script>
<script type="text/javascript" src="/bbs/javascript/comment/editor/third-party/template.min.js"></script>
<script type="text/javascript" charset="utf-8" src="/bbs/javascript/comment/editor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/bbs/javascript/comment/editor/editor_api.js"></script>
<script type="text/javascript" src="/bbs/javascript/comment/editor/lang/zh-cn/zh-cn.js"></script>
<link rel="stylesheet" type="text/css" href="../css/postdetail.css">
<title>帖子内容</title>
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
	<div class= "title">
	<p><a href="/bbs/jsp/main.jsp">返回主页</a></p>
	   <div class="by">
	   <a href="/bbs/jsp/userdetail.jsp?uid=<%=post.getUser_id() %>" class="icon"><img width="48px" height="48px" src="/bbs/images/<%=u.getUser_icon() %>"></a>
	   <p>标题:&nbsp;&nbsp;&nbsp;<b title="<%=post.getTitle()%>"><%=post.getTitle()%></b></p>
	   <p>by:&nbsp;&nbsp;&nbsp;<a href="/bbs/jsp/userdetail.jsp?uid=<%=post.getUser_id() %>"><%=u.getNickname()%></a></p>
	   <p> 发表时间:&nbsp;&nbsp;<%=post.getPost_time() %>&nbsp;&nbsp;<span class="comment_counter">评论数：<%=cs.size() %></span></p>
	   </div>
	</div>
	<div id="content" class="center">
		<div id="data">
		<% 
				String path=application.getRealPath("/WEB-INF/tie"+post.getSrc());
				FileInputStream	in=new FileInputStream(path);
				BufferedReader br=new BufferedReader(new InputStreamReader(in,"utf-8"));
				String str=null;
				while((str=br.readLine())!=null){
				   out.print(str);
				   out.flush();
				} 
		%>
		</div>
	</div>
	<div class="ding"><a href="javascript:;" class="counter"><i class="icon_1"></i><span class="handle">顶一下(<%=post.getHot()
	 %>)</span></a><a href="javascript:;" class="counter"><i class="icon_2"></i><span class="handle">收藏(<%=cls.size() %>)</span></a></div>
	<div class="comment">
	   <div id="comment_hot">
	   <div class="comment_header">
		  <p>热门评论</p>
	   </div>
	   <div class="comment_content_hot">
	  	 <ul id=comment_ul_hot></ul>
  	    </div>
  	    </div>
  	    
  	    
	   <div class="comment_header">
		  <p>最新评论</p>
	   </div>
	   <div class="comment_content">
	  	 <ul id=comment_ul></ul>
  	</div>
  	<div id="loadmore">加载更多>></div>
	</div>
	<div class="editor">
		<div class="sendcomment">
			<p><span>发表评论</span><a href="#">回到顶部^</a></p>
		</div>
		<div class="myeditor">
		   <script type="text/plain" id="editor" style="width:900px;height:120px;">
           </script>
        </div>
        <p class="p_button"><button class="submit" pid=<%=post.getPost_id()
         %>>发表评论</button></p>
	</div>
	<script type="text/javascript" src="/bbs/javascript/comment.js">
     </script>
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

