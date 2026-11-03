/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brinkmikro.app.model;

/**
 *
 * @author ASUS
 */

public class Admin extends Pengguna {
    private String cakupanTugas;
    private String keteranganDetail;

    public Admin() {}

    public Admin(int idPengguna, String namaLengkap, String username, String password, String statusAktif,
                 String cakupanTugas, String keteranganDetail) {
        super(idPengguna, namaLengkap, username, password, "Admin", statusAktif);
        this.cakupanTugas = cakupanTugas;
        this.keteranganDetail = keteranganDetail;
    }

    public String getCakupanTugas() { return cakupanTugas; }
    public void setCakupanTugas(String cakupanTugas) { this.cakupanTugas = cakupanTugas; }

    public String getKeteranganDetail() { return keteranganDetail; }
    public void setKeteranganDetail(String keteranganDetail) { this.keteranganDetail = keteranganDetail; }
}