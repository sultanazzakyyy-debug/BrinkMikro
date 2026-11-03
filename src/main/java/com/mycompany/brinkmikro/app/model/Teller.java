/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brinkmikro.app.model;

/**
 *
 * @author ASUS
 */

public class Teller extends Pengguna {
    private String loket;
    private String shiftTeller; // 'Pagi', 'Siang'

    public Teller() {}

    public Teller(int idPengguna, String namaLengkap, String username, String password, String statusAktif,
                  String loket, String shiftTeller) {
        super(idPengguna, namaLengkap, username, password, "Teller", statusAktif);
        this.loket = loket;
        this.shiftTeller = shiftTeller;
    }

    public String getLoket() { return loket; }
    public void setLoket(String loket) { this.loket = loket; }

    public String getShiftTeller() { return shiftTeller; }
    public void setShiftTeller(String shiftTeller) { this.shiftTeller = shiftTeller; }
}