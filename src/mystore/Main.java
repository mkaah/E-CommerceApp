package mystore;

public class Main {
    public static void main(String[] args) {
        StoreManager store = new StoreManager();
        Inventory inv = store.getInventory();
        store.assignCartID();
        store.assignCartID();

        ShoppingCart cart1 = store.getCart(1);
        ShoppingCart cart2 = store.getCart(2);


//        inv.initialize();
//        p1 =
//
//        System.out.println(p1.getId());
//        System.out.println(p2.getId());
//        System.out.println(p3.getId());

//        System.out.println("\n--- ORIGINAL INVENTORY ---");
//        printInvInfo(inv, p1);
//        printInvInfo(inv, p2);
//        printInvInfo(inv, p3);
//
//        System.out.println("\n--- ADDING TO CART ---");
//        store.addToCart(cart1,1, 2);
//        store.addToCart(cart1,1, 10); //should not work
//        store.addToCart(cart1,2, 5);
//        store.addToCart(cart1,3, 5);
//
//        store.addToCart(cart2,1,5);
//        store.addToCart(cart2,2,3);
//        store.addToCart(cart2,3,1);
//
//        System.out.println("\nCart -- Stock | Name | ID | Price");
//        printCartInfo(cart1, p1);
//        printCartInfo(cart1, p2);
//        printCartInfo(cart1, p3);
//
//        printCartInfo(cart2, p1);
//        printCartInfo(cart2, p2);
//        printCartInfo(cart2, p3);
//
//        System.out.println("Inventory -- Stock | Name | ID | Price");
//        printInvInfo(inv, p1);
//        printInvInfo(inv, p2);
//        printInvInfo(inv, p3);
//
//        System.out.println("\n--- REMOVE FROM CART ---");
//        store.delFromCart(cart1,2,2);
//        printCartInfo(cart1, p1);
//        printCartInfo(cart1, p2);
//        printCartInfo(cart1, p3);
//        printCartInfo(cart2, p1);
//        printCartInfo(cart2, p2);
//        printCartInfo(cart2, p3);
//
//        printInvInfo(inv, p1);
//        printInvInfo(inv, p2);
//        printInvInfo(inv, p3);


    }

    private static void printInvInfo(Inventory inv, Product p){
        System.out.println("Inventory: " + inv.getStock(p.getId()) +"|" + inv.getInformation(p)[0] + "|" + inv.getInformation(p)[1] + "|" + inv.getInformation(p)[2]);
    }

    private static void printCartInfo(ShoppingCart cart, Product p){
        System.out.println("Cart" + cart.getId() + ": " + cart.getStock(p.getId()) +"|" + cart.getInformation(p)[0] + "|" + cart.getInformation(p)[2]);
    }

}
