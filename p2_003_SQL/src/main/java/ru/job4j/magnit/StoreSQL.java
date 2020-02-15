package ru.job4j.magnit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreSQL implements AutoCloseable {
    private final Config config;
    private Connection connection;

    public StoreSQL(Config config) {
        this.config = config;
    }

    public void init() {
        try {
            this.connection = DriverManager.getConnection(config.url());
            Statement st = this.connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='accounts';");
            if (!rs.next()) {
                st.executeUpdate(
                        "CREATE TABLE accounts (name varchar(100), description varchar(100))"
                );
            }
            st.close();
            this.connection.setAutoCommit(false);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public void generate(int size) {
        if (size > 0) {
            try {
                Statement st = this.connection.createStatement();
                st.executeUpdate("DELETE FROM accounts;");
                st.close();
                PreparedStatement pst = this.connection.prepareStatement(
                        "INSERT INTO accounts (name, description) VALUES ('name' || ?, 'description' || ?);");
                for (int i = 1; i <= size; i++) {
                    pst.setInt(1, i);
                    pst.setInt(2, i);
                    pst.executeUpdate();
                }
                this.connection.commit();
                pst.close();
            } catch (SQLException e) {
                try {
                    this.connection.rollback();
                } catch (SQLException r) {
                    throw new IllegalStateException(r);
                }
                throw new IllegalStateException(e);
            }
        }
    }

    public List<Entry> load() {
        ArrayList result = new ArrayList();
        try {
            Statement st = this.connection.createStatement();
            ResultSet accounts = st.executeQuery("SELECT rowid, name, description FROM accounts");
            while (accounts.next()) {
                result.add(new Entry(
                        accounts.getLong("rowid"),
                        accounts.getString("name"),
                        accounts.getString("description")
                ));
            }
            st.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

}