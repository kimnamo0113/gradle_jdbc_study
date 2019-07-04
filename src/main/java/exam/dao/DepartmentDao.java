package exam.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import exam.dto.Department;
import exam.dto.Employee;



public interface DepartmentDao {
	List<Department> selectDepartmentByAll();
	Department selectDepartmentByNo(Department dept) throws SQLException;
	int insertDepartment(Department dept) throws SQLException ;
	int deleteDepartment(Department dept) throws SQLException;
	int updateDepartment(Department dept) throws SQLException;
	
	List<Employee> selectDeptNo(Department dept);
	Employee getEployee(ResultSet rs) throws SQLException;
}
