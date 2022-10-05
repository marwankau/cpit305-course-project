package Game;

import java.util.ArrayList;

public class Player {

    private String email;
    private String name;
    private String password;
    private int id;
    private ArrayList<Player> arrPlayer = new ArrayList<Player>();

    private Player(String email, String name, String password, int id) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.id = id = 1;
    }

    public void addPlayer(String email, String name, String password, int id) {
        for (Player p : arrPlayer) {
            if (p.getEmail() == email) {
//                throw new EmailException;
            }
            if (p.getName() == name) {
//                throw new NameException;
            }
        }
        new Player(email, name, password, ++id);
    }

    public Player getPlayer(Player p) {
        return p;
    }

    public void matchHistroyUpdate() {

    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

}
