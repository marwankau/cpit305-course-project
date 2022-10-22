public class Customer {

    private String name;
    private String id;
    private String home;
    private String email;
    private String orderDate;
    private double amountPaid;
    private int resturantName;

    public Customer(String a, String b, String c, String d){
        name = a;
        id = b;
        home = c;
        email = d;              
    }

    public String getName(){
        return name;
    }
    
    public String getID(){
        return id;
    }
    
    public String getHome(){
        return home;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getOrderDate(){
        return orderDate;
    }
    
    public double getAmountPaid(){
        return amountPaid;
    }
    
    public int getRestaurantName(){
        return getRestaurantName();
    }
}
