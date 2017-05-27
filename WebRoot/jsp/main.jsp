<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*,bean.*,dao.*" pageEncoding="utf-8"%>  
<%              
		  User user=(User)session.getAttribute("user");
		  if(user==null){
		     response.sendRedirect("/bbs/index.jsp");
		     return;
		   }
		  UserDao ud=new UserDao();
          PostDao pd=new PostDao();
          List<Post> ps=pd.getPosts(user.getUser_id());   
          CollectionDao cld=new CollectionDao(); 
          List<bean.Collection> cls=cld.getCollectionByUserId(user.getUser_id());
          String type=request.getParameter("type");
          String strStart=request.getParameter("start");  
          String sort=request.getParameter("sort");
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="ContentType" content="utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> 
<link rel="stylesheet" type="text/css" href="../css/main.css">
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
		<div class="content_left">
			<ul class="content_nav">
		    	<% 
		    	if(type==null||strStart==null)
		    	{
		    	   strStart="0";
		    	   type="new";    
		    	}
				String li[]={"<li _type='new'>最新</li>",
			    "<li _type='study' >学习</li>",
				"<li _type='share' >分享</li>",
				"<li _type='live' >身边</li>",
				"<li _type='music' >音乐</li>",
				"<li _type='video' >视频</li>",
				"<li _type='jok' >搞笑</li>"
				};
				for(int i=0;i<li.length;i++){
					if(li[i].contains(type)){
					  String temp[]=li[i].split(" ");
					  int start=li[i].lastIndexOf(" ");
					  String str1=li[i].substring(0,start);
					  str1+=" class='selected'";
					  String str2=li[i].substring(start+1,li[i].length());
					  li[i]=str1+str2;
					}
					out.print(li[i]);
				}
				if(sort==null||!sort.equals("true")){
				  sort="false";
				}
				%>
				<li class="sort"><input type="checkbox" class="sort_button" _sort=<%=sort %> 
				<%
				 if(sort.equals("true")){
				  out.print("checked");
				 } 
				%>
				>顶贴数排序</li>	
			</ul>
			<div id="data">
				<ul id="dataList">
					<%
						String TYPE = null;
						if (type.equals("new")) {
							TYPE = null;
						} else if (type.equals("study")) {
							TYPE = "学习";
						} else if (type.equals("share")) {
							TYPE = "分享";
						} else if (type.equals("live")) {
							TYPE = "身边";
						} else if (type.equals("music")) {
							TYPE = "音乐";
						} else if (type.equals("video")) {
							TYPE = "视频";
						} else if (type.equals("jok")) {
							TYPE = "搞笑";
						} else {
							response.sendRedirect("/bbs/index.jsp");
							return;
						}
						int max = pd.getMaxCountByType(TYPE);
						int start = 0;
						try {
							start = Integer.parseInt(strStart);
						} catch (Exception e) {
							response.sendRedirect("/bbs/index.jsp");
							return;
						}
						if (start * 15 > max) {
							start = max / 15;
						}
						if (start < 0)
							start = 0;
						List<Post> list = null;
						if(sort.equals("true")){
						  List<Post> temp=pd.getRangeOfPostByTypeDesc(TYPE, start * 15, 15);
						  List<Post> tag=new ArrayList<Post>();
						   while(temp.size()!=0){
						   		tag.add(getMaxPost(temp));
						   }
						   list=tag;
						}else{
					      list=pd.getRangeOfPostByTypeDesc(TYPE, start * 15, 15);
						}
						for (int i = 0; i < list.size(); i++) {
							Post pt = list.get(i);
							User us = ud.getUser(pt.getUser_id());
							String title = pt.getTitle();
							String time = pt.getPost_time();
							int uid = us.getUser_id();
							int hot = pt.getHot();
							int pid = pt.getPost_id();
							String ptype = pt.getType();
							String uicon = us.getUser_icon();
							String nickname = us.getNickname();
							String userLink = "/bbs/jsp/userdetail.jsp?uid=" + uid;
							String postLink = "/bbs/jsp/postdetail.jsp?pid=" + pid;
							out.print("<li>");

							out.print("<div class='data_li_center'>");
							out.print("<a href='" + userLink
									+ "' class='data_user_icon'><img width='48px' height='48px' src='/bbs/images/" + uicon
									+ "'></a>");
							out.print("<div class='data_li_left'>");
							out.print("<p style='padding-bottom:7px;'><a href='" + postLink
									+ "'><b style='font-size:16px;font-weight:normal;'>" + title + "</b></a></p>");
							out.print("<p><a href='javascript:;' class='data_li_type'>" + ptype);
							out.print("</a>&nbsp;<span>by</span>&nbsp;<a href='" + userLink + "'><b>" + nickname
									+ "</b></a>&nbsp;<span sytle='font-size:12px;'>" + time + "</span></p>");
							out.print("</div>");
							out.print("<span class='data_center_right'>" + hot + "</span>");
							out.print("</li>");
						}
					%>
				</ul>
				<div class="data_footer">
				  <a href="javascript:;" class="upper" _index="<%=start%>">上一页</a><a href="javascript:;"  class="next" _index="<%=start%>" _type=<%=type %>>下一页</a>
				  <span class="pagecounter" _max="<%=max %>">
				  <% 
				     out.print((start+1)+"/"+(max/15+1)+"页"); 
				  %>
				  <%!
				    public Post getMaxPost(List<Post> list){
				         Post p=list.get(0);
				         for(int i=1;i<list.size();i++){
				              if(p.getHot()>list.get(i).getHot()){
				              	  continue;
				              }else{
				                   p=list.get(i);
				              }
				         }
				         Post pp=new Post();
				         clonePost(pp, p);
				         list.remove(p);
				         return pp;
				    } 
				    public void clonePost(Post np,Post op){
				    	np.setHot(op.getHot());
				    	np.setPost_id(op.getPost_id());
				    	np.setPost_time(op.getPost_time());
				    	np.setSrc(op.getSrc());
				        np.setTitle(op.getTitle());
				        np.setType(op.getType());
				        np.setUser_id(op.getUser_id());
				    }
				  %>
				  </span>
				</div>
			</div>
			<script type="text/javascript" charset="utf-8" src="/bbs/javascript/main.js"></script>
		</div>
		<div class="content_right">
			<div class="user">
				<p class="content_right_p">当前用户</p>
				<div class="user_info">
				 <a href="/bbs/jsp/userinfo.jsp" class="user_icon"><img width="80px" height="80px" src="../images/${sessionScope['user'].getUser_icon()}"></a>
				 <div class="user_info_item">
				 	<p><a href="/bbs/jsp/userdetail.jsp?uid=${sessionScope['user'].getUser_id()}" title="${sessionScope['user'].getNickname()}" class="user_nickname">${sessionScope['user'].getNickname()}</a></p>
				 	<p><a href="/bbs/jsp/userdetail.jsp?uid=${sessionScope['user'].getUser_id()}">收藏（<b><%=cls.size() %></b>）</a></p>
				 	<p><a href="/bbs/jsp/userdetail.jsp?uid=${sessionScope['user'].getUser_id()}">帖子（<b><%=ps.size()%></b>）</a></p>
				 	<p><a href="/bbs/jsp/userinfo.jsp">修改用户信息</a></p>
				 </div>
				</div>
				<a href="/bbs/jsp/fatie.jsp" class="fatie">发帖</a>
			</div>
			<div class="today">
			  <p class="content_right_p">今日热门</p>
			  <div class="content_right_today">
			  	<%
			  	      Date d=new Date();
                      SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
                      List<Post> todayHoPs=pd.getPostsByTimeAndHot("%"+sdf.format(d)+"%");
                      if(todayHoPs.size()!=0){ 
             		  for(int i=0;i<todayHoPs.size();i++){
             		       Post p=todayHoPs.get(i);
             		       User u=ud.getUser(p.getUser_id());
             		       out.print("<div class='today_p_wrap'>");
             		   	   out.print("<img src='/bbs/images/"+u.getUser_icon()+"' width='32px' height='32px'>");
             		   	   out.print("<p class='today_p'>");
             		   	   out.print("&nbsp;<a href='/bbs/jsp/postdetail.jsp?pid="+p.getPost_id()+"'>");
             		   	   out.print(p.getTitle()+"</a>");  		   	   
                           out.print("</p>");
                           out.print("<p class='today_p'>");
             		   	   out.print("&nbsp; by&nbsp;<a href='/bbs/jsp/userdetail.jsp?uid="+u.getUser_id()+"'>");
             		   	   out.print(u.getNickname()+"</a></p>");
             		   	   out.print("</p>");
             		   	   out.print("</div>");
             		  }
			       }
			  	%>
			  </div>
			</div>
			<div class="sugg">
			   <p class="content_right_p">社区推荐</p>
			   <div class="content_right_sugg">
			    <div class='today_p_wrap'>
             		 <img src='/bbs/images/face/1.png' width='32px' height='32px'>
             		 <p class='today_p'>
             		 <a href='/bbs/jsp/postdetail.jsp?pid=11'>来自网易云的一份歌单</a>	   	   
                     </p>
                     <p class='today_p'>
             		<a href='/bbs/jsp/userdetail.jsp?uid=1'>
             		我是Admin</a>
					</p></p></div>
					<div class='today_p_wrap'>
             		 <img src='/bbs/images/face/1.png' width='32px' height='32px'>
             		 <p class='today_p'>
             		 <a href='/bbs/jsp/postdetail.jsp?pid=12'>如何发表帖子</a>	   	   
                     </p>
                     <p class='today_p'>
             		<a href='/bbs/jsp/userdetail.jsp?uid=1'>
             		我是Admin</a>
					</p></p></div>
					<div class='today_p_wrap'>
             		 <img src='/bbs/images/face/1.png' width='32px' height='32px'>
             		 <p class='today_p'>
             		 <a href='/bbs/jsp/postdetail.jsp?pid=13'>关于论坛的名字</a>	   	   
                     </p>
                     <p class='today_p'>
             		<a href='/bbs/jsp/userdetail.jsp?uid=1'>
             		我是Admin</a>
					</p></p></div>
					<div class='today_p_wrap'>
             		 <img src='/bbs/images/face/1.png' width='32px' height='32px'>
             		 <p class='today_p'>
             		 <a href='/bbs/jsp/postdetail.jsp?pid=14'>网站是如何写的</a>	   	   
                     </p>
                     <p class='today_p'>
             		<a href='/bbs/jsp/userdetail.jsp?uid=1'>
             		我是Admin</a>
					</p></p></div>
			   </div>
			</div>
		</div>
		</div>
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

