package exam.dto;

import java.util.Date;

public class Employee {
	private int empNo;
	private String empName;
	private Title title;
	private int salary;
	private int gender;
	private Department dno;
	private Date hire_date;
	
	
	public Employee(int empNo) {
		this.empNo = empNo;
	}
	public Employee(int empNo, String empName, Title title, int salary, int gender, Department dno, Date hire_date) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.salary = salary;
		this.gender = gender;
		this.dno = dno;
		this.hire_date = hire_date;
	}
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public Department getDno() {
		return dno;
	}
	public void setDno(Department dno) {
		this.dno = dno;
	}
	public Date getHire_date() {
		return hire_date;
	}
	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}
	@Override
	public String toString() {
		return String.format("%s(%s)",empName, empNo);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + empNo;
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
		Employee other = (Employee) obj;
		if (empNo != other.empNo)
			return false;
		return true;
	}
	
	public Object[] toArray() {
		return new Object[] { empNo, empName, title.getTname(), salary, gender, dno.getDeptName()+"("+dno.getDeptNo()+")",hire_date };
	}
	
	
	
}
