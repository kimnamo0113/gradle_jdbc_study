package gradle_jdbc_study.dao;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceDao<T> {
	List<T> selectItemByAll(T item) throws SQLException;
	int insertItem(T item) throws SQLException;
	int deleteItem(T item) throws SQLException;
	int updateItem(T item) throws SQLException;
	
}
