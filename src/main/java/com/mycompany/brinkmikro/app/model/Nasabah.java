/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brinkmikro.app.model;

/**
 *
 * @author ASUS
 */
import java.sql.Date;

public class Nasabah {
    protected String nik;
    protected String namaNasabah;
    protected String alamat;
    protected String noTelepon;
    protected String jenisNasabah; // 'Petani', 'UMKM'
    protected Date tanggalDaftar;

    public Nasabah() {}

    public Nasabah(String nik, String namaNasabah, String alamat, String noTelepon, String jenisNasabah, Date tanggalDaftar) {
        this.nik = nik;
        this.namaNasabah = namaNasabah;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
        this.jenisNasabah = jenisNasabah;
        this.tanggalDaftar = tanggalDaftar;
    }

    public String getNik() { return nik; }
    public void setNik(String nik) { this.nik = nik; }

    public String getNamaNasabah() { return namaNasabah; }
    public void setNamaNasabah(String namaNasabah) { this.namaNasabah = namaNasabah; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }

    public String getNoTelepon() { return noTelepon; }
    public void setNoTelepon(String noTelepon) { this.noTelepon = noTelepon; }

    public String getJenisNasabah() { return jenisNasabah; }
    public void setJenisNasabah(String jenisNasabah) { this.jenisNasabah = jenisNasabah; }

    public Date getTanggalDaftar() { return tanggalDaftar; }
    public void setTanggalDaftar(Date tanggalDaftar) { this.tanggalDaftar = tanggalDaftar; }
}