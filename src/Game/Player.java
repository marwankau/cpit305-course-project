package Game;

import java.util.ArrayList;

public class Player {

    private String email;
    private String name;
    private String password;
    private static int id = 1;
    private ArrayList<Player> arrPlayer = new ArrayList<Player>();

    private Player(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        id++;
    }

    public void addPlayer(String email, String name, String password) {
        for (Player p : arrPlayer) {
            if (p.getEmail() == email) {
//                throw new EmailException;
            }
            if (p.getName() == name) {
//                throw new NameException;
            }
        }
        new Player(email, name, password);

    }

    public Player getPlayer(String userName, String password) {
        for(Player p : arrPlayer){
            if (p.getName() != name) {
                // throw new NameException;
            }
            if (p.getName() == name && p.getPassword() != password) {
                // throw new PasswordException;
            }
            if (p.getName() == name && p.getPassword() == password){
                return p;
            }
        }
        return null;
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


}
