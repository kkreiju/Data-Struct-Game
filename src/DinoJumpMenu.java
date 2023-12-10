/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class DinoJumpMenu extends javax.swing.JFrame {

    public DinosaurGame dinosaurGame;
    private String name;
    private JLabel nameLabel;
    ImageIcon logo = new ImageIcon("src\\textures\\icon.jpg");

    public DinoJumpMenu() {
        nameLabel = new JLabel("Dinosaur Name: ");
        nameLabel.setFont(new java.awt.Font("Sitka Small", 0, 12));
        nameLabel.setAlignmentY(0.7F);
        nameLabel.setPreferredSize(new java.awt.Dimension(136, 13));
        dinosaurGame = new DinosaurGame();
        initComponents();
        setTitle("Dino Jump!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(logo.getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 69, 0));

        jPanel6.setBackground(new java.awt.Color(0, 144, 255));
        jPanel6.setForeground(new java.awt.Color(242, 242, 242));
        jPanel6.setPreferredSize(new java.awt.Dimension(850, 450));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ICON3dinasour.jpg"))); // NOI18N
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clou.png"))); // NOI18N
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 80, 50));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clou.png"))); // NOI18N
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 80, 50));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clou.png"))); // NOI18N
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 80, 50));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clou.png"))); // NOI18N
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 170, 80, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clou.png"))); // NOI18N
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 380, 80, 50));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clou.png"))); // NOI18N
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 80, 50));

        jPanel4.setBackground(new java.awt.Color(102, 51, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(204, 102, 0)));

        jButton1.setBackground(new java.awt.Color(51, 204, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 51));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 204, 51));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Settings");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 153, 51));
        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 0, 0));
        jButton4.setText("Credits");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 117, -1, -1));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Dinosaur Name: T-rex");
        jLabel1.setAlignmentY(0.7F);
        jLabel1.setPreferredSize(new java.awt.Dimension(136, 13));
        jPanel6.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 410, 46));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String[] options = {"Change Name", "How to Play"};
        int choice = JOptionPane.showOptionDialog(
                this,
                "Choose an option:",
                "Settings",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        switch (choice) {
            case 0: // Change Name
                String newName = JOptionPane.showInputDialog(this, "Enter new name (max 10 characters):");
                if (newName != null) {
                    if (newName.length() <= 10) {
                        dinosaurGame.setDinosaurName(newName);
                        updateNameLabel();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error: Name must be 10 characters or fewer.");
                    }
                }
                break;
            case 1: // How to Play
                showHowToPlay();
                break;
            default:
                break;
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void showHowToPlay() {
        String instructions = "Instructions:\n\n"
                + "1. Press the 'Start' button to begin the game.\n"
                + "2. Use the space bar to make the dinosaur jump.\n"
                + "3. Avoid obstacles and try to achieve a high score.\n\n"
                + "Have fun playing!";
        JOptionPane.showMessageDialog(this, instructions, "How to Play", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateNameLabel() {
        if (nameLabel != null) {
            nameLabel.setText("Dinosaur Name: " + dinosaurGame.getDinosaurName());
            jLabel1.setText("Dinosaur Name: " + dinosaurGame.getDinosaurName());
            setName(dinosaurGame.getDinosaurName());
        }
    }

    public String getName(){
        return name;
    }
    
    public void setName(String n){
        this.name = n;
    }
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
        new DinoFrame();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String creditsMessage = "CREATORS: Arjay Niño Sauguisa, Sherwill Mae Alivio, Anthony Peralta, Shine Florence Padillo";
        JOptionPane.showMessageDialog(this, creditsMessage, "Credits", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DinoJumpMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DinoJumpMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DinoJumpMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DinoJumpMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DinoJumpMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DinoJumpMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DinoJumpMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DinoJumpMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DinoJumpMenu().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}

class DinosaurGame {

    private String dinosaurName;

    public DinosaurGame() {
        dinosaurName = "T-Rex";
    }

    public String getDinosaurName() {
        return dinosaurName;
    }

    public void setDinosaurName(String dinosaurName) {
        this.dinosaurName = dinosaurName;
    }
}
