package Connections.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

    private static DataBase single_DataBase = null;

    private final static String username = "root";
    private final static String password = "12345";

    private static Connection con;
    private static Statement statement;
    private static ResultSet result;

    private DataBase() {
        System.out.println("Database connection has been created");
    }

    public static DataBase getDatabase() {
        if (single_DataBase != null) {
            return single_DataBase;
        }

        try {
            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/tictactoc", username, password);
            statement = con.createStatement();
            result = statement.executeQuery("SELECT * FROM players;");
            single_DataBase = new DataBase();
        } catch (SQLException e) {
            // TODO
            System.out.println(e);
        }

        return single_DataBase;
    }

    public boolean searchUsername(String username) {
        try {
            while (result.next()) {
                String usernameTemp = result.getString("username");
                if (usernameTemp.equalsIgnoreCase(username)) {
                    // TODO username exception
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean searchEmail(String email) {
        try {
            while (result.next()) {
                String emailTemp = result.getString("email");
                if (emailTemp.equalsIgnoreCase(email)) {
                    // TODO email Exception
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean isCorrectLogin(String username, String password) {
        try {
            while (result.next()) {
                String usernameTemp = result.getString("username");
                String passwordTemp = result.getString("password");
                if (usernameTemp.equalsIgnoreCase(username) && passwordTemp.equalsIgnoreCase(password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public void addPlayerToDatabase(int id, String username, String password, String email) {

        Statement statementAdd;
        ResultSet resultAdd;
        String insertQuery = String.format(
                "INSERT INTO players (id, username, password, email) VALUES ('%d', '%s', '%s', '%s');\n", id, username,
                password, email);
        try {
            statementAdd = con.createStatement();
            resultAdd = statementAdd.executeQuery(insertQuery);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e);
        }
        System.out.printf("Row added to Database (ID: %d, Username: %s, Password: %s, Email: %s)", id, username, password, email);
        // TODO Log file to database
    }

    public int lastIDRecord() {
        int id = 0;
        try {
            while (result.next()) {
                id = result.getInt("id");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return id;
    }
}
