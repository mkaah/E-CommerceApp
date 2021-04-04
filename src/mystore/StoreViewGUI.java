package mystore;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;


public class StoreViewGUI {
    private final JFrame frame;
    private int cartID;
    private StoreManager store;
    private ShoppingCart cart;

    public StoreViewGUI(StoreManager store, int cartID){
        this.frame = new JFrame();
        this.frame.setTitle("D&M Grocery Store");
        this.frame.setVisible(true);
        this.cartID = cartID;
        this.store = store;
        this.cart = store.getCart(cartID);
    }

    /**
     * This method displays a confirmation prompt when user
     * tries to quit the window
     */
    private void windowExit(){
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

    private JButton cartButton() {
        JButton button = new JButton();

        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.setIcon(new ImageIcon("cart.png"));

        button.setSize(new Dimension(200,90));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String[][] label = store.getCartInfo(store.getCart(cartID));
                StringBuilder sb = new StringBuilder();
                for(String[] a : label)
                   sb.append(Arrays.toString(a));
                JOptionPane.showMessageDialog(null,sb,"View Cart", JOptionPane.PLAIN_MESSAGE);

            }
        });
        return button;
    }

    private JButton quitButton() {
        JButton button = new JButton("Quit");

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
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
        });
        return button;
    }

    private JButton addButton(int option, JLabel selectedStock) {
        JButton button = new JButton("+");

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String[][] info = store.getCartInfo(cart);

                store.addToCart(cart, Integer.parseInt(info[option][3]), Integer.parseInt(info[option][2]));
                selectedStock.setText(String.valueOf(store.getCartStock(cart, Integer.parseInt(info[option][3]))));


            }
        });
        return button;
    }

    private JToggleButton removeButton(int option) {
        JToggleButton button = new JToggleButton("-");

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String[][] info = store.getCartInfo(cart);
                store.delFromCart(cart, Integer.parseInt(info[option][3]), Integer.parseInt(info[option][2]));

            }
        });
        return button;
    }


    private JButton checkoutButton() {
        JButton button = new JButton("Checkout");
        button.setFont(new Font("Helvetica",Font.BOLD,11));

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

            }
        });
        return button;
    }



    private JPanel productPanel(){
        JPanel panel = new JPanel(new GridLayout());

        for(int i = 0; i < store.getInventoryInfo().length; i++){
            JPanel productPanel = new JPanel(new BorderLayout());
            productPanel.setBackground(getColour());
            JPanel infoPanel = new JPanel(new BorderLayout());


            JLabel name = new JLabel(store.getInventoryInfo()[i][0]);
            JLabel price = new JLabel("$"+store.getInventoryInfo()[i][1]);
            JLabel stock = new JLabel("Stock: "+store.getInventoryInfo()[i][2]);
            JLabel selectedStock = new JLabel("0");
            //add image
            JPanel selectStock = new JPanel(new GridLayout(1,4));
            selectStock.add(stock);

            selectStock.add(selectedStock);
            selectStock.add(addButton(i, selectedStock));
            selectStock.add(removeButton(i));


            infoPanel.add(name, BorderLayout.PAGE_START);
            infoPanel.add(price, BorderLayout.CENTER);
            infoPanel.add(selectStock, BorderLayout.PAGE_END);


            productPanel.add(infoPanel, BorderLayout.SOUTH);

            panel.add(productPanel);
        }
        return panel;
    }

    public void displayGUI(){
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel headerPanel = new JPanel(new FlowLayout());
        JPanel bodyPanel = new JPanel(new GridLayout());
        JPanel footerPanel = new JPanel();

        //delete
        headerPanel.setBackground(getColour());
        bodyPanel.setBackground(getColour());
        footerPanel.setBackground(getColour());


        JLabel headerLabel = new JLabel("Welcome to D&M Grocery Store");
        headerPanel.add(headerLabel);
        headerPanel.add(cartButton());
        headerPanel.add(quitButton());

        JScrollPane scrollPane = new JScrollPane(productPanel());
        bodyPanel.add(scrollPane);

        //bodyPanel.add(productPanel());

        footerPanel.add(checkoutButton());


        //add panels to main panel
        mainPanel.add(headerPanel, BorderLayout.PAGE_START);
        mainPanel.add(bodyPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.PAGE_END);

        //set the preferred sizes and colours here
        headerPanel.setPreferredSize(new Dimension(800, 100));
        bodyPanel.setPreferredSize(new Dimension(800, 500));
        footerPanel.setPreferredSize(new Dimension(800, 100));

        frame.add(mainPanel);
        frame.pack();
        windowExit();
    }

    public static void main(String[] args) {
        StoreManager sm = new StoreManager();
        StoreViewGUI sv1 = new StoreViewGUI(sm, sm.assignCartID());
        sv1.displayGUI();
    }

}
