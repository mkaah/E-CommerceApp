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


public class StoreViewGUI {
    private StoreManager store;
    private ShoppingCart cart;
    private static StoreViewGUI[] users;

    public StoreViewGUI(StoreManager store, int cartID){
        this.store = store;
        this.cart = store.getCart(cartID);
    }

    public int getCartID(){
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

    private JPanel[] getProductPanels(){
        JPanel[] productPanels = new JPanel[store.getInventoryInfo().length];

        for(int i = 0; i < store.getInventoryInfo().length; i++){
            JPanel infoPanel = new JPanel(new BorderLayout());
            JPanel buttonPanel = new JPanel();

            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(new Color(255, 232, 171));
            panel.setMinimumSize(new Dimension(300,500)); //not working??
            panel.setBorder(new EmptyBorder(10,10,10,10));

            JLabel nameLabel = new JLabel(store.getInventoryInfo()[i][0]);
            JLabel infoLabel = new JLabel("($"+store.getInventoryInfo()[i][1] + ") - Stock: " + store.getInventoryInfo()[i][2]);
            infoPanel.add(nameLabel, BorderLayout.PAGE_START);
            infoPanel.add(infoLabel, BorderLayout.CENTER);

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
                for(StoreViewGUI currentUser : users){
                    if(currentUser.getCartID() == cartID){
                        displayStore(currentUser);
                        frame.setVisible(false);
                        frame.dispose();
                        break;
                    }
                }
                JOptionPane.showMessageDialog(frame, "Invalid ID");
            }
        });

        bodyPanel.add(input);

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

    public static void displayStore(StoreViewGUI storeview){
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

        windowExit(frame);

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        StoreManager sm = new StoreManager();
        StoreViewGUI sv1 = new StoreViewGUI(sm, sm.assignCartID());
        StoreViewGUI sv2 = new StoreViewGUI(sm, sm.assignCartID());
        StoreViewGUI sv3 = new StoreViewGUI(sm, sm.assignCartID());

        users = new StoreViewGUI[]{sv1, sv2, sv3};

        selectCart();
    }

}
