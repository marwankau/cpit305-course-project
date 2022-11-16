package Game;

/* 
 * Game Class is Responsiable for Game process:
 * Create Thread
 * Update Board by setIcons and send the update using DataStream
 * Print the game in file
 * Communication between clients
 */

import javax.swing.*;

import Log.Logs;

import java.io.*;

public class Game implements Runnable {

    public static int counter = 0;
    private static boolean sign = true; // TRUE -> X || FALSE -> O
    private char WinnerSign;
    private static char[][] gameboard = new char[3][3];
    private ImageIcon XIcon = new ImageIcon("Game picture/X.png");
    private ImageIcon OIcon = new ImageIcon("Game picture/O.png");

    private DataInputStream dis;
    private DataOutputStream dos;

    private Thread thread;

    private javax.swing.JButton _1x1Button;
    private javax.swing.JButton _1x2Button;
    private javax.swing.JButton _1x3Button;
    private javax.swing.JButton _2x1Button;
    private javax.swing.JButton _2x2Button;
    private javax.swing.JButton _2x3Button;
    private javax.swing.JButton _3x1Button;
    private javax.swing.JButton _3x2Button;
    private javax.swing.JButton _3x3Button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;

    private static Logs log = new Logs();

    private String msg;

    public Game() {
        thread = new Thread(this, "TicTacToe");
        thread.start();
    }

    //Run Method is responsiable to create BufferReader and check if other Client did play or not and update the board
    @Override
    public void run() {
        log.writeInFile("loggame", "Initializing Game Object.");
        BufferedReader br;
        int x, y;

        while(true){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                log.writeInFile("logerror", e.toString());
                e.printStackTrace();
            }
            System.out.println("BufferReader is null.");
            if(getDis() != null){
                msg = "BufferReader Has been defined.";
                log.writeInFile("loggame", msg);
                System.out.println(msg);
                br = new BufferedReader(new InputStreamReader(getDis()));
                break;
            }
        }

