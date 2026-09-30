import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ShopService {

    private static int ordersCounter = 0;
    ProductRepo productRepo = new ProductRepo();
    OrderListRepo orderListRepo = new OrderListRepo();


    public void addProductToRepo(Product product){
        productRepo.addProduct(product);
    }

    public void placeOrder(String [] ids){
        if (Objects.isNull(ids) || ids.length == 0 || ids[0].isEmpty()){
            System.out.println("Order cancelled: No id's provided to place the order");
            return;
        }
        List<Product> listOfProducts = new ArrayList<>();
        for (String id : ids){
            Product product = productRepo.retrieveProduct(id);
            if(Objects.isNull(product)){
                System.out.printf("The product with ID: %s is out of stock.\n", id);
            }else{
                listOfProducts.add(product);
            }
        }

        if (listOfProducts.size() > 0){
            ordersCounter++;
            orderListRepo.addOrder(new Order("Order_" + String.valueOf(ordersCounter), listOfProducts));
        }
    }

    public Order getOrder(String id){
        return orderListRepo.retrieveOrder(id);
    }

    public void listOrders(){
        orderListRepo.retrieveAllOrders().forEach(order-> System.out.println(order));
    }

}
