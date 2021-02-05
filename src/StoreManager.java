import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
//Dhriti Aravind
//Mika Le 101141818

public class StoreManager{
    private Inventory inventory;

    public StoreManager() {
        this.inventory = new Inventory();
    }

    public int checkStock(Product product) {
        return inventory.getStock(product.getId());
    }

    public double transaction(int[][] cart) {
        double total = 0;
        //check desired quantity exists for all products
        for (int[] ints : cart) {
            if (ints[1] > inventory.getStock(ints[0])) {
                //insufficient supply
                return -1;
            }
        }

        //process transaction
        for (int[] ints : cart) {
            inventory.delStock(ints[0], ints[1]);
            total += inventory.getProduct(ints[0]).getPrice() * ints[1];
        }

        return total;
    }


}


