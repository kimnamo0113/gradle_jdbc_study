package gradle_jdbc_study.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gradle_jdbc_study.dao.AbstractDao;
import gradle_jdbc_study.dto.Department;

public class DepartmentDaoImpl2 extends AbstractDao<Department>{

	@Override
	public Department getItem(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int getItemNo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void insertSetGet(PreparedStatement pstmt, Department item) throws SQLException {
		pstmt.setInt(1, item.getDeptNo());
		pstmt.setString(2, item.getDeptName());
		pstmt.setInt(3, item.getFloor());
	}

	
	
	
	
}
