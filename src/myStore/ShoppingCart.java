package myStore;

import java.util.ArrayList;

/**
 * A ShoppingCart
 * @author Mika Le 101141818
 * @version 2.0
 */

public class ShoppingCart implements ProductStockContainer{
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Integer> stocks = new ArrayList<>();
    private int numOfProducts = 0;
    private final int id;
    private double total = 0.0;

    /**
     * Constructor for a ShoppingCart
     * @param id int, id of the cart
     */
    public ShoppingCart(int id){
        this.id = id;
    }

    /**
     * This method gives access to the id of the cart
     * @return int, id of the cart
     */
    public int getId() {
        return this.id;
    }

    public double getTotal() {
        return total;
    }

    /**
     * This method gives access to the list of stock
     * @return ArrayList<Integer>, list of stock
     */
    public ArrayList<Integer> getStockList() {
        return this.stocks;
    }

    /**
     * This method gives access to the list of products
     * @return ArrayList<Product>, list of products
     */
    public ArrayList<Product> getProductList() {
        return this.products;
    }

    /**
     * Finds the index of the product within the the ArrayList of products
     * @param  id int, id of the product
     * @return    int, index of the product within the ArrayList or -1 if not found
     */
    private int getProdIndex(int id) {
        for(int i = 0; i < products.size(); i++) {
            if(products.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getProductQuantity(Product product) {
        int i = getProdIndex(product.getId());
        if(i > -1) {
            return stocks.get(i);
        }
        return -1;
    }

    @Override
    public void addProductQuantity(Product product, int quantity) {
        int i = getProdIndex(product.getId());
        if(i == -1) {
            products.add(product);
            stocks.add(quantity);
            numOfProducts++;
        } else {
            stocks.set(i, stocks.get(i) + quantity);
        }
        total += product.getPrice() * quantity;
        System.out.println("Total: " + total);
    }

    @Override
    public boolean removeProductQuantity(Product product, int quantity) {
        int i = getProdIndex(product.getId());
        boolean removed = false;

        if(i > -1) {
            if (stocks.get(i) >= quantity) { //sufficient stock
                stocks.set(i, stocks.get(i) - quantity);
                total -= product.getPrice() * quantity;
                removed = true;
            }
        }
        return removed;
    }

    @Override
    public int getNumOfProducts() {
        return numOfProducts;
    }

}
