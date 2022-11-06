package client.RestaurantsClasses;

import java.util.ArrayList;

public abstract class Restaurant {
    private String name;
    private  ArrayList<String> menuList = new ArrayList<String>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getMenuList() {
        return menuList;
    }

    public void setMenuList(ArrayList<String> menuList) {
        this.menuList = menuList;
    }


    //
    public void displayReMe(){

        System.out.println(getName());
        int s = 1;
        //menuList = getMenuList();
        for (String x : menuList) {

            System.out.println(s+"- "+ x);
            s++;
        }

    }
}
