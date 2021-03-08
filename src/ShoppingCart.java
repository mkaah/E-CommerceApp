/**
 * A ShoppingCart
 * @author Mika Le 101141818
 */

public class ShoppingCart extends Inventory{
    private int id;
    /**
     * Constructor for a ShoppingCart
     */
    public ShoppingCart(){

    }
    public ShoppingCart(int id){
        super();
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
