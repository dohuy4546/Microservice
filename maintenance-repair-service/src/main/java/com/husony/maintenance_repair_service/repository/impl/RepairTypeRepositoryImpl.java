package com.husony.maintenance_repair_service.repository.impl;

import com.husony.maintenance_repair_service.pojo.MaintenanceType;
import com.husony.maintenance_repair_service.pojo.RepairType;
import com.husony.maintenance_repair_service.repository.RepairTypeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class RepairTypeRepositoryImpl implements RepairTypeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<RepairType> getRepairType(Map<String, String> params) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<RepairType> q = b.createQuery(RepairType.class);
        Root<RepairType> root = q.from(RepairType.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String name = params.get("q");
            if (name != null && !name.isEmpty()) {
                Predicate p1 = b.like(b.lower(root.get("name")), "%" + name.toLowerCase() + "%");
                predicates.add(p1);
            }
            q.where(predicates.toArray(new Predicate[0]));
        }
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public void addOrUpdate(RepairType l) {
        if (l.getId() != null && entityManager.find(RepairType.class, l.getId()) != null) {
            entityManager.merge(l);
        } else {
            entityManager.persist(l);
        }
    }

    @Override
    public RepairType getRepairTypeById(Long id) {
        return entityManager.find(RepairType.class, id);
    }

    @Override
    public void deleteRepairType(Long id) {
        entityManager.remove(this.getRepairTypeById(id));
    }
}
