package exam.dto;

public class Title {
	private int tno;
	private String tname;
	
	
	public Title() {
		super();
	}

	public Title(int tno, String title) {
		super();
		this.tno = tno;
		this.tname = title;
	}
	


	public Title(int tno) {
		this.tno=tno;
	}

	public int getTno() {
		return tno;
	}

	public void setTno(int tno) {
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
		return String.format("%s(%s)",tname, tno);
	}

	public Object[] toArray() {
		String no=String.format("T%03d", tno);
		return new Object[] {no,tname};
	}


}
