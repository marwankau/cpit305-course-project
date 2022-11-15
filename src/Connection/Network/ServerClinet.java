package Connection.Network;

/* 
 * ServerClient class is responsiable for network and send DataStream to Game Class
 * if port doesn't exist Create server
 * if port exist it open the game and communicate with each other 
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

import GUI.GameGUI;
import Log.Logs;



public class ServerClinet {

    private Scanner scanner = new Scanner(System.in);

    private Socket socket;
    private ServerSocket serverSocket;

    private String ip;
    private int port;
    private boolean accepted = false;

    private DataInputStream dis;
    private DataOutputStream dos;

    private static GameGUI gameGUI = new GameGUI();

    private static Logs log = new Logs();

    private String msg;


    public ServerClinet(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
    
    //Method is doing all the Class Functions and start the game 
    public void init(){

        if (!connect()) initializeServer();
        if (!accepted) ServerRequest();

        gameGUI.setVisible(true);
    } 
    
    /* 
    Check if port exist
    if it exist it connect to it 
    */
    private boolean connect() {
        try {
            socket = new Socket(ip, port);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            gameGUI.game.setDis(dis);
            gameGUI.game.setDos(dos);
            accepted = true;
        } catch (IOException e) {
            msg = "Unable to connect to the address: " + ip + ":" + port + " | Starting a server";
            log.writeInFile("logerror", msg);
            log.writeInFile("lognetwork", msg);
            System.out.println(msg);
            return false;
        }
        String msg = "Successfully connected to the server.";
        log.writeInFile("lognetwork", msg);
        System.out.println(msg);
        return true;
    }

    //If port doesn't exist
    private void initializeServer() {
        try {
            serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
            msg = "Server is Initlized \nWaiting for other player to connect!! ";
            log.writeInFile("lognetwork", msg);
            JOptionPane.showMessageDialog(null, msg);
            System.out.println(msg);
        } catch (Exception e) {
            log.writeInFile("logerror", e.toString());
            e.printStackTrace();
        }
    }

    
    //Accept Client
    private void ServerRequest() {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            gameGUI.game.setDis(dis);
            gameGUI.game.setDos(dos);
            accepted = true;
            msg = "CLIENT HAS REQUESTED TO JOIN, AND WE HAVE ACCEPTED";
            log.writeInFile("lognetwork", msg);
            System.out.println(msg);
        } catch (IOException e) {
            log.writeInFile("logerror", e.toString());
            e.printStackTrace();
        }
    }

    //GetMethods start
    public DataInputStream getDis() {
        return dis;
    }

    public DataOutputStream getDos() {
        return dos;
    }
    public boolean getAccepted() {
        return accepted;
    }
    //GetMethods End
}
