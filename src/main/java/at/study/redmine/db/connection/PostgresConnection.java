package at.study.redmine.db.connection;

import at.study.redmine.property.Property;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class PostgresConnection implements DatabaseConnection {
    public final static DatabaseConnection INSTANCE = new PostgresConnection();

    private String host = Property.getStringProperty("db.host");
    private Integer port = Property.getIntegerProperty("db.port");
    private String database = Property.getStringProperty("db.database");
    private String user = Property.getStringProperty("db.user");
    private String password = Property.getStringProperty("db.password");
    private Connection connection;

    public PostgresConnection() {
        connect();
    }

    @SneakyThrows
    private void connect() {
        Class.forName("org.postgresql.Driver");

        String url = String.format("jdbc:postgresql://%s:%d/%s", host, port, database);
        Properties connectionProperties = new Properties();
        connectionProperties.setProperty("user", user);
        connectionProperties.setProperty("password", password);
        connection = DriverManager.getConnection(url, connectionProperties);
    }

    @Override
    @SneakyThrows
    public List<Map<String, Object>> executeQuery(String query, Object... parameters) {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 0; i < parameters.length; i++) {
            statement.setObject(i + 1, parameters[i]);
        }
        ResultSet resultSet = statement.executeQuery();

        List<Map<String, Object>> result = new ArrayList<>();
        while (resultSet.next()) {
            int columnCount = resultSet.getMetaData().getColumnCount();
            Map<String, Object> resultRow = new HashMap<>();
            for (int i = 1; i < columnCount; i++) {
                String key = resultSet.getMetaData().getColumnName(i);
                resultRow.put(key, resultSet.getObject(key));
            }
            result.add(resultRow);
        }
        return result;
    }
}
