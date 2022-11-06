package App;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

class ClientHandler extends Thread {
     db dataBase;
     BufferedReader reader;
     PrintWriter writer;
     Socket socket;
    ObjectInputStream objectInputStream;
    private ArrayList<String> list = new ArrayList<String>();


    // Constructor
    public ClientHandler(Socket socket, BufferedReader inputStream, PrintWriter outputStream,db dataBase,ObjectInputStream objectInputStream) {
        this.socket = socket;
        this.reader = inputStream;
        this.writer = outputStream;
        this.dataBase =dataBase;
        this.objectInputStream = objectInputStream;
    }

    @Override
    public void run() {

        try {

            writer.println("(S)SignUp\t(L)LogIn");
            writer.flush();
            String sL = reader.readLine();
            //writer.flush();

            if (sL.equalsIgnoreCase("S")){
                signUpDB();
                logIn();
                recOrderList();
            }else if (sL.equalsIgnoreCase("l")) {
                logIn();
                recOrderList();
            }

            objectInputStream.close();

        } catch (IOException | InterruptedException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void signUpDB() throws IOException {

        String userName = reader.readLine();
        System.out.println("username  " + userName);
        String password = reader.readLine();
        System.out.println("password  " + password);
        String phoneNum = reader.readLine();
        System.out.println("PhoneNumber  " + phoneNum);
        String address = reader.readLine();
        System.out.println("Address  " + address);

        boolean xtx = false;
        try {
           xtx = dataBase.Sign_up(userName,password,phoneNum,address);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (xtx){
            writer.println("done  "+ userName);
        }else {
            writer.println("signUp failed  ");
        }
        writer.flush();
        writer.close();
    }

    public void logIn() throws IOException, InterruptedException, SQLException {

        String userName = reader.readLine();
        System.out.println("username  " + userName);
        String password = reader.readLine();
        System.out.println("password  " + password);


        if (dataBase.loginDBCheck(userName,password)){
            writer.println("done"+ userName);
        }else {
            writer.println("Login failed  ");
        }

        writer.flush();

    }

    public void recOrderList() throws IOException, ClassNotFoundException, SQLException {
        Object object = objectInputStream.readObject();

        list = (ArrayList<String>) object;
        for (String in :
                list) {
            System.out.println(in);
        }

        writer.println("driver information:   "+dataBase.deliverDB());
        writer.flush();
    }


}