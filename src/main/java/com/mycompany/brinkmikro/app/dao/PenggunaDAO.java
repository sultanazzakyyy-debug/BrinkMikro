/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brinkmikro.app.dao;

/**
 *
 * @author ASUS
 */

import com.mycompany.brinkmikro.app.config.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PenggunaDAO {

    public static class PenggunaRecord {
        public int idPengguna;
        public String namaLengkap, username, peran, statusAktif, cakupanTugas, keteranganDetail, loket, shiftTeller, wilayahKerja;
        public BigDecimal batasPersetujuan;
    }

    public List<PenggunaRecord> getAllPengguna() throws SQLException {
        List<PenggunaRecord> list = new ArrayList<>();
        String sql = "SELECT p.*, a.cakupan_tugas, a.keterangan_detail, " +
                     "t.loket, t.shift_teller, m.batas_persetujuan, m.wilayah_kerja " +
                     "FROM pengguna p " +
                     "LEFT JOIN admin a ON p.id_pengguna = a.id_pengguna " +
                     "LEFT JOIN teller t ON p.id_pengguna = t.id_pengguna " +
                     "LEFT JOIN manajer m ON p.id_pengguna = m.id_pengguna " +
                     "ORDER BY p.id_pengguna ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PenggunaRecord r = new PenggunaRecord();
                r.idPengguna = rs.getInt("id_pengguna");
                r.namaLengkap = rs.getString("nama_lengkap");
                r.username = rs.getString("username");
                r.peran = rs.getString("peran");
                r.statusAktif = rs.getString("status_aktif");
                r.cakupanTugas = rs.getString("cakupan_tugas");
                r.keteranganDetail = rs.getString("keterangan_detail");
                r.loket = rs.getString("loket");
                r.shiftTeller = rs.getString("shift_teller");
                r.batasPersetujuan = rs.getBigDecimal("batas_persetujuan");
                r.wilayahKerja = rs.getString("wilayah_kerja");
                list.add(r);
            }
        }
        return list;
    }

    public boolean tambahPengguna(String namaLengkap, String username, String password, String peran,
                                  String param1, String param2, BigDecimal batasPersetujuan) throws SQLException {
        String sqlInduk = "INSERT INTO pengguna (nama_lengkap, username, password, peran, status_aktif) VALUES (?, ?, ?, ?, 'Aktif')";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(sqlInduk, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, namaLengkap);
                ps.setString(2, username);
                ps.setString(3, password);
                ps.setString(4, peran);
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idBaru = rs.getInt(1);
                        if ("Admin".equalsIgnoreCase(peran)) {
                            try (PreparedStatement psSub = conn.prepareStatement(
                                    "INSERT INTO admin (id_pengguna, cakupan_tugas, keterangan_detail) VALUES (?, ?, ?)")) {
                                psSub.setInt(1, idBaru);
                                psSub.setString(2, param1 != null ? param1 : "Administrasi Sistem");
                                psSub.setString(3, param2 != null ? param2 : "-");
                                psSub.executeUpdate();
                            }
                        } else if ("Teller".equalsIgnoreCase(peran)) {
                            try (PreparedStatement psSub = conn.prepareStatement(
                                    "INSERT INTO teller (id_pengguna, loket, shift_teller) VALUES (?, ?, ?)")) {
                                psSub.setInt(1, idBaru);
                                psSub.setString(2, param1 != null ? param1 : "Loket 01");
                                psSub.setString(3, param2 != null ? param2 : "Pagi");
                                psSub.executeUpdate();
                            }
                        } else if ("Manajer".equalsIgnoreCase(peran)) {
                            try (PreparedStatement psSub = conn.prepareStatement(
                                    "INSERT INTO manajer (id_pengguna, batas_persetujuan, wilayah_kerja) VALUES (?, ?, ?)")) {
                                psSub.setInt(1, idBaru);
                                psSub.setBigDecimal(2, batasPersetujuan != null ? batasPersetujuan : new BigDecimal("50000000.00"));
                                psSub.setString(3, param1 != null ? param1 : "Wilayah Samarinda Kota");
                                psSub.executeUpdate();
                            }
                        }
                    }
                }
                conn.commit();
                return true;
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        }
    }

    public boolean updatePengguna(int idPengguna, String namaLengkap, String username, String statusAktif) throws SQLException {
        String sql = "UPDATE pengguna SET nama_lengkap = ?, username = ?, status_aktif = ? WHERE id_pengguna = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, namaLengkap);
            ps.setString(2, username);
            ps.setString(3, statusAktif);
            ps.setInt(4, idPengguna);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean hapusPengguna(int idPengguna) throws SQLException {
        String sql = "DELETE FROM pengguna WHERE id_pengguna = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPengguna);
            return ps.executeUpdate() > 0;
        }
    }
}