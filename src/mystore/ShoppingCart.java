package mystore;

/**
 * A mystore.ShoppingCart
 * @author Mika Le 101141818
 * @version 1.0
 */

public class ShoppingCart extends Inventory{
    private int id;
    /**
     * Constructor for a mystore.ShoppingCart
     * @param id int, id of the cart
     */
    public ShoppingCart(int id){
        super();
        this.id = id;
    }

    /**
     * This method gives access to the id of the cart
     * @return int, id of the cart
     */
    public int getId() {
        return id;
    }
}
