package at.tests;

import at.study.redmine.db.connection.DatabaseConnection;
import at.study.redmine.db.connection.PostgresConnection;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class DatabaseConnectionTests {

    @Test
    public void executeSimpleQueryTest(){
        DatabaseConnection databaseConnection = new PostgresConnection();
        String query = "SELECT * FROM users WHERE id = ? OR id = ?";
        List<Map<String,Object>> result = databaseConnection.executeQuery(query, 26475, 26474);
    }
}
