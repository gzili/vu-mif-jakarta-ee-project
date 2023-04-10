package lt.vu.mif.api.persistence;

import lt.vu.mif.api.entities.OrderItem;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class OrderItemsDao {
    @Inject
    private EntityManager em;

    public void persist(OrderItem orderItem) {
        em.persist(orderItem);
    }
}
