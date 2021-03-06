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

    /**
     * Gets the available products in the Inventory
     * @return      ArrayList, products available in Inventory
     */
    public ArrayList<Product> getAvailableProducts() {
        return inventory.getProductList();
    }

    /**
     * Gets the stock of a product in the inventory
     * @param p     Product, the product which we want to know the stock
     * @return      int, the amount of stock based on the product
     */
    public int getInvProdStock(Product p){
        return inventory.getProductQuantity(p);
    }

    /**
     * Gets the contents of a ShoppingCart
     * @param cart  ShoppingCart, the shopping cart we want to analyse
     * @return      ArrayList, the list of products in the shopping cart
     */
    public ArrayList<Product> getCartContents(ShoppingCart cart){
        return cart.getProductList();
    }

    /**
     * Gets the amount of a product in the ShoppingCart
     * @param p     Product, the product in the shopping cart
     * @param cart  ShoppingCart, the cart we want to see in
     * @return      int, the amount of stock based on the product in the cart
     */
    public int getCartProdStock(ShoppingCart cart, Product p){
        return cart.getProductQuantity(p);
    }

}


