package moviesession.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import moviesession.dao.OrderDao;
import moviesession.lib.Inject;
import moviesession.lib.Service;
import moviesession.model.Order;
import moviesession.model.ShoppingCart;
import moviesession.model.Ticket;
import moviesession.model.User;
import moviesession.service.OrderService;
import moviesession.service.ShoppingCartService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order newOrder = new Order();
        List<Ticket> tickets = new ArrayList<>(shoppingCart.getTickets());
        newOrder.setTickets(tickets);
        newOrder.setOrderDate(LocalDateTime.now());
        newOrder.setUser(shoppingCart.getUser());
        shoppingCartService.clearShoppingCart(shoppingCart);
        return orderDao.add(newOrder);
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        return orderDao.getAllOrders(user);
    }
}
