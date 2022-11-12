package client;


import client.PaymentsClasses.Payment;
import client.RestaurantsClasses.Restaurant;
import client.RestaurantsClasses.RestaurantFactory;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {
    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        Socket socket = new Socket("localhost", 1000);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());


        //main menu
        String ordTol = "";
        while (true){

            if(ordTol.equalsIgnoreCase("f")||ordTol.equalsIgnoreCase("exit"))break;

            System.out.println("welcome to main menu: ");
            System.out.println("chose one of the following:\n1. albaik\n2. kfc\n3. macdonalds");
            System.out.println("if you want to quit write (exit) ");

            String restN = scan.nextLine();
            if(restN.equalsIgnoreCase("exit"))break;

        RestaurantFactory restaurantFactory = new RestaurantFactory();
        Restaurant restaurant = restaurantFactory.choseRestaurant(restN);

        Order order = new Order(restaurant.getMenuList());


            while (true) {

                restaurant.displayReMe();

                System.out.println("(L)OrderList\t(O)Add Order\t(t)Total\t(B)Back\t(F)Finish Order\tExit");


                ordTol = scan.nextLine();
                if (ordTol.equalsIgnoreCase("b") || ordTol.equalsIgnoreCase("exit")) break;
                if (ordTol.equalsIgnoreCase("o"))
                    order.choseOrder();
                if (ordTol.equalsIgnoreCase("l"))
                    order.showFullOrderList();
                if (ordTol.equalsIgnoreCase("t"))
                    order.showFullPrice();
                if (ordTol.equalsIgnoreCase("f")) {
                    Sender senD = new Sender(socket, reader, writer, objectOutputStream, order.getOrderList());
                    senD.contactServer();
                    order.showFullOrderList();
                    order.showFullPrice();
                    //strategy
                    order.chosePayments();
                    System.out.println("Thanks for using This Application ~~~ 3afya");

                    break;
                }

            }
        }




        }
    }

