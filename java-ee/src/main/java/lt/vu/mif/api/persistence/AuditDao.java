package lt.vu.mif.api.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lt.vu.mif.api.entities.AuditEntry;

@ApplicationScoped
public class AuditDao {
    @Inject
    private EntityManager em;

    public void persist(AuditEntry auditEntry) {
        em.persist(auditEntry);
    }
}
