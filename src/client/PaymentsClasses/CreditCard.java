package client.PaymentsClasses;

import java.util.Scanner;

public class CreditCard implements Payment{


    @Override
    public String pay() {

        Scanner scanner =  new Scanner(System.in);

        System.out.println("card number:  ");
        String cardNum = scanner.nextLine();
        System.out.println("name Holder:  ");
        String nameHolder = scanner.nextLine();
        System.out.println("expiredDate  ");
        String expiredDate = scanner.nextLine();
        System.out.println("csv  ");
        String csv = scanner.nextLine();


        System.out.println(cardNum + "\n" + nameHolder+ "\n" + expiredDate+"\n"+ csv);

        for (int i=0; i < 10; i++) {
            System.out.print(".");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("payments done");
        return "Payed with Card";
    }
}
