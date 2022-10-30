package server.player;

public class Player {
    private int id;
    private String username;
    
    public Player(int id, String username){
        this.id = id;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
