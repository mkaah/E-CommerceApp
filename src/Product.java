/* Product class. Has accessor methods for all attributes.
 * Authors: Dhriti Aravind 101141942, Mika Le 101141818
 * Last edited: February 9, 2021
 */

public class Product {
    private int hello;
    private final String NAME;
    private final int ID;
    private final double PRICE;

    public Product() {
        this("",0,0);
    }

    public Product(String name, int id, double price) {
        NAME = name;
        ID= id;
        PRICE = price;
    }

    public String getName() {
        return NAME;
    }

    public int getId() {
        return ID;
    }

    public double getPrice() {
        return PRICE;
    }
}
