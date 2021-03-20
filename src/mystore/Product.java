package mystore;

/**
 * A Product
 * @author Dhriti Aravind 101141942, Mika Le 101141818
 * @version 2.0
 */
public class Product {
    private final String NAME;
    private final int ID;
    private final double PRICE;

    /**
     * Default constructor for a Product
     */
    public Product() {
        this("",0,0);
    }

    /**
     * Constructor for a Product
     * @param name  String, name of the product
     * @param id    int, the product ID
     * @param price double, the price of the product
     */
    public Product(String name, int id, double price) {
        NAME = name;
        ID= id;
        PRICE = price;
    }

    /**
     * This method provides access to the name of the product
     * @return String, name of the Product
     */
    public String getName() {
        return this.NAME;
    }

    /**
     * This method provides access to the ID of the product
     * @return int, id of the Product
     */
    public int getId() {
        return this.ID;
    }

    /**
     * This method provides access to the price of the product
     * @return double, price of the Product
     */
    public double getPrice() { return this.PRICE; }
}
