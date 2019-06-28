package gradle_jdbc_study;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import gradle_jdbc_study.jdbc.ConnectionProvider;

public class DBConnectionTest {
	static final Logger log = LogManager.getLogger();
	
	@Test
	public void testConnection() {
		try {
			Connection con=ConnectionProvider.getConnection();
			log.trace("con= "+con);
			Assert.assertNotNull(con);
		} catch (SQLException e) {
			System.out.println("데이떠붸이스 연겨르 정보르ㄹ 화긴하삼");
		}
	}

}
