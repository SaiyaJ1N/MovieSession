package moviesession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import moviesession.lib.Injector;
import moviesession.model.CinemaHall;
import moviesession.model.Movie;
import moviesession.model.MovieSession;
import moviesession.model.ShoppingCart;
import moviesession.model.User;
import moviesession.service.CinemaHallService;
import moviesession.service.MovieService;
import moviesession.service.MovieSessionService;
import moviesession.service.OrderService;
import moviesession.service.ShoppingCartService;
import moviesession.service.UserService;

public class Main {
    public static void main(String[] args) {
        Injector injector = Injector.getInstance("moviesession");

        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);

        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setDescription("An action film about street racing, heists, and spies.");
        movieService.add(fastAndFurious);
        System.out.println(movieService.get(fastAndFurious.getId()));
        movieService.getAll().forEach(System.out::println);

        CinemaHall firstCinemaHall = new CinemaHall();
        firstCinemaHall.setCapacity(100);
        firstCinemaHall.setDescription("first hall with capacity 100");

        CinemaHall secondCinemaHall = new CinemaHall();
        secondCinemaHall.setCapacity(200);
        secondCinemaHall.setDescription("second hall with capacity 200");

        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        cinemaHallService.add(firstCinemaHall);
        cinemaHallService.add(secondCinemaHall);

        System.out.println(cinemaHallService.getAll());
        System.out.println(cinemaHallService.get(firstCinemaHall.getId()));

        MovieSession tomorrowMovieSession = new MovieSession();
        tomorrowMovieSession.setCinemaHall(firstCinemaHall);
        tomorrowMovieSession.setMovie(fastAndFurious);
        tomorrowMovieSession.setShowTime(LocalDateTime.now().plusDays(1L));

        MovieSession yesterdayMovieSession = new MovieSession();
        yesterdayMovieSession.setCinemaHall(firstCinemaHall);
        yesterdayMovieSession.setMovie(fastAndFurious);
        yesterdayMovieSession.setShowTime(LocalDateTime.now().minusDays(1L));

        MovieSession todayMovieSession = new MovieSession();
        todayMovieSession.setCinemaHall(firstCinemaHall);
        todayMovieSession.setMovie(fastAndFurious);
        todayMovieSession.setShowTime(LocalDateTime.now());

        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(tomorrowMovieSession);
        movieSessionService.add(yesterdayMovieSession);
        movieSessionService.add(todayMovieSession);

        System.out.println(movieSessionService.get(todayMovieSession.getId()));
        System.out.println(movieSessionService.findAvailableSessions(
                fastAndFurious.getId(), LocalDate.now()));

        UserService userService = (UserService) injector.getInstance(UserService.class);
        User user = new User();
        user.setEmail("bobUser@gmail.com");
        user.setPassword("password");
        userService.add(user);

        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        shoppingCartService.registerNewShoppingCart(user);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        shoppingCartService.addSession(todayMovieSession, user);
        System.out.println(shoppingCart);

        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        System.out.println(orderService.completeOrder(shoppingCart));
        orderService.getOrdersHistory(user).forEach(System.out::println);
    }
}
