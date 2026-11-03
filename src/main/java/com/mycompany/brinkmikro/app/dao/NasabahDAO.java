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
import com.mycompany.brinkmikro.app.model.Nasabah;
import com.mycompany.brinkmikro.app.model.Petani;
import com.mycompany.brinkmikro.app.model.PedagangUmkm;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NasabahDAO {

    public List<Nasabah> getAllNasabah() throws SQLException {
        List<Nasabah> list = new ArrayList<>();
        String sql = "SELECT n.*, p.luas_lahan, p.komoditas_utama, p.pendapatan_tahunan, " +
                     "u.jenis_usaha, u.lokasi_usaha, u.omzet_bulanan " +
                     "FROM nasabah n " +
                     "LEFT JOIN petani p ON n.nik = p.nik " +
                     "LEFT JOIN pedagang_umkm u ON n.nik = u.nik " +
                     "ORDER BY n.tanggal_daftar DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String jenis = rs.getString("jenis_nasabah");
                if ("Petani".equalsIgnoreCase(jenis)) {
                    Petani pt = new Petani(
                        rs.getString("nik"),
                        rs.getString("nama_nasabah"),
                        rs.getString("alamat"),
                        rs.getString("no_telepon"),
                        rs.getDate("tanggal_daftar"),
                        rs.getBigDecimal("luas_lahan"),
                        rs.getString("komoditas_utama"),
                        rs.getBigDecimal("pendapatan_tahunan")
                    );
                    list.add(pt);
                } else {
                    PedagangUmkm um = new PedagangUmkm(
                        rs.getString("nik"),
                        rs.getString("nama_nasabah"),
                        rs.getString("alamat"),
                        rs.getString("no_telepon"),
                        rs.getDate("tanggal_daftar"),
                        rs.getString("jenis_usaha"),
                        rs.getString("lokasi_usaha"),
                        rs.getBigDecimal("omzet_bulanan")
                    );
                    list.add(um);
                }
            }
        }
        return list;
    }

    public boolean tambahNasabahPetani(String nik, String nama, String alamat, String noTelp,
                                       BigDecimal luasLahan, String komoditas, BigDecimal pendapatanTahunan) throws SQLException {
        if (nik == null || nik.trim().length() != 16) {
            throw new IllegalArgumentException("NIK harus terdiri dari 16 digit!");
        }

        String sqlNasabah = "INSERT INTO nasabah (nik, nama_nasabah, alamat, no_telepon, jenis_nasabah, tanggal_daftar) " +
                            "VALUES (?, ?, ?, ?, 'Petani', CURRENT_DATE)";
        String sqlPetani = "INSERT INTO petani (nik, luas_lahan, komoditas_utama, pendapatan_tahunan) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps1 = conn.prepareStatement(sqlNasabah);
                 PreparedStatement ps2 = conn.prepareStatement(sqlPetani)) {

                ps1.setString(1, nik.trim());
                ps1.setString(2, nama);
                ps1.setString(3, alamat);
                ps1.setString(4, noTelp);
                ps1.executeUpdate();

                ps2.setString(1, nik.trim());
                ps2.setBigDecimal(2, luasLahan);
                ps2.setString(3, komoditas);
                ps2.setBigDecimal(4, pendapatanTahunan);
                ps2.executeUpdate();

                conn.commit();
                return true;
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        }
    }

    public boolean tambahNasabahUMKM(String nik, String nama, String alamat, String noTelp,
                                     String jenisUsaha, String lokasiUsaha, BigDecimal omzetBulanan) throws SQLException {
        if (nik == null || nik.trim().length() != 16) {
            throw new IllegalArgumentException("NIK harus terdiri dari 16 digit!");
        }

        String sqlNasabah = "INSERT INTO nasabah (nik, nama_nasabah, alamat, no_telepon, jenis_nasabah, tanggal_daftar) " +
                            "VALUES (?, ?, ?, ?, 'UMKM', CURRENT_DATE)";
        String sqlUmkm = "INSERT INTO pedagang_umkm (nik, jenis_usaha, lokasi_usaha, omzet_bulanan) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps1 = conn.prepareStatement(sqlNasabah);
                 PreparedStatement ps2 = conn.prepareStatement(sqlUmkm)) {

                ps1.setString(1, nik.trim());
                ps1.setString(2, nama);
                ps1.setString(3, alamat);
                ps1.setString(4, noTelp);
                ps1.executeUpdate();

                ps2.setString(1, nik.trim());
                ps2.setString(2, jenisUsaha);
                ps2.setString(3, lokasiUsaha);
                ps2.setBigDecimal(4, omzetBulanan);
                ps2.executeUpdate();

                conn.commit();
                return true;
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        }
    }

    public boolean updateNasabah(String nik, String nama, String alamat, String noTelp) throws SQLException {
        String sql = "UPDATE nasabah SET nama_nasabah = ?, alamat = ?, no_telepon = ? WHERE nik = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nama);
            ps.setString(2, alamat);
            ps.setString(3, noTelp);
            ps.setString(4, nik);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean hapusNasabah(String nik) throws SQLException {
        String sql = "DELETE FROM nasabah WHERE nik = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nik);
            return ps.executeUpdate() > 0;
        }
    }
}