package app.constants;

public class DbConfigProperties {
    public static final String DB_NAME = "e_store_db";
    public static final String DB_PORT = "3306";
    public static final String DB_HOST = "localhost";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "12345";
    public static final String DB_URL = String.format("jdbc:mysql://%s:%s/%s",DB_HOST, DB_PORT, DB_NAME);
}
