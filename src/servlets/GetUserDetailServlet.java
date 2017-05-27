package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.json.JSONArray;
import org.json.JSONObject;

import bean.Collection;
import bean.Comment;
import bean.Post;
import bean.User;
import dao.CollectionDao;
import dao.CommentDao;
import dao.PostDao;
import dao.UserDao;

@SuppressWarnings("serial")
public class GetUserDetailServlet extends HttpServlet{
	
	 @SuppressWarnings("unused")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
		 
		 req.setCharacterEncoding("utf-8");
		 resp.setContentType("text/plain;charset=utf-8");
		 String type=req.getParameter("type");
		 String uid=req.getParameter("uid");
		 int user_id=0;
		 if(uid!=null){
		    user_id=Integer.parseInt(uid);
		 }
		 PrintWriter out=resp.getWriter();
		 if(type==null)return;
		 HttpSession session=req.getSession();
		 User user=(User)session.getAttribute("user");
		 if(user==null){
			 return;
		 }
		 boolean isSmall=false;
		 if(user_id==user.getUser_id())
			 isSmall=true;
		 
		 if(type.equals("getuserpost")){
			 	UserDao ud=new UserDao();
			    User u=ud.getUser(user_id);//当前浏览的用户
			    if(u==null)return;
			    PostDao pd=new PostDao();
			    List<Post> ps=pd.getPosts(u.getUser_id());//用户发表过的帖子
			    JSONArray jsonArray=new JSONArray();
			    /*time
			    * pid
			    * title
			    * isSamll*/
			    for(Post p:ps){
			    	JSONObject json=new JSONObject();
			    	json.put("time", p.getPost_time());
			    	json.put("pid", p.getPost_id());
			    	json.put("title", p.getTitle());
			    	if(isSmall){
			    		json.put("isSmall", "true");
			    	}else{
			    		json.put("isSmall", "false");
			    	}
			    	jsonArray.put(json);
			    }
			    out.print(jsonArray.toString());
			    
		 }else if(type.equals("delpost")){
			  
			   String pid=req.getParameter("pid");
			   if(pid==null)return;
			   int post_id=Integer.parseInt(pid);
			   PostDao pd=new PostDao();
			   Post p=pd.getPost(post_id);
			   if(p==null)return;
			   pd.delPost(post_id);
			   CollectionDao cld=new CollectionDao();
			   cld.delCollectionByPostId(post_id);
			   CommentDao cd=new CommentDao();
			    
			   out.print("ok");
		 }else if(type.equals("delcollection")){
			   String clid=req.getParameter("clid");
			   if(clid==null)return;
			   int cl_id=Integer.parseInt(clid);
			   CollectionDao cld=new CollectionDao();
			   cld.delCollectionById(cl_id);
			   out.print("ok");
			   return;
		 }else if(type.equals("getusercollection")){
			  if(uid==null){return;}
			  CollectionDao cld=new CollectionDao();
			  List<Collection> cls=cld.getCollectionByUserId(user_id);
			  if(cls.size()==0)return;
			  PostDao pd=new PostDao();
			  UserDao ud=new UserDao();
			  JSONArray jsonArray=new JSONArray();
			  for(Collection c:cls){
				  Post p=pd.getPost(c.getPost_id());
				  int uuid=pd.getUserIdByPostId(p.getPost_id());
				  User u=ud.getUser(uuid);
				  JSONObject json=new JSONObject();
				  /*time
				    *    cl_id
				     *  pid
				     *  title
				     *  isSamll
				     *  uid
				     *  unickname
			          * pid time tile isSmall 
			           */
				  json.put("cl_id",c.getCollection_id());
				  json.put("pid", p.getPost_id());
				  json.put("title", p.getTitle());
				  json.put("time", c.getTime());
				  json.put("uid",u.getUser_id());
				  json.put("unickname", u.getNickname());
				  if(isSmall){
				    json.put("isSmall", "true");
				  }else{
					json.put("isSmall", "false");
				  }
				  jsonArray.put(json);
			  }
			  out.print(jsonArray.toString());
		 }else if(type.equals("getusercomment")){
			 if(uid==null){return;}
			  CommentDao cd=new CommentDao();
			  List<Comment> cs=cd.getCommentByUserId(user_id);
			  JSONArray jsonArray=new JSONArray();
			  for(Comment c:cs){
				 PostDao pd=new PostDao();
				 String isExist="false";
				 Post p=pd.getPost(c.getPost_id()); //发表的帖子
				 JSONObject json=new JSONObject();
				 if(p!=null){
					 isExist="true";
					 UserDao ud=new UserDao(); 
					 User u=ud.getUser(p.getUser_id()); //创建人
					 json.put("uid", u.getUser_id());
					 json.put("title",p.getTitle());
					 json.put("unickname",u.getNickname());
					 json.put("pid",p.getPost_id());
				 }
				 json.put("cid", c.getComment_id());
				 json.put("time",c.getTime());
				 if(isSmall){
				    json.put("isSmall", "true");
				 }else{
					  json.put("isSmall", "false");
				 }
				 json.put("content", c.getContent());
				 json.put("isExist", isExist);
				 jsonArray.put(json);
			  }
			  out.print(jsonArray.toString());
			  /*cid time isSmall content uid title unickname pid isExist*/
		 }else if(type.equals("delcomment")){
			 String cid=req.getParameter("cid");
			 if(cid==null)return;
			 int comment_id=Integer.parseInt(cid);
			 CommentDao cd=new CommentDao();
			 cd.delCommentById(comment_id);
			 out.print("ok");
			 
		 }		 
	}
}
