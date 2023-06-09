package lt.vu.mif.api.persistence;

import lt.vu.mif.api.entities.Order;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class OrdersDao {
    @Inject
    private EntityManager em;

    public void persist(Order order) {
        em.persist(order);
    }

    public List<Order> findAll() {
        return em.createQuery("select o from Order o", Order.class).getResultList();
    }

    public Order find(int id) {
        return em.createQuery("select o from Order o where o.id = :id", Order.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
