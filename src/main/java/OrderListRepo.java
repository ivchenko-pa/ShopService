import java.util.*;

public class OrderListRepo implements OrderRepo {
    Set<Order> allOrders = new HashSet<>();

    public int addOrder(Order order) {
        allOrders.add(order);
        return allOrders.size();
    }

    public int removeOrder(String id) {
        allOrders.removeIf((order) -> order.id().equals(id));
        return allOrders.size();
    }

    public Order retrieveOrder(String id) {
        if (Objects.isNull(id) || id.isEmpty()) return null;
        for (Order order : allOrders) {
            if (order.id().equals(id)) {
                return order;
            }
        }
        return null;
    }

    public List<Order> retrieveAllOrders() {
        return new ArrayList<>(allOrders);
    }
}
