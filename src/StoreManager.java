import java.util.ArrayList;

/**
 *  Store Manager class for managing the inventory
 *  @author Dhriti Aravind 101141942, Mika Le 101141818
 *  @version 2.0 updated by Mika Le
 */

public class StoreManager{
    private Inventory inventory;
    private ArrayList<ShoppingCart> carts;
    private static int cartID = 0;

    public StoreManager(){
        this.inventory = new Inventory();
        carts = new ArrayList<ShoppingCart>();
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public ShoppingCart getCart(int id) {
        return carts.get(id - 1);
    }

    public int assignCartID(){
        cartID++;
        ShoppingCart newCart = new ShoppingCart(cartID);
        carts.add(newCart);
        return cartID;
    }

    public int getInvStock(int id) {
        return this.inventory.getStock(id);
    }

    public int getCartStock(ShoppingCart cart, int id){
        return cart.getStock(id);
    }

    public void addToCart(ShoppingCart cart, int id, int quantity){
        if(getInvStock(id) >= quantity){
            cart.addStock(inventory.getProduct(id), quantity);
            inventory.delStock(id, quantity);
            System.out.println("The items are added to cart.");
        }
        else {
            System.out.println("Not enough stock available to add to cart");
        }
    }

    public void delFromCart(ShoppingCart cart, int id, int quantity){
        if(getCartStock(cart, id) >= quantity){
            cart.delStock(id, quantity);
            inventory.addStock(inventory.getProduct(id), quantity);
        }
        else {
            System.out.println("Not enough stock available to remove from cart");
        }
    }

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

        //disconnect user or reset cart
        emptyCart(cart);

        return total;
    }

    private void emptyCart(ShoppingCart cart){
        ArrayList<Product> products = cart.getProductList();
        ArrayList<Integer> stock = cart.getStockList();

        for(Product p : products){
            products.remove(p);
        }

        for(Integer s : stock){
            stock.remove(s);
        }
    }

    public void returnProducts(ShoppingCart cart){
        ArrayList<Product> products = cart.getProductList();

        for(Product p : products){
            delFromCart(cart, p.getId(), getCartStock(cart, p.getId()));
        }
    }

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


