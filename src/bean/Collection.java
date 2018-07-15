package bean;

public class Collection {
	private int collection_id;
	private int user_id;
	private int post_id;
	
	private String time;

	public int getCollection_id() {
		return collection_id;
	}

	public void setCollection_id(int collection_id) {
		this.collection_id = collection_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
