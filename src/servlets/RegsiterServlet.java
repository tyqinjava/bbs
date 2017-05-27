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

import bean.User;
import dao.UserDao;

@SuppressWarnings("serial")
public class RegsiterServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter out = resp.getWriter();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String nickName = req.getParameter("nickname");
		String email = req.getParameter("email");
		UserDao ud = new UserDao();
		HttpSession session = req.getSession();
		String vercode = (String) session.getAttribute("vercode");
		String vr = req.getParameter("vercode");
		if (username == null) {
			if (vercode.equals(vr)) {
				out.print("{\"state\":0}");
				return;
			} else {
				out.print("{\"state\":111}");
				return;
			}
		} else {
			if (password == null || email == null || nickName == null) {
				User user = ud.getUser(username);
				if (user == null) { // 该用户还没被注册
					String data = "{\"state\":0}";
					out.print(data);
					out.close();
				} else { // 该用户被注册
					String data = "{\"state\":1}";
					out.print(data);
					out.close();
				}

			} else {
				User user = new User();
				user.setUser_name(username);
				user.setPassword(password);
				user.setNickname(nickName);
				user.setEmail(email);
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String date = dateFormat.format(now);
				user.setReg_time(date);
				ud.insertUser(user);
				User currentUser = ud.getUser(username);
				session.setAttribute("user", currentUser);
				resp.sendRedirect("/bbs/jsp/main.jsp");
				out.close();
			}
		}

	}
}
