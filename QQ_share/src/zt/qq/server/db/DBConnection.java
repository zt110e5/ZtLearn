package zt.qq.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {
	private Connection conn;
	private PreparedStatement pst ;
	private ResultSet rs;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	private static final String PASSWORD = "tiger";
	private static final String USER = "scott";
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public PreparedStatement getPst() {
		return pst;
	}
	public void setPst(PreparedStatement pst) {
		this.pst = pst;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public static String getUrl() {
		return URL;
	}
	public static String getPassword() {
		return PASSWORD;
	}
	public static String getUser() {
		return USER;
	}
	
	public DBConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public ResultSet queryExecute(String sql,String[] params) throws Exception{
		pst =conn.prepareStatement(sql);
		for(int i = 0;i<params.length;i++){
			pst.setString(i+1, params[i]);;
			
		}
		rs = pst.executeQuery();
		return rs;
	}
	// �ر����ݿ���Դ
			public void close() {
				// �ر���Դ
				try {
					if (rs != null) {
						rs.close();
					}
					if (pst != null) {
						pst.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
}
