package moviesession.dao;

import java.util.Optional;
import moviesession.model.User;

public interface UserDao {
    User add(User user);

    Optional<User> findByEmail(String email);
}
