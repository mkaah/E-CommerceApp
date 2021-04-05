package myStore;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.lang.Math;
import java.text.DecimalFormat;

/**
 * A StoreView class to manage the UI of the store.
 *
 * @author Dhriti Aravind 101141942, Mika Le 101141818
 * @version 2.0
 */
public class StoreView {
    private StoreManager store;
    private ShoppingCart cart;
    private static StoreView[] users;

    /**
     * Constructor for StoreView
     * @param store     StoreManager, current store manager
     * @param cartID    int, id number of current cart
     */
    public StoreView(StoreManager store, int cartID){
        this.store = store;
        this.cart = store.getCart(cartID);
    }

    /**
     * This method gives access to the cartID
     * @return int, cartID of the StoreView's cart
     */
    private int getCartID(){
        return this.cart.getId();
    }

    private static JButton cartButton() {
        JButton button = new JButton("Cart");

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

            }
        });
        return button;
    }

    private static JButton quitButton() {
        JButton button = new JButton("Quit");

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

            }
        });
        return button;
    }

    private static JButton checkoutButton() {
        JButton button = new JButton("Checkout");

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

            }
        });
        return button;
    }

    private JButton addToCartButton() {
        JButton button = new JButton("+");

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

            }
        });
        return button;
    }

    private JButton removeFromCartButton() {
        JButton button = new JButton("-");

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

            }
        });
        return button;
    }

    /**
     * This method creates and returns a list of JPanels representing
     * a product in the store. Each JPanel displays information about a
     * product and has buttons to add and remove the product from the cart.
     * @return JPanel[], list of JPanels representing a product
     */
    private JPanel[] getProductPanels(){
        JPanel[] productPanels = new JPanel[store.getInventoryInfo().length];

        for(int i = 0; i < store.getInventoryInfo().length; i++){
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(new Color(230, 255, 219));
            panel.setMinimumSize(new Dimension(300,500));

            JPanel infoPanel = new JPanel(new BorderLayout());
            infoPanel.setBackground(new Color(230, 255, 219));
            JLabel nameLabel = new JLabel(store.getInventoryInfo()[i][0]);
            JLabel infoLabel = new JLabel("($"+store.getInventoryInfo()[i][1] + ") - Stock: " + store.getInventoryInfo()[i][2]);
            infoPanel.add(nameLabel, BorderLayout.PAGE_START);
            infoPanel.add(infoLabel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setBorder(new EmptyBorder(0,0,10,0));
            buttonPanel.add(addToCartButton());
            buttonPanel.add(removeFromCartButton());

            Image image = null;
            try {
                image = ImageIO.read(new File("./src/myStore/images/"+ store.getInventoryInfo()[i][0]+".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            image = image.getScaledInstance(200,200, Image.SCALE_DEFAULT);
            JLabel imageLabel = new JLabel(new ImageIcon(image));

            panel.add(infoPanel, BorderLayout.NORTH);
            panel.add(imageLabel, BorderLayout.CENTER);
            panel.add(buttonPanel, BorderLayout.SOUTH);

            productPanels[i] = panel;
        }
        return productPanels;
    }

    /**
     * This method displays a confirmation prompt when user
     * tries to quit the window
     */
    private static void windowExit(JFrame frame){
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?")
                        == JOptionPane.OK_OPTION) {
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });
    }

    /**
     * This method prompts the user to select a cart and
     * brings them to the store when a valid input is entered
     */
    public static void selectCart(){
        JFrame frame = new JFrame();
        frame.setTitle("D&M Grocery Store");
        frame.setSize(new Dimension(400,200));

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel headerPanel = new JPanel();
        JPanel bodyPanel = new JPanel();

        //header
        JLabel headerLabel = new JLabel("SELECT YOUR CART");
        headerPanel.add(headerLabel);

        //body
        NumberFormatter inputFormat = new NumberFormatter(new DecimalFormat("####"));
        inputFormat.setValueClass(Integer.class);
        inputFormat.setAllowsInvalid(false);
        inputFormat.setMinimum(1);
        inputFormat.setMaximum(9999);
        JFormattedTextField input = new JFormattedTextField(inputFormat);
        input.setColumns(3);

        //TODO: cannot delete last char in text field

        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int cartID = Integer.parseInt(input.getText());
                for(StoreView currentUser : users){
                    if(currentUser.getCartID() == cartID){
                        displayStore(currentUser);
                        frame.setVisible(false);
                        frame.dispose();
                        break;
                    }
                }
                if(cartID > users.length){
                    JOptionPane.showMessageDialog(frame, "Invalid ID");
                }
            }
        });

        bodyPanel.add(input);

        mainPanel.add(headerPanel, BorderLayout.PAGE_START);
        mainPanel.add(bodyPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
        windowExit(frame);
        frame.setVisible(true);
    }

    /**
     * This is the main store GUI. It allows users to browse the store,
     * add and remove items from their cart, view their cart, checkout, and quit
     * @param storeview StoreView, the StoreView that was selected in the previous prompt
     */
    public static void displayStore(StoreView storeview){
        JFrame frame = new JFrame();
        frame.setTitle("D&M Grocery Store");
        frame.setPreferredSize(new Dimension(800,800));
        frame.setMinimumSize(new Dimension(600,500));

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel headerPanel = new JPanel(new FlowLayout());
        int numRows = (int)(Math.ceil(storeview.store.getInventoryInfo().length/2.0));
        JPanel bodyPanel = new JPanel(new GridLayout(numRows,2));
        JPanel footerPanel = new JPanel();

        //header
        JLabel headerLabel = new JLabel("Welcome to D&M Grocery Store (ID: " + storeview.getCartID() + ")");
        headerPanel.add(headerLabel);
        headerPanel.add(cartButton());
        headerPanel.add(quitButton());

        //body
        JPanel[] productPanels = storeview.getProductPanels();
        for(int i = 0; i < productPanels.length; i++){
            bodyPanel.add(productPanels[i]);
        }
        JScrollPane scrollPane = new JScrollPane(bodyPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        //footer
        footerPanel.add(checkoutButton());

        //add panels to main panel
        mainPanel.add(headerPanel, BorderLayout.PAGE_START);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.PAGE_END);


        frame.add(mainPanel);
        frame.pack();
        windowExit(frame);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        StoreManager sm = new StoreManager();
        StoreView sv1 = new StoreView(sm, sm.assignCartID());
        StoreView sv2 = new StoreView(sm, sm.assignCartID());
        StoreView sv3 = new StoreView(sm, sm.assignCartID());

        users = new StoreView[]{sv1, sv2, sv3};

        selectCart();
    }

}


