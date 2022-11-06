package App;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Receiver {

    private ArrayList<String> orderList = new ArrayList<String>();

    Socket socket;

    public Receiver(Socket socket){
        this.socket = socket;
    }

    public void receiveOrderList() throws IOException {

        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        try {
            Object object = input.readObject();
            orderList = (ArrayList<String>) object;
            for (String in :
                    orderList) {
                System.out.println(in);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }




    public ArrayList<String> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<String> orderList) {
        this.orderList = orderList;
    }


//    @Override
//    public void run() {
//        InputStream inputStream;
//
//        try {
//            inputStream = socket.getInputStream();
//            Scanner recTol = new Scanner(inputStream);
//
//
//            String line;
//            while (true){
//                line = recTol.nextLine();
//                System.out.println("clint msg:  "+line);
//
//
//                if (line.equalsIgnoreCase("exit")) break;
//            }
//
//            socket.shutdownInput();
//
//        } catch (IOException e) {
//            //e.printStackTrace();
//        }
//
//    }
}
