//package Game;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//public class Connections {
//    public static final String username = "";
//    public static final String password = "";
//    public static final String url = "";
//    public static Connection con = null;
//    
//    public static Connection getConnection(){
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            con = DriverManager.getConnection(url, username, password);
//        } catch (Exception e){
//            
//        }
//        return con;
//    }
//    
//    
//}