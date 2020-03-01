package ru.job4j.tracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class TrackerConnection {
    private static final Logger LOG = LogManager.getLogger(TrackerSQL.class.getName());
    private static final String PROPERTIES_FILE = "app.properties";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String URL = "url";
    private static final String DB = "dbname";
    private static final String DRIVER_CLASS_NAME = "driver-class-name";

    static Connection getRollbackConnection() {
        Connection result = null;
        try {
            result = ConnectionRollback.create(getConnection());
        } catch (SQLException e) {
            LOG.error(e);
        }
        return result;
    }

    static Connection getConnection() {
        final Connection result;
            try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
                final Properties config = new Properties();
                config.load(in);
                final Connection conn = createConnection(config, "");
                final String dbName = config.getProperty(DB);
                Class.forName(config.getProperty(DRIVER_CLASS_NAME));
                if (!isDBExists(conn, dbName)) {
                    createDB(conn, dbName);
                }
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                    throw new IllegalStateException(e);
                }
                result = createConnection(config, dbName);
                LOG.info(String.format("Connected to DB %s", dbName));
            } catch (Exception e) {
                LOG.error(e.getMessage());
                throw new IllegalStateException(e);
            }
            return result;
        }

    private static Connection createConnection(Properties config, String dbname) throws SQLException {
        String url = String.format("%s%s", config.getProperty(URL), dbname);
        return DriverManager.getConnection(
                url,
                config.getProperty(USERNAME),
                config.getProperty(PASSWORD));
    }

    /**
     * Checks if Database 'dbName' is exists
     *
     * @param dbName
     * @return if exists
     * @throws SQLException
     */
    private static boolean isDBExists(Connection connection, String dbName) throws SQLException {
        boolean result = false;
        if (connection != null) {
            try (PreparedStatement pst = connection.prepareStatement(
                    "SELECT datname FROM pg_catalog.pg_database WHERE lower(datname) = lower(?);")) {
                pst.setString(1, dbName);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * Creates database 'dbName'
     *
     * @param dbName
     * @throws SQLException
     */
    private static void createDB(Connection connection, String dbName) throws SQLException {
        if (connection != null) {
            LOG.warn(String.format("DB %s not exists, creating new", dbName));
            try (Statement st = connection.createStatement()) {
                st.executeUpdate(String.format("CREATE DATABASE %s", dbName));
            }
        }
    }
}
