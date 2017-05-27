package bean;

/**
 * 
 * 
 * @author 用户实体bean
 *
 */
public class User {
	private int user_id; 
	private String user_name;
	private String password;
	private String nickname;
	private String sex;
	private String birthday;
	private String user_icon;
	private String email;
	private String reg_time; //注册时间

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getUser_icon() {
		return user_icon;
	}

	public void setUser_icon(String user_icon) {
		this.user_icon = user_icon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReg_time() {
		return reg_time;
	}

	public void setReg_time(String reg_time) {
		this.reg_time = reg_time;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name + ", password=" + password + ", nickname="
				+ nickname + ", sex=" + sex + ", birthday=" + birthday + ", user_icon=" + user_icon + ", email=" + email
				+ ", reg_time=" + reg_time + "]";
	}
	
}
