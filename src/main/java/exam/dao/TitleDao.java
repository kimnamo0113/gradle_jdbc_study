package exam.dao;

import java.sql.SQLException;
import java.util.List;

import exam.dto.Employee;
import exam.dto.Title;

public interface TitleDao {
	List<Title> selectTitleByAll() throws SQLException;
	Title selectTitleByNo(Title title) throws SQLException;
	int insertTitle(Title title) throws SQLException ;
	int deleteTitle(Title title) throws SQLException;
	int updateTitle(Title title) throws SQLException;
}
