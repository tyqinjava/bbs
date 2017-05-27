package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Comment;
import util.DBUtil;

public class CommentDao {
	
	 public void insert(Comment com){
		 
		 Connection cont=DBUtil.getConnection();
	    	String sql="insert into comment (post_id,user_id,content,time)values(?,?,?,?)";
	    	try {
				PreparedStatement prepStmt=cont.prepareStatement(sql);
				prepStmt.setInt(1, com.getPost_id());
				prepStmt.setInt(2, com.getUser_id());
				prepStmt.setString(3, com.getContent());
				prepStmt.setString(4, com.getTime());
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
	 public void delCommentById(int comment_id){
		 
		Connection cont=DBUtil.getConnection();
	    String sql="delete from comment where comment_id=?";
	    	try {
				PreparedStatement prepStmt=cont.prepareStatement(sql);
				prepStmt.setInt(1,comment_id);
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
	 public List<Comment> getComment(int post_id){
		    Connection cont=DBUtil.getConnection();
	    	String sql="select * from comment where post_id=?";
	    	List<Comment> coms=new ArrayList<Comment>();
	    	try {
				PreparedStatement prepStmt=cont.prepareStatement(sql);
				prepStmt.setInt(1, post_id);
				ResultSet rs=prepStmt.executeQuery();
				while(rs.next()){
					Comment com=new Comment();
					com.setPost_id(rs.getInt("post_id"));
					com.setComment_id(rs.getInt("comment_id"));
					com.setTime(rs.getString("time"));
					com.setUser_id(rs.getInt("user_id"));
					com.setContent(rs.getString("content"));
					com.setAgree(rs.getInt("agree"));
					coms.add(com);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	try {
				cont.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	return coms;
	 }
	 public List<Comment> getCommentByUserId(int user_id){
		    Connection cont=DBUtil.getConnection();
	    	String sql="select * from comment where user_id=?";
	    	List<Comment> coms=new ArrayList<Comment>();
	    	try {
				PreparedStatement prepStmt=cont.prepareStatement(sql);
				prepStmt.setInt(1, user_id);
				ResultSet rs=prepStmt.executeQuery();
				while(rs.next()){
					Comment com=new Comment();
					com.setPost_id(rs.getInt("post_id"));
					com.setComment_id(rs.getInt("comment_id"));
					com.setTime(rs.getString("time"));
					com.setUser_id(rs.getInt("user_id"));
					com.setContent(rs.getString("content"));
					com.setAgree(rs.getInt("agree"));
					coms.add(com);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	try {
				cont.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	return coms;
	 }
	 public void update(Comment c){
		 Connection cont=DBUtil.getConnection();
	    	String sql="update comment set agree=? where comment_id=?";
	    	try {
				PreparedStatement prepStmt=cont.prepareStatement(sql);
				prepStmt.setInt(1,c.getAgree());
				prepStmt.setInt(2, c.getComment_id());
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
	 public Comment getCommentById(int comment_id){
		    Connection cont=DBUtil.getConnection();
	    	String sql="select * from comment where comment_id=?";
	    	Comment com=null;
	    	try {
				PreparedStatement prepStmt=cont.prepareStatement(sql);
				prepStmt.setInt(1, comment_id);
				ResultSet rs=prepStmt.executeQuery();
				if(rs.next()){
					com=new Comment();
					com.setPost_id(rs.getInt("post_id"));
					com.setComment_id(rs.getInt("comment_id"));
					com.setTime(rs.getString("time"));
					com.setUser_id(rs.getInt("user_id"));
					com.setContent(rs.getString("content"));
					com.setAgree(rs.getInt("agree"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	try {
				cont.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	return com;
	 }
	 public List<Comment> getRangeOfCommentByDesc(int start,int end,int post_id){
		    Connection cont=DBUtil.getConnection();
	    	String sql="select * from comment where post_id=? order by comment_id desc limit ?,?";
	    	List<Comment> coms=new ArrayList<Comment>();
	    	try {
				PreparedStatement prepStmt=cont.prepareStatement(sql);
				prepStmt.setInt(1, post_id);
				prepStmt.setInt(2,start);
				prepStmt.setInt(3, end);
				ResultSet rs=prepStmt.executeQuery();
				while(rs.next()){
					Comment com=new Comment();
					com.setPost_id(rs.getInt("post_id"));
					com.setComment_id(rs.getInt("comment_id"));
					com.setTime(rs.getString("time"));
					com.setUser_id(rs.getInt("user_id"));
					com.setContent(rs.getString("content"));
					com.setAgree(rs.getInt("agree"));
					coms.add(com);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	try {
				cont.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	return coms;
	 }
	 public List<Comment> getHotComment(int post_id){
		    Connection cont=DBUtil.getConnection();
	    	String sql="SELECT * from comment WHERE post_id=? and agree >15 order by agree LIMIT 0,10;";
	    	List<Comment> coms=new ArrayList<Comment>();
	    	try {
				PreparedStatement prepStmt=cont.prepareStatement(sql);
				prepStmt.setInt(1, post_id);
				ResultSet rs=prepStmt.executeQuery();
				while(rs.next()){
					Comment com=new Comment();
					com.setPost_id(rs.getInt("post_id"));
					com.setComment_id(rs.getInt("comment_id"));
					com.setTime(rs.getString("time"));
					com.setUser_id(rs.getInt("user_id"));
					com.setContent(rs.getString("content"));
					com.setAgree(rs.getInt("agree"));
					coms.add(com);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	try {
				cont.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	return coms;
	 }
}
