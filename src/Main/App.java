package Main;


import Connection.DataBase.DataBaseInit;
import GUI.LoginGUI;

public class App {
    public static void main(String[] args) {
        DataBaseInit DatabaseCreateTableIfNotExist = new DataBaseInit();
        new LoginGUI().setVisible(true);

        
    }
}