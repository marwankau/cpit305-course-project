package Game;

// import javax.swing.ImageIcon;
// import javax.swing.JLabel;
// import javax.swing.JOptionPane;
import javax.swing.*;

import GUI.GameGUI;

public class Game {

    private String lobbyName;
    private static boolean sign = true; // TRUE -> X || FALSE -> O
    public static int counter = 0;
    private static char[][] gameboard = new char[3][3];
    private ImageIcon XIcon = new ImageIcon("Game picture/X.png");
    private ImageIcon OIcon = new ImageIcon("Game picture/O.png");
    // public Game(String lobbyName) {
    // this.lobbyName = lobbyName;
    // }

    public Game() {
    }

    public JLabel setsignInGUI(GameGUI gui, JLabel label, int x, int y) {

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

    public boolean winner() {
        // Horizontal Win
        if (gameboard[0][0] == 'X' && gameboard[0][1] == 'X' && gameboard[0][2] == 'X') {
            System.out.println("X Wins");
            return true;
        } else if (gameboard[0][0] == 'O' && gameboard[0][1] == 'O' && gameboard[0][2] == 'O') {
            System.out.println("O Wins");
            return true;
        } else if (gameboard[1][0] == 'X' && gameboard[1][1] == 'X' && gameboard[1][2] == 'X') {
            System.out.println("X Wins");
            return true;
        } else if (gameboard[1][0] == 'O' && gameboard[1][1] == 'O' && gameboard[1][2] == 'O') {
            System.out.println("O Wins");
            return true;
        } else if (gameboard[2][0] == 'X' && gameboard[2][1] == 'X' && gameboard[2][2] == 'X') {
            System.out.println("X Wins");
            return true;
        } else if (gameboard[2][0] == 'O' && gameboard[2][1] == 'O' && gameboard[2][2] == 'O') {
            System.out.println("O Wins");
            return true;
        } // Vertical Wins
        else if (gameboard[0][0] == 'X' && gameboard[1][0] == 'X' && gameboard[2][0] == 'X') {
            System.out.println("X Wins");
            return true;
        } else if (gameboard[0][0] == 'O' && gameboard[1][0] == 'O' && gameboard[2][0] == 'O') {
            System.out.println("O Wins");
            return true;
        } else if (gameboard[0][1] == 'X' && gameboard[1][1] == 'X' && gameboard[2][1] == 'X') {
            System.out.println("X Wins");
            return true;
        } else if (gameboard[0][1] == 'O' && gameboard[1][1] == 'O' && gameboard[2][1] == 'O') {
            System.out.println("O Wins");
            return true;
        } else if (gameboard[0][2] == 'X' && gameboard[1][2] == 'X' && gameboard[2][2] == 'X') {
            System.out.println("X Wins");
            return true;
        } else if (gameboard[0][2] == 'O' && gameboard[1][2] == 'O' && gameboard[2][2] == 'O') {
            System.out.println("O Wins");
            return true;
        } // Diagonal Wins
        else if (gameboard[0][0] == 'X' && gameboard[1][1] == 'X' && gameboard[2][2] == 'X') {
            System.out.println("X Wins");
            return true;
        } else if (gameboard[0][0] == 'O' && gameboard[1][1] == 'O' && gameboard[2][2] == 'O') {
            System.out.println("O Wins");
            return true;
        } else if (gameboard[0][2] == 'X' && gameboard[1][1] == 'X' && gameboard[2][0] == 'X') {
            System.out.println("X Wins");
            return true;
        } else if (gameboard[0][2] == 'O' && gameboard[1][1] == 'O' && gameboard[2][0] == 'O') {
            System.out.println("O Wins");
            return true;
        }
        return false;
    }

}
