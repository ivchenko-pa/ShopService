import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {

        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(new Product("1", "Milk"));
        productRepo.addProduct(new Product("2", "Eggs"));
        productRepo.addProduct(new Product("3", "Lemon"));
        productRepo.addProduct(new Product("4", "Apple"));
        productRepo.addProduct(new Product("5", "Potato"));

//        OrderRepo orderRepo = new OrderMapRepo();
        OrderRepo orderRepo = new OrderListRepo();

        ShopService shop = new ShopService(productRepo, orderRepo);

        Scanner scanner = new Scanner(System.in);
        boolean placeNewOrder = false;
        String anotherWord = "";
        do{
            System.out.printf("Would you like to place an%s order? [Y/N]\n", anotherWord);
            anotherWord = "other";
            placeNewOrder = (!scanner.nextLine().equalsIgnoreCase("N")) ? true : false;
            if (!placeNewOrder) break;
            System.out.println("Please provide product Id's you would like to order (separated by \" \")");
            String ids = scanner.nextLine();
            String id = shop.placeOrder(ids.split(" "));
            if(Objects.nonNull(id)){
                System.out.printf("Order %s succesfully placed\n", id);
            }
        }while (placeNewOrder);

        System.out.println("--List all orders--");
        shop.listOrders();
        System.out.println("--Show order with id Order_1--");
        System.out.println(shop.getOrder("Order_1"));


    }
}
