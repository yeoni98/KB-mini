package config;

public interface ServerInfo {
	//필드가 없고 무조건 상수로~~~ public static final
	    String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	    String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	    String USER = "kb_db";
	    String PASSWORD = "1234";
}