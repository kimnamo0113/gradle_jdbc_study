package exam.dto;

import java.sql.SQLException;
import java.util.Date;

import exam.dao.EmployeeDao;
import exam.daoImpl.EmployeeDaoImpl;

public class Employee {
	private int empNo;
	private String empName;
	private Title title;
	private int salary;
	private int gender;
	private Department dno;
	private Date hire_date;
	private Employee manager;
	
	
	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	public Employee(int empNo) {
		this.empNo = empNo;
	}
	
	
	public Employee(int empNo, String empName) {
		this.empNo = empNo;
		this.empName = empName;
	}
	public Employee(int empNo, String empName, Title title,Employee manager, int salary, int gender, Department dno, Date hire_date) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager=manager;
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
		/*
		 * EmployeeDao empDao=new EmployeeDaoImpl(); Employee emp=new Employee(empNo);
		 * try { emp=empDao.selectEmployeeByNo(emp); System.out.println(emp); } catch
		 * (SQLException e) { e.printStackTrace(); }
		 */
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
		String genderStr="";
		if(gender==1) {
			genderStr="남";
		}else
			genderStr="여";
		String no=String.format("E%06d", empNo); 
		return new Object[] { no, empName, title,manager,  salary, genderStr, dno.getDeptName()+"("+dno.getDeptNo()+")",hire_date };
	}
	
	
	
}
