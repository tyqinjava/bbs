package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Post;
import bean.User;
import dao.PostDao;
/**
 * 
 * 
 * 此类用户处理用户发表的帖子
 * @author acer
 *
 */
@SuppressWarnings("serial")
public class PublishPostServlet extends HttpServlet	{
		
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    		
	}
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    			req.setCharacterEncoding("utf-8");
    			resp.setContentType("text/html;charset=utf-8");
    			PrintWriter out=resp.getWriter();
    			String type=req.getParameter("type");
    			String title=req.getParameter("title");
    			String content=req.getParameter("content");
    			if(type==null||title==null||content==null){
    				return;
    			}
    			HttpSession session=req.getSession();
    			User user=(User)session.getAttribute("user");
    			if(user==null){
    				out.print("<script type='text/javascript'>"+""
    						+ "alert('系统检测您的登录过期，为你跳转登录界面');"
    						+ "window.location='/bbs/index.jsp';"
    						+ "</script>");
    				out.close();
    				return;
    			}
    			ServletContext sc=getServletContext();
    			String path=sc.getRealPath("/WEB-INF/tie");
    			String fileid=UUID.randomUUID().toString();
    			PrintWriter outContent=new PrintWriter(new File(path+"/"+fileid),"utf-8");
    			outContent.print(content);
    			outContent.flush();
    			outContent.close();
    			PostDao pd=new PostDao();
    			Post p=new Post();
    			p.setUser_id(user.getUser_id());
    			p.setSrc("/"+fileid);
    			p.setTitle(title);
    			p.setType(type);
    			Date now=new Date();
    			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    			String formatDate=sdf.format(now);
    			p.setPost_time(formatDate);
    			pd.insert(p);//插入到数据库中
    			List<Post> ps=pd.getPosts(user.getUser_id());
    			int post_id=getMaxIdPost(ps);
    		    resp.sendRedirect("/bbs/jsp/postdetail.jsp?pid="+post_id);
    	 }
    	public int getMaxIdPost(List<Post> posts){
    		if(posts.size()==0)return 0;
    		int max=-1;
    		for(int i=0;i<posts.size();i++){
    			int t=posts.get(i).getPost_id();
    			if(t>max){
    				max=t;
    			}
    		}
    		return max;
    	}
}
