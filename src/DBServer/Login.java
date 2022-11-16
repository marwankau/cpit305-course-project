package DBServer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

public class Login extends Thread  {
   static private Socket socket;

   public Login(Socket socket) {
    this.socket = socket;
}
static Connection conn=DB.getConnection();
   
    @Override
    public void run() {
        try {    
            DataInputStream dis =  new DataInputStream(socket.getInputStream());
            DataOutputStream dos = dos = new DataOutputStream(socket.getOutputStream());

            String username = dis.readUTF();
            String password = dis.readUTF();

            

            if (DBServer.checkLogin(username, password,conn)) {
                System.out.println("true");
                 dos.writeUTF("success");
        
                 User x= new User(DBServer.getID(username,conn),username,DBServer.getRole(username,conn),DBServer.getFullName(username,conn), socket, dis, dos);
                 System.out.println(x.fullName+": connect to server...");
                 new Mediator(socket,x,dis,dos).start();
                 dos.writeUTF(x.getFullName());
                 dos.writeUTF(x.getRole());
                
            } else {
                dos.writeUTF("fail");
            }
        } catch (IOException e) {
        }
    }


}

