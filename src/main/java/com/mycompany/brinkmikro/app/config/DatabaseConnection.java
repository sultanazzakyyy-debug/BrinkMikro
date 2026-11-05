package com.mycompany.brinkmikro.app.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class: DatabaseConnection
 * Murni Koneksi ke Database 'brinkmikro' (Tanpa Menambah/Membuat Tabel Baru).
 */
public class DatabaseConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/brinkmikro?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.err.println("Driver JDBC MySQL tidak ditemukan: " + ex.getMessage());
            }
        }
    }

    /**
     * Membuka koneksi murni ke database MySQL Laragon 'brinkmikro'.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
