import java.util.ArrayList;

/**
 *  Store Manager class for managing the inventory
 *  @author Dhriti Aravind 101141942, Mika Le 101141818
 *  @version 2.0 updated by Mika Le
 */

public class StoreManager{
    private Inventory inventory;
    private ShoppingCart cart;
    private static int cartID = 0;

    public StoreManager(){
        this.inventory = new Inventory();
        this.cart = new ShoppingCart();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public int assignCartID(){
        return cartID++;
    }

    public int getInvStock(int id) {
        return inventory.getStock(id);
    }

    public int getCartStock(int id){
        return cart.getStock(id);
    }

    public void addToCart(int id, int quantity){
        if(getInvStock(id) >= quantity){
            cart.addStock(inventory.getProduct(id), quantity);
            inventory.delStock(id, quantity);
        }
        else {
            System.out.println("Not enough stock available to add to cart");
        }
    }

    public void delFromCart(int id, int quantity){
        if(getCartStock(id) >= quantity){
            cart.delStock(id, quantity);
            inventory.addStock(inventory.getProduct(id), quantity);
        }
        else {
            System.out.println("Not enough stock available to remove from cart");
        }
    }

    public double checkout(){
        double total = 0;
        return total;
    }

    public String[][] getCartInfo(ShoppingCart cart){
        ArrayList<Product> products = cart.getProductList();
        ArrayList<Integer> stock = cart.getStockList();

        String[][] info = new String[products.size()][stock.size()];
        for(int i = 0; i < products.size(); i++){
            info[i][0] = products.get(i).getName();
            info[i][1] = Double.toString(products.get(i).getPrice());
            info[i][2] = Integer.toString(stock.get(i));
        }
        return info;
    }

    public String[][] getInventoryInfo(){
        ArrayList<Product> products = this.inventory.getProductList();
        ArrayList<Integer> stock = this.inventory.getStockList();

        String[][] info = new String[products.size()][stock.size()];
        for(int i = 0; i < products.size(); i++){
            info[i][0] = products.get(i).getName();
            info[i][1] = Double.toString(products.get(i).getPrice());
            info[i][2] = Integer.toString(stock.get(i));
        }
        return info;
    }



//    public int checkStock(Product product) {
//        return inventory.getStock(product.getId());
//    }

//    public double transaction(int[][] cart) {
//        double total = 0;
//        //check desired quantity exists for all products
//        for (int[] item : cart) {
//            if (item[1] > inventory.getStock(item[0])) {
//                //insufficient supply
//                System.out.println("Insufficient supply. Transaction cancelled\n");
//                return -1;
//            }
//        }
//
//        //process transaction
//        for (int[] item : cart) {
//            inventory.delStock(item[0], item[1]);
//            total += inventory.getProduct(item[0]).getPrice() * item[1];
//        }
//
//        return Math.round(total * 100.0)/100.0;
//    }


}


