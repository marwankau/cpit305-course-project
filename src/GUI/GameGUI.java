package GUI;

/* 
 * Game GUI 
 * Create Game Class Object
 * Display the Game 
 * Game class update the GUI
 * If Winner Method returns true it close the Game
 * If Counter == 9 it close the Game
 */
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Game.Game;

public class GameGUI extends javax.swing.JFrame {

    public Game game = new Game();

    public GameGUI() {
        initComponents();
    }

    private void initComponents() {

        _3x1Button = new javax.swing.JButton();
        _1x3Button = new javax.swing.JButton();
        _1x2Button = new javax.swing.JButton();
        _1x1Button = new javax.swing.JButton();
        _2x1Button = new javax.swing.JButton();
        _2x2Button = new javax.swing.JButton();
        _2x3Button = new javax.swing.JButton();
        _3x3Button = new javax.swing.JButton();
        _3x2Button = new javax.swing.JButton();
        _boardLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(605, 830));
        setResizable(false);
        getContentPane().setLayout(null);

        _3x1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _3x1ButtonActionPerformed(evt);
            }
        });
        _3x1Button.setIcon(new ImageIcon("Game picture/Box3x1.png"));
        getContentPane().add(_3x1Button);
        _3x1Button.setBounds(40, 650, 140, 100);

        _1x3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _1x3ButtonActionPerformed(evt);
            }
        });
        _1x3Button.setIcon(new ImageIcon("Game picture/Box1x3.png"));
        getContentPane().add(_1x3Button);
        _1x3Button.setBounds(440, 290, 140, 100);

        _1x2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _1x2ButtonActionPerformed(evt);
            }
        });
        _1x2Button.setIcon(new ImageIcon("Game picture/Box1x2.png"));
        getContentPane().add(_1x2Button);
        _1x2Button.setBounds(240, 290, 140, 100);

        _1x1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _1x1ButtonActionPerformed(evt);
            }
        });
        _1x1Button.setIcon(new ImageIcon("Game picture/Box1x1.png"));
        getContentPane().add(_1x1Button);
        _1x1Button.setBounds(40, 290, 140, 100);

        _2x1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _2x1ButtonActionPerformed(evt);
            }
        });
        _2x1Button.setIcon(new ImageIcon("Game picture/Box2x1.png"));
        getContentPane().add(_2x1Button);
        _2x1Button.setBounds(40, 460, 140, 100);

        _2x2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _2x2ButtonActionPerformed(evt);
            }
        });
        _2x2Button.setIcon(new ImageIcon("Game picture/Box2x2.png"));
        getContentPane().add(_2x2Button);
        _2x2Button.setBounds(240, 460, 140, 100);

        _2x3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _2x3ButtonActionPerformed(evt);
            }
        });
        _2x3Button.setIcon(new ImageIcon("Game picture/Box2x3.png"));
        getContentPane().add(_2x3Button);
        _2x3Button.setBounds(440, 460, 140, 100);

        _3x3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _3x3ButtonActionPerformed(evt);
            }
        });
        _3x3Button.setIcon(new ImageIcon("Game picture/Box3x3.png"));
        getContentPane().add(_3x3Button);
        _3x3Button.setBounds(440, 650, 140, 100);

        _3x2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _3x2ButtonActionPerformed(evt);
            }
        });
        _3x2Button.setIcon(new ImageIcon("Game picture/Box3x2.png"));
        getContentPane().add(_3x2Button);
        _3x2Button.setBounds(240, 650, 140, 100);

        getContentPane().add(jLabel1);
        jLabel1.setBounds(80, 280, 120, 100);
        getContentPane().add(jLabel2);
        jLabel2.setBounds(250, 270, 120, 120);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(450, 270, 120, 120);
        getContentPane().add(jLabel4);
        jLabel4.setBounds(60, 460, 120, 120);
        getContentPane().add(jLabel5);
        jLabel5.setBounds(250, 450, 120, 120);
        getContentPane().add(jLabel6);
        jLabel6.setBounds(450, 460, 120, 120);
        getContentPane().add(jLabel7);
        jLabel7.setBounds(60, 640, 120, 120);
        getContentPane().add(jLabel8);
        jLabel8.setBounds(250, 630, 120, 120);
        getContentPane().add(jLabel9);
        jLabel9.setBounds(440, 640, 120, 120);

        _boardLabel.setIcon(new javax.swing.ImageIcon(("Game picture/Game board.jpg")));
        getContentPane().add(_boardLabel);
        _boardLabel.setBounds(0, 0, 600, 800);

        pack();
        setLocationRelativeTo(null);
        setAllButtonAndLabelToGame();
    }

    // This to give the Game Class to Update the GUI
    private void setAllButtonAndLabelToGame() {
        game.set_1x1Button(_1x1Button);
        game.set_1x2Button(_1x2Button);
        game.set_1x3Button(_1x3Button);
        game.set_2x1Button(_2x1Button);
        game.set_2x2Button(_2x2Button);
        game.set_2x3Button(_2x3Button);
        game.set_3x1Button(_3x1Button);
        game.set_3x2Button(_3x2Button);
        game.set_3x3Button(_3x3Button);
        game.setjLabel1(jLabel1);
        game.setjLabel2(jLabel2);
        game.setjLabel3(jLabel3);
        game.setjLabel4(jLabel4);
        game.setjLabel5(jLabel5);
        game.setjLabel6(jLabel6);
        game.setjLabel7(jLabel7);
        game.setjLabel8(jLabel8);
        game.setjLabel9(jLabel9);
    }

    private void _1x1ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        _1x1Button.setVisible(false);
        ;

        jLabel1 = game.setsignInGUI(jLabel1, 0, 0, false);

        if (game.winner() == true) {
            JOptionPane.showMessageDialog(this, "Thank you for Playing!");

            System.exit(0);
        }
        if (game.counter == 9) {
            System.out.println("It's a Tie!");
            JOptionPane.showMessageDialog(this, "It's a Tie!\nThank you for Playing!");

            System.exit(0);
        }
    }

    private void _1x2ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        _1x2Button.setVisible(false);
        ;

        jLabel2 = game.setsignInGUI(jLabel2, 0, 1, false);

        if (game.winner() == true) {
            JOptionPane.showMessageDialog(this, "Thank you for Playing!");

            System.exit(0);
        }
        if (game.counter == 9) {
            System.out.println("It's a Tie!");
            JOptionPane.showMessageDialog(this, "It's a Tie!\nThank you for Playing!");

            System.exit(0);
        }
    }

    private void _1x3ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        _1x3Button.setVisible(false);
        ;

        jLabel3 = game.setsignInGUI(jLabel3, 0, 2, false);

        if (game.winner() == true) {
            JOptionPane.showMessageDialog(this, "Thank you for Playing!");

            System.exit(0);
        }
        if (game.counter == 9) {
            System.out.println("It's a Tie!");
            JOptionPane.showMessageDialog(this, "It's a Tie!\nThank you for Playing!");

            System.exit(0);
        }
    }

    private void _2x1ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        _2x1Button.setVisible(false);
        ;

        jLabel4 = game.setsignInGUI(jLabel4, 1, 0, false);

        if (game.winner() == true) {
            JOptionPane.showMessageDialog(this, "Thank you for Playing!");

            System.exit(0);
        }
        if (game.counter == 9) {
            System.out.println("It's a Tie!");
            JOptionPane.showMessageDialog(this, "It's a Tie!\nThank you for Playing!");

            System.exit(0);
        }
    }

    private void _2x2ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        _2x2Button.setVisible(false);
        ;

        jLabel5 = game.setsignInGUI(jLabel5, 1, 1, false);

        if (game.winner() == true) {
            JOptionPane.showMessageDialog(this, "Thank you for Playing!");

            System.exit(0);
        }
        if (game.counter == 9) {
            System.out.println("It's a Tie!");
            JOptionPane.showMessageDialog(this, "It's a Tie!\nThank you for Playing!");

            System.exit(0);
        }
    }

    private void _2x3ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        _2x3Button.setVisible(false);
        ;

        jLabel6 = game.setsignInGUI(jLabel6, 1, 2, false);

        if (game.winner() == true) {
            JOptionPane.showMessageDialog(this, "Thank you for Playing!");

            System.exit(0);
        }
        if (game.counter == 9) {
            System.out.println("It's a Tie!");
            JOptionPane.showMessageDialog(this, "It's a Tie!\nThank you for Playing!");

            System.exit(0);
        }
    }

    private void _3x1ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        _3x1Button.setVisible(false);
        ;

        jLabel7 = game.setsignInGUI(jLabel7, 2, 0, false);

        if (game.winner() == true) {
            char WinnerSign = game.GetWhoIsWin();
            String ExitMassage = String.format("%c Wins! \nThank you for Playing", WinnerSign);
            JOptionPane.showMessageDialog(this, ExitMassage);
            System.exit(0);
        }
        if (game.counter == 9) {
            System.out.println("It's a Tie!");
            String ExitMassage = "It's a Tie! \nThank you for Playing!";
            JOptionPane.showMessageDialog(this, ExitMassage);
            System.exit(0);
        }
    }

    private void _3x2ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        _3x2Button.setVisible(false);
        ;

        jLabel8 = game.setsignInGUI(jLabel8, 2, 1, false);

        if (game.winner() == true) {
            JOptionPane.showMessageDialog(this, "Thank you for Playing!");
            System.exit(0);
        }
        if (game.counter == 9) {
            System.out.println("It's a Tie!");
            JOptionPane.showMessageDialog(this, "It's a Tie!\nThank you for Playing!");
            System.exit(0);
        }
    }

    private void _3x3ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        _3x3Button.setVisible(false);
        ;

        jLabel9 = game.setsignInGUI(jLabel9, 2, 2, false);

        if (game.winner() == true) {
            JOptionPane.showMessageDialog(this, "Thank you for Playing!");
            System.exit(0);
        }
        if (game.counter == 9) {
            System.out.println("It's a Tie!");
            JOptionPane.showMessageDialog(this, "It's a Tie!\nThank you for Playing!");
            System.exit(0);
        }
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
            java.util.logging.Logger.getLogger(GameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton _1x1Button;
    private javax.swing.JButton _1x2Button;
    private javax.swing.JButton _1x3Button;
    private javax.swing.JButton _2x1Button;
    private javax.swing.JButton _2x2Button;
    private javax.swing.JButton _2x3Button;
    private javax.swing.JButton _3x1Button;
    private javax.swing.JButton _3x2Button;
    private javax.swing.JButton _3x3Button;
    private javax.swing.JLabel _boardLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
