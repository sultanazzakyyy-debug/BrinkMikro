/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brinkmikro.app.model;

/**
 *
 * @author ASUS
 */

import java.math.BigDecimal;
import java.sql.Date;

public class PedagangUmkm extends Nasabah {
    private String jenisUsaha;
    private String lokasiUsaha;
    private BigDecimal omzetBulanan;

    public PedagangUmkm() {}

    public PedagangUmkm(String nik, String namaNasabah, String alamat, String noTelepon, Date tanggalDaftar,
                        String jenisUsaha, String lokasiUsaha, BigDecimal omzetBulanan) {
        super(nik, namaNasabah, alamat, noTelepon, "UMKM", tanggalDaftar);
        this.jenisUsaha = jenisUsaha;
        this.lokasiUsaha = lokasiUsaha;
        this.omzetBulanan = omzetBulanan;
    }

    public String getJenisUsaha() { return jenisUsaha; }
    public void setJenisUsaha(String jenisUsaha) { this.jenisUsaha = jenisUsaha; }

    public String getLokasiUsaha() { return lokasiUsaha; }
    public void setLokasiUsaha(String lokasiUsaha) { this.lokasiUsaha = lokasiUsaha; }

    public BigDecimal getOmzetBulanan() { return omzetBulanan; }
    public void setOmzetBulanan(BigDecimal omzetBulanan) { this.omzetBulanan = omzetBulanan; }
}
