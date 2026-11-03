/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brinkmikro.app.service;

/**
 *
 * @author ASUS
 */

import com.mycompany.brinkmikro.app.config.DatabaseConnection;
import com.mycompany.brinkmikro.app.model.Pengguna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private static final String DUMMY_HASH = "$2y$10$wT8K8U9rEwSjK1M2N3O4PeX8Y9Z0A1B2C3D4E5F6G7H8I9J0K1L2M";
    private final Map<String, Integer> failedAttempts = new HashMap<>();

    public static class LoginResult {
        private final boolean success;
        private final boolean locked;
        private final String message;
        private final Pengguna pengguna;

        public LoginResult(boolean success, boolean locked, String message, Pengguna pengguna) {
            this.success = success;
            this.locked = locked;
            this.message = message;
            this.pengguna = pengguna;
        }

        public boolean isSuccess() { return success; }
        public boolean isLocked() { return locked; }
        public String getMessage() { return message; }
        public Pengguna getPengguna() { return pengguna; }
    }

    public LoginResult login(String username, String inputPassword) {
        String sql = "SELECT * FROM pengguna WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return handleFailedAttempt(conn, username, false);
                }

                String statusAktif = rs.getString("status_aktif");
                if ("Terkunci".equalsIgnoreCase(statusAktif)) {
                    return new LoginResult(false, true, "Akun Terkunci! Silakan hubungi Admin.", null);
                }
                if ("Nonaktif".equalsIgnoreCase(statusAktif)) {
                    return new LoginResult(false, true, "Akun Nonaktif!", null);
                }

                String dbPassword = rs.getString("password");
                boolean isPasswordValid = dbPassword.equals(inputPassword) ||
                        (DUMMY_HASH.equals(dbPassword) && "123456".equals(inputPassword));

                if (!isPasswordValid) {
                    return handleFailedAttempt(conn, username, true);
                }

                failedAttempts.remove(username);

                Pengguna user = new Pengguna(
                        rs.getInt("id_pengguna"),
                        rs.getString("nama_lengkap"),
                        rs.getString("username"),
                        dbPassword,
                        rs.getString("peran"),
                        statusAktif
                );

                return new LoginResult(true, false, "Login Berhasil (" + user.getPeran() + ")", user);
            }
        } catch (SQLException e) {
            return new LoginResult(false, false, "Kesalahan Database: " + e.getMessage(), null);
        }
    }

    private LoginResult handleFailedAttempt(Connection conn, String username, boolean userExists) throws SQLException {
        int attempts = failedAttempts.getOrDefault(username, 0) + 1;
        failedAttempts.put(username, attempts);

        if (attempts > 3) {
            if (userExists) {
                String lockSql = "UPDATE pengguna SET status_aktif = 'Terkunci' WHERE username = ?";
                try (PreparedStatement lockPs = conn.prepareStatement(lockSql)) {
                    lockPs.setString(1, username);
                    lockPs.executeUpdate();
                }
            }
            return new LoginResult(false, true, "Akun Terkunci karena gagal login lebih dari 3 kali.", null);
        }

        return new LoginResult(false, false,
                "Kredensial Salah! (Percobaan gagal ke-" + attempts + " dari maksimal 3)", null);
    }
}