package myStore;

public interface ProductStockContainer {

    int getProductQuantity(Product product);

    void addProductQuantity(Product product, int quantity);

    boolean removeProductQuantity(Product product, int quantity);

    int getNumOfProducts();

}
