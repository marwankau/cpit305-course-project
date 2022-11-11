
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Bank_System {

    public static void main(String[] args) throws IOException {
        int counter = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("\t\t ** Welcome To FCIT Server ** ");
        System.out.print("Login As: ");
        String n = sc.nextLine();
        System.out.println("");
        String ch = "";
        String msg;

        while (!ch.equalsIgnoreCase("3")) {

            InputStreamReader inr = new InputStreamReader(s.getInputStream());
            OutputStreamWriter owr = new OutputStreamWriter(s.getOutputStream());

            BufferedReader bfr = new BufferedReader(inr);
            BufferedWriter bfw = new BufferedWriter(owr);
            
            msg = "";

            

            System.out.print("\n1. To Talk With Server\n2. To Enter FCIT Bank\n3. Exit\n -----> ");
            ch = sc.nextLine();

            switch (ch) {
                case "1":

                    while (!msg.equalsIgnoreCase("bye")) {

                        if (counter > 0) {
                            bfw.write("** Successful Login **");
                            bfw.newLine();
                            bfw.flush();
                            counter--;
                        }
                        System.out.print("\n\nEnter what do you want to say to Server ('?' FOR INSTRUCTION): ");
                        msg = sc.nextLine();

                        bfw.write(msg);
                        bfw.newLine();
                        bfw.flush();

                        System.out.print("Server: " + bfr.readLine()); 
                        

                        if (msg.equalsIgnoreCase("?")) {
                            System.out.println("** name: to show name of user\n   \t   date: to show current date\n   \t   hi:   welcome message\n   \t   bye:  Log out \t**");
                        }

                        if (msg.equalsIgnoreCase("bye")) {
                            System.out.println("");
                            break;
                        } else if (msg.equalsIgnoreCase("name")) {
                            System.out.println("' "+n.toUpperCase()+" '");
                        }
                    }
                    counter++;
                    break;

                case "2":

                    while (true) {
                        Bank bank = new Bank();

                        String answerUser;
                        String answerPass;

                        while (true) {

                            System.out.println("\t-- Welcom to FCIT Bank --");

                            System.out.print("Do you have an account (Y/N) ? ");
                            String answer = sc.next();
                        }
