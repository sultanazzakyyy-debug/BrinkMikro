/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brinkmikro.app.model;

/**
 *
 * @author ASUS
 */
public class Pengguna {
    protected int idPengguna;
    protected String namaLengkap;
    protected String username;
    protected String password;
    protected String peran;       // 'Admin', 'Teller', 'Manajer'
    protected String statusAktif; // 'Aktif', 'Terkunci', 'Nonaktif'

    public Pengguna() {}

    public Pengguna(int idPengguna, String namaLengkap, String username, String password, String peran, String statusAktif) {
        this.idPengguna = idPengguna;
        this.namaLengkap = namaLengkap;
        this.username = username;
        this.password = password;
        this.peran = peran;
        this.statusAktif = statusAktif;
    }

    public int getIdPengguna() { return idPengguna; }
    public void setIdPengguna(int idPengguna) { this.idPengguna = idPengguna; }

    public String getNamaLengkap() { return namaLengkap; }
    public void setNamaLengkap(String namaLengkap) { this.namaLengkap = namaLengkap; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPeran() { return peran; }
    public void setPeran(String peran) { this.peran = peran; }

    public String getStatusAktif() { return statusAktif; }
    public void setStatusAktif(String statusAktif) { this.statusAktif = statusAktif; }
}