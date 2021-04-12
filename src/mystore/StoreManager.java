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

//    /**
//     * This method gives access to the inventory
//     * @return Inventory, the inventory of the StoreManager
//     */
//    public Inventory getInventory() {
//        return this.inventory;
//    }

    /**
     * This method gives access to a ShoppingCart
     * @param id int, the id of the cart
     * @return   ShoppingCart, the cart associated with the id
     */
    public ShoppingCart getCart(int id) {
        return carts.get(id - 1);
    }

//    /**
//     * This method gives access to the list of ShoppingCart
//     * @return ArrayList<ShoppingCart>, list of all carts being managed
//     */
//    public ArrayList<ShoppingCart> getCarts() { return this.carts; }

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

//    /**
//     * This method gives access to the stock of a product within the inventory
//     * @param id int, id of the product
//     * @return   int, stock of the product
//     */
//    public int getInvStock(int id) {
//        return this.inventory.getStock(id);
//    }
//
//    /**
//     * This method gives access to the stock of a product in the cart
//     * @param cart ShoppingCart, the cart
//     * @param id   int, id of the product
//     * @return     int, stock of the product
//     */
//    public int getCartStock(ShoppingCart cart, int id){
//        return cart.getStock(id);
//    }

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

//    /**
//     * Gives access to products in the cart. For each product, the
//     * name, price, stock, and id are provided
//     * @param cart ShoppingCart, the cart
//     * @return     String[][], all the information on each product
//     */
//    public String[][] getCartInfo(ShoppingCart cart){
//        ArrayList<Product> products = cart.getProductList();
//        ArrayList<Integer> stock = cart.getStockList();
//
//        String[][] info = new String[products.size()][4];
//        for(int i = 0; i < products.size(); i++){
//            info[i][0] = products.get(i).getName();
//            info[i][1] = Double.toString(products.get(i).getPrice());
//            info[i][2] = Integer.toString(stock.get(i));
//            info[i][3] = Integer.toString(products.get(i).getId());
//        }
//        return info;
//    }
//
//    /**
//     * Gives access to products in the inventory. For each product, the
//     * name, price, stock, and id are provided
//     * @return String[][], all the information on each product
//     */
//    public String[][] getInventoryInfo(){
//        ArrayList<Product> products = this.inventory.getProductList();
//        ArrayList<Integer> stock = this.inventory.getStockList();
//
//        String[][] info = new String[products.size()][4];
//
//        for(int i = 0; i < products.size(); i++){
//            info[i][0] = products.get(i).getName();
//            info[i][1] = Double.toString(products.get(i).getPrice());
//            info[i][2] = Integer.toString(stock.get(i));
//            info[i][3] = Integer.toString(products.get(i).getId());
//        }
//        return info;
//    }

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


