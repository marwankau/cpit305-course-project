package client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Sender {


         BufferedReader reader;
         PrintWriter writer;
         Socket socket;
         ObjectOutputStream objectOutputStream;
         ArrayList<String> ordList;

    public Sender(Socket socket, BufferedReader reader, PrintWriter writer,ObjectOutputStream objectOutputStream,ArrayList<String> ordList) {
        this.socket = socket;
        this.reader = reader;
        this.writer = writer;
        this.objectOutputStream = objectOutputStream;
        this.ordList = ordList;
    }


    public void contactServer() throws IOException {
        Scanner scan = new Scanner(System.in);

        String f = reader.readLine();
        System.out.println(f);
        String client = scan.nextLine();
        writer.println(client);
        writer.flush();
        if(client.equalsIgnoreCase("S")){
            signUp();
            logInServer();
            sendOrderList();
        }
        else if(client.equalsIgnoreCase("L")){
            logInServer();
            sendOrderList();


        }

        objectOutputStream.flush();
        objectOutputStream.close();



    }


    public void signUp()throws IOException{
        Scanner scan = new Scanner(System.in);

        System.out.print("user name:  ");
        String userName = scan.nextLine();
        writer.println(userName);

        System.out.print("password:  ");
        String pass = scan.nextLine();
        writer.println(pass);

        System.out.println("PhoneNumber  ");
        String phoneNum = scan.nextLine();
        writer.println(phoneNum);

        System.out.print("Address:  ");
        String address = scan.nextLine();
        writer.println(address);


        writer.flush();
        String resp = reader.readLine();
        //response
        System.out.println("server response:  "+resp);
    }

    public void logInServer() throws IOException {

        Scanner scan = new Scanner(System.in);
        System.out.print("user name:  ");
        String userName = scan.nextLine();
        writer.println(userName);



        System.out.print("password:  ");
        String pass = scan.nextLine();
        writer.println(pass);

        writer.flush();

        String resp = reader.readLine();
        if(resp.equalsIgnoreCase("Login failed")) {
            System.out.println("server response:  " + resp);
            logInServer();
        }

    }




    public void sendOrderList() throws IOException {

        objectOutputStream.writeObject(ordList);

        String ttt = reader.readLine();
        System.out.println(ttt);
    }



}
