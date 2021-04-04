package mystore;

import javax.swing.*;
import java.util.Scanner;

/**
 * A StoreView class to manage the UI of the store.
 *
 * @author Dhriti Aravind 101141942
 * @version 1.0
 */
public class StoreView {
    private StoreManager store;
    private ShoppingCart cart;
    private boolean done = false;

    /**
     * Constructor for store view
     * @param store     StoreManager, current store manager
     * @param cartID    int, id number of current cart
     */
    public StoreView(StoreManager store, int cartID) {
        this.store = store;
        this.cart = store.getCart(cartID);
    }

    /**
     * Prints out the help menu on the console
     */
    private void helpCommands() {
        System.out.println("\nHELP >>>");
        System.out.println("Type the appropriate commands into the command line \n");
        System.out.println("To add items to cart: 'Add to cart'");
        System.out.println("To remove items from cart: 'Remove from cart'");
        System.out.println("View items in cart: 'Current cart'");
        System.out.println("View items in inventory: 'Current Inventory'");
        System.out.println("Switch Storeview: 'Switch Cart'");
        System.out.println("Checkout items: 'Checkout'");
        System.out.println("Quit Program: 'Quit'");
        System.out.println("To see this menu again: 'Help'");
    }

    /**
     * Displays the current inventory with amounts and prices
     */
    private void getInventory() {
        System.out.println("\n|--------------- THE COURSE STORE ---------------|");
        System.out.println("/--------------- INVENTORY --------------/");
        System.out.println("Type 'help' for a list of commands. \n");
        System.out.println("Stock | Name | Price");
        String[][] info = store.getInventoryInfo();
        for (String[] strings : info) {
            System.out.println(strings[2] + " | " + strings[0] + " | $" + strings[1]);
        }
        System.out.println("Enter a command...");
        mainMenu(stringInput());
    }

    /**
     * Adds the specified item and amount to the cart
     * @param cart      ShoppingCart, the current cart being used
     */
    private void addItem(ShoppingCart cart) {
        System.out.println("\n|--------------- THE COURSE STORE ---------------|");
        System.out.println("/--------------- ADD TO CART ---------------/");
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
        System.out.println("/--------------- END OF ADD TO CART ---------------/\n");
    }

    /**
     * Removes the specified item and amount from the cart
     * @param cart      ShoppingCart, the current cart being used
     */
    private void removeItem(ShoppingCart cart) {
        System.out.println("\n|--------------- THE COURSE STORE ---------------|");
        System.out.println("/--------------- REMOVE FROM CART ---------------/");
        System.out.println("Type 'help' for a list of commands. \n");
        System.out.println("Stock | Name | Price | Option");
        String[][] info = store.getCartInfo(cart);
        for(int i = 0; i < info.length; i++){
            System.out.println(info[i][2] + " | " + info[i][0] + " | $" + info[i][1] +" | ("+ i+ ")");
        }
        System.out.println("Enter the product you would like to remove from your cart:");
        int option = intInput(info.length - 1);
        System.out.println("Enter the amount you would like to remove from your cart:");
        store.delFromCart(cart, Integer.parseInt(info[option][3]), intInput(Integer.parseInt(info[option][2])));
        System.out.println("/--------------- END OF REMOVE FROM CART ---------------/\n");
    }

    /**
     * Returns all items to shelf if the user does not checkout.
     * @param cart      ShoppingCart, the current cart being used
     */
    private void quitView(ShoppingCart cart) {
        String[][] info = store.getCartInfo(cart);

        for(int i = 0; i < info.length; i++){
            store.delFromCart(cart, Integer.parseInt(info[i][3]), Integer.parseInt(info[i][2]));
        }
    }

