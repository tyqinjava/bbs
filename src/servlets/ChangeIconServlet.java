package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.UserDao;
/**
 * 
 * ���������û��޸�ͷ�� �����ϴ�ͷ��
 * @author acer
 *
 */
@SuppressWarnings("serial")
public class ChangeIconServlet extends HttpServlet{
		
	  @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  	
		/*    UserDao ud=new UserDao();
		    User user=ud.getUser("tyqinjava");
		    user.setUser_icon("/face/1.png");
		    ud.update(user);*/
    }
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		     
		           req.setCharacterEncoding("utf-8");
		           resp.setContentType("text/plain;charset=utf-8");
		           PrintWriter out=resp.getWriter();
		           String changeIcon=req.getParameter("changeIcon");
		           String iconName=req.getParameter("iconname");
		           HttpSession session=req.getSession();
		           UserDao ud=new UserDao();
		           
		           
		           if(changeIcon!=null&&iconName!=null){  //����ͷ���߼�
		        	   User user=(User)session.getAttribute("user"); //session��ͬ�������Ȳ�����
		        	   if(user==null){
		        		  out.print("{\"state\":1}"); //session ����
		        	   }else{
		        		  user.setUser_icon("face/"+iconName);
		        		  ud.update(user);
		        		  out.print("{\"state\":0}");
		        	   }
		           }else{
		        	   User user=(User)session.getAttribute("user"); //session��ͬ�������Ȳ�����
		        	   if(user==null){
		        		  out.print("{\"state\":1}"); //session ����
		        	   }else{
		        		  user.setUser_icon(iconName); //�ϴ�ͷ��
		        		  ud.update(user);
		        		  out.print("{\"state\":0}");
		        	   }
		           }
	}
}
