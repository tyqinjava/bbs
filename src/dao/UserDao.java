package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.User;
import util.DBUtil;

public class UserDao {
	
	public void update(User user){
		Connection cont=DBUtil.getConnection();
    	String sql="update user set user_name=?,password=?,nickname=?,sex=?,birthday=?,user_icon=?,"
    			+ "email=? where user_id=?";
    	try {
			PreparedStatement prepStmt=cont.prepareStatement(sql);
			prepStmt.setString(1, user.getUser_name());
			prepStmt.setString(2,user.getPassword());
			prepStmt.setString(3, user.getNickname());
			prepStmt.setString(4, user.getSex());
			prepStmt.setString(5, user.getBirthday());
			prepStmt.setString(6, user.getUser_icon());
			prepStmt.setString(7, user.getEmail());
			prepStmt.setInt(8, user.getUser_id());
			prepStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	try {
			cont.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
	}
	public List<User> getUserByNickName(String nickname){
		Connection cont=DBUtil.getConnection();
		List<User> us=new ArrayList<User>();
		String sql="select * from user where nickname like ?";
		PreparedStatement prepStmt;
		try {
			prepStmt = cont.prepareStatement(sql);
			prepStmt.setString(1,nickname );
			ResultSet rs = prepStmt.executeQuery();
			while(rs.next()){
				User u=new User();
				u.setUser_name(rs.getString("user_name"));
				u.setBirthday(rs.getString("birthday"));
				u.setEmail(rs.getString("email"));
			    u.setNickname(rs.getString("nickname"));
			    u.setPassword(rs.getString("password"));
			    u.setReg_time(rs.getString("reg_time"));
			    u.setSex(rs.getString("sex"));
			    u.setUser_icon(rs.getString("user_icon"));
			    u.setUser_id(rs.getInt("user_id"));
			    us.add(u);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return us;	
	}
    public User getUser(String username){
    	Connection cont=DBUtil.getConnection();
    	String sql="select * from user where user_name=?";
    	User user=null;
    	try {
			PreparedStatement prepStmt=cont.prepareStatement(sql);
			prepStmt.setString(1, username);
			ResultSet rs=prepStmt.executeQuery();
			if(rs.next()){
				user=new User();
				user.setUser_name(rs.getString("user_name"));
			    user.setUser_id(rs.getInt("user_id"));
			    user.setBirthday(rs.getString("birthday"));
			    user.setEmail(rs.getString("email"));
			    user.setNickname(rs.getString("nickname"));
			    user.setPassword(rs.getString("password"));
			    user.setReg_time(rs.getString("reg_time"));
			    user.setSex(rs.getString("sex"));
			    user.setUser_icon(rs.getString("user_icon"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	try {
			cont.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return user;
    }
    public User getUser(int user_id){
    	Connection cont=DBUtil.getConnection();
    	String sql="select * from user where user_id=?";
    	User user=null;
    	try {
			PreparedStatement prepStmt=cont.prepareStatement(sql);
			prepStmt.setInt(1, user_id);
			ResultSet rs=prepStmt.executeQuery();
			if(rs.next()){
				user=new User();
				user.setUser_name(rs.getString("user_name"));
			    user.setUser_id(rs.getInt("user_id"));
			    user.setBirthday(rs.getString("birthday"));
			    user.setEmail(rs.getString("email"));
			    user.setNickname(rs.getString("nickname"));
			    user.setPassword(rs.getString("password"));
			    user.setReg_time(rs.getString("reg_time"));
			    user.setSex(rs.getString("sex"));
			    user.setUser_icon(rs.getString("user_icon"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	try {
			cont.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return user;
    }
    public void insertUser(User user){
    	
    	Connection cont=DBUtil.getConnection();
    	String sql="insert into user (user_name,password,nickname,email,reg_time) values(?,?,?,?,?)";
    	try {
			PreparedStatement prepStmt=cont.prepareStatement(sql);
			prepStmt.setString(1, user.getUser_name());
			prepStmt.setString(2,user.getPassword());
			prepStmt.setString(3, user.getNickname());
			prepStmt.setString(4, user.getEmail());
			prepStmt.setString(5,user.getReg_time());
			prepStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	try {
			cont.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
}