        while(true){
            try {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.writeInFile("logerror", e.toString());
                    e.printStackTrace();
                }
                while((x = br.read()) != -1){
                    y = br.read();
                    msg = String.format("From Client: x: %d   y: %d\n", x, y);
                    log.writeInFile("loggame", msg);
                    System.out.println(msg);
                    hideButton(x, y);
                }
            } catch (IOException e) {
                log.writeInFile("logerror", e.toString());
                e.printStackTrace();
            }
        }
    }

    //Responsiable for Update from other client
    private void hideButton(int x, int y) {
        if(x == 0 && y == 0){
            msg = "Hide Button 1x1";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            get_1x1Button().setVisible(false);
            msg = "Draw Sign in Label 1";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            setsignInGUI(getjLabel1(), x, y, true);
        } else if(x == 0 && y == 1){
            msg = "Hide Button 1x2";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            get_1x2Button().setVisible(false);
            msg = "Draw Sign in Label 2";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            setsignInGUI(getjLabel2(), x, y, true);
        } else if(x == 0 && y == 2){
            msg = "Hide Button 1x3";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            get_1x3Button().setVisible(false);
            msg = "Draw Sign in Label 3";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            setsignInGUI(getjLabel3(), x, y, true);
        } else if(x == 1 && y == 0){
            msg = "Hide Button 2x1";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            get_2x1Button().setVisible(false);
            msg = "Draw Sign in Label 4";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            setsignInGUI(getjLabel4(), x, y, true);
        } else if(x == 1 && y == 1){
            msg = "Hide Button 2x2";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            get_2x2Button().setVisible(false);
            msg = "Draw Sign in Label 5";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            setsignInGUI(getjLabel5(), x, y, true);
        } else if(x == 1 && y == 2){
            msg = "Hide Button 2x3";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            get_2x3Button().setVisible(false);
            msg = "Draw Sign in Label 6";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            setsignInGUI(getjLabel6(), x, y, true);
        } else if(x == 2 && y == 0){
            msg = "Hide Button 3x1";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            get_3x1Button().setVisible(false);
            msg = "Draw Sign in Label 7";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            setsignInGUI(getjLabel7(), x, y, true);
        } else if(x == 2 && y == 1){
            msg = "Hide Button 3x2";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            get_3x2Button().setVisible(false);
            msg = "Draw Sign in Label 8";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            setsignInGUI(getjLabel8(), x, y, true);
        } else if(x == 2 && y == 2){
            msg = "Hide Button 3x3";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            get_3x3Button().setVisible(false);
            msg = "Draw Sign in Label 9";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            setsignInGUI(getjLabel9(), x, y, true);
        }
    }
    
    //Print the game to File
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

    /* 
     * Set Sign to board
     * Send to other Client the changes
     */
    public JLabel setsignInGUI(JLabel label, int x, int y, boolean update) {
        
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
        if(!update){
            try {
                dos.write(x);
                dos.write(y);
                msg = String.format("Played in x: %d   y: %d\n", x, y);
                log.writeInFile("loggame", msg);
                System.out.printf(msg);
                dos.flush();
            } catch (IOException e) {
                log.writeInFile("logerror", e.toString());
                e.printStackTrace();
            }
        }
        
        return label;
    }
    
    /* 
     * Winner Check Method all possiable way
     * X|-|-    -|X|-   -|-|X   X|-|-   -|-|X   X|X|X   -|-|-   -|-|-
     * X|-|-    -|X|-   -|-|X   -|X|-   -|X|-   -|-|-   X|X|X   -|-|-
     * X|-|-    -|X|-   -|-|X   -|-|X   X|-|-   -|-|-   -|-|-   X|X|X
     */
    public boolean winner() {
        // Horizontal Win
        if (gameboard[0][0] == 'X' && gameboard[0][1] == 'X' && gameboard[0][2] == 'X') {
            msg = "X Wins";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            SetWhoIsWin('X');
            return true;
        } else if (gameboard[0][0] == 'O' && gameboard[0][1] == 'O' && gameboard[0][2] == 'O') {
            msg = "O Wins";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            SetWhoIsWin('O');
            return true;
        } else if (gameboard[1][0] == 'X' && gameboard[1][1] == 'X' && gameboard[1][2] == 'X') {
            msg = "X Wins";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            SetWhoIsWin('X');
            return true;
        } else if (gameboard[1][0] == 'O' && gameboard[1][1] == 'O' && gameboard[1][2] == 'O') {
            msg = "O Wins";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            SetWhoIsWin('O');
            return true;
        } else if (gameboard[2][0] == 'X' && gameboard[2][1] == 'X' && gameboard[2][2] == 'X') {
            msg = "X Wins";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            SetWhoIsWin('X');
            return true;
        } else if (gameboard[2][0] == 'O' && gameboard[2][1] == 'O' && gameboard[2][2] == 'O') {
            msg = "O Wins";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            SetWhoIsWin('O');
            return true;
        } // Vertical Wins
        else if (gameboard[0][0] == 'X' && gameboard[1][0] == 'X' && gameboard[2][0] == 'X') {
            msg = "X Wins";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            SetWhoIsWin('X');
            return true;
        } else if (gameboard[0][0] == 'O' && gameboard[1][0] == 'O' && gameboard[2][0] == 'O') {
            msg = "O Wins";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            SetWhoIsWin('O');
            return true;
        } else if (gameboard[0][1] == 'X' && gameboard[1][1] == 'X' && gameboard[2][1] == 'X') {
            msg = "X Wins";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            SetWhoIsWin('X');
            return true;
        } else if (gameboard[0][1] == 'O' && gameboard[1][1] == 'O' && gameboard[2][1] == 'O') {
            msg = "O Wins";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            SetWhoIsWin('O');
            return true;
        } else if (gameboard[0][2] == 'X' && gameboard[1][2] == 'X' && gameboard[2][2] == 'X') {
            msg = "X Wins";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            SetWhoIsWin('X');
            return true;
        } else if (gameboard[0][2] == 'O' && gameboard[1][2] == 'O' && gameboard[2][2] == 'O') {
            msg = "O Wins";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            SetWhoIsWin('O');
            return true;
        } // Diagonal Wins
        else if (gameboard[0][0] == 'X' && gameboard[1][1] == 'X' && gameboard[2][2] == 'X') {
            msg = "X Wins";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            SetWhoIsWin('X');
            return true;
        } else if (gameboard[0][0] == 'O' && gameboard[1][1] == 'O' && gameboard[2][2] == 'O') {
            msg = "O Wins";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            SetWhoIsWin('O');
            return true;
        } else if (gameboard[0][2] == 'X' && gameboard[1][1] == 'X' && gameboard[2][0] == 'X') {
            msg = "X Wins";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            SetWhoIsWin('X');
            return true;
        } else if (gameboard[0][2] == 'O' && gameboard[1][1] == 'O' && gameboard[2][0] == 'O') {
            msg = "O Wins";
            log.writeInFile("loggame", msg);
            System.out.println(msg);
            SetWhoIsWin('O');
            return true;
        }
        return false;
    }
    
    //Print the game to File
    public static void printBoard(char[][] gameboard) {
        File file = new File("Logs/Game Record.txt");
        if(!file.exists())
            try {
                file.createNewFile();
            } catch (IOException e1) {
                log.writeInFile("logerror", e1.toString());
                e1.printStackTrace();
            }
        
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
            String rec = "Game Record \n";
            byte[] b = rec.getBytes();
            fos.write(b);
        } catch (FileNotFoundException e) {
            log.writeInFile("logerror", e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            log.writeInFile("logerror", e.toString());
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
                    log.writeInFile("logerror", e.toString());
                    e.printStackTrace();
                }
            }
            try {
                fos.write('\n');
            } catch (IOException e) {
                log.writeInFile("logerror", e.toString());
                e.printStackTrace();
            }
        }
    }
    
    //Setters and Getters Method Start
    public void SetWhoIsWin(char winnersign){
        this.WinnerSign = winnersign;
    }

    public char GetWhoIsWin(){
        return WinnerSign;
    }

    public void setDis(DataInputStream dis) {
        this.dis = dis;
    }
    
    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }
    
    public void set_1x1Button(JButton _1x1Button) {
        this._1x1Button = _1x1Button;
    }
    
    public void set_1x2Button(JButton _1x2Button) {
        this._1x2Button = _1x2Button;
    }
    
    public void set_1x3Button(JButton _1x3Button) {
        this._1x3Button = _1x3Button;
    }

    public void set_2x1Button(JButton _2x1Button) {
        this._2x1Button = _2x1Button;
    }

    public void set_2x2Button(JButton _2x2Button) {
        this._2x2Button = _2x2Button;
    }

    public void set_2x3Button(JButton _2x3Button) {
        this._2x3Button = _2x3Button;
    }

    public void set_3x1Button(JButton _3x1Button) {
        this._3x1Button = _3x1Button;
    }

    public void set_3x2Button(JButton _3x2Button) {
        this._3x2Button = _3x2Button;
    }

    public void set_3x3Button(JButton _3x3Button) {
        this._3x3Button = _3x3Button;
    }

    public JButton get_1x1Button() {
        return _1x1Button;
    }

    public JButton get_1x2Button() {
        return _1x2Button;
    }

    public JButton get_1x3Button() {
        return _1x3Button;
    }

    public JButton get_2x1Button() {
        return _2x1Button;
    }

    public JButton get_2x2Button() {
        return _2x2Button;
    }

    public JButton get_2x3Button() {
        return _2x3Button;
    }

    public JButton get_3x1Button() {
        return _3x1Button;
    }

    public JButton get_3x2Button() {
        return _3x2Button;
    }

    public JButton get_3x3Button() {
        return _3x3Button;
    }

    public DataInputStream getDis() {
        return dis;
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public javax.swing.JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(javax.swing.JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public javax.swing.JLabel getjLabel2() {
        return jLabel2;
    }

    public void setjLabel2(javax.swing.JLabel jLabel2) {
        this.jLabel2 = jLabel2;
    }

    public javax.swing.JLabel getjLabel3() {
        return jLabel3;
    }

    public void setjLabel3(javax.swing.JLabel jLabel3) {
        this.jLabel3 = jLabel3;
    }

    public javax.swing.JLabel getjLabel4() {
        return jLabel4;
    }

    public void setjLabel4(javax.swing.JLabel jLabel4) {
        this.jLabel4 = jLabel4;
    }

    public javax.swing.JLabel getjLabel5() {
        return jLabel5;
    }

    public void setjLabel5(javax.swing.JLabel jLabel5) {
        this.jLabel5 = jLabel5;
    }

    public javax.swing.JLabel getjLabel6() {
        return jLabel6;
    }

    public void setjLabel6(javax.swing.JLabel jLabel6) {
        this.jLabel6 = jLabel6;
    }

    public javax.swing.JLabel getjLabel7() {
        return jLabel7;
    }

    public void setjLabel7(javax.swing.JLabel jLabel7) {
        this.jLabel7 = jLabel7;
    }

    public javax.swing.JLabel getjLabel8() {
        return jLabel8;
    }

    public void setjLabel8(javax.swing.JLabel jLabel8) {
        this.jLabel8 = jLabel8;
    }

    public javax.swing.JLabel getjLabel9() {
        return jLabel9;
    }

    public void setjLabel9(javax.swing.JLabel jLabel9) {
        this.jLabel9 = jLabel9;
    }
    //Setters and Getters Method Ends 
}
