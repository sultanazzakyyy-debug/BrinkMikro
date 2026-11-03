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

public class Manajer extends Pengguna {
    private BigDecimal batasPersetujuan;
    private String wilayahKerja;

    public Manajer() {}

    public Manajer(int idPengguna, String namaLengkap, String username, String password, String statusAktif,
                   BigDecimal batasPersetujuan, String wilayahKerja) {
        super(idPengguna, namaLengkap, username, password, "Manajer", statusAktif);
        this.batasPersetujuan = batasPersetujuan;
        this.wilayahKerja = wilayahKerja;
    }

    public BigDecimal getBatasPersetujuan() { return batasPersetujuan; }
    public void setBatasPersetujuan(BigDecimal batasPersetujuan) { this.batasPersetujuan = batasPersetujuan; }

    public String getWilayahKerja() { return wilayahKerja; }
    public void setWilayahKerja(String wilayahKerja) { this.wilayahKerja = wilayahKerja; }
}