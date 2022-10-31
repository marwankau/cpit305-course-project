import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Menu {
    PrintWriter writer;
    int state;
    public Menu(PrintWriter w) {
        this.writer = w;
    }

    public void MainMenu() {
        System.out.println("==========Welcome to Booking Management System==========");
        System.out.println("1. Check available rooms");
        System.out.println("2. Edit a room");
        System.out.println("3. Book a room");
        System.out.println("Note: To exit type exit: ");
        System.out.println("========================================================");
        System.out.println("Enter your choice: ");

    }
}
