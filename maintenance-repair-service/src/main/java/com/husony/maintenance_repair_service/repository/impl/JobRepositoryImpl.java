package com.husony.maintenance_repair_service.repository.impl;

import com.husony.maintenance_repair_service.pojo.Job;
import com.husony.maintenance_repair_service.pojo.Maintenance;
import com.husony.maintenance_repair_service.pojo.RepairType;
import com.husony.maintenance_repair_service.repository.JobRepository;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class JobRepositoryImpl implements JobRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Job> getJob(Map<String, String> params) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Job> q = b.createQuery(Job.class);
        Root<Job> root = q.from(Job.class);
        q.select(root);
        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String kw = params.get("q");
            if (kw != null && !kw.isEmpty()) {
                Predicate p1 = b.like(root.get("name"), String.format("%%%s%%", kw));
                predicates.add(p1);
            }

            String maintenanceId = params.get("maintenanceId");
            if (maintenanceId != null && !maintenanceId.isEmpty()) {
                Predicate p2 = b.equal(root.get("maintenanceId").get("id"), Long.parseLong(maintenanceId));
                predicates.add(p2);
            }

            String repairId = params.get("repairId");
            if (repairId != null && !repairId.isEmpty()) {
                Predicate p3 = b.equal(root.get("repairId").get("id"), Long.parseLong(repairId));
                predicates.add(p3);
            }
            q.where(predicates.toArray(new Predicate[0]));
        }
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public void addOrUpdate(Job l) {
        if (l.getId() != null && entityManager.find(Job.class, l.getId()) != null) {
            entityManager.merge(l);
        } else {
            entityManager.persist(l);
        }
    }

    @Override
    public Job getJobById(Long id) {
        return entityManager.find(Job.class, id);
    }

    @Override
    public void deleteJob(Long id) {
        entityManager.remove(this.getJobById(id));
    }
}
