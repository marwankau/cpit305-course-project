package client;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Order {

    private ArrayList<String> orderList = new ArrayList<String>();
    private ArrayList<String> menuList = new ArrayList<String>();

    public Order(ArrayList<String> menuList) {
        this.menuList = menuList;
    }

    public ArrayList<String> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<String> orderList) {
        this.orderList = orderList;
    }

    public ArrayList<String> getMenuList() {
        return menuList;
    }

    public void setMenuList(ArrayList<String> menuList) {
        this.menuList = menuList;
    }

    public void choseOrder(){
        System.out.println("chose an order: ");
        Scanner scan = new Scanner(System.in);
        String customerReader = scan.nextLine();

        int tol = Integer.parseInt(customerReader) - 1;
        System.out.println("how manny:  ");
        int numT = scan.nextInt();
        for (int i = 0; i < numT ; i++) {
            orderList.add(menuList.get(tol));
        }

        System.out.println("this order add to your list:   "+menuList.get(tol)+"-> "+numT);



    }

    public void showFullOrderList(){
        for (String gg :
                orderList) {
            System.out.println(gg);
        }
    }

    public void showFullPrice(){


        int total = 0;
        for (String s : orderList) {

            StringTokenizer tok = new StringTokenizer(s);
            String xxx = "";
            while (tok.hasMoreTokens()) {
                xxx = tok.nextToken(" ");

                if (xxx.matches("[0-9]+")) {

                    total += Integer.parseInt(xxx);
                }
            }

        }
        System.out.println("this is total:  "+total);
    }


}
