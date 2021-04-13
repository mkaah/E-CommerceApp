package myStore;

import java.util.ArrayList;

/**
 *  A StoreManager
 *  @author Dhriti Aravind 101141942, Mika Le 101141818
 *  @version 3.0 updated by Mika Le
 */

public class StoreManager{
    private Inventory inventory;
    private ArrayList<ShoppingCart> carts;
    private static int cartID = 0;

    /**
     * Constructor for a StoreManager
     */
    public StoreManager(){
        this.inventory = new Inventory();
        this.carts = new ArrayList<ShoppingCart>();
    }

    /**
     * This method gives access to a ShoppingCart
     * @param id int, the id of the cart
     * @return   ShoppingCart, the cart associated with the id
     */
    public ShoppingCart getCart(int id) {
        return carts.get(id - 1);
    }

    /**
     * Creates a unique cart ID, creates a ShoppingCart with that id
     * and adds it to the list of carts
     * @return int, unique cart id
     */
    public int assignCartID(){
        cartID++;
        carts.add(new ShoppingCart(cartID));
        return cartID;
    }

    /**
     * Adds a product to the cart
     * @param cart      ShoppingCart, the cart to add to
     * @param product   Product, the product to be removed
     * @param quantity  int, amount of stock to add
     */
    public void addToCart(ShoppingCart cart, Product product, int quantity){
        if(inventory.removeProductQuantity(product, quantity)){ //enough stock
            cart.addProductQuantity(product, quantity);
        }
    }

    /**
     * Removes a product from the cart
     * @param cart      ShoppingCart, the cart to remove from
     * @param product   Product, the product to be removed
     * @param quantity  int, amount of stock to remove
     */
    public void delFromCart(ShoppingCart cart, Product product, int quantity){
        if(cart.removeProductQuantity(product, quantity)){
            inventory.addProductQuantity(product, quantity);
        }
    }

    /**
     * Calculates the total for the products within the cart,
     * prints out a summary of items in the cart, and empties the cart
     * @param cart ShoppingCart, the cart to checkout
     * @return     double, total for the products in the cart
     */
    public double checkout(ShoppingCart cart){
        return cart.getTotal();
    }

    public ArrayList<Product> getAvailableProducts() {
        return inventory.getProductList();
    }

    public int geInvProdStock(Product p){
        return inventory.getProductQuantity(p);
    }

    public ArrayList<Product> getCartContents(ShoppingCart cart){
        return cart.getProductList();
    }

    public int getCartProdStock(Product p){
        return inventory.getProductQuantity(p);
    }

}


