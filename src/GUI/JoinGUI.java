package GUI;

/* 
 * JoinGUI Class is responsiable to Connect the User to network
 */

import javax.swing.JOptionPane;

import Connection.Network.ServerClinet;

public class JoinGUI extends javax.swing.JFrame {

    public JoinGUI() {
        initComponents();
    }

    private void initComponents() {

        IPLabel = new javax.swing.JLabel();
        PortLabel = new javax.swing.JLabel();
        PortTextField = new javax.swing.JTextField();
        IPTextField = new javax.swing.JTextField();
        JoinButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        IPLabel.setText("IP (Default: localhost)");

        PortLabel.setText("Port");

        JoinButton.setText("Join");
        JoinButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JoinButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(PortLabel)
                                        .addComponent(IPLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23,
                                        Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(PortTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 97,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(IPTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 97,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(55, 55, 55))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(JoinButton)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(IPLabel)
                                        .addComponent(IPTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(PortLabel)
                                        .addComponent(PortTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addComponent(JoinButton)
                                .addContainerGap(79, Short.MAX_VALUE)));

        pack();
    }

    private void JoinButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (IPTextField.getText().length() == 0 || PortTextField.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "All field must be filled.");
            return;
        }
        int port = Integer.parseInt(PortTextField.getText());
        if (port < 1 || port > 65535) {
            JOptionPane.showMessageDialog(this, "The port must be between 1 and 65535");
            return;
        }
        ServerClinet serverclient = new ServerClinet(IPTextField.getText(), port);
        serverclient.init();
        this.dispose();
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JoinGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JoinGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JoinGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JoinGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JoinGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IPLabel;
    private javax.swing.JTextField IPTextField;
    private javax.swing.JButton JoinButton;
    private javax.swing.JLabel PortLabel;
    private javax.swing.JTextField PortTextField;
    // End of variables declaration//GEN-END:variables
}
