package autoservice.dataBase.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private final HikariDataSource dataSource;
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/autoserviceDB";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "root";
    private static final int MAX_POOL_SIZE = 5;

    private DatabaseConnection() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(JDBC_URL);
        config.setUsername(DB_USERNAME);
        config.setPassword(DB_PASSWORD);
        config.setMaximumPoolSize(MAX_POOL_SIZE);

        this.dataSource = new HikariDataSource(config);
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}

