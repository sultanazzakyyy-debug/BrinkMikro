package com.mycompany.brinkmikro.app.view;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mycompany.brinkmikro.app.config.DatabaseConnection;

public class FormLogin extends javax.swing.JFrame {

    public FormLogin() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelCard = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        chkShowPassword = new javax.swing.JCheckBox();
        lblRole = new javax.swing.JLabel();
        cmbRole = new javax.swing.JComboBox<>();
        btnLogin = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        lblFooter = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BrinkMikro - Login System");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(235, 240, 245));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelCard.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCard.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(221, 221, 221), 1, true));
        jPanelCard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(51, 0, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("BrinkMikro");
        jPanelCard.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 380, 40));

        lblSubtitle.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSubtitle.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubtitle.setText("Sistem Informasi Bank & Keuangan Mikro");
        jPanelCard.add(lblSubtitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 380, 20));

        lblUsername.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(51, 51, 51));
        lblUsername.setText("Username / ID Teller");
        jPanelCard.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 115, 360, 20));

        txtUsername.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jPanelCard.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 360, 38));

        lblPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(51, 51, 51));
        lblPassword.setText("Password");
        jPanelCard.add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 360, 20));

        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jPanelCard.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 215, 360, 38));

        chkShowPassword.setBackground(new java.awt.Color(255, 255, 255));
        chkShowPassword.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        chkShowPassword.setForeground(new java.awt.Color(51, 51, 51));
        chkShowPassword.setText("Tampilkan Password");
        chkShowPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkShowPasswordActionPerformed(evt);
            }
        });
        jPanelCard.add(chkShowPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 258, 160, 22));

        lblRole.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRole.setForeground(new java.awt.Color(51, 51, 51));
        lblRole.setText("Hak Akses (Role)");
        jPanelCard.add(lblRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 288, 360, 20));

        cmbRole.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Teller", "Manajer" }));
        jPanelCard.add(cmbRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 312, 360, 38));

        btnLogin.setBackground(new java.awt.Color(51, 0, 255));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("LOGIN SEKARANG");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanelCard.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 375, 220, 42));

        btnReset.setBackground(new java.awt.Color(255, 255, 255));
        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReset.setForeground(new java.awt.Color(51, 51, 51));
        btnReset.setText("RESET");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanelCard.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 375, 130, 42));

        jPanel1.add(jPanelCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(463, 134, 440, 500));

        lblFooter.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblFooter.setForeground(new java.awt.Color(119, 119, 119));
        lblFooter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFooter.setText("© 2026 BrinkMikro Financial System. All rights reserved.");
        jPanel1.add(lblFooter, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 660, 1366, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 768));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void chkShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkShowPasswordActionPerformed
        if (chkShowPassword.isSelected()) {
            txtPassword.setEchoChar((char) 0);
        } else {
            txtPassword.setEchoChar('•');
        }
    }//GEN-LAST:event_chkShowPasswordActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        String selectedRole = (String) cmbRole.getSelectedItem();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username dan Password tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean userExistsInDb = false;
        boolean dbConnected = false;

        try (Connection conn = DatabaseConnection.getConnection()) {
            dbConnected = true;
            String sql = "SELECT * FROM pengguna WHERE username = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        userExistsInDb = true;
                    }
                }
            }
        } catch (SQLException e) {
            dbConnected = false;
            System.err.println("Database connection error: " + e.getMessage());
        } catch (Exception e) {
            dbConnected = false;
            System.err.println("General login error: " + e.getMessage());
        }

        if (!dbConnected) {
            JOptionPane.showMessageDialog(this, "Koneksi ke database terputus", "Koneksi Terputus", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verifikasi Password berdasarkan Role (Username harus terdaftar di DB)
        boolean authenticated = false;
        if (userExistsInDb) {
            if (selectedRole.equalsIgnoreCase("Admin") && password.equals("admin123")) {
                authenticated = true;
            } else if (selectedRole.equalsIgnoreCase("Teller") && password.equals("teller123")) {
                authenticated = true;
            } else if (selectedRole.equalsIgnoreCase("Manajer") && password.equals("manajer123")) {
                authenticated = true;
            }
        }

        // Proses Login
        if (authenticated) {
            JOptionPane.showMessageDialog(this, "Login Berhasil sebagai " + selectedRole + "!", "Sukses Login", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();

            if (selectedRole.equalsIgnoreCase("Admin")) {
                new AdminDataPengguna().setVisible(true);
            } else if (selectedRole.equalsIgnoreCase("Teller")) {
                new TellerDataNasabah().setVisible(true);
            } else if (selectedRole.equalsIgnoreCase("Manajer")) {
                new ManajerDashboardKas().setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Username, Password, atau Hak Akses (Role) tidak sesuai!", "Gagal Login", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtUsername.setText("");
        txtPassword.setText("");
        chkShowPassword.setSelected(false);
        txtPassword.setEchoChar('•');
        cmbRole.setSelectedIndex(0);
        txtUsername.requestFocus();
    }//GEN-LAST:event_btnResetActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnReset;
    private javax.swing.JCheckBox chkShowPassword;
    private javax.swing.JComboBox<String> cmbRole;
    private javax.swing.JLabel lblFooter;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelCard;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
