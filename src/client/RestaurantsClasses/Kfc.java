package client.RestaurantsClasses;


import java.io.*;
import java.util.ArrayList;

public class Kfc extends Restaurant {


    public Kfc(){
        ArrayList<String> menu = new ArrayList<String>();
        setName("KFC");
        String tol = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(
                    "E:\\food_delivery_App\\src\\Kfc_menu"))));
            while ((tol = reader.readLine()) != null) {

                menu.add(tol);

            }
            setMenuList(menu);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

