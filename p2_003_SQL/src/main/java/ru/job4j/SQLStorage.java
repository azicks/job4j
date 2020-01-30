package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class SQLStorage {
    private static final Logger log = LogManager.getLogger(SQLStorage.class.getName());

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/db1";
        String user = "postgres";
        String pass = "iddqd";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
            PreparedStatement st = conn.prepareStatement("SELECT * FROM item");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }

    }
}
