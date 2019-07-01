package exam.dto;

public class Title {
	private String tno;
	private String tname;
	
	
	public Title() {
		super();
	}

	public Title(String tno, String title) {
		super();
		this.tno = tno;
		this.tname = title;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	@Override
	public String toString() {
		return tname;
	}


}
