package DBclients;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import javax.management.relation.Role;
import javax.print.event.PrintEvent;

public class linkToServer extends Thread{
    private DataOutputStream dos;
    private DataInputStream dis;
    private Socket socket;
    private static String role;

    public linkToServer(DataOutputStream dos,DataInputStream dis, Socket socket, String role) {
        this.dos = dos;
        this.dis = dis;
        this.socket = socket;
        this.role = role;
    }

    @Override
    public void run() {
        Scanner keyboard = new Scanner(System.in);
        try {

            while (true) {if(role.equalsIgnoreCase("admin")){
                System.out.println(" Choose one of the options below.");
                System.out.println("  1: Add a new worker in the library.");
                System.out.println("  2: Removing a worker from the library.");
                System.out.println("  3:list all user.");
                System.out.println("  0: exit");
                System.out.print("options:");
                String msg = keyboard.nextLine();
                
                dos.writeUTF(msg);
                if (msg.equals("1")) {
                    System.out.print("enter username should be the length 3-12 :  ");
                    String username = keyboard.nextLine();
                    System.out.print("enter password should be the length 6-30 :  ");
                    String password = keyboard.nextLine();
                    System.out.print("enter role should be the length 0-20(admin-emp-manager) :  ");
                    String role = keyboard.nextLine();
                    System.out.print("enter fullname should be the length 8-50 :  ");
                    String fullname = keyboard.nextLine();
                    dos.writeUTF(username);
                    dos.writeUTF(password);
                    dos.writeUTF(role);
                    dos.writeUTF(fullname);

                }else if(msg.equals("2")){

                    System.out.print("Enter ID for the worker want to remove: ");
                    int id=keyboard.nextInt();
                    dos.writeInt(id);
                    System.out.println(dis.readUTF());
                    System.out.println("  do you want this worker?");
                    System.out.print("y/n:");
                    String Y_N=keyboard.next();
                    dos.writeUTF(Y_N);

                }
               else if (msg.equals("3")) {
           
              System.out.println(  dis.readUTF());
             
               
                } else if (msg.equals("0")) {
                    break;
                }

            }


            else if(role.equalsIgnoreCase("manager")){
                System.out.println(" Choose one of the options below.");
                System.out.println("  1: Add a new book in the library.");
                System.out.println("  2: Removing a book from the library.");
                System.out.println("  3:list all book.");
                System.out.println("  0: exit");
                System.out.print("options:");
                String msg = keyboard.nextLine();
                 dos.writeUTF(msg);
                 if (msg.equals("1")) {
                    System.out.print("enter book name  :  ");
                    String bookname = keyboard.nextLine();
                    System.out.print("enter book section (if book do not have section in library type :`no`) :  ");
                    String booksec = keyboard.nextLine();
                    
                    dos.writeUTF(bookname);
                    dos.writeUTF(booksec);
                   

                }
                else  if (msg.equals("2")) {
                    
                    System.out.print("Enter ID for the book want to remove: ");
                    int id=keyboard.nextInt();
                    dos.writeInt(id);
                    System.out.println(dis.readUTF());
                    System.out.println("  do you want this book?");
                    System.out.print("y/n:");
                    String Y_N=keyboard.next();
                    dos.writeUTF(Y_N);

                }
                else  if (msg.equals("3")) {
                    
                    System.out.println(  dis.readUTF());

                }else if (msg.equals("0")) {
                    break;
                }


                    
            }   else if(role.equalsIgnoreCase("emp")){
                System.out.println(" Choose one of the options below.");
                System.out.println("  1: Borrow a book from the library.");
                System.out.println("  2: Return a book to the library.");
                System.out.println("  3:list all available books. (isAvailable=1)");
                System.out.println("  4:list all unavailable books. (isAvailable=0)");
                System.out.println("  0: exit");
                System.out.print("options:");
                String msg = keyboard.nextLine();
                dos.writeUTF(msg);
                  if (msg.equals("1")) {
                    
                    System.out.print("Enter ID for the book want to borrow: ");
                    int id=keyboard.nextInt();
                    dos.writeInt(id);
                    System.out.println(dis.readUTF());
                    System.out.println("  do you want this book?");
                    System.out.print("y/n:");
                    String Y_N=keyboard.next();
                    dos.writeUTF(Y_N);

                }else if (msg.equals("2")) {
                    
                    System.out.print("Enter ID for the book want to return: ");
                    int id=keyboard.nextInt();
                    dos.writeInt(id);
                    System.out.println(dis.readUTF());
                    System.out.println("  do you want this book?");
                    System.out.print("y/n:");
                    String Y_N=keyboard.next();
                    dos.writeUTF(Y_N);

                } else  if (msg.equals("3")) {
                    
                    System.out.println(  dis.readUTF());

                } else  if (msg.equals("4")) {
                    
                    System.out.println(  dis.readUTF());

                }else if (msg.equals("0")) {
                    break;
                }
               
                



            }
               
           

               
              
            }
            socket.shutdownInput();

        } catch (IOException e) { 
            System.err.println(e.getMessage());
        } finally {
            keyboard.close();
            try {
                socket.shutdownOutput();
            } catch (IOException e) {
                System.err.println(e.getMessage());
             }
        }
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        linkToServer.role = role;
    }

}
