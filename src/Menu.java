import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Menu {
    PrintWriter writer;
    int state;
    public Menu(PrintWriter w) {
        this.writer = w;
    }

    public void MainMenu() {
        writer.print("==========Welcome to Booking Management System==========\n" +
        "1. Check available rooms\n" +
        "2. Edit a room\n" +
        "3. Book a room\n" +
        "========================================================\n" + "Enter your choice: ");
    }
}
