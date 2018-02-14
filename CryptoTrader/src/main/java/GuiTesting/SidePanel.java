/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiTesting;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author rubenr
 */
public class SidePanel extends javax.swing.JPanel {

    /**
     * Creates new form SidePannel
     */
    private static JButton homeButton;
    private static JButton settingsButton;
    private static JButton accountsButton;
    
    public SidePanel() {
        initComponents();
        homeButton = jButton_Home;
        settingsButton = jButton_Settings;
        accountsButton = jButton_AcctSettings;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton_Home = new javax.swing.JButton();
        jButton_Settings = new javax.swing.JButton();
        jButton_AcctSettings = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 153, 255));

        jButton_Home.setBackground(new java.awt.Color(0, 153, 255));
        jButton_Home.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 18)); // NOI18N
        jButton_Home.setText("Home");
        jButton_Home.setBorder(null);
        jButton_Home.setBorderPainted(false);
        jButton_Home.setFocusPainted(false);
        jButton_Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_HomeActionPerformed(evt);
            }
        });

        jButton_Settings.setBackground(new java.awt.Color(0, 153, 255));
        jButton_Settings.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 18)); // NOI18N
        jButton_Settings.setText("Settings");
        jButton_Settings.setBorder(null);
        jButton_Settings.setBorderPainted(false);
        jButton_Settings.setFocusPainted(false);
        jButton_Settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SettingsActionPerformed(evt);
            }
        });

        jButton_AcctSettings.setBackground(new java.awt.Color(0, 153, 255));
        jButton_AcctSettings.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 18)); // NOI18N
        jButton_AcctSettings.setText("Account");
        jButton_AcctSettings.setBorder(null);
        jButton_AcctSettings.setBorderPainted(false);
        jButton_AcctSettings.setFocusPainted(false);
        jButton_AcctSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AcctSettingsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton_Home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton_Settings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton_AcctSettings, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton_Home, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Settings, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_AcctSettings, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public static void resetColors() {
        homeButton.setBackground(Color.red);
        
    }
    
    private void jButton_HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_HomeActionPerformed
        // TODO add your handling code here:
        System.out.println("Home");
        boolean[] constant = {true, false, false};
        MainWindow.setContent(constant);
    }//GEN-LAST:event_jButton_HomeActionPerformed

    private void jButton_SettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SettingsActionPerformed
        // TODO add your handling code here:
        System.out.println("Settings");
        boolean[] constant = {false, true, false};
        MainWindow.setContent(constant);
    }//GEN-LAST:event_jButton_SettingsActionPerformed

    private void jButton_AcctSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AcctSettingsActionPerformed
        // TODO add your handling code here:
        System.out.println("Accounts");
        boolean[] constant = {false, false, true};
        MainWindow.setContent(constant);
    }//GEN-LAST:event_jButton_AcctSettingsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_AcctSettings;
    private javax.swing.JButton jButton_Home;
    private javax.swing.JButton jButton_Settings;
    // End of variables declaration//GEN-END:variables
}
