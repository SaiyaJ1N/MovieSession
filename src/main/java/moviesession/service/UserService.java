package moviesession.service;

import java.util.Optional;
import moviesession.model.User;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);
}
