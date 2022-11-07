import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;

public class Database {
    public static void main(String[] args) throws SQLException {

        Connection con = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/rooms", "root",
                "*abdo2001*");
        Statement stmt = con.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM rooms WHERE Room_No = 3;");

        int room_no = result.getInt("Room_No");

        System.out.println(room_no);

        con.close();

    }

}
