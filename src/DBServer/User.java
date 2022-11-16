package DBServer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class User {
    int id;
     String username;
      String role;
       String fullName;
        Socket socket; DataInputStream dis;
            DataOutputStream dos;
    public User(int id, String username, String role, String fullName, Socket socket, DataInputStream dis,DataOutputStream dos) {
                this.id=id;
                this.username=username;
                this.role=role;
                this.fullName=fullName;
                this.socket=socket;
                this.dis=dis;
                this.dos=dos;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public Socket getSocket() {
        return socket;
    }
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    public DataInputStream getDis() {
        return dis;
    }
    public void setDis(DataInputStream dis) {
        this.dis = dis;
    }
    public DataOutputStream getDos() {
        return dos;
    }
    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }

}
