package dto;

public class GradeDTO {
	private String id;
	private String passwd;
	private String name;
	private String nick;
	private int gradeNo;
	
	public GradeDTO() { 	}

	public GradeDTO(String id, String passwd, String name, String nick, int gradeNo) {
		super();
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.nick = nick;
		this.gradeNo = gradeNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getGradeNo() {
		return gradeNo;
	}

	public void setGradeNo(int gradeNo) {
		this.gradeNo = gradeNo;
	}

	@Override
	public String toString() {
		return "GradeDTO [id=" + id + ", passwd=" + passwd + ", name=" + name + ", nick=" + nick + ", gradeNo="
				+ gradeNo + "]";
	}
	
	
}
