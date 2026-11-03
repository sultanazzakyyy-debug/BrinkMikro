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

public class Petani extends Nasabah {
    private BigDecimal luasLahan;
    private String komoditasUtama;
    private BigDecimal pendapatanTahunan;

    public Petani() {}

    public Petani(String nik, String namaNasabah, String alamat, String noTelepon, Date tanggalDaftar,
                  BigDecimal luasLahan, String komoditasUtama, BigDecimal pendapatanTahunan) {
        super(nik, namaNasabah, alamat, noTelepon, "Petani", tanggalDaftar);
        this.luasLahan = luasLahan;
        this.komoditasUtama = komoditasUtama;
        this.pendapatanTahunan = pendapatanTahunan;
    }

    public BigDecimal getLuasLahan() { return luasLahan; }
    public void setLuasLahan(BigDecimal luasLahan) { this.luasLahan = luasLahan; }

    public String getKomoditasUtama() { return komoditasUtama; }
    public void setKomoditasUtama(String komoditasUtama) { this.komoditasUtama = komoditasUtama; }

    public BigDecimal getPendapatanTahunan() { return pendapatanTahunan; }
    public void setPendapatanTahunan(BigDecimal pendapatanTahunan) { this.pendapatanTahunan = pendapatanTahunan; }
}
