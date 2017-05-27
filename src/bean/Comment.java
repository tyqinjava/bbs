package bean;

/**
 * 
 * ÆÀÂÛÊµÌåbean
 * 
 * @author acer
 *
 */
public class Comment {
	private int comment_id;
	private int post_id;
	private int user_id;
	private String content;
	private String time;
	private int agree;

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getAgree() {
		return agree;
	}

	public void setAgree(int agree) {
		this.agree = agree;
	}

	@Override
	public String toString() {
		return "Comment [comment_id=" + comment_id + ", post_id=" + post_id + ", user_id=" + user_id + ", content="
				+ content + ", time=" + time + ", agree=" + agree + "]";
	}

}
