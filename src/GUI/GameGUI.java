package GUI;

import Game.Game;

public class GameGUI extends javax.swing.JFrame {

    /**
     * Creates new form GameGUI
     */
    public GameGUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        _3x1Button = new java.awt.Button();
        _1x3Button = new java.awt.Button();
        _1x2Button = new java.awt.Button();
        _1x1Button = new java.awt.Button();
        _2x1Button = new java.awt.Button();
        _2x2Button = new java.awt.Button();
        _2x3Button = new java.awt.Button();
        _3x3Button = new java.awt.Button();
        _3x2Button = new java.awt.Button();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(605, 830));
        setResizable(false);
        getContentPane().setLayout(null);

        _3x1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _3x1ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(_3x1Button);
        _3x1Button.setBounds(40, 650, 140, 100);

        _1x3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _1x3ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(_1x3Button);
        _1x3Button.setBounds(440, 290, 140, 100);

        _1x2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _1x2ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(_1x2Button);
        _1x2Button.setBounds(240, 290, 140, 100);

        _1x1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _1x1ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(_1x1Button);
        _1x1Button.setBounds(40, 290, 140, 100);

        _2x1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _2x1ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(_2x1Button);
        _2x1Button.setBounds(40, 460, 140, 100);

        _2x2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _2x2ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(_2x2Button);
        _2x2Button.setBounds(240, 460, 140, 100);

        _2x3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _2x3ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(_2x3Button);
        _2x3Button.setBounds(440, 460, 140, 100);

        _3x3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _3x3ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(_3x3Button);
        _3x3Button.setBounds(440, 650, 140, 100);

        _3x2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _3x2ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(_3x2Button);
        _3x2Button.setBounds(240, 650, 140, 100);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Game board.jpg"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 600, 800);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void _1x1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__1x1ButtonActionPerformed
        _1x1Button.hide();
    }//GEN-LAST:event__1x1ButtonActionPerformed

    private void _1x2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__1x2ButtonActionPerformed
        _1x2Button.hide();
    }//GEN-LAST:event__1x2ButtonActionPerformed

    private void _1x3ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__1x3ButtonActionPerformed
        _1x3Button.hide();
    }//GEN-LAST:event__1x3ButtonActionPerformed

    private void _2x1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__2x1ButtonActionPerformed
        _2x1Button.hide();
    }//GEN-LAST:event__2x1ButtonActionPerformed

    private void _2x2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__2x2ButtonActionPerformed
        _2x2Button.hide();
    }//GEN-LAST:event__2x2ButtonActionPerformed

    private void _2x3ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__2x3ButtonActionPerformed
        _2x3Button.hide();
    }//GEN-LAST:event__2x3ButtonActionPerformed

    private void _3x1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__3x1ButtonActionPerformed
        _3x1Button.hide();
    }//GEN-LAST:event__3x1ButtonActionPerformed

    private void _3x2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__3x2ButtonActionPerformed
        _3x2Button.hide();
    }//GEN-LAST:event__3x2ButtonActionPerformed

    private void _3x3ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__3x3ButtonActionPerformed
        _3x3Button.hide();
    }//GEN-LAST:event__3x3ButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button _1x1Button;
    private java.awt.Button _1x2Button;
    private java.awt.Button _1x3Button;
    private java.awt.Button _2x1Button;
    private java.awt.Button _2x2Button;
    private java.awt.Button _2x3Button;
    private java.awt.Button _3x1Button;
    private java.awt.Button _3x2Button;
    private java.awt.Button _3x3Button;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
