import java.util.List;
import java.util.Set;

public interface OrderRepo {
    int addOrder(Order order);
    int removeOrder(String id);
    Order retrieveOrder(String id);
    List<Order> retrieveAllOrders();
}
