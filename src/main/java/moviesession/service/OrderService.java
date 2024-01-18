package moviesession.service;

import java.util.List;
import moviesession.model.Order;
import moviesession.model.ShoppingCart;
import moviesession.model.User;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}