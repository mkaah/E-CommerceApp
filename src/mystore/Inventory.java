package mystore;

import java.util.ArrayList;

/**
 * An Inventory
 * @author Dhriti Aravind 101141942, Mika Le 101141818
 * @version 1.0
 */
public class Inventory {
    private ArrayList<Product> products; //Products available in the inventory
    private ArrayList<Integer> stocks;   //Amount of stock available for each product

    /**
     * Constructor for an Inventory
     */
    public Inventory() {
        this.products = new ArrayList<>();
        this.stocks = new ArrayList<>();
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
     * This method returns a Product given it's id
     * @param id int, id of the product
     * @return   Product, the product (if it does not exist, null is returned)
     */
    public Product getProduct(int id) {
        if (getProdIndex(id) != -1) { //product exists
            return products.get(getProdIndex(id));
        }
        return null;
    }

    /**
     * This method gives access to the name of the Product
     * @param id int, id of the product
     * @return   String, name of the Product (if it does not exist, null is returned)
     */
    public String getProdName(int id) {
        if (getProdIndex(id) != -1) {
            return getProduct(id).getName();
        }
        return null;

    }

    /**
     * This method gives access to the name of the Product
     * @param id int, id of the product
     * @return   double, price of the Product (returns -1 if the product DNE)
     */
    public double getProdPrice(int id) {
        if (getProdIndex(id) != -1) {
            return getProduct(id).getPrice();
        }
        return -1;
    }

     /**
     * Gets the amount of stock available for a given product
     * @param id int, id of the Product
     * @return   int, amount of stock available (returns -1 if the product DNE)
     */
    public int getStock(int id) {
        int i = getProdIndex(id);
        if(i > -1) { //product exists
            return stocks.get(i);
        }
        return -1;
    }

    /**
     * Add stock to the inventory for a given product
     * @param product Product, the product to be added
     * @param stock   int, the amount of stock to be added
     */
    public void addStock(Product product, int stock) {
        int i = getProdIndex(product.getId());
        if(i == -1) {
            products.add(product);
            stocks.add(stock);
        } else {
            stocks.set(i, stocks.get(i) + stock);
        }
    }

    /**
     * Remove stock of a given product if available
     * @param id    int, id of the product
     * @param stock int, amount of stock to remove
     * @return      boolean, true if stock was removed, false otherwise
     */
    public boolean delStock(int id, int stock) {
        int i = getProdIndex(id);
        boolean removed = false;

        if(i > -1) {
            if (stocks.get(i) >= stock) { //sufficient stock
                stocks.set(i, stocks.get(i) - stock);
                removed = true;
            }
        }
        return removed;
    }

    /**
     * Returns an Array containing the product's name, id, and price
     * @param product Product, the product to get information on
     * @return        String[], Array containing the product's name, id, and price
     */
    public String[] getInformation(Product product) {
        return new String[]{product.getName(), String.valueOf(product.getId()), String.valueOf(product.getPrice())};
    }

    /**
     * This method adds some default stock to the inventory
     */
    public void initialize(){
        this.addStock(new Product("Apple", 1, 10.00), 10);
        this.addStock(new Product("A", -1, 50.00), 10);
        this.addStock(new Product("Banana", 2, 20.00), 20);
        this.addStock(new Product("Orange", 3, 40.00), 30);
    }

}
