
import java.io.*;
import java.sql.*;

public class Bank {

    Scanner sc = new Scanner(System.in);

    public String account_number;
    public String name;

    public Bank() {

    }

    // ----------------method to open new account
    public void newAccount() {

        System.out.print("What FileName Do you Want To save Your Data on it: ");
        FileName = sc.next();

        System.out.print("\nPlease Enter Account No: ");
        account_number = sc.next();

        System.out.print("Please Enter Name: ");
        name = sc.next();

        System.out.print("Please Enter User Name: ");
        User_name = sc.next();

        System.out.print("Please Enter Passwoard: ");
        Passwoard = sc.next();

        System.out.print("Enter Balance: ");
        balance = sc.nextLong();
        System.out.println("");

        InsertRow(account_number, name, balance);

    }

    public void showAccount() {

        String fmt = "%5s %16s %12s";
        String fmt2 = "%6s %18s %12s";
        System.out.printf(fmt, "\nAccount_Name", "  Account_Number", "Balance\n");

        System.out.printf(fmt, "------------", "  -------------", "  ------------");
        printDate();
        System.out.printf(fmt2, name, "SA" + account_number, balance);
        System.out.println();

    }

    // --------------------method to deposit money
    public void deposit() {
        long deposit;
        System.out.print("How much do you want to deposit: ");
        deposit = sc.nextLong();
        System.out.print("Successful to deposit " + deposit + "\t\t");
        balance = balance + deposit;
    }

    // -----------method to withdraw money
    public void withdrawal() {
    
            long amount_of_width;
            System.out.print("Enter the amount you want to withdraw: ");
            amount_of_width = sc.nextLong();
    
            if (balance >= amount_of_width) {
                balance = balance - amount_of_width;
                System.out.print("Balance after withdrawal: " + balance + "\t\t");
            } else {
                System.out.println("Your balance is less than " + amount_of_width + "\tTransaction failed...!!");
            }
        }

// ---------------------------
// public void search(String account_num) {
// if (account_number.equals(account_num)) {
// showAccount();
// }
// }
