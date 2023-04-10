package config;

public interface DBConfig {

		//강사컴 DB 주소 : jdbc:oracle:thin:@//192.168.5.101:1521/xe
	public String DB_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
	public String DB_DRIVER = "oracle.jdbc.OracleDriver";
	public String DB_USER = "romi";
	public String DB_PASSWD = "123456";

}
