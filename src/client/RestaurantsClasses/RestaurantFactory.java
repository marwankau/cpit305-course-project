package client.RestaurantsClasses;

public class RestaurantFactory {

    public Restaurant choseRestaurant(String newRestaurantIs){

        Restaurant newRestaurant = null;

        if (newRestaurantIs.equalsIgnoreCase("1")) return new AlBaik();
        else if (newRestaurantIs.equalsIgnoreCase("2"))return new Kfc();
        else if (newRestaurantIs.equalsIgnoreCase("3"))return new MacDonalds();
        else return null;
    }

}
