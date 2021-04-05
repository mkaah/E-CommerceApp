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
    public int getCartID(){
        return this.cart.getId();
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

            JPanel buttonPanel = new JPanel();
            buttonPanel.setBorder(new EmptyBorder(0,0,10,0));

            JPanel infoPanel = new JPanel(new BorderLayout());
            infoPanel.setBackground(new Color(230, 255, 219));
            JLabel nameLabel = new JLabel(store.getInventoryInfo()[i][0]);
            JLabel infoLabel = new JLabel("($"+store.getInventoryInfo()[i][1] + ") - Stock: " + store.getInventoryInfo()[i][2]);
            infoPanel.add(nameLabel, BorderLayout.PAGE_START);
            infoPanel.add(infoLabel, BorderLayout.CENTER);

            JButton addButton = new JButton("+");
            JButton removeButton = new JButton("-");
            JLabel count = new JLabel("0");

            int finalI = i;
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    store.addToCart(cart, Integer.parseInt(store.getInventoryInfo()[finalI][3]), 1);
                    count.setText(String.valueOf(cart.getStock(Integer.parseInt(store.getInventoryInfo()[finalI][3]))));
                    infoLabel.setText("($"+store.getInventoryInfo()[finalI][1] + ") - Stock: " + store.getInventoryInfo()[finalI][2]);

                }
            });

            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    store.delFromCart(cart, Integer.parseInt(store.getInventoryInfo()[finalI][3]), 1);
                    if(cart.getStock(Integer.parseInt(store.getInventoryInfo()[finalI][3])) != -1) {
                        count.setText(String.valueOf(cart.getStock(Integer.parseInt(store.getInventoryInfo()[finalI][3]))));
                    }
                    infoLabel.setText("($"+store.getInventoryInfo()[finalI][1] + ") - Stock: " + store.getInventoryInfo()[finalI][2]);
                }
            });

            buttonPanel.add(addButton);
            buttonPanel.add(count);
            buttonPanel.add(removeButton);

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

        JComboBox<Integer> currentCarts = new JComboBox<Integer>();
        JButton b = new JButton("Ok");

        for(StoreView currentUser: users) {
            currentCarts.addItem(currentUser.getCartID());
        }

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for(StoreView currentU : users){
                    if(currentU.getCartID() == currentCarts.getItemAt(currentCarts.getSelectedIndex())) {
                        displayStore(currentU);
                        frame.setVisible(false);
                        frame.dispose();
                        break;
                    }
                }

            }
        });

        bodyPanel.add(currentCarts);
        bodyPanel.add(b);

        mainPanel.add(headerPanel, BorderLayout.PAGE_START);
        mainPanel.add(bodyPanel, BorderLayout.CENTER);

        frame.add(mainPanel);

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

        JButton cartButton = new JButton("Cart");
        cartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[][] info = storeview.store.getCartInfo(storeview.cart);
                StringBuilder sb = new StringBuilder();
                String s = "";
                for(String[] strings : info) {
                    //sb.append(Arrays.toString(strings)).append('\n');
                    sb.append(strings[0] +" - $"+ strings[1] + " |  Amount: "+ strings[2] + "\n");
                }
                sb.append("\nCURRENT TOTAL: $" + storeview.store.checkout(storeview.cart));
                JOptionPane.showMessageDialog(null, sb, "View Cart", JOptionPane.PLAIN_MESSAGE);
            }
        });

        headerPanel.add(cartButton);
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?")
                        == JOptionPane.OK_OPTION) {
                    frame.setVisible(false);
                    frame.dispose();
                    windowExit(frame);
                }
            }
        });

        headerPanel.add(quitButton);

        //body
        JPanel[] productPanels = storeview.getProductPanels();
        for(int i = 0; i < productPanels.length; i++){
            bodyPanel.add(productPanels[i]);
        }
        JScrollPane scrollPane = new JScrollPane(bodyPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        //footer
        JButton checkoutB = new JButton("Checkout");
        checkoutB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(JOptionPane.showConfirmDialog(frame, "Your Total Is:  $" + storeview.store.checkout(storeview.cart),"Checkout",JOptionPane.YES_NO_OPTION)
                        == JOptionPane.OK_OPTION) {
                    frame.setVisible(false);
                    frame.dispose();
                    int c = 0;
                    for (StoreView currentUser : users) {
                        c++;
                        if (currentUser.getCartID() == storeview.getCartID()) {
                            users[c] = null;
                            break;
                        }
                    }
                    selectCart();
                }
            }
        });
        footerPanel.add(checkoutB);

        //add panels to main panel
        mainPanel.add(headerPanel, BorderLayout.PAGE_START);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.PAGE_END);

        windowExit(frame);

        frame.add(mainPanel);
        frame.pack();
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
