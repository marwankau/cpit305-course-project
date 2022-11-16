package DBclients;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import DBServer.User;

public class Worker  {
     
    public static void main(String[] args) {

     Socket socket ;
        
             ;
             try {
                 socket = new Socket(InetAddress.getLocalHost(), 2000);
     
     
                 DataInputStream dis = new DataInputStream(socket.getInputStream());
                 DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
         
                 Scanner keyboard = new Scanner(System.in);
         
                 System.out.print("username: ");
                 String username = keyboard.nextLine();
                 
                 System.out.print("password: ");
                 String password = keyboard.nextLine();
         
         
                 dos.writeUTF(username);
                 dos.writeUTF(password);
         
                 String res = dis.readUTF();
                
         
                 if (res.equals("success")) {
                     // create two threads one for receiveing and one for sending
                     String name=dis.readUTF();
                     String role=dis.readUTF();
                     System.out.println("welcome MR."+name);
                     linkToServer sender = new linkToServer(dos, dis,socket, role);
                   
         
                     sender.start();
                     
         
                     sender.join();
                    
                 }
                 
                 else {
                     System.out.println("Wrong username or password!");
                 }
         
                 keyboard.close();
                 socket.close();
             } catch (UnknownHostException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             } catch (IOException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             } catch(InterruptedException e){
                 e.printStackTrace();
             }
         }
        
    }



