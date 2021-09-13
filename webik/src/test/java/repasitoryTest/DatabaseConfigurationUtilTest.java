package repasitoryTest;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class DatabaseConfigurationUtilTest {
    public static final String DATABASE_PROPERTIES_FILE = "database.properties";

    public static Properties getTestConnectionProperties() {
        Properties props = new Properties();
        try {
            props.load(Objects.requireNonNull(DatabaseConfigurationUtilTest.class
                    .getClassLoader()
                    .getResource(DATABASE_PROPERTIES_FILE)).openStream());

            return props;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to load db properties from: "
                    + DATABASE_PROPERTIES_FILE);
        }
    }
}
