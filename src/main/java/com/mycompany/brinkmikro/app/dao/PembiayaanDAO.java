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
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class PembiayaanDAO {

    public static class PembiayaanRecord {
        public int idPembiayaan, jangkaWaktuBulan, skorKelayakan;
        public BigDecimal jumlahPengajuan, sukuBungaPersen, totalKewajiban;
        public String skemaPembiayaan, statusPembiayaan, nasabahNik, namaNasabah, jenisNasabah, namaManajer;
        public Date tanggalPengajuan;
        public Integer manajerIdPengguna;
    }

    public List<PembiayaanRecord> getAllPembiayaan() throws SQLException {
        List<PembiayaanRecord> list = new ArrayList<>();
        String sql = "SELECT p.*, n.nama_nasabah, n.jenis_nasabah, u.nama_lengkap AS nama_manajer " +
                     "FROM pembiayaan p " +
                     "JOIN nasabah n ON p.nasabah_nik = n.nik " +
                     "LEFT JOIN pengguna u ON p.manajer_id_pengguna = u.id_pengguna " +
                     "ORDER BY p.id_pembiayaan DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PembiayaanRecord r = new PembiayaanRecord();
                r.idPembiayaan = rs.getInt("id_pembiayaan");
                r.jumlahPengajuan = rs.getBigDecimal("jumlah_pengajuan");
                r.jangkaWaktuBulan = rs.getInt("jangka_waktu_bulan");
                r.sukuBungaPersen = rs.getBigDecimal("suku_bunga_persen");
                r.totalKewajiban = rs.getBigDecimal("total_kewajiban");
                r.skemaPembiayaan = rs.getString("skema_pembiayaan");
                r.skorKelayakan = rs.getInt("skor_kelayakan");
                r.statusPembiayaan = rs.getString("status_pembiayaan");
                r.tanggalPengajuan = rs.getDate("tanggal_pengajuan");
                r.nasabahNik = rs.getString("nasabah_nik");
                r.namaNasabah = rs.getString("nama_nasabah");
                r.jenisNasabah = rs.getString("jenis_nasabah");
                int manajerId = rs.getInt("manajer_id_pengguna");
                r.manajerIdPengguna = rs.wasNull() ? null : manajerId;
                r.namaManajer = rs.getString("nama_manajer");
                list.add(r);
            }
        }
        return list;
    }

    public BigDecimal hitungTotalKewajiban(BigDecimal jumlahPengajuan, int jangkaWaktuBulan, BigDecimal sukuBungaPersen) {
        BigDecimal bungaProporsional = jumlahPengajuan
                .multiply(sukuBungaPersen.divide(new BigDecimal("100"), 6, RoundingMode.HALF_UP))
                .multiply(new BigDecimal(jangkaWaktuBulan))
                .divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP);
        return jumlahPengajuan.add(bungaProporsional).setScale(2, RoundingMode.HALF_UP);
    }

    public boolean tambahPengajuan(BigDecimal jumlahPengajuan, int jangkaWaktuBulan, BigDecimal sukuBungaPersen,
                                   String skemaPembiayaan, Integer skorKelayakan, String nikNasabah) throws SQLException {
        if (jumlahPengajuan == null || jumlahPengajuan.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Jumlah pengajuan harus lebih dari Rp 0!");
        }
        if (sukuBungaPersen == null) {
            sukuBungaPersen = new BigDecimal("6.00");
        }

        BigDecimal totalKewajiban = hitungTotalKewajiban(jumlahPengajuan, jangkaWaktuBulan, sukuBungaPersen);

        String sql = "INSERT INTO pembiayaan (jumlah_pengajuan, jangka_waktu_bulan, suku_bunga_persen, " +
                     "total_kewajiban, skema_pembiayaan, skor_kelayakan, status_pembiayaan, tanggal_pengajuan, nasabah_nik) " +
                     "VALUES (?, ?, ?, ?, ?, ?, 'Diajukan', CURRENT_DATE, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, jumlahPengajuan);
            ps.setInt(2, jangkaWaktuBulan);
            ps.setBigDecimal(3, sukuBungaPersen);
            ps.setBigDecimal(4, totalKewajiban);
            ps.setString(5, skemaPembiayaan);
            if (skorKelayakan != null) {
                ps.setInt(6, skorKelayakan);
            } else {
                ps.setNull(6, Types.INTEGER);
            }
            ps.setString(7, nikNasabah);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean updatePembiayaan(int idPembiayaan, BigDecimal jumlahPengajuan, int jangkaWaktuBulan,
                                    BigDecimal sukuBungaPersen, String skemaPembiayaan, Integer skorKelayakan) throws SQLException {
        BigDecimal totalKewajiban = hitungTotalKewajiban(jumlahPengajuan, jangkaWaktuBulan, sukuBungaPersen);
        String sql = "UPDATE pembiayaan SET jumlah_pengajuan = ?, jangka_waktu_bulan = ?, suku_bunga_persen = ?, " +
                     "total_kewajiban = ?, skema_pembiayaan = ?, skor_kelayakan = ? WHERE id_pembiayaan = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, jumlahPengajuan);
            ps.setInt(2, jangkaWaktuBulan);
            ps.setBigDecimal(3, sukuBungaPersen);
            ps.setBigDecimal(4, totalKewajiban);
            ps.setString(5, skemaPembiayaan);
            if (skorKelayakan != null) {
                ps.setInt(6, skorKelayakan);
            } else {
                ps.setNull(6, Types.INTEGER);
            }
            ps.setInt(7, idPembiayaan);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean putuskanPembiayaan(int idPembiayaan, Integer manajerIdPengguna, boolean disetujui) throws SQLException {
        String statusBaru = disetujui ? "Disetujui" : "Ditolak";

        try (Connection conn = DatabaseConnection.getConnection()) {
            if (disetujui && manajerIdPengguna != null) {
                String cekLimitSql = "SELECT p.jumlah_pengajuan, m.batas_persetujuan " +
                                     "FROM pembiayaan p " +
                                     "LEFT JOIN manajer m ON m.id_pengguna = ? " +
                                     "WHERE p.id_pembiayaan = ?";
                try (PreparedStatement psCek = conn.prepareStatement(cekLimitSql)) {
                    psCek.setInt(1, manajerIdPengguna);
                    psCek.setInt(2, idPembiayaan);
                    try (ResultSet rs = psCek.executeQuery()) {
                        if (rs.next()) {
                            BigDecimal pengajuan = rs.getBigDecimal("jumlah_pengajuan");
                            BigDecimal batas = rs.getBigDecimal("batas_persetujuan");
                            if (batas != null && pengajuan.compareTo(batas) > 0) {
                                throw new IllegalStateException("Jumlah pengajuan melebihi batas persetujuan Manajer (Rp " + batas + ")!");
                            }
                        }
                    }
                }
            }

            String sql = "UPDATE pembiayaan SET status_pembiayaan = ?, manajer_id_pengguna = ? WHERE id_pembiayaan = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, statusBaru);
                if (manajerIdPengguna != null) {
                    ps.setInt(2, manajerIdPengguna);
                } else {
                    ps.setNull(2, Types.INTEGER);
                }
                ps.setInt(3, idPembiayaan);
                return ps.executeUpdate() > 0;
            }
        }
    }
}
