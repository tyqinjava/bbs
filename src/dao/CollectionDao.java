package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Collection;
import util.DBUtil;

public class CollectionDao {
		
	public void insert(Collection c){
		
		Connection cont=DBUtil.getConnection();
		String sql="insert into collection(user_id,post_id,time)values(?,?,?) ";
		try {
			PreparedStatement prepStmt=cont.prepareStatement(sql);
			prepStmt.setInt(1, c.getUser_id());
			prepStmt.setInt(2, c.getPost_id());
			prepStmt.setString(3,c.getTime());
			prepStmt.execute();
		} catch (SQLException e1) {
				e1.printStackTrace();
		}	
		try {
			cont.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void delCollectionByPostId(int post_id){
		Connection cont=DBUtil.getConnection();
		String sql="delete from collection where post_id=?";
		PreparedStatement prepStmt;
		try {
			prepStmt = cont.prepareStatement(sql);
			prepStmt.setInt(1,post_id);
			prepStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void delCollectionById(int cl_id){
		Connection cont=DBUtil.getConnection();
		String sql="delete from collection where collection_id=?";
		PreparedStatement prepStmt;
		try {
			prepStmt = cont.prepareStatement(sql);
			prepStmt.setInt(1,cl_id);
			prepStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void delCollectionByUserId(int user_id){
		Connection cont=DBUtil.getConnection();
		String sql="delete from collection where user_id=?";
		PreparedStatement prepStmt;
		try {
			prepStmt = cont.prepareStatement(sql);
			prepStmt.setInt(1,user_id);
			prepStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public List<Collection> getCollectionByUserId(int user_id){
		
		Connection cont=DBUtil.getConnection();
		List<Collection> cs=new ArrayList<Collection>();
		String sql="select * from collection where user_id=?";
		try {
			PreparedStatement prepStmt=cont.prepareStatement(sql);
			prepStmt.setInt(1,user_id);
		    ResultSet rs=prepStmt.executeQuery();
		    while(rs.next()){
		    	Collection c=new Collection();
		    	c.setCollection_id(rs.getInt("collection_id"));
		    	c.setPost_id(rs.getInt("post_id"));
		    	c.setUser_id(rs.getInt("user_id"));
		    	c.setTime(rs.getString("time"));
		    	cs.add(c);
		    }
		} catch (SQLException e1) {
				e1.printStackTrace();
		}	
		try {
			cont.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cs;
	}
public List<Collection> getCollectionPostId(int post_id){
		
		Connection cont=DBUtil.getConnection();
		List<Collection> cs=new ArrayList<Collection>();
		String sql="select * from collection where post_id=?";
		try {
			PreparedStatement prepStmt=cont.prepareStatement(sql);
			prepStmt.setInt(1,post_id);
		    ResultSet rs=prepStmt.executeQuery();
		    while(rs.next()){
		    	Collection c=new Collection();
		    	c.setCollection_id(rs.getInt("collection_id"));
		    	c.setPost_id(rs.getInt("post_id"));
		    	c.setUser_id(rs.getInt("user_id"));
		    	c.setTime(rs.getString("time"));
		    	cs.add(c);
		    }
		} catch (SQLException e1) {
				e1.printStackTrace();
		}	
		try {
			cont.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cs;
	}
}
