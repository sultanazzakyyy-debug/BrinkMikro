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
import java.util.ArrayList;
import java.util.List;

public class AngsuranDAO {

    public static class AngsuranRecord {
        public int idAngsuran, angsuranKe, idPembiayaan, tellerIdPengguna;
        public BigDecimal jumlahBayar, denda, totalKewajiban;
        public Timestamp tanggalBayar;
        public String nasabahNik, namaNasabah, skemaPembiayaan, statusPembiayaan, namaTeller;
    }

    public List<AngsuranRecord> getAllAngsuran() throws SQLException {
        List<AngsuranRecord> list = new ArrayList<>();
        String sql = "SELECT a.*, p.nasabah_nik, n.nama_nasabah, p.skema_pembiayaan, " +
                     "p.total_kewajiban, p.status_pembiayaan, u.nama_lengkap AS nama_teller " +
                     "FROM angsuran a " +
                     "JOIN pembiayaan p ON a.id_pembiayaan = p.id_pembiayaan " +
                     "JOIN nasabah n ON p.nasabah_nik = n.nik " +
                     "JOIN pengguna u ON a.teller_id_pengguna = u.id_pengguna " +
                     "ORDER BY a.tanggal_bayar DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                AngsuranRecord r = new AngsuranRecord();
                r.idAngsuran = rs.getInt("id_angsuran");
                r.angsuranKe = rs.getInt("angsuran_ke");
                r.jumlahBayar = rs.getBigDecimal("jumlah_bayar");
                r.tanggalBayar = rs.getTimestamp("tanggal_bayar");
                r.denda = rs.getBigDecimal("denda");
                r.idPembiayaan = rs.getInt("id_pembiayaan");
                r.nasabahNik = rs.getString("nasabah_nik");
                r.namaNasabah = rs.getString("nama_nasabah");
                r.skemaPembiayaan = rs.getString("skema_pembiayaan");
                r.totalKewajiban = rs.getBigDecimal("total_kewajiban");
                r.statusPembiayaan = rs.getString("status_pembiayaan");
                r.tellerIdPengguna = rs.getInt("teller_id_pengguna");
                r.namaTeller = rs.getString("nama_teller");
                list.add(r);
            }
        }
        return list;
    }

    public boolean tambahAngsuran(int idPembiayaan, BigDecimal jumlahBayar, BigDecimal denda, int tellerIdPengguna) throws SQLException {
        if (jumlahBayar == null || jumlahBayar.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Nominal angsuran harus lebih dari Rp 0!");
        }
        if (denda == null || denda.compareTo(BigDecimal.ZERO) < 0) {
            denda = BigDecimal.ZERO;
        }

        String cekPembiayaanSql = "SELECT total_kewajiban, status_pembiayaan FROM pembiayaan WHERE id_pembiayaan = ? FOR UPDATE";
        String cekUrutanSql = "SELECT COALESCE(MAX(angsuran_ke), 0) + 1 AS next_ke, COALESCE(SUM(jumlah_bayar), 0) AS total_terbayar " +
                              "FROM angsuran WHERE id_pembiayaan = ?";
        String insertSql = "INSERT INTO angsuran (angsuran_ke, jumlah_bayar, tanggal_bayar, denda, id_pembiayaan, teller_id_pengguna) " +
                           "VALUES (?, ?, NOW(), ?, ?, ?)";
        String updateLunasSql = "UPDATE pembiayaan SET status_pembiayaan = 'Lunas' WHERE id_pembiayaan = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                BigDecimal totalKewajiban;
                try (PreparedStatement psCek = conn.prepareStatement(cekPembiayaanSql)) {
                    psCek.setInt(1, idPembiayaan);
                    try (ResultSet rs = psCek.executeQuery()) {
                        if (!rs.next()) {
                            throw new IllegalArgumentException("Data pembiayaan tidak ditemukan!");
                        }
                        String status = rs.getString("status_pembiayaan");
                        if (!"Disetujui".equalsIgnoreCase(status)) {
                            throw new IllegalStateException("Hanya pembiayaan berstatus 'Disetujui' yang dapat dibayar.");
                        }
                        totalKewajiban = rs.getBigDecimal("total_kewajiban");
                    }
                }

                int nextAngsuranKe = 1;
                BigDecimal totalTerbayarSebelumnya = BigDecimal.ZERO;
                try (PreparedStatement psUrut = conn.prepareStatement(cekUrutanSql)) {
                    psUrut.setInt(1, idPembiayaan);
                    try (ResultSet rs = psUrut.executeQuery()) {
                        if (rs.next()) {
                            nextAngsuranKe = rs.getInt("next_ke");
                            totalTerbayarSebelumnya = rs.getBigDecimal("total_terbayar");
                        }
                    }
                }

                try (PreparedStatement psIns = conn.prepareStatement(insertSql)) {
                    psIns.setInt(1, nextAngsuranKe);
                    psIns.setBigDecimal(2, jumlahBayar);
                    psIns.setBigDecimal(3, denda);
                    psIns.setInt(4, idPembiayaan);
                    psIns.setInt(5, tellerIdPengguna);
                    psIns.executeUpdate();
                }

                BigDecimal totalSetelahBayar = totalTerbayarSebelumnya.add(jumlahBayar);
                if (totalSetelahBayar.compareTo(totalKewajiban) >= 0) {
                    try (PreparedStatement psLunas = conn.prepareStatement(updateLunasSql)) {
                        psLunas.setInt(1, idPembiayaan);
                        psLunas.executeUpdate();
                    }
                }

                conn.commit();
                return true;
            } catch (Exception ex) {
                conn.rollback();
                throw ex;
            }
        }
    }
}