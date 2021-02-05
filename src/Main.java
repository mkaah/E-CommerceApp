import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StoreManager store = new StoreManager();
        Inventory inv = new Inventory();

        Product p1 = new Product("Apple", 1, 1.00);
        Product p2 = new Product("Banana", 2, 2.00);
        Product p3 = new Product("Candy", 3, 4.00);

        System.out.println("--- TESTING PRODUCT FUNC ---");
        System.out.println("NAME: " + p1.getName() + ", ID: " + p1.getId() + ", PRICE $" + p1.getPrice());
        System.out.println("NAME: " + p2.getName() + ", ID: " + p2.getId() + ", PRICE $" + p2.getPrice());
        System.out.println("NAME: " + p3.getName() + ", ID: " + p3.getId() + ", PRICE $" + p3.getPrice());

        inv.addStock(p1, 10);
        inv.addStock(p2, 5);
        inv.addStock(p3, 2);

        System.out.println("--- TESTING INVENTORY ---");




    }

}
