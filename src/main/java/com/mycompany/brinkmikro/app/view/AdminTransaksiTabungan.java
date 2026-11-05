/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.brinkmikro.app.view;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Class: AdminTransaksiTabungan
 * Role: Admin
 * Fitur: Single Multi-Field Form Dialog CRUD, Real-Time Filter Search, Validasi Format Tanggal (dd-MM-yyyy), & Left-Shifted Logout Button (x=720)
 */
public class AdminTransaksiTabungan extends javax.swing.JFrame {

    private DefaultTableModel model;
    private TableRowSorter<DefaultTableModel> sorter;

    /**
     * Creates new form AdminTransaksiTabungan
     */
    public AdminTransaksiTabungan() {
        initComponents();
        setLocationRelativeTo(null);
        initTableAndSearch();
    }

    private void initTableAndSearch() {
        // AKTIFKAN HORIZONTAL SCROLLBAR PADA JTABLE
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Atur lebar minimal tiap kolom agar scrollbar horizontal aktif & nyaman dibaca
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(160);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(180);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(160);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(180);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(130);

        model = (DefaultTableModel) jTable1.getModel();
        sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);

        // Real-time Search Listener
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { filterSearch(); }
            @Override
            public void removeUpdate(DocumentEvent e) { filterSearch(); }
            @Override
            public void changedUpdate(DocumentEvent e) { filterSearch(); }
        });
    }

    private void filterSearch() {
        String query = txtSearch.getText().trim();
        if (query.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButtonLogout = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnAction1 = new javax.swing.JButton();
        btnAction2 = new javax.swing.JButton();
        btnAction3 = new javax.swing.JButton();
        jLabelSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BrinkMikro - Transaksi Tabungan (Setor / Tarik)");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 0, 255));
        jLabel3.setText("BrinkMikro");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Super Administrator");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 130, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Menu Navigasi");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 130, -1));

        jLabel2.setBackground(new java.awt.Color(102, 102, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 0, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Admin");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 255)));
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 70, 20));

        btnNav1 = new javax.swing.JButton();
        btnNav1.setBackground(new java.awt.Color(255, 255, 255));
        btnNav1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNav1.setForeground(new java.awt.Color(0, 0, 0));
        btnNav1.setText("1. Data Pengguna");
        btnNav1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNav1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNav1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnNav1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 290, 40));

        btnNav2 = new javax.swing.JButton();
        btnNav2.setBackground(new java.awt.Color(255, 255, 255));
        btnNav2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNav2.setForeground(new java.awt.Color(0, 0, 0));
        btnNav2.setText("2. Data Nasabah");
        btnNav2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNav2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNav2ActionPerformed(evt);
            }
        });
        jPanel2.add(btnNav2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 290, 40));

        btnNav3 = new javax.swing.JButton();
        btnNav3.setBackground(new java.awt.Color(255, 255, 255));
        btnNav3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNav3.setForeground(new java.awt.Color(0, 0, 0));
        btnNav3.setText("3. Rekening Tabungan");
        btnNav3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNav3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNav3ActionPerformed(evt);
            }
        });
        jPanel2.add(btnNav3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 290, 40));

        btnNav4 = new javax.swing.JButton();
        btnNav4.setBackground(new java.awt.Color(0, 0, 255));
        btnNav4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNav4.setForeground(new java.awt.Color(255, 255, 255));
        btnNav4.setText("4. Transaksi Tabungan");
        btnNav4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNav4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNav4ActionPerformed(evt);
            }
        });
        jPanel2.add(btnNav4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 290, 40));

        btnNav5 = new javax.swing.JButton();
        btnNav5.setBackground(new java.awt.Color(255, 255, 255));
        btnNav5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNav5.setForeground(new java.awt.Color(0, 0, 0));
        btnNav5.setText("5. Pengajuan Pembiayaan");
        btnNav5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNav5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNav5ActionPerformed(evt);
            }
        });
        jPanel2.add(btnNav5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 290, 40));

        btnNav6 = new javax.swing.JButton();
        btnNav6.setBackground(new java.awt.Color(255, 255, 255));
        btnNav6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNav6.setForeground(new java.awt.Color(0, 0, 0));
        btnNav6.setText("6. Data Angsuran");
        btnNav6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNav6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNav6ActionPerformed(evt);
            }
        });
        jPanel2.add(btnNav6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 290, 40));

        btnNav7 = new javax.swing.JButton();
        btnNav7.setBackground(new java.awt.Color(255, 255, 255));
        btnNav7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNav7.setForeground(new java.awt.Color(0, 0, 0));
        btnNav7.setText("7. Monitoring dan Laporan");
        btnNav7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNav7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNav7ActionPerformed(evt);
            }
        });
        jPanel2.add(btnNav7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 290, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 770));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Transaksi Tabungan (Setor / Tarik)");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 10, 500, 25));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Pencatatan Transaksi Loket Teller");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 38, 500, 20));

        jButtonLogout.setBackground(new java.awt.Color(255, 255, 255));
        jButtonLogout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonLogout.setForeground(new java.awt.Color(255, 0, 0));
        jButtonLogout.setText("Logout");
        jButtonLogout.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 1, true));
        jButtonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogoutActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 15, 120, 38));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 1040, 70));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        btnAction1.setBackground(new java.awt.Color(0, 0, 255));
        btnAction1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAction1.setForeground(new java.awt.Color(255, 255, 255));
        btnAction1.setText("+ Setor / Tarik Tunai");
        btnAction1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAction1ActionPerformed(evt);
            }
        });

        btnAction2.setBackground(new java.awt.Color(255, 255, 0));
        btnAction2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAction2.setForeground(new java.awt.Color(0, 0, 0));
        btnAction2.setText("Update Transaksi");
        btnAction2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAction2ActionPerformed(evt);
            }
        });

        btnAction3.setBackground(new java.awt.Color(204, 0, 0));
        btnAction3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAction3.setForeground(new java.awt.Color(255, 255, 255));
        btnAction3.setText("Hapus Transaksi");
        btnAction3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAction3ActionPerformed(evt);
            }
        });

        jLabelSearch.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelSearch.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSearch.setText("Cari ID TRX/No. Rek:");

        txtSearch.setBackground(new java.awt.Color(255, 255, 255));
        txtSearch.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabelSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnAction1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAction2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAction3)))
                .addContainerGap(600, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAction1)
                    .addComponent(btnAction2)
                    .addComponent(btnAction3))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 1030, 110));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBackground(new java.awt.Color(255, 255, 255));
        jTable1.setForeground(new java.awt.Color(0, 0, 0));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { "TRX-001", "10012026001", "Setor Tunai", "5000000.00", "10-01-2026 08:30", "3" },
                { "TRX-002", "10012026002", "Tarik Tunai", "1500000.00", "11-01-2026 09:15", "3" }
            },
            new String [] {
                "ID Transaksi", "No. Rekening", "Jenis Transaksi", "Jumlah (Rp)", "Waktu Transaksi", "ID Teller"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(0, 0, 0));
        jTable1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable1.setShowGrid(true);
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 1030, 500));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 768));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNav1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNav1ActionPerformed
        new AdminDataPengguna().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnNav1ActionPerformed

    private void btnNav2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNav2ActionPerformed
        new AdminDataNasabah().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnNav2ActionPerformed

    private void btnNav3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNav3ActionPerformed
        new AdminRekeningTabungan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnNav3ActionPerformed

    private void btnNav4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNav4ActionPerformed
        new AdminTransaksiTabungan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnNav4ActionPerformed

    private void btnNav5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNav5ActionPerformed
        new AdminPengajuanPembiayaan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnNav5ActionPerformed

    private void btnNav6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNav6ActionPerformed
        new AdminDataAngsuran().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnNav6ActionPerformed

    private void btnNav7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNav7ActionPerformed
        new AdminMonitoringDanLaporan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnNav7ActionPerformed

    private void btnAction1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAction1ActionPerformed
        // AKSI TAMBAH DATA (SINGLE FORM DIALOG)

        javax.swing.JPanel panelForm = new javax.swing.JPanel(new java.awt.GridLayout(6, 2, 10, 10));
        javax.swing.JTextField[] txtFields = new javax.swing.JTextField[6];
        String[] colLabels = new String[] { "ID Transaksi", "No. Rekening", "Jenis Transaksi", "Jumlah (Rp)", "Waktu Transaksi", "ID Teller" };

        for (int i = 0; i < 6; i++) {
            panelForm.add(new javax.swing.JLabel(colLabels[i] + " :"));
            txtFields[i] = new javax.swing.JTextField(15);
            panelForm.add(txtFields[i]);
        }

        int result = JOptionPane.showConfirmDialog(
            this, 
            panelForm, 
            "Form Input Transaksi Tabungan (Setor / Tarik)", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            
            // Validasi Input Tanggal (Urutan Wajib: Tanggal-Bulan-Tahun / dd-MM-yyyy)
            for (int i = 0; i < colLabels.length; i++) {
                String lbl = colLabels[i].toLowerCase();
                if (lbl.contains("tanggal") || lbl.contains("tgl") || lbl.contains("waktu") || lbl.contains("timestamp")) {
                    String val = txtFields[i].getText().trim();
                    if (!val.isEmpty()) {
                        // Cek jika pengguna menginput tahun terlebih dahulu (cth: 2026-01-10)
                        if (val.matches("^\\d{4}.*")) {
                            JOptionPane.showMessageDialog(
                                this, 
                                "FORMAT TANGGAL SALAH!\n\n" +
                                "Anda memasukkan tahun terlebih dahulu: '" + val + "'\n" +
                                "Sistem mewajibkan urutan: Tanggal-Bulan-Tahun (dd-MM-yyyy).\n" +
                                "Contoh yang benar: 10-01-2026 atau 10-01-2026 08:30\n\n" +
                                "Silakan input ulang tanggal Anda!", 
                                "Format Tanggal Ditolak", 
                                JOptionPane.WARNING_MESSAGE
                            );
                            txtFields[i].requestFocus();
                            return;
                        }
                        // Cek kelayakan pola format dd-MM-yyyy
                        if (!val.matches("^\\d{2}-\\d{2}-\\d{4}.*")) {
                            JOptionPane.showMessageDialog(
                                this, 
                                "FORMAT TANGGAL TIDAK VALID!\n\n" +
                                "Input: '" + val + "'\n" +
                                "Format wajib: Tanggal-Bulan-Tahun dengan pemisah strip (dd-MM-yyyy).\n" +
                                "Contoh: 10-01-2026", 
                                "Format Tanggal Ditolak", 
                                JOptionPane.WARNING_MESSAGE
                            );
                            txtFields[i].requestFocus();
                            return;
                        }
                    }
                }
            }
    

            Object[] rowData = new Object[6];
            for (int i = 0; i < 6; i++) {
                String val = txtFields[i].getText().trim();
                if (val.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Kolom '" + colLabels[i] + "' wajib diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                rowData[i] = val;
            }
            model.addRow(rowData);
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        }
    
    }//GEN-LAST:event_btnAction1ActionPerformed

    private void btnAction2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAction2ActionPerformed
        // AKSI UPDATE / EDIT DATA (SINGLE FORM DIALOG)

        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris pada tabel yang ingin di-update terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int modelRow = jTable1.convertRowIndexToModel(selectedRow);

        javax.swing.JPanel panelForm = new javax.swing.JPanel(new java.awt.GridLayout(6, 2, 10, 10));
        javax.swing.JTextField[] txtFields = new javax.swing.JTextField[6];
        String[] colLabels = new String[] { "ID Transaksi", "No. Rekening", "Jenis Transaksi", "Jumlah (Rp)", "Waktu Transaksi", "ID Teller" };

        for (int i = 0; i < 6; i++) {
            panelForm.add(new javax.swing.JLabel(colLabels[i] + " :"));
            txtFields[i] = new javax.swing.JTextField(15);
            Object currentVal = model.getValueAt(modelRow, i);
            txtFields[i].setText(currentVal != null ? currentVal.toString() : "");
            panelForm.add(txtFields[i]);
        }

        int result = JOptionPane.showConfirmDialog(
            this, 
            panelForm, 
            "Form Edit Transaksi Tabungan (Setor / Tarik)", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            
            // Validasi Input Tanggal (Urutan Wajib: Tanggal-Bulan-Tahun / dd-MM-yyyy)
            for (int i = 0; i < colLabels.length; i++) {
                String lbl = colLabels[i].toLowerCase();
                if (lbl.contains("tanggal") || lbl.contains("tgl") || lbl.contains("waktu") || lbl.contains("timestamp")) {
                    String val = txtFields[i].getText().trim();
                    if (!val.isEmpty()) {
                        // Cek jika pengguna menginput tahun terlebih dahulu (cth: 2026-01-10)
                        if (val.matches("^\\d{4}.*")) {
                            JOptionPane.showMessageDialog(
                                this, 
                                "FORMAT TANGGAL SALAH!\n\n" +
                                "Anda memasukkan tahun terlebih dahulu: '" + val + "'\n" +
                                "Sistem mewajibkan urutan: Tanggal-Bulan-Tahun (dd-MM-yyyy).\n" +
                                "Contoh yang benar: 10-01-2026 atau 10-01-2026 08:30\n\n" +
                                "Silakan input ulang tanggal Anda!", 
                                "Format Tanggal Ditolak", 
                                JOptionPane.WARNING_MESSAGE
                            );
                            txtFields[i].requestFocus();
                            return;
                        }
                        // Cek kelayakan pola format dd-MM-yyyy
                        if (!val.matches("^\\d{2}-\\d{2}-\\d{4}.*")) {
                            JOptionPane.showMessageDialog(
                                this, 
                                "FORMAT TANGGAL TIDAK VALID!\n\n" +
                                "Input: '" + val + "'\n" +
                                "Format wajib: Tanggal-Bulan-Tahun dengan pemisah strip (dd-MM-yyyy).\n" +
                                "Contoh: 10-01-2026", 
                                "Format Tanggal Ditolak", 
                                JOptionPane.WARNING_MESSAGE
                            );
                            txtFields[i].requestFocus();
                            return;
                        }
                    }
                }
            }
    

            for (int i = 0; i < 6; i++) {
                String val = txtFields[i].getText().trim();
                if (val.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Kolom '" + colLabels[i] + "' tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                model.setValueAt(val, modelRow, i);
            }
            JOptionPane.showMessageDialog(this, "Data berhasil diperbarui!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        }
    
    }//GEN-LAST:event_btnAction2ActionPerformed

    private void btnAction3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAction3ActionPerformed
        // AKSI HAPUS DATA (DELETE)
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris pada tabel yang ingin dihapus terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            this, 
            "Apakah Anda yakin ingin menghapus data yang dipilih ini?", 
            "Konfirmasi Hapus Data", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            int modelRow = jTable1.convertRowIndexToModel(selectedRow);
            model.removeRow(modelRow);
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus dari tabel!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAction3ActionPerformed

    private void jButtonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogoutActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin logout?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            new FormLogin().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jButtonLogoutActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(AdminTransaksiTabungan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminTransaksiTabungan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAction1;
    private javax.swing.JButton btnAction2;
    private javax.swing.JButton btnAction3;
    private javax.swing.JButton btnNav1;
    private javax.swing.JButton btnNav2;
    private javax.swing.JButton btnNav3;
    private javax.swing.JButton btnNav4;
    private javax.swing.JButton btnNav5;
    private javax.swing.JButton btnNav6;
    private javax.swing.JButton btnNav7;
    private javax.swing.JButton jButtonLogout;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
