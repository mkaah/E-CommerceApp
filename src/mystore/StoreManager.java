package mystore;

import java.util.ArrayList;

/**
 *  A mystore.StoreManager
 *  @author Dhriti Aravind 101141942, Mika Le 101141818
 *  @version 2.0 updated by Mika Le
 */

public class StoreManager{
    private Inventory inventory;
    private ArrayList<ShoppingCart> carts;
    private static int cartID = 0;

    /**
     * Constructor for a mystore.StoreManager
     */
    public StoreManager(){
        this.inventory = new Inventory();
        this.carts = new ArrayList<ShoppingCart>();
    }

    /**
     * This method gives access to the inventory
     * @return mystore.Inventory, the inventory of the mystore.StoreManager
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * This method gives access to a mystore.ShoppingCart
     * @param id int, the id of the cart
     * @return   mystore.ShoppingCart, the cart associated with the id
     */
    public ShoppingCart getCart(int id) {
        return carts.get(id - 1);
    }

    /**
     * Creates a unique cart ID, creates a mystore.ShoppingCart with that id
     * and adds it to the list of carts
     * @return int, unique cart id
     */
    public int assignCartID(){
        cartID++;
        carts.add(new ShoppingCart(cartID));
        return cartID;
    }

    /**
     * This method gives access to the stock of a product within the inventory
     * @param id int, id of the product
     * @return   int, stock of the product
     */
    public int getInvStock(int id) {
        return this.inventory.getStock(id);
    }

    /**
     * This method gives access to the stock of a product in the cart
     * @param cart mystore.ShoppingCart, the cart
     * @param id   int, id of the product
     * @return     int, stock of the product
     */
    public int getCartStock(ShoppingCart cart, int id){
        return cart.getStock(id);
    }

    /**
     * Adds a product to the cart
     * @param cart      mystore.ShoppingCart, the cart to add to
     * @param id        int, id of the product
     * @param quantity  int, amount of stock to add
     */
    public void addToCart(ShoppingCart cart, int id, int quantity){
        if(getInvStock(id) >= quantity){
            cart.addStock(inventory.getProduct(id), quantity);
            inventory.delStock(id, quantity);
        }
        else {
            System.out.println("Not enough stock available to add to cart");
        }
    }

    /**
     * Removes a product from the cart
     * @param cart      mystore.ShoppingCart, the cart to remove from
     * @param id        int, id of the product
     * @param quantity  int, amount of stock to remove
     */
    public void delFromCart(ShoppingCart cart, int id, int quantity){
        if(getCartStock(cart, id) >= quantity){
            cart.delStock(id, quantity);
            inventory.addStock(inventory.getProduct(id), quantity);
        }
        else {
            System.out.println("Not enough stock available to remove from cart");
        }
    }

    /**
     * Calculates the total for the products within the cart,
     * prints out a summary of items in the cart, and empties the cart
     * @param cart mystore.ShoppingCart, the cart to checkout
     * @return     double, total for the products in the cart
     */
    public double checkout(ShoppingCart cart){
        double total = 0;
        ArrayList<Product> products = cart.getProductList(); //need this.cart??? need cart as param??
        ArrayList<Integer> stock = cart.getStockList();
        String[][] infoCart = getCartInfo(cart);

        //calculate total
        for(int i = 0; i < products.size(); i++){
            total += products.get(i).getPrice() * stock.get(i);
        }
        total = Math.round(total * 100.0)/100.0;

        //print summary of cart
        System.out.println("YOUR CART:");
        for(int i = 0; i < infoCart.length; i++){
            System.out.println(infoCart[i][0] + " | $" + infoCart[i][1] + " | x" + infoCart[i][2]);
        }
        System.out.println("TOTAL: $" + total + " CAD");


        return total;
    }


    /**
     * Gives access to products in the cart. For each product, the
     * name, price, stock, and id are provided
     * @param cart mystore.ShoppingCart, the cart
     * @return     String[][], all the information on each product
     */
    public String[][] getCartInfo(ShoppingCart cart){
        ArrayList<Product> products = cart.getProductList();
        ArrayList<Integer> stock = cart.getStockList();

        String[][] info = new String[products.size()][4];
        for(int i = 0; i < products.size(); i++){
            info[i][0] = products.get(i).getName();
            info[i][1] = Double.toString(products.get(i).getPrice());
            info[i][2] = Integer.toString(stock.get(i));
            info[i][3] = Integer.toString(products.get(i).getId());
        }
        return info;
    }

    /**
     * Gives access to products in the inventory. For each product, the
     * name, price, stock, and id are provided
     * @return String[][], all the information on each product
     */
    public String[][] getInventoryInfo(){
        ArrayList<Product> products = this.inventory.getProductList();
        ArrayList<Integer> stock = this.inventory.getStockList();

        String[][] info = new String[products.size()][stock.size() + 1];

        for(int i = 0; i < products.size(); i++){
            info[i][0] = products.get(i).getName();
            info[i][1] = Double.toString(products.get(i).getPrice());
            info[i][2] = Integer.toString(stock.get(i));
            info[i][3] = Integer.toString(products.get(i).getId());
        }
        return info;
    }

}


