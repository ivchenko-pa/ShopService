import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {

        ProductRepo productRepo = new ProductRepo();

        try (var in = new Scanner(new FileInputStream("src/main/resources/ean_db.csv"));) {
            System.out.println("Available products:");
            while (in.hasNext()){
                var rowDB = in.nextLine();
                System.out.println(rowDB);
                String[] rowFields = rowDB.split(",");
                productRepo.addProduct(new Product(rowFields[0], rowFields[1], rowFields[2]));
            }
        }catch (FileNotFoundException e){
            System.out.println(e);
        }

//        OrderRepo orderRepo = new OrderMapRepo();
        OrderRepo orderRepo = new OrderListRepo();

        ShopService shop = new ShopService(productRepo, orderRepo);

        Scanner scanner = new Scanner(System.in);
        boolean skipNewOrder = false;
        String anotherWord = "";

        do{
            System.out.printf("Would you like to place an%s order? [Y/N]\n", anotherWord);
            anotherWord = "other";
            skipNewOrder = scanner.nextLine().equalsIgnoreCase("N");

            if (skipNewOrder) break;

            System.out.println("Please provide product Id's you would like to order (separated by \" \")");
            String ids = scanner.nextLine();
            String id = shop.placeOrder(ids.split(" "));
            if(Objects.nonNull(id)){
                System.out.printf("Order %s succesfully placed\n", id);
            }
        }while (!skipNewOrder);

        System.out.println("--List all orders--");
        System.out.println(shop.listOrders());

        System.out.println("--Show order with id Order_1--");
        System.out.println(shop.getOrder("ID_1"));

        System.out.println("--Print receipts for all orders in OrderRepo--");
        for (String orderId : shop.listOrders()){
            System.out.println(shop.getReceipt(orderId));
            System.out.println(("-" + Character.toString(0x2704) + "-").repeat(12));
        }
    }
}
