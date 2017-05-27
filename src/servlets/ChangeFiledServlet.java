package servlets;


/**
 * 
 *  此类用于处理基本资料的更改
 *  
 *  
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.User;
import dao.UserDao;

@SuppressWarnings("serial")
public class ChangeFiledServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		    req.setCharacterEncoding("utf-8");
			resp.setContentType("text/plain;charset=utf-8");
			PrintWriter out=resp.getWriter();
			String type=req.getParameter("type");
			String value=req.getParameter("value");
		 	UserDao ud=new UserDao();
		 	HttpSession session=req.getSession();
			if(type==null&&value==null)return;
			if(type.equals("changeusername")){
				User user=ud.getUser(value);
				if(user==null){
					User us=(User)session.getAttribute("user");
					if(us==null){
		        		  out.print("{\"state\":1}"); //session 过期
		        	   }else{
		        		  us.setUser_name(value);
		        		  ud.update(us);
		        		  out.print("{\"state\":0}");//成功
		        	 }
				}else{
					 out.print("{\"state\":2}"); //用户已存在
				}
			}else if(type.equals("changenickname")){
				User us=(User)session.getAttribute("user");
				if(us==null){
	        		  out.print("{\"state\":1}"); //session 过期
	        	   }else{
	        		  us.setNickname(value);
	        		  ud.update(us);
	        		  out.print("{\"state\":0}");//成功
	        	 }
			}else if(type.equals("setsex")){
				User us=(User)session.getAttribute("user");
				if(us==null){
	        		  out.print("{\"state\":1}"); //session 过期
	        	   }else{
	        		  us.setSex(value);
	        		  ud.update(us);
	        		  out.print("{\"state\":0}");//成功
	        	 }
			}else if(type.equals("setbirthday")){
				User us=(User)session.getAttribute("user");
				if(us==null){
	        		  out.print("{\"state\":1}"); //session 过期
	        	   }else{
	        		  us.setBirthday(value);
	        		  ud.update(us);
	        		  out.print("{\"state\":0}");//成功
	        	 }
			}else if(type.equals("changeemail")){
					User us=(User)session.getAttribute("user");
					if(us==null){
		        		  out.print("{\"state\":1}"); //session 过期
		        	   }else{
		        		  us.setEmail(value);;
		        		  ud.update(us);
		        		  out.print("{\"state\":0}");//成功
		        	 }
			}else if(type.equals("changepassword")){
				User us=(User)session.getAttribute("user");
				  if(us==null){
	        		  out.print("{\"state\":1}"); //session 过期
	        	   }else{
	        		  String[] v=value.split(",");
	        		  String old=v[0];
	        		  String New=v[1];
	        		  if(us.getPassword().equals(old)){
	        			  us.setPassword(New);
	        			  ud.update(us);
		        		  out.print("{\"state\":0}");//成功
	        		  }else{
	        			  out.print("{\"state\":2}");//密码输入错误
	        		  }
	        	 }
			}
			
	}
}
