package storetest;
import mystore.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import mystore.Inventory;

import java.util.ArrayList;


public class InventoryTest {
    private static Inventory inventory;

    @BeforeEach
    public void init() {
        inventory = new Inventory();
        inventory.initialize();
    }

    @Test
    public void testGetStockList() {
        //assertArrayEquals(inventory.getStockList(), inventory.)
        ArrayList<Integer> list =  inventory.getStockList();
        assertEquals(10, list.get(0),"getStockList() does not return the correct stocks amount");
        assertEquals(20, list.get(1),"getStockList() does not return the correct stocks amount");
        assertEquals(30, list.get(2),"getStockList() does not return the correct stocks amount");
    }

    @Test
    public void testGetProductList() {
         ArrayList<Product> list =  inventory.getProductList();
         assertEquals(1, list.get(0).getId(),"getProductList() does not return the correct product");
         assertEquals("Apple", list.get(0).getName(),"getProductList() does not return the correct product");
         assertEquals(10.00, list.get(0).getPrice(),"getProductList() does not return the correct product");

         assertEquals(2, list.get(1).getId(),"getProductList() does not return the correct product");
         assertEquals("Banana", list.get(1).getName(),"getProductList() does not return the correct product");
         assertEquals(20.00, list.get(1).getPrice(),"getProductList() does not return the correct product");

        assertEquals(3, list.get(2).getId(),"getProductList() does not return the correct product");
        assertEquals("Orange", list.get(2).getName(),"getProductList() does not return the correct product");
        assertEquals(40.00, list.get(2).getPrice(),"getProductList() does not return the correct product");
    }

    @Test
    public void testGetProdIndex() {
        //assertEquals(0, inventory.getProdIndex(1),"getProductList() does not return the correct product");

    }

    @Test
    public void testGetProduct() {
        assertEquals(1, inventory.getProduct(1).getId(), "getProduct(int id) does not return the correct id");
        assertEquals("Apple", inventory.getProduct(1).getName(),  "getProduct(int id) does not return the correct name");
        assertEquals(10.00, inventory.getProduct(1).getPrice(), "getProduct(int id) does not return the correct price");

        assertEquals(2, inventory.getProduct(2).getId(),  "getProduct(int id) does not return the correct id");
        assertEquals("Banana", inventory.getProduct(2).getName(),   "getProduct(int id) does not return the correct name");
        assertEquals(20.00, inventory.getProduct(2).getPrice(), "getProduct(int id) does not return the correct price");

        assertEquals(3, inventory.getProduct(3).getId(), "getProduct(int id) does not return the correct id");
        assertEquals("Orange", inventory.getProduct(3).getName(), "getProduct(int id) does not return the correct name");
        assertEquals(40.00, inventory.getProduct(3).getPrice(), "getProduct(int id) does not return the correct price");
    }

    @Test
    public void testGetProdName() {
        assertEquals("Apple", inventory.getProdName(1),   "getProdName(int id) does not return the correct name");
        assertEquals("Banana", inventory.getProdName(2),  "getProdName(int id) does not return the correct name");
        assertEquals("Orange",inventory.getProdName(3),   "getProdName(int id) does not return the correct name");
    }

    @Test
    public void testGetProdPrice() {
        assertEquals(10.00, inventory.getProdPrice(1),  "getProdPrice(int id) does not return the correct price");
        assertEquals(20.00, inventory.getProdPrice(2), "getProdPrice(int id) does not return the correct price");
        assertEquals(40.00, inventory.getProdPrice(3),  "getProdPrice(int id) does not return the correct price");
    }

    @Test
    public void testGetStock() {
        assertEquals(10, inventory.getStock(1),  "getStock(int id) does not return the correct amount of stock");
        assertEquals(20,inventory.getStock(2),  "getStock(int id) does not return the correct amount of stock");
        assertEquals(30,inventory.getStock(3),  "getStock(int id) does not return the correct amount of stock");
    }

    @Test
    public void testAddStock() {
        inventory.addStock(inventory.getProduct(1), 5);
        inventory.addStock(inventory.getProduct(2), 5);
        inventory.addStock(inventory.getProduct(3), 5);

        assertEquals(15, inventory.getStock(1), "addStock(Product prod, int id) does not return the correct amount of stock");
        assertEquals(25,inventory.getStock(2),  "addStock(Product prod, int id) does not return the correct amount of stock");
        assertEquals(35,inventory.getStock(3),  "addStock(Product prod, int id)does not return the correct amount of stock");
    }

    @Test
    public void testDelStock() {
        inventory.delStock(1, 5);
        inventory.delStock(2, 5);
        inventory.delStock(3, 5);

        assertEquals(5, inventory.getStock(1),  "delStock(Product prod, int id) does not return the correct amount of stock");
        assertEquals(15,inventory.getStock(2),  "delStock(Product prod, int id) does not return the correct amount of stock");
        assertEquals(25,inventory.getStock(3),  "delStock(Product prod, int id) does not return the correct amount of stock");
    }



}
