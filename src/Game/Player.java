package Game;

import Connections.DataBase.DataBase;

public class Player {

    private String email;
    private String username;
    private String password;
    private static DataBase db = DataBase.getDatabase();
    private static int id = db.lastIDRecord();

    public Player(){

    }

    public void addPlayer(String email, String username, String password) {
        db.addPlayerToDatabase(++id, username, password, email);
    }

    public static boolean isCorrectLogin(String u, String p){
        return db.isCorrectLogin(u, p);
    }

    public boolean isEmailExist(String e){
        return db.searchEmail(e);
    }

    public boolean isUsernameExist(String u){
        return db.searchUsername(u);
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
