package lt.vu.mif.api.persistence;

import lt.vu.mif.api.entities.Product;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ProductsDao {
    @Inject
    private EntityManager em;

    public List<Product> findAll() {
        return em.createQuery("select p from Product p join fetch p.categories", Product.class)
                .getResultList();
    }

    public List<Product> findByCategory(int categoryId) {
        return em.createQuery("select p from Product p join p.categories c where c.id = :categoryId", Product.class)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    public Product find(int id) {
        return em.find(Product.class, id);
    }

    public Product update(Product product) {
        return em.merge(product);
    }

    public void persist(Product product) {
        em.persist(product);
    }
}
