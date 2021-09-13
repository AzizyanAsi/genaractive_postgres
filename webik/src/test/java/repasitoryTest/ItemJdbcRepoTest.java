package repasitoryTest;

import com.example.webik.database.DatabaseConnection;
import com.example.webik.models.Configuration;
import com.example.webik.models.Item;
import com.example.webik.models.Stock;
import com.example.webik.repository.ItemJdbcRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ItemJdbcRepoTest {
    public class ItemJdbcRepositoryTest {

        @BeforeAll
        public static void initDatabase() throws SQLException, ClassNotFoundException {
            Connection databaseConnection = TestDatabaseConnection.initializeConnection();
            Statement statement = databaseConnection.createStatement();
            statement.executeUpdate("CREATE TABLE item (" +
                    "id SERIAL PRIMARY KEY," +
                    "name  VARCHAR," +
                    "base_price  INTEGER" +
                    ")");
            databaseConnection.close();
        }

        @Test
        public void testConnection() {
            assertDoesNotThrow(TestDatabaseConnection::initializeConnection);
        }

        @Test
        @DisplayName("Insert")
        public void insert() throws SQLException, ClassNotFoundException {
            ItemJdbcRepo itemJdbcRepository = new ItemJdbcRepo();
            itemJdbcRepository.createItem(new Stock("1","testI","img",200, Configuration.Resolution.HD));
            Item item = itemJdbcRepository.getOneById("1");
            assertNotNull(item);
            assertEquals(1, item.getId());
            itemJdbcRepository.createItem(new Stock("2","testI1","img",300, Configuration.Resolution.HD));
            Item item1 = itemJdbcRepository.getOneById("2");
            assertNotNull(item1);
            assertEquals(2, item1.getId());
        }
        @Test
        @DisplayName("Delete")
        public void delete() throws SQLException, ClassNotFoundException {
            ItemJdbcRepo itemJdbcRepository = new ItemJdbcRepo();
            itemJdbcRepository.deleteItem("1");
            Item item = itemJdbcRepository.getOneById("1");
            assertEquals(null, item.getId());

        }
        @Test
        @DisplayName("Update")
        public void update() throws SQLException, ClassNotFoundException {
            ItemJdbcRepo itemJdbcRepository = new ItemJdbcRepo();
            itemJdbcRepository.updateItem("2");
            Item item = itemJdbcRepository.getOneById("2");
            assertNotNull(item);
            assertNotEquals("row2", item.getName());

        }
        @Test
        @DisplayName("Search")
        public void search() throws SQLException, ClassNotFoundException {
            ItemJdbcRepo itemJdbcRepository = new ItemJdbcRepo();
            List<Item> item = itemJdbcRepository.findItems("row3");
            assertNotNull(item);

        }
    }
}
