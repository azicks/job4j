package ru.job4j.tracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TrackerSQL implements ITracker, AutoCloseable {
    private static final Logger LOG = LogManager.getLogger(TrackerSQL.class.getName());
    private Connection connection;

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            String dbName = config.getProperty("dbname");
            boolean newDB = false;
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password"));
            if (!this.isDBExists(dbName)) {
                this.createDB(dbName);
                newDB = true;
            }
            try {
                this.connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
                throw new IllegalStateException(e);
            }
            String url = String.format("%s%s", config.getProperty("url"), dbName);
            this.connection = DriverManager.getConnection(
                    url,
                    config.getProperty("username"),
                    config.getProperty("password"));
            LOG.info(String.format("Connected to DB %s", dbName));
            if (newDB) {
                this.createTable();
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    @Override
    public void close() throws Exception {
        String dbName = this.connection.getCatalog();
        this.connection.close();
        LOG.info(String.format("Disconnected from db %s", dbName));
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement pst = connection.prepareStatement(
                "insert into items (name, description) values (?, ?) returning id")) {
            pst.setString(1, item.getName());
            pst.setString(2, item.getDesc());
            ResultSet insertedItem = pst.executeQuery();
            insertedItem.next();
            int itemId = insertedItem.getInt(1);
            item.setId(Integer.toString(itemId));
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean result = false;
        try (PreparedStatement pst = this.connection.prepareStatement(
                "UPDATE items SET name = ?, description = ? WHERE id = ?")) {
            pst.setString(1, item.getName());
            pst.setString(2, item.getDesc());
            pst.setInt(3, Integer.valueOf(id));
            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                result = true;
                item.setId(id);
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 0) {
                LOG.info(e.getMessage());
            } else {
                LOG.error(e.getMessage(), e);
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        try (PreparedStatement pst = this.connection.prepareStatement("DELETE FROM items WHERE id = ?")) {
            pst.setInt(1, Integer.valueOf(id));
            int affectedRows = pst.executeUpdate();
            result = affectedRows > 0;
        } catch (SQLException e) {
            if (e.getErrorCode() == 0) {
                LOG.info(e.getMessage());
            } else {
                LOG.error(e.getMessage(), e);
            }
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (Statement st = this.connection.createStatement()) {
            ResultSet items = st.executeQuery("SELECT * FROM items");
            while (items.next()) {
                Item item = new Item(
                        items.getString("id"),
                        items.getString("name"),
                        items.getString("description"));
                result.add(item);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement pst = this.connection.prepareStatement("SELECT * FROM items WHERE name = ?")) {
            pst.setString(1, key);
            ResultSet items = pst.executeQuery();
            while (items.next()) {
                Item item = new Item(
                        items.getString("id"),
                        items.getString("name"),
                        items.getString("description"));
                result.add(item);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Item findById(String id) {
        Item item = null;
        try (PreparedStatement pst = this.connection.prepareStatement("SELECT * FROM items WHERE id = ?")) {
            pst.setInt(1, Integer.valueOf(id));
            ResultSet items = pst.executeQuery();
            if (items.next()) {
                item = new Item(
                        items.getString("id"),
                        items.getString("name"),
                        items.getString("description"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return item;
    }

    /**
     * Checks if Database 'dbName' is exists
     *
     * @param dbName
     * @return if exists
     * @throws SQLException
     */
    private boolean isDBExists(String dbName) throws SQLException {
        boolean result = false;
        if (this.connection != null) {
            try (PreparedStatement pst = this.connection.prepareStatement(
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
    private void createDB(String dbName) throws SQLException {
        if (this.connection != null) {
            LOG.warn(String.format("DB %s not exists, creating new", dbName));
            try (Statement st = this.connection.createStatement()) {
                st.executeUpdate(String.format("CREATE DATABASE %s", dbName));
            }
        }
    }

    /**
     * Creates table 'items' (id, name, description)
     *
     * @throws SQLException
     */
    private void createTable() throws SQLException {
        if (this.connection != null) {
            try (Statement st = this.connection.createStatement()) {
                st.executeUpdate(
                        "CREATE TABLE items (id serial primary key, name varchar(100), description varchar(1000))");
            }
        }
    }
}
