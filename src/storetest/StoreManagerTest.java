package storetest;

import mystore.*;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests functions in StoreManager
 * @author Mika Le 101141818
 * @version 1.0
 */
public class StoreManagerTest {
    private static StoreManager sm;

    /**
     * Instantiates a StoreManager object and creates and adds
     * a ShoppingCart to the StoreManager's list of carts.
     * This cart will be used throughout the tests.
     */
    @BeforeAll
    public static void init(){
        sm = new StoreManager();
        sm.assignCartID();
    }

    /**
     * Tests getInventory(). Since we cannot access this Inventory object without calling
     * on getInventory(), we cannot compare the Inventory object itself. This method checks
     * if the expected and actual Inventory contain the same products, stock, and list sizes.
     */
    @Test
    public void testGetInventory(){
        Inventory expectedInv = new Inventory();
        expectedInv.initialize();

        ArrayList<Product> actualInvProd = sm.getInventory().getProductList();
        ArrayList<Integer> actualInvStock = sm.getInventory().getStockList();

        //check that Products are the same
        for(int i = 0; i < expectedInv.getProductList().size(); i++){
            assertEquals(expectedInv.getProductList().get(i).getName(), actualInvProd.get(i).getName(), "getInventory " +
                    "contains a product with the wrong name");
            assertEquals(expectedInv.getProductList().get(i).getId(), actualInvProd.get(i).getId(), "getInventory " +
                    "contains a product with the wrong id");
            assertEquals(expectedInv.getProductList().get(i).getPrice(), actualInvProd.get(i).getPrice(), "getInventory " +
                    "contains a product with the wrong price");
        }

        //check that stock is the same
        for (int i = 0; i < expectedInv.getStockList().size(); i++){
            assertEquals(expectedInv.getStockList().get(i), actualInvStock.get(i), "getInventory contains a product with" +
                    "the wrong stock");
        }

        //check that list sizes are the same
        assertEquals(expectedInv.getProductList().size(), actualInvProd.size(),"getInventory returns an Inventory with " +
                "a list of products with a different length");
        assertEquals(expectedInv.getStockList().size(), actualInvStock.size(),"getInventory returns an Inventory with " +
                "a list of stock with a different length");
    }

    /**
     * Tests that getCart() returns the correct ShoppingCart by comparing
     * the ID. Each cart has a unique ID.
     */
    @Test
    public void testGetCart(){
        assertEquals(1,sm.getCart(1).getId(), "getCart() returns the incorrect cart");
    }

    /**
     * Tests that getCarts() returns the correct ArrayList of ShoppingCart
     * objects by checking their IDs and the size of the list.
     */
    @Test
    public void testGetCarts(){
        assertEquals(1,sm.getCart(1).getId(), "getCart() returns the incorrect cart");
        assertEquals(2,sm.getCarts().size(),"The size of the carts list is incorrect");
        assertEquals(2,sm.getCart(2).getId(), "getCart() returns the incorrect cart");
        assertEquals(2,sm.getCarts().size(),"The size of the carts list is incorrect");
    }

    /**
     * Tests that assignCartID() returns the correct cartID and adds a cart to
     * the StoreManager's list of carts. Expected ID is 2 since cartID is static
     * and one cart was created at the beginning of testing.
     */
    @Test
    public void testAssignCartID(){
        assertEquals(2,sm.assignCartID(),"assignCartID() does not return the correct ID");
        assertEquals(2,sm.getCarts().size(),"assignCartID() does not add another cart to the cart list");
    }

    /**
     * Tests that getInvStock() returns the correct stock
     * by comparing it with the default stock.
     */
    @Test
    public void testGetInvStock(){
        assertEquals(10,sm.getInvStock(1),"Inventory does not return the correct stock");
        assertEquals(20,sm.getInvStock(2),"Inventory does not return the correct stock");
        assertEquals(30,sm.getInvStock(3),"Inventory does not return the correct stock");
    }

    /**
     * Tests that getCartStock() returns the correct stock after
     * adding items into the cart.
     */
    @Test
    public void testGetCartStock(){
        ShoppingCart cart = sm.getCart(1);
        sm.addToCart(cart,1,4);
        sm.addToCart(cart,2,5);
        sm.addToCart(cart,3,6);

        assertEquals(4,sm.getCartStock(cart,1),"Cart does not return the correct stock");
        assertEquals(5,sm.getCartStock(cart,2),"Cart does not return the correct stock");
        assertEquals(6,sm.getCartStock(cart,3),"Cart does not return the correct stock");
    }

