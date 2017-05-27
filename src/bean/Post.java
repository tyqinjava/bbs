package bean;

/**
 * Ìû×ÓÊµÌåbean
 * 
 * @author tyq
 *
 */
public class Post {

	private int post_id;
	private int user_id;
	private String post_time;
	private int hot;
	private String src;
	private String type;

	@Override
	public String toString() {
		return "Post [post_id=" + post_id + ", user_id=" + user_id + ", post_time=" + post_time + ", hot=" + hot
				+ ", src=" + src + ", type=" + type + ", title=" + title + "]";
	}

	private String title;

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getPost_time() {
		return post_time;
	}

	public void setPost_time(String post_time) {
		this.post_time = post_time;
	}

	public int getHot() {
		return hot;
	}

	public void setHot(int hot) {
		this.hot = hot;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
