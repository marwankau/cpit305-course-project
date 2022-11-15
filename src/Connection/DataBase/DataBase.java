package Connection.DataBase;

/* 
 * This class is to connect to Database using Singleton Database connection
 * Register Player into Database
 * Verfiy Login using Database
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Log.Logs;


public class DataBase {

    private static DataBase single_DataBase = null;

    //TODO:(IGNORE IF DATABASE IS WORKING) Change User name and Password for Database if is not same as yours Database differet
    private final static String user = "root";
    private final static String pass = "12345";

    private static Connection con;
    private static Statement statement;
    private static MessageDigest md;
    private static ResultSet result;

    private static Logs log = new Logs();

    private DataBase() {
        System.out.println("Database connection has been created");
    }

    //Singleton Database connection
    //Define Variables
    public static DataBase getDatabase() {
        if (single_DataBase != null) {
            return single_DataBase;
        }

        try {
            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/tictactoc", user, pass);
            statement = con.createStatement();
            md = MessageDigest.getInstance("MD5");
            result = statement.executeQuery("SELECT * FROM players;");
            single_DataBase = new DataBase();
            log.writeInFile("logdatabase", user + " has Connected to Database.");

        } catch (SQLException e) {
            log.writeInFile("logerror", e.toString());
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            log.writeInFile("logerror", e.toString());
            e.printStackTrace();
        }

        return single_DataBase;
    }

    //Method to Reset Result SELECT * FROM players; to start from top
    private void resetResult(){
        try {
            result = statement.executeQuery("SELECT * FROM players;");
        } catch (SQLException e) {
            log.writeInFile("logerror", e.toString());
            e.printStackTrace();
        }
    }

    //Search Username to prevent duplicated Username in Database
    public boolean searchUsername(String username) {
        log.writeInFile("logdatabase", String.format("Looking for Username %s in Database.",username));
        resetResult();
        try {
            while (result.next()) {
                String usernameTemp = result.getString("username");
                if (usernameTemp.equalsIgnoreCase(username)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            log.writeInFile("logerror", e.toString());
            e.printStackTrace();
        }
        return false;
    }

    //Search Email to prevent duplicated Email in Database
    public boolean searchEmail(String email) {
        log.writeInFile("logdatabase", String.format("Looking for Email %s in Database.",email));
        resetResult();
        try {
            while (result.next()) {
                String emailTemp = result.getString("email");
                if (emailTemp.equalsIgnoreCase(email)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            log.writeInFile("logerror", e.toString());
            System.out.println(e);
        }
        return false;
    }

    //Verfiy Login in Database
    public boolean isCorrectLogin(String username, String password) {
        log.writeInFile("logdatabase", String.format("Verfiy login Username %s in Database.",username));
        resetResult();
        md.update(password.getBytes());
        password = byte2hex(md.digest());
        try {
            while (result.next()) {
                String usernameTemp = result.getString("username");
                String passwordTemp = result.getString("password");
                System.out.println("Username and Password is correct");
                if (usernameTemp.equalsIgnoreCase(username) && passwordTemp.equalsIgnoreCase(password)) {
                    log.writeInFile("logdatabase", String.format("Verfied Login Username %s",username));
                    return true;
                }
            }
        } catch (SQLException e) {
            log.writeInFile("logerror", e.toString());
            e.printStackTrace();
        }
        return false;
    }

    //Insert new play into Database
    public void addPlayerToDatabase(int id, String username, String password, String email) {
        md.update(password.getBytes());
        password = byte2hex(md.digest());
        Statement statementAdd;
        ResultSet resultAdd;
        String insertQuery = String.format(
            "INSERT INTO players (id, username, password, email) VALUES ('%d', '%s', '%s', '%s');", id, username,
            password, email);
            try {
                statementAdd = con.createStatement();
                resultAdd = statementAdd.executeQuery(insertQuery);
                
            } catch (SQLException e) {
                log.writeInFile("logerror", e.toString());
                e.printStackTrace();
            }
            System.out.printf("Row added to Database (ID: %d, Username: %s, Password: %s, Email: %s)\n", id, username,
            password, email);
            log.writeInFile("logdatabase", String.format("Add player into DataBase \nID: %d \nUsername: %s \nEmail: %s", id, username, email));
        }

    //To Increment ID 
    public int lastIDRecord() {
        resetResult();
        int id = 0;
        try {
            while (result.next()) {
                id = result.getInt("id");
            }
        } catch (SQLException e) {
            log.writeInFile("logerror", e.toString());
            e.printStackTrace();
        }
        return id;
    }

    //Convert Byte to Hexadecimal for Digest
    private String byte2hex(byte[] digest) {
        StringBuilder hex = new StringBuilder();

        for (byte b : digest) {
            hex.append(String.format("%02x", b));
        }

        return hex.toString();
    }
}
