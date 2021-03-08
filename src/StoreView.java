import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A StoreView
 * @author Dhriti Aravind 101141942
 * @version 1.0
 */
public class StoreView {
    private StoreManager store;
    private ShoppingCart cart;

    public StoreView(StoreManager store, int cartID) {
        this.store = store;
        this.cart = store.getCart(cartID);
    }

    private void helpCommands() {
        System.out.println("\nHELP >>>");
        System.out.println("Type the appropriate commands into the command line \n");
        System.out.println("To add items to cart: 'Add to cart'");
        System.out.println("To remove items from cart: 'Remove from cart'");
        System.out.println("View items in cart: 'Current cart'");
        System.out.println("View items in inventory: 'Current Inventory'");
        System.out.println("Checkout items: 'Checkout'");
        System.out.println("Quit Program: 'Quit'");
        System.out.println("To see this menu again: 'Help'\n");
    }

    private void getInventory() {
        System.out.println("\n--- THE COURSE STORE ---");
        System.out.println("--- INVENTORY ---");
        System.out.println("Type 'help' for a list of commands. \n");
        System.out.println("Stock | Name | Price");
        String[][] info = store.getInventoryInfo();
        for(int i = 0; i < info.length; i++){
            System.out.println(info[i][2] + " | " + info[i][0] + " | $" + info[i][1]);
        }
        mainMenu(stringInput());
    }

    private void addItem(ShoppingCart cart) {
        System.out.println("\n--- THE COURSE STORE ---");
        System.out.println("--- ADD TO CART ---");
        System.out.println("Type 'help' for a list of commands. \n");
        System.out.println("Stock | Name | Price | Option");
        String[][] info = store.getInventoryInfo();
        for(int i = 0; i < info.length; i++){
            System.out.println(info[i][2] + " | " + info[i][0] + " | $" + info[i][1] +" | ("+ i+ ")");
        }
        System.out.println("Enter the product you would like to add to cart:");
        int option = intInput(info.length - 1);
        System.out.println("Enter the amount you would like to add to cart:");

        store.addToCart(cart, Integer.parseInt(info[option][3]), intInput(Integer.parseInt(info[option][2])));
        System.out.println("--- END OF ADD TO CART ----\n");
    }

    private void removeItem(ShoppingCart cart) {
        System.out.println("\n--- THE COURSE STORE ---");
        System.out.println("--- REMOVE FROM CART ---");
        System.out.println("Type 'help' for a list of commands. \n");
        System.out.println("Stock | Name | Price | Option");
        String[][] info = store.getInventoryInfo();
        for(int i = 0; i < info.length; i++){
            System.out.println(info[i][2] + " | " + info[i][0] + " | $" + info[i][1] +" | ("+ i+ ")");
        }
        System.out.println("Enter the product you would like to add to cart:");
        int option = intInput(info.length - 1);
        System.out.println("Enter the amount you would like to add to cart:");

        store.delFromCart(cart, Integer.parseInt(info[option][3]), intInput(Integer.parseInt(info[option][2])));
        System.out.println("--- END OF REMOVE FROM CART ----\n");
    }

    private void getCart(ShoppingCart cart) {
        System.out.println("\n--- THE COURSE STORE ---");
        System.out.println("\n--- CURRENT CART ---");
        System.out.println("\n Stock | Name | Price");
        String[][] info = store.getCartInfo(cart);
        for(int i = 0; i < info.length; i++){
            System.out.println(info[i][2] + " | " + info[i][0] + " | $" + info[i][1]);
        }
        System.out.println("--- END OF CURRENT CART ----\n");
    }

    private String stringInput() {
        System.out.print(">>> ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine().toLowerCase();
        s = s.replaceAll("\\s+","");
        return s;
    }

    private int intInput(int maximum) {
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\n>>> ");
            if (scanner.hasNextInt()) {
                int n = scanner.nextInt();
                if (n <= maximum && n >= 0) {
                    return n;
                } else {
                    System.out.println("Please enter a valid numerical input");
                }
            }
            else {
                System.out.println("Please enter a valid numerical input");
            }
        }while (true);
    }



    public void mainMenu(String s) {
        switch(s) {
            case("help"):
                //print all commands
                helpCommands();
                mainMenu(stringInput());
                break;
            case("addtocart"):
                //add to cart
                addItem(cart);
                getInventory();
                break;
            case ("removefromcart"):
                //remove from cart
                removeItem(cart);
                getInventory();
                break;
            case("checkout"):
                //checkout
                System.out.println(completePurchase(cart));
                break;
            case("currentcart"):
                //print current cart
                getCart(cart);
                getInventory();
                break;
            case("currentinventory"):
                //print current inventory
                getInventory();
                break;
            case("quit"):
                //end program
                System.out.println("ALL STORE VIEWS DEACTIVATED");
                System.exit(0);
                break;
            default:
                //none of the above work
                System.out.println("Invalid input. Please try again. \nType: 'Help' for a full list of options");
                mainMenu(stringInput());
                break;
        }
    }


    public double completePurchase(ShoppingCart cartID) {
        return store.checkout(cartID);
    }

    public static void main(String[] args) {
        StoreManager sm = new StoreManager();
        StoreView sv1 = new StoreView(sm, sm.assignCartID());

        sm.getInventory().addStock(new Product("Apple", 1, 10.00), 10);
        sm.getInventory().addStock(new Product("Banana", 2, 20.00), 20);
        sm.getInventory().addStock(new Product("Orange", 3, 40.00), 30);


        //sv1.helpCommands();

        sv1.getInventory();





    }


}
