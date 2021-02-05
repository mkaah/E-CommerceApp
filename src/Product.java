//Dhriti Aravind
//Mika Le 101141818

public class Product {
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
