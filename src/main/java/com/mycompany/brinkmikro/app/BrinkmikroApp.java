/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.brinkmikro.app;

/**
 *
 * @author ASUS
 */

import com.mycompany.brinkmikro.app.dao.MonitoringDAO;
import com.mycompany.brinkmikro.app.dao.NasabahDAO;
import com.mycompany.brinkmikro.app.dao.PembiayaanDAO;
import com.mycompany.brinkmikro.app.dao.RekeningTransaksiDAO;
import com.mycompany.brinkmikro.app.service.AuthService;
import java.math.BigDecimal;
import java.util.Map;

public class BrinkmikroApp {

    public static void main(String[] args) {
        System.out.println("=== UJI KONEKSI & BACKEND BRINKMIKRO ===");

        AuthService auth = new AuthService();
        AuthService.LoginResult hasilLogin = auth.login("admin_mikhel", "123456");
        System.out.println("Status Login             : " + hasilLogin.getMessage());

        try {
            NasabahDAO nasabahDao = new NasabahDAO();
            RekeningTransaksiDAO rekeningDao = new RekeningTransaksiDAO();
            PembiayaanDAO pembiayaanDao = new PembiayaanDAO();
            MonitoringDAO monitoringDao = new MonitoringDAO();

            System.out.println("Total Nasabah            : " + nasabahDao.getAllNasabah().size() + " data");
            System.out.println("Total Rekening Tabungan  : " + rekeningDao.getAllRekening().size() + " data");
            System.out.println("Total Pengajuan Pinjaman : " + pembiayaanDao.getAllPembiayaan().size() + " data");

            Map<String, BigDecimal> kas = monitoringDao.getDashboardKas();
            System.out.println("Total Saldo Tabungan     : Rp " + kas.get("total_saldo_tabungan"));
            System.out.println("Total Pembiayaan Cair    : Rp " + kas.get("total_pembiayaan_cair"));
            System.out.println("Total Angsuran Masuk     : Rp " + kas.get("total_angsuran_masuk"));
            System.out.println("=== BACKEND SIAP DIGUNAKAN OLEH TIM GUI ===");
        } catch (Exception e) {
            System.err.println("Gagal terhubung ke database: " + e.getMessage());
        }
    }
}