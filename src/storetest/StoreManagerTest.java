package storetest;
import org.junit.jupiter.api.*;
import mystore.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class StoreManagerTest {
    private static StoreManager sm;

    @BeforeAll
    public static void init(){
        sm = new StoreManager();
        sm.assignCartID();
    }

    @Test
    public void testGetInventory(){

    }

    @Test
    public void testGetCart(){
        assertEquals(1,sm.getCart(1).getId(), "getCart() returns the incorrect cart");
    }

    @Test
    public void testGetCarts(){
        assertEquals(1,sm.getCart(1).getId(), "getCart() returns the incorrect cart");
        assertEquals(2,sm.getCarts().size(),"The size of the carts list is incorrect");
        assertEquals(2,sm.getCart(2).getId(), "getCart() returns the incorrect cart");
        assertEquals(2,sm.getCarts().size(),"The size of the carts list is incorrect");
    }

    @Test
    public void testAssignCartID(){
        //assertEquals(1,sm.assignCartID(),"assignCartID() does not return the correct ID");
        //assertEquals(1,sm.getCarts().size(),"assignCartID() does not add another cart to the cart list");

        assertEquals(2,sm.assignCartID(),"assignCartID() does not return the correct ID");
        assertEquals(2,sm.getCarts().size(),"assignCartID() does not add another cart to the cart list");
    }

    @Test
    public void testGetInvStock(){
        assertEquals(10,sm.getInvStock(1),"Inventory does not return the correct stock");
        assertEquals(20,sm.getInvStock(2),"Inventory does not return the correct stock");
        assertEquals(30,sm.getInvStock(3),"Inventory does not return the correct stock");
    }

    @Test
    public void testGetCartStock(){
        //create cart and add products
        //sm.assignCartID();
        ShoppingCart cart = sm.getCart(1);
        sm.addToCart(cart,1,4);
        sm.addToCart(cart,2,5);
        sm.addToCart(cart,3,6);

        //test function
        assertEquals(4,sm.getCartStock(cart,1),"Cart does not return the correct stock");
        assertEquals(5,sm.getCartStock(cart,2),"Cart does not return the correct stock");
        assertEquals(6,sm.getCartStock(cart,3),"Cart does not return the correct stock");
    }

    @Test
    public void testAddToCart(){

        ShoppingCart cart = sm.getCart(1);

        sm.addToCart(cart,1,4);
        assertEquals(4,sm.getCartStock(cart,1),"Incorrect stock added to cart");
        assertEquals(6,sm.getInvStock(1),"Incorrect stock removed from inventory");

        sm.addToCart(cart,-1,4);
        assertEquals(4,sm.getCartStock(cart,1),"Incorrect stock added to cart");
        assertEquals(6,sm.getInvStock(1),"Incorrect stock removed from inventory");
    }

    @Test
    public void testDelFromCart(){

        ShoppingCart cart = sm.getCart(1);
        sm.addToCart(cart,1,10);

        sm.delFromCart(cart,1,5);
        assertEquals(5,sm.getCartStock(cart,1),"Incorrect stock removed from cart");
        assertEquals(5,sm.getInvStock(1),"Incorrect stock returned to inventory");

        sm.delFromCart(cart,-1,5);
        assertEquals(5,sm.getCartStock(cart,1),"Incorrect stock removed from cart");
        assertEquals(5,sm.getInvStock(1),"Incorrect stock returned to inventory");
    }

    @Test
    public void testCheckout(){

        ShoppingCart cart = sm.getCart(1);
        sm.addToCart(cart,1,2);
        sm.addToCart(cart,2,4);
        sm.addToCart(cart,3,6);

        assertEquals(340.0, sm.checkout(cart), "Checkout returns the incorrect total");
    }

    @Test
    public void testGetCartInfo(){

        ShoppingCart cart = sm.getCart(1);
        sm.addToCart(cart,1,2);
        sm.addToCart(cart,2,4);
        sm.addToCart(cart,3,6);

        String[][] expected = {{"Apple","10.0","2","1"},{"Banana","20.0","4","2"},{"Orange","40.0","6","3"}};
        assertArrayEquals(expected, sm.getCartInfo(cart), "Cart does not return correct information");
    }

    @Test
    public void testGetInventoryInfo(){
        String[][] expected = {{"Apple","10.0","10","1"},{"Banana","20.0","20","2"},{"Orange","40.0","30","3"}};
        assertArrayEquals(expected, sm.getInventoryInfo(), "Inventory does not return correct information");
    }

    @AfterEach
    public void cleanup(){
        sm.getInventory().getProductList().clear();
        sm.getInventory().getStockList().clear();
        sm.getInventory().initialize();
        sm.getCart(1).getProductList().clear();
        sm.getCart(1).getStockList().clear();
    }

}
