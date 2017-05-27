package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Comment;
import bean.User;
import dao.CommentDao;

//处理发表评论
@SuppressWarnings("serial")
public class CommentServlet extends HttpServlet{
	
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {               
			 req.setCharacterEncoding("utf-8");
			 resp.setContentType("text/html;charset=utf-8");
			 String type=req.getParameter("type");
			 String content=req.getParameter("content");
			 String pid=req.getParameter("pid");
			 PrintWriter out=resp.getWriter();
			 if(type==null||content==null||pid==null){
				 return;
			 }
			 HttpSession session=req.getSession();
			 User user=(User)session.getAttribute("user");
			 if(user==null){
				 out.print("<script type='text/javascript'>"
				    		+ "alert('登录过期！');"
				    		+ "window.location.href='/bbs/index.jsp'"
				    		+ "</script>");
				 return;
			 }		
			 int post_id=Integer.parseInt(pid);
			 Comment com=new Comment();
			 com.setContent(content);
			
			 com.setPost_id(post_id);
			 com.setUser_id(user.getUser_id());
			 Date now=new Date();
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			 String time=sdf.format(now);
			 com.setTime(time);
			 CommentDao cd=new CommentDao();
			 cd.insert(com);
		     out.print("<script type='text/javascript'>"
		    		+ "alert('评论发表成！');"
		    		+ "window.location.href='/bbs/jsp/postdetail.jsp?pid="+pid+"'"
		    		+ "</script>");
			 
		}
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		}
}
