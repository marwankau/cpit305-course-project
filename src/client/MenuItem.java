package client;

public class MenuItem {
    private int id;
    private String product;
    private float price;

    public MenuItem(int id, String product, float price) {
        this.id = id;
        this.product = product;
        this.price = price;
    }








    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