    /**
     * Displays the current contents of the cart
     * @param cart      ShoppingCart, the current cart being used
     */
    private void getCart(ShoppingCart cart) {
        System.out.println("\n|--------------- THE COURSE STORE ---------------|");
        System.out.println("/--------------- CURRENT CART ---------------/");
        System.out.println("\n Stock | Name | Price");
        String[][] info = store.getCartInfo(cart);
        for(int i = 0; i < info.length; i++){
            System.out.println(info[i][2] + " | " + info[i][0] + " | $" + info[i][1]);
        }
        System.out.println("-/--------------- END OF CURRENT CART ---------------/\n");
    }

    /**
     * Checks if the user input is a string input and removes spaces and
     * sets everything to lowercase.
     *
     * @return      String, user string, lowercase and without spaces
     */
    private String stringInput() {
        System.out.print("\n>>> ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine().toLowerCase();
        s = s.replaceAll("\\s+","");
        return s;
    }

    /**
     * Checks if the user input is an int and between the ranges of 0 and maximum
     * @param maximum      int, the maximum value the int can be
     * @return             int, user value between 0 and maximum
     */
    private static int intInput(int maximum) {
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\n>>> ");
            if (scanner.hasNextInt()) {
                int n = scanner.nextInt();
                if (n <= maximum && n >= 0) {
                    return n;
                } else {
                    System.out.println("Please enter a valid numerical input between the appropriate range");
                }
            }
            else {
                System.out.println("Please enter a valid numerical input");
            }
        }while (true);
    }


    /**
     * Directs the user to the appropriate screen for their task
     *
     * @param s     String, command entered by user
     */
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
                System.out.println("YOUR CART:");
                System.out.println("Stock | Name | Price");
                for(int i = 0; i < store.getCartInfo(cart).length; i++){
                    System.out.println(store.getCartInfo(cart)[i][2] + " | " + store.getCartInfo(cart)[i][0] + " | " + store.getCartInfo(cart)[i][1]);
                }
                System.out.println("TOTAL: $" + completePurchase(cart) + " CAD");
                done = true;
                break;
            case("currentcart"):
                //print current cart
                getCart(cart);
                getInventory();
                break;
            case("inventory"):
                //print current inventory
                getInventory();
                break;
            case("switchview"):
                break;
            case("quit"):
                //end program
                quitView(cart);
                System.out.println("Quit current cart");
                done = true;
                break;
            default:
                //none of the above work
                System.out.println("Invalid input. Please try again. \nType: 'Help' for a full list of options");
                mainMenu(stringInput());
                break;
        }
    }

    /**
     * Completes the purchase based on the items in the cart
     *
     * @param cart      ShoppingCart, the current cart being used
     * @return          double, total in dollars
     */
    private double completePurchase(ShoppingCart cart) {
        return store.checkout(cart);
    }

    /**
     *  Ensures selected storeview is valid
     *
     * @param m     int, number of users
     * @return      int, user selected input
     */
    public static int chooseStoreview(int m) {
        System.out.print("CHOOSE YOUR STOREVIEW: ");


        return intInput(m);
    }



    public static void main(String[] args) {
        StoreManager sm = new StoreManager();
        StoreView sv1 = new StoreView(sm, sm.assignCartID());
        StoreView sv2 = new StoreView(sm, sm.assignCartID());
        StoreView sv3 = new StoreView(sm, sm.assignCartID());

        StoreView[] users = {sv1, sv2, sv3};
        int activeSV = users.length;

        Scanner scanner = new Scanner(System.in);
        String newView = "";
        while (activeSV > 0 && (newView.equals("") || newView.equals("y")||newView.equals("Y"))) {
            int choice = chooseStoreview(activeSV-1);
            if (users[choice] != null) {
                System.out.print("CART >>> "+choice);
                users[choice].getInventory();
                if(users[choice].done) {
                    users[choice] = null;
                    activeSV--;
                }
            } else {
                System.out.println("MAIN > ERROR > BAD CHOICE\nTHAT STOREVIEW WAS DEACTIVATED");
            }
            System.out.print("GO TO ANOTHER STOREVIEW? (y) >>> ");
            newView = scanner.nextLine();
        }
        System.out.println("ALL STOREVIEWS DEACTIVATED");
        System.exit(0);
    }


}
