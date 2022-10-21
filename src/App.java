import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int choice;

        while(true){
            System.out.println("---------Welcome to Hospital Reservation System---------");
            System.out.println(" 1. Add Doctor\n 2. Add Patient\n 3. Book Appointment\n 4. Cancel Appointment\n 10. Exit");
            System.out.print("Choose from the above: ");
            choice = input.nextInt();

            if(choice == 10){
                System.out.println("Exiting the system...");
                break;
            }else{
                System.out.println("Enter a valid choice");
            }
        }
    }
}