//package myStore;
//
//import javax.swing.*;
//import java.util.Scanner;
//
///**
// * A StoreView class to manage the UI of the store.
// *
// * @author Dhriti Aravind 101141942
// * @version 1.0
// */
//public class StoreView {
//    private StoreManager store;
//    private ShoppingCart cart;
//    private boolean done = false;
//
//    /**
//     * Constructor for store view
//     * @param store     StoreManager, current store manager
//     * @param cartID    int, id number of current cart
//     */
//    public StoreView(StoreManager store, int cartID) {
//        this.store = store;
//        this.cart = store.getCart(cartID);
//    }
//
//    /**
//     * Prints out the help menu on the console
//     */
//    private void helpCommands() {
//        System.out.println("\nHELP >>>");
//        System.out.println("Type the appropriate commands into the command line \n");
//        System.out.println("To add items to cart: 'Add to cart'");
//        System.out.println("To remove items from cart: 'Remove from cart'");
//        System.out.println("View items in cart: 'Current cart'");
//        System.out.println("View items in inventory: 'Current Inventory'");
//        System.out.println("Switch Storeview: 'Switch Cart'");
//        System.out.println("Checkout items: 'Checkout'");
//        System.out.println("Quit Program: 'Quit'");
//        System.out.println("To see this menu again: 'Help'");
//    }
//
//    /**
//     * Displays the current inventory with amounts and prices
//     */
//    private void getInventory() {
//        System.out.println("\n|--------------- THE COURSE STORE ---------------|");
//        System.out.println("/--------------- INVENTORY --------------/");
//        System.out.println("Type 'help' for a list of commands. \n");
//        System.out.println("Stock | Name | Price");
//        String[][] info = store.getInventoryInfo();
//        for (String[] strings : info) {
//            System.out.println(strings[2] + " | " + strings[0] + " | $" + strings[1]);
//        }
//        System.out.println("Enter a command...");
//        mainMenu(stringInput());
//    }
//
//    /**
//     * Adds the specified item and amount to the cart
//     * @param cart      ShoppingCart, the current cart being used
//     */
//    private void addItem(ShoppingCart cart) {
//        System.out.println("\n|--------------- THE COURSE STORE ---------------|");
//        System.out.println("/--------------- ADD TO CART ---------------/");
//        System.out.println("Type 'help' for a list of commands. \n");
//        System.out.println("Stock | Name | Price | Option");
//        String[][] info = store.getInventoryInfo();
//        for(int i = 0; i < info.length; i++){
//            System.out.println(info[i][2] + " | " + info[i][0] + " | $" + info[i][1] +" | ("+ i+ ")");
//        }
//        System.out.println("Enter the product you would like to add to cart:");
//        int option = intInput(info.length - 1);
//        System.out.println("Enter the amount you would like to add to cart:");
//
//        store.addToCart(cart, Integer.parseInt(info[option][3]), intInput(Integer.parseInt(info[option][2])));
//        System.out.println("/--------------- END OF ADD TO CART ---------------/\n");
//    }
//
//    /**
//     * Removes the specified item and amount from the cart
//     * @param cart      ShoppingCart, the current cart being used
//     */
//    private void removeItem(ShoppingCart cart) {
//        System.out.println("\n|--------------- THE COURSE STORE ---------------|");
//        System.out.println("/--------------- REMOVE FROM CART ---------------/");
//        System.out.println("Type 'help' for a list of commands. \n");
//        System.out.println("Stock | Name | Price | Option");
//        String[][] info = store.getCartInfo(cart);
//        for(int i = 0; i < info.length; i++){
//            System.out.println(info[i][2] + " | " + info[i][0] + " | $" + info[i][1] +" | ("+ i+ ")");
//        }
//        System.out.println("Enter the product you would like to remove from your cart:");
//        int option = intInput(info.length - 1);
//        System.out.println("Enter the amount you would like to remove from your cart:");
//        store.delFromCart(cart, Integer.parseInt(info[option][3]), intInput(Integer.parseInt(info[option][2])));
//        System.out.println("/--------------- END OF REMOVE FROM CART ---------------/\n");
//    }
//
//    /**
//     * Returns all items to shelf if the user does not checkout.
//     * @param cart      ShoppingCart, the current cart being used
//     */
//    private void quitView(ShoppingCart cart) {
//        String[][] info = store.getCartInfo(cart);
//
//        for(int i = 0; i < info.length; i++){
//            store.delFromCart(cart, Integer.parseInt(info[i][3]), Integer.parseInt(info[i][2]));
//        }
//    }
//
//    /**
//     * Displays the current contents of the cart
//     * @param cart      ShoppingCart, the current cart being used
//     */
//    private void getCart(ShoppingCart cart) {
//        System.out.println("\n|--------------- THE COURSE STORE ---------------|");
//        System.out.println("/--------------- CURRENT CART ---------------/");
//        System.out.println("\n Stock | Name | Price");
//        String[][] info = store.getCartInfo(cart);
//        for(int i = 0; i < info.length; i++){
//            System.out.println(info[i][2] + " | " + info[i][0] + " | $" + info[i][1]);
//        }
//        System.out.println("-/--------------- END OF CURRENT CART ---------------/\n");
//    }
//
//    /**
//     * Checks if the user input is a string input and removes spaces and
//     * sets everything to lowercase.
//     *
//     * @return      String, user string, lowercase and without spaces
//     */
//    private String stringInput() {
//        System.out.print("\n>>> ");
//        Scanner scanner = new Scanner(System.in);
//        String s = scanner.nextLine().toLowerCase();
//        s = s.replaceAll("\\s+","");
//        return s;
//    }
//
//    /**
//     * Checks if the user input is an int and between the ranges of 0 and maximum
//     * @param maximum      int, the maximum value the int can be
//     * @return             int, user value between 0 and maximum
//     */
//    private static int intInput(int maximum) {
//        do {
//            Scanner scanner = new Scanner(System.in);
//            System.out.print("\n>>> ");
//            if (scanner.hasNextInt()) {
//                int n = scanner.nextInt();
//                if (n <= maximum && n >= 0) {
//                    return n;
//                } else {
//                    System.out.println("Please enter a valid numerical input between the appropriate range");
//                }
//            }
//            else {
//                System.out.println("Please enter a valid numerical input");
//            }
//        }while (true);
//    }
//
//
//    /**
//     * Directs the user to the appropriate screen for their task
//     *
//     * @param s     String, command entered by user
//     */
//    public void mainMenu(String s) {
//        switch(s) {
//            case("help"):
//                //print all commands
//                helpCommands();
//                mainMenu(stringInput());
//                break;
//            case("addtocart"):
//                //add to cart
//                addItem(cart);
//                getInventory();
//                break;
//            case ("removefromcart"):
//                //remove from cart
//                removeItem(cart);
//                getInventory();
//                break;
//            case("checkout"):
//                //checkout
//                System.out.println("YOUR CART:");
//                System.out.println("Stock | Name | Price");
//                for(int i = 0; i < store.getCartInfo(cart).length; i++){
//                    System.out.println(store.getCartInfo(cart)[i][2] + " | " + store.getCartInfo(cart)[i][0] + " | " + store.getCartInfo(cart)[i][1]);
//                }
//                System.out.println("TOTAL: $" + completePurchase(cart) + " CAD");
//                done = true;
//                break;
//            case("currentcart"):
//                //print current cart
//                getCart(cart);
//                getInventory();
//                break;
//            case("inventory"):
//                //print current inventory
//                getInventory();
//                break;
//            case("switchview"):
//                break;
//            case("quit"):
//                //end program
//                quitView(cart);
//                System.out.println("Quit current cart");
//                done = true;
//                break;
//            default:
//                //none of the above work
//                System.out.println("Invalid input. Please try again. \nType: 'Help' for a full list of options");
//                mainMenu(stringInput());
//                break;
//        }
//    }
//
//    /**
//     * Completes the purchase based on the items in the cart
//     *
//     * @param cart      ShoppingCart, the current cart being used
//     * @return          double, total in dollars
//     */
//    private double completePurchase(ShoppingCart cart) {
//        return store.checkout(cart);
//    }
//
//    /**
//     *  Ensures selected storeview is valid
//     *
//     * @param m     int, number of users
//     * @return      int, user selected input
//     */
//    public static int chooseStoreview(int m) {
//        System.out.print("CHOOSE YOUR STOREVIEW: ");
//
//
//        return intInput(m);
//    }
//
//
//
//    public static void main(String[] args) {
//        StoreManager sm = new StoreManager();
//        StoreView sv1 = new StoreView(sm, sm.assignCartID());
//        StoreView sv2 = new StoreView(sm, sm.assignCartID());
//        StoreView sv3 = new StoreView(sm, sm.assignCartID());
//
//        StoreView[] users = {sv1, sv2, sv3};
//        int activeSV = users.length;
//
//        Scanner scanner = new Scanner(System.in);
//        String newView = "";
//        while (activeSV > 0 && (newView.equals("") || newView.equals("y")||newView.equals("Y"))) {
//            int choice = chooseStoreview(activeSV-1);
//            if (users[choice] != null) {
//                System.out.print("CART >>> "+choice);
//                users[choice].getInventory();
//                if(users[choice].done) {
//                    users[choice] = null;
//                    activeSV--;
//                }
//            } else {
//                System.out.println("MAIN > ERROR > BAD CHOICE\nTHAT STOREVIEW WAS DEACTIVATED");
//            }
//            System.out.print("GO TO ANOTHER STOREVIEW? (y) >>> ");
//            newView = scanner.nextLine();
//        }
//        System.out.println("ALL STOREVIEWS DEACTIVATED");
//        System.exit(0);
//    }
//
//
//}
