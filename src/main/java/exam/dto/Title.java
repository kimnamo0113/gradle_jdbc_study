package exam.dto;

import java.sql.SQLException;

import exam.dao.TitleDao;
import exam.daoImpl.TitleDaoImpl;

public class Title {
	private int tno;
	private String tname;
	
	
	public Title() {
		super();
	}
	
	public Title(int tno) {
		this.tno = tno;
	}

	public Title(int tno, String title) {
		super();
		this.tno = tno;
		this.tname = title;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + tno;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Title other = (Title) obj;
		if (tno != other.tno)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s(%s)",tname, tno);
	}
	
	public String toListString(String tname) {
		return String.format("%s(%s)",tname, tno);
	}

	public Object[] toArray() {
		String no=String.format("T%03d", tno);
		return new Object[] {no,tname};
	}


}
