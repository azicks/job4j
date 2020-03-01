package ru.job4j.tracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrackerSQL implements ITracker, AutoCloseable {
    private static final Logger LOG = LogManager.getLogger(TrackerSQL.class.getName());
    private final Connection connection;

    public TrackerSQL(final Connection conn) {
        this.connection = conn;
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
                "insert into items (name, description) values (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, item.getName());
            pst.setString(2, item.getDesc());
            pst.executeUpdate();
            ResultSet insertedItem = pst.getGeneratedKeys();
            insertedItem.next();
            int itemId = insertedItem.getInt("id");
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
}
