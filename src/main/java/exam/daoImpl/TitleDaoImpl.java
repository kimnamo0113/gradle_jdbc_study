package exam.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exam.dao.TitleDao;
import exam.dto.Department;
import exam.dto.Employee;
import exam.dto.Title;
import gradle_jdbc_study.jdbc.ConnectionProvider;

public class TitleDaoImpl implements TitleDao{
	static final Logger log = LogManager.getLogger();
	@Override
	public List<Title> selectTitleByAll() throws SQLException {
		String sql = "SELECT * FROM Title";
		List<Title> lists = null;
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			log.trace(pstmt);
			if (rs.next()) {
				lists = new ArrayList<Title>();
				do {
					lists.add(getTitle(rs));
				}while(rs.next());
			}
		}
		return lists;
	}
	private Title getTitle(ResultSet rs) throws SQLException {
		return new Title(rs.getInt("tno"), 
				            rs.getString("tname")); 
				        
	}
	
	
	@Override
	public Title selectTitleByNo(Title title) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertTitle(Title title) throws SQLException {
		String sql="INSERT INTO title(tno, tname) VALUES(?, ?)";
		int res = -1;

		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, title.getTno());
			pstmt.setString(2, title.getTname());
			log.trace(pstmt);
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int deleteTitle(Title title) throws SQLException {
		String sql="DELETE FROM title WHERE tno=?";
		int res = -1;

		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, title.getTno());
			log.trace(pstmt);
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int updateTitle(Title title) throws SQLException {
		String sql = "UPDATE title SET tname=? WHERE tno=?";
		int res = -1;

		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, title.getTname());
			pstmt.setInt(2, title.getTno());
			log.trace(pstmt);
			res = pstmt.executeUpdate();
		}
		return res;
	}
	
}
