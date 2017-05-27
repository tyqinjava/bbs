package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.Comment;
import bean.User;
import dao.CommentDao;
import dao.UserDao;
//获取评论
@SuppressWarnings("serial")
public class GetCommentServlet extends HttpServlet{
		
	    @Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    	
		}
	    @SuppressWarnings("unused")
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	    	       
	    	 req.setCharacterEncoding("utf-8");
	    	 resp.setContentType("text/plain;charset=utf-8");
	    	 PrintWriter out=resp.getWriter();
	    	 String strStart=req.getParameter("start");
	    	 String strEnd=req.getParameter("end");
	    	 String strPid=req.getParameter("pid");
	    	 String hot=req.getParameter("hot");
	    	 List<String[]> agree=new ArrayList<String[]>(); 
	    	 if(strStart==null||strEnd==null||strPid==null)return;
	    	 int start=Integer.parseInt(strStart);
	    	 int end=Integer.parseInt(strEnd);
	    	 int pid=Integer.parseInt(strPid);
	    	 
	    	 if(hot!=null){
	    		 CommentDao cd=new CommentDao();
		    	 List<Comment> coms=cd.getHotComment(pid); //获取热门评论
		    	 if(coms.size()==0){
		    		 out.print("[]");
		    		 return;
		    	 }
		    	 JSONArray jsonArray=new JSONArray();
		    	 UserDao ud=new UserDao();
				 for(Comment c:coms){
					 User u=ud.getUser(c.getUser_id());
					 JSONObject json=new JSONObject();
					 json.put("cid", c.getComment_id());
					 json.put("uid", u.getUser_id());
					 json.put("nickname", u.getNickname());
					 json.put("uicon", u.getUser_icon());
					 json.put("time", c.getTime());
					 json.put("agree", c.getAgree());
					 json.put("content", c.getContent());
					 jsonArray.put(json);
				 }
				 out.print(jsonArray.toString());
				 return;
	    	 }
	    	 CommentDao cd=new CommentDao();
	    	 List<Comment> coms=cd.getRangeOfCommentByDesc(start, end, pid);
	    	 if(coms.size()==0){
	    		 out.print("[]");
	    		 return;
	    	 }
	    	 JSONArray jsonArray=new JSONArray();
	    	 UserDao ud=new UserDao();
			 for(Comment c:coms){
				 User u=ud.getUser(c.getUser_id());  
				 JSONObject json=new JSONObject();
				 json.put("cid", c.getComment_id());
				 json.put("uid", u.getUser_id());
				 json.put("nickname", u.getNickname());
				 json.put("uicon", u.getUser_icon());
				 json.put("time", c.getTime());
				 json.put("agree", c.getAgree());
				 json.put("content", c.getContent());
				 jsonArray.put(json);
			 }
			 out.print(jsonArray.toString());
		}
	   
}
