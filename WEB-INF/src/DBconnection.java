import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {

    private static String url = "jdbc:postgresql://localhost:5432/complaints";
    private static String username = "postgres";
    private static String password = "0025";

    public static Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, username, password);
    }

}