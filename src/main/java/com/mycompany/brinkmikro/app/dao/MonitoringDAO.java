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
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonitoringDAO {

    public static class JatuhTempoRecord {
        public int idPembiayaan, jangkaWaktuBulan;
        public String nik, namaNasabah, noTelepon, jenisNasabah, skemaPembiayaan;
        public BigDecimal totalKewajiban, totalTerbayar, sisaKewajiban;
        public Timestamp pembayaranTerakhir;
        public Date tanggalPengajuan, tanggalJatuhTempoAkhir;
    }

    public static class RekapRecord {
        public String jenisNasabah, statusPembiayaan;
        public int jumlahAkun;
        public BigDecimal totalPokok, totalKewajiban;
    }

    public Map<String, BigDecimal> getDashboardKas() throws SQLException {
        Map<String, BigDecimal> dashboard = new HashMap<>();
        String sqlSaldoTabungan = "SELECT COALESCE(SUM(saldo), 0) FROM rekening_tabungan WHERE status_rekening = 'Aktif'";
        String sqlTotalSetoran = "SELECT COALESCE(SUM(jumlah), 0) FROM transaksi_tabungan WHERE jenis_transaksi = 'Setor Tunai'";
        String sqlTotalPenarikan = "SELECT COALESCE(SUM(jumlah), 0) FROM transaksi_tabungan WHERE jenis_transaksi = 'Tarik Tunai'";
        String sqlPembiayaanCair = "SELECT COALESCE(SUM(jumlah_pengajuan), 0) FROM pembiayaan WHERE status_pembiayaan IN ('Disetujui', 'Lunas')";
        String sqlAngsuranMasuk = "SELECT COALESCE(SUM(jumlah_bayar + denda), 0) FROM angsuran";

        try (Connection conn = DatabaseConnection.getConnection()) {
            dashboard.put("total_saldo_tabungan", executeSingleDecimal(conn, sqlSaldoTabungan));
            dashboard.put("total_setor_tunai", executeSingleDecimal(conn, sqlTotalSetoran));
            dashboard.put("total_tarik_tunai", executeSingleDecimal(conn, sqlTotalPenarikan));
            dashboard.put("total_pembiayaan_cair", executeSingleDecimal(conn, sqlPembiayaanCair));
            dashboard.put("total_angsuran_masuk", executeSingleDecimal(conn, sqlAngsuranMasuk));
        }
        return dashboard;
    }

    private BigDecimal executeSingleDecimal(Connection conn, String sql) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getBigDecimal(1) : BigDecimal.ZERO;
        }
    }

    public List<JatuhTempoRecord> getDaftarJatuhTempo() throws SQLException {
        List<JatuhTempoRecord> list = new ArrayList<>();
        String sql = "SELECT p.id_pembiayaan, n.nik, n.nama_nasabah, n.no_telepon, n.jenis_nasabah, " +
                     "p.skema_pembiayaan, p.tanggal_pengajuan, p.jangka_waktu_bulan, p.total_kewajiban, " +
                     "COALESCE(SUM(a.jumlah_bayar), 0) AS total_terbayar, " +
                     "(p.total_kewajiban - COALESCE(SUM(a.jumlah_bayar), 0)) AS sisa_kewajiban, " +
                     "MAX(a.tanggal_bayar) AS pembayaran_terakhir, " +
                     "DATE_ADD(p.tanggal_pengajuan, INTERVAL p.jangka_waktu_bulan MONTH) AS tanggal_jatuh_tempo_akhir " +
                     "FROM pembiayaan p " +
                     "JOIN nasabah n ON p.nasabah_nik = n.nik " +
                     "LEFT JOIN angsuran a ON p.id_pembiayaan = a.id_pembiayaan " +
                     "WHERE p.status_pembiayaan = 'Disetujui' " +
                     "GROUP BY p.id_pembiayaan " +
                     "ORDER BY tanggal_jatuh_tempo_akhir ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                JatuhTempoRecord r = new JatuhTempoRecord();
                r.idPembiayaan = rs.getInt("id_pembiayaan");
                r.nik = rs.getString("nik");
                r.namaNasabah = rs.getString("nama_nasabah");
                r.noTelepon = rs.getString("no_telepon");
                r.jenisNasabah = rs.getString("jenis_nasabah");
                r.skemaPembiayaan = rs.getString("skema_pembiayaan");
                r.tanggalPengajuan = rs.getDate("tanggal_pengajuan");
                r.jangkaWaktuBulan = rs.getInt("jangka_waktu_bulan");
                r.totalKewajiban = rs.getBigDecimal("total_kewajiban");
                r.totalTerbayar = rs.getBigDecimal("total_terbayar");
                r.sisaKewajiban = rs.getBigDecimal("sisa_kewajiban");
                r.pembayaranTerakhir = rs.getTimestamp("pembayaran_terakhir");
                r.tanggalJatuhTempoAkhir = rs.getDate("tanggal_jatuh_tempo_akhir");
                list.add(r);
            }
        }
        return list;
    }

    public List<RekapRecord> getRekapLaporanPembiayaan() throws SQLException {
        List<RekapRecord> list = new ArrayList<>();
        String sql = "SELECT n.jenis_nasabah, p.status_pembiayaan, COUNT(p.id_pembiayaan) AS jumlah_akun, " +
                     "SUM(p.jumlah_pengajuan) AS total_pokok, SUM(p.total_kewajiban) AS total_kewajiban " +
                     "FROM pembiayaan p " +
                     "JOIN nasabah n ON p.nasabah_nik = n.nik " +
                     "GROUP BY n.jenis_nasabah, p.status_pembiayaan";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                RekapRecord r = new RekapRecord();
                r.jenisNasabah = rs.getString("jenis_nasabah");
                r.statusPembiayaan = rs.getString("status_pembiayaan");
                r.jumlahAkun = rs.getInt("jumlah_akun");
                r.totalPokok = rs.getBigDecimal("total_pokok");
                r.totalKewajiban = rs.getBigDecimal("total_kewajiban");
                list.add(r);
            }
        }
        return list;
    }
}