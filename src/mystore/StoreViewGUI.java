package mystore;

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
import java.util.ArrayList;
import java.util.Arrays;


public class StoreViewGUI {
//    private final JFrame frame;

    private StoreManager store;
    private ShoppingCart cart;
    private static StoreViewGUI[] users;

    public StoreViewGUI(StoreManager store, int cartID){
//        this.frame = new JFrame();
//        this.frame.setTitle("D&M Grocery Store");
//        this.frame.setVisible(true);

        this.store = store;
        this.cart = store.getCart(cartID);
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

    private Color getColour() {
        int r = (int)(Math.random()*256);
        int g = (int)(Math.random()*256);
        int b = (int)(Math.random()*256);
        double luma = (0.2126 * r) + (0.7152 * g) + (0.0722 * b);

        while (luma < 75) {
            r = (int)(Math.random()*256);
            g = (int)(Math.random()*256);
            b = (int)(Math.random()*256);
            luma = (0.2126 * r) + (0.7152 * g) + (0.0722 * b);
        }
        return new Color(r, g, b);
    }


    private JPanel[] getProductPanels(){
        JPanel[] productPanels = new JPanel[store.getInventoryInfo().length];

        for(int i = 0; i < store.getInventoryInfo().length; i++){
            JPanel infoPanel = new JPanel(new BorderLayout());
            JPanel buttonPanel = new JPanel();

            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(getColour());
            panel.setMinimumSize(new Dimension(300,500)); //not working??
            panel.setBorder(new EmptyBorder(10,10,10,10));

            JLabel nameLabel = new JLabel(store.getInventoryInfo()[i][0]);
            JLabel infoLabel = new JLabel("($"+store.getInventoryInfo()[i][1] + ") - Stock: " + store.getInventoryInfo()[i][2]);
            infoPanel.add(nameLabel, BorderLayout.PAGE_START);
            infoPanel.add(infoLabel, BorderLayout.CENTER);

            JButton addButton = new JButton("+");
            JButton removeButton = new JButton("-");
            //JLabel count = new JLabel(String.valueOf(cart.getStock(Integer.parseInt(store.getInventoryInfo()[i][3]))));
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
                    //if (Integer.parseInt(store.getInventoryInfo()[finalI][2]) > 0) {
                        store.delFromCart(cart, Integer.parseInt(store.getInventoryInfo()[finalI][3]), 1);
                        if(cart.getStock(Integer.parseInt(store.getInventoryInfo()[finalI][3])) != -1) {
                            count.setText(String.valueOf(cart.getStock(Integer.parseInt(store.getInventoryInfo()[finalI][3]))));
                        }

                        infoLabel.setText("($"+store.getInventoryInfo()[finalI][1] + ") - Stock: " + store.getInventoryInfo()[finalI][2]);
                   // }
                }
            });

            buttonPanel.add(addButton);
            buttonPanel.add(count);
            buttonPanel.add(removeButton);

            Image image = null;
            try {
                image = ImageIO.read(new File("./src/mystore/images/"+ store.getInventoryInfo()[i][0]+".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            image = image.getScaledInstance(200,200, Image.SCALE_DEFAULT);
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            //JLabel imageLabel = new JLabel("IMAGE");


            panel.add(infoPanel, BorderLayout.NORTH);
            panel.add(imageLabel, BorderLayout.CENTER);
            panel.add(buttonPanel, BorderLayout.SOUTH);

            productPanels[i] = panel;
        }

        return productPanels;
    }

    public int getCartID(){
        return this.cart.getId();
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
        inputFormat.setMinimum(0);
        inputFormat.setMaximum(9999);
        JFormattedTextField input = new JFormattedTextField(inputFormat);
        input.setColumns(3);

        //TODO: cannot delete last char in text field

        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                boolean achieved = false;
                int cartID = Integer.parseInt(input.getText());
                for(StoreViewGUI currentUser : users){
                    if(currentUser.getCartID() == cartID && currentUser != null){
                        //dee add logic
                        achieved = true;
                        displayStore(currentUser);
                        frame.setVisible(false);
                        frame.dispose();
                        break;
                    }
                }
                if(achieved == false) {
                    JOptionPane.showMessageDialog(frame, "Invalid Input", "Cart Input", JOptionPane.ERROR_MESSAGE);
                }
                //pop up dialog saying invalid input

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
        JPanel bodyPanel = new JPanel(new GridLayout(4,2));
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
                    for (StoreViewGUI currentUser : users) {
                        c++;
                        if (currentUser.getCartID() == storeview.getCartID()) {
                            users[c] = null;
                            break;
                        }
                    }
                    selectCart();
                }
                    /*StoreViewGUI[] arr = new StoreViewGUI[users.length];

                    for(int i = 0, k = 0; i < arr.length; i++) {
                        if(users[i].getCartID() !=  storeview.getCartID()) {
                            arr[k] = users[i];
                            k++;
                        }
                    }
                    users = arr;
                    selectCart();
                }*/

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
        StoreViewGUI sv1 = new StoreViewGUI(sm, sm.assignCartID());
        StoreViewGUI sv2 = new StoreViewGUI(sm, sm.assignCartID());
        StoreViewGUI sv3 = new StoreViewGUI(sm, sm.assignCartID());

        users = new StoreViewGUI[]{sv1, sv2, sv3};

        selectCart();

    }

}
