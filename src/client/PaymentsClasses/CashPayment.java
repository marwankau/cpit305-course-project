package client.PaymentsClasses;

public class CashPayment implements Payment{


    @Override
    public String pay() {
        System.out.println("driver been noticed");
        return "please keep cash with you when driver arrive";
    }
}
