package servlets;


/**
 * 
 * 此类用于处理 用户登录的验证
 * 
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
public class LoginServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			resp.setContentType("text/plain;charset=utf-8");
			req.setCharacterEncoding("utf-8");
			PrintWriter out=resp.getWriter();
			String username=req.getParameter("username");
			String password=req.getParameter("password");
			HttpSession session=req.getSession();
			if(username==null||password==null){
				return;
			}
			UserDao ud=new UserDao();
			User user=ud.getUser(username);
			if(user!=null){
				if(user.getPassword().equals(password)){
					out.print("{\"state\":0}");
					session.setAttribute("user", user);
					out.close();
				}else{
					out.print("{\"state\":2}");
					out.close();
				}
			}else{
				out.print("{\"state\":1}");
				out.close();
			}
			
			
	}
}
