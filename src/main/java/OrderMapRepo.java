import java.util.*;

public class OrderMapRepo implements OrderRepo {

    Map<String, Order> allOrders = new HashMap<>();

    public int addOrder(Order order) {
        allOrders.put(order.id(), order);
        return allOrders.size();
    }

    public int removeOrder(String id) {
        allOrders.remove(id);
        return allOrders.size();
    }

    public Order retrieveOrder(String id) {
        if (Objects.isNull(id) || id.isEmpty()) return null;
        return allOrders.get(id);
    }

    public List<Order> retrieveAllOrders() {
        return new ArrayList<>(allOrders.values());
    }
}
