/* Store Manager class for managing the inventory
 * Authors: Dhriti Aravind 101141942, Mika Le 101141818
 * Last edited: February 9, 2021
 */

public class StoreManager{
    private Inventory inventory;

    public StoreManager() {
        this.inventory = new Inventory();
    }

    public StoreManager(Inventory inv) {
        this.inventory = inv;
    }

    public int checkStock(Product product) {
        return inventory.getStock(product.getId());
    }

    public double transaction(int[][] cart) {
        double total = 0;
        //check desired quantity exists for all products
        for (int[] item : cart) {
            if (item[1] > inventory.getStock(item[0])) {
                //insufficient supply
                System.out.println("Insufficient supply. Transaction cancelled\n");
                return -1;
            }
        }

        //process transaction
        for (int[] item : cart) {
            inventory.delStock(item[0], item[1]);
            total += inventory.getProduct(item[0]).getPrice() * item[1];
        }

        return Math.round(total * 100.0)/100.0;
    }


}


