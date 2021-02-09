/* Inventory class for managing all products and their stock
 * Authors: Dhriti Aravind 101141942, Mika Le 101141818
 * Last edited: February 9, 2021
 */

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Product> products; //Products available
    private ArrayList<Integer> stocks; //Number of stock for each product

    public Inventory() {
        this.products = new ArrayList<>();
        this.stocks = new ArrayList<>();
    }

    //return the index of the product within the arraylist
    private int getProdIndex(int id) {
        for(int i = 0; i < products.size(); i++) {
            if(products.get(i).getId() == id) {
                return i;
            }
        }
        //product not found
        return -1;
    }

    //get the product given its id
    public Product getProduct(int id) {
        return products.get(getProdIndex(id));
    }

    //return the product name
    public String getProdName(int id) {
        return getProduct(id).getName();
    }

    //return the product price
    public double getProdPrice(int id) {
        return getProduct(id).getPrice();
    }

    //get the amount of stock for a given product
    public int getStock(int id) {
        int i = getProdIndex(id);
        if(i > -1) { //product exists
            return stocks.get(i);
        }
        //product does not exist
        System.out.println("Product with ID " + id +  " does not exist");
        return -1;
    }

    //add stock of a given product
    public void addStock(Product product, int stock) {
        int i = getProdIndex(product.getId());

        if(i == -1) { //product DNE
            products.add(product);
            stocks.add(stock);
        } else { //product exists
            stocks.set(i, stocks.get(i) + stock);
        }
    }

    //remove stock of a given product
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

    public String[] getInformation(Product product) {
        return new String[]{product.getName(), String.valueOf(product.getId()), "$" + product.getPrice()};
    }

}
