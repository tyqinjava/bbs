package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Collection;
import bean.Comment;
import bean.Post;
import bean.User;
import dao.CollectionDao;
import dao.CommentDao;
import dao.PostDao;

/**
 * 
 * 
 * 此类用于处理用户点赞，收藏，顶贴
 * @author acer
 *
 */
@SuppressWarnings("serial")
public class HandleServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		      req.setCharacterEncoding("utf-8");
		      resp.setContentType("text/plain;charset=utf-8");
		      PrintWriter out=resp.getWriter();
		      String type=req.getParameter("type");
		      HttpSession session=req.getSession();
			  User user=(User)session.getAttribute("user");
			  if(user==null){
				 out.print("session");
				 return;
			   }
			  if(type.equals("agree")){
				  String cid=req.getParameter("cid");
				  List<Integer> agree=(List<Integer>) session.getAttribute("agree");
				  int comment_id=Integer.parseInt(cid);
				  if(agree==null){
				     CommentDao cd=new CommentDao();
				     Comment c=cd.getCommentById(comment_id);
				     c.setAgree(c.getAgree()+1);
				     cd.update(c);
				     agree=new ArrayList<Integer>();
				     session.setAttribute("agree",agree);
				     agree.add(c.getComment_id());
				     out.print(c.getAgree());
				     return;
				  } 
				  if(agree.size()==0){
					  CommentDao cd=new CommentDao();
					  Comment c=cd.getCommentById(comment_id);
  				      c.setAgree(c.getAgree()+1);
  			 	      cd.update(c);
  			 	      agree.add(comment_id);
  				      out.print(c.getAgree());
       	         }else{
       	        	 boolean isAgain=false;
       	        	 for(Integer i:agree){
       	        		if(i==comment_id){
       	        			isAgain=true;
       	        			break;
       	        		}else {
       	        			continue;
       	        		}
       	        	 }
       	        	 if(isAgain){
       	        		 out.print("agree");
       	        		 return;
       	        	 }
       	        	  CommentDao cd=new CommentDao();
 				      Comment c=cd.getCommentById(comment_id);
 				      c.setAgree(c.getAgree()+1);
 			 	      cd.update(c);
 			 	      agree.add(comment_id);
 				      out.print(c.getAgree());
 				      return;
       	         }
			  }else if(type.equals("collection")){
				  int  pid=Integer.parseInt(req.getParameter("pid"));
				  CollectionDao cld=new CollectionDao();
				  List<Collection> cs=cld.getCollectionByUserId(user.getUser_id());
				  boolean isAgain=false;
				  if(cs.size()!=0){
					  for(Collection c:cs){
						  if(c.getPost_id()==pid){
							  isAgain=true;
							  break;
						  }else{
							  continue;
						  }
					  }
				  }
				  if(isAgain){
					  out.print("again");
					  return;
				  }
				  Collection c=new Collection();
				  c.setPost_id(pid);
				  c.setUser_id(user.getUser_id());
				  Date now=new Date();
				  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
				  String time=sdf.format(now);
				  c.setTime(time);
				  cld.insert(c);
				  out.print("ok");
				  return;
			  }else if(type.equals("hot")){
		         String pid=req.getParameter("pid");
       	         int post_id=Integer.parseInt(pid);
       	         List<Integer> hot=(List<Integer>)session.getAttribute("hot");
       	         if(hot==null){
       	        	  PostDao pd=new PostDao();
   				      Post p=pd.getPost(post_id);
   				      p.setHot(p.getHot()+1);
   			 	      pd.update(p);
   			 	      hot=new ArrayList<Integer>();
   			 	      hot.add(post_id);
   			 	      session.setAttribute("hot", hot);
   				      out.print("ok");
   				      return;
       	         }else if(hot.size()==0){
       	        	  PostDao pd=new PostDao();
  				      Post p=pd.getPost(post_id);
  				      p.setHot(p.getHot()+1);
  			 	      pd.update(p);
  			 	      hot.add(post_id);
  				      out.print("ok");
       	         }else{
       	        	 boolean isAgain=false;
       	        	 for(Integer i:hot){
       	        		if(i==post_id){
       	        			isAgain=true;
       	        			break;
       	        		}else {
       	        			continue;
       	        		}
       	        	 }
       	        	 if(isAgain){
       	        		 out.print("again");
       	        		 return;
       	        	 }
       	        	  PostDao pd=new PostDao();
 				      Post p=pd.getPost(post_id);
 				      p.setHot(p.getHot()+1);
 			 	      pd.update(p);
 			 	      hot.add(post_id);
 				      out.print("ok");
 				      return;
       	         }
			  }
	}
}
