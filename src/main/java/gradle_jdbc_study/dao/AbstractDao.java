package gradle_jdbc_study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gradle_jdbc_study.dto.Department;
import gradle_jdbc_study.dto.Employee;
import gradle_jdbc_study.jdbc.ConnectionProvider;

public abstract class AbstractDao<T>{
	static final Logger log = LogManager.getLogger();
	
	public List<T> selectItemByAll(String sql) throws SQLException{
		List<T> lists = new ArrayList<T>();
				
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			log.trace(pstmt);

			while(rs.next()) {
				lists.add(getItem(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return lists;
	};
	
	public abstract T getItem(ResultSet rs) throws SQLException;
	
	
	public T selectItemByNo(T item) throws SQLException {
		String sql="";
		if(item instanceof Department)
			 sql= "select deptno, deptname, floor from department";
		else
			sql="SELECT empno, empname, title, manager, salary, dno, pic FROM employee where empno = ?";
		
		T selDept = null;
		
		try (Connection conn =ConnectionProvider.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			
			pstmt.setInt(1, getItemNo());
			log.trace(pstmt);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					selDept = getItem(rs);
				}
			}
		} 		
		return selDept;
	}

	protected abstract int getItemNo();
	
	public int insertItem(T item) throws SQLException {
		String sql="";
		if(item instanceof Department)
			 sql= "insert into department(deptno, deptname, floor) values(?, ?, ?)";
		else
			sql="insert into employee values(?, ?, ?, ?, ?, ?, ?)";
		int res = -1;
		
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
//			pstmt.setInt(1, item.getDeptNo());
//			pstmt.setString(2, item.getDeptName());
//			pstmt.setInt(3, item.getFloor());
			insertSetGet(pstmt,item);
			log.trace(pstmt);
			res = pstmt.executeUpdate();
		}
		return res;
	}

	protected abstract void insertSetGet(PreparedStatement pstmt,T item) throws SQLException;
	
	
}
