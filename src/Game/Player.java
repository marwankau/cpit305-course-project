package Game;
import Connection.DataBase.DataBase;

public class Player {

    private static DataBase db = DataBase.getDatabase();
    private static int id = db.lastIDRecord();

    public Player(){

    }
    
    // Add Player into Database
    public void addPlayer(String email, String username, String password) {
        db.addPlayerToDatabase(++id, username, password, email);
    }
    //Return boolean if Correct Login 
    public static boolean isCorrectLogin(String u, String p){
        return db.isCorrectLogin(u, p);
    }

    //Return boolean if Email exist in Database
    public boolean isEmailExist(String e){
        return db.searchEmail(e);
    }

    //Return boolean if User name exist in Database
    public boolean isUsernameExist(String u){
        return db.searchUsername(u);
    }

}
