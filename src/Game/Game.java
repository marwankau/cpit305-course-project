package Game;


import javax.swing.*;

import GUI.GameGUI;

import java.io.*;


public class Game {

    public static int counter = 0;
    private static boolean sign = true; // TRUE -> X || FALSE -> O
    private static char[][] gameboard = new char[3][3];
    private String lobbyName;
    private ImageIcon XIcon = new ImageIcon("Game picture/X.png");
    private ImageIcon OIcon = new ImageIcon("Game picture/O.png");

    // public Game(String lobbyName) {
    // this.lobbyName = lobbyName;
    // }

    public Game() {
    }

    public static void printBoard(char[][] gameboard) {
        File file = new File("Game Record.txt");
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
            String rec = "Game Record\n";
            byte[] b = rec.getBytes();
            fos.write(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = 0;
        for (char[] row : gameboard) {
            for (char c : row) {
                try {

                    char s = '|';

                    fos.write(c);

                    i++;

                    if (i == 3) {
                        i = 0;
                        break;
                    }
                    fos.write(s);
                    fos.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                fos.write('\n');
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static void updateBoard(boolean sign, int x, int y) {

        char character;

        if (sign == true) {
            character = 'X';
        } else {
            character = 'O';
        }
        while (true) {
            gameboard[x][y] = character;
            printBoard(gameboard);

            break;
        }

    }

    public JLabel setsignInGUI(GameGUI gui, JLabel label, int x, int y) {

        updateBoard(sign, x, y);
        if (sign == true) {
            label.setIcon(XIcon);
            gameboard[x][y] = 'X';

            sign = !sign;
            counter++;
        } else {
            label.setIcon(OIcon);
            gameboard[x][y] = 'O';

            sign = !sign;
            counter++;
        }
        return label;
    }

    public boolean winner() throws IOException {
        File file = new File("Game Record.txt");
        FileOutputStream fos= null;
        try {
             fos = new FileOutputStream(file, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String winX = "\nX Wins\n";
        String winO = "\nO Wins\n";
        // Horizontal Win
        if (gameboard[0][0] == 'X' && gameboard[0][1] == 'X' && gameboard[0][2] == 'X') {
            byte[] By = winX.getBytes();
            fos.write(By);
            JOptionPane.showMessageDialog(null,"X Wins\nThank you for Playing!");
            return true;
        } else if (gameboard[0][0] == 'O' && gameboard[0][1] == 'O' && gameboard[0][2] == 'O') {
            JOptionPane.showMessageDialog(null,"O Wins\nThank you for Playing!");
            byte[] By = winO.getBytes();
            fos.write(By);
            return true;
        } else if (gameboard[1][0] == 'X' && gameboard[1][1] == 'X' && gameboard[1][2] == 'X') {
            byte[] By = winX.getBytes();
            fos.write(By);
            JOptionPane.showMessageDialog(null,"X Wins\nThank you for Playing!");
            return true;
        } else if (gameboard[1][0] == 'O' && gameboard[1][1] == 'O' && gameboard[1][2] == 'O') {
              JOptionPane.showMessageDialog(null,"O Wins\nThank you for Playing!");
            byte[] By = winO.getBytes();
            fos.write(By);
            return true;
        } else if (gameboard[2][0] == 'X' && gameboard[2][1] == 'X' && gameboard[2][2] == 'X') {
            byte[] By = winX.getBytes();
            fos.write(By);
            JOptionPane.showMessageDialog(null,"X Wins\nThank you for Playing!");
            return true;
        } else if (gameboard[2][0] == 'O' && gameboard[2][1] == 'O' && gameboard[2][2] == 'O') {
              JOptionPane.showMessageDialog(null,"O Wins\nThank you for Playing!");
            byte[] By = winO.getBytes();
            fos.write(By);
            return true;
        } // Vertical Wins
        else if (gameboard[0][0] == 'X' && gameboard[1][0] == 'X' && gameboard[2][0] == 'X') {
            byte[] By = winX.getBytes();
            fos.write(By);
            JOptionPane.showMessageDialog(null,"X Wins\nThank you for Playing!");
            return true;
        } else if (gameboard[0][0] == 'O' && gameboard[1][0] == 'O' && gameboard[2][0] == 'O') {
              JOptionPane.showMessageDialog(null,"O Wins\nThank you for Playing!");
            byte[] By = winO.getBytes();
            fos.write(By);
            return true;
        } else if (gameboard[0][1] == 'X' && gameboard[1][1] == 'X' && gameboard[2][1] == 'X') {
            byte[] By = winX.getBytes();
            fos.write(By);
            JOptionPane.showMessageDialog(null,"X Wins\nThank you for Playing!");
            return true;
        } else if (gameboard[0][1] == 'O' && gameboard[1][1] == 'O' && gameboard[2][1] == 'O') {
              JOptionPane.showMessageDialog(null,"O Wins\nThank you for Playing!");
            byte[] By = winO.getBytes();
            fos.write(By);
            return true;
        } else if (gameboard[0][2] == 'X' && gameboard[1][2] == 'X' && gameboard[2][2] == 'X') {
            byte[] By = winX.getBytes();
            fos.write(By);
            JOptionPane.showMessageDialog(null,"X Wins\nThank you for Playing!");
            return true;
        } else if (gameboard[0][2] == 'O' && gameboard[1][2] == 'O' && gameboard[2][2] == 'O') {
              JOptionPane.showMessageDialog(null,"O Wins\nThank you for Playing!");
            byte[] By = winO.getBytes();
            fos.write(By);
            return true;
        } // Diagonal Wins
        else if (gameboard[0][0] == 'X' && gameboard[1][1] == 'X' && gameboard[2][2] == 'X') {
            byte[] By = winX.getBytes();
            fos.write(By);
            JOptionPane.showMessageDialog(null,"X Wins\nThank you for Playing!");
            return true;
        } else if (gameboard[0][0] == 'O' && gameboard[1][1] == 'O' && gameboard[2][2] == 'O') {
              JOptionPane.showMessageDialog(null,"O Wins\nThank you for Playing!");
            byte[] By = winO.getBytes();
            fos.write(By);
            return true;
        } else if (gameboard[0][2] == 'X' && gameboard[1][1] == 'X' && gameboard[2][0] == 'X') {
            byte[] By = winX.getBytes();
            fos.write(By);
            JOptionPane.showMessageDialog(null,"X Wins\nThank you for Playing!");
            return true;
        } else if (gameboard[0][2] == 'O' && gameboard[1][1] == 'O' && gameboard[2][0] == 'O') {
              JOptionPane.showMessageDialog(null,"O Wins\nThank you for Playing!");
            byte[] By = winO.getBytes();
            fos.write(By);
            return true;
        }
        return false;
    }

}
