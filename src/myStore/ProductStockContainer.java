package myStore;

/**
 * @author Dhriti Aravind 101141942
 * @author Mika Le 101141818
 */
public interface ProductStockContainer {

    /**
     * Get the quantity of a given product
     *
     * @param product   Product, a product
     * @return          int, amount of stock
     */
    int getProductQuantity(Product product);

    /**
     * Add a specified amount of Product
     *
     * @param product   Product, a product
     * @param quantity  int, amount of stock
     */
    void addProductQuantity(Product product, int quantity);

    /**
     * Remove a specified amount of Product
     * @param product   Product, a product
     * @param quantity  int, amount of stock
     * @return          boolean, if the product quantity has been removed successfully
     */
    boolean removeProductQuantity(Product product, int quantity);

    /**
     * Get the number of products
     *
     * @return      int, Number of products
     */
    int getNumOfProducts();

}
