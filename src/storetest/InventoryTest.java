package storetest;

import mystore.*;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests functions in Inventory
 * @author Dhriti Aravind 101141942
 * @version 1.0
 */

public class InventoryTest {
    private static Inventory inventory;

    /**
     * Instantiates an Inventory object
     * Initializes the inventory and adds in some items to the inventory
     */
    @BeforeEach
    public void init() {
        inventory = new Inventory();
        inventory.initialize();
    }

    /**
     * Tests getStockList() it compares the expected amount of stock with each amount found
     * in the stock array
     */
    @Test
    public void testGetStockList() {
        //assertArrayEquals(inventory.getStockList(), inventory.)
        ArrayList<Integer> list =  inventory.getStockList();
        assertEquals(10, list.get(0),"getStockList() does not return the correct stocks amount");
        assertEquals(20, list.get(1),"getStockList() does not return the correct stocks amount");
        assertEquals(30, list.get(2),"getStockList() does not return the correct stocks amount");
    }

    /**
     * Tests getProductList() it compares the Product's attributes for each item in the array
     * to the ones in the initalize function. Because there is no way to directly compare the
     * Products, it compares attributes of each product to the expected.
     */
    @Test
    public void testGetProductList() {
         ArrayList<Product> list =  inventory.getProductList();
         assertEquals(1, list.get(0).getId(),"getProductList() does not return the correct product");
         assertEquals("Apple", list.get(0).getName(),"getProductList() does not return the correct product");
         assertEquals(1.00, list.get(0).getPrice(),"getProductList() does not return the correct product");

         assertEquals(2, list.get(1).getId(),"getProductList() does not return the correct product");
         assertEquals("Banana", list.get(1).getName(),"getProductList() does not return the correct product");
         assertEquals(2.00, list.get(1).getPrice(),"getProductList() does not return the correct product");

        assertEquals(3, list.get(2).getId(),"getProductList() does not return the correct product");
        assertEquals("Orange", list.get(2).getName(),"getProductList() does not return the correct product");
        assertEquals(4.00, list.get(2).getPrice(),"getProductList() does not return the correct product");
    }

    /**
     * Tests getProduct(int id) it compares the Product's attributes to the ones in the initalize function.
     * Because there is no way to directly compare the Products, it compares attributes of each product to the expected.
     */
    @Test
    public void testGetProduct() {
        assertEquals(1, inventory.getProduct(1).getId(), "getProduct(int id) does not return the correct id");
        assertEquals("Apple", inventory.getProduct(1).getName(),  "getProduct(int id) does not return the correct name");
        assertEquals(1.00, inventory.getProduct(1).getPrice(), "getProduct(int id) does not return the correct price");

        assertEquals(2, inventory.getProduct(2).getId(),  "getProduct(int id) does not return the correct id");
        assertEquals("Banana", inventory.getProduct(2).getName(),   "getProduct(int id) does not return the correct name");
        assertEquals(2.00, inventory.getProduct(2).getPrice(), "getProduct(int id) does not return the correct price");

        assertEquals(3, inventory.getProduct(3).getId(), "getProduct(int id) does not return the correct id");
        assertEquals("Orange", inventory.getProduct(3).getName(), "getProduct(int id) does not return the correct name");
        assertEquals(4.00, inventory.getProduct(3).getPrice(), "getProduct(int id) does not return the correct price");
    }

    /**
     * Tests that getProdName(int id) returns the right name
     */
    @Test
    public void testGetProdName() {
        assertEquals("Apple", inventory.getProdName(1),   "getProdName(int id) does not return the correct name");
        assertEquals("Banana", inventory.getProdName(2),  "getProdName(int id) does not return the correct name");
        assertEquals("Orange",inventory.getProdName(3),   "getProdName(int id) does not return the correct name");
    }

    /**
     * Tests that getProdPrice(int id) returns the right price
     */
    @Test
    public void testGetProdPrice() {
        assertEquals(1.00, inventory.getProdPrice(1),  "getProdPrice(int id) does not return the correct price");
        assertEquals(2.00, inventory.getProdPrice(2), "getProdPrice(int id) does not return the correct price");
        assertEquals(4.00, inventory.getProdPrice(3),  "getProdPrice(int id) does not return the correct price");
    }

    /**
     * Tests that getStock(int id) returns the right amount of stock for the given id
     */
    @Test
    public void testGetStock() {
        assertEquals(10, inventory.getStock(1),  "getStock(int id) does not return the correct amount of stock");
        assertEquals(20,inventory.getStock(2),  "getStock(int id) does not return the correct amount of stock");
        assertEquals(30,inventory.getStock(3),  "getStock(int id) does not return the correct amount of stock");
    }

    /**
     * Tests that addStock(int id) correctly returns the total amount of product after having stock added.
     */
    @Test
    public void testAddStock() {
        inventory.addStock(inventory.getProduct(1), 5);
        inventory.addStock(inventory.getProduct(2), 5);
        inventory.addStock(inventory.getProduct(3), 5);

        assertEquals(15, inventory.getStock(1), "addStock(Product prod, int id) does not return the correct amount of stock");
        assertEquals(25,inventory.getStock(2),  "addStock(Product prod, int id) does not return the correct amount of stock");
        assertEquals(35,inventory.getStock(3),  "addStock(Product prod, int id)does not return the correct amount of stock");
    }

    /**
     * Tests that delStock(int id) correctly returns the total amount of product after having stock removed.
     */
    @Test
    public void testDelStock() {
        inventory.delStock(1, 5);
        inventory.delStock(2, 5);
        inventory.delStock(3, 5);

        assertEquals(5, inventory.getStock(1),  "delStock(Product prod, int id) does not return the correct amount of stock");
        assertEquals(15,inventory.getStock(2),  "delStock(Product prod, int id) does not return the correct amount of stock");
        assertEquals(25,inventory.getStock(3),  "delStock(Product prod, int id) does not return the correct amount of stock");
    }
    /**
     * Tests that getInformation() returns the correct product
     * name, price, quantity in inventory, and id.
     */
    @Test
    public void testGetInformation() {
        ArrayList<Product> list =  inventory.getProductList();
        String[][] expected = {{"Apple","1","1.0"},{"Banana","2","2.0"},{"Orange","3","4.0"}};
        assertArrayEquals(expected[0], inventory.getInformation(list.get(0)), "Inventory does not return correct information");
        assertArrayEquals(expected[1], inventory.getInformation(list.get(1)), "Inventory does not return correct information");
        assertArrayEquals(expected[2], inventory.getInformation(list.get(2)), "Inventory does not return correct information");


    }

    /**
     * After each test, clear and reinitialize the Inventory products
     * and stock back to default.
     */
    @AfterEach
    public void cleanup() {
        inventory.getProductList().clear();
        inventory.getStockList().clear();
        inventory.initialize();
    }



}
