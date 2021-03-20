package storetest;
import org.junit.jupiter.api.*;
import mystore.*;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class StoreManagerTest {
    private static StoreManager sm;
    private static Inventory inv;

    @BeforeEach
    public void init(){
        sm = new StoreManager();
        //inv = new Inventory();
    }

    @Test
    public void testGetInventory(){
        //assertEquals(inv.getProductList(), sm.getInventory().getProductList(), "Products are different");
    }

    @Test
    public void testGetCart(){

    }

    @Test
    public void testAssignCartID(){
        assertEquals(1,sm.assignCartID(),"assignCartID() does not return the correct ID");
        assertEquals(1,sm.getCarts().size(),"assignCartID() does not add another cart to the cart list");
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
        sm.assignCartID();
        ShoppingCart cart = sm.getCart(1);
        sm.addToCart(cart,1,4);
        sm.addToCart(cart,2,5);
        sm.addToCart(cart,3,6);


        //test function
        assertEquals(4,sm.getCartStock(cart,1),"Cart does not return the correct stock");
        assertEquals(5,sm.getCartStock(cart,2),"Cart does not return the correct stock");
        assertEquals(6,sm.getCartStock(cart,3),"Cart does not return the correct stock");
    }


}
