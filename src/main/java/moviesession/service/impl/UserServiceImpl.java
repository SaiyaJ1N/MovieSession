package moviesession.service.impl;

import java.util.Optional;
import moviesession.dao.UserDao;
import moviesession.lib.Inject;
import moviesession.lib.Service;
import moviesession.model.User;
import moviesession.service.UserService;
import moviesession.util.HashUtil;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        user.setSalt(HashUtil.getSalt());
        user.setPassword(HashUtil.hashPassword(user.getPassword(), user.getSalt()));
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
