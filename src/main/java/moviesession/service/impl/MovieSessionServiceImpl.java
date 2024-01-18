package moviesession.service.impl;

import java.time.LocalDate;
import java.util.List;
import moviesession.dao.MovieSessionDao;
import moviesession.lib.Inject;
import moviesession.lib.Service;
import moviesession.model.MovieSession;
import moviesession.service.MovieSessionService;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    @Inject
    private MovieSessionDao sessionDao;

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return sessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession get(Long id) {
        return sessionDao.get(id).get();
    }

    @Override
    public MovieSession add(MovieSession session) {
        return sessionDao.add(session);
    }
}
