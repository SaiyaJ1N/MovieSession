package moviesession.service;

import moviesession.model.MovieSession;
import moviesession.model.ShoppingCart;
import moviesession.model.User;

public interface ShoppingCartService {
    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clearShoppingCart(ShoppingCart cart);
}
