
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Bank {

    Scanner sc = new Scanner(System.in);

    public String Accountnum;
    public String name;
    public String UserName;
    public String Passwoard;
    public float B;
    public String FileName;

    public Bank() {

    }

    // ----------------method to open new account
    public void newAccount() {

        System.out.print("What FileName Do you Want To save Your Data on it: ");
        FileName = sc.next();

        System.out.print("\nPlease Enter Account No: ");
        Accountnum = sc.next();

        System.out.print("Please Enter Name: ");
        name = sc.next();

        System.out.print("Enter Balance: ");
        B = sc.nextLong();

        System.out.print("Please Enter Passwoard: ");
        Passwoard = sc.next();

        System.out.print("Please Enter User Name: ");
        UserName = sc.next();

        System.out.println("");

        InsertRow(Accountnum, name, B, Passwoard, UserName);

    }

    public void showAccount() {

        String fmt = "%5s %16s %12s";
        String fmt2 = "%6s %18s %12s";
        System.out.printf(fmt, "\nAccount_Name", "  Account_Number", "Balance\n");

        System.out.printf(fmt, "------------", "  -------------", "  ------------");
        printDate();
        System.out.printf(fmt2, name, "SA" + Accountnum, B);
        System.out.println();

    }

    // --------------------method to deposit money
    public void deposit() {
        long deposit;
        System.out.print("How much do you want to deposit: ");
        deposit = sc.nextLong();
        System.out.print("Successful to deposit " + deposit + "\t\t");
        B = B + deposit;
    }

    // -----------method to withdraw money
    public void withdrawal() {

        long amount_of_width;
        System.out.print("Enter the amount you want to withdraw: ");
        amount_of_width = sc.nextLong();

        if (B >= amount_of_width) {
            B = B - amount_of_width;
            System.out.print("Balance after withdrawal: " + B + "\t\t");
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
    public void check_Username_passwoard(String UserName, String passwoard) {

        if (UserName.equals(UserName) && passwoard.equals(Passwoard)) {
            System.out.println("\tSucsses Login.......");

        } else {
            System.out.println("\tunSucsses to login......");
            System.exit(0);
        }

    }

    public String printDate() {
        Date d = new Date();

        SimpleDateFormat sd = new SimpleDateFormat("\t\t\t\t\t hh:mm a -------");
        System.out.print(sd.format(d));

        SimpleDateFormat sd2 = new SimpleDateFormat(" dd/mm/yyyy");
        System.out.println(sd2.format(d));

        return d.toString();
    }

    public void printFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(
                "X:/Documents/Programming/Java//" + FileName + ".txt", true);
        OutputStreamWriter fw = new OutputStreamWriter(fos);

        fw.write("--------- FCIT Bank ---------\n");
        fw.write("\t\n Welcome User: " + name);
        fw.write("\t\t\t\t\t\t " + printDate() + "\n");
        fw.write("\t\n Your Account Number :" + "SA" + Accountnum + "\n");
        fw.write("\t Your Balance :" + B + "\n");
        fw.write("\n-------------------------------------------------------\n");
        fw.write("\n");
        fw.close();

    }

    public void ExitMessage() throws IOException {
        FileOutputStream fos = new FileOutputStream(
                "X:/Documents/Programming/Java//" + FileName + ".txt", true);
        OutputStreamWriter fw = new OutputStreamWriter(fos);
        fw.write("\t\n ** USER HAS EXIT FROM PROGRAM **");
        fw.write("\t\t\t\t\tin -->" + printDate() + "\n");
        fw.write("\n");
        fw.close();
    }

    public void ResetFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(
                "X:/Documents/Programming/Java//" + FileName + ".txt");
        fos.close();
    }

    public void InsertRow(String Accountnum, String name, float B, String Passwoard, String UserName) {

        String sql = "INSERT INTO user(Accountnum, Name, B, passwoard , UserName) VALUES(?,?,?,?,?)";

        try (Connection conn = SQL_Bank_Server.conn();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, Accountnum);
            pstmt.setString(2, name);
            pstmt.setInt(3, (int) B);
            pstmt.setString(4, Passwoard);
            pstmt.setString(5, UserName);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        /*
         * try {
         * Connection con = SQL_Bank_Server.conn();
         * PreparedStatement ps = null;
         * 
         * String s =
         * "INSERT INTO USER(Accountnum, Name, B, passwoard , UserName) VALUES(?,?,?,?,?)"
         * ;
         * ps = con.prepareStatement(s);
         * ps.setString(1, Accountnum);
         * ps.setString(2, name);
         * ps.setInt(3, (int) B);
         * ps.setString(4, Passwoard);
         * ps.setString(5, UserName);
         * ps.executeUpdate();
         * Thread.sleep(2000);
         * for (int i = 0; i < 7; i++) {
         * System.out.print(".");
         * }
         * System.out.println("Done");
         * } catch (SQLException ex) {
         * System.out.println(ex.getMessage());
         * } catch (InterruptedException ex) {
         * System.err.println(ex.getMessage());
         * }
         * 
         * }
         */
    }

}
