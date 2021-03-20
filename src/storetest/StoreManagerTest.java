package storetest;
import org.junit.jupiter.api.*;
import mystore.*;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class StoreManagerTest {
    private static StoreManager sm;

    @BeforeAll
    public static void init(){
        sm = new StoreManager();
    }

    @Test
    public void testGetInventory(){
        //ArrayList<Product> expectedProducts;
        //ArrayList<Integer> expectedStocks;

        //assertEquals();

    }


}
