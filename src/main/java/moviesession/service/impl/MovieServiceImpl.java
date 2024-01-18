package moviesession.service.impl;

import java.util.List;
import moviesession.dao.MovieDao;
import moviesession.lib.Inject;
import moviesession.lib.Service;
import moviesession.model.Movie;
import moviesession.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
    @Inject
    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id).get();
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
