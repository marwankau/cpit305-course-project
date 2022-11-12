package client.RestaurantsClasses;


import java.io.*;
import java.util.ArrayList;

public class MacDonalds extends Restaurant {


    public MacDonalds(){
        ArrayList<String> macMenu = new ArrayList<String>();
        setName("MacDonalds");
        String tol = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(
                    "E:\\food_delivery_App\\src\\MacDonalds"))));
            while ((tol = reader.readLine()) != null) {

                macMenu.add(tol);

            }
            setMenuList(macMenu);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
