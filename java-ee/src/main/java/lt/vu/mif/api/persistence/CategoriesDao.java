package lt.vu.mif.api.persistence;

import lt.vu.mif.api.entities.Category;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CategoriesDao {
    @Inject
    private EntityManager em;

    public List<Category> findAll() {
        return em.createQuery("select c from Category c", Category.class).getResultList();
    }

    public Category find(int id) {
        return em.find(Category.class, id);
    }
}
