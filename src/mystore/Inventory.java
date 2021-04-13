package myStore;

import java.util.ArrayList;

/**
 * An Inventory
 * @author Dhriti Aravind 101141942, Mika Le 101141818
 * @version 4.0 updated by Mika Le
 */
public class Inventory implements ProductStockContainer{
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Integer> stocks = new ArrayList<>();
    private int numOfProducts = 0;

    /**
     * Constructor for an Inventory
     */
    public Inventory() {
        initialize();
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

    /**
     * Get the quantity of a given product in the Inventory
     *
     * @param product   Product, a product
     * @return          int, amount of stock
     */
    @Override
    public int getProductQuantity(Product product) {
        int i = getProdIndex(product.getId());
        if(i > -1) { //product exists
            return stocks.get(i);
        }
        return -1;
    }

    /**
     * Add a specified amount of Product to the Inventory
     *
     * @param product   Product, a product
     * @param quantity  int, amount of stock
     */
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
    }

    /**
     * Remove a specified amount of Product from the Inventory
     * @param product   Product, a product
     * @param quantity  int, amount of stock
     * @return          boolean, if the product quantity has been removed successfully
     */
    @Override
    public boolean removeProductQuantity(Product product, int quantity) {
        int i = getProdIndex(product.getId());
        boolean removed = false;

        if(i > -1) {
            if (stocks.get(i) >= quantity) { //sufficient stock
                stocks.set(i, stocks.get(i) - quantity);
                removed = true;
            }
        }
        return removed;
    }

    /**
     * Get the number of products in the Inventory
     *
     * @return      int, Number of products
     */
    @Override
    public int getNumOfProducts() {
        return numOfProducts;
    }

    /**
     * This method adds some default stock to the inventory
     */
    public void initialize(){
        this.addProductQuantity(new Product("Apple", products.size() + 1, 0.79), 10);
        this.addProductQuantity(new Product("Banana", products.size() + 1, 0.59), 20);
        this.addProductQuantity(new Product("Orange", products.size() + 1, 0.79), 30);
        this.addProductQuantity(new Product("Strawberries", products.size() + 1, 6.99), 50);
        this.addProductQuantity(new Product("Blueberries", products.size() + 1, 5.29), 50);
        this.addProductQuantity(new Product("Kiwi", products.size() + 1, 5.98), 30);
        this.addProductQuantity(new Product("Mango", products.size() + 1, 2.65), 30);
        this.addProductQuantity(new Product("Watermelon", products.size() + 1, 9.85), 30);
    }
}
