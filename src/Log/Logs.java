package Log;

/* 
 * This class responsiable for Logs files 
 * Make sure Logs folder is exist
 * if it is not it will create new one
 * Make sure Logs files (LogError, LogGame, LogNetwork, LogDatabase) is exist
 * if it is not it will create new one
 * Write in the file from all classes
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class Logs {
    private File folder;
    private File LogError;
    private File LogGame;
    private File LogNetwork;
    private File LogDatabase;

    private FileOutputStream fos;
    private PrintWriter writer;

    private DateTimeFormatter  dtf;
    private LocalDateTime now;

    public Logs(){
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeInFile(String f, String msg) {
        File ChoosenFile = LogError;
        
        if(f.equalsIgnoreCase("LogError")) ChoosenFile = LogError;
        if(f.equalsIgnoreCase("LogGame")) ChoosenFile = LogGame;
        if(f.equalsIgnoreCase("LogNetwork")) ChoosenFile = LogNetwork;
        if(f.equalsIgnoreCase("LogDatabase")) ChoosenFile = LogDatabase;

        try {
            fos = new FileOutputStream(ChoosenFile, true);
            writer = new PrintWriter(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        now = LocalDateTime.now();  

        String time = dtf.format(now);

        writer.printf("%s\t%s\n", time, msg);

        writer.flush();
        writer.close();
    }

    private void init() throws IOException{
        folder = new File("Logs");
        if(!folder.exists()){
            folder.mkdirs();
            System.out.println(folder.getName() + " Folder Has created.");
        }
        
        LogError = new File("Logs/LogError.txt");
        if(!LogError.exists()){
            LogError.createNewFile();
            System.out.println(LogError.getName() + " file Has created.");
        }
        
        LogGame = new File("Logs/LogGame.txt");
        if(!LogGame.exists()){
            LogGame.createNewFile();
            System.out.println(LogGame.getName() + " file Has created.");
        }
        
        LogNetwork = new File("Logs/LogNetwork.txt");
        if(!LogNetwork.exists()){
            LogNetwork.createNewFile();
            System.out.println(LogNetwork.getName() + " file Has created.");
        }
        
        LogDatabase = new File("Logs/LogDatabase.txt");
        if(!LogDatabase.exists()){
            LogDatabase.createNewFile();
            System.out.println(LogDatabase.getName() + " file Has created.");
        }
    }
}
