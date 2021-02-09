public class Main {
    public static void main(String[] args) {
        Inventory inv = new Inventory();
        StoreManager store = new StoreManager(inv);

        Product p1 = new Product("Apple", 1, 1.00);
        Product p2 = new Product("Banana", 2, 2.00);
        Product p3 = new Product("Candy", 3, 4.00);

        System.out.println("--- TESTING PRODUCT FUNC ---");
        System.out.println("NAME: " + p1.getName() + ", ID: " + p1.getId() + ", PRICE $" + p1.getPrice());
        System.out.println("NAME: " + p2.getName() + ", ID: " + p2.getId() + ", PRICE $" + p2.getPrice());
        System.out.println("NAME: " + p3.getName() + ", ID: " + p3.getId() + ", PRICE $" + p3.getPrice());


        System.out.println("\n--- TESTING INVENTORY ---");
        inv.addStock(p1, 10);
        inv.addStock(p2, 5);
        inv.addStock(p3, 2);

        //APPLE SHOULD WORK
        System.out.println("Product: " + inv.getProduct(1));
        System.out.println("Name: " + inv.getProdName(1));
        System.out.println("Price: " + inv.getProdPrice(1));
        System.out.println("Stock: " + inv.getStock(1));
        inv.addStock(p1, 5);
        System.out.println("Added 5 Stock: " + inv.getStock(1));
        inv.delStock(1, 5);
        System.out.println("Removed 5 Stock: " + inv.getStock(1));
        inv.delStock(1, 20);
        System.out.println("Try to remove 20 Stock: " + inv.getStock(1));
        System.out.println("Product Information: " + inv.getInformation(p1)[0] + " " + inv.getInformation(p1)[1] + " " + inv.getInformation(p1)[2]);

        //NOT SUPPOSED TO WORK
//        System.out.println("\nNOT SUPPOSED TO WORK");
//        System.out.println("Product: " + inv.getProduct(4));
//        System.out.println("Name: " + inv.getProdName(4));
//        System.out.println("Price: " + inv.getProdPrice(4));
//        System.out.println("Stock: " + inv.getStock(4));


        System.out.println("\n--- TESTING STORE MANAGER ---");
        //APPLE SHOULD WORK
        System.out.println("SHOULD WORK");
        System.out.println("Checking Stock p1: " + store.checkStock(p1));
        System.out.println("Checking Stock p2: " + store.checkStock(p2));
        System.out.println("Checking Stock p3: " + store.checkStock(p3));
        int[][] cart = new int[3][2];
        cart[0][0] = 1; //id apple
        cart[0][1] = 10; //stock
        cart[1][0] = 2; //id banana
        cart[1][1] = 4; //stock
        cart[2][0] = 3; //id candy
        cart[2][1] = 1; //stock
        System.out.print("Total: " + store.transaction(cart)); //should be $22
        System.out.println("\nChecking Stock p1: " + store.checkStock(p1)); //0
        System.out.println("Checking Stock p2: " + store.checkStock(p2)); //1
        System.out.println("Checking Stock p3: " + store.checkStock(p3)); //1

        System.out.println("\nSHOULD NOT WORK");
        System.out.println("Checking Stock p1: " + store.checkStock(p1));
        System.out.println("Checking Stock p2: " + store.checkStock(p2));
        System.out.println("Checking Stock p3: " + store.checkStock(p3));

        int[][] cart2 = new int[3][2];
        cart2[0][0] = 1; //id apple
        cart2[0][1] = 11; //stock
        cart2[1][0] = 2; //id banana
        cart2[1][1] = 4; //stock
        cart2[2][0] = 3; //id candy
        cart2[2][1] = 1; //stock
        System.out.print("Total: " + store.transaction(cart)); //should be $22

    }

}
