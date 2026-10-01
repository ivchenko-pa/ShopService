import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {

        ProductRepo productRepo = new ProductRepo();

        try (var in = new Scanner(new FileInputStream("src/main/resources/ean_db.csv"))) {
            while (in.hasNext()) {
                var rowDB = in.nextLine();
                String[] rowFields = rowDB.split(",");
                productRepo.addProduct(new Product(rowFields[0], rowFields[1], rowFields[2]));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

//        OrderRepo orderRepo = new OrderMapRepo();
        OrderRepo orderRepo = new OrderListRepo();

        ShopService shop = new ShopService(productRepo, orderRepo);

        Scanner scanner = new Scanner(System.in);
        boolean skipNewOrder = false;
        String anotherWord = "";

        do {
            System.out.printf("Would you like to place an%s order? [Y/N]\n", anotherWord);
            anotherWord = "other";
            skipNewOrder = scanner.nextLine().equalsIgnoreCase("N");

            if (skipNewOrder) break;

            System.out.println("Available products:");
            for (String productEntry : shop.listProducts()) {
                System.out.println(productEntry);
            }
            System.out.println("Please provide product id(s) you would like to order (separated by \" \")");
            String ids = scanner.nextLine();
            String orderId = shop.placeOrder(ids.split(" "));
            if (Objects.nonNull(orderId)) {
                System.out.println("Order placed: " + orderId);
            }
        } while (!skipNewOrder);

        // Demo: To list all manually placed orders
        System.out.println("--List all orders--");
        System.out.println(shop.listOrders());

        // Demo: To show order with ID_2
        System.out.println("--Show order with id ID_2--");
        System.out.println(shop.getOrder("ID_2"));

        // Demo: To place n random orders
        int amountOfRandomOrdersToPlace = 20;
        boolean skipDemo = false;
        anotherWord = "";
        do {
            System.out.printf("Would you place%s %d random orders? [Y/N]\n", anotherWord, amountOfRandomOrdersToPlace);
            skipDemo = scanner.nextLine().equalsIgnoreCase("N");
            anotherWord = " another";

            if (skipDemo) break;
            List<Product> productsList = new ArrayList<>(productRepo.getAllProducts());
            for (int i = 0; i < amountOfRandomOrdersToPlace; i++) {
                int productsAmountForOrder = (int) (20 * Math.random());
                String[] idsArray = new String[productsAmountForOrder];
                for (int j = 0; j < productsAmountForOrder; j++) {
                    int productIndex = (int) (10 * Math.random());
                    idsArray[j] = productsList.get(productIndex).id();
                }
                String orderId = shop.placeOrder(idsArray);
                if (Objects.nonNull(orderId)) System.out.println("Order placed: " + orderId);
            }
        } while (!skipDemo);

        // Demo: To print receipts for all placed orders
        System.out.println("--Print receipts for all orders in OrderRepo--");
        for (String orderId : shop.listOrders()) {
            System.out.println(shop.getReceipt(orderId));
            System.out.println(("\u001B[34m-" + Character.toString(0x2704) + "-\u001B[0m").repeat(12));
        }
    }
}
