package moviesession.dao.impl;

import java.util.List;
import moviesession.dao.OrderDao;
import moviesession.exception.DataProcessingException;
import moviesession.lib.Dao;
import moviesession.model.Order;
import moviesession.model.User;
import moviesession.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public List<Order> getAllOrders(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM Order o "
                                    + "LEFT JOIN FETCH o.tickets t "
                                    + "LEFT JOIN FETCH o.user u "
                                    + "LEFT JOIN FETCH t.movieSession ms "
                                    + "LEFT JOIN FETCH ms.movie m "
                                    + "LEFT JOIN FETCH ms.cinemaHall c "
                                    + "WHERE o.user =:user", Order.class)
                    .setParameter("user", user)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all orders by user: " + user, e);
        }
    }

    @Override
    public Order add(Order order) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(order);
            transaction.commit();
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save order: " + order, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
