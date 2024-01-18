package moviesession.dao;

import java.util.List;
import moviesession.model.Order;
import moviesession.model.User;

public interface OrderDao {
    List<Order> getAllOrders(User user);

    Order add(Order order);
}
