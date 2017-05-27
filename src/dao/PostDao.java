package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Post;
import util.DBUtil;

public class PostDao {

	Connection cont = DBUtil.getConnection();

	public void insert(Post p) {
		Connection cont = DBUtil.getConnection();
		String sql = "insert into post (user_id,post_time,src,type,title) values(?,?,?,?,?)";
		try {
			PreparedStatement prepStmt = cont.prepareStatement(sql);
			prepStmt.setInt(1, p.getUser_id());
			prepStmt.setString(2, p.getPost_time());
			prepStmt.setString(3, p.getSrc());
			prepStmt.setString(4, p.getType());
			prepStmt.setString(5, p.getTitle());
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
	public void update(Post p){
		Connection cont = DBUtil.getConnection();
		String sql="update post set user_id=?,type=?,src=?,hot=?,title=? where post_id=?";
		try {
			PreparedStatement prepStmt = cont.prepareStatement(sql);
			prepStmt.setInt(1, p.getUser_id());
			prepStmt.setString(2, p.getType());
			prepStmt.setString(3, p.getSrc());
			prepStmt.setInt(4, p.getHot());
			prepStmt.setString(5, p.getTitle());
			prepStmt.setInt(6, p.getPost_id());
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
	public Post getPost(int id) {
		Connection cont = DBUtil.getConnection();
		Post p = null;
		String sql = "select * from post where post_id=?";
		try {
			PreparedStatement prepStmt = cont.prepareStatement(sql);
			prepStmt.setInt(1, id);
			ResultSet rs = prepStmt.executeQuery();
			if (rs.next()) {
				p = new Post();
				p.setSrc(rs.getString("src"));
				p.setPost_id(rs.getInt("post_id"));
				p.setUser_id(rs.getInt("user_id"));
				p.setTitle(rs.getString("title"));
				p.setType(rs.getString("type"));
				p.setPost_time(rs.getString("post_time"));
				p.setHot(rs.getInt("hot"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			cont.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	public List<Post> getPostsByTtile(String title) {
		Connection cont = DBUtil.getConnection();
		List<Post> posts = new ArrayList<Post>();
		String sql = "select * from post where title like ?";
		try {
			PreparedStatement prepStmt = cont.prepareStatement(sql);
			prepStmt.setString(1,title);
			ResultSet rs = prepStmt.executeQuery();
			while (rs.next()) {
				Post p = new Post();
				p.setPost_id(rs.getInt("post_id"));
				p.setUser_id(rs.getInt("user_id"));
				p.setTitle(rs.getString("title"));
				p.setType(rs.getString("type"));
				p.setPost_time(rs.getString("post_time"));
				p.setHot(rs.getInt("hot"));
				p.setSrc(rs.getString("src"));
				posts.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			cont.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return posts;
	}
	public int getMaxCountByType(String type){
	     Connection cont=DBUtil.getConnection();
	     String sql=null;
	     PreparedStatement prepStmt=null;
	     if(type==null){
	    	 sql="select count(*) from post";
	    	 try {
				prepStmt = cont.prepareStatement(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	     }else{
	    	 sql="select count(*) from post where type=?";
	    	 try {
				prepStmt = cont.prepareStatement(sql);
				prepStmt.setString(1, type);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	     }
		
		int count=0;
		try {
			
			ResultSet rs=prepStmt.executeQuery();
			if(rs.next()){
				count=rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public List<Post> getPosts(int user_id) {
		Connection cont = DBUtil.getConnection();
		List<Post> posts = new ArrayList<Post>();
		String sql = "select * from post where user_id=?";
		try {
			PreparedStatement prepStmt = cont.prepareStatement(sql);
			prepStmt.setInt(1, user_id);
			ResultSet rs = prepStmt.executeQuery();
			while (rs.next()) {
				Post p = new Post();
				p.setPost_id(rs.getInt("post_id"));
				p.setUser_id(rs.getInt("user_id"));
				p.setTitle(rs.getString("title"));
				p.setType(rs.getString("type"));
				p.setPost_time(rs.getString("post_time"));
				p.setHot(rs.getInt("hot"));
				p.setSrc(rs.getString("src"));
				posts.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			cont.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return posts;
	}
	public List<Post> getPostsByTimeAndHot(String time) {
		Connection cont = DBUtil.getConnection();
		List<Post> posts = new ArrayList<Post>();
		String sql = "select * from post where post_time like ? order by hot desc limit 0,12";
		try {
			PreparedStatement prepStmt = cont.prepareStatement(sql);
			prepStmt.setString(1,time);
			ResultSet rs = prepStmt.executeQuery();
			while (rs.next()) {
				Post p = new Post();
				p.setPost_id(rs.getInt("post_id"));
				p.setUser_id(rs.getInt("user_id"));
				p.setTitle(rs.getString("title"));
				p.setType(rs.getString("type"));
				p.setPost_time(rs.getString("post_time"));
				p.setHot(rs.getInt("hot"));
				p.setSrc(rs.getString("src"));
				posts.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			cont.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return posts;
	}

	public List<Post> getRangeOfPostByTypeDesc(String type, int start, int end) {
		
		Connection cont = DBUtil.getConnection();
		List<Post> posts = new ArrayList<Post>();
		PreparedStatement prepStmt = null;
		String sql = null;
		if (type == null) {
			sql = "select * from post order by "
					+ "post_id desc limit ?,?;";
			try {
				prepStmt = cont.prepareStatement(sql);
				prepStmt.setInt(1, start);
				prepStmt.setInt(2, end);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			sql = "select * from post where type=? "
					+ "order by post_id desc limit ?,?;";
			try {
				prepStmt = cont.prepareStatement(sql);
				prepStmt.setString(1, type);
				prepStmt.setInt(2, start);
				prepStmt.setInt(3, end);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			ResultSet rs = prepStmt.executeQuery();
			while (rs.next()) {
				Post p = new Post();
				p.setPost_id(rs.getInt("post_id"));
				p.setUser_id(rs.getInt("user_id"));
				p.setTitle(rs.getString("title"));
				p.setType(rs.getString("type"));
				p.setPost_time(rs.getString("post_time"));
				p.setHot(rs.getInt("hot"));
				p.setSrc(rs.getString("src"));
				posts.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			cont.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return posts;
	}
	public void delPost(int post_id){
		Connection cont=DBUtil.getConnection();
		String sql="delete from post where post_id=?";
		try {
			PreparedStatement prepStmt=cont.prepareStatement(sql);
			prepStmt.setInt(1, post_id);
			prepStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public int getUserIdByPostId(int post_id){
		int uid=0;
	    Connection cont=DBUtil.getConnection();
		String sql="select user_id from post where post_id=?";
		try {
			PreparedStatement prepStmt=cont.prepareStatement(sql);
			prepStmt.setInt(1, post_id);
			ResultSet rs=prepStmt.executeQuery();
			if(rs.next()){
				uid=rs.getInt("user_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uid;
	}
	public List<Post> getRangeOfPostByDesc(int start, int end) {
			return getRangeOfPostByTypeDesc(null, start, end);
	}
}
