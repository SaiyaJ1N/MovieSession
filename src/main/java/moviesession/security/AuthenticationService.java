package moviesession.security;

import moviesession.exception.AuthenticationException;
import moviesession.exception.RegistrationException;
import moviesession.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password) throws RegistrationException;
}
