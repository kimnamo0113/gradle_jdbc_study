package exam.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exam.dao.EmployeeDao;
import exam.dto.Department;
import exam.dto.Employee;
import exam.dto.Title;
import gradle_jdbc_study.jdbc.ConnectionProvider;


public class EmployeeDaoImpl implements EmployeeDao {
	static final Logger log = LogManager.getLogger();
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public List<Employee> selectEmployeeByAll() throws SQLException {
		String sql = "SELECT e1.empno, e1.empname, e1.title,e1.manager,e1.salary,e1.gender,e1.dno,e1.hire_date,e2.empname as mname,d.deptname,t.tname \r\n" + 
				"from employee e1 \r\n" + 
				"join employee e2 \r\n" + 
				"on (e1.manager=e2.empno)\r\n" + 
				"join department d\r\n" + 
				"on (e1.dno=d.deptno)\r\n" + 
				"join title t\r\n" + 
				"on (e1.title=t.tno)";
		List<Employee> lists = null;
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			log.trace(pstmt);
			if (rs.next()) {
				lists = new ArrayList<Employee>();
				do {
					lists.add(getEmployee(rs));
				}while(rs.next());
			}

		}
		return lists;
	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		return new Employee(rs.getInt("empno"), 
				            rs.getString("empname"), 
				            new Title(rs.getInt("title"),rs.getString("tname")), 
				            new Employee(rs.getInt("manager"),rs.getString("mname")),
				            rs.getInt("salary"),
				            rs.getInt("gender"),
				            new Department(rs.getInt("dno"),rs.getString("deptname")),
				            rs.getDate("hire_date")); 
	}

	@Override
	public Employee selectEmployeeByNo(Employee employee) throws SQLException {
		log.trace("selectEmployeeByNo()");
		String sql = "SELECT * FROM employee,department,title where employee.dno=department.deptno and employee.title=title.tno and employee.empno=?";
		Employee selEmp = null;
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, employee.getEmpNo());
			log.trace(pstmt);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					selEmp = getEmployee(rs);
				}
			}
		}
		System.out.println(selEmp);
		return selEmp;
	}

	@Override
	public int insertEmployee(Employee employee) throws SQLException {
		log.trace("insertEmployee()");
		String sql = "insert into employee(empno,empname,title,manager,salary,gender,dno,hire_date) values(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setInt(1, employee.getEmpNo());
			pstmt.setString(2, employee.getEmpName());
			pstmt.setInt(3, employee.getTitle().getTno());
			pstmt.setInt(4, employee.getManager().getEmpNo());
			pstmt.setInt(5, employee.getSalary());
			pstmt.setInt(6, employee.getGender());
			pstmt.setInt(7, employee.getDno().getDeptNo());
			pstmt.setString(8, sf.format(employee.getHire_date()));
			log.trace(pstmt);
			return pstmt.executeUpdate();
		}
	}

	@Override
	public int deleteEmployee(Employee employee) throws SQLException {
		log.trace("deleteEmployee()");
		String sql = "delete from employee where empno = ?";
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, employee.getEmpNo());
			log.trace(pstmt);
			return pstmt.executeUpdate();
		}
	}

	@Override
	public int updateEmployee(Employee employee) throws SQLException {
		log.trace("updateEmployee()");
		String sql = "update employee "
			       + "set empname=?, title=?, manager=?, salary=?,gender=?, dno=?, hire_date=?"
				   + "where empno=?";
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, employee.getEmpName());
			pstmt.setInt(2, employee.getTitle().getTno());
			pstmt.setInt(3, employee.getManager().getEmpNo());
			pstmt.setInt(4, employee.getSalary());
			pstmt.setInt(5, employee.getGender());
			pstmt.setInt(6, employee.getDno().getDeptNo());
			pstmt.setString(7, sf.format(employee.getHire_date()));
			pstmt.setInt(8, employee.getEmpNo());
			
			System.out.println("----------2-------");
			System.out.println(pstmt);
			log.trace(pstmt);
			return pstmt.executeUpdate();
		}

	}

}
