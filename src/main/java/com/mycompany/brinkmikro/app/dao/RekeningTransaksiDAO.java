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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RekeningTransaksiDAO {

    public static class RekeningRecord {
        public String nomorRekening, statusRekening, nasabahNik, namaNasabah, jenisNasabah;
        public BigDecimal saldo;
        public Date tanggalPembukaan;
    }

    public static class TransaksiRecord {
        public int idTransaksi, tellerIdPengguna;
        public Timestamp tanggalTransaksi;
        public String jenisTransaksi;
        public BigDecimal jumlah;
        public String nomorRekening, nasabahNik, namaNasabah, namaTeller;
    }

    public List<RekeningRecord> getAllRekening() throws SQLException {
        List<RekeningRecord> list = new ArrayList<>();
        String sql = "SELECT r.*, n.nama_nasabah, n.jenis_nasabah " +
                     "FROM rekening_tabungan r " +
                     "JOIN nasabah n ON r.nasabah_nik = n.nik " +
                     "ORDER BY r.tanggal_pembukaan DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                RekeningRecord r = new RekeningRecord();
                r.nomorRekening = rs.getString("nomor_rekening");
                r.saldo = rs.getBigDecimal("saldo");
                r.tanggalPembukaan = rs.getDate("tanggal_pembukaan");
                r.statusRekening = rs.getString("status_rekening");
                r.nasabahNik = rs.getString("nasabah_nik");
                r.namaNasabah = rs.getString("nama_nasabah");
                r.jenisNasabah = rs.getString("jenis_nasabah");
                list.add(r);
            }
        }
        return list;
    }

    public boolean bukaRekeningBaru(String nomorRekening, BigDecimal saldoAwal, String statusRekening, String nikNasabah) throws SQLException {
        if (saldoAwal == null || saldoAwal.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Saldo awal tidak boleh negatif!");
        }
        String sql = "INSERT INTO rekening_tabungan (nomor_rekening, saldo, tanggal_pembukaan, status_rekening, nasabah_nik) " +
                     "VALUES (?, ?, CURRENT_DATE, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nomorRekening);
            ps.setBigDecimal(2, saldoAwal);
            ps.setString(3, statusRekening != null ? statusRekening : "Aktif");
            ps.setString(4, nikNasabah);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean updateStatusRekening(String nomorRekening, String statusBaru) throws SQLException {
        String sql = "UPDATE rekening_tabungan SET status_rekening = ? WHERE nomor_rekening = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, statusBaru);
            ps.setString(2, nomorRekening);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean hapusRekening(String nomorRekening) throws SQLException {
        String sql = "DELETE FROM rekening_tabungan WHERE nomor_rekening = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nomorRekening);
            return ps.executeUpdate() > 0;
        }
    }

    public List<TransaksiRecord> getRiwayatTransaksi(String jenisTransaksi) throws SQLException {
        List<TransaksiRecord> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT t.*, r.nasabah_nik, n.nama_nasabah, p.nama_lengkap AS nama_teller " +
                "FROM transaksi_tabungan t " +
                "JOIN rekening_tabungan r ON t.nomor_rekening = r.nomor_rekening " +
                "JOIN nasabah n ON r.nasabah_nik = n.nik " +
                "JOIN pengguna p ON t.teller_id_pengguna = p.id_pengguna ");

        if (jenisTransaksi != null && !jenisTransaksi.isEmpty()) {
            sql.append("WHERE t.jenis_transaksi = ? ");
        }
        sql.append("ORDER BY t.tanggal_transaksi DESC");

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            if (jenisTransaksi != null && !jenisTransaksi.isEmpty()) {
                ps.setString(1, jenisTransaksi);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TransaksiRecord tr = new TransaksiRecord();
                    tr.idTransaksi = rs.getInt("id_transaksi");
                    tr.tanggalTransaksi = rs.getTimestamp("tanggal_transaksi");
                    tr.jenisTransaksi = rs.getString("jenis_transaksi");
                    tr.jumlah = rs.getBigDecimal("jumlah");
                    tr.nomorRekening = rs.getString("nomor_rekening");
                    tr.nasabahNik = rs.getString("nasabah_nik");
                    tr.namaNasabah = rs.getString("nama_nasabah");
                    tr.tellerIdPengguna = rs.getInt("teller_id_pengguna");
                    tr.namaTeller = rs.getString("nama_teller");
                    list.add(tr);
                }
            }
        }
        return list;
    }

    public boolean setorTunai(String nomorRekening, BigDecimal nominal, int tellerIdPengguna) throws SQLException {
        if (nominal == null || nominal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Nominal setoran harus lebih dari Rp 0!");
        }

        String cekRekSql = "SELECT status_rekening FROM rekening_tabungan WHERE nomor_rekening = ? FOR UPDATE";
        String updateSaldoSql = "UPDATE rekening_tabungan SET saldo = saldo + ? WHERE nomor_rekening = ?";
        String insertTrxSql = "INSERT INTO transaksi_tabungan (tanggal_transaksi, jenis_transaksi, jumlah, nomor_rekening, teller_id_pengguna) " +
                              "VALUES (NOW(), 'Setor Tunai', ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                try (PreparedStatement psCek = conn.prepareStatement(cekRekSql)) {
                    psCek.setString(1, nomorRekening);
                    try (ResultSet rs = psCek.executeQuery()) {
                        if (!rs.next()) {
                            throw new IllegalArgumentException("Nomor rekening tidak ditemukan!");
                        }
                        if (!"Aktif".equalsIgnoreCase(rs.getString("status_rekening"))) {
                            throw new IllegalStateException("Rekening tidak berstatus Aktif!");
                        }
                    }
                }

                try (PreparedStatement psUpd = conn.prepareStatement(updateSaldoSql);
                     PreparedStatement psIns = conn.prepareStatement(insertTrxSql)) {
                    psUpd.setBigDecimal(1, nominal);
                    psUpd.setString(2, nomorRekening);
                    psUpd.executeUpdate();

                    psIns.setBigDecimal(1, nominal);
                    psIns.setString(2, nomorRekening);
                    psIns.setInt(3, tellerIdPengguna);
                    psIns.executeUpdate();
                }

                conn.commit();
                return true;
            } catch (Exception ex) {
                conn.rollback();
                throw ex;
            }
        }
    }

    public boolean tarikTunai(String nomorRekening, BigDecimal nominal, int tellerIdPengguna) throws SQLException {
        if (nominal == null || nominal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Nominal penarikan harus lebih dari Rp 0!");
        }

        String cekSaldoSql = "SELECT saldo, status_rekening FROM rekening_tabungan WHERE nomor_rekening = ? FOR UPDATE";
        String updateSaldoSql = "UPDATE rekening_tabungan SET saldo = saldo - ? WHERE nomor_rekening = ?";
        String insertTrxSql = "INSERT INTO transaksi_tabungan (tanggal_transaksi, jenis_transaksi, jumlah, nomor_rekening, teller_id_pengguna) " +
                              "VALUES (NOW(), 'Tarik Tunai', ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                try (PreparedStatement psCek = conn.prepareStatement(cekSaldoSql)) {
                    psCek.setString(1, nomorRekening);
                    try (ResultSet rs = psCek.executeQuery()) {
                        if (!rs.next()) {
                            throw new IllegalArgumentException("Nomor rekening tidak ditemukan!");
                        }
                        if (!"Aktif".equalsIgnoreCase(rs.getString("status_rekening"))) {
                            throw new IllegalStateException("Rekening tidak berstatus Aktif!");
                        }
                        BigDecimal saldoSaatIni = rs.getBigDecimal("saldo");
                        if (saldoSaatIni.compareTo(nominal) < 0) {
                            throw new IllegalStateException("Saldo tidak cukup! Saldo saat ini: Rp " + saldoSaatIni);
                        }
                    }
                }

                try (PreparedStatement psUpd = conn.prepareStatement(updateSaldoSql);
                     PreparedStatement psIns = conn.prepareStatement(insertTrxSql)) {
                    psUpd.setBigDecimal(1, nominal);
                    psUpd.setString(2, nomorRekening);
                    psUpd.executeUpdate();

                    psIns.setBigDecimal(1, nominal);
                    psIns.setString(2, nomorRekening);
                    psIns.setInt(3, tellerIdPengguna);
                    psIns.executeUpdate();
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