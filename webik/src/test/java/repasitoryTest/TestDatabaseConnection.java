package repasitoryTest;

import com.example.webik.util.DatabaseConfigurationUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class TestDatabaseConnection {
    public static Connection initializeConnection() throws SQLException {

        Properties props = DatabaseConfigurationUtilTest.getTestConnectionProperties();
        String dbDriverClass = props.getProperty("db.driver.class");
        String dbConnUrl = props.getProperty("db.url");
        try {
            Class.forName(dbDriverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return DriverManager.getConnection(dbConnUrl);
    }
}