    /**
     * Tests that addToCart() increases the cart stock and removes
     * inventory stock accordingly. Tests with valid id and valid quantity,
     * invalid id and valid quantity, valid id and invalid quantity.
     */
    @Test
    public void testAddToCart(){
        ShoppingCart cart = sm.getCart(1);

        sm.addToCart(cart,1,4);
        assertEquals(4,sm.getCartStock(cart,1),"Incorrect stock added to cart");
        assertEquals(6,sm.getInvStock(1),"Incorrect stock removed from inventory");

        //invalid ID should not change anything
        sm.addToCart(cart,-1,4);
        assertEquals(4,sm.getCartStock(cart,1),"Incorrect stock added to cart");
        assertEquals(6,sm.getInvStock(1),"Incorrect stock removed from inventory");

        //insufficient supply should not change anything
        sm.addToCart(cart,1,10);
        assertEquals(4,sm.getCartStock(cart,1),"Incorrect stock added to cart");
        assertEquals(6,sm.getInvStock(1),"Incorrect stock removed from inventory");
    }

    /**
     * Tests that delFromCart() decreases the cart stock and increases
     * inventory stock accordingly. Tests with valid id and valid quantity,
     * invalid id and valid quantity, valid id and invalid quantity.
     */
    @Test
    public void testDelFromCart(){
        ShoppingCart cart = sm.getCart(1);
        sm.addToCart(cart,1,10);

        sm.delFromCart(cart,1,5);
        assertEquals(5,sm.getCartStock(cart,1),"Incorrect stock removed from cart");
        assertEquals(5,sm.getInvStock(1),"Incorrect stock returned to inventory");

        //invalid ID should not change anything
        sm.delFromCart(cart,-1,5);
        assertEquals(5,sm.getCartStock(cart,1),"Incorrect stock removed from cart");
        assertEquals(5,sm.getInvStock(1),"Incorrect stock returned to inventory");

        //insufficient supply should not change anything
        sm.delFromCart(cart,1,10);
        assertEquals(5,sm.getCartStock(cart,1),"Incorrect stock removed from cart");
        assertEquals(5,sm.getInvStock(1),"Incorrect stock returned to inventory");
    }

    /**
     * Tests that checkout() returns the correct total.
     */
    @Test
    public void testCheckout(){
        ShoppingCart cart = sm.getCart(1);
        sm.addToCart(cart,1,2);
        sm.addToCart(cart,2,4);
        sm.addToCart(cart,3,6);

        assertEquals(34.0, sm.checkout(cart), "Checkout returns the incorrect total");
    }

    /**
     * Tests that getCartInfo() returns the correct product
     * name, price, quantity in cart, and id.
     */
    @Test
    public void testGetCartInfo(){
        ShoppingCart cart = sm.getCart(1);
        sm.addToCart(cart,1,2);
        sm.addToCart(cart,2,4);
        sm.addToCart(cart,3,6);

        String[][] expected = {{"Apple","1.0","2","1"},{"Banana","2.0","4","2"},{"Orange","4.0","6","3"}};
        assertArrayEquals(expected, sm.getCartInfo(cart), "Cart does not return correct information");
    }

    /**
     * Tests that getCartInfo() returns the correct product
     * name, price, quantity in inventory, and id.
     */
    @Test
    public void testGetInventoryInfo(){
        String[][] expected = {{"Apple","1.0","10","1"},{"Banana","2.0","20","2"},{"Orange","4.0","30","3"}};
        assertArrayEquals(expected, sm.getInventoryInfo(), "Inventory does not return correct information");
    }

    /**
     * After each test, clear and reinitialize the Inventory products
     * and stock back to default. Clear items in the cart.
     */
    @AfterEach
    public void cleanup(){
        sm.getInventory().getProductList().clear();
        sm.getInventory().getStockList().clear();
        sm.getInventory().initialize();
        sm.getCart(1).getProductList().clear();
        sm.getCart(1).getStockList().clear();
    }

}